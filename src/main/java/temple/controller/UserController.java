package temple.controller;

import com.google.common.base.Strings;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import temple.model.SearchUserInfo;
import temple.model.User;
import temple.service.UserService;

/**
 * User: shenzhang
 * Date: 8/20/14
 * Time: 9:15 PM
 */
@Controller
public class UserController extends TempleController {
    private static final Logger log = LoggerFactory.getLogger(UserController.class);
    private static final String SEARCH_USER_MENU = "searchUser";

    @Autowired
    private UserService userService;

    @ModelAttribute("newUser")
    public User newUser() {
        User user = new User();
        return user;
    }

    @ModelAttribute("menu")
    public String menu() {
        return SEARCH_USER_MENU;
    }

    @ModelAttribute("info")
    public SearchUserInfo searchInfo() {
        return new SearchUserInfo();
    }

    @RequestMapping(value = "/searchUser", method = RequestMethod.GET)
    public String show(Model model) {
        return "searchUser";
    }

    @RequestMapping(value = "/searchUser", method = RequestMethod.POST)
    public String search(@ModelAttribute("info") SearchUserInfo searchUserInfo, BindingResult errors, Model model) {
        validateSearchUserInfo(searchUserInfo, errors);
        if (errors.hasErrors()) {
            return "searchUser";
        }

        log.info("Search user by name, name = {}", searchUserInfo.getUserName());
        User user = userService.getUser(searchUserInfo.getUserName());
        if (user == null) {
            message(model, false, "No Result");
        } else {
            model.addAttribute("user", user);
            message(model, true, "Search Success");
        }

        return "searchUser";
    }

    @RequestMapping(value = "/deleteUser", method = RequestMethod.POST)
    public String delete(int userId) {
        userService.deleteUser(userId);
        return "redirect:searchUser";
    }

    @RequestMapping(value = "/addUser", method = RequestMethod.POST)
    public String addUser(@ModelAttribute("newUser") User user, BindingResult result, Model model, RedirectAttributes redirectAttributes) {
        validateBasicUser(user, result);
        validateUserExist(user, result);

        if (result.hasErrors()) {
            model.addAttribute("showDialog", true);
            return "searchUser";
        } else {
            log.info("Add user: {}", user.toString());
            userService.addUser(user);
            message(redirectAttributes, true, "create user success");
        }

        return "redirect:searchUser";
    }

    @RequestMapping(value = "/updateUser", method = RequestMethod.POST)
    public String updateUser(@ModelAttribute("user") User user, BindingResult errors, Model model) {
        if (user.getId() <= 0) {
            errors.rejectValue("id", null, "Should provide user id");
        }
        validateBasicUser(user, errors);

        if (!errors.hasErrors()) {
            log.info("Update user: {}", user.toString());
            userService.updateUser(user.getId(), user);
            message(model, true, "Update user successfully");
        }

        return "searchUser";
    }

    private void validateSearchUserInfo(SearchUserInfo info, Errors errors) {
        String userName = info.getUserName();
        if (Strings.isNullOrEmpty(userName)) {
            errors.rejectValue("userName", null, "Please input valid user name");
        }
    }

    private void validateBasicUser(User user, Errors errors) {
        ValidationUtils.rejectIfEmpty(errors, "name", null, "Should provide user name");
        ValidationUtils.rejectIfEmpty(errors, "password", null, "Should provide password");

        if (!user.getPassword().equals(user.getConfirmPassword())) {
            errors.rejectValue("confirmPassword", null, "Confirmed password is different with password");
        }
    }

    private void validateUserExist(User user, Errors errors) {
        String name = user.getName();
        if (!Strings.isNullOrEmpty(name)) {
            if (userService.getUser(name) != null) {
                errors.rejectValue("name", null, "User name has already exists");
            }
        }
    }
}
