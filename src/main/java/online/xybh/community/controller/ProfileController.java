package online.xybh.community.controller;

import com.github.pagehelper.PageInfo;
import online.xybh.community.dto.QuestionDTO;
import online.xybh.community.model.Question;
import online.xybh.community.model.User;
import online.xybh.community.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * @Author: XYBH
 * @Description: 个人中心控制器
 * @Date: Created in 2020/2/8 0008 17:39
 * @Modified:
 */
@Controller
public class ProfileController {

    @Autowired
    private QuestionService questionService;

    @GetMapping("/profile/{action}")
    public String profile(@PathVariable(name = "action") String action,
                          HttpServletRequest request,
                          Model model,
                          @RequestParam(name = "page", defaultValue = "1") Integer page,
                          @RequestParam(name = "size", defaultValue = "5") Integer size) {
        User user = (User) request.getSession().getAttribute("user");
        if (user == null) {
            return "redirect:/";
        }
        if ("questions".equals(action)) {
            model.addAttribute("section", "questions");
            model.addAttribute("sectionName", "我的提问");
            List<Question> questions = questionService.queryQuestions(user.getId(), page, size);
            PageInfo pageInfo = new PageInfo(questions);
            List<QuestionDTO> questionDTOList = questionService.list(user.getId(), page, size);
            model.addAttribute("pageInfo", pageInfo);
            model.addAttribute("questions", questionDTOList);
        } else if ("replies".equals(action)) {
            model.addAttribute("section", "replies");
            model.addAttribute("sectionName", "最新回复");
        }
        return "profile";
    }
}
