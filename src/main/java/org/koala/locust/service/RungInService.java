package org.koala.locust.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.koala.locust.domain.CheckIn;
import org.koala.locust.domain.Course;
import org.koala.locust.domain.RungIn;
import org.koala.locust.entity.User;
import org.koala.locust.repository.CheckInDao;
import org.koala.locust.repository.CourseDao;
import org.koala.locust.repository.RungInDao;
import org.koala.locust.repository.UserDao;
import org.koala.locust.util.RungInLabel;
import org.koala.locust.util.tag.RungInBlock;
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
public class RungInService {

    private UserDao userDao;
    private CheckInDao checkInDao;
    private CourseDao courseDao;
    private RungInDao rungInDao;
 
    public List<Course> getCourses(int status){
        
        SearchFilter filter = new SearchFilter("status", Operator.EQ, status);
        Sort sort = new Sort(Direction.ASC, "id");
        Specification<Course> spec = DynamicSpecifications.bySearchFilter(Lists.newArrayList(filter),Course.class);
        List<Course> courses = courseDao.findAll(spec, sort);
        return courses;
    }

    public List<CheckIn> getCheckIns(Long courseId){

        SearchFilter filter = new SearchFilter("courseId", Operator.EQ, courseId);
        Sort sort = new Sort(Direction.ASC, "id");
        Specification<CheckIn> spec = DynamicSpecifications.bySearchFilter(Lists.newArrayList(filter),CheckIn.class);
        return checkInDao.findAll(spec, sort);
        
    }

    public List<RungInBlock> getRungInBlock(Long courseId,List<RungInLabel> labels){

        List<CheckIn> checkIns = getCheckIns(courseId);
        List<RungInBlock> blocks = new ArrayList<RungInBlock>();
        for (CheckIn c : checkIns) {
            RungInBlock block = new RungInBlock();
            User user = userDao.findOne(c.getCheckinUserId()) ;
            block.setUser(user);

            for (RungInLabel label : labels) {
                
              SearchFilter f1 = new SearchFilter("runginUserId", Operator.EQ, c.getCheckinUserId());
              SearchFilter f2 = new SearchFilter("forenoon", Operator.EQ, label.getForenoon());
              SearchFilter f3 = new SearchFilter("runginDay", Operator.EQ, label.getRungInDay());
              SearchFilter f4 = new SearchFilter("courseId", Operator.EQ, courseId);
              Specification<RungIn> spec = DynamicSpecifications.bySearchFilter(Lists.newArrayList(f1,f2,f3,f4),RungIn.class);
              RungIn rungIn = rungInDao.findOne(spec);     
                
                if (null == rungIn) {
                    rungIn = new RungIn();
                    rungIn.setId(0L);
                    rungIn.setCourseId(courseId);
                    rungIn.setForenoon(label.getForenoon());
                    rungIn.setRunginDay(label.getRungInDay());
                    rungIn.setRunginUserId(c.getCheckinUserId());
                    block.addRungIn(rungIn);
                } else {
                    block.addRungIn(rungIn);
                }
            }
            blocks.add(block);
        }
        return blocks;
    }
    
    public void rungIn(Long courseId, Date rungInDay, int forenoon,Long runginUserId) {

        RungIn rungIn = new RungIn();
        rungIn.setCourseId(courseId);
        rungIn.setForenoon(forenoon);
        rungIn.setRunginDay(rungInDay);
        rungIn.setRunginUserId(runginUserId);
        
        Course course = courseDao.findOne(courseId);
        rungIn.setPlanId(course.getPlan().getId());
        rungIn.setProjectId(course.getPlan().getProject().getId());
        rungInDao.save(rungIn);
    }
    
    
    @Autowired
    public void setCheckInDao(CheckInDao checkInDao) {
        this.checkInDao = checkInDao;
    }

    @Autowired
    public void setUserDao(UserDao userDao) {
        this.userDao = userDao;
    }

    @Autowired
    public void setCourseDao(CourseDao courseDao) {
        this.courseDao = courseDao;
    }

    @Autowired
    public void setRungInDao(RungInDao rungInDao) {
        this.rungInDao = rungInDao;
    }
    
}
