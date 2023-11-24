package org.parkhojin.models.board;

import lombok.RequiredArgsConstructor;
import org.parkhojin.controllers.boards.BoardForm;
import org.parkhojin.entities.BoardData;
import org.parkhojin.models.board.config.BoardNotFoundException;
import org.parkhojin.repositories.BoardDataRepository;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
@RequiredArgsConstructor
public class BoardSaveService {
    private final BoardDataRepository boardDataRepository;

    public void save(BoardForm form) {
        Long seq = form.getSeq();
        String mode = Objects.requireNonNullElse(form.getMode(),"add");

        BoardData data = null;
        if(mode.equals("update") && seq != null) {
            data = boardDataRepository.findById(seq).orElseThrow(BoardNotFoundException::new);
        } else {
            data = new BoardData();
        }

        data.setSubject(form.getSubject());
        data.setContent(form.getContent());
        data.setPoster(form.getPoster());

        boardDataRepository.saveAndFlush(data);
    }
}
