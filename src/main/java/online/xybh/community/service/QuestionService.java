package online.xybh.community.service;

import com.github.pagehelper.PageHelper;
import online.xybh.community.dto.QuestionDTO;
import online.xybh.community.mapper.QuestionMapper;
import online.xybh.community.mapper.UserMapper;
import online.xybh.community.model.Question;
import online.xybh.community.model.User;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author: XYBH
 * @Description:
 * @Date: Created in 2020/2/7 0007 16:56
 * @Modified:
 */
@Service
public class QuestionService {
    @Autowired
    private QuestionMapper questionMapper;
    @Autowired
    private UserMapper userMapper;


    public List<Question> queryQuesitons(Integer page, Integer size){
        PageHelper.startPage(page, size);
        return questionMapper.list();
    }

    public List<QuestionDTO> list(Integer page, Integer size) {
        List<Question> questions = queryQuesitons(page,size);
        List<QuestionDTO> questionDTOList = new ArrayList<>();
        for (Question question : questions) {
            User user = userMapper.findById(question.getCreator());
            QuestionDTO questionDTO = new QuestionDTO();
            BeanUtils.copyProperties(question, questionDTO);
            questionDTO.setUser(user);
            questionDTOList.add(questionDTO);
        }
        return questionDTOList;
    }
}
