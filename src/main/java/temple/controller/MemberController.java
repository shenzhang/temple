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

import static com.google.common.collect.Lists.newArrayList;

/**
 * User: shenzhang
 * Date: 8/18/14
 * Time: 10:52 PM
 */
@Controller
@RequestMapping("/search")
public class MemberController {
    private static final String SEARCH_MENU = "search";

    @Autowired
    private MemberService memberService;

    @ModelAttribute("info")
    public SearchMemberInfo info() {
        return new SearchMemberInfo();
    }

    @RequestMapping(method = RequestMethod.GET)
    public String show(ModelMap model) {
        model.put("menu", SEARCH_MENU);
        return "search";
    }

    @RequestMapping(method = RequestMethod.POST)
    public String search(@ModelAttribute("info") SearchMemberInfo searchMemberInfo, ModelMap model) {
        model.put("menu", SEARCH_MENU);

        List<Member> list = newArrayList(new Member(), new Member());
        model.put("result", list);

        return "search";
    }

    @RequestMapping(value = "/addMember", method = RequestMethod.GET)
    public String addMember() {
        return "addMember";
    }

    @RequestMapping(value = "/addMember", method = RequestMethod.POST)
    public String addMember(@ModelAttribute("member") Member member) {

        return "redirect:search";
    }
}
