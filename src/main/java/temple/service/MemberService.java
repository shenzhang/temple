package temple.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import temple.dao.MemberContactDao;
import temple.dao.MemberDao;
import temple.dao.MemberNoteDao;
import temple.model.Member;
import temple.model.MemberNote;
import temple.model.SearchMemberInfo;

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

    public List<Member> searchMember(SearchMemberInfo info) {
        return null;
    }

    @Transactional
    public long addMember(Member member) {
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
    public void deleteMember(int memberId) {
        memberNoteDao.deleteAllMemberNotes(memberId);
        memberContactDao.deleteMemberContact(memberId);
        memberDao.deleteMemberById(memberId);
    }
}
