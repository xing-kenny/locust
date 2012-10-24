package org.koala.locust.domain;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import org.koala.locust.entity.IdEntity;

@Entity
@Table(name = "t_Rungin")
public class RungIn extends IdEntity {

    @Column
    private Long courseId;

    @Column
    private Date runginDay;

    @Column
    private int forenoon;

    @Column
    private Long runginUserId;

    @Column
    private Long planId;

    @Column
    private Long projectId;

    public Long getCourseId() {
        return courseId;
    }

    public void setCourseId(Long courseId) {
        this.courseId = courseId;
    }

    public Date getRunginDay() {
        return runginDay;
    }

    public void setRunginDay(Date runginDay) {
        this.runginDay = runginDay;
    }

    public int getForenoon() {
        return forenoon;
    }

    public void setForenoon(int forenoon) {
        this.forenoon = forenoon;
    }

    

    public Long getRunginUserId() {
        return runginUserId;
    }

    public void setRunginUserId(Long runginUserId) {
        this.runginUserId = runginUserId;
    }

    public Long getPlanId() {
        return planId;
    }

    public void setPlanId(Long planId) {
        this.planId = planId;
    }

    public Long getProjectId() {
        return projectId;
    }

    public void setProjectId(Long projectId) {
        this.projectId = projectId;
    }


//    @One(target = Account.class, field = "rungInAccountId")
//    private Account rungInAccount;

    
}
