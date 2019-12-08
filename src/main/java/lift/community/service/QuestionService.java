package lift.community.service;

import lift.community.Model.Question;
import lift.community.Model.User;
import lift.community.dot.QuestionDto;
import lift.community.mapper.QuesstionMapper;
import lift.community.mapper.UserMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class QuestionService {
    @Autowired
    private QuesstionMapper quesstionMapper;
    @Autowired
    private UserMapper userMapper;
    public List<QuestionDto> list() {
        List<Question> questionList = quesstionMapper.list();
        List<QuestionDto>questionDtoList = new ArrayList<>();
        for (Question question : questionList) {
         User user = userMapper.findByID(question.getCreator());
            QuestionDto questionDto = new QuestionDto();
            BeanUtils.copyProperties(question,questionDto);
            questionDto.setUser(user);
            questionDtoList.add(questionDto);
        }
        return  questionDtoList;
    }
}
