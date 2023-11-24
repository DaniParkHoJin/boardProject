package org.parkhojin.models.board;

import lombok.RequiredArgsConstructor;
import org.parkhojin.entities.BoardData;
import org.parkhojin.models.board.config.BoardDataNotFoundException;
import org.parkhojin.repositories.BoardDataRepository;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor

public class BoardInfoService {

    private final BoardDataRepository boardDataRepository;

    public BoardData get(Long seq){

        BoardData data = boardDataRepository.findById(seq).orElseThrow(BoardDataNotFoundException::new);

        return data;
    }

}
