package temple.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
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
@RequestMapping("/members/new")
public class AddMemberController extends TempleController {
    private static final Logger LOG = LoggerFactory.getLogger(AddMemberController.class);

    @Autowired
    private MemberService memberService;

    @InitBinder
    public void initBinder(WebDataBinder binder) {
//        binder.addValidators(new Validator() {
//            @Override
//            public boolean supports(Class<?> clazz) {
//                return Member.class.isAssignableFrom(clazz);
//            }
//
//            @Override
//            public void validate(Object target, Errors errors) {
//                Member member = (Member) target;
//                if (member != null) {
//                    MemberContact memberContact = member.getMemberContact();
//                    if (memberContact == null || (isNullOrEmpty(memberContact.getHomePhone()) && isNullOrEmpty(memberContact.getMobilePhone()))) {
//                        String errorMessage = "Home/mobile phone is required";
//                        errors.rejectValue("memberContact.homePhone", null, errorMessage);
//                        errors.rejectValue("memberContact.mobilePhone", null, errorMessage);
//                    }
//                }
//            }
//        });
    }

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
    public String addMember(@ModelAttribute("member") @Validated Member member, BindingResult errors, RedirectAttributes redirectAttributes) {
        if (errors.hasErrors()) {
            return "addMember";
        }

        int memberId = memberService.addMember(member);
        message(redirectAttributes, true, "Add member success");

        return "redirect:/members/" + memberId;
    }
}
