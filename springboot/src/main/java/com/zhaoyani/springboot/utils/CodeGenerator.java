package com.zhaoyani.springboot.utils;

import com.baomidou.mybatisplus.generator.FastAutoGenerator;
import com.baomidou.mybatisplus.generator.config.OutputFile;
import com.baomidou.mybatisplus.generator.engine.FreemarkerTemplateEngine;

import java.util.Collections;

public class CodeGenerator {
    public static void main(String[] args) {
        CodeGenerator generator = new CodeGenerator();
        generator.generator();
    }

    void generator(){
        FastAutoGenerator.create("jdbc:mysql://localhost:3306/zyn?serverTimezone=GMT%2b8", "root", "123")
                .globalConfig(builder -> {
                    builder.author("ZYN") // 设置作者
                            .enableSwagger()
                            .fileOverride()//覆盖已经生成文件
                            .outputDir("D:\\JavaWorkplace\\MyProject\\springboot\\src\\main\\java"); // 指定输出目录
                })
                .packageConfig(builder -> {
                    builder.parent("com.zhaoyani.springboot") // 设置父包名
                            .moduleName(null) // 设置父包模块名
                            .pathInfo(Collections.singletonMap(OutputFile.mapperXml, "D:\\JavaWorkplace\\MyProject\\springboot\\src\\main\\resources\\mapper")); // 设置mapperXml生成路径
                })
                .strategyConfig(builder -> {
                    builder.entityBuilder().enableLombok();
                    builder.controllerBuilder().enableHyphenStyle()  // 开启驼峰转连字符
                            .enableRestStyle();  // 开启生成@RestController 控制器
                    builder.addInclude("sort") // 设置需要生成的表名
                           .addTablePrefix("t_", ""); // 设置过滤表前缀



                })
//                .templateEngine(new FreemarkerTemplateEngine()) // 使用Freemarker引擎模板，默认的是Velocity引擎模板
                .execute();
    }
}
