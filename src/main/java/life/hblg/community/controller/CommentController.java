package life.hblg.community.controller;

import life.hblg.community.dto.CommentDTO;
import life.hblg.community.mapper.CommentMapper;
import life.hblg.community.model.Comment;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
public class CommentController {

    @Autowired
    private CommentMapper commentMapper;

    //返回json 一种通用的数据格式（前后端）  接受一个json

    @ResponseBody
    @RequestMapping(value = "/comment",method = RequestMethod.POST)
    public Object post(@RequestBody CommentDTO commentDTO){  //JSON反序列化
        Comment comment = new Comment ( );
        comment.setTopicId ( commentDTO.getTopicId () );
        comment.setCommentorId ( commentDTO.getCommentorId () );
        comment.setCommentContent ( commentDTO.getCommentContent () );
        comment.setType ( commentDTO.getType () );
        comment.setGmtCreate ( System.currentTimeMillis () );
        comment.setGmtModify ( System.currentTimeMillis () );
        commentMapper.insert ( comment );
        Map<Object,Object> objectObjectMap = new HashMap <> (  );
        objectObjectMap.put ( "message","成功" );  //再次反序列化
        return objectObjectMap;
    }
}
