package cn.nanchengyu.lease.web.admin.controller.login;


import cn.nanchengyu.lease.common.login.LoginUserHolder;
import cn.nanchengyu.lease.common.result.Result;
import cn.nanchengyu.lease.common.utils.JwtUtil;
import cn.nanchengyu.lease.model.entity.SystemUser;
import cn.nanchengyu.lease.web.admin.service.LoginService;
import cn.nanchengyu.lease.web.admin.vo.login.CaptchaVo;
import cn.nanchengyu.lease.web.admin.vo.login.LoginVo;
import cn.nanchengyu.lease.web.admin.vo.system.user.SystemUserInfoVo;
import io.jsonwebtoken.Claims;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@Tag(name = "后台管理系统登录管理")
@RestController
@RequestMapping("/admin")
public class LoginController {
    @Autowired
    private LoginService loginService;
    @Operation(summary = "获取图形验证码")
    @GetMapping("login/captcha")
    public Result<CaptchaVo> getCaptcha() {
       CaptchaVo result = loginService.getCaptcha();
        return Result.ok(result);
    }

    @Operation(summary = "登录")
    @PostMapping("login")
    public Result<String> login(@RequestBody LoginVo loginVo) {
       String jwt = loginService.login(loginVo);
        return Result.ok(jwt);
    }

    @Operation(summary = "获取登陆用户个人信息")
    @GetMapping("info")
    public Result<SystemUserInfoVo> info() {
        Long userId = LoginUserHolder.getLoginUser().getUserId();
        SystemUserInfoVo systemUserInfoVo = loginService.getLoginUserInfo(userId);
        return Result.ok(systemUserInfoVo);
    }
}