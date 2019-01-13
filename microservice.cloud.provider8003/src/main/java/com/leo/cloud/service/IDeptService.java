package com.leo.cloud.service;

import com.leo.cloud.entity.Dept;

import java.util.List;

public interface IDeptService {
    public boolean addDept(Dept dept);

    public Dept findById(Long id);

    public List<Dept> findAll();
}
