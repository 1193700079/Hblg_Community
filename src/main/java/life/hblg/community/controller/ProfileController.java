package life.hblg.community.controller;

import life.hblg.community.dto.PaginationDTO;
import life.hblg.community.mapper.UserMapper;
import life.hblg.community.model.Topic;
import life.hblg.community.model.User;
import life.hblg.community.service.TopicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

@Controller
public class ProfileController {

    @Autowired
    UserMapper userMapper;
    @Autowired
    TopicService topicService;

    @GetMapping("/profile/{action}")
    public String profile(@PathVariable(name = "action")String action,
                          HttpServletRequest request,
                          Model model,
                          @RequestParam(name = "pageId",defaultValue = "1")Integer pageId,
                          @RequestParam(name = "size",defaultValue = "2")Integer size){
        User user = getUserMsg ( request );
        if(user==null){
            return "redirect:/";
        }

        if(action.equals ( "topics" )){
            model.addAttribute ( "section","topics" );
            model.addAttribute ( "sectionName","我的话题哎呀~" );
        }else if(action.equals ( "replies" )){
            model.addAttribute ( "section","replies" );
            model.addAttribute ( "sectionName","我的回复BiuBiu~~~" );
        }


        PaginationDTO paginationDTO =  topicService.getListByUserId(user.getId (),pageId,size);
        model.addAttribute ( "paginationDTO",paginationDTO );
        return "profile";
    }
    //获取User信息
    public User getUserMsg(HttpServletRequest request){
        Cookie[] cookies=request.getCookies ();
        for (Cookie cookie : cookies) {
            if (cookie.getName ( ).equals ( "token" )) {
                String token = cookie.getValue ( );
                User user = userMapper.findByToken ( token );
                if (user != null) {
                    request.getSession ().setAttribute ( "user",user );
                    return user;
                }
                break;
            }
        }
        return null;
    }
}
