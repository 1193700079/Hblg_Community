package life.hblg.community.mapper;

import life.hblg.community.model.Comment;
import life.hblg.community.model.CommentExample;
import life.hblg.community.model.Topic;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.apache.ibatis.session.RowBounds;
import sun.security.krb5.internal.tools.Klist;

import java.util.List;

@Mapper
public interface CommentExtMapper {


    /*按照修改时间递增的顺序来进行排序*/
    @Select ( "select * from comment where topic_id = #{topicId} and type = #{type} order by gmt_create desc" )
    List<Comment> listById(@Param ( "topicId" ) Integer topicId,@Param("type")Integer type );

    //更新一级评论的回复数
    @Update( "update comment set comment_count=comment_count+1 where id = #{id} " )
    int incCommetCount(Comment comment);
}

