//package com.example.demo.config;
//
//import org.apache.catalina.connector.Connector;
//import org.springframework.boot.web.embedded.tomcat.TomcatServletWebServerFactory;
//import org.springframework.boot.web.servlet.server.ServletWebServerFactory;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//
//@Configuration
//public class TomcatConfig {
//
//    /**
//     * 配置http转https
//     * 下面是2.0的配置，1.x请搜索对应的设置
//     *
//     * @return
//     */
//    @Bean
//    public ServletWebServerFactory servletContainer() {
//        TomcatServletWebServerFactory tomcat = new TomcatServletWebServerFactory();
//        // 添加http
//        tomcat.addAdditionalTomcatConnectors(createHttpConnector());
//        return tomcat;
//    }
//
//    @Bean
//    public Connector createHttpConnector() {
//        Connector connector = new Connector(TomcatServletWebServerFactory.DEFAULT_PROTOCOL);
//        // 设置http端口
//        connector.setPort(60080);
//        return connector;
//    }
//}
