package temple.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import temple.model.User;

/**
 * User: shenzhang
 * Date: 8/30/14
 * Time: 5:32 PM
 */
@Controller
@RequestMapping("/test")
public class TestController {
    @RequestMapping(method = RequestMethod.GET)
    public void get(@ModelAttribute("fish")User user) {
    }
}
