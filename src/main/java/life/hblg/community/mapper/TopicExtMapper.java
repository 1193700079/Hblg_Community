package life.hblg.community.mapper;

import com.github.pagehelper.Page;
import life.hblg.community.model.Topic;
import life.hblg.community.model.TopicExample;
import life.hblg.community.model.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.apache.ibatis.session.RowBounds;

import java.util.List;

@Mapper
public interface TopicExtMapper {
  int incView(Topic topic);

//更新话题的回复数
  @Update ( "update topic set comment_count=comment_count+#{commentCount} where id = #{id} " )
  int incCommetCount(Topic topic);

//相关话题
 @Select( "select * from topic where (tag regexp #{tag}) and id != #{id};" )
  List<Topic> selectRelevantTopics(Topic topic);

}