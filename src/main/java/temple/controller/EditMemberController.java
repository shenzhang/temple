package temple.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import temple.model.City;
import temple.model.Member;
import temple.model.MemberContact;
import temple.model.Temple;
import temple.service.MemberNoteService;
import temple.service.MemberService;

import java.util.List;

import static com.google.common.base.Strings.isNullOrEmpty;

/**
 * User: shenzhang
 * Date: 8/18/14
 * Time: 10:52 PM
 */
@Controller
@RequestMapping("/members/{memberId}")
public class EditMemberController extends TempleController {
    private static final Logger LOG = LoggerFactory.getLogger(EditMemberController.class);

    @Autowired
    private MemberService memberService;
    @Autowired
    private MemberNoteService memberNoteService;

    @InitBinder
    public void initBinder(WebDataBinder binder) {
        binder.addValidators(new Validator() {
            @Override
            public boolean supports(Class<?> clazz) {
                return Member.class.isAssignableFrom(clazz);
            }

            @Override
            public void validate(Object target, Errors errors) {
                Member member = (Member) target;
                if (member != null) {
                    MemberContact memberContact = member.getMemberContact();
                    if (memberContact == null || (isNullOrEmpty(memberContact.getHomePhone()) && isNullOrEmpty(memberContact.getMobilePhone()))) {
                        String errorMessage = "Home/mobile phone is required";
                        errors.rejectValue("memberContact.homePhone", null, errorMessage);
                        errors.rejectValue("memberContact.mobilePhone", null, errorMessage);
                    }
                }
            }
        });
    }

    @ModelAttribute("allTemples")
    public List<Temple> getAllTemples() {
        return memberService.getAllTemples();
    }

    @ModelAttribute("allCities")
    public List<City> getAllCities() {
        return memberService.getAllCities();
    }

    @RequestMapping(method = RequestMethod.GET)
    public String getMember(@PathVariable("memberId") int memberId, Model model) {
        Member member = memberService.getMemberById(memberId);
        model.addAttribute("member", member);

        return "editMember";
    }

    @RequestMapping(method = RequestMethod.POST)
    public String editMember(@ModelAttribute("member") @Validated Member member, BindingResult errors, Model model) {
        retrieveMemberNotes(member);

        if (errors.hasErrors()) {
            return "editMember";
        }

        memberService.updateMember(member);
        message(model, true, "Update member success");

        return "editMember";
    }

    private void retrieveMemberNotes(Member member) {
        if (member != null || member.getId() != null) {
            member.setMemberNotes(memberNoteService.listMemberNotes(member.getId()));
        }
    }
}
