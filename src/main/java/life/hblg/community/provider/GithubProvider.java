package life.hblg.community.provider;

import com.alibaba.fastjson.JSON;
import life.hblg.community.dto.Access_TokenDTO;
import life.hblg.community.dto.GithubUser;
import okhttp3.*;
import org.springframework.stereotype.Component;

import java.io.IOException;

//此项目中需要用到的外来插件来实现提供一些功能  模拟post请求 获取access_token
//将一个类快速变成一个json fastjson
@Component
public class GithubProvider {

    //获取AccessToken 模仿post请求 其中参数access_tokenDTO携带关键的code
    public String getAccessToken(Access_TokenDTO access_tokenDTO){
        MediaType mediaType = MediaType.get("application/json; charset=utf-8");

        OkHttpClient client = new OkHttpClient();

        RequestBody body = RequestBody.create(mediaType, JSON.toJSONString ( access_tokenDTO ) );
        Request request = new Request.Builder()
                .url("https://github.com/login/oauth/access_token")
                .post(body)
                .build();
        try ( Response response = client.newCall(request).execute()) {
            String string = response.body().string();
            System.out.println(string);
            return string;
        } catch (IOException e) {
            e.printStackTrace ( );
        }
        return null;

    }

    //okhttp模仿get请求 得到用户信息
    public GithubUser getGithubUser(String access_token){
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder()
                .url("https://api.github.com/user?access_token"+access_token)
                .build();

        try {
            Response response = client.newCall(request).execute();
            String string = response.body().string();
            GithubUser githubUser = JSON.parseObject ( string,GithubUser.class );
            return githubUser;
        } catch (IOException e) {
            e.printStackTrace ( );
        }
        return null;
    }
}
