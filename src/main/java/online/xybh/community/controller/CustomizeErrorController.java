package online.xybh.community.controller;

import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @Author: XYBH
 * @Description:
 * @Date: Created in 2020/2/13 0013 18:04
 * @Modified:
 */
@Controller
@RequestMapping("${server.error.path:${error.path:/error}")
public class CustomizeErrorController implements ErrorController {

    @Override
    public String getErrorPath() {
        return "error";
    }
}
