package life.hblg.community.mapper;

import life.hblg.community.model.User;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;



@Mapper
public interface UserMapper {
    //通过mybatis的注解 可以自动把User中的相关属性填入#{}中 然后插入到数据库表中
    @Insert ( "insert into user (accountId,name,token,gmtCreate,gmtModify,avatar_url,bio) values\n" +
            " ( #{accountId},#{name},#{token},#{gmtCreate},#{gmtModify},#{avatarUrl},#{bio});" )
    void insert(User user);

    @Select ( "select * from user where token = #{token}" )
    User findByToken(@Param ( "token" ) String token);

    @Select ( "select * from user where id = #{id}" )
    User findById(@Param ( "id" )Integer id);

    @Select ( "select * from user where accountId = #{accountId}" )
    User findByAccountId(@Param ( "accountId" )String accountId);

    @Update ( "update user set name = #{name},token = #{token},gmtModify=#{gmtModify},avatar_url=#{avatarUrl} where id = #{id}")
    void update(User user);
}
