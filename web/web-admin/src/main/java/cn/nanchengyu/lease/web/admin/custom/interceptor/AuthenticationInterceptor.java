package cn.nanchengyu.lease.web.admin.custom.interceptor;

import cn.nanchengyu.lease.common.utils.JwtUtil;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

/**
 * ClassName: AuthenticationInterceptor
 * Package: cn.nanchengyu.lease.web.admin.custom.interceptor
 * Description:
 *
 * @Author 南城余
 * @Create 2024/6/5 17:08
 * @Version 1.0
 */
@Component
public class AuthenticationInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String token = request.getHeader("access-token"); //此处与前端约定 token 的名字access-token

        JwtUtil.parseToken(token);
        return true;

    }
}
