package life.hblg.community.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import life.hblg.community.dto.Access_TokenDTO;
import life.hblg.community.dto.GithubUser;
import life.hblg.community.dto.ResultDTO;
import life.hblg.community.model.User;
import life.hblg.community.provider.GithubProvider;
import life.hblg.community.service.UserService;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.UUID;

//gihub授权登录的控制器
@Api(value = "github 授权登录控制器")
@Controller
public class AuthorizeController {

    @Autowired  //由于GithubProvider为spring中的组件 已经由容器实例化了 直接注入就可以
    private GithubProvider githubProvider;

    @Autowired
    private UserService userService;

    @Value("${github.client.id}")
    private String clientId;

    @Value("${github.client.secret}")
    private String clientSecret;

    @Value("${github.redirect.uri}")
    private String redirectUrl;


    @ResponseBody
    @ApiOperation(value="github登录的接口", notes="")
    @GetMapping("loginByGithub")  //由于在github上设置了返回路由为callback
    //根据github文档中的说明需要获取code和state（这些值来源于github）
    public ResultDTO returnIndex(@RequestParam(name = "code") String code,
                              @RequestParam(name = "state") String state,
                              HttpServletRequest request,
                              HttpServletResponse response,
                              Model model) {

        System.out.println (code );
        //从github服务端获取的值传给封装数据对象
        Access_TokenDTO access_tokenDTO = new Access_TokenDTO ( );
        access_tokenDTO.setClient_id ( clientId );
        access_tokenDTO.setClient_secret ( clientSecret );
        access_tokenDTO.setCode ( code );
        access_tokenDTO.setRedirect_uri ( redirectUrl );
        access_tokenDTO.setState ( state );

        //获取到了accessToken
        String accessToken = githubProvider.getAccessToken ( access_tokenDTO );//post
        GithubUser githubUser = githubProvider.getGithubUser ( accessToken ); //get

        if (githubUser != null && githubUser.getId ( ) != null) {
            User user = new User ( );
            String token = UUID.randomUUID ( ).toString ( );
            user.setToken ( token );//设置随机码
            user.setName ( githubUser.getName ( ) );
            user.setAccountId ( String.valueOf ( githubUser.getId ( ) ) );
            user.setAvatarUrl ( githubUser.getAvatar_url ( ) );
            user.setBio ( githubUser.getBio () );
            userService.createOrUpdate ( user );


            response.addCookie ( new Cookie ( "token", token ) );
            System.out.println ("我把cookie给你了" );
            //登录成功的话 从request中获取session信息 然后把user信息存放在Session中
            request.getSession ().setAttribute ( "user",user );

            return ResultDTO.okOf ( user);
        } else {
            response.addCookie ( new Cookie ( "token", "231231" ) );
            return ResultDTO.errorOf ( 2002,"登录失败" );
        }

    }

    //退出登录的接口
    @ResponseBody
    @ApiOperation(value="退出登录的接口", notes="")
    @GetMapping("/logout")
    public Object logout(HttpServletRequest request,
                         HttpServletResponse response){
        request.getSession ().removeAttribute ( "user" ); //移除user这个Session
        Cookie cookie = new Cookie("token",null);
        cookie.setMaxAge(0);
        response.addCookie(cookie);
        return ResultDTO.okOf (200,"退出登录成功");
    }


}
