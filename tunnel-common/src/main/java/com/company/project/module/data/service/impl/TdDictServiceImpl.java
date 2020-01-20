package com.company.project.module.data.service.impl;

import com.company.project.core.AbstractService;
import com.company.project.core.model.Tree;
import com.company.project.core.util.TreeUtils;
import com.company.project.module.data.dao.TdDictMapper;
import com.company.project.module.data.model.TdDict;
import com.company.project.module.data.service.TdDictService;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import tk.mybatis.mapper.entity.Example;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;


/**
 * Created by paodingsoft.chen on 2019/08/15.
 */
@Service
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true, rollbackFor = Exception.class)
public class TdDictServiceImpl extends AbstractService<TdDict> implements TdDictService {
    private Logger logger = LoggerFactory.getLogger(this.getClass());
    @Resource
    private TdDictMapper tdDictMapper;

    @Override
    public List<Map> getDictColor() {
        return tdDictMapper.getDictColor();
    }

    @Override
    public void insertApp(Map param) {
        tdDictMapper.insertApp(param);
    }

    @Override
    public List<Map> getHealthMarck() {
        return tdDictMapper.getHealthMarck();
    }

    @Override
    public List<Map> getHealthWeight() {
        return tdDictMapper.getHealthWeight();
    }

    @Override
    public Tree<TdDict> findTreeDicts(String flag) {
        Example example = new Example(TdDict.class);
        Example.Criteria criteria = example.createCriteria();
        if (StringUtils.equals("top", flag)) {
            criteria.andIsNull("pid");
        } else {
            criteria.andIsNotNull("pid");
        }
        example.setOrderByClause("uuid");
        List<TdDict> dicts = this.findByExample(example);
        List<Tree<TdDict>> trees = new ArrayList<>();
        dicts.forEach(dict -> {
            Tree<TdDict> tree = new Tree<>();
            tree.setId(dict.getUuid());
            tree.setParentId(dict.getPid());
            tree.setText(dict.getDictValue());
            trees.add(tree);
        });
        return TreeUtils.build(trees);
    }

    @Override
    public List<TdDict> findTreeDicts() {
        return tdDictMapper.findTreeDicts();
    }

    /***
     * 根据pid查询数据字典
     * @param map
     * @return
     */
    @Override
    public List<TdDict> findDictByPid(Map map) {
        return tdDictMapper.findDictByPid(map);
    }

    @Override
    public void deleteDictById(String id) {
        tdDictMapper.deleteDictById(id);
    }

    @Override
    public void updateDict(TdDict tdDict) {
        tdDictMapper.updateDict(tdDict);
    }
}
