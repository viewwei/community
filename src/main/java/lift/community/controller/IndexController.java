package lift.community.controller;

import lift.community.Model.User;
import lift.community.dot.PageinationDTO;
import lift.community.dot.QuestionDto;
import lift.community.mapper.UserMapper;
import lift.community.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
public class IndexController {
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private QuestionService questionService;

    @GetMapping("/")
    public String index(HttpServletRequest httpServletRequest, Model model,
                        @RequestParam(name = "page", defaultValue = "1") Integer page,
                        @RequestParam(name = "size", defaultValue = "5") Integer size
    ) {
//        Cookie[] cookies = httpServletRequest.getCookies();
//        if (cookies != null) {
//            for (Cookie cookie : cookies) {
//                if ("token".equals(cookie.getName())) {
//                    String token = cookie.getValue();
//                    User user = userMapper.findByToken(token);
//                    if (user != null) {
//                        httpServletRequest.getSession().setAttribute("user", user);
//                    }
//                    break;
//                }
//            }
//        }
//        List<QuestionDto> questionList = questionService.list();
        PageinationDTO pagination = questionService.list(page, size);
        model.addAttribute("pagination", pagination);

        return "index";
    }
}
