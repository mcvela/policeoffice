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
import com.maav.bandit.service.BanditService;

import javax.inject.Inject;
import javax.validation.Valid;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

/**
 * REST endpoint for Bandit.
 */
@RestController
@RequestMapping("/api")
public class BandResource {
    private final Logger log = LoggerFactory.getLogger(BandResource.class);


    @Inject
    private BanditRepository banditRepository;

    @Inject
    private BanditService banditService;

    /**
     * GET  /band -> get all bandits like band.
     */
    /*
    @RequestMapping(value = "/band",
        method = RequestMethod.GET,
        produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Bandit> getAll() {
        log.debug("REST request to get all Bandits");
        return banditRepository.findOlderSameLevelAndBoss(1L);
    }
    */

    /**
     * GET  /band/:id -> get all bandits from id.
     */
    @RequestMapping(value = "/band/{id}",
        method = RequestMethod.GET,
        produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Bandit> getAllFromId(@PathVariable Long id) {
        log.debug("REST request to get all Bandits from id:"+id);

        return banditService.findOlderSameLevelAndBoss(id);
       
    }

    /**
     * POST  /band -> Create a new bandit.
     */
    @RequestMapping(value = "/band",
        method = RequestMethod.POST,
        produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> create(@RequestBody Bandit bandit) throws URISyntaxException {
        log.debug("REST request to save Bandit : {}", bandit);
        if (bandit.getId() != null) {
            return ResponseEntity.badRequest().header("Failure", "A new bandit cannot already have an ID").build();
        }
        banditRepository.save(bandit);
        return ResponseEntity.created(new URI("/api/band/" + bandit.getId())).build();
    }


    /**
     * PUT  /band -> Updates an existing bandit.
     */
    @RequestMapping(value = "/band/{id}",
        method = RequestMethod.PUT,
        produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> update(@PathVariable("id") long id, @RequestBody Bandit bandit) throws URISyntaxException {
        log.debug("REST request to update Bandit : {}", bandit);
        banditService.updateBandit(bandit,id);
        return ResponseEntity.ok().build();
    }

    /**
     * DELETE  /band/:id -> delete the "id" bandit.
     */
    @RequestMapping(value = "/band/{id}",
        method = RequestMethod.DELETE,
        produces = MediaType.APPLICATION_JSON_VALUE)
    public void delete(@PathVariable Long id) {
        log.debug("REST request to delete Bandits : {}", id);
        banditRepository.delete(id);
    }

}
