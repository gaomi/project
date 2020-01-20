package com.company.project.module.sys.service.impl;

import com.company.project.core.AbstractService;
import com.company.project.core.model.QueryRequest;
import com.company.project.core.model.Tree;
import com.company.project.core.util.TreeUtils;
import com.company.project.module.sys.dao.SysDictMapper;
import com.company.project.module.sys.model.SysDict;
import com.company.project.module.sys.service.SysDictService;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import tk.mybatis.mapper.entity.Example;
import tk.mybatis.mapper.entity.Example.Criteria;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;


/**
 * Created by chen on 2019/05/21.
 */
@Service
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true, rollbackFor = Exception.class)
public class SysDictServiceImpl extends AbstractService<SysDict> implements SysDictService {
    private Logger log = LoggerFactory.getLogger(this.getClass());
    @Resource
    private SysDictMapper sysDictMapper;

    @Override
    public List<SysDict> findAllDicts(SysDict dict, QueryRequest request) {
        try {
            Example example = new Example(SysDict.class);
            Criteria criteria = example.createCriteria();
            Map params = request.getParams();
            if (params.get("id") != null && StringUtils.isNotBlank(params.get("id").toString())) {
                criteria.andCondition("pid =", Long.valueOf(params.get("id").toString()));
            } else if (params.get("keyWord") != null && StringUtils.isNotBlank(params.get("keyWord").toString())) {
                String keyWord = params.get("keyWord").toString();
                /*if (StringUtils.isNotBlank(dict.getKey())) {
                    criteria.andCondition("key=", Long.valueOf(dict.getKey()));
                }
                if (StringUtils.isNotBlank(dict.getValue())) {
                    criteria.andCondition("value=", dict.getValue());
                }
                if (StringUtils.isNotBlank(dict.getTableName())) {
                    criteria.andCondition("table_name=", dict.getTableName());
                }
                if (StringUtils.isNotBlank(dict.getFieldName())) {
                    criteria.andCondition("field_name=", dict.getFieldName());
                }*/
                criteria.andCondition("FIELD_NAME like ", "%" + keyWord.toLowerCase() + "%")
                        .orCondition("TABLE_NAME like ", "%" + keyWord.toLowerCase() + "%")
                        .orCondition("VALUE like ", "%" + keyWord.toLowerCase() + "%")
                        .orCondition("NAME like ", "%" + keyWord.toLowerCase() + "%");
            }

            example.setOrderByClause("id");
            return this.findByExample(example);
        } catch (Exception e) {
            log.error("获取字典信息失败", e);
            return new ArrayList<>();
        }
    }

    @Override
    public Tree<SysDict> findTreeDicts(String flag) {
        Example example = new Example(SysDict.class);
        Criteria criteria = example.createCriteria();
        if (StringUtils.equals("top", flag)) {
            criteria.andCondition("pid = ", "0");
        } else {
            criteria.andCondition("pid != ", "0");
        }
        example.setOrderByClause("id");
        List<SysDict> dicts = this.findByExample(example);
        List<Tree<SysDict>> trees = new ArrayList<>();
        dicts.forEach(dict -> {
            Tree<SysDict> tree = new Tree<>();
            tree.setId(dict.getId());
            tree.setParentId(dict.getPid());
            tree.setText(dict.getName());
            trees.add(tree);
        });
        return TreeUtils.build(trees);
    }

    @Override
    public List<SysDict> findParentDicts(String flag) {
        Example example = new Example(SysDict.class);
        Criteria criteria = example.createCriteria();
        if (StringUtils.equals("top", flag)) {
            criteria.andCondition("pid = ", "0");
        } else {
            criteria.andCondition("pid != ", "0");
        }
        example.setOrderByClause("id");
        List<SysDict> dicts = this.findByExample(example);
        return dicts;
    }

}
