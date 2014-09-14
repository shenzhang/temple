package temple.controller;

import org.springframework.ui.Model;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

/**
 * User: shenzhang
 * Date: 9/14/14
 * Time: 11:54 AM
 */
public class TempleController {
    protected void message(Model model, boolean success, String content) {
        model.addAttribute("messageStyle", success ? "success" : "failed");
        model.addAttribute("messageContent", content);
    }

    protected void message(RedirectAttributes model, boolean success, String content) {
        model.addFlashAttribute("messageStyle", success ? "success" : "failed");
        model.addFlashAttribute("messageContent", content);
    }
}
