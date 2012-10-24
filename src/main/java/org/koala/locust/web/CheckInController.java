package org.koala.locust.web;

import java.util.List;

import org.koala.locust.domain.Course;
import org.koala.locust.domain.Plan;
import org.koala.locust.domain.SignUp;
import org.koala.locust.service.CheckInService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 
 * List courses   : GET  /checkIn/course
 * List page      : GET  /checkIn/list/{id}
 * checkIn action : POST /checkIn/checkIn/{id}/signUpId/{signUpId} 
 * 
 * @author Xing Kenny
 *
 */
@Controller
@RequestMapping(value = "/checkIn")
public class CheckInController {

    @Autowired
    private CheckInService checkInService;
    
    @RequestMapping(value = "courses")
    public String listCourses(Model model) {

        List<Course> courses = checkInService.getCourses(Plan.PLAN_STATUS_DOING);
        model.addAttribute("courses", courses);
        return "checkInCourses";
    }

    @RequestMapping(value = "list/{id}")
    public String list(@PathVariable("id") Long courseId,Model model) {
    
        List<SignUp> signUps = checkInService.getSignUps(courseId) ;
        model.addAttribute("signUps", signUps);
        return "checkInList";
    }    
    
    @RequestMapping(value = "checkIn/{id}/signUpId/{signUpId}")
    public String checkIn(@PathVariable("id") Long courseId,@PathVariable("signUpId") Long signUpId,Model model) {

        checkInService.checkIn(courseId, signUpId);
        return "redirect:/checkIn/list/" + courseId ;
    }

    
}
