package online.xybh.community.provider;

import com.alibaba.fastjson.JSON;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import online.xybh.community.dto.QQAccessTokenDTO;
import online.xybh.community.dto.QQUser;

import java.io.IOException;
import java.util.HashMap;

/**
 * @Author: XYBH
 * @Description:
 * @Date: Created in 2020/2/13 0013 9:38
 * @Modified:
 */
public class QQProvider {
    public static HashMap<String, String> getAccessToken(QQAccessTokenDTO accessToken) {
        String url = "https://graph.qq.com/oauth2.0/token?grant_type=" + accessToken.getGrant_type()
                + "&client_id=" + accessToken.getClient_id() + "&client_secret=" + accessToken.getClient_secret()
                + "&code=" + accessToken.getCode() + "&state=" + accessToken.getState();
        System.out.println(url);
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder()
                .url(url)
                .get()
                .build();
        try (Response response = client.newCall(request).execute()) {
            String string = response.body().string();
            System.out.println(string);
            HashMap<String, String> Map = new HashMap<>();
            String token = string.split("&")[0].split("=")[1];
            String expiresIn = string.split("&")[1].split("=")[1];
            String refreshToken = string.split("&")[2].split("=")[1];
            Map.put("token", token);
            Map.put("expiresIn", expiresIn);
            Map.put("refreshToken", refreshToken);
            return Map;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static String getOpenId(String token) {
        String url = "https://graph.qq.com/oauth2.0/me?access_token=" + token;
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder()
                .url(url)
                .get()
                .build();
        try (Response response = client.newCall(request).execute()) {
            String string = response.body().string();
            return string.split(":")[2].split("}")[0];
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static QQUser getUser(String token, String qqClientId, String openId) {
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder()
                .url("https://graph.qq.com/user/get_user_info?access_token="+token+"&oauth_consumer_key="+qqClientId+"&openid="+openId)
                .build();
        try {
            Response response = client.newCall(request).execute();
            String string = response.body() != null ? response.body().string() : null;
            return JSON.parseObject(string, QQUser.class);
        } catch (IOException ignored) {
        }
        return null;
    }
}
