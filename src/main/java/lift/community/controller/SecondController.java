package lift.community.controller;

import lift.community.dot.GithubUser;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@Controller
public class SecondController {
    @GetMapping("/hello")
    public  String hello(HttpServletRequest httpServletRequest){
        System.out.println("点击登录了");
        GithubUser githubUser = new GithubUser();
        githubUser.setName("view");
        githubUser.setId((long) 1234441111);
        githubUser.setBio("dkdkkdkd");
        httpServletRequest.getSession().setAttribute("user",githubUser);
        //冲定向
        return  "redirect:/";
    }
}
