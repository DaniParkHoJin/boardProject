package org.parkhojin.models.comment;

import lombok.RequiredArgsConstructor;
import org.parkhojin.commons.MemberUtil;
import org.parkhojin.controllers.comments.CommentForm;
import org.parkhojin.controllers.comments.CommentFormValidator;
import org.parkhojin.entities.BoardData;
import org.parkhojin.entities.CommentData;
import org.parkhojin.models.board.BoardDataNotFoundException;
import org.parkhojin.repositories.BoardDataRepository;
import org.parkhojin.repositories.CommentDataRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.validation.Errors;

@Service
@RequiredArgsConstructor
public class CommentSaveService {
    private final CommentDataRepository commentDataRepository;
    private final BoardDataRepository boardDataRepository;
    private final CommentInfoService commentInfoService;
    private final CommentFormValidator validator;
    private final MemberUtil memberUtil;
    private final PasswordEncoder encoder;

    public void save(CommentForm form, Errors errors) {
        validator.validate(form, errors);
        if (errors.hasErrors()) {
            return;
        }
        CommentData commentData = null;
        Long seq = form.getSeq();
        if (seq == null) { //  추가 - 게시글 번호, 회원 번호
            commentData = new CommentData();
            Long boardDataSeq = form.getBoardDataSeq();
            BoardData boardData = boardDataRepository.findById(boardDataSeq).orElseThrow(BoardDataNotFoundException::new);
            commentData.setBoardData(boardData);
            commentData.setMember(memberUtil.getMember());
        } else { // 수정
            commentData = commentDataRepository.findById(seq).orElseThrow(CommentNotFoundException::new);
        }
        commentData.setPoster(form.getPoster());
        commentData.setContent(form.getContent());

        String guestPw = form.getGuestPw();
        if (StringUtils.hasText(guestPw)) {
            commentData.setGuestPw(encoder.encode(guestPw));
        }

        save(commentData);


    }

    public void save(CommentData comment) {

        commentDataRepository.saveAndFlush(comment);

        // 총 댓글 갯수 없데이트
        Long boardDataSeq = comment.getBoardData().getSeq();
        commentInfoService.updateCommentCnt(boardDataSeq);
    }
}
