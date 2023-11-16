package org.parkhojin.jpaex;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.parkhojin.entities.BoardData;
import org.parkhojin.repositories.BoardDataRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.test.context.TestPropertySource;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.springframework.data.domain.Sort.Order.asc;
import static org.springframework.data.domain.Sort.Order.desc;

@SpringBootTest
@TestPropertySource(properties = "spring.profiles.active=test")
public class Ex04 {

    @Autowired
    private BoardDataRepository repository;

    @BeforeEach
    void init() {
        List<BoardData> items = new ArrayList<>();

        for (int i = 1; i <= 10; i++) {
            items.add(BoardData.builder()
                    .subject("제목" + i)
                    .content("내용" + i)
                    .build());
        }
        repository.saveAllAndFlush(items);
    }

    @Test
    void test1() {
        List<BoardData> items = repository.findAll(Sort.by(desc("seq"), asc("subject")));

        items.stream().forEach(System.out::println);

    }

    @Test
    void test2() {
        Pageable pageable = PageRequest.of(2, 3);
        Page<BoardData> data = repository.findAll(pageable);
        List<BoardData> items = data.getContent();
        items.stream().forEach(System.out::println);
    }

    @Test
    void test3() {
        BoardData data = repository.findById(3L).orElseGet(null);
        System.out.println(data);
    }

    @Test
    void test4() {
        List<BoardData> items = repository.findBySubjectContainingOrContentContainingOrderBySeqDesc("목", "용");

        items.stream().forEach(System.out::println);
    }

    @Test
    void test5() {
        Pageable pageable = PageRequest.of(1, 3, Sort.by(desc("seq")));
        LocalDateTime now = LocalDateTime.now();
        List<BoardData> items = repository.findByCreatedAtBetween(now.minusDays(1), now.plusDays(1), pageable);
        items.stream().forEach(System.out::println);
    }

    @Test
    void test6() {
        List<BoardData> items = repository.getList("목", "용");
        items.stream().forEach(System.out::println);
    }

}
