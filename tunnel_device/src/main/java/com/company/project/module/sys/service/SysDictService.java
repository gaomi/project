package com.company.project.module.sys.service;

import com.company.project.core.Service;
import com.company.project.core.model.QueryRequest;
import com.company.project.core.model.Tree;
import com.company.project.module.sys.model.SysDict;

import java.util.List;


/**
 * Created by chen on 2019/05/21.
 */
public interface SysDictService extends Service<SysDict> {
    List<SysDict> findAllDicts(SysDict sysDict, QueryRequest request);

    /**
     * 字典第一二级
     */
    Tree findTreeDicts(String flag);

    /**
     * 字典第一级
     * @param flag
     * @return
     */
    List<SysDict> findParentDicts(String flag);
}
