package temple.service;

import com.google.common.base.Strings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import temple.dao.CityDao;
import temple.dao.MemberContactDao;
import temple.dao.MemberDao;
import temple.dao.MemberNoteDao;
import temple.dao.TempleDao;
import temple.dao.UserDao;
import temple.model.City;
import temple.model.Member;
import temple.model.MemberNote;
import temple.model.SearchMemberInfo;
import temple.model.Temple;

import java.util.Date;
import java.util.List;

/**
 * User: shenzhang
 * Date: 8/30/14
 * Time: 8:13 PM
 */
@Service
public class MemberService {
    @Autowired
    private MemberDao memberDao;
    @Autowired
    private MemberContactDao memberContactDao;
    @Autowired
    private MemberNoteDao memberNoteDao;
    @Autowired
    private TempleDao templeDao;
    @Autowired
    private CityDao cityDao;
    @Autowired
    private UserDao userDao;

    @Transactional(readOnly = true)
    public List<Member> searchMember(SearchMemberInfo info) {
        List<Member> list = memberDao.searchMember(info);
        for (Member member : list) {
            if (!Strings.isNullOrEmpty(member.getMembershipAcquisitionTempleCode())) {
                member.setAcquisitionTemple(templeDao.getTempleByCode(member.getMembershipAcquisitionTempleCode()));
            }
        }
        return list;
    }

    @Transactional
    public int addMember(Member member) {
        updateLastModifyInformation(member);

        int memberId = memberDao.addMember(member);

        if (member.getMemberContact() != null) {
            memberContactDao.addMemberContact(memberId, member.getMemberContact());
        }

        if (member.getMemberNotes() != null) {
            for (MemberNote note : member.getMemberNotes()) {
                memberNoteDao.addMemberNote(memberId, note);
            }
        }

        return memberId;
    }

    @Transactional
    public void updateMember(Member member) {
        updateLastModifyInformation(member);

        memberDao.updateMember(member);

        Integer memberId = member.getId();
        if (member.getMemberContact() != null) {
            member.getMemberContact().setMemberId(memberId);
            if (memberContactDao.getMemberContact(memberId) != null) {
                memberContactDao.updateMemberContact(memberId, member.getMemberContact());
            } else {
                memberContactDao.addMemberContact(memberId, member.getMemberContact());
            }
        }

        memberNoteDao.deleteAllMemberNotes(memberId);
        if (member.getMemberNotes() != null) {
            for (MemberNote note : member.getMemberNotes()) {
                memberNoteDao.addMemberNote(memberId, note);
            }
        }
    }

    @Transactional(readOnly = true)
    public Member getMemberById(int id) {
        Member member = memberDao.getMemberById(id);
        member.setLastModifyUser(userDao.getUserByName(member.getLastUpdateUserId()));

        member.setMemberContact(memberContactDao.getMemberContact(id));
        member.setMemberNotes(memberNoteDao.listMemberNotes(id));

        return member;
    }

    private void updateLastModifyInformation(Member member) {
        member.setLastUpdateDate(new Date());
        member.setLastUpdateUserId(SecurityContextHolder.getContext().getAuthentication().getName());
    }

    @Transactional
    public void deleteMember(int memberId) {
        memberNoteDao.deleteAllMemberNotes(memberId);
        memberContactDao.deleteMemberContact(memberId);
        memberDao.deleteMemberById(memberId);
    }

    @Transactional(readOnly = true)
    public List<Temple> getAllTemples() {
        return templeDao.listAllTemples();
    }

    @Transactional(readOnly = true)
    public List<City> getAllCities() {
        return cityDao.listAllCities();
    }
}
