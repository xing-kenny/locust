package org.koala.locust.domain;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.koala.locust.entity.IdEntity;
import org.koala.locust.entity.User;

@Entity
@Table(name = "t_signup")
public class SignUp extends IdEntity {


    private Course course;
    private User signer;
    private CheckIn checkin;
    private Date signupDay;
    
    //course.id
    private Long courseId;
    //signer.id
    private Long signerId;
    //checkin.id
    private Long checkinId;

    @ManyToOne
    @JoinColumn(name="course_id", nullable=false, updatable=false)
    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }

    @ManyToOne
    @JoinColumn(name="signer_id", nullable=false, updatable=false)
    public User getSigner() {
        return signer;
    }

    public void setSigner(User signer) {
        this.signer = signer;
    }

    public Date getSignupDay() {
        return signupDay;
    }

    public void setSignupDay(Date signupDay) {
        this.signupDay = signupDay;
    }

    @OneToOne
    @JoinColumn(name="checkin_id", nullable=true, updatable=true)
    public CheckIn getCheckin() {
        return checkin;
    }

    public void setCheckin(CheckIn checkin) {
        this.checkin = checkin;
    }

    @Transient
    public Long getCheckinId() {
        return checkinId;
    }

    public void setCheckinId(Long checkinId) {
        this.checkinId = checkinId;
    }

    @Transient
    public Long getCourseId() {
        return courseId;
    }

    public void setCourseId(Long courseId) {
        this.courseId = courseId;
    }

    @Transient
    public Long getSignerId() {
        return signerId;
    }

    public void setSignerId(Long signerId) {
        this.signerId = signerId;
    }

}
