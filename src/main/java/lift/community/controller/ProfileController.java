package lift.community.controller;

import lift.community.Model.User;
import lift.community.dot.PageinationDTO;
import lift.community.mapper.UserMapper;
import lift.community.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

@Controller
public class ProfileController {
    @Autowired
    private UserMapper userMapper;
    @Autowired
    QuestionService questionService;

    @GetMapping("/profile/{action}")
    public String profile(HttpServletRequest httpServletRequest,
                          @PathVariable(name = "action") String action,
                          Model model,
                          @RequestParam(name = "page", defaultValue = "1") Integer page,
                          @RequestParam(name = "size", defaultValue = "5") Integer size
    ) {
//        Cookie[] cookies = httpServletRequest.getCookies();
//        User user = null;
//        if (cookies != null) {
//            for (Cookie cookie : cookies) {
//                if ("token".equals(cookie.getName())) {
//                    String token = cookie.getValue();
//                    user = userMapper.findByToken(token);
//                    if (user != null) {
//                        httpServletRequest.getSession().setAttribute("user", user);
//                    }
//                    break;
//                }
//            }
//        }
        User user = (User)httpServletRequest.getSession().getAttribute("user");
        if (user == null) {
            return "redirect:/";
        }
        if ("questions".equals(action)) {
            model.addAttribute("sectionName", "我的提问");
            model.addAttribute("section", "questions");
        } else if ("replies".equals(action)) {
            model.addAttribute("sectionName", "最新回复");
            model.addAttribute("section", "replies");
        }
        PageinationDTO pagination = questionService.listPerson(user.getId(), page, size);
        model.addAttribute("pagination", pagination);
        return "profile";
    }
}
