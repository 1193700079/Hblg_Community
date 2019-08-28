package life.hblg.community.intercepter;

import life.hblg.community.mapper.UserMapper;
import life.hblg.community.model.User;
import life.hblg.community.model.UserExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

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
                //使用 mybatis generator 的方法
                UserExample userExample = new UserExample ();  //创建实例
                userExample.createCriteria ().andTokenEqualTo ( token );  //使用方法
                List <User> users = userMapper.selectByExample ( userExample );
                if(users.size () != 0){
                    request.getSession ().setAttribute ( "user",users.get ( 0 ) );
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
