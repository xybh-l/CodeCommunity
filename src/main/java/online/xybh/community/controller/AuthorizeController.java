package online.xybh.community.controller;

import online.xybh.community.dto.QQUser;
import online.xybh.community.dto.AccessTokenDTO;
import online.xybh.community.dto.GithubUser;
import online.xybh.community.dto.QQAccessTokenDTO;
import online.xybh.community.model.User;
import online.xybh.community.provider.GithubProvider;
import online.xybh.community.provider.QQProvider;
import online.xybh.community.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.UUID;

/**
 * @Author: XYBH
 * @Description: Github认证
 * @Date: Created in 2020/2/5 0005 23:22
 * @Modified:
 */
@Controller
public class AuthorizeController {

    @Autowired
    private GithubProvider githubProvider;

    @Value("${github.client.id}")
    private String githubClientId;
    @Value("${github.client.secret}")
    private String githubClientSecret;
    @Value("${github.redirect.url}")
    private String githubRedirectUrl;

    @Value("${qq.grant.type}")
    private String qqGrantType;
    @Value("${qq.client.id}")
    private String qqClientId;
    @Value("${qq.client.secret}")
    private String qqClientSecret;
    @Value("${qq.redirect.url}")
    private String qqRedirectUrl;

    @Autowired
    private UserService userService;

    @RequestMapping("/callback")
    public String callback(@RequestParam(name = "code") String code,
                           @RequestParam(name = "state") String state,
                           HttpServletResponse response) {
        AccessTokenDTO accessTokenDTO = new AccessTokenDTO();
        accessTokenDTO.setClient_id(githubClientId);
        accessTokenDTO.setClient_secret(githubClientSecret);
        accessTokenDTO.setRedirect_url(githubRedirectUrl);
        accessTokenDTO.setCode(code);
        accessTokenDTO.setState(state);
        String accessToken = githubProvider.getAccessToken(accessTokenDTO);
        GithubUser githubUser = githubProvider.getUser(accessToken);
        if (githubUser != null && githubUser.getId() != null) {
            User user = new User();
            String token = UUID.randomUUID().toString();
            user.setToken(token);
            user.setName(githubUser.getName());
            user.setBio(githubUser.getBio());
            user.setAccountId(String.valueOf(githubUser.getId()));
            user.setAvatarUrl(githubUser.getAvatar_url());
            userService.createOrUpdate(user);
            response.addCookie(new Cookie("token", token));
            return "redirect:/";
        } else {
            //登录失败
            return "redirect:/";
        }
    }

    @RequestMapping("/auth/callback_qq")
    public String callback(@RequestParam(name = "code") String code,
                           @RequestParam(name = "state") String state){
        System.out.println("code="+code);
        System.out.println("state="+state);
        QQAccessTokenDTO accessTokenDTO = new QQAccessTokenDTO();
        accessTokenDTO.setGrant_type(qqGrantType);
        accessTokenDTO.setClient_id(qqClientId);
        accessTokenDTO.setClient_secret(qqClientSecret);
        accessTokenDTO.setRedirect_url(URLEncoder.encode(qqRedirectUrl));
        accessTokenDTO.setCode(code);
        accessTokenDTO.setState(state);
        System.out.println(accessTokenDTO);
        HashMap<String, String> returnData = QQProvider.getAccessToken(accessTokenDTO);
        assert returnData != null;
        String accessToken = returnData.get("token");
        String openId = QQProvider.getOpenId(accessToken);
        QQUser qqUser = QQProvider.getUser(accessToken, qqClientId, openId);
        System.out.println(accessToken+" "+openId);
//        if (qqUser != null && qqUser.getId() != null) {
//            User user = new User();
//            String token = UUID.randomUUID().toString();
//            user.setToken(token);
//            user.setName(qqUser.getName());
//            user.setBio(githubUser.getBio());
//            user.setAccountId(openId);
//            user.setAvatarUrl(githubUser.getAvatar_url());
//            userService.createOrUpdate(user);
//            response.addCookie(new Cookie("token", token));
//            return "redirect:/";
//        } else {
//            //登录失败
//            return "redirect:/";
//        }
        return "/";
    }
    @GetMapping("/logout")
    public String logout(HttpServletRequest request,
                         HttpServletResponse response){
        request.getSession().removeAttribute("user");
        Cookie token = new Cookie("token", null);
        token.setMaxAge(0);
        response.addCookie(token);
        return "redirect:/";
    }
}
