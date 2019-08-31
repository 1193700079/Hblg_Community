package life.hblg.community.mapper;

import com.github.pagehelper.Page;
import life.hblg.community.model.User;
import life.hblg.community.model.UserExample;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.session.RowBounds;

import java.util.List;

@Mapper
public interface UserExtMapper {
    @Select ("SELECT * FROM USER")
    Page<User> getUserList();
}