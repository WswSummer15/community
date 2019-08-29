package com.wsw.community.controller;

import com.wsw.community.dto.AccessTokenDTO;
import com.wsw.community.dto.GithubUser;
import com.wsw.community.provider.GithubProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;

/**
 * 认证
 */
@Controller
public class AuthorizeController {
    private GithubProvider githubProvider;

    @Autowired //使用set方法注入
    public void setGithubProvider(GithubProvider githubProvider) {
        this.githubProvider = githubProvider;
    }

    @Value("${github.client.id}")
    private String clientId;
    @Value("${github.client.secret}")
    private String clientSecret;
    @Value("${github.redirect.uri}")
    private String redirectUrl;

    @RequestMapping("/callback")
    public String callback(@RequestParam("code") String code,
                           @RequestParam("state") String state, HttpServletRequest request){
        AccessTokenDTO accessTokenDTO = new AccessTokenDTO();
        accessTokenDTO.setClient_id(clientId);
        accessTokenDTO.setClient_secret(clientSecret);
        accessTokenDTO.setCode(code);
        accessTokenDTO.setRedirect_uri(redirectUrl);
        accessTokenDTO.setState(state);
        String accessToken = githubProvider.getAccessToken(accessTokenDTO);
        GithubUser user = githubProvider.getUser(accessToken);
        if (user != null){
            //登录成功，写cookie和session
            request.getSession().setAttribute("user", user);
            return "redirect:/";
        }else{
            //登录失败，重新登录
            return "redirect:/";
        }
    }
}
