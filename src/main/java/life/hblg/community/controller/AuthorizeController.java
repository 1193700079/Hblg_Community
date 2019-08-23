package life.hblg.community.controller;

import life.hblg.community.dto.Access_TokenDTO;
import life.hblg.community.dto.GithubUser;
import life.hblg.community.provider.GithubProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

//gihub授权登录的控制器
@Controller
public class AuthorizeController {

    @Autowired  //由于GithubProvider为spring中的组件 已经由容器实例化了 直接注入就可以
    private GithubProvider githubProvider;

    @Value ( "${github.client.id}" )
    private String clientId;

    @Value ( "${github.client.secret}" )
    private String clientSecret;

    @Value ( "${github.redirect.uri}" )
    private String redirectUrl;

    @GetMapping("callback")  //由于在github上设置了返回路由为callback
    //根据github文档中的说明需要获取code和state（这些值来源于github）
    public String returnIndex(@RequestParam(name="code") String code,
                              @RequestParam(name="state") String state){

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
        return "index";//返回主页View
    }
}
