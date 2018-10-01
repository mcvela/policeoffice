package com.maav.bandit.repository;

import com.maav.bandit.domain.History;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.transaction.annotation.Transactional;

/**
 * Spring Data JPA repository for the Bandit entity.
 */
public interface HistoryRepository extends JpaRepository<History, Long> {

    Optional<History> findOneById(@Param("id") 
                                Long id);
    @Query("select b from History b where b.idBandit = :id")
    List<History> findAllIdBandit(@Param("id") 
                                       Long id); 
    @Transactional
    @Modifying                                
    @Query("delete from History b where b.idBandit = :id")
    void deleteAllIdBandit(@Param("id") 
                             Long id);                                   

}
