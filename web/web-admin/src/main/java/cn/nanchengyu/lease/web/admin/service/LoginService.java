package cn.nanchengyu.lease.web.admin.service;


import cn.nanchengyu.lease.web.admin.vo.login.CaptchaVo;
import cn.nanchengyu.lease.web.admin.vo.login.LoginVo;

public interface LoginService {

    CaptchaVo getCaptcha();

    String login(LoginVo loginVo);
}
