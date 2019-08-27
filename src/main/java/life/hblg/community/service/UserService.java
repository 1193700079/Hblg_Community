package life.hblg.community.service;

import life.hblg.community.mapper.UserMapper;
import life.hblg.community.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    @Autowired
    UserMapper userMapper;

    public void createOrUpdate(User user) {
        //从数据库总拿出来的user
        User dbUser = userMapper.findByAccountId(user.getAccountId ());
        if(dbUser == null){
            user.setGmtCreate ( System.currentTimeMillis ( ) );
            user.setGmtModify ( user.getGmtCreate ( ) );
            userMapper.insert ( user );
        }else{
            dbUser.setToken ( user.getToken () );//设置随机码
            dbUser.setName ( user.getName ( ) );
            dbUser.setAvatarUrl ( user.getAvatarUrl ());
            dbUser.setGmtModify ( System.currentTimeMillis () );
            user.setId ( dbUser.getId () );
            userMapper.update(dbUser);
        }
    }
}
