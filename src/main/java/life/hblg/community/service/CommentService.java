package life.hblg.community.service;

import life.hblg.community.dto.CommentCreateDTO;
import life.hblg.community.dto.CommentDTO;
import life.hblg.community.enums.CommentTypeEnum;
import life.hblg.community.enums.NotificationEnum;
import life.hblg.community.exception.CustomizeErrorCode;
import life.hblg.community.exception.CustomizeException;
import life.hblg.community.mapper.*;
import life.hblg.community.model.*;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class CommentService {

    @Autowired
    private CommentMapper commentMapper;

    @Autowired
    private CommentExtMapper commentExtMapper;

    @Autowired
    private TopicMapper topicMapper;

    @Autowired
    private TopicExtMapper topicExtMapper;

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private NotificationMapper notificationMapper;


    @Transactional
    public void insert(Comment comment) {
        if(comment.getTopicId () == null || comment.getTopicId ()==0){
            throw new CustomizeException ( CustomizeErrorCode.QUSETION_NOT_FOUND);
        }

        if(comment.getType () == CommentTypeEnum.TOPIC_TYPE.getType ( )){
            //回复一级
          Topic dbTopic = topicMapper.selectByPrimaryKey ( comment.getTopicId ()) ;
            if(dbTopic == null){
                throw new CustomizeException (CustomizeErrorCode.NOT_TOPIC);
            }
            commentMapper.insert ( comment );
            dbTopic.setCommentCount ( 1 )   ;
            topicExtMapper.incCommetCount ( dbTopic );

        }else if(comment.getType () == CommentTypeEnum.COMMNET_TYPE.getType ()){
            //回复二级
            Comment dbComment = commentMapper.selectByPrimaryKey ( comment.getTopicId ());
            if(dbComment == null){
                throw new CustomizeException ( CustomizeErrorCode.NOT_COMMENT );
            }
            /*增加一级回复的评论数*/
            Comment parentComment = new Comment ();
            parentComment.setId ( comment.getTopicId () );
            parentComment.setCommentCount ( 1 );
            commentExtMapper.incCommetCount ( parentComment );

            commentMapper.insert ( comment );
            Notification notification = new Notification ( );
            notification.setStatus ( 2 );
            notification.setNotifier ( comment.getId () );
            notification.setReciver ( comment.getTopicId () );
            notification.setType ( NotificationEnum.REPLY_COMMENT.getType () );
            notification.setGmtCreate ( System.currentTimeMillis () );
            notification.setGmtModify ( System.currentTimeMillis () );
            notificationMapper.insert ( notification );

        }
    }

/* id是话题的id
* */
    public List <CommentDTO> listbyid(Integer id, Integer type) {
        //问题： 代码生成器的取不出来String （text）于是自己手写了Ext扩展
    /*  CommentExample example = new CommentExample ( );
        example.createCriteria ().andTopicIdEqualTo ( id ).andTopicIdEqualTo ( CommentTypeEnum.TOPIC_TYPE.getType () );
        List <Comment> comments = commentMapper.selectByExample ( example );*/

        List <Comment> comments = commentExtMapper.listById (id, type );
        List<CommentDTO> commentDTOS = new ArrayList <> (  );
        for(Comment comment:comments){
            //1.通过找到comment表中对应的关联UserID找到User
            Integer userId = comment.getCommentorId ();
            User user = userMapper.selectByPrimaryKey ( userId);
            CommentDTO commentDTO = new CommentDTO ();
            BeanUtils.copyProperties ( comment,commentDTO );
            commentDTO.setUser ( user );
            commentDTOS.add ( commentDTO );
        }
        return  commentDTOS;

    }

    public Comment createCommnet(CommentCreateDTO commentDTO) {
        Comment comment = new Comment ( );
        comment.setTopicId ( commentDTO.getTopicId () );
        comment.setCommentorId ( commentDTO.getCommentorId () );
        comment.setCommentContent ( commentDTO.getCommentContent () );
        comment.setLikeCount ( 0 );
        comment.setCommentCount ( 0 );
        if(commentDTO.getType () == 1 || commentDTO.getType () == 2 ){
            comment.setType ( commentDTO.getType () );
        }else{
            throw new CustomizeException ( CustomizeErrorCode.NOT_COMMENTTYPE );
        }
        comment.setGmtCreate ( System.currentTimeMillis () );
        comment.setGmtModify ( System.currentTimeMillis () );
        return comment;
    }
}
