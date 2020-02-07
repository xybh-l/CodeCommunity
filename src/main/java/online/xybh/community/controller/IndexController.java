package online.xybh.community.controller;

import com.github.pagehelper.PageInfo;
import online.xybh.community.dto.QuestionDTO;
import online.xybh.community.mapper.QuestionMapper;
import online.xybh.community.mapper.UserMapper;
import online.xybh.community.model.Question;
import online.xybh.community.model.User;
import online.xybh.community.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * @Author: XYBH
 * @Description: 首页控制器
 * @Date: Created in 2020/2/5 0005 21:04
 * @Modified:
 */
@Controller
public class IndexController {

    @Autowired
    private UserMapper usermapper;
    @Autowired
    private QuestionService questionService;

    @RequestMapping("/")
    public String index(HttpServletRequest request,
                        Model model,
                        @RequestParam(name = "page", defaultValue = "1") Integer page,
                        @RequestParam(name = "size", defaultValue = "5") Integer size) {
        Cookie[] cookies = request.getCookies();
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals("token")) {
                    String token = cookie.getValue();
                    User user = usermapper.findByToken(token);
                    if (user != null) {
                        request.getSession().setAttribute("user", user);
                    }
                    break;
                }
            }
        }
        List<QuestionDTO> questionList = questionService.list(page, size);
//        List<Question> list = questionService.list(page, size);
        PageInfo pageInfo = new PageInfo(questionService.queryQuesitons(page, size));
        model.addAttribute("pageInfo", pageInfo);
        model.addAttribute("questions",questionList);
        return "index";
    }
}
