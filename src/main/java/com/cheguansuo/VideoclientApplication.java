package com.cheguansuo;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@EnableTransactionManagement(proxyTargetClass = true)
@EnableAsync//开启异步调用
@MapperScan("com.cheguansuo.business.mapper") // 扫描Mapper接口
//@EnableAutoConfiguration(exclude = {MybatisAutoConfiguration.class,DataSourceAutoConfiguration.class})
@SpringBootApplication
public class VideoclientApplication  {//extends SpringBootServletInitializer

    public static void main(String[] args) {
        SpringApplication.run(VideoclientApplication.class, args);
    }

//    @Override
//    protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
//        return builder.sources(VideoclientApplication.class);
//    }

//    @Bean
//    public EmbeddedServletContainerFactory createEmbeddedServletContainerFactory() {
//        TomcatEmbeddedServletContainerFactory tomcat = new TomcatEmbeddedServletContainerFactory();
//        tomcat.addConnectorCustomizers(new TomcatConnectorCustomizer() {
//            @Override
//            public void customize(Connector connector) {
//                Http11NioProtocol protocol = (Http11NioProtocol) connector.getProtocolHandler();
//                //设置最大连接数
//                protocol.setMaxConnections(0);
//                protocol.setMaxHttpHeaderSize(0);
//                //设置最大线程数
//                protocol.setMaxThreads(0);
//                protocol.setMinSpareThreads(0);
//                //设置最大post长度
//                connector.setMaxPostSize(0);
//            }
//        });
//
//        return tomcat;
//    }




}
