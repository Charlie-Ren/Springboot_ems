package com.baizhi.ems.controller;

import com.baizhi.ems.entity.Emp;
import com.baizhi.ems.service.EmpService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("emp")
public class EmpController {
    //查询所有
    @Autowired
    private EmpService empService;

    @GetMapping("findAll")
    public String findAll(Model model){
        List<Emp> emps=empService.findAll();
        model.addAttribute("emps",emps);
        System.out.println("员工是"+emps);
        return "ems/emplist";
    }
    //保存员工
    @PostMapping("save")
    public String save(Emp emp)
    {
        empService.save(emp);
        return "redirect:/emp/findAll";
    }

    //删除员工
    @GetMapping("delete")
    public String delete(String id)
    {
        empService.delete(id);
        return "redirect:/emp/findAll";
    }

    @GetMapping("findOne")
    public String findOne(String id,Model model)
    {
        Emp emp=empService.findById(id);
        System.out.println("emp is"+emp);
        model.addAttribute("emp",emp);
        return "ems/updateEmp";

    }

    @PostMapping("update")
    public String update(Emp emp)
    {
        empService.update(emp);
        return "redirect:/emp/findAll";
    }
}
