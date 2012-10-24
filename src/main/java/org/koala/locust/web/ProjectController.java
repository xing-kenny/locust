package org.koala.locust.web;

import javax.servlet.ServletRequest;
import javax.validation.Valid;

import org.koala.locust.domain.Project;
import org.koala.locust.service.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

/**
 * 
 * List   page    : GET  /project/list 
 * Create page    : GET  /project/create 
 * Create action  : POST /project/create 
 * Update page    : GET  /project/update/{id} 
 * Update action  : POST /project/update 
 * Delete action  : GET  /project/delete/{id}
 * Done   action  : GET  /project/done
 * 
 * @author Xing Kenny
 */
@Controller
@RequestMapping(value = "/project")
public class ProjectController {

    private static final int PAGE_SIZE = 3;

//    private static Logger logger = LoggerFactory.getLogger(ProjectController.class);

    @Autowired
    private ProjectService projectService;

    @RequestMapping(value = "list")
    public String list(@RequestParam(value = "page", defaultValue = "1") int pageNumber, Model model, 
                       ServletRequest request) {

        Page<Project> projects = projectService.getProjects(pageNumber, PAGE_SIZE);
        model.addAttribute("projects", projects);
        return "projectList";
    }

    @RequestMapping(value = "create", method = RequestMethod.GET)
    public String createForm(Model model) {
        
        model.addAttribute("project", new Project());
        model.addAttribute("action", "create");
        return "projectForm";
    }

    @RequestMapping(value = "create", method = RequestMethod.POST)
    public String create(@Valid Project newProject, RedirectAttributes redirectAttributes) {

        projectService.saveProject(newProject);
        redirectAttributes.addFlashAttribute("message", "创建成功");
        return "redirect:/project/list";
    }

    @RequestMapping(value = "update/{id}", method = RequestMethod.GET)
    public String updateForm(@PathVariable("id") Long id, Model model) {

        model.addAttribute("project", projectService.getProject(id));
        model.addAttribute("action", "update");
        return "projectForm";
    }

    @RequestMapping(value = "update", method = RequestMethod.POST)
    public String update(@Valid @ModelAttribute("preloadProject") Project project,
                         RedirectAttributes redirectAttributes) {
        
        projectService.saveProject(project);
        redirectAttributes.addFlashAttribute("message", "更新成功");
        return "redirect:/project/list";
    }

    
    @RequestMapping(value = "delete/{id}")
    public String delete(@PathVariable("id") Long id, RedirectAttributes redirectAttributes) {

        try{
            projectService.deleteProject(id);
            redirectAttributes.addFlashAttribute("message", "删除成功");
        }
        catch(DataIntegrityViolationException e){
            redirectAttributes.addFlashAttribute("message", "can't delete since the plan(s) existed!");
        }
        return "redirect:/project/list";
    }

    @RequestMapping(value = "done", method = RequestMethod.POST)
    public String done(@Valid @ModelAttribute("preloadProject") Project project,
                     RedirectAttributes redirectAttributes) {

        projectService.saveProject(project);
        redirectAttributes.addFlashAttribute("message", "done!");
        return "redirect:/project/list";

    }
    
    @ModelAttribute("preloadProject")
    public Project getProject(@RequestParam(value = "id", required = false) Long id) {
        if (id != null) {
            return projectService.getProject(id);
        }
        return null;
    }

//    @InitBinder  
//    public void initBinder(WebDataBinder binder) { 
//
//        logger.warn("this is ProjectController.initBinder");
//        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");  
//        dateFormat.setLenient(false);  
//        binder.registerCustomEditor(Date.class, new CustomDateEditor(  
//                dateFormat, false));  
//    }
}
