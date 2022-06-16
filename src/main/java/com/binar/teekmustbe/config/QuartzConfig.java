//package com.binar.teekmustbe.config;
//
//import org.quartz.JobDetail;
//import org.quartz.Trigger;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.ApplicationContext;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.scheduling.quartz.CronTriggerFactoryBean;
//import org.springframework.scheduling.quartz.JobDetailFactoryBean;
//import org.springframework.scheduling.quartz.SchedulerFactoryBean;
//import org.springframework.scheduling.quartz.SpringBeanJobFactory;
//
//@Configuration
//public class QuartzConfig {
//    @Autowired
//    private ApplicationContext applicationContext;
//
//    public QuartzConfig(ApplicationContext applicationContext) {
//        this.applicationContext = applicationContext;
//    }
//
//    @Bean
//    public SpringBeanJobFactory springBeanJobFactory(){
//        var springBeanJobFactory = new SpringBeanJobFactory();
//        springBeanJobFactory.setApplicationContext(this.applicationContext);
//        return springBeanJobFactory;
//    }
//
//    @Bean
//    public SchedulerFactoryBean schedulerFactoryBean(){
//        var schedulerFactoryBean = new SchedulerFactoryBean();
//        schedulerFactoryBean.setJobFactory(springBeanJobFactory());
//        JobDetail[] jobDetails = {jobDetailFactoryBean().getObject()};
//        Trigger[] triggers = {cronTriggerFactoryBean().getObject()};
//        return schedulerFactoryBean;
//    }
//
//    @Bean
//    public CronTriggerFactoryBean cronTriggerFactoryBean(){
//        var cronTriggerFactoryBean = new CronTriggerFactoryBean();
//        cronTriggerFactoryBean.setJobDetail(jobDetailFactoryBean().getObject());
//        cronTriggerFactoryBean.setCronExpression("0 15 10 ? * *");//Fire at 10:15 AM every day
//        return cronTriggerFactoryBean;
//    }
//
//    @Bean
//    public JobDetailFactoryBean jobDetailFactoryBean(){
//        var jobDetailFactoryBean = new JobDetailFactoryBean();
//        jobDetailFactoryBean.setJobClass(LoadJobScheduler.class);
//        jobDetailFactoryBean.setDescription("show films");
//        jobDetailFactoryBean.setName("load films");
//        jobDetailFactoryBean.setDurability(true);
//        return jobDetailFactoryBean;
//    }
//
//}
