package life.hblg.community.intercepter;

import life.hblg.community.mapper.UserMapper;
import life.hblg.community.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Service
public class SessionInterceptor implements HandlerInterceptor {
    @Autowired
    UserMapper userMapper;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        Cookie[] cookies=request.getCookies ();
        for (Cookie cookie : cookies) {
            if(cookie.getName ().equals ( "token" )){
                String token = cookie.getValue ();
                User user = userMapper.findByToken ( token );
                if(user != null){
                    request.getSession ().setAttribute ( "user",user );
                }
                break;
            }
        }

//        默认返回true 不然后面的代码无法执行
        return  true;

    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {

    }
}
