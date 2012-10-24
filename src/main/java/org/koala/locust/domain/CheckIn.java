package org.koala.locust.domain;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Table;

import org.koala.locust.entity.IdEntity;

@Entity
@Table(name = "t_checkin")
public class CheckIn extends IdEntity {

    private Long courseId;

    private Long checkinUserId;

    private Date checkinDay;


    public Long getCourseId() {
        return courseId;
    }

    public void setCourseId(Long courseId) {
        this.courseId = courseId;
    }

    public Long getCheckinUserId() {
        return checkinUserId;
    }

    public void setCheckinUserId(Long checkinUserId) {
        this.checkinUserId = checkinUserId;
    }

    public Date getCheckinDay() {
        return checkinDay;
    }

    public void setCheckinDay(Date checkinDay) {
        this.checkinDay = checkinDay;
    }

    
}
