package org.koala.locust.domain;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.koala.locust.entity.IdEntity;

@Entity
@Table(name = "t_project")
public class Project extends IdEntity {

    public static final int PROJECT_STATUS_DRAFT = 0;
    public static final int PROJECT_STATUS_DOING = 1;
    public static final int PROJECT_STATUS_DONE = 2;

    private static String statusLabels[] = {"draft","doing","done"}; 
    
    private String name;
    private String description;
    private Date startDate;
    private Date endDate;
    private int status = PROJECT_STATUS_DRAFT;
    private int mandays;


    @Transient
    private float actualMandays = 0.0f;

    @Transient
    public String getStatusLabel(){
        return statusLabels[status];
    }
    
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public int getMandays() {
        return mandays;
    }

    public void setMandays(int mandays) {
        this.mandays = mandays;
    }

    @Transient
    public float getActualMandays() {
        return actualMandays;
    }

    public void setActualMandays(float actualMandays) {
        this.actualMandays = actualMandays;
    }

    
    
}
