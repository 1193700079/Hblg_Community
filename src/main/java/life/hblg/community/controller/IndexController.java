package life.hblg.community.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller  //处理URL请求的
public class IndexController {

    //接受hello请求  其中@RequestParam 是对于接受请求后面的参数的设置
    @GetMapping("")
    public String say(@RequestParam(name="name", required=false, defaultValue="World") String name,
                      Model model){
        model.addAttribute ( "name",name ); //将request Scope 添加到model层中
        return "index" ;  //index 为view层
    }
}
