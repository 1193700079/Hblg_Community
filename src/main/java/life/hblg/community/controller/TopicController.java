package life.hblg.community.controller;

import life.hblg.community.dto.TopicDTO;
import life.hblg.community.mapper.TopicMapper;
import life.hblg.community.service.TopicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

//话题详情页的路由控制器
@Controller
public class TopicController {

    @Autowired
    private TopicMapper topicMapper;
    @Autowired
    private TopicService topicService;

    @GetMapping("topic/{id}")
    public String topic(@PathVariable(name = "id") Integer id,
                        Model model){

        TopicDTO topicDTO =topicService.getTopicDetialById(id);  //返回一个TopicDTO
        model.addAttribute ( "topicDTO",topicDTO );
        return "topic";
    }
}
