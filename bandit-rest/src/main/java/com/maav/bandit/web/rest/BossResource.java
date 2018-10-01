package com.maav.bandit.web.rest;

import com.maav.bandit.domain.Bandit;
import com.maav.bandit.domain.User;
import com.maav.bandit.repository.BanditRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.inject.Inject;
import javax.validation.Valid;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import com.maav.bandit.service.BanditService;

/**
 * REST endpoint for Bandit (Boss).
 */
@RestController
@RequestMapping("/api")
public class BossResource {
    private final Logger log = LoggerFactory.getLogger(BossResource.class);
    protected EntityManager em;

    @Inject
    private BanditRepository banditRepository;

    @Inject
    private BanditService  banditService;

    /**
     * GET  /bosses -> get all bandits.
     */
    @RequestMapping(value = "/bosses",
        method = RequestMethod.GET,
        produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Bandit> getAll() {
        log.debug("REST request to get all Boss Bandits");
        return banditRepository.findByIsBossAvailable();
            
    }

    /**
     * GET  /bosses/:id -> get "id" bandit.
     */
    @RequestMapping(value = "/bosses/{id}",
        method = RequestMethod.GET,
        produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Bandit> getAllChildrens(@PathVariable Long id) {
            log.debug("REST request to get all Boss Childrens");
            return banditRepository.findChildrenList(id);
                
        }

    /**
     * PUT  /bosses -> Updates an existing bandit.
     */
    @RequestMapping(value = "/bosses/{id}",
        method = RequestMethod.PUT,
        produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> update(@PathVariable("id") long id, @RequestBody Bandit bandit) throws URISyntaxException {
        log.debug("REST request to update Bandit : {}", bandit);
        banditService.updateBandit(bandit,id);
        return ResponseEntity.ok().build();
    }

    /**
     * DELETE  /bosses/:id -> delete the "id" bandit.
     */
    @RequestMapping(value = "/bosses/{id}",
        method = RequestMethod.DELETE,
        produces = MediaType.APPLICATION_JSON_VALUE)
    public void delete(@PathVariable Long id) {
        log.debug("REST request to delete Bandits : {}", id);
        banditRepository.delete(id);
    }
  

}
