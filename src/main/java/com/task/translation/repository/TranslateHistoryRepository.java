package com.task.translation.repository;

import com.task.translation.domain.TranslateHistoryEntity;
import java.util.List;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface TranslateHistoryRepository extends CrudRepository<TranslateHistoryEntity, Long> {
    Page<TranslateHistoryEntity> findAllByUserId(Long userId, Pageable pageable);

    Optional<TranslateHistoryEntity> findByIdAndUserId(Long id, Long userId);

    void deleteByIdIn(List<Long> ids);

    @Modifying
    @Transactional
    @Query(value = "delete from TranslateHistoryEntity u where u.id in ?1")
    void deleteAllWithIds(List<Long> ids);
}
