package life.hblg.community.controller;

import life.hblg.community.dto.Access_TokenDTO;
import life.hblg.community.dto.GithubUser;
import life.hblg.community.mapper.UserMapper;
import life.hblg.community.model.User;
import life.hblg.community.provider.GithubProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.UUID;

//gihub授权登录的控制器
@Controller
public class AuthorizeController {

    @Autowired  //由于GithubProvider为spring中的组件 已经由容器实例化了 直接注入就可以
    private GithubProvider githubProvider;

    @Autowired //由于mapper注解 也是直接成为了spring中的组件
    private UserMapper userMapper ;

    @Value ( "${github.client.id}" )
    private String clientId;

    @Value ( "${github.client.secret}" )
    private String clientSecret;

    @Value ( "${github.redirect.uri}" )
    private String redirectUrl;

    @GetMapping("callback")  //由于在github上设置了返回路由为callback
    //根据github文档中的说明需要获取code和state（这些值来源于github）
    public String returnIndex(@RequestParam(name="code") String code,
                              @RequestParam(name="state") String state,
                              HttpServletRequest request,
                              HttpServletResponse response){

        //从github服务端获取的值传给封装数据对象
        Access_TokenDTO access_tokenDTO = new Access_TokenDTO ();
        access_tokenDTO.setClient_id ( clientId );
        access_tokenDTO.setClient_secret ( clientSecret );
        access_tokenDTO.setCode ( code );
        access_tokenDTO.setRedirect_uri ( redirectUrl );
        access_tokenDTO.setState ( state );

        //获取到了accessToken
        String accessToken =githubProvider.getAccessToken ( access_tokenDTO );
       GithubUser githubUser= githubProvider.getGithubUser ( accessToken );
        System.out.println (  githubUser.getBio ());
        System.out.println (  githubUser.getName ());
        System.out.println (  githubUser.getId ());
        System.out.println (githubUser.getLogin () );

        if(githubUser!=null && githubUser.getId ()!=null){

            User user = new User ();
            String token = UUID.randomUUID ().toString ();
            user.setToken ( token );//设置随机码
            user.setName ( githubUser.getName () );
            user.setAccountId ( String.valueOf ( githubUser.getId ()) );
            user.setGmtCreate ( System.currentTimeMillis () );
            user.setGmtModify ( user.getGmtCreate () );
            userMapper.insert ( user );

            response.addCookie ( new Cookie ( "token",token ) );

            //登录成功的话 从request中获取session信息 然后把user信息存放在Session中
//            request.getSession ().setAttribute ( "user",githubUser );
            return "redirect:/" ;
        }else{
            return "redirect:/" ;
        }

//        return "index";//返回主页View
    }
}
