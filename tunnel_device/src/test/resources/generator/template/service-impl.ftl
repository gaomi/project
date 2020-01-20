package ${modulePackage}.service.impl;

import ${modulePackage}.dao.${modelNameUpperCamel}Mapper;
import ${modulePackage}.model.${modelNameUpperCamel};
import ${modulePackage}.service.${modelNameUpperCamel}Service;
import ${basePackage}.core.AbstractService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import javax.annotation.Resource;


/**
 * Created by ${author} on ${date}.
 */
@Service
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true, rollbackFor = Exception.class)
public class ${modelNameUpperCamel}ServiceImpl extends AbstractService<${modelNameUpperCamel}> implements ${modelNameUpperCamel}Service {
    private Logger logger = LoggerFactory.getLogger(this.getClass());
    @Resource
    private ${modelNameUpperCamel}Mapper ${modelNameLowerCamel}Mapper;

}
