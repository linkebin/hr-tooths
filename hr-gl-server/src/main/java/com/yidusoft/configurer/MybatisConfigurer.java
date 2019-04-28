package com.yidusoft.configurer;


import com.github.pagehelper.PageHelper;
import com.yidusoft.project.interceptor.SqlInterceptor;
import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternResolver;
import tk.mybatis.spring.mapper.MapperScannerConfigurer;

import javax.sql.DataSource;
import java.util.Properties;

/**
 * Mybatis & Mapper & PageHelper 配置
 */
@Configuration
public class MybatisConfigurer {

    public  final String BASE_PACKAGE = "com.yidusoft";//项目基础包名称
    public  final String MODEL_PACKAGE = BASE_PACKAGE + ".project.*.domain";//Model所在包
    public  final String MAPPER_PACKAGE = BASE_PACKAGE + ".project.*.dao";//Mapper所在包
    public  final String MAPPER_INTERFACE_REFERENCE = BASE_PACKAGE + ".core.Mapper";//Mapper插件基础接口的完全限定名

    @Bean
    public SqlSessionFactory sqlSessionFactoryBean(DataSource dataSource) throws Exception {
        SqlSessionFactoryBean factory = new SqlSessionFactoryBean();
        factory.setDataSource(dataSource);
        factory.setTypeAliasesPackage(MODEL_PACKAGE);

        //配置分页插件，详情请查阅官方文档
        PageHelper pageHelper = new PageHelper();
        Properties properties = new Properties();
        properties.setProperty("pageSizeZero", "true");//分页尺寸为0时查询所有纪录不再执行分页
        properties.setProperty("reasonable", "true");//页码<=0 查询第一页，页码>=总页数查询最后一页
        properties.setProperty("supportMethodsArguments", "true");//支持通过 Mapper 接口参数来传递分页参数
        pageHelper.setProperties(properties);


        // sql拦截
        SqlInterceptor sqlInterceptor=new SqlInterceptor();

        //添加插件
        factory.setPlugins(new Interceptor[]{pageHelper,sqlInterceptor});

        //factory.setPlugins(new Interceptor[] { sqlInterceptor });

        //添加XML目录
        ResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();
        factory.setMapperLocations(resolver.getResources("classpath:mapper/*.xml"));
        return factory.getObject();
    }

    @Bean
    public MapperScannerConfigurer mapperScannerConfigurer() {
        MapperScannerConfigurer mapperScannerConfigurer = new MapperScannerConfigurer();
        mapperScannerConfigurer.setSqlSessionFactoryBeanName("sqlSessionFactoryBean");
        mapperScannerConfigurer.setBasePackage(MAPPER_PACKAGE);

        //配置通用Mapper，详情请查阅官方文档
        Properties properties = new Properties();
        properties.setProperty("mappers", MAPPER_INTERFACE_REFERENCE);
        //insert、update是否判断字符串类型!='' 即 test="str != null"表达式内是否追加 and str != ''
        properties.setProperty("notEmpty", "false");
        properties.setProperty("IDENTITY", "MYSQL");
        //属性pageSqlId指示拦截的规则，以正则方式匹配。
        properties.setProperty("pageSqlId", ".*CustomRule$");
        mapperScannerConfigurer.setProperties(properties);

        return mapperScannerConfigurer;
    }

/*    @Bean
    public SqlInterceptor  paginationInterceptor(){
        SqlInterceptor sqlInterceptor=new SqlInterceptor();
        sqlInterceptor.setFilterSqlId(".*find$");
        return sqlInterceptor;
    }*/
}

