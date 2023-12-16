package org.parkhojin.repositories;

import org.parkhojin.entities.BoardView;
import org.parkhojin.entities.BoardViewId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;

public interface BoardViewRepository extends JpaRepository<BoardView, BoardViewId>, QuerydslPredicateExecutor<BoardView> {
}
