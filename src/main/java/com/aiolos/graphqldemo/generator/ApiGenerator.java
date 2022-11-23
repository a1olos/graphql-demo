package com.aiolos.graphqldemo.generator;

import com.baomidou.mybatisplus.annotation.DbType;
import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.config.DataSourceConfig;
import com.baomidou.mybatisplus.generator.config.GlobalConfig;
import com.baomidou.mybatisplus.generator.config.PackageConfig;
import com.baomidou.mybatisplus.generator.config.StrategyConfig;
import com.baomidou.mybatisplus.generator.config.converts.MySqlTypeConvert;
import com.baomidou.mybatisplus.generator.config.rules.DateType;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;
import org.apache.commons.io.FileUtils;

import java.io.File;

public class ApiGenerator {

	private static final String URL = "jdbc:mysql://localhost:3306/graphql-demo?characterEncoding=utf8";

	private static final String OUTPUT_DIR = "C:/data/out/game";

    public static void main(String[] args) throws Exception {
        FileUtils.deleteDirectory(new File(OUTPUT_DIR));

        AutoGenerator generator = new AutoGenerator();
       // 全局配置
        GlobalConfig gc = new GlobalConfig();
        gc.setOutputDir(OUTPUT_DIR);
        gc.setFileOverride(true);
        gc.setActiveRecord(true);
        gc.setEnableCache(false);// XML 二级缓存
        gc.setBaseResultMap(true);// XML ResultMap
        gc.setBaseColumnList(true);// XML columnList
        gc.setOpen(true);
        gc.setAuthor("system");
        // 自定义文件命名，注意 %s 会自动填充表实体属性！
        /*gc.setServiceName("%sService");
        gc.setServiceImplName("%sServiceImpl");*/
        gc.setSimpleServiceName("%sService");
        gc.setSimpleService(true);

        gc.setDateType(DateType.ONLY_DATE);//指定生成的时间类型为Date
        generator.setGlobalConfig(gc);

        // 数据源配置
        DataSourceConfig dsc = new DataSourceConfig();
        dsc.setDbType(DbType.MYSQL);
        dsc.setTypeConvert(new MySqlTypeConvert());
        dsc.setDriverName("com.mysql.jdbc.Driver");
        dsc.setUsername("root");
        dsc.setPassword("123456");
        dsc.setUrl(URL);
        generator.setDataSource(dsc);

        // 策略配置
        StrategyConfig strategy = new StrategyConfig();
        strategy.setNaming(NamingStrategy.underline_to_camel);// 表名生成策略
		strategy.setEntityLombokModel(true);
//		strategy.setSuperControllerClass("com.aiolos.graphqdemo.api.controller.BaseController");
		strategy.setRestControllerStyle(true);
		strategy.setControllerMappingHyphenStyle(true);
        strategy.entityTableFieldAnnotationEnable(true);
        strategy.setTablePrefix("tb_");
        strategy.setInclude(new String[] { "tb_book","tb_author" }); // 需要生成的表

        // strategy.setExclude(new String[]{"test"}); // 排除生成的表
        generator.setStrategy(strategy);

        // 包配置
        PackageConfig pc = new PackageConfig();
        pc.setParent("com.aiolos.graphqdemol")
        .setEntity("entity")
		.setMapper("dao")
		.setXml("dao")
        .setSimpleService("service")
		.setController("controller");
        generator.setPackageInfo(pc);

        //执行
        generator.execute();
    }
}
