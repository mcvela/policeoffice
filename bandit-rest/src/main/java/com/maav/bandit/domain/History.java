package com.maav.bandit.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;

import org.joda.time.DateTime;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.List;
import java.util.LinkedList;

/**
 * A Bandit for your bandit list
 * 
 */
@Entity
@Table(name = "history")
public class History extends AbstractEntity implements Serializable {
    private static final long serialVersionUID = 1L;
   

    @Column(name = "id_bandit")
    private Long idBandit = 0L;
 
    @Column(name = "id_child")
    private Long idChild = 0L;


    
   
  
    public Long getIdChild() {
        return idChild;
    }
    public void setIdChild(Long idChild) {
        this.idChild = idChild;
    }    
    public Long getIdBandit() {
        return idBandit;
    }
    public void setIdBandit(Long idBandit) {
        this.idBandit = idBandit;
    }        
}
