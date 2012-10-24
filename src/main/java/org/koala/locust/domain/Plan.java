package org.koala.locust.domain;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.koala.locust.entity.IdEntity;

/**
 * 
 * @author Xing Kenny
 *
 */
@Entity
@Table(name = "t_plan")
public class Plan extends IdEntity {

    public static final int PLAN_STATUS_DRAFT = 0;
    public static final int PLAN_STATUS_DOING = 1;
    public static final int PLAN_STATUS_DONE = 2;

    private static String statusLabels[] = {"draft","doing","done"}; 

    private String name;
    private Project project;
    private int status;
    private List<Course> courses;

    //project.id
    private int projectId;

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

    @Transient
    public int getProjectId() {
        return projectId;
    }

    public void setProjectId(int projectId) {
        this.projectId = projectId;
    }

    @ManyToOne
    @JoinColumn(name="project_Id", nullable=false, updatable=false)
    public Project getProject() {
        return project;
    }

    public void setProject(Project project) {
        this.project = project;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    @OneToMany(targetEntity = Course.class, mappedBy="plan" )
    public List<Course> getCourses() {
        return courses;
    }

    public void setCourses(List<Course> courses) {
        this.courses = courses;
    }

    
}
