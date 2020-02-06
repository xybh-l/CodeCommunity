package online.xybh.community.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @Author: XYBH
 * @Description: 问题发布页面
 * @Date: Created in 2020/2/6 0006 20:28
 * @Modified:
 */
@Controller
public class PublishController {
    @GetMapping("/publish")
    public String publish(){
        return "publish";
    }
}
