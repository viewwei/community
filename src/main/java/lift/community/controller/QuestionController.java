package lift.community.controller;

import lift.community.dot.QuestionDto;
import lift.community.mapper.QuesstionMapper;
import lift.community.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class QuestionController {
    @Autowired
    QuestionService questionService;
    @GetMapping("/question/{id}")
    public  String question(@PathVariable String id, Model model){
        QuestionDto questionDto =   questionService.getById(id);
        model.addAttribute("question",questionDto);
     return  "question";
    }
}
