package life.hblg.community.provider;

import com.alibaba.fastjson.JSON;
import life.hblg.community.dto.Access_TokenDTO;
import life.hblg.community.dto.GithubUser;
import okhttp3.*;
import org.springframework.stereotype.Component;

import javax.net.ssl.*;
import java.io.IOException;

//此项目中需要用到的外来插件来实现提供一些功能  模拟post请求 获取access_token
//将一个类快速变成一个json fastjson
@Component
public class GithubProvider {

    //获取AccessToken 模仿post请求 其中参数access_tokenDTO携带关键的code
    public String getAccessToken(Access_TokenDTO access_tokenDTO){
        MediaType mediaType = MediaType.get("application/json; charset=utf-8");

        OkHttpClient client = new OkHttpClient();
        //通过忽略证书 解决SSL异常
//        OkHttpClient client = getUnsafeOkHttpClient();


        RequestBody body = RequestBody.create(mediaType, JSON.toJSONString ( access_tokenDTO ) );
        Request request = new Request.Builder()
                .url("https://github.com/login/oauth/access_token")
                .post(body)
                .build();
        try  {
            Response response = client.newCall(request).execute();
            String string = response.body().string();
            String token = string.split ( "&" )[0].split ( "=" )[1];
            System.out.println (token );
            return token;
        } catch (IOException e) {
            e.printStackTrace ( );
        }
        return null;

    }

    //okhttp模仿get请求 得到用户信息(将从服务端获取的信息 装换成一个类)
    public GithubUser getGithubUser(String access_token){
        OkHttpClient client = new OkHttpClient();
        //通过忽略证书 解决SSL异常
//        OkHttpClient client = getUnsafeOkHttpClient();
        Request request = new Request.Builder()
                .url("https://api.github.com/user?access_token="+access_token)
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
    //使用okhttp忽略证书
//    public static OkHttpClient getUnsafeOkHttpClient() {
//
//        try {
//            final TrustManager[] trustAllCerts = new TrustManager[]{
//                    new X509TrustManager () {
//                        @Override
//                        public void checkClientTrusted(java.security.cert.X509Certificate[] chain, String authType) {
//                        }
//
//                        @Override
//                        public void checkServerTrusted(java.security.cert.X509Certificate[] chain, String authType) {
//                        }
//
//                        @Override
//                        public java.security.cert.X509Certificate[] getAcceptedIssuers() {
//                            return new java.security.cert.X509Certificate[]{};
//                        }
//                    }
//            };
//
//            final SSLContext sslContext = SSLContext.getInstance("SSL");
//            sslContext.init(null, trustAllCerts, new java.security.SecureRandom());
//            final javax.net.ssl.SSLSocketFactory sslSocketFactory = sslContext.getSocketFactory();
//            OkHttpClient.Builder builder = new OkHttpClient.Builder();
//            builder.sslSocketFactory(sslSocketFactory);
//
//            builder.hostnameVerifier(new HostnameVerifier() {
//                @Override
//                public boolean verify(String hostname, SSLSession session) {
//                    return true;
//                }
//            });
//
//            return builder.build();
//        } catch (Exception e) {
//            throw new RuntimeException(e);
//        }
//
//    }

}
