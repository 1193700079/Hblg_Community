package life.hblg.community.controller;

import life.hblg.community.dto.Access_TokenDTO;
import life.hblg.community.provider.GithubProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

//gihub授权登录的控制器
@Controller
public class AuthorizeController {

    @Autowired  //由于GithubProvider为spring中的组件 已经由容器实例化了 直接注入就可以
    private GithubProvider githubProvider;

    @GetMapping("callback")  //由于在github上设置了返回路由为callback
    //根据github文档中的说明需要获取code和state（这些值来源于github）
    public String returnIndex(@RequestParam(name="code") String code,
                              @RequestParam(name="state") String state){

        //从github服务端获取的值传给封装数据对象
        Access_TokenDTO access_tokenDTO = new Access_TokenDTO ();
        access_tokenDTO.setClient_id ( "b5b6b3eb2aa8d6ca3e22" );
        access_tokenDTO.setClient_secret ( "85f13faa332a1576e472cfbff1805c4c0e75eb21" );
        access_tokenDTO.setCode ( code );
        access_tokenDTO.setRedirect_uri ( "http://localhost:8080/callback" );
        access_tokenDTO.setState ( state );

        //获取到了accessToken
        String accessToken =githubProvider.getAccessToken ( access_tokenDTO );

        return "index";//返回主页View
    }
}
