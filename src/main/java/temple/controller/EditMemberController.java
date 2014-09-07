package temple.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
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
@RequestMapping("/editMember")
public class EditMemberController {
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

    @RequestMapping(method = RequestMethod.GET)
    public String addMember(@RequestParam("memberId") int memberId, Model model) {
        Member member = memberService.getMemberById(memberId);
        model.addAttribute("member", member);
        return "editMember";
    }

    @RequestMapping(method = RequestMethod.POST)
    public String addMember(@ModelAttribute("member") Member member) {
        memberService.updateMember(member);
        return "redirect:search";
    }
}
