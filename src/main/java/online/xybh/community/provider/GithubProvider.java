package online.xybh.community.provider;

import com.alibaba.fastjson.JSON;
import okhttp3.*;
import online.xybh.community.dto.AccessTokenDTO;
import online.xybh.community.dto.GithubUser;
import org.springframework.stereotype.Component;

import java.io.IOException;

/**
 * @Author: XYBH
 * @Description: 使用OkHttp向GitHub发送Http请求
 * @Date: Created in 2020/2/5 0005 23:29
 * @Modified:
 */
@Component
public class GithubProvider {
    public String getAccessToken(AccessTokenDTO accessTokenDTO) {
        MediaType mediaType = MediaType.get("application/json; charset=utf-8");
        OkHttpClient client = new OkHttpClient();
        RequestBody body = RequestBody.create(mediaType, JSON.toJSONString(accessTokenDTO));
        Request request = new Request.Builder()
                .url("https://github.com/login/oauth/access_token")
                .post(body)
                .build();
        try (Response response = client.newCall(request).execute()) {
            String string = response.body().string();
            String token = string.split("&")[0].split("=")[1];
            return token;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public GithubUser getUser(String accessToken) {
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder()
                .url("https://api.github.com/user?access_token=" + accessToken)
                .build();
        try {
            Response response = client.newCall(request).execute();
            String string = response.body() != null ? response.body().string() : null;
            GithubUser githubUser = JSON.parseObject(string, GithubUser.class);
            return githubUser;
        } catch (IOException ignored) {
        }
        return null;
    }
}
