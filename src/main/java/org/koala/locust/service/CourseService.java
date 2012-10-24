package org.koala.locust.service;

import java.util.List;

import org.koala.locust.domain.Course;
import org.koala.locust.domain.Plan;
import org.koala.locust.repository.CourseDao;
import org.koala.locust.repository.PlanDao;
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
public class CourseService {

    private CourseDao courseDao;
    private PlanDao planDao;

    public Course getCourse(Long id){
        return courseDao.findOne(id);
    }

    public List<Plan> getPlans(int status){
        SearchFilter filter = new SearchFilter("status", Operator.EQ, status);
        Sort sort = new Sort(Direction.ASC, "id");
        Specification<Plan> spec = DynamicSpecifications.bySearchFilter(Lists.newArrayList(filter),Plan.class);
        return planDao.findAll(spec, sort);
    }
    
    public List<Course> getCourses(Long planId){
        SearchFilter filter = new SearchFilter("plan.id", Operator.EQ, planId);
        Sort sort = new Sort(Direction.ASC, "id");
        Specification<Course> spec = DynamicSpecifications.bySearchFilter(Lists.newArrayList(filter),Course.class);
        return courseDao.findAll(spec, sort);
    }
    
    public Plan getPlan(Long planId)
    {
        return planDao.findOne(planId);
    }

    @Transactional(readOnly = false)
    public void saveCourse(Course entity) {
        courseDao.save(entity);
    }
    
    @Transactional(readOnly = false)
    public void deleteCourse(Long id) {
        courseDao.delete(id);
    }
    
    @Autowired
    public void setPlanDao(PlanDao planDao) {
        this.planDao = planDao;
    }

    @Autowired
    public void setCourseDao(CourseDao courseDao) {
        this.courseDao = courseDao;
    }
    
}
