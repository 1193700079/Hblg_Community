package life.hblg.community.controller;

import life.hblg.community.mapper.TopicMapper;
import life.hblg.community.mapper.UserMapper;
import life.hblg.community.model.Topic;
import life.hblg.community.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

@Controller
public class publishController {

    @Autowired
    private TopicMapper topicMapper;

    @Autowired
    private UserMapper userMapper;

    @GetMapping("/publish")
    public String publish(){
        return "publish";
    }

    @PostMapping("/publish")   //接受post请求
    public String doPublish(@RequestParam(value = "title",required = false) String title,
                            @RequestParam(value = "description",required = false) String description,
                            @RequestParam(value = "tag",required = false) String tag,
                            HttpServletRequest request,
                            Model model){

        System.out.println (description );
        //model中的东西可以展现到前端去
        model.addAttribute("title",title);
        model.addAttribute("description",description);
        model.addAttribute("tag",tag);

        if(title == null || title == ""){
            model.addAttribute ( "error" ,"标题不能为空");
            return "publish";
        }
        if(description == null || description == ""){
            model.addAttribute ( "error" ,"内容不能为空");
            return "publish";
        }
        if(tag == null || tag == ""){
            model.addAttribute ( "error" ,"标签不能为空");
            return "publish";
        }

        Topic topic = new Topic ();
        topic.setTitle ( title );
        topic.setDescription ( description );
        topic.setTag ( tag );
        User user = getUserMsg ( request ); //获取User信息
        if(user==null){
            model.addAttribute ( "error","用户未登陆" );//属性值 和 属性名
            return "publish";
        }
        topic.setCreateId ( user.getId () );
        topic.setGmtCreate ( System.currentTimeMillis () );
        topic.setGmtModify ( topic.getGmtCreate () );
        topicMapper.insert ( topic );

        return "redirect:/" ; //重定向 返回首页


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
