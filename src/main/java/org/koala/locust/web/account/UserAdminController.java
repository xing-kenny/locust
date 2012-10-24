package org.koala.locust.web.account;

import java.util.List;

import javax.validation.Valid;

import org.apache.shiro.SecurityUtils;
import org.koala.locust.entity.User;
import org.koala.locust.service.account.AccountService;
import org.koala.locust.service.account.ShiroDbRealm.ShiroUser;
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
 * List   page    : GET  /admin/user/list 
 * create page    : GET  /admin/user/create
 * create action  : POST /admin/user/create
 * update page    : GET  /admin/user/update/{id}
 * update action  : POST /admin/user/update
 * delete action  : GET  /admin/user/delete/{id}
 * profile page   : GET  /admin/profile
 * profile action : POST /admin/profile
 * 
 * @author calvin
 */
@Controller
@RequestMapping(value = "/admin")
public class UserAdminController {

	@Autowired
	private AccountService accountService;

	@RequestMapping(value = "user/list")
	public String list(Model model) {
		List<User> users = accountService.getAllUser();
		model.addAttribute("users", users);
		return "account/adminUserList";
	}

    @RequestMapping(value = "user/create", method = RequestMethod.GET)
    public String createForm(Model model) {
        model.addAttribute("user", new User());
        model.addAttribute("depts",accountService.getDepts());
        model.addAttribute("action", "create");
        return "account/adminUserForm";
    }
    
    @RequestMapping(value = "user/create", method = RequestMethod.POST)
    public String create(@Valid User user, RedirectAttributes redirectAttributes, Model model) {

        try{
            accountService.registerUser(user);
            redirectAttributes.addFlashAttribute("message", "创建成功");
            return "redirect:/admin/user/list";
        }
        catch(DataIntegrityViolationException e){
            model.addAttribute("user", user);
            model.addAttribute("depts",accountService.getDepts());
            model.addAttribute("action", "create");
            model.addAttribute("message", "duplicate for '" + user.getLoginName() + "'!");
            return "account/adminUserForm";
        }
    }
    
	
	@RequestMapping(value = "user/update/{id}", method = RequestMethod.GET)
	public String updateForm(@PathVariable("id") Long id, Model model) {
		model.addAttribute("user", accountService.getUser(id));
        model.addAttribute("depts",accountService.getDepts());
        model.addAttribute("action", "update");
		return "account/adminUserForm";
	}

	@RequestMapping(value = "user/update", method = RequestMethod.POST)
	public String update(@Valid @ModelAttribute("preloadUser") User user, RedirectAttributes redirectAttributes, Model model) {
        try{
    		accountService.updateUser(user);
    		redirectAttributes.addFlashAttribute("message", "更新用户" + user.getLoginName() + "成功");
            return "redirect:/admin/user/list";
        }
        catch(DataIntegrityViolationException e){
            model.addAttribute("user", user);
            model.addAttribute("depts",accountService.getDepts());
            model.addAttribute("action", "update");
            model.addAttribute("message", "duplicate for '" + user.getLoginName() + "'!");
            return "account/adminUserForm";
        }
	}

	@RequestMapping(value = "user/delete/{id}")
	public String delete(@PathVariable("id") Long id, RedirectAttributes redirectAttributes) {
		User user = accountService.getUser(id);
		accountService.deleteUser(id);
		redirectAttributes.addFlashAttribute("message", "删除用户" + user.getLoginName() + "成功");
        return "redirect:/admin/user/list";
	}

   @RequestMapping(value = "profile",method = RequestMethod.GET)
    public String profileForm(Model model) {

       ShiroUser user = (ShiroUser) SecurityUtils.getSubject().getPrincipal();
       
       model.addAttribute("user", accountService.getUser(user.id));
       model.addAttribute("depts",accountService.getDepts());
       model.addAttribute("action", "profile");
       return "account/profile";
    }

   @RequestMapping(value = "profile",method = RequestMethod.POST)
   public String profile(@Valid @ModelAttribute("preloadUser") User user,RedirectAttributes redirectAttributes) {
       accountService.updateUser(user);
       updateCurrentUserName(user.getName());
       redirectAttributes.addFlashAttribute("message", "更新成功");
       return "redirect:/";
   }
   
   /**
    * 更新Shiro中当前用户的用户名.
    */
   private void updateCurrentUserName(String userName) {
       ShiroUser user = (ShiroUser) SecurityUtils.getSubject().getPrincipal();
       user.name = userName;
   }
   
	/**
	 * 使用@ModelAttribute, 实现Struts2 Preparable二次部分绑定的效果,先根据form的id从数据库查出User对象,再把Form提交的内容绑定到该对象上。
	 * 因为仅update()方法的form中有id属性，因此本方法在该方法中执行.
	 */
	@ModelAttribute("preloadUser")
	public User getUser(@RequestParam(value = "id", required = false) Long id) {
		if (id != null) {
			return accountService.getUser(id);
		}
		return null;
	}
}
