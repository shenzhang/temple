package temple.controller.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import temple.model.MemberNote;
import temple.service.MemberNoteService;

/**
 * User: shenzhang
 * Date: 9/13/14
 * Time: 10:36 PM
 */
@RestController
public class MemberNoteController {
    @Autowired
    private MemberNoteService memberNoteService;

    @RequestMapping(value = "/members/{memberId}/notes", method = RequestMethod.GET)
    public void getAllNotes(@PathVariable("memberId") int memberId) {
    }

    @RequestMapping(value = "/members/{memberId}/notes", method = RequestMethod.POST)
    public MemberNote addNote(@PathVariable("memberId") int memberId, @RequestParam("note") String note) {
        MemberNote memberNote = new MemberNote();
        memberNote.setMemberId(memberId);
        memberNote.setNote(note);

        return memberNoteService.addMemberNote(memberId, memberNote);
    }

    @RequestMapping(value = "/members/{memberId}/notes/{noteId}", method = RequestMethod.DELETE)
    public void deleteNote(@PathVariable("noteId") int noteId) {
        memberNoteService.deleteMemberNote(noteId);
    }

    @RequestMapping(value = "/members/{memberId}/notes/${noteId}", method = RequestMethod.POST)
    public void updateNote(@PathVariable("memberId") int memberId, @PathVariable("noteId") int noteId, @RequestParam(value = "note", required = false) String note) {
        MemberNote memberNote = new MemberNote();
        memberNote.setMemberId(memberId);
        memberNote.setNoteId(noteId);
        memberNote.setNote(note);

        memberNoteService.updateMemberNote(memberNote);
    }
}
