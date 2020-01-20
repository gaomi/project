package com.company.project.module.sys.dao;

import com.company.project.core.Mapper;
import com.company.project.module.sys.model.SysDept;

import java.util.List;

public interface SysDeptMapper extends Mapper<SysDept> {
    // 删除父节点，子节点变成顶级节点（根据实际业务调整）
    void changeToTop(List<String> deptIds);
}