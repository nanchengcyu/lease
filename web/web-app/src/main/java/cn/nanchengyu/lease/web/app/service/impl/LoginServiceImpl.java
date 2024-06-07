package cn.nanchengyu.lease.web.app.service.impl;

import cn.nanchengyu.lease.common.constant.RedisConstant;
import cn.nanchengyu.lease.common.exception.LeaseException;
import cn.nanchengyu.lease.common.result.ResultCodeEnum;
import cn.nanchengyu.lease.common.utils.CodeUtil;
import cn.nanchengyu.lease.common.utils.JwtUtil;
import cn.nanchengyu.lease.model.entity.UserInfo;
import cn.nanchengyu.lease.model.enums.BaseStatus;
import cn.nanchengyu.lease.web.app.mapper.UserInfoMapper;
import cn.nanchengyu.lease.web.app.service.LoginService;
import cn.nanchengyu.lease.web.app.service.SmsService;
import cn.nanchengyu.lease.web.app.vo.user.LoginVo;
import cn.nanchengyu.lease.web.app.vo.user.UserInfoVo;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.concurrent.TimeUnit;

@Service
public class LoginServiceImpl implements LoginService {
    @Autowired
    private SmsService smsService;
    @Autowired
    private StringRedisTemplate stringRedisTemplate;
    @Autowired
    private UserInfoMapper userInfoMapper;

    @Override
    public void getCode(String phone) {
        String code = CodeUtil.getRandomCode(6);
        String key = RedisConstant.APP_LOGIN_PREFIX + phone;
        smsService.sendCode(phone, code);
        //限制用户频繁发送
        Boolean hasKey = stringRedisTemplate.hasKey(key);
        if (hasKey) {
            Long ttl = stringRedisTemplate.getExpire(key, TimeUnit.SECONDS);
            if (RedisConstant.APP_LOGIN_CODE_RESEND_TIME_SEC - ttl < RedisConstant.APP_LOGIN_CODE_RESEND_TIME_SEC) {
                throw new LeaseException(ResultCodeEnum.APP_SEND_SMS_TOO_OFTEN);
            }

        }
        //保存验证码到Redis
        stringRedisTemplate.opsForValue().set(key, code, RedisConstant.ADMIN_LOGIN_CAPTCHA_TTL_SEC, TimeUnit.MINUTES);

    }

    @Override
    public String login(LoginVo loginVo) {
        //1.判断手机号码和验证码是否为空
        if (!StringUtils.hasText(loginVo.getPhone())) {
            throw new LeaseException(ResultCodeEnum.APP_LOGIN_PHONE_EMPTY);
        }

        if (!StringUtils.hasText(loginVo.getCode())) {
            throw new LeaseException(ResultCodeEnum.APP_LOGIN_CODE_EMPTY);
        }
        //2.判断验证码是否正确
        String key = RedisConstant.APP_LOGIN_PREFIX + loginVo.getPhone();
        String code = stringRedisTemplate.opsForValue().get(key);
        if (code == null) {
            throw new LeaseException(ResultCodeEnum.APP_LOGIN_CODE_EXPIRED);
        }
        if (!code.equals(loginVo.getCode())) {
            throw new LeaseException(ResultCodeEnum.APP_LOGIN_CODE_ERROR);
        }
        //3.判断用户是否存在
        LambdaQueryWrapper<UserInfo> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(UserInfo::getPhone, loginVo.getPhone());
        UserInfo userInfo = userInfoMapper.selectOne(queryWrapper);
        if (userInfo == null) {
            //注册
            userInfo = new UserInfo();
            userInfo.setPhone(loginVo.getPhone());
            userInfo.setNickname("用户" + loginVo.getPhone());
            userInfo.setStatus(BaseStatus.ENABLE);
            userInfoMapper.insert(userInfo);
        } else {
            //账号是否禁用
            if (userInfo.getStatus() == BaseStatus.DISABLE) {
                throw new LeaseException(ResultCodeEnum.APP_ACCOUNT_DISABLED_ERROR);
            }

        }

        return JwtUtil.createToken(userInfo.getId(), userInfo.getPhone());
    }

    @Override
    public UserInfoVo getLoginUserById(Long userId) {
        UserInfo userInfo = userInfoMapper.selectById(userId);
        return new UserInfoVo(userInfo.getNickname(), userInfo.getAvatarUrl());
    }
}
