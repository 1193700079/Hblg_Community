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
        User dbUser = userMapper.findByAccountId(user.getAccountId ());
        if(dbUser == null){
            //作创建操作(插入)
            dbUser.setGmtCreate ( System.currentTimeMillis ( ) );
            dbUser.setGmtModify ( user.getGmtCreate ( ) );
            userMapper.insert ( user );
        }else{
            //做更新

            dbUser.setToken ( user.getToken () );//设置随机码
            dbUser.setName ( user.getName ( ) );
            dbUser.setAvatarUrl ( user.getAvatarUrl ());
            dbUser.setGmtModify ( System.currentTimeMillis () );
            userMapper.update(user);
        }
    }
}
