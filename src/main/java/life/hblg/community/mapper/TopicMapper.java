package life.hblg.community.mapper;

import life.hblg.community.model.Topic;
import life.hblg.community.model.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface TopicMapper {
    //通过mybatis的注解 可以自动把User中的相关属性填入#{}中 然后插入到数据库表中
    @Insert( "insert into topic (title,description,createId,gmtCreate,gmtModify,comment_count,view_count,like_count,tag) values\n" +
            " ( #{title},#{description},#{createId},#{gmtCreate},#{gmtModify},#{commentCount},#{viewCount},#{likeCount},#{tag});" )
    void insert(Topic topic);

    //为了分页显示
    @Select ( "select * from topic limit #{offset},#{size}" )
    List<Topic> getList(@Param( "offset" )Integer offset, @Param ( "size" )Integer size);

    @Select ( "select count(1) from topic" )
    Integer Count();


    //某用户展示话题
    @Select ( "select * from topic where createId = #{userId} limit #{offset},#{size}" )
    List<Topic> getListByUserId(@Param ( "userId" )Integer userId, @Param( "offset" )Integer offset, @Param ( "size" )Integer size);

    @Select ( "select count(1) from topic where createId = #{userId}" )
    Integer CountByuserId(@Param ( "userId" )Integer userId);
}
