package life.hblg.community.controller;

import life.hblg.community.dto.PaginationDTO;
import life.hblg.community.mapper.TopicMapper;
import life.hblg.community.mapper.UserMapper;
import life.hblg.community.model.User;
import life.hblg.community.service.TopicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

@Controller  //处理URL请求的
public class IndexController {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private TopicMapper topicMapper;

    @Autowired
    private TopicService topicService;

    @GetMapping("") //pageId 表示从前端接受的当前为第几页  size为一页显示topic的数量
    public String say(HttpServletRequest request,
                      Model model,
                      @RequestParam(name = "pageId",defaultValue = "1")Integer pageId,
                      @RequestParam(name = "size",defaultValue = "2")Integer size){


        PaginationDTO paginationDTO = topicService.getList(pageId, size);
        model.addAttribute ( "paginationDTO",paginationDTO );
        return "index" ;  //index 为view层
    }
    //接受hello请求  其中@RequestParam 是对于接受请求后面的参数的设置
//    @GetMapping("")
//    public String say(@RequestParam(name="name", required=false, defaultValue="World") String name,
//                      Model model){
//        model.addAttribute ( "name",name ); //将request Scope 添加到model层中
//        return "index" ;  //index 为view层
//    }

}