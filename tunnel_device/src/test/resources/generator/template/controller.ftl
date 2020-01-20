package ${modulePackage}.web;

import ${basePackage}.core.Result;
import ${basePackage}.core.ResultGenerator;
import ${basePackage}.core.controller.BaseController;
import ${modulePackage}.model.${modelNameUpperCamel};
import ${modulePackage}.service.${modelNameUpperCamel}Service;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

import javax.annotation.Resource;
import java.util.List;

/**
* Created by ${author} on ${date}.
*/
@RestController
@RequestMapping("${baseRequestMapping}")
@Api(description = "${apiModelName}管理")
public class ${modelNameUpperCamel}Controller extends BaseController {
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Resource
    private ${modelNameUpperCamel}Service ${modelNameLowerCamel}Service;

    @PostMapping("/list")
    @ApiOperation(value = "列表", notes = "${apiModelName}列表")
    public Result list(@RequestBody QueryRequest request) {
        try {
            Map<String, Object> result = super.selectByPageNumSize(request, () -> this.${modelNameLowerCamel}Service.findAll());
            return ResultGenerator.genSuccessResult(result);
        } catch (Exception e) {
            logger.error("获取${apiModelName}列表失败", e);
            return ResultGenerator.genFailResult("获取${apiModelName}列表失败！");
        }
    }

    @PostMapping("/detail")
    @ApiOperation(value = "明细", notes = "${apiModelName}详细数据")
    public Result detail(@RequestParam String id) {
        ${modelNameUpperCamel} ${modelNameLowerCamel} = ${modelNameLowerCamel}Service.findById(id);
        return ResultGenerator.genSuccessResult(${modelNameLowerCamel});
    }

    @PostMapping("/add")
    @ApiOperation(value = "新增", notes = "${apiModelName}新增")
    public Result add(${modelNameUpperCamel} ${modelNameLowerCamel}) {
        try {
            ${modelNameLowerCamel}Service.save(${modelNameLowerCamel});
            return ResultGenerator.genSuccessResult();
          } catch (Exception e) {
            logger.error("新增${apiModelName}信息失败", e);
            return ResultGenerator.genFailResult("新增${apiModelName}信息失败，请联系网站管理员！");
        }
    }

    @PostMapping("/delete")
    @ApiOperation(value = "删除", notes = "${apiModelName}删除")
    public Result delete(@RequestParam String id) {
        try {
            ${modelNameLowerCamel}Service.deleteById(id);
            return ResultGenerator.genSuccessResult();
          } catch (Exception e) {
            logger.error("删除${apiModelName}信息失败", e);
            return ResultGenerator.genFailResult("删除${apiModelName}信息失败，请联系网站管理员！");
        }
    }

    @PostMapping("/update")
    @ApiOperation(value = "修改", notes = "${apiModelName}修改")
    public Result update(${modelNameUpperCamel} ${modelNameLowerCamel}) {
        try {
            ${modelNameLowerCamel}Service.update(${modelNameLowerCamel});
            return ResultGenerator.genSuccessResult();
          } catch (Exception e) {
            logger.error("修改${apiModelName}信息失败", e);
            return ResultGenerator.genFailResult("修改${apiModelName}信息失败，请联系网站管理员！");
        }

    }


}
