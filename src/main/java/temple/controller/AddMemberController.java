package temple.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import temple.model.City;
import temple.model.Member;
import temple.model.Temple;
import temple.service.MemberService;

import java.util.List;

/**
 * User: shenzhang
 * Date: 8/18/14
 * Time: 10:52 PM
 */
@Controller
@RequestMapping("/addMember")
public class AddMemberController {
    @Autowired
    private MemberService memberService;

    @ModelAttribute("allTemples")
    public List<Temple> getAllTemples() {
        return memberService.getAllTemples();
    }

    @ModelAttribute("allCities")
    public List<City> getAllCities() {
        return memberService.getAllCities();
    }

    @ModelAttribute("menu")
    public String menu() {
        return "addMember";
    }

    @RequestMapping(method = RequestMethod.GET)
    public String addMember(Model model) {
        model.addAttribute("member", new Member());
        return "addMember";
    }

    @RequestMapping(method = RequestMethod.POST)
    public String addMember(@ModelAttribute("member") Member member) {
        memberService.addMember(member);
        return "redirect:search";
    }
}
