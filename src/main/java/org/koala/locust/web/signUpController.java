package org.koala.locust.web;

import java.util.List;

import org.apache.shiro.SecurityUtils;
import org.koala.locust.domain.Course;
import org.koala.locust.service.SignUpService;
import org.koala.locust.service.account.ShiroDbRealm.ShiroUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 
 * List plans     : GET  /signup/courses
 * signup action  : POST /signup/signup/{id} 
 * signup action  : POST /cancel/cancel/{id} 
 * 
 * @author Xing Kenny
 *
 */
@Controller
@RequestMapping(value = "/signup")
public class signUpController {

    @Autowired
    private SignUpService signUpService;
    
    @RequestMapping(value = "courses")
    public String listCourses(Model model) {

        List<Course> courses = signUpService.getActiveCourses();
        model.addAttribute("courses", courses);
        return "signUpCourses";
    }

    @RequestMapping(value = "signup/{id}")
    public String signup(@PathVariable("id") Long courseId,Model model) {

        ShiroUser user = (ShiroUser) SecurityUtils.getSubject().getPrincipal();
        signUpService.signUp(courseId, user.id);
        return "redirect:/signup/courses" ;
    }

    @RequestMapping(value = "cancel/{id}")
    public String cancel(@PathVariable("id") Long id,Model model) {

        signUpService.delete(id);

        return "redirect:/signup/courses" ;
    }

    
}
