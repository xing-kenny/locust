package org.koala.locust.web;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.koala.locust.domain.Course;
import org.koala.locust.domain.Plan;
import org.koala.locust.service.CourseService;
import org.koala.locust.service.RungInService;
import org.koala.locust.util.RungInLabel;
import org.koala.locust.util.tag.RungInBlock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 
 * List courses   : GET  /rungIn/course
 * List checkIns  : GET  /rungIn/list/{id}
 * rungIn action  : GET  /rungIn/rungIn/{courseId}/rungInDay/{rungInDay}/forenoon/{forenoon}/runginUserId/{runginUserId} 
 * 
 * @author Xing Kenny
 *
 */
@Controller
@RequestMapping(value = "/rungIn")
public class RungInController {

    @Autowired
    private CourseService courseService;
    @Autowired
    private RungInService rungInService;
    
    @RequestMapping(value = "courses")
    public String listCourses(Model model) {

        List<Course> courses = rungInService.getCourses(Plan.PLAN_STATUS_DOING);
        model.addAttribute("courses", courses);
        return "rungInCourses";
    }

    @RequestMapping(value = "list/{id}")
    public String list(@PathVariable("id") Long courseId,Model model) {
    
        Course course = courseService.getCourse(courseId);
        List<RungInLabel> labels = initRungInLabels(course);
        List<RungInBlock> blocks = rungInService.getRungInBlock(courseId,labels);
        
        model.addAttribute("course", course);
        model.addAttribute("labels", labels);
        model.addAttribute("blocks", blocks);
        return "rungInList";

    }    

    @RequestMapping(value = "rungIn/{courseId}/rungInDay/{rungInDay}/forenoon/{forenoon}/runginUserId/{runginUserId}")
    public String rungIn(@PathVariable("courseId") Long courseId,
                      @PathVariable("rungInDay") Date rungInDay,
                      @PathVariable("forenoon") int forenoon,
                      @PathVariable("runginUserId") Long runginUserId
                      ) {

        rungInService.rungIn(courseId, rungInDay, forenoon, runginUserId);
        return "redirect:/rungIn/list/" + courseId;
        
    }
    
    private List<RungInLabel> initRungInLabels(Course course) {

        List<RungInLabel> labels = new ArrayList<RungInLabel>();

        Calendar cStart = Calendar.getInstance();
        Calendar cEnd = Calendar.getInstance();
        cStart.setTime(course.getStartDate());
        cEnd.setTime(course.getEndDate());
        int loop = 0;
        while (!cStart.after(cEnd) && loop < 10) {
            loop++;
            RungInLabel r = new RungInLabel();
            r.setRungInDay(cStart.getTime());
            r.setForenoon(1);
            labels.add(r);
            r = new RungInLabel();
            r.setRungInDay(cStart.getTime());
            r.setForenoon(0);
            labels.add(r);
            cStart.add(Calendar.DAY_OF_MONTH, 1);
        }

        return labels;
    }

}
