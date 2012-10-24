package org.koala.locust.web;

import org.apache.shiro.SecurityUtils;
import org.koala.locust.service.account.ShiroDbRealm.ShiroUser;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 
 */
@Controller
@RequestMapping(value = "/main")
public class MainController {

    @RequestMapping(value = "")
    public String list(Model model){
        ShiroUser user = (ShiroUser) SecurityUtils.getSubject().getPrincipal();
        model.addAttribute("user", user);
        return "account/main";
    }
}
