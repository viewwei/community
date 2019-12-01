package lift.community.controller;

import lift.community.dot.AccessTokenDTO;
import lift.community.dot.GithubUser;
import lift.community.provider.GithubProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class AuthorizeController {
    @Autowired
    private GithubProvider githubProvider;
    @GetMapping("/callBack")
    public  String callback(@RequestParam(name = "code") String code,@RequestParam(name = "state")String state){
        AccessTokenDTO accessTokenDTO = new AccessTokenDTO();
        accessTokenDTO.setClient_id("B");
        accessTokenDTO.setClient_secret("C");
        accessTokenDTO.setCode(code);
        accessTokenDTO.setRedirect_url("D");
        accessTokenDTO.setState(state);
       String accessToken =  githubProvider.getAccessToken(accessTokenDTO);
        GithubUser githubUser = githubProvider.getUser(accessToken);

        return  "index";
    }
}
