package com.maav.bandit.repository;

import com.maav.bandit.domain.Bandit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.Query;

/**
 * Spring Data JPA repository for the Bandit entity.
 */
public interface BanditRepository extends JpaRepository<Bandit, Long> {

    Optional<Bandit> findOneById(@Param("id") 
                                Long id);

    List<Bandit> findByIsBoss(@Param("isBoss") 
                                  Boolean isBoss);

    @Query("select b from Bandit b where b.isBoss = TRUE AND b.notAvailable = FALSE")
    List<Bandit> findByIsBossAvailable();    
    
    
    @Query("select b from Bandit b where (b.idBoss = :id OR b.id = :id ) AND b.notAvailable = FALSE order by banditDate")
    List<Bandit> findOlderSameLevelAndBoss(@Param("id") 
                                Long id);

    @Query("select b from Bandit b where b.idBoss = :id AND b.id != :id AND b.notAvailable = FALSE order by banditDate")
    List<Bandit> findOlderSameLevel(@Param("id") 
                                Long id);                                   

    List<Bandit> findByNotAvailable(@Param("notAvailable") 
                                  Boolean notAvailable);

    @Query("select b from Bandit b where b.idBoss = :id AND b.notAvailable = FALSE order by banditDate")
    List<Bandit> findChildrenList(@Param("id")
                                    Long id); 
                                    
    @Query("select b from Bandit b where b.idBoss = :id AND b.id != :id AND b.notAvailable = FALSE order by banditDate")
    List<Bandit> findChildrenListWithoutBoss(@Param("id")
                                             Long id); 
                                                                                                    
   
}
