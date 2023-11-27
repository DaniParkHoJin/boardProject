package org.parkhojin.models.board.config;

import com.querydsl.core.BooleanBuilder;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.parkhojin.commons.ListData;
import org.parkhojin.commons.Pagination;
import org.parkhojin.commons.Utils;
import org.parkhojin.controllers.admins.BoardSearch;
import org.parkhojin.entities.Board;
import org.parkhojin.repositories.BoardRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import static org.springframework.data.domain.Sort.Order.desc;

@Service
@RequiredArgsConstructor
public class BoardConfigInfoService {

    private final BoardRepository repository;

    private final HttpServletRequest request;


    public Board get(String bId) {
        Board data = repository.findById(bId).orElseThrow(BoardNotFoundException::new);

        return data;
    }

    public ListData<Board> getList(BoardSearch search) {
        BooleanBuilder andBuilder = new BooleanBuilder();

        int page = Utils.getNumber(search.getPage(),1);
        int limit = Utils.getNumber(search.getLimit(),20);

        // Sort.Order>desc("엔티티 속성명"), Sort.Order.asc("엔티티 속성명")

        Pageable pageable = PageRequest.of(page-1, limit, Sort.by(desc("createdAt")));


        Page<Board> data = repository.findAll(andBuilder, pageable);

        Pagination pagination = new Pagination(page, (int)data.getTotalElements(), 10 , limit, request);

        ListData<Board> listData = new ListData<>();
        listData.setContent(data.getContent());
        listData.setPagination(pagination);

        return listData;
    }
}
