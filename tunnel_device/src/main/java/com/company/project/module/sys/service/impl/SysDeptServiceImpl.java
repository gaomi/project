package com.company.project.module.sys.service.impl;

import com.company.project.core.AbstractService;
import com.company.project.core.model.Tree;
import com.company.project.core.util.PaodingUtils;
import com.company.project.core.util.TreeUtils;
import com.company.project.module.sys.dao.SysDeptMapper;
import com.company.project.module.sys.model.SysDept;
import com.company.project.module.sys.service.SysDeptService;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import tk.mybatis.mapper.entity.Example;

import javax.annotation.Resource;
import java.util.*;


/**
 * Created by chen on 2019/05/21.
 */
@Service
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true, rollbackFor = Exception.class)
public class SysDeptServiceImpl extends AbstractService<SysDept> implements SysDeptService {
    private Logger log = LoggerFactory.getLogger(this.getClass());

    @Resource
    private SysDeptMapper sysDeptMapper;

    @Override
    public List<Tree<SysDept>> getDeptTree() {
        List<Tree<SysDept>> trees = new ArrayList<>();
        List<SysDept> depts = this.findAllDepts(new SysDept());
        depts.forEach(dept -> {
            Tree<SysDept> tree = new Tree<>();
            tree.setId(dept.getId());
            tree.setParentId(dept.getParentId());
            tree.setText(dept.getName());
            tree.setAttributes((Map<String, Object>) PaodingUtils.objectToMap(dept));
            trees.add(tree);
        });
        return TreeUtils.buildList(trees,"0");
    }


    @Override
    public List<SysDept> findAllDepts(SysDept dept) {
        try {
            Example example = new Example(SysDept.class);
            if (StringUtils.isNotBlank(dept.getName())) {
                example.createCriteria().andCondition("name=", dept.getName());
            }
            example.setOrderByClause("id");
            return this.findByExample(example);
        } catch (Exception e) {
            log.error("获取部门列表失败", e);
            return new ArrayList<>();
        }

    }

    @Override
    public SysDept findByName(String deptName) {
        Example example = new Example(SysDept.class);
        example.createCriteria().andCondition("lower(name) =", deptName.toLowerCase());
        List<SysDept> list = this.findByExample(example);
        return list.isEmpty() ? null : list.get(0);
    }


    @Override
    @Transactional
    public void addDept(SysDept dept) {
        String parentId = dept.getParentId();
        if (parentId == null) {
            dept.setParentId(0 + "");
        }
        dept.setOperateTime(new Date());
        this.save(dept);
    }

    @Override
    @Transactional
    public void deleteDepts(String deptIds) {
        this.batchDelete(deptIds, "id", SysDept.class);

        List<String> list = Arrays.asList(deptIds.split(","));
        this.sysDeptMapper.changeToTop(list);
    }

}
