package org.koala.locust.web;

import java.util.List;

import javax.validation.Valid;

import org.koala.locust.domain.Course;
import org.koala.locust.domain.Plan;
import org.koala.locust.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

/**
 * 
 * List plans     : GET  /course/plans
 * List page      : GET  /course/list/{id}
 * Create page    : GET  /course/create{planId} 
 * Create action  : POST /course/create 
 * Update page    : GET  /course/update/{id} 
 * Update action  : POST /course/update 
 * Delete action  : GET  /course/delete/{id}/planId/{planId}
 * 
 * @author Xing Kenny
 *
 */
@Controller
@RequestMapping(value = "/course")
public class CourseController {

    @Autowired
    private CourseService courseService;

    @RequestMapping(value = "plans")
    public String listPlans(Model model) {

        List<Plan> plans = courseService.getPlans(Plan.PLAN_STATUS_DRAFT);
        model.addAttribute("plans", plans);
        return "coursePlans";
    }
    
    @RequestMapping(value = "list/{id}")
    public String list(@PathVariable("id") Long planId,Model model) {

        model.addAttribute("courses", courseService.getCourses(planId));
        model.addAttribute("plan", courseService.getPlan(planId));
        return "courseList";
    }
    
    @RequestMapping(value = "create/{id}", method = RequestMethod.GET)
    public String createForm(@PathVariable("id") Long planId,Model model) {

        Course course = new Course();
        course.setPlan(courseService.getPlan(planId));
        
        model.addAttribute("course", course);
        model.addAttribute("action", "create");
        return "courseForm";
    }

    @RequestMapping(value = "create", method = RequestMethod.POST)
    public String create(@Valid Course course, RedirectAttributes redirectAttributes) {

        course.setPlan(courseService.getPlan(course.getPlanId()));
        courseService.saveCourse(course);
        redirectAttributes.addFlashAttribute("message", "创建成功");
        return "redirect:/course/list/" + course.getPlanId();
    }    

    @RequestMapping(value = "update/{id}", method = RequestMethod.GET)
    public String updateForm(@PathVariable("id") Long id, Model model) {

        model.addAttribute("course", courseService.getCourse(id));
        model.addAttribute("action", "update");
        return "courseForm";
    }
    
    @RequestMapping(value = "update", method = RequestMethod.POST)
    public String update(@Valid Course course, RedirectAttributes redirectAttributes) {
        
        courseService.saveCourse(course);
        redirectAttributes.addFlashAttribute("message", "更新成功");
        return "redirect:/course/list/" + course.getPlanId();
    }
    
    @RequestMapping(value = "delete/{id}/planId/{planId}")
    public String delete(@PathVariable("id") Long id,
                         @PathVariable("planId") Long planId,
                         RedirectAttributes redirectAttributes) {
        
        courseService.deleteCourse(id);
        redirectAttributes.addFlashAttribute("message", "删除成功");
        return "redirect:/course/list/" + planId;
    }

}
