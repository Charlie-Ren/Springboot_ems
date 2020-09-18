package com.baizhi.ems.dao;

import com.baizhi.ems.entity.Emp;

import java.util.List;

public interface EmpDAO {
    List<Emp> findAll();

    //保存员工对象
    void save(Emp emp);

    void delete(String id);

    Emp findById(String id);

    void update(Emp emp);
}
