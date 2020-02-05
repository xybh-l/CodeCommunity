package online.xybh.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @Author: XYBH
 * @Description: 首页控制器
 * @Date: Created in 2020/2/5 0005 21:04
 * @Modified:
 */
@Controller
public class IndexController {
    @RequestMapping("/")
    public String index(){
        return "index";
    }
}
