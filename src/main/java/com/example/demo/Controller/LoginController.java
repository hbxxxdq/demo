package com.example.demo.Controller;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.thymeleaf.util.StringUtils;

import javax.servlet.http.HttpSession;

@Controller
public class LoginController {
    @RequestMapping("/user/login")
    public String login(
            @RequestParam("username") String username,
            @RequestParam("password") String password,
            Model model,
            HttpSession session) {

        //具体的业务：
        if (!StringUtils.isEmpty(username) && "123456".equals(password)) {
            //重定向到main界面
            session.setAttribute("loginUser", username);
            return "redirect:/main.html";
        } else {
            //告诉用户你登录失败了!
            model.addAttribute("msg","用户名或者密码错误!");
            return "index";
        }

    }
}
