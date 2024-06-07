package cn.nanchengyu.lease.web.app.service;

import cn.nanchengyu.lease.web.app.vo.user.LoginVo;
import cn.nanchengyu.lease.web.app.vo.user.UserInfoVo;

public interface LoginService {
    void getCode(String phone);

    String login(LoginVo loginVo);

    UserInfoVo getLoginUserById(Long userId);
}
