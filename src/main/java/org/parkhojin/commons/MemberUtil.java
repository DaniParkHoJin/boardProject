package org.parkhojin.commons;

import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.parkhojin.commons.constants.MemberType;
import org.parkhojin.entities.Member;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class MemberUtil {
    private final HttpSession session;

    public boolean isLogin() {
        return getMember() != null;
    }

    /**
     * 관리자 여부 체크
     * @return
     */
    public boolean isAdmin() {
        return isLogin() && getMember().getMtype() == MemberType.ADMIN;
    }
    public Member getMember(){
        return (Member)session.getAttribute("loginMember");
    }
}
