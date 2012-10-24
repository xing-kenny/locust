package org.koala.locust.web;

import java.util.List;

import javax.validation.Valid;

import org.koala.locust.domain.Plan;
import org.koala.locust.domain.Project;
import org.koala.locust.service.PlanService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

/**
 * 
 * List projects  : GET  /plan/projects
 * List page      : GET  /plan/list/{projectId} 
 * Create page    : GET  /plan/create{projectId} 
 * Create action  : POST /plan/create 
 * Update page    : GET  /plan/update/{id} 
 * Update action  : POST /plan/update 
 * Delete action  : GET  /plan/delete/{id}/projectId/{projectId}
 * Pub    action  : POST /plan/pub/{id}/projectId/{projectId}
 * @author Xing Kenny
 */
@Controller
@RequestMapping(value = "/plan")
public class PlanController {

    @SuppressWarnings("unused")
    private static Logger logger = LoggerFactory.getLogger(PlanController.class);

    @Autowired
    private PlanService planService;

    @RequestMapping(value = "projects")
    public String listProjects(Model model) {

        List<Project> projects = planService.getProjects();
        model.addAttribute("projects", projects);
        return "planProjects";
    }
    
    @RequestMapping(value = "list/{id}")
    public String list(@PathVariable("id") Long projectId,Model model){
        
        model.addAttribute("plans", planService.getPlans(projectId));
        model.addAttribute("project", planService.getProject(projectId));
        return "planList";
    }

    @RequestMapping(value = "create/{id}", method = RequestMethod.GET)
    public String createForm(@PathVariable("id") Long projectId,Model model) {

        Plan plan = new Plan();
        plan.setProject(planService.getProject(projectId));
        model.addAttribute("plan", plan);
        model.addAttribute("action", "create");
        return "planForm";
    }
    
    @RequestMapping(value = "create", method = RequestMethod.POST)
    public String create(@Valid Plan plan, RedirectAttributes redirectAttributes) {

        plan.setProject(planService.getProject(Long.valueOf(plan.getProjectId()))) ;
        planService.savePlan(plan);
        redirectAttributes.addFlashAttribute("message", "创建成功");
        return "redirect:/plan/list/" + plan.getProject().getId();
    }

    @RequestMapping(value = "update/{id}", method = RequestMethod.GET)
    public String updateForm(@PathVariable("id") Long id, Model model) {

        model.addAttribute("plan", planService.getPlan(id));
        model.addAttribute("action", "update");
        return "planForm";
    }

    @RequestMapping(value = "update", method = RequestMethod.POST)
    public String update(@Valid Plan plan,RedirectAttributes redirectAttributes) {
        
        planService.savePlan(plan);
        redirectAttributes.addFlashAttribute("message", "更新成功");
        return "redirect:/plan/list/" + plan.getProjectId();
    }
    
    @RequestMapping(value = "pub/{id}/projectId/{projectId}")
    public String pub(@PathVariable("id") Long id,
                      @PathVariable("projectId") Long projectId,
                      RedirectAttributes redirectAttributes) {
        planService.pubPlan(id);
        redirectAttributes.addFlashAttribute("message", "发布成功");
        return "redirect:/plan/list/" + projectId;
    }

    @RequestMapping(value = "delete/{id}/projectId/{projectId}")
    public String delete(@PathVariable("id") Long id,
                         @PathVariable("projectId") Long projectId,
                         RedirectAttributes redirectAttributes) {
        planService.deletePlan(id);
        redirectAttributes.addFlashAttribute("message", "删除成功");
        return "redirect:/plan/list/" + projectId;
    }
    
}
