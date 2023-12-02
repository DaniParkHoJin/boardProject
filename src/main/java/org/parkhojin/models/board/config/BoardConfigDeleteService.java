package org.parkhojin.models.board.config;

import lombok.RequiredArgsConstructor;
import org.parkhojin.commons.Utils;
import org.parkhojin.commons.exceptions.AlertException;
import org.parkhojin.entities.Board;
import org.parkhojin.repositories.BoardRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BoardConfigDeleteService {

    private final BoardRepository repository;
    private final Utils utils;

    /*
     *  게시판 설정 삭제
     *
     *  @param bId
     */
    public void delete(String bId) {
        Board board = repository.findById(bId).orElseThrow(BoardNotFoundException::new);

        repository.delete(board);
        repository.flush();
    }

    /*
     *  목록에서 일괄 삭제
     *
     *  @param idxes
     */
    public void delete(List<Integer> idxes) {
        if(idxes == null|| idxes.isEmpty()){
            throw new AlertException("삭제할 게시판을 선택하세요.");

        }
        for (int idx : idxes) {
            String bId = utils.getParam("bId_" + idx);
            Board board = repository.findById(bId).orElse(null);
            if (board == null) continue;

            repository.delete(board);

        }
        repository.flush();


    }
}
