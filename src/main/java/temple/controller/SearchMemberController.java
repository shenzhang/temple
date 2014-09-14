package temple.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
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
public class SearchMemberController extends TempleController {
    private static final String SEARCH_MENU = "search";

    @Autowired
    private MemberService memberService;

    @RequestMapping(method = RequestMethod.GET)
    public String show(Model model) {
        model.addAttribute("menu", SEARCH_MENU);
        model.addAttribute("info", new SearchMemberInfo());
        return "search";
    }

    @RequestMapping(method = RequestMethod.POST)
    public String search(@ModelAttribute("info") SearchMemberInfo searchMemberInfo, Model model) {
        model.addAttribute("menu", SEARCH_MENU);
        model.addAttribute("bannerSearchName", searchMemberInfo.getName());

        List<Member> list = memberService.searchMember(searchMemberInfo);
        model.addAttribute("result", list);

        return "search";
    }
}
