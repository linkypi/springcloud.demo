package com.leo.cloud.entity;

import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;

//@SuppressWarnings("serial")
@Data
@Accessors(chain = true)
public class Dept implements Serializable {
    private Long deptno;
    private String dname;
    private String db_source;

    public Dept(){

    }
    public Dept(String dname) {
        super();
        this.dname = dname;
    }

    public static void main(String[] args){
        Dept dept = new Dept();
        dept.setDb_source("db1").setDname("大大").setDeptno(1L);
    }
}
