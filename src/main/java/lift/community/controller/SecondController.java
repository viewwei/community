package lift.community.controller;

import lift.community.Model.User;
import lift.community.dot.GithubUser;
import lift.community.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.UUID;

@Controller
public class SecondController {
    @Autowired
    private UserMapper userMapper;

    @GetMapping("/hello")
    public String hello(HttpServletRequest httpServletRequest, HttpServletResponse response) {
        System.out.println("点击登录了");
        GithubUser githubUser = new GithubUser();
        githubUser.setName("view");
        githubUser.setId((long) 1111111);
        githubUser.setBio("dedekind");
        httpServletRequest.getSession().setAttribute("user", githubUser);
        User user = new User();
        String token = UUID.randomUUID().toString();
        user.setToken(token);
        user.setName("view");
        user.setAccountId("111111");
        user.setGmtCreate(System.currentTimeMillis());
        user.setGmtModified(user.getGmtCreate());
        userMapper.insert(user);
        response.addCookie(new Cookie("token", token));
        //冲定向
        return "redirect:/";
    }
}
