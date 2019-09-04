package life.hblg.community.service;

import life.hblg.community.mapper.UserMapper;
import life.hblg.community.model.User;
import life.hblg.community.model.UserExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    @Autowired
    UserMapper userMapper;

    public void createOrUpdate(User user) {
        UserExample userExample = new UserExample ( );
        userExample.createCriteria ().andAccountIdEqualTo ( user.getAccountId () );
        List <User> users = userMapper.selectByExample ( userExample );

        if(users.size () == 0){
            user.setGmtCreate ( System.currentTimeMillis () );
            user.setGmtModify ( user.getGmtCreate () );
            userMapper.insert ( user );
        }else{
            //更新
            User dbUser = users.get ( 0 );
            User updateUser = new User ();
            updateUser.setToken ( user.getToken () );//设置随机码
            updateUser.setName ( user.getName ( ) );
            updateUser.setAvatarUrl ( user.getAvatarUrl ());
            updateUser.setGmtModify ( System.currentTimeMillis ());

            UserExample example = new UserExample ();
            example.createCriteria ().andIdEqualTo ( dbUser.getId () );
            userMapper.updateByExampleSelective ( updateUser,example);
        }

//        //从数据库总拿出来的user
//        User dbUser = userMapper.findByAccountId(user.getAccountId ());
//        if(dbUser == null){
//            user.setGmtCreate ( System.currentTimeMillis ( ) );
//            user.setGmtModify ( user.getGmtCreate ( ) );
//            userMapper.insert ( user );
//        }else{
//            User duUser = users.get ( 0 );
//            dbUser.setToken ( user.getToken () );//设置随机码
//            dbUser.setName ( user.getName ( ) );
//            dbUser.setAvatarUrl ( user.getAvatarUrl ());
//            dbUser.setGmtmodify ( System.currentTimeMillis ());
//            userMapper.update(dbUser);
//        }
    }

    public User findByToken(String token) {
        UserExample example = new UserExample ( );
        example.createCriteria ().andTokenEqualTo ( token );
        List<User> users = userMapper.selectByExample ( example );
        return users.get ( 0 );
    }
}
