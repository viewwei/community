package lift.community.controller;

import lift.community.Model.Question;
import lift.community.Model.User;
import lift.community.mapper.QuesstionMapper;
import lift.community.mapper.UserMapper;
import org.springframework.ui.Model;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

@Controller
public class PublishController {
    @Autowired
    private QuesstionMapper quesstionMapper;
    @Autowired
    private UserMapper userMapper;

    @GetMapping("/publish/{id}")
    public  String edit(@PathVariable("id")Integer id,Model model){
       Question question= quesstionMapper.getById(id+"");
        model.addAttribute("title", question.getTitle());
        model.addAttribute("description", question.getDescription());
        model.addAttribute("tag", question.getTag());
        return "publish";
    }
    @GetMapping("/publish")
    public String publish() {
        return "publish";
    }

    @PostMapping("/publish")
    public String doPublish(
            @RequestParam("title") String title,
            @RequestParam("description") String description,
            @RequestParam("tag") String tag,
            HttpServletRequest httpServletRequest, Model model
    ) {
        model.addAttribute("title", title);
        model.addAttribute("description", description);
        model.addAttribute("tag", tag);
        if (title == null || title == "") {
            model.addAttribute("error", "标题不能为空");
            return "publish";
        }
        if (description == null || description == "") {
            model.addAttribute("error", "描述不能为空");
            return "publish";
        }
        if (tag == null || tag == "") {
            model.addAttribute("error", "标签不能为空");
            return "publish";
        }
//        User user = null;
//        Cookie[] cookies = httpServletRequest.getCookies();
//
//        if (cookies != null) {
//            for (Cookie cookie : cookies) {
//                if ("token".equals(cookie.getName())) {
//                    String token = cookie.getValue();
//                    user = userMapper.findByToken(token);
//
//                    break;
//                }
//            }
//        }
        User user = (User)httpServletRequest.getSession().getAttribute("user");
        if (user == null) {
            model.addAttribute("error", "用户未登陆");
            System.out.println("点点滴滴");
            return "publish";
        }
        Question question = new Question();
        question.setTitle(title);
        question.setDescription(description);
        question.setTag(tag);
        question.setCreator(user.getId());
        question.setGmt_create(System.currentTimeMillis());
        question.setGmt_modified(System.currentTimeMillis());
        quesstionMapper.pushishQuestion(question);
        return "redirect:/";
    }
}
