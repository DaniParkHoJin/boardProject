package org.parkhojin.models.board;

import lombok.RequiredArgsConstructor;
import org.parkhojin.entities.BoardData;
import org.parkhojin.models.file.FileDeleteService;
import org.parkhojin.repositories.BoardDataRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class BoardDeleteService {
    private final BoardInfoService infoService;
    private final BoardDataRepository repository;
    private final FileDeleteService fileDeleteService;

    public void delete(Long seq) {
        BoardData data = infoService.get(seq);
        String gid = data.getGid();
        // 파일 삭제
        fileDeleteService.deleteByGid(gid);

        // 게시글 삭제
        repository.delete(data);

        repository.flush();
    }
}
