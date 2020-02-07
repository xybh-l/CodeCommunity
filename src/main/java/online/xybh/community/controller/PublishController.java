package online.xybh.community.controller;

import online.xybh.community.mapper.QuestionMapper;
import online.xybh.community.mapper.UserMapper;
import online.xybh.community.model.Question;
import online.xybh.community.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

/**
 * @Author: XYBH
 * @Description: 问题发布页面
 * @Date: Created in 2020/2/6 0006 20:28
 * @Modified:
 */
@Controller
public class PublishController {

    @Autowired
    private QuestionMapper questionMapper;
    @Autowired
    private UserMapper userMapper;

    @GetMapping("/publish")
    public String publish(HttpServletRequest request) {
        Cookie[] cookies = request.getCookies();
        User user;
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals("token")) {
                    String token = cookie.getValue();
                    user = userMapper.findByToken(token);
                    if (user != null) {
                        request.getSession().setAttribute("user", user);
                    }
                    break;
                }
            }
        }
        return "publish";
    }


    @PostMapping("/publish")
    public String doPublish(@RequestParam(value = "title") String title,
                            @RequestParam(value = "description") String description,
                            @RequestParam("tag") String tag,
                            HttpServletRequest request,
                            Model model) {
        model.addAttribute("title", title);
        model.addAttribute("description", description);
        model.addAttribute("tag", tag);
        if (title == null || title.equals("")) {
            model.addAttribute("error", "标题不能为空");
            return "publish";
        }
        if (description == null || description.equals("")) {
            model.addAttribute("error", "描述不能为空");
            return "publish";
        }
        if (tag == null || tag.equals("")) {
            model.addAttribute("error", "标签不能为空");
            return "publish";
        }
        Cookie[] cookies = request.getCookies();
        User user = null;
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals("token")) {
                    String token = cookie.getValue();
                    user = userMapper.findByToken(token);
                    if (user != null) {
                        request.getSession().setAttribute("user", user);
                    }
                    break;
                }
            }
            if (user == null) {
                model.addAttribute("error", "用户未登录");
                return "publish";
            }
            Question question = new Question();
            question.setTitle(title);
            question.setDescription(description);
            question.setTag(tag);
            question.setCreator(user.getId());
            question.setGmtCreate(System.currentTimeMillis());
            question.setGmtModified(question.getGmtCreate());
            questionMapper.create(question);
            return "redirect:/";
        }
        return "/";
    }
}
