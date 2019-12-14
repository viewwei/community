package lift.community.service;

import lift.community.Model.Question;
import lift.community.Model.User;
import lift.community.dot.PageinationDTO;
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

    public PageinationDTO list(Integer page, Integer size) {
        Integer offset = size * (page - 1);
        List<Question> questionList = quesstionMapper.listView(offset, size);
        List<QuestionDto> questionDtoList = new ArrayList<>();
        for (Question question : questionList) {
            User user = userMapper.findByID(question.getCreator());
            QuestionDto questionDto = new QuestionDto();
            BeanUtils.copyProperties(question, questionDto);
            questionDto.setUser(user);
            questionDtoList.add(questionDto);
        }
        PageinationDTO pageinationDTO = new PageinationDTO();
        pageinationDTO.setQuestions(questionDtoList);
        Integer total = quesstionMapper.count();
        pageinationDTO.setPagination(total, page, size);
        return pageinationDTO;
    }

    public PageinationDTO listPerson(Integer id, Integer page, Integer size) {
        Integer offset = size * (page - 1);
        List<Question> questionList = quesstionMapper.listPerson(id, offset, size);
        List<QuestionDto> questionDtoList = new ArrayList<>();
        for (Question question : questionList) {
            User user = userMapper.findByID(question.getCreator());
            QuestionDto questionDto = new QuestionDto();
            BeanUtils.copyProperties(question, questionDto);
            questionDto.setUser(user);
            questionDtoList.add(questionDto);
        }
        PageinationDTO pageinationDTO = new PageinationDTO();
        pageinationDTO.setQuestions(questionDtoList);
        Integer total = quesstionMapper.countQuestion(id);
        pageinationDTO.setPagination(total, page, size);
        return pageinationDTO;
    }

    public QuestionDto getById(String id) {
        Question question = quesstionMapper.getById(id);
        QuestionDto questionDto = new QuestionDto();
        BeanUtils.copyProperties(question,questionDto);
        User user = userMapper.findByID(question.getCreator());
        questionDto.setUser(user);
        return  questionDto;
    }
}
