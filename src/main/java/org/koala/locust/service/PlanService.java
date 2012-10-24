package org.koala.locust.service;

import java.util.List;

import org.koala.locust.domain.Course;
import org.koala.locust.domain.Plan;
import org.koala.locust.domain.Project;
import org.koala.locust.repository.CourseDao;
import org.koala.locust.repository.PlanDao;
import org.koala.locust.repository.ProjectDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springside.modules.persistence.DynamicSpecifications;
import org.springside.modules.persistence.SearchFilter;
import org.springside.modules.persistence.SearchFilter.Operator;

import com.google.common.collect.Lists;

@Component
@Transactional(readOnly = true)
public class PlanService {

    private PlanDao planDao;
    private ProjectDao projectDao;
    private CourseDao courseDao;

    public List<Project> getProjects() {
        SearchFilter filter = new SearchFilter("status", Operator.EQ, Project.PROJECT_STATUS_DOING);
        Sort sort = new Sort(Direction.ASC, "id");
        Specification<Project> spec = DynamicSpecifications.bySearchFilter(Lists.newArrayList(filter),Project.class);
        return projectDao.findAll(spec,sort);
    }

    public Project getProject(Long id){
        return projectDao.findOne(id);
    }
    
    public Plan getPlan(Long id){
        return planDao.findOne(id);
    }

    public List<Plan> getPlans(Long projectId){
        
        SearchFilter filter = new SearchFilter("project.id", Operator.EQ, projectId);
        Sort sort = new Sort(Direction.ASC, "id");
        Specification<Plan> spec = DynamicSpecifications.bySearchFilter(Lists.newArrayList(filter),Plan.class);
        return planDao.findAll(spec,sort);
    }
    
    @Transactional(readOnly = false)
    public void savePlan(Plan entity) {
        planDao.save(entity);
    }
    
    @Transactional(readOnly = false)
    public void deletePlan(Long id) {
        planDao.delete(id);
    }
    
    @Transactional(readOnly = false)
    public void pubPlan(Long id) {
        Plan plan = planDao.findOne(id);
        plan.setStatus(Plan.PLAN_STATUS_DOING);
        for(Course course : plan.getCourses())
        {
            course.setStatus(Plan.PLAN_STATUS_DOING);
            courseDao.save(course);
        }
        planDao.save(plan);
    }
    
    @Autowired
    public void setPlanDao(PlanDao planDao) {
        this.planDao = planDao;
    }

    @Autowired
    public void setProjectDao(ProjectDao projectDao) {
        this.projectDao = projectDao;
    }

    @Autowired
    public void setCourseDao(CourseDao courseDao) {
        this.courseDao = courseDao;
    }
    
    
}
