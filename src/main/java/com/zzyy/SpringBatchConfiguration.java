//package com.zzyy;
//
//import com.zzyy.batch.MessageMigrationJobConfiguration;
//import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
//import org.springframework.batch.core.configuration.support.ApplicationContextFactory;
//import org.springframework.batch.core.configuration.support.GenericApplicationContextFactory;
//import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//
///**
// * @Auther: zhouyu
// * @Date: 2019/8/12 17:07
// * @Description:
// */
//@Configuration
//@EnableAutoConfiguration
//@EnableBatchProcessing(modular = true)
//public class SpringBatchConfiguration {
//
//
//
//    @Bean
//    public ApplicationContextFactory firstJobContext() {
//        return new GenericApplicationContextFactory(MessageMigrationJobConfiguration.class);
//    }
//
//}
