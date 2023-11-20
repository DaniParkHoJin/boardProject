package org.parkhojin.jpaex;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.parkhojin.commons.constants.MemberType;
import org.parkhojin.entities.BoardData;
import org.parkhojin.entities.Member;
import org.parkhojin.models.board.BoardListService;
import org.parkhojin.repositories.BoardDataRepository;
import org.parkhojin.repositories.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@SpringBootTest
@TestPropertySource(properties = "spring.profiles.active=test")
@Transactional
public class Ex02 {
    @Autowired
    private MemberRepository memberRepository;
    @Autowired
    private BoardDataRepository boardDataRepository;
    @PersistenceContext
    private EntityManager em;
    @Autowired
    private BoardListService listService;

    @BeforeEach
    void init() {
        Member member = Member.builder()
                .email("user01@test.org")
                .password("12345678")
                .userNm("사용자01")
                .mtype(MemberType.USER)
                .mobile("01012345678")
                .build();

        memberRepository.saveAndFlush(member);

        List<BoardData> items = new ArrayList<>();
        for (int i = 1; i <= 10; i++) {
            BoardData item = BoardData.builder()
                    .subject("제목" + i)
                    .content("내용" + i)
                    .member(member)
                    .build();
            items.add(item);
        }
        boardDataRepository.saveAllAndFlush(items);
        em.clear();
    }

    @Test
    void test1() {
        List<BoardData> items = boardDataRepository.findAll(); // 10개
        for (BoardData item : items) {
            Member member = item.getMember();
            String email = member.getEmail(); // 2차 쿼리
            System.out.println(email);
        }
    }

    @Test
    void test2() {
        List<BoardData> items = boardDataRepository.getList2();
    }



    @Test
    void test3() {
        List<BoardData> items = listService.getList();
    }
    @Test
    void test4() {
        List<BoardData> items = boardDataRepository.findBySubjectContaining("목");
        }

    @Test
    void test5() {
        Member member = memberRepository.findByEmail("user01@test.org").orElse(null);
        List<BoardData> items = member.getItems();
        items.stream().forEach(System.out::println);

        memberRepository.delete(member);
        memberRepository.flush();
    }
}