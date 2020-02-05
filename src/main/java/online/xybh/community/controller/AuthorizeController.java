package online.xybh.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @Author: XYBH
 * @Description: Github认证
 * @Date: Created in 2020/2/5 0005 23:22
 * @Modified:
 */
@Controller
public class AuthorizeController {
    @RequestMapping("/callback")
    public String callback(@RequestParam(name = "code") String code,
                           @RequestParam(name = "state") String state){
        return "index";
    }
}
