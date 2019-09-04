package life.hblg.community.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import life.hblg.community.CommentType;
import life.hblg.community.dto.CommentCreateDTO;
import life.hblg.community.dto.CommentDTO;
import life.hblg.community.dto.ResultDTO;
import life.hblg.community.enums.CommentTypeEnum;
import life.hblg.community.exception.CustomizeErrorCode;
import life.hblg.community.exception.CustomizeException;
import life.hblg.community.model.Comment;
import life.hblg.community.model.User;
import life.hblg.community.service.CommentService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
@Api(value = "CommentController|评论回复" ,tags={"bb","dd"})
public class CommentController {

    @Autowired
    private CommentService commentService;

    @ApiOperation(value="一级回复信息", notes="数据库的基本操作")
    @ResponseBody
    @RequestMapping(value = "/comment",method = RequestMethod.POST)
    public Object post(@RequestBody CommentCreateDTO commentDTO,
                       HttpServletRequest request){

        /* 持久化校验功能*/
        User user = (User) request.getSession ( ).getAttribute ( "user" );
       /* if(user==null){
            return ResultDTO.errorOf ( CustomizeErrorCode.NOT_LOGIN_IN );
        }*/
        /* 服务端进行校验 防止数据进入数据库 */
       if( commentDTO == null || StringUtils.isBlank ( commentDTO.getCommentContent () )){
           throw new CustomizeException ( CustomizeErrorCode.COMMENT_ISNULL );
       }

       Comment comment =  commentService.createCommnet(commentDTO);
        commentService.insert ( comment );
        return ResultDTO.okOf ();
    }

    @ApiOperation(value="二级回复的信息", notes="id是指一级评论的id")
    @ResponseBody
    @RequestMapping(value = "/comment/{id}",method = RequestMethod.GET)
    public ResultDTO<List> post(@PathVariable(name = "id")Integer id){
        List<CommentDTO> commentDTOs = commentService.listbyid ( id, CommentTypeEnum.COMMNET_TYPE.getType () );
        return ResultDTO.okOf (commentDTOs);
    }

}

//        Map<Object,Object> objectObjectMap = new HashMap <> (  );
//        objectObjectMap.put ( "message","成功" );  //再次反序列化