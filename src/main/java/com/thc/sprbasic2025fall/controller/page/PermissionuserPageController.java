package com.thc.sprbasic2025fall.controller.page;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/permissionuser")
@Controller
public class PermissionuserPageController {
    @GetMapping("/{page}")
    public String page(@PathVariable String page){
        return "permissionuser/" + page;
    }
    @GetMapping("/{page}/{id}")
    public String page2(@PathVariable String page, @PathVariable String id) {
        return "permissionuser/" + page;
    }
}
