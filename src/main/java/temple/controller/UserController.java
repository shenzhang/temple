package temple.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * User: shenzhang
 * Date: 8/20/14
 * Time: 9:15 PM
 */
@Controller
public class UserController {
    @RequestMapping(value = "/user", method = RequestMethod.GET)
    public String show() {
        return "userSearch";
    }

    @RequestMapping(value = "/user", method = RequestMethod.POST)
    public String detail(int userId, ModelMap model) {
        return "userDetails";
    }

    @RequestMapping(value = "/addUser", method = RequestMethod.GET)
    public String addUser() {
        return "addUser";
    }
}
