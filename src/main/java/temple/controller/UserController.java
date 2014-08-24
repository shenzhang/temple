package temple.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import temple.model.NewUser;
import temple.model.SearchUserInfo;
import temple.model.User;
import temple.service.UserService;

/**
 * User: shenzhang
 * Date: 8/20/14
 * Time: 9:15 PM
 */
@Controller
public class UserController {
    private static final Logger log = LoggerFactory.getLogger(UserController.class);
    private static final String SEARCH_USER_MENU = "searchUser";

    @Autowired
    private UserService userService;

    @ModelAttribute("newUser")
    public NewUser newUser() {
        NewUser user = new NewUser();
        return user;
    }

    @RequestMapping(value = "/searchUser", method = RequestMethod.GET)
    public String show(ModelMap model) {
        model.put("menu", SEARCH_USER_MENU);
        return "searchUser";
    }

    @RequestMapping(value = "/searchUser", method = RequestMethod.POST)
    public String detail(@ModelAttribute("info") SearchUserInfo searchUserInfo, ModelMap model) {
        model.put("menu", SEARCH_USER_MENU);

        log.info("Search user by id, id = ?", searchUserInfo.getUserId());
        User user = userService.getUser(searchUserInfo.getUserId());
        model.put("user", user);

        return "searchUser";
    }

    @RequestMapping(value = "/addUser", method = RequestMethod.POST)
    public String addUser(@ModelAttribute("newUser") NewUser user, BindingResult result, ModelMap model) {
        log.info("Add user: {}", user.toString());
        if (result.hasErrors()) {
            model.put("showDialog", true);
        } else {
            userService.addUser(user);
        }
        return "searchUser";
    }
}
