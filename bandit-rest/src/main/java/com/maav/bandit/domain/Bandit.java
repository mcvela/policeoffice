package com.maav.bandit.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;

import org.joda.time.DateTime;
import org.hibernate.annotations.Type;

import javax.inject.Inject;
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
@Table(name = "bandit")
public class Bandit extends AbstractEntity implements Serializable {
    private static final long serialVersionUID = 1L;
   
    @Column(name = "first_name", length = 255)
    private String firstName;

    @Column(name = "not_available", nullable = false)
    private Boolean notAvailable = true;

    @Column(name = "content")
    private String content;

    
    @Column(name = "id_boss")
    private Long idBoss = 0L;

   
    @Column(name = "is_boss", nullable = false)
    private Boolean isBoss = false;

    @Transient
    private Long banditChildren = 0L;
 
    @Type(type = "org.jadira.usertype.dateandtime.joda.PersistentDateTime")
    @Column(name = "bandit_date")
    private DateTime banditDate =  DateTime.now();
    
    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public Boolean getNotAvailable() {
        return notAvailable;
    }
    public void setNotAvailable(Boolean notAvailable) {
        this.notAvailable = notAvailable;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Long getIdBoss() {
        return idBoss;
    }
    public void setIdBoss(Long idBoss) {
        this.idBoss = idBoss;
    }

    
    public Boolean getIsBoss() {
        return isBoss;
    }
    public void setIsBoss(Boolean isBoss) {
        this.isBoss = isBoss;
    }    
    public DateTime getBanditDate() {
        return banditDate;
    }
    public void setBanditDate(DateTime banditDate) {
        this.banditDate = banditDate;
    } 
    
    public void setBanditChildren(Long banditChildren ){
        this.banditChildren = banditChildren;
    }
    public Long getBanditChildren(){
        return banditChildren;
    }
}
