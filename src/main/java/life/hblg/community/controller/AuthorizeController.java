package life.hblg.community.controller;

import life.hblg.community.dto.Access_TokenDTO;
import life.hblg.community.dto.GithubUser;
import life.hblg.community.model.User;
import life.hblg.community.provider.GithubProvider;
import life.hblg.community.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.UUID;

//github授权登录的控制器
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

    @GetMapping("callback")  //由于在github上设置了返回路由为callback
    //根据github文档中的说明需要获取code和state（这些值来源于github）
    public String returnIndex(@RequestParam(name = "code") String code,
                              @RequestParam(name = "state") String state,
                              HttpServletRequest request,
                              HttpServletResponse response,
                              Model model) {

        //从github服务端获取的值传给封装数据对象
        Access_TokenDTO access_tokenDTO = new Access_TokenDTO ( );
        access_tokenDTO.setClient_id ( clientId );
        access_tokenDTO.setClient_secret ( clientSecret );
        access_tokenDTO.setCode ( code );
        access_tokenDTO.setRedirect_uri ( redirectUrl );
        access_tokenDTO.setState ( state );

        //获取到了accessToken
        String accessToken = githubProvider.getAccessToken ( access_tokenDTO );
        GithubUser githubUser = githubProvider.getGithubUser ( accessToken );

        if (githubUser != null && githubUser.getId ( ) != null) {
            User user = new User ( );
            String token = UUID.randomUUID ( ).toString ( );
            user.setToken ( token );//设置随机码
            user.setName ( githubUser.getName ( ) );
            user.setAccountId ( String.valueOf ( githubUser.getId ( ) ) );
//            user.setAccountId ( String.valueOf ( githubUser.getId ( ) ) );
       //    user.setGmtCreate ( System.currentTimeMillis ( ) );   //放在Service层
      //      user.setGmtModify ( user.getGmtCreate ( ) );
            user.setAvatarUrl ( githubUser.getAvatar_url ( ) );
            user.setBio ( githubUser.getBio () );
            model.addAttribute ( "avatarUrl", user.getAvatarUrl ( ) ); //由于最后返回是重定向 所以这个model就消失了
            System.out.println (user.getAvatarUrl () +"**c****************");
            System.out.println (user.toString() +"**c****************");

            userService.createOrUpdate ( user );


            response.addCookie ( new Cookie ( "token", token ) );
//            request.getSession ().setAttribute ( "avatarUrl",user.getAvatarUrl ( ));

            //登录成功的话 从request中获取session信息 然后把user信息存放在Session中
            request.getSession ().setAttribute ( "user",user );
            return "redirect:/";
        } else {
            return "redirect:/";
        }

//        return "index";//返回主页View
    }

    //退出登录的接口
    @GetMapping("/logout")
    public String logout(HttpServletRequest request,
                         HttpServletResponse response){
        request.getSession ().removeAttribute ( "user" ); //移除user这个Session
        Cookie cookie = new Cookie("token",null);
        cookie.setMaxAge(0);
        //cookie.setPath("/");//根据你创建cookie的路径进行填写
        response.addCookie(cookie);

//        Cookie[] cookies=request.getCookies();
//        try{
//            for(int i=0;i<cookies.length;i++){
//                Cookie cookie = new Cookie("token",null);
//                cookie.setMaxAge(0);
//                //cookie.setPath("/");//根据你创建cookie的路径进行填写
//                response.addCookie(cookie);
//            }
//        }catch(Exception ex) {
//            System.out.println ( "清空Cookies发生异常！" );
//        }

        return "redirect:/";
    }
}
