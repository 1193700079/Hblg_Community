package life.hblg.community.mapper;

import life.hblg.community.model.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;



@Mapper
public interface UserMapper {
    //通过mybatis的注解 可以自动把User中的相关属性填入#{}中 然后插入到数据库表中
    @Insert ( "insert into user (accountId,name,token,gmtCreate,gmtModify,avatar_url) values\n" +
            " ( #{accountId},#{name},#{token},#{gmtCreate},#{gmtModify},#{avatarUrl});" )
    void insert(User user);

    @Select ( "select * from user where token = #{token}" )
    User findByToken(@Param ( "token" ) String token);

    @Select ( "select * from user where id = #{id}" )
    User findById(@Param ( "id" )Integer id);
}