package com.maav.bandit.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.maav.bandit.repository.BanditRepository;
import com.maav.bandit.domain.Bandit;
import com.maav.bandit.domain.History;

import javax.inject.Inject;
import java.util.List;
import com.maav.bandit.service.HistoryService;


/**
 * Service class for managing bandits.
 */
@Service
@Transactional
public class BanditService {

    private final Logger log = LoggerFactory.getLogger(BanditService.class);

  
    @Inject
    private BanditRepository banditRepository;

    @Inject
    private HistoryService historyService;
 

    public void checkBossInJail(Bandit bandit){
        if (bandit.getIsBoss()){ /// Si es jefe
            if (bandit.getNotAvailable()){
                bossInJail(bandit);
            }else{
                bossOutJail(bandit);
            }
       }
    }
    public void bossInJail(Bandit bandit) {
     /*
     When an organization member goes to jail, he temporarily disappears from the organization.
      1. All his direct subordinates are immediately relocated and now work for the oldest 
      remaining boss at the same level as their previous boss. 
      2.
      If there is no such possible alternative boss the oldest direct subordinate of 
      the previous boss is promoted to be the boss of the others
     */
        System.out.println("\n**************************************************************");
        System.out.println("*********** BANDIDO JEFE ENTRA EN LA CARCEL    *****************");
        System.out.println("- Bandit con id: "+ bandit.getId() +", firstName: "+bandit.getFirstName());
        System.out.println("\n");
        Bandit banditOlderSameLevel = getNextBoss(bandit);
        System.out.println("\n**************************************************************");
        System.out.println("\n- Jefe encontrado que le sustituye, id:"+banditOlderSameLevel.getId()+", firstname:"+banditOlderSameLevel.getFirstName());
        System.out.println("\n");
        changeBossInJail(bandit,banditOlderSameLevel);
        System.out.println("\n");
        
        
    }

    public void bossOutJail(Bandit bandit) {

        System.out.println("\n**************************************************************");
        System.out.println("*********** BANDIDO SALE DE LA CARCEL     ********************");
        System.out.println("- Bandit con id: "+ bandit.getId() +", firstName:"+bandit.getFirstName()+ "\n");
        historyService.updateBackYourBoss(bandit.getId());
        System.out.println("\n");
       
    }
    public void changeBossInJail(Bandit banditOldBoss,Bandit banditNewBoss){
       List<Bandit> bandits= banditRepository.findChildrenListWithoutBoss(banditOldBoss.getId());
       for (Bandit bandit : bandits) {
        if (bandit.getId() != banditNewBoss.getId()){ // No cambiar el nuevo jefe
            System.out.println("\n**************************************************************");
            System.out.println("*********** CAMBIAR BANDIDO,su jefe directo **************");
           
            System.out.println("\n-- Bandit : id:"+bandit.getId()+",firstName:"+bandit.getFirstName());
            Bandit currentBandit = bandit;
            currentBandit.setIdBoss(banditNewBoss.getId());
            
            System.out.println("-- Jefe actual id: "+ banditOldBoss.getId()+",firstName:"+banditOldBoss.getFirstName());
            System.out.println("-- Jefe nuevo  id: "+ banditNewBoss.getId()+",firstName:"+banditNewBoss.getFirstName()+"\n");
            banditRepository.saveAndFlush(currentBandit);
            historyService.setHistoryBoss(banditOldBoss,bandit);
            System.out.println("----------- FIN CAMBIAR BANDIDO,su jefe directo ---------------------------------");
           
        }else{
            System.out.println("\n**************************************************************");
            System.out.println("*********** MARCAR NUEVO JEFE **************");
            System.out.println("-- Jefe nuevo  id: "+ bandit.getId()+",firstName:"+bandit.getFirstName()+"\n");
            historyService.setHistoryBoss(banditOldBoss,bandit);
            Bandit currentBandit = bandit;
            currentBandit.setIsBoss(true); // hacer jefe a uno nuevo
            currentBandit.setIdBoss(banditOldBoss.getIdBoss());
            banditRepository.saveAndFlush(currentBandit);
        }
      }
    }

    /*
        Buscar un jefe de sustitución porque el anterior esta en la carcel
    */
    public Bandit getNextBoss(Bandit bandit){
        List <Bandit> banditsOlderSameLevel = banditRepository.findOlderSameLevel(bandit.getId());
        
        if (!banditsOlderSameLevel.isEmpty()){
           return banditsOlderSameLevel.get(0);
        }else{
            /// No hay jefe en el mismo nivel
            System.out.println("----- ERROR -- no hay jefe en el mismo nivel, poner el jefe superior");
            return banditRepository.findOne(bandit.getIdBoss());
        }
    }


    /*
        Actualizar todos los valores  
    */
    public void updateBandit (Bandit bandit, Long id){
        Bandit currentBandit = banditRepository.findOne(id);
        currentBandit.setFirstName(bandit.getFirstName());
        currentBandit.setContent(bandit.getContent());
        currentBandit.setNotAvailable(bandit.getNotAvailable());
        currentBandit.setContent(bandit.getContent());
        currentBandit.setIdBoss(bandit.getIdBoss());
        currentBandit.setIsBoss(bandit.getIsBoss());
        currentBandit.setBanditDate(bandit.getBanditDate());
        banditRepository.save(currentBandit);
        checkBossInJail(currentBandit);
    }

    /*
      Buscar en los registros historicos y volver el valor anterior de la tabla bandit
    */
    public void updateBackBandit(History banditHistory){
 
        Bandit currentBandit = banditRepository.findOne(banditHistory.getIdChild());
        Bandit bossNow = banditRepository.findOne(banditHistory.getIdBandit());
       
        System.out.println("\n**************************************************************");
        System.out.println("*********** BANDIDO VUELVE A JEFE ANTERIOR *******************");
        System.out.println("- Bandit >> id:"+ currentBandit.getId()+",firstName:"+ currentBandit.getFirstName());
        System.out.println("-      Ahora   = Jefe: "+ currentBandit.getIdBoss()+", firstName:"+ currentBandit.getFirstName());
        System.out.println("-      Cambiar = Jefe: "+ banditHistory.getIdBandit()+", firstName:"+ bossNow.getFirstName());
        currentBandit.setIdBoss(banditHistory.getIdBandit());
        banditRepository.saveAndFlush(currentBandit);
    }

    /*
      Obtener listado de subordinadores de un bandido
    */
    public List<Bandit> getBanditChildren(Long id){

        List<Bandit> banditChildren = banditRepository.findChildrenListWithoutBoss(id);

        System.out.println("\n**************************************************************");
        System.out.println("*********** BANDIDO id:"+id+" , children *******************");
        for (Bandit bandit : banditChildren) {
          System.out.println("- Bandit >> id:"+ bandit.getId()+"\n");
        }
        return banditChildren;

    }

    /*
      Contar el numero de bandidos partiendo de un jefe
    */
    public Long countBanditChildren (Long id, Long countChildren){
       
        List<Bandit> banditChildren =getBanditChildren(id);
        if (!banditChildren.isEmpty()){
            countChildren= countChildren + Long.valueOf(banditChildren.size());
            for (Bandit bandit : banditChildren) {
              countChildren=countBanditChildren (bandit.getId(),countChildren);
            }
        }else{
            System.out.println("- Bandit >> id:"+ id +"-- no children\n");    
        }
        
        return countChildren;
    }
    /*
     Listado del jefe y todos sus subordinados, e incluir el total de subordinados
      del jefe
    */
    public List<Bandit> findOlderSameLevelAndBoss(Long id){
        Long countBandit = countBanditChildren(id,0L);
        System.out.println("");
        System.out.println("\n BANDIDO--->id:"+ id +"- TIENE A SU CARGO-->"+countBandit + "\n");
        System.out.println("");
        List <Bandit> banditList=banditRepository.findOlderSameLevelAndBoss(id);
        banditList.get(0).setBanditChildren(countBandit);

        return banditList;

    }
}
