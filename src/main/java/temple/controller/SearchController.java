package temple.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import temple.model.SearchMemberInfo;

/**
 * User: shenzhang
 * Date: 8/18/14
 * Time: 10:52 PM
 */
@Controller
@RequestMapping("/search")
public class SearchController {
    private static final String SEARCH_MENU = "search";

    @RequestMapping(method = RequestMethod.GET)
    public String show(ModelMap model) {
        model.put("menu", SEARCH_MENU);
        return "search";
    }

    @RequestMapping(method = RequestMethod.POST)
    public String search(@ModelAttribute SearchMemberInfo searchMemberInfo, ModelMap model) {
        model.put("menu", SEARCH_MENU);
        return "search";
    }
}
