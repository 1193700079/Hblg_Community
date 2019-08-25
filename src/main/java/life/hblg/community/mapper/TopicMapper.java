package life.hblg.community.mapper;

import life.hblg.community.model.Topic;
import life.hblg.community.model.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface TopicMapper {
    //通过mybatis的注解 可以自动把User中的相关属性填入#{}中 然后插入到数据库表中
    @Insert( "insert into topic (title,description,createId,gmtCreate,gmtModify,comment_count,view_count,like_count,tag) values\n" +
            " ( #{title},#{description},#{createId},#{gmtCreate},#{gmtModify},#{commentCount},#{viewCount},#{likeCount},#{tag});" )
    void insert(Topic topic);
}
