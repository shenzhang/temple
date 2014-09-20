package temple.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import temple.dao.MemberNoteDao;
import temple.model.MemberNote;

import java.util.List;

/**
 * User: shenzhang
 * Date: 9/14/14
 * Time: 4:52 PM
 */
@Service
public class MemberNoteService {
    @Autowired
    private MemberNoteDao memberNoteDao;

    public MemberNote addMemberNote(int memberId, MemberNote note) {
        int noteId = memberNoteDao.addMemberNote(memberId, note);
        return memberNoteDao.getMemberNote(noteId);
    }

    public void deleteMemberNote(int noteId) {
        memberNoteDao.deleteMemberNote(noteId);
    }

    public MemberNote updateMemberNote(MemberNote note) {
        memberNoteDao.updateMemberNote(note.getNoteId(), note);
        return memberNoteDao.getMemberNote(note.getNoteId());
    }

    public List<MemberNote> listMemberNotes(int memberId) {
        return memberNoteDao.listMemberNotes(memberId);
    }
}
