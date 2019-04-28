package com.yidusoft.utils;

import freemarker.template.TemplateExceptionHandler;
import org.mybatis.generator.api.MyBatisGenerator;
import org.mybatis.generator.internal.DefaultShellCallback;
import org.mybatis.generator.config.*;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.*;

import static com.yidusoft.core.ProjectConstant.*;


/**
 * 代码生成器，根据数据表名称生成对应的Model、Mapper、Service、Controller简化开发。
 */
public class CodeGenerator {
    //JDBC配置，请修改为你项目的实际配置
    private static final String JDBC_URL = "jdbc:mysql://192.168.1.35/hr_dental";
    private static final String JDBC_USERNAME = "root";
    private static final String JDBC_PASSWORD = "";
    private static final String JDBC_DIVER_CLASS_NAME = "com.mysql.jdbc.Driver";

//    private static final String PROJECT_PATH = System.getProperty("user.dir");//项目在硬盘上的基础路径
    private static final String PROJECT_PATH = "D:\\company\\Project\\hr-mz-server";//项目在硬盘上的基础路径
    private static final String TEMPLATE_FILE_PATH = PROJECT_PATH +"/src/main/resources/generator/template";//模板位置

    private static final String JAVA_PATH = "/src/main/java"; //java文件路径
    private static final String RESOURCES_PATH = "/src/main/resources";//资源文件路径

    private static final String PACKAGE_PATH_SERVICE = packageConvertPath(SERVICE_PACKAGE);//生成的Service存放路径
    private static final String PACKAGE_PATH_SERVICE_IMPL = packageConvertPath(SERVICE_IMPL_PACKAGE);//生成的Service实现存放路径
    private static final String PACKAGE_PATH_CONTROLLER = packageConvertPath(CONTROLLER_PACKAGE);//生成的Controller存放路径

    private static final String AUTHOR = "CodeGenerator";//@author
    private static final String DATE = new SimpleDateFormat("yyyy/MM/dd").format(new Date());//@date

    public static void main(String[] args) {
//        genCode("behavior_log","operation_log","sec_menu","sec_menu_member",
//                "sec_menu_org","sec_menu_role"
//                ,"sec_org","sec_role","sec_role_member"
//                ,"sec_user","sys_option","cust_bl","cust_bl_de","customer","in_order","in_order_in","notice_administration","potential_cust","scheduling_plan","stock","stock_in_out","stock_in_out_de","sys_accessory","sys_message","tooth_order","yy_manage");
//    genCode("cust_bl","cust_bl_de","cust_jz","cust_sfjl","cust_sfjl_de","customer","in_order","in_order_in","notice_administration"
//    ,"potential_cust","scheduling_plan","stock","stock_in_out","stock_in_out_de","stock_product","stock_product_type","stock_warn","sys_accessory"
//    ,"sys_message","sys_type","tooth_order","yy_manage");
        genCode("customer");
    }

    public static void genCode(String... tableNames) {
        for (String tableName : tableNames) {
            //根据需求生成，不需要的注掉，模板有问题的话可以自己修改。
            genModelAndMapper(tableName);
//            genService(tableName);
//            genController(tableName);
        }
    }

    public static void genModelAndMapper(String tableName) {
        Context context = new Context(ModelType.FLAT);
        context.setId("Potato");
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
        pluginConfiguration.setConfigurationType("tk.mybatis.mapper.generator.MapperPlugin");
        pluginConfiguration.addProperty("mappers", MAPPER_INTERFACE_REFERENCE);
        context.addPluginConfiguration(pluginConfiguration);

        JavaModelGeneratorConfiguration javaModelGeneratorConfiguration = new JavaModelGeneratorConfiguration();
        javaModelGeneratorConfiguration.setTargetProject(PROJECT_PATH + "/"+JAVA_PATH);
        javaModelGeneratorConfiguration.setTargetPackage(MODEL_PACKAGE); //damain
        context.setJavaModelGeneratorConfiguration(javaModelGeneratorConfiguration);

        SqlMapGeneratorConfiguration sqlMapGeneratorConfiguration = new SqlMapGeneratorConfiguration();
        sqlMapGeneratorConfiguration.setTargetProject(PROJECT_PATH+ "/" + RESOURCES_PATH);
        sqlMapGeneratorConfiguration.setTargetPackage("mapper");//生成mapper文件夹
        context.setSqlMapGeneratorConfiguration(sqlMapGeneratorConfiguration);

        JavaClientGeneratorConfiguration javaClientGeneratorConfiguration = new JavaClientGeneratorConfiguration();
        javaClientGeneratorConfiguration.setTargetProject(PROJECT_PATH + "/"+ JAVA_PATH);
        javaClientGeneratorConfiguration.setTargetPackage(MAPPER_PACKAGE);//dao
        javaClientGeneratorConfiguration.setConfigurationType("XMLMAPPER");
        context.setJavaClientGeneratorConfiguration(javaClientGeneratorConfiguration);

        TableConfiguration tableConfiguration = new TableConfiguration(context);
        tableConfiguration.setTableName(tableName);
        tableConfiguration.setGeneratedKey(new GeneratedKey("id", "Mysql", true, null));
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

        String modelName = tableNameConvertUpperCamel(tableName);

        System.out.println(modelName + ".java 生成成功");
        System.out.println(modelName + "Mapper.java 生成成功");
        System.out.println(modelName + "Mapper.xml 生成成功");
    }

    public static void genService(String tableName) {
        try {
            freemarker.template.Configuration cfg = getConfiguration();

            Map<String, Object> data = new HashMap<>();
            data.put("date", DATE);
            data.put("author", AUTHOR);
            String modelNameUpperCamel = tableNameConvertUpperCamel(tableName);
            data.put("modelNameUpperCamel", modelNameUpperCamel);
            data.put("modelNameLowerCamel", tableNameConvertLowerCamel(tableName));
            data.put("basePackage", BASE_PACKAGE);
            data.put("basePackageChild", BASE_PACKAGE_CHILD);

            File file = new File(PROJECT_PATH + "/"+ JAVA_PATH + PACKAGE_PATH_SERVICE + modelNameUpperCamel + "Service.java");
            if (!file.getParentFile().exists()) {
                file.getParentFile().mkdirs();
            }
            cfg.getTemplate("service.ftl").process(data,
                    new FileWriter(file));
            System.out.println(modelNameUpperCamel + "Service.java 生成成功");

            File file1 = new File(PROJECT_PATH + "/"+ JAVA_PATH + PACKAGE_PATH_SERVICE_IMPL + modelNameUpperCamel + "ServiceImpl.java");
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

    public static void genHtml(String tableName){
        try {
            freemarker.template.Configuration cfg = getConfiguration();

            Map<String, Object> data = new HashMap<>();
            String modelNameUpperCamel = tableNameConvertUpperCameltoLc(tableName);


            File file = new File(PROJECT_PATH + "/"   +RESOURCES_PATH +"/"+
                    "templates/" +modelNameUpperCamel.toLowerCase() +"/" + modelNameUpperCamel.toLowerCase() + "-add.html");

            File file1 = new File(PROJECT_PATH + "/"   +RESOURCES_PATH +"/"+
                    "templates/" +modelNameUpperCamel.toLowerCase() +"/" + modelNameUpperCamel.toLowerCase() + "-update.html");

            File file2 = new File(PROJECT_PATH + "/"   +RESOURCES_PATH +"/"+
                    "templates/" +modelNameUpperCamel.toLowerCase() +"/" + modelNameUpperCamel.toLowerCase() + "-list.html");

            if (!file.getParentFile().exists()) {
                file.getParentFile().mkdirs();
            }
            if (!file1.getParentFile().exists()) {
                file1.getParentFile().mkdirs();
            }

            if (!file2.getParentFile().exists()) {
                file2.getParentFile().mkdirs();
            }

            cfg.getTemplate("html-form.ftl").process(data, new FileWriter(file));
            cfg.getTemplate("html-form.ftl").process(data, new FileWriter(file1));
            cfg.getTemplate("html-form.ftl").process(data, new FileWriter(file2));

            System.out.println(modelNameUpperCamel.toLowerCase() + "-add.html 生成成功");
            System.out.println(modelNameUpperCamel.toLowerCase() + "-update.html 生成成功");
            System.out.println(modelNameUpperCamel.toLowerCase() + "-list.html 生成成功");
        } catch (Exception e) {
            throw new RuntimeException("生成html失败", e);
        }
    }


    public static void genController(String tableName) {
        try {
            freemarker.template.Configuration cfg = getConfiguration();

            Map<String, Object> data = new HashMap<>();
            data.put("date", DATE);
            data.put("author", AUTHOR);
            data.put("baseRequestMapping", tableNameConvertMappingPath(tableName));
            String modelNameUpperCamel = tableNameConvertUpperCamel(tableName);
            String modelNameUpperCamelLc = tableNameConvertUpperCameltoLc(tableName);
            data.put("modelNameUpperCamelLc", modelNameUpperCamelLc);

            data.put("modelNameUpperCamel", modelNameUpperCamel);
            data.put("modelNameLowerCamel", tableNameConvertLowerCamel(tableName));
            data.put("basePackage", BASE_PACKAGE);
            data.put("basePackageChild", BASE_PACKAGE_CHILD);

            File file = new File(PROJECT_PATH + "/"+ JAVA_PATH + PACKAGE_PATH_CONTROLLER + modelNameUpperCamel + "Controller.java");
            if (!file.getParentFile().exists()) {
                file.getParentFile().mkdirs();
            }
            cfg.getTemplate("controller.ftl").process(data, new FileWriter(file));

            System.out.println(modelNameUpperCamel + "Controller.java 生成成功");
        } catch (Exception e) {
            throw new RuntimeException("生成Controller失败", e);
        }

    }



    private static freemarker.template.Configuration getConfiguration() throws IOException {
        freemarker.template.Configuration cfg = new freemarker.template
                .Configuration(freemarker.template.Configuration.VERSION_2_3_23);

        cfg.setDirectoryForTemplateLoading(new File(TEMPLATE_FILE_PATH));
        cfg.setDefaultEncoding("UTF-8");
        cfg.setTemplateExceptionHandler(TemplateExceptionHandler.IGNORE_HANDLER);
        return cfg;
    }

    private static String tableNameConvertLowerCamel(String tableName) {
        StringBuilder result = new StringBuilder();
        if (tableName != null && tableName.length() > 0) {
            tableName = tableName.toLowerCase();//兼容使用大写的表名
            boolean flag = false;
            for (int i = 0; i < tableName.length(); i++) {
                char ch = tableName.charAt(i);
                if ("_".charAt(0) == ch) {
                    flag = true;
                } else {
                    if (flag) {
                        result.append(Character.toUpperCase(ch));
                        flag = false;
                    } else {
                        result.append(ch);
                    }
                }
            }
        }
        return result.toString();
    }
    //类名小写
    private static String tableNameConvertUpperCameltoLc(String tableName) {
        String camel = tableNameConvertLowerCamel(tableName);
        return camel.toLowerCase();
    }


    private static String tableNameConvertUpperCamel(String tableName) {
        String camel = tableNameConvertLowerCamel(tableName);
        return camel.substring(0, 1).toUpperCase() + camel.substring(1);
    }

    private static String tableNameConvertMappingPath(String tableName) {
        tableName = tableName.toLowerCase();//兼容使用大写的表名
        return "/" + (tableName.contains("_") ? tableName.replaceAll("_", "/") : tableName);
    }

    private static String packageConvertPath(String packageName) {
        return String.format("/%s/", packageName.contains(".") ? packageName.replaceAll("\\.", "/") : packageName);
    }
}
