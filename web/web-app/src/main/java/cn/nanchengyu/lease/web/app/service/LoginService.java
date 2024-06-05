package cn.nanchengyu.lease.web.app.service;

import cn.nanchengyu.lease.web.app.vo.user.LoginVo;

public interface LoginService {
    void getCode(String phone);

    String login(LoginVo loginVo);
}
