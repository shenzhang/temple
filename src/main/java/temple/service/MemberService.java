package temple.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import temple.dao.MemberDao;
import temple.model.Member;
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

    public List<Member> searchMember(SearchMemberInfo info) {
        return null;
    }
}
