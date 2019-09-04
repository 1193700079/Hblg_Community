package life.hblg.community.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import life.hblg.community.dto.CommentDTO;
import life.hblg.community.dto.ResultDTO;
import life.hblg.community.dto.TopicDTO;
import life.hblg.community.dto.TopicDTOAndCommentDTO;
import life.hblg.community.enums.CommentTypeEnum;
import life.hblg.community.model.Topic;
import life.hblg.community.service.CommentService;
import life.hblg.community.service.TopicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

//话题详情页的路由控制器
@Controller
@Api(value = "话题详情页面")
public class TopicController {

    @Autowired
    private TopicService topicService;

    @Autowired
    private CommentService commentService;

    @ResponseBody
    @ApiOperation(value="获取话题详情信息以及评论信息", notes="")
    @GetMapping("topic/{id}")
    public TopicDTOAndCommentDTO detailTopic(@PathVariable(name = "id") Integer id,
                                                     HttpServletRequest request){
        //发送话题的用户ID！= 编辑用户的ID
    /*   User user = (User) request.getSession ().getAttribute ( "user" );  //编辑用户的ID
        User topicUser =  topicService.findById(id);//根据id查到发送话题的用户ID
        Boolean userIsLoginIn =( user.getId () == topicUser.getId ());*/

        TopicDTO topicDTO =topicService.getTopicDetialById(id);
//        model.addAttribute ( "topicDTO",topicDTO );

        List<CommentDTO> commentDTOs = commentService.listbyid(id, CommentTypeEnum.TOPIC_TYPE.getType ( ) ); //根据话题的id找到其回复
//        model.addAttribute ( "comments",commentDTOs );

        //增加阅读数功能(有bug 稍后完善)
        topicService.incView(id);

        TopicDTOAndCommentDTO topicDTOAndCommentDTO = new TopicDTOAndCommentDTO ();
        topicDTOAndCommentDTO.setCommentDTO ( commentDTOs );
        topicDTOAndCommentDTO.setTopicDTO ( topicDTO );

        return topicDTOAndCommentDTO;
    }


    @ResponseBody
    @ApiOperation(value="获取相关话题", notes="id表示当前话题")
    @GetMapping("topic/{id}/relevant")
    public ResultDTO relevantTopic(@PathVariable(name = "id") Integer id){
        List<Topic> topics = topicService.selectRelevantTopic(id);
        return ResultDTO.okOf (topics);
    }

}
