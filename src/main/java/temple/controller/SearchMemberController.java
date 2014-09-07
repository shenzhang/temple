package temple.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import temple.model.Member;
import temple.model.SearchMemberInfo;
import temple.service.MemberService;

import java.util.List;

/**
 * User: shenzhang
 * Date: 8/18/14
 * Time: 10:52 PM
 */
@Controller
@RequestMapping("/search")
public class SearchMemberController {
    private static final String SEARCH_MENU = "search";

    @Autowired
    private MemberService memberService;

    @RequestMapping(method = RequestMethod.GET)
    public String show(ModelMap model) {
        model.put("menu", SEARCH_MENU);
        model.put("info", new SearchMemberInfo());
        return "search";
    }

    @RequestMapping(method = RequestMethod.POST)
    public String search(@ModelAttribute("info") SearchMemberInfo searchMemberInfo, ModelMap model) {
        model.put("menu", SEARCH_MENU);
        model.put("bannerSearchName", searchMemberInfo.getName());

        List<Member> list = memberService.searchMember(searchMemberInfo);
        model.put("result", list);

        return "search";
    }
}
