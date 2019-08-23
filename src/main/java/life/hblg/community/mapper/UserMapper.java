package life.hblg.community.mapper;

import life.hblg.community.model.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserMapper {
    //通过mybatis的注解 可以自动把User中的相关属性填入#{}中 然后插入到数据库表中
    @Insert ( "insert into user (id,account_id,name,token,gmt_create,gmt_modify) values\n" +
            " ( #{id},#{accountId},#{name},#{token},#{gmtCreate},#{gmtModify});" )
    void insert(User user);

}
