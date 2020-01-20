package com.company.project.module.sys.service;

import com.company.project.core.Service;
import com.company.project.core.model.Tree;
import com.company.project.module.sys.model.SysDept;

import java.util.List;


/**
 * Created by chen on 2019/05/21.
 */
public interface SysDeptService extends Service<SysDept> {

    List<SysDept> findAllDepts(SysDept dept);

    SysDept findByName(String deptName);

    void addDept(SysDept dept);

    void deleteDepts(String ids);

    List<Tree<SysDept>> getDeptTree();
}
