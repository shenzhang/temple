package temple.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import temple.model.SearchUserInfo;

/**
 * User: shenzhang
 * Date: 8/20/14
 * Time: 9:15 PM
 */
@Controller
public class UserController {
    private static final String SEARCH_USER_MENU = "searchUser";

    @RequestMapping(value = "/searchUser", method = RequestMethod.GET)
    public String show(ModelMap model) {
        model.put("menu", SEARCH_USER_MENU);
        return "searchUser";
    }

    @RequestMapping(value = "/searchUser", method = RequestMethod.POST)
    public String detail(@ModelAttribute SearchUserInfo searchUserInfo, ModelMap model) {
        return "searchUser";
    }

    @RequestMapping(value = "/addUser", method = RequestMethod.GET)
    public String addUser() {
        return "addUser";
    }
}
