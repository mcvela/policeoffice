package com.maav.bandit.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.maav.bandit.service.BanditService;
import com.maav.bandit.repository.HistoryRepository;
import com.maav.bandit.domain.History;
import com.maav.bandit.domain.Bandit;
import javax.inject.Inject;
import java.util.List;


/**
 * Service class for managing bandits history.
 */
@Service
@Transactional
public class HistoryService {

    private final Logger log = LoggerFactory.getLogger(HistoryService.class);

   

    @Inject
    private HistoryRepository historyRepository;
    @Inject
    private BanditService banditService;

    public void updateBackYourBoss(Long id){
       List<History> banditHistories= historyRepository.findAllIdBandit(id);
       for (History banditHistory : banditHistories) { 
          banditService.updateBackBandit(banditHistory);    
       }
       historyRepository.deleteAllIdBandit(id);
    }

    public void setHistoryBoss(Bandit banditBoss, Bandit banditChild){
        History newHistory = new History();
        newHistory.setIdBandit(banditBoss.getId());
        newHistory.setIdChild(banditChild.getId());
    
        historyRepository.saveAndFlush(newHistory); 
        System.out.println("---------- GUARDAR REGISTRO EN HISTORY       ---------------");
        System.out.println(" -- Bandit Jefe en prision id:"+banditBoss.getId()+",Bandit Hijo:"+banditChild.getId());
       System.out.println("\n"); 
    }
}
