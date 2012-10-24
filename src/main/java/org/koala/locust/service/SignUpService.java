package org.koala.locust.service;

import java.util.Date;
import java.util.List;

import org.koala.locust.domain.Course;
import org.koala.locust.domain.Plan;
import org.koala.locust.domain.SignUp;
import org.koala.locust.repository.CourseDao;
import org.koala.locust.repository.SignUpDao;
import org.koala.locust.repository.UserDao;
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
public class SignUpService {

    private SignUpDao signUpDao;
    private CourseDao courseDao;
    private UserDao userDao;
 
    public List<Course> getActiveCourses(){

        SearchFilter filter1 = new SearchFilter("status", Operator.EQ, Plan.PLAN_STATUS_DOING);
        SearchFilter filter2 = new SearchFilter("startDate", Operator.GT, new Date());
        
        Sort sort = new Sort(Direction.ASC, "id");
        Specification<Course> spec = DynamicSpecifications.bySearchFilter(Lists.newArrayList(filter1,filter2),Course.class);
        List<Course> courses = courseDao.findAll(spec, sort);

        for(Course course : courses){
            SearchFilter f1 = new SearchFilter("course.id", Operator.EQ, course.getId());
            Specification<SignUp> s = DynamicSpecifications.bySearchFilter(Lists.newArrayList(f1),SignUp.class);
            SignUp signUp = signUpDao.findOne(s);
            course.setSignUp(signUp);
        }
        return courses;
        
    }
    
    public List<Course> getCourses(int status){
        
        SearchFilter filter = new SearchFilter("status", Operator.EQ, status);
        Sort sort = new Sort(Direction.ASC, "id");
        Specification<Course> spec = DynamicSpecifications.bySearchFilter(Lists.newArrayList(filter),Course.class);
        List<Course> courses = courseDao.findAll(spec, sort);

        for(Course course : courses){
            SearchFilter f = new SearchFilter("course.id", Operator.EQ, course.getId());
            Specification<SignUp> s = DynamicSpecifications.bySearchFilter(Lists.newArrayList(f),SignUp.class);
            SignUp signUp = signUpDao.findOne(s);
            course.setSignUp(signUp);
        }
        return courses;
    }
    
    @Transactional(readOnly = false)
    public void signUp(Long courseId,Long userId)
    {
        SignUp signUp = new SignUp();
        signUp.setCourse(courseDao.findOne(courseId));
        signUp.setSignupDay(new Date());
        signUp.setSigner(userDao.findOne(userId) );
        signUpDao.save(signUp);
    }
    
    @Transactional(readOnly = false)
    public void delete(Long id) {
        signUpDao.delete(id);
    }
    
    @Autowired
    public void setSignUpDao(SignUpDao signUpDao) {
        this.signUpDao = signUpDao;
    }

    @Autowired
    public void setCourseDao(CourseDao courseDao) {
        this.courseDao = courseDao;
    }

    @Autowired
    public void setUserDao(UserDao userDao) {
        this.userDao = userDao;
    }

    
}
