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
import com.maav.bandit.service.BanditService;

/**
 * REST endpoint for Bandit (Jail).
 */
@RestController
@RequestMapping("/api")
public class JailResource {
    private final Logger log = LoggerFactory.getLogger(JailResource.class);


    @Inject
    private BanditRepository banditRepository;

    @Inject
    private BanditService banditService;
    

    /**
     * GET  /jails -> get all bandits (jail).
     */
    @RequestMapping(value = "/jails",
        method = RequestMethod.GET,
        produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Bandit> getAll() {
        log.debug("REST request to get all Jail Bandits");
        return banditRepository.findByNotAvailable(true);
    }
  /**
     * PUT  /jails -> Updates an existing bandit.
     */
    @RequestMapping(value = "/jails/{id}",
        method = RequestMethod.PUT,
        produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> update(@PathVariable("id") long id, @RequestBody Bandit bandit) throws URISyntaxException {
        log.debug("REST request to update Bandit : {}", bandit);
        banditService.updateBandit(bandit,id);
        return ResponseEntity.ok().build();
    }

    /**
     * DELETE  /jails/:id -> delete the "id" bandit.
     */
    @RequestMapping(value = "/jails/{id}",
        method = RequestMethod.DELETE,
        produces = MediaType.APPLICATION_JSON_VALUE)
    public void delete(@PathVariable Long id) {
        log.debug("REST request to delete Bandits : {}", id);
        banditRepository.delete(id);
    }
  
    


  

}
