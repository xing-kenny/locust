package org.koala.locust.web;

import java.util.List;

import javax.validation.Valid;

import org.koala.locust.domain.Dept;
import org.koala.locust.service.DeptService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
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
 * List page      : GET  /dept/ 
 * Create page    : GET  /dept/create 
 * Create action  : POST /dept/create 
 * Update page    : GET  /dept/update/{id} 
 * Update action  : POST /dept/update 
 * Delete action  : GET  /dept/delete/{id}
 * 
 * @author Xing Kenny
 */
@Controller
@RequestMapping(value = "/dept")
public class DeptController {

    @SuppressWarnings("unused")
    private static Logger logger = LoggerFactory.getLogger(DeptController.class);

    @Autowired
    private DeptService deptService;

    @RequestMapping(value = "")
    public String list(Model model) {

        List<Dept> depts = deptService.getAllDept();
        model.addAttribute("depts", depts);
        return "deptList";
    }

    @RequestMapping(value = "create", method = RequestMethod.GET)
    public String createForm(Model model) {
        model.addAttribute("dept", new Dept());
        model.addAttribute("action", "create");
        return "deptForm";
    }

    @RequestMapping(value = "create", method = RequestMethod.POST)
    public String create(@Valid Dept dept, RedirectAttributes redirectAttributes,Model model) {

        return save(dept, "create",redirectAttributes, model);
    }

    private String save(Dept dept, String action,RedirectAttributes redirectAttributes, Model model) {
        try{
          deptService.saveDept(dept);
          redirectAttributes.addFlashAttribute("message", "operation completed!");
          return "redirect:/dept/";
        }
        catch(DataIntegrityViolationException e){
            model.addAttribute("dept", dept);
            model.addAttribute("action", action );
            model.addAttribute("message", "duplicate name for '" + dept.getName() + "'!");
            return "deptForm";
        }
    }

    @RequestMapping(value = "update/{id}", method = RequestMethod.GET)
    public String updateForm(@PathVariable("id") Long id, Model model) {

        model.addAttribute("dept", deptService.getDept(id));
        model.addAttribute("action", "update");
        return "deptForm";
    }

    @RequestMapping(value = "update", method = RequestMethod.POST)
    public String update(@Valid @ModelAttribute("preloadDept") Dept dept,
                         RedirectAttributes redirectAttributes, Model model) {
        
        return save(dept, "update",redirectAttributes, model);
    }

    
    @RequestMapping(value = "delete/{id}")
    public String delete(@PathVariable("id") Long id, RedirectAttributes redirectAttributes) {
        try{
            deptService.deleteDept(id);
            redirectAttributes.addFlashAttribute("message", "operation completed!");
        }
        catch(DataIntegrityViolationException e){
            redirectAttributes.addFlashAttribute("message", "user existed!");
        }
        return "redirect:/dept/";
    }
    
    @ModelAttribute("preloadDept")
    public Dept getDept(@RequestParam(value = "id", required = false) Long id) {
        if (id != null) {
            return deptService.getDept(id);
        }
        return null;
    }

}
