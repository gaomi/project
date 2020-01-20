package com.company.project.core;


import com.company.project.core.exception.ServiceException;
import org.apache.ibatis.exceptions.TooManyResultsException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import tk.mybatis.mapper.entity.Condition;
import tk.mybatis.mapper.entity.Example;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.util.Arrays;
import java.util.List;

/**
 * 基于通用MyBatis Mapper插件的Service接口的实现
 */
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true, rollbackFor = Exception.class)
public abstract class AbstractService<T> implements Service<T> {
    private Logger logger = LoggerFactory.getLogger(this.getClass());
    @Autowired
    protected Mapper<T> mapper;

    private Class<T> modelClass;    // 当前泛型真实类型的Class

    public Mapper<T> getMapper() {
        return mapper;
    }

    public AbstractService() {
        ParameterizedType pt = (ParameterizedType) this.getClass().getGenericSuperclass();
        modelClass = (Class<T>) pt.getActualTypeArguments()[0];
    }

    @Override
    @Transactional(readOnly = false, rollbackFor = Exception.class)
    public void save(T model) {
        mapper.insertSelective(model);
    }

    @Override
    @Transactional(readOnly = false, rollbackFor = Exception.class)
    public void save(List<T> models) {
        mapper.insertList(models);
    }

    @Override
    @Transactional(readOnly = false, rollbackFor = Exception.class)
    public void deleteById(String id) {
        mapper.deleteByPrimaryKey(id);
    }

    @Override
    @Transactional(readOnly = false, rollbackFor = Exception.class)
    public void deleteByIds(String ids) {
        mapper.deleteByIds(ids);
    }

    @Override
    @Transactional(readOnly = false, rollbackFor = Exception.class)
    public void update(T model) {
        mapper.updateByPrimaryKeySelective(model);
    }

    @Override
    public T findById(String id) {
        return mapper.selectByPrimaryKey(id);
    }

    @Override
    public T findBy(String fieldName, Object value) throws TooManyResultsException {
        try {
            T model = modelClass.newInstance();
            Field field = modelClass.getDeclaredField(fieldName);
            field.setAccessible(true);
            field.set(model, value);
            return mapper.selectOne(model);
        } catch (ReflectiveOperationException e) {
            throw new ServiceException(e.getMessage(), e);
        }
    }

    @Override
    public List<T> findByIds(String ids) {
        return mapper.selectByIds(ids);
    }

    @Override
    public List<T> findByCondition(Condition condition) {
        return mapper.selectByCondition(condition);
    }

    @Override
    public List<T> findAll() {
        return mapper.selectAll();
    }

    @Override
    public T findByKey(Object key) {
        return mapper.selectByPrimaryKey(key);
    }

    @Override
    @Transactional(readOnly = false, rollbackFor = Exception.class)
    public int batchDelete(String ids, String property, Class<T> clazz) {
        List<String> list = Arrays.asList(ids.split(","));
        Example example = new Example(clazz);
        example.createCriteria().andIn(property, list);
        return this.mapper.deleteByExample(example);
    }

    @Override
    @Transactional(readOnly = false, rollbackFor = Exception.class)
    public int delete(Object key) {
        return mapper.deleteByPrimaryKey(key);
    }

    @Override
    @Transactional(readOnly = false, rollbackFor = Exception.class)
    public int updateNotNull(T model) {
        return mapper.updateByPrimaryKeySelective(model);
    }

    @Override
    public List<T> findByExample(Object example) {
        return mapper.selectByExample(example);
    }

    @Override
    @Transactional(readOnly = false, rollbackFor = Exception.class)
    public void batchInsertData(List<T> list) {
        try {
            int size = list.size();
            int unitNum = 199;
            int startIndex = 0;
            int endIndex = 0;
            while (size > 0) {
                if (size > unitNum) {
                    endIndex = startIndex + unitNum;
                    mapper.insertList(list.subList(startIndex, endIndex));
                    logger.info(" start insert :" + startIndex + " to " + endIndex);
                } else {
                    endIndex = startIndex + size;
                    mapper.insertList(list.subList(startIndex, endIndex));
                    logger.info(" start insert :" + startIndex + " to " + endIndex);
                    logger.info("insertBatch success : " + list.size() + " data inserted ;");
                }
                List<T> insertData = list.subList(startIndex, endIndex);
                save(insertData);
                size = size - unitNum;
                startIndex = endIndex;
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new ServiceException("插入数据失败！", e);
        }

    }

    @Override
    @Transactional(readOnly = false, rollbackFor = Exception.class)
    public void batchInsertByMethod(List<T> list, String MethodKey) {
        try {
            Method method = this.mapper.getClass().getDeclaredMethod("batchSave" + MethodKey, List.class);
            int batchCount = 199;//每批commit的个数
            int batchLastIndex = batchCount - 1;//每批最后一个的下标
            for (int index = 0; index < list.size() - 1; ) {
                if (batchLastIndex > list.size() - 1) {
                    batchLastIndex = list.size();
                    method.invoke(this.mapper, list.subList(index, batchLastIndex));
                    //eamMapper.insertList(detailList.subList(index, batchLastIndex));
                    logger.info("insert :" + index + " to " + batchLastIndex);
                    logger.info("insertBatch success : " + list.size() + " data inserted ;");
                    break;//数据插入完成,退出循环
                } else {
                    method.invoke(this.mapper, list.subList(index, batchLastIndex));
                    logger.info("insert :" + index + " to " + batchLastIndex);
                    index = batchLastIndex;//设置下一批下标
                    batchLastIndex = index + (batchCount - 1);
                    if (index == list.size() - 1) {
                        method.invoke(this.mapper, list.subList(index, batchLastIndex));
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new ServiceException("插入数据失败！", e);
        }

    }
}
