package com.wsw.community.controller;

import com.wsw.community.dto.AccessTokenDTO;
import com.wsw.community.dto.GithubUser;
import com.wsw.community.provider.GithubProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

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

    @RequestMapping("/callback")
    public String callback(@RequestParam("code") String code, @RequestParam("state") String state){
        AccessTokenDTO accessTokenDTO = new AccessTokenDTO();
        accessTokenDTO.setClient_id("19cbdc2cc0861f19ec2f");
        accessTokenDTO.setClient_secret("8ca9f47d28ad0b4d490907b652a845b8861c9151");
        accessTokenDTO.setCode(code);
        accessTokenDTO.setRedirect_uri("http://localhost:8887/callback");
        accessTokenDTO.setState(state);
        String accessToken = githubProvider.getAccessToken(accessTokenDTO);
        GithubUser user = githubProvider.getUser(accessToken);
        System.out.println(user.getName());
        System.out.println(user.getId());
        System.out.println(user.getBio());
        return "index";
    }
}
