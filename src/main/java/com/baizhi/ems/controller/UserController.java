package com.baizhi.ems.controller;


import com.baizhi.ems.entity.User;
import com.baizhi.ems.service.UserService;
import com.baizhi.ems.utils.ValidateImageCodeUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.awt.image.BufferedImage;
import java.io.IOException;

@Controller
@RequestMapping("user")
public class UserController {

    @Autowired
    private UserService userService;


    @RequestMapping("login")
    public String login(String username, String password, HttpSession session)
    {
        User user=userService.login(username,password);
        System.out.println(username);
        System.out.println(password);
        System.out.println(user);
        //为空则错误
        if(user!=null)
        {
            System.out.println("success");
            session.setAttribute("user",user);
            //return "redirect:/emp/findAll";//否则要重新提交表单。
            return "redirect:/emp/findAll";
        }
        else {
            System.out.println("failed");
            return "redirect:/ems/login.jsp";
        }
    }
    //开发用户注册
    @PostMapping("register")
    public String register(User user,String code, HttpSession session){
        System.out.println("user="+user);
        //1.判断验证码.
        System.out.println(session.getAttribute("code"));
        System.out.println(code);
        if(session.getAttribute("code").toString().equalsIgnoreCase(code))
        {
            System.out.println("nice");
            userService.save(user);//保存用户
            return "redirect:/ems/login.jsp";
        }
        else{//验证失败回到注册界面
            System.out.println("fail");
            return "redirect:/ems/regist.jsp"; //
        }

    }
    //生成验证码
    @GetMapping("getImage")
    public void getImage(HttpSession session, HttpServletResponse response) throws IOException {
        String securityCOde = ValidateImageCodeUtils.getSecurityCode();
        session.setAttribute("code",securityCOde);//session保存验证码
        //生成图片
        BufferedImage image=ValidateImageCodeUtils.createImage(securityCOde);
        ServletOutputStream os=response.getOutputStream();
        //输出图片
        ImageIO.write(image,"png",os);
    }
}
