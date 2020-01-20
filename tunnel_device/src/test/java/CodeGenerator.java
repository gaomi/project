import com.google.common.base.CaseFormat;
import freemarker.template.TemplateExceptionHandler;
import org.apache.commons.lang3.StringUtils;
import org.mybatis.generator.api.MyBatisGenerator;
import org.mybatis.generator.config.*;
import org.mybatis.generator.internal.DefaultShellCallback;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.*;

import static com.company.project.core.ProjectConstant.*;

/**
 * 代码生成器，根据数据表名称生成对应的Model、Mapper、Service、Controller简化开发。
 */
public class CodeGenerator {
    //JDBC配置，请修改为你项目的实际配置
    /*private static final String JDBC_URL = "jdbc:mysql://localhost:3306/paoding_crm";
    private static final String JDBC_USERNAME = "paoding";
    private static final String JDBC_PASSWORD = "paoding";
    private static final String JDBC_DIVER_CLASS_NAME = "com.mysql.jdbc.Driver";*/
    //private static final String JDBC_URL = "jdbc:oracle:thin:@192.168.8.138:1521:ORCL";
    private static final String JDBC_URL = "jdbc:oracle:thin:@192.168.18.39:1521:ORCL";
    //private static final String JDBC_URL = "jdbc:oracle:thin:@127.0.0.1:1521:XE";
    private static final String JDBC_USERNAME = "metro";
    private static final String JDBC_PASSWORD = "metro_123";
    //private static final String JDBC_USERNAME = "metro";
    //private static final String JDBC_PASSWORD = "metro_123";
    private static final String JDBC_DIVER_CLASS_NAME = "oracle.jdbc.driver.OracleDriver";

    private static final String PROJECT_PATH = System.getProperty("user.dir");//项目在硬盘上的基础路径
    private static final String TEMPLATE_FILE_PATH = PROJECT_PATH + "/src/test/resources/generator/template";//模板位置

    private static final String JAVA_PATH = "/src/main/java"; //java文件路径
    private static final String RESOURCES_PATH = "/src/main/resources";//资源文件路径

    private static final String PACKAGE_PATH_SERVICE = packageConvertPath(SERVICE_PACKAGE);//生成的Service存放路径
    private static final String PACKAGE_PATH_SERVICE_IMPL = packageConvertPath(SERVICE_IMPL_PACKAGE);//生成的Service实现存放路径
    private static final String PACKAGE_PATH_CONTROLLER = packageConvertPath(CONTROLLER_PACKAGE);//生成的Controller存放路径

    private static final String AUTHOR = "paodingsoft.chen";//@author
    private static final String DATE = new SimpleDateFormat("yyyy/MM/dd").format(new Date());//@date

    public static void main(String[] args) {
        //genCode("输入表名");
        //genCodeByCustomModelName("输入表名","输入自定义Model名称","日志模块名称");
        /*genCodeByCustomModelName("j302_td_sys_user", "SysUser", "系统用户");
        genCodeByCustomModelName("j302_td_sys_log", "SysLog", "系统日志");
        genCodeByCustomModelName("j302_td_sys_dept", "SysDept", "部门");
        genCodeByCustomModelName("j302_td_sys_user_role", "SysUserRole", "用户角色");
        genCodeByCustomModelName("j302_td_sys_menu", "SysMenu", "系统菜单");
        genCodeByCustomModelName("j302_td_sys_role", "SysRole", "系统角色");
        genCodeByCustomModelName("j302_td_sys_role_menu", "SysRoleMenu", "角色菜单");
        genCodeByCustomModelName("j302_td_sys_dict", "SysDict", "数据字典");*/

        /* genCodeByCustomModelName("tb_skxx_project", "SkxxProject", "项目信息");
         genCodeByCustomModelName("J302_API_JHJC_PROJECT", "JhjcProject", "监护监测项目信息");*/
        //genCodeByCustomModelName("J302_API_BH_INFO", "BhInfo", "病害信息");
        //genCodeByCustomModelName("J302_METRO_DEVICE_INFO_GW", "GwTunnelDevice", "工务设备信息");
        //genCodeByCustomModelName("J302_TD_DICT", "TdDict", "隧道设备系统字典表");
        //genCodeByCustomModelName("J302_TD_SEGMENT_INFO", "TdSegment", "地铁区间信息");
        //genCodeByCustomModelName("J302_TD_STATION_INFO", "TdStation", "地铁站点信息");
        //genCodeByCustomModelName("J302_TD_LINE_INFO", "TdLine", "地铁线路信息");
        //genCodeByCustomModelName("J302_TD_DUCT_INFO", "TdDuct", "环号信息");

        //genCodeByCustomModelName("J302_TD_SANDY_INFO", "TdSandy", "隧道砂性土信息");
        /*genCodeByCustomModelName("j302_td_sys_job_info", "JobInfo", "定时任务");
        genCodeByCustomModelName("j302_td_sys_job_log", "JobLog", "定时任务日志");*/

//        genCodeByCustomModelName("J302_METRO_LINE_INFO", "MetroLine", "线路信息");
//        genCodeByCustomModelName("J302_METRO_STATION_INFO", "MetroStation", "站点信息");
//        genCodeByCustomModelName("J302_METRO_DUCT_INFO", "MetroDuct", "环号信息");
//        genCodeByCustomModelName("J302_METRO_TUNNEL_ZONE_INFO", "MetroZone", "区间信息");
//        genCodeByCustomModelName("J302_TUNNEL_DICT", "TunnelDict", "数据字典信息");
//        genCodeByCustomModelName("J302_SI_PLAN", "InspectPlan", "施工计划");
        genCodeByCustomModelName("J302_EAM_EQUIP", "EamEquip", "EAM设备");
        genCodeByCustomModelName("J302_EAM_DEPART", "EamDepart", "EAM部门");
    }

    /**
     * 通过数据表名称生成代码，Model 名称通过解析数据表名称获得，下划线转大驼峰的形式。
     * 如输入表名称 "t_user_detail" 将生成 TUserDetail、TUserDetailMapper、TUserDetailService ...
     *
     * @param tableNames 数据表名称...
     */
    public static void genCode(String... tableNames) {
        for (String tableName : tableNames) {
            genCodeByCustomModelName(tableName, null, null);
        }
    }

    /**
     * 通过数据表名称，和自定义的 Model 名称生成代码
     * 如输入表名称 "t_user_detail" 和自定义的 Model 名称 "User" 将生成 User、UserMapper、UserService ...
     *
     * @param tableName 数据表名称
     * @param modelName 自定义的 Model 名称
     */
    public static void genCodeByCustomModelName(String tableName, String modelName, String apiModelName) {
        genModelAndMapper(tableName, modelName);
        genService(tableName, modelName);
        genController(tableName, modelName, apiModelName);
    }


    public static void genModelAndMapper(String tableName, String modelName) {
        Context context = new Context(ModelType.FLAT);
        context.setId("PaodingSoft");
        context.setTargetRuntime("MyBatis3Simple");
        context.addProperty(PropertyRegistry.CONTEXT_BEGINNING_DELIMITER, "`");
        context.addProperty(PropertyRegistry.CONTEXT_ENDING_DELIMITER, "`");

        JDBCConnectionConfiguration jdbcConnectionConfiguration = new JDBCConnectionConfiguration();
        jdbcConnectionConfiguration.setConnectionURL(JDBC_URL);
        jdbcConnectionConfiguration.setUserId(JDBC_USERNAME);
        jdbcConnectionConfiguration.setPassword(JDBC_PASSWORD);
        jdbcConnectionConfiguration.setDriverClass(JDBC_DIVER_CLASS_NAME);
        context.setJdbcConnectionConfiguration(jdbcConnectionConfiguration);

        PluginConfiguration pluginConfiguration = new PluginConfiguration();
        pluginConfiguration.setConfigurationType("org.mybatis.generator.plugins.SerializablePlugin");//序列化
        pluginConfiguration.setConfigurationType("org.mybatis.generator.plugins.ToStringPlugin");
        pluginConfiguration.setConfigurationType("tk.mybatis.mapper.generator.MapperPlugin");
        pluginConfiguration.addProperty("mappers", MAPPER_INTERFACE_REFERENCE);
        context.addPluginConfiguration(pluginConfiguration);

        JavaModelGeneratorConfiguration javaModelGeneratorConfiguration = new JavaModelGeneratorConfiguration();
        javaModelGeneratorConfiguration.setTargetProject(PROJECT_PATH + JAVA_PATH);
        javaModelGeneratorConfiguration.setTargetPackage(MODEL_PACKAGE);
        context.setJavaModelGeneratorConfiguration(javaModelGeneratorConfiguration);

        SqlMapGeneratorConfiguration sqlMapGeneratorConfiguration = new SqlMapGeneratorConfiguration();
        sqlMapGeneratorConfiguration.setTargetProject(PROJECT_PATH + RESOURCES_PATH);
        sqlMapGeneratorConfiguration.setTargetPackage("mapper" + MODULE_PACKAGE);
        context.setSqlMapGeneratorConfiguration(sqlMapGeneratorConfiguration);

        JavaClientGeneratorConfiguration javaClientGeneratorConfiguration = new JavaClientGeneratorConfiguration();
        javaClientGeneratorConfiguration.setTargetProject(PROJECT_PATH + JAVA_PATH);
        javaClientGeneratorConfiguration.setTargetPackage(MAPPER_PACKAGE);
        javaClientGeneratorConfiguration.setConfigurationType("XMLMAPPER");
        context.setJavaClientGeneratorConfiguration(javaClientGeneratorConfiguration);

        TableConfiguration tableConfiguration = new TableConfiguration(context);
        tableConfiguration.setTableName(tableName);
        if (StringUtils.isNotEmpty(modelName)) tableConfiguration.setDomainObjectName(modelName);
        //tableConfiguration.setGeneratedKey(new GeneratedKey("id", "Mysql", true, null));
        context.addTableConfiguration(tableConfiguration);

        List<String> warnings;
        MyBatisGenerator generator;
        try {
            Configuration config = new Configuration();
            config.addContext(context);
            config.validate();

            boolean overwrite = true;
            DefaultShellCallback callback = new DefaultShellCallback(overwrite);
            warnings = new ArrayList<String>();
            generator = new MyBatisGenerator(config, callback, warnings);
            generator.generate(null);
        } catch (Exception e) {
            throw new RuntimeException("生成Model和Mapper失败", e);
        }

        if (generator.getGeneratedJavaFiles().isEmpty() || generator.getGeneratedXmlFiles().isEmpty()) {
            throw new RuntimeException("生成Model和Mapper失败：" + warnings);
        }
        if (StringUtils.isEmpty(modelName)) modelName = tableNameConvertUpperCamel(tableName);
        System.out.println(modelName + ".java 生成成功");
        System.out.println(modelName + "Mapper.java 生成成功");
        System.out.println(modelName + "Mapper.xml 生成成功");
    }

    public static void genService(String tableName, String modelName) {
        try {
            freemarker.template.Configuration cfg = getConfiguration();

            Map<String, Object> data = new HashMap<>();
            data.put("date", DATE);
            data.put("author", AUTHOR);
            String modelNameUpperCamel = StringUtils.isEmpty(modelName) ? tableNameConvertUpperCamel(tableName) : modelName;
            data.put("modelNameUpperCamel", modelNameUpperCamel);
            //data.put("modelNameLowerCamel", tableNameConvertLowerCamel(modelName));
            data.put("modelNameLowerCamel", CaseFormat.UPPER_CAMEL.to(CaseFormat.LOWER_CAMEL, modelNameUpperCamel));
            data.put("basePackage", BASE_PACKAGE);
            data.put("modulePackage", BASE_PACKAGE + MODULE_PACKAGE);

            File file = new File(PROJECT_PATH + JAVA_PATH + PACKAGE_PATH_SERVICE + modelNameUpperCamel + "Service.java");
            if (!file.getParentFile().exists()) {
                file.getParentFile().mkdirs();
            }
            cfg.getTemplate("service.ftl").process(data,
                    new FileWriter(file));
            System.out.println(modelNameUpperCamel + "Service.java 生成成功");

            File file1 = new File(PROJECT_PATH + JAVA_PATH + PACKAGE_PATH_SERVICE_IMPL + modelNameUpperCamel + "ServiceImpl.java");
            if (!file1.getParentFile().exists()) {
                file1.getParentFile().mkdirs();
            }
            cfg.getTemplate("service-impl.ftl").process(data,
                    new FileWriter(file1));
            System.out.println(modelNameUpperCamel + "ServiceImpl.java 生成成功");
        } catch (Exception e) {
            throw new RuntimeException("生成Service失败", e);
        }
    }

    public static void genController(String tableName, String modelName, String apiModelName) {
        try {
            freemarker.template.Configuration cfg = getConfiguration();

            Map<String, Object> data = new HashMap<>();
            data.put("date", DATE);
            data.put("author", AUTHOR);
            String modelNameUpperCamel = StringUtils.isEmpty(modelName) ? tableNameConvertUpperCamel(tableName) : modelName;
            data.put("baseRequestMapping", modelNameConvertMappingPath(modelNameUpperCamel));
            data.put("modelNameUpperCamel", modelNameUpperCamel);
            data.put("modelNameLowerCamel", CaseFormat.UPPER_CAMEL.to(CaseFormat.LOWER_CAMEL, modelNameUpperCamel));
            data.put("basePackage", BASE_PACKAGE);
            data.put("modulePackage", BASE_PACKAGE + MODULE_PACKAGE);
            data.put("apiModelName", apiModelName);

            File file = new File(PROJECT_PATH + JAVA_PATH + PACKAGE_PATH_CONTROLLER + modelNameUpperCamel + "Controller.java");
            if (!file.getParentFile().exists()) {
                file.getParentFile().mkdirs();
            }
            //cfg.getTemplate("controller-restful.ftl").process(data, new FileWriter(file));
            cfg.getTemplate("controller.ftl").process(data, new FileWriter(file));

            System.out.println(modelNameUpperCamel + "Controller.java 生成成功");
        } catch (Exception e) {
            throw new RuntimeException("生成Controller失败", e);
        }

    }

    private static freemarker.template.Configuration getConfiguration() throws IOException {
        freemarker.template.Configuration cfg = new freemarker.template.Configuration(freemarker.template.Configuration.VERSION_2_3_23);
        cfg.setDirectoryForTemplateLoading(new File(TEMPLATE_FILE_PATH));
        cfg.setDefaultEncoding("UTF-8");
        cfg.setTemplateExceptionHandler(TemplateExceptionHandler.IGNORE_HANDLER);
        return cfg;
    }

    private static String tableNameConvertLowerCamel(String tableName) {
        return CaseFormat.LOWER_UNDERSCORE.to(CaseFormat.LOWER_CAMEL, tableName.toLowerCase());
    }

    private static String tableNameConvertUpperCamel(String tableName) {
        return CaseFormat.LOWER_UNDERSCORE.to(CaseFormat.UPPER_CAMEL, tableName.toLowerCase());

    }

    private static String tableNameConvertMappingPath(String tableName) {
        tableName = tableName.toLowerCase();//兼容使用大写的表名
        String moduleName = MODULE.replace(".", "/");
        return moduleName + "/" + (tableName.contains("_") ? tableName.replaceAll("_", "/") : tableName);
    }

    private static String modelNameConvertMappingPath(String modelName) {
        String tableName = CaseFormat.UPPER_CAMEL.to(CaseFormat.LOWER_UNDERSCORE, modelName);
        return tableNameConvertMappingPath(tableName);
    }

    private static String packageConvertPath(String packageName) {
        return String.format("/%s/", packageName.contains(".") ? packageName.replaceAll("\\.", "/") : packageName);
    }

}
