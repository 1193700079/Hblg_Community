package life.hblg.community.controller;

import life.hblg.community.mapper.UserMapper;
import life.hblg.community.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

@Controller  //处理URL请求的
public class IndexController {

    @Autowired
    UserMapper userMapper;
    //接受hello请求  其中@RequestParam 是对于接受请求后面的参数的设置
    @GetMapping("")
    public String say(HttpServletRequest request){

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
        return "index" ;  //index 为view层
    }
//    @GetMapping("")
//    public String say(@RequestParam(name="name", required=false, defaultValue="World") String name,
//                      Model model){
//        model.addAttribute ( "name",name ); //将request Scope 添加到model层中
//        return "index" ;  //index 为view层
//    }
}
