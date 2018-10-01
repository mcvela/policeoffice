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
public class BanditResource {
    private final Logger log = LoggerFactory.getLogger(BanditResource.class);


    @Inject
    private BanditRepository banditRepository;

    @Inject
    private BanditService banditService;

    /**
     * GET  /bandits -> get all bandits.
     */
    @RequestMapping(value = "/bandits",
        method = RequestMethod.GET,
        produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Bandit> getAll() {
        log.debug("REST request to get all Bandits");
        return banditRepository.findAll();
    }

    /**
     * GET  /bandits/:id -> get "id" bandit.
     */
    @RequestMapping(value = "/bandits/{id}",
        method = RequestMethod.GET,
        produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Bandit> get(@PathVariable Long id) {
        return banditRepository.findOneById(id)
            .map(bandit -> new ResponseEntity<>(bandit, HttpStatus.OK))
            .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    /**
     * POST  /bandits -> Create a new bandit.
     */
    @RequestMapping(value = "/bandits",
        method = RequestMethod.POST,
        produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> create(@RequestBody Bandit bandit) throws URISyntaxException {
        log.debug("REST request to save Bandit : {}", bandit);
        if (bandit.getId() != null) {
            return ResponseEntity.badRequest().header("Failure", "A new bandit cannot already have an ID").build();
        }
        banditRepository.save(bandit);
        return ResponseEntity.created(new URI("/api/bandits/" + bandit.getId())).build();
    }


    /**
     * PUT  /bandits -> Updates an existing bandit.
     */
    @RequestMapping(value = "/bandits/{id}",
        method = RequestMethod.PUT,
        produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> update(@PathVariable("id") long id, @RequestBody Bandit bandit) throws URISyntaxException {
        log.debug("REST request to update Bandit : {}", bandit);
        banditService.updateBandit(bandit,id);
        return ResponseEntity.ok().build();
    }

    /**
     * DELETE  /bandits/:id -> delete the "id" bandit.
     */
    @RequestMapping(value = "/bandits/{id}",
        method = RequestMethod.DELETE,
        produces = MediaType.APPLICATION_JSON_VALUE)
    public void delete(@PathVariable Long id) {
        log.debug("REST request to delete Bandits : {}", id);
        banditRepository.delete(id);
    }

}
