package life.hblg.community.mapper;

import com.github.pagehelper.Page;
import life.hblg.community.model.Topic;
import life.hblg.community.model.TopicExample;
import life.hblg.community.model.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.session.RowBounds;

import java.util.List;

@Mapper
public interface TopicExtMapper {
  int incView(Topic topic);

  @Select("SELECT * FROM topic")
  Page<Topic> getTopicList();
}