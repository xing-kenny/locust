package org.koala.locust.service;

import java.util.Date;
import java.util.List;

import org.koala.locust.domain.CheckIn;
import org.koala.locust.domain.Course;
import org.koala.locust.domain.SignUp;
import org.koala.locust.repository.CheckInDao;
import org.koala.locust.repository.CourseDao;
import org.koala.locust.repository.SignUpDao;
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
public class CheckInService {

    private CheckInDao checkInDao;
    private CourseDao courseDao;
    private SignUpDao signUpDao;
    
    public List<Course> getCourses(int status){

        SearchFilter filter = new SearchFilter("status", Operator.EQ, status);
        Sort sort = new Sort(Direction.ASC, "id");
        Specification<Course> spec = DynamicSpecifications.bySearchFilter(Lists.newArrayList(filter),Course.class);
        return courseDao.findAll(spec, sort);

    }

    public List<SignUp> getSignUps(Long courseId)
    {
        SearchFilter filter = new SearchFilter("course.id", Operator.EQ, courseId);
        Sort sort = new Sort(Direction.ASC, "id");
        Specification<SignUp> spec = DynamicSpecifications.bySearchFilter(Lists.newArrayList(filter),SignUp.class);
        
        List<SignUp> signUps = signUpDao.findAll(spec,sort);
        return signUps;    
    }
    
    @Transactional(readOnly = false)
    public void checkIn(Long courseId,Long signUpId ) {

        SignUp signUp = signUpDao.findOne(signUpId);

        CheckIn checkIn = new CheckIn();
        checkIn.setCourseId(courseId);
        checkIn.setCheckinUserId(signUp.getSigner().getId());
        checkIn.setCheckinDay(new Date());
        checkIn = checkInDao.save(checkIn);
        
        signUp.setCheckin(checkIn);
        signUpDao.save(signUp);
    }
    
    
    @Autowired
    public void setCheckInDao(CheckInDao checkInDao) {
        this.checkInDao = checkInDao;
    }

    @Autowired
    public void setCourseDao(CourseDao courseDao) {
        this.courseDao = courseDao;
    }

    @Autowired
    public void setSignUpDao(SignUpDao signUpDao) {
        this.signUpDao = signUpDao;
    }
    
}
