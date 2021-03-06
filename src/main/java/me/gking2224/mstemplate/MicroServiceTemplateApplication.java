package me.gking2224.mstemplate;


import javax.security.auth.message.config.AuthConfigFactory;
import javax.servlet.ServletContext;

import org.apache.catalina.authenticator.jaspic.AuthConfigFactoryImpl;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.context.embedded.AnnotationConfigEmbeddedWebApplicationContext;
import org.springframework.boot.web.support.ServletContextApplicationContextInitializer;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.context.ApplicationContextInitializer;
import org.springframework.context.annotation.AnnotationBeanNameGenerator;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.web.context.support.StandardServletEnvironment;

import me.gking2224.common.CommonConfiguration;
import me.gking2224.common.jmx.CommonJmxConfiguration;
import me.gking2224.mstemplate.aop.AopConfiguration;
import me.gking2224.mstemplate.db.DatabaseConfiguration;
import me.gking2224.mstemplate.db.EmbeddedDatabaseConfiguration;
import me.gking2224.mstemplate.jms.MessagingConfiguration;
import me.gking2224.mstemplate.security.SecurityConfiguration;
import me.gking2224.mstemplate.web.WebAppConfiguration;

@Configuration
@ComponentScan(basePackages={"me.gking2224.mstemplate.service", "me.gking2224.mstemplate.model"})
@Import({
    WebAppConfiguration.class,
    DatabaseConfiguration.class, EmbeddedDatabaseConfiguration.class,
    CommonConfiguration.class,
    CommonJmxConfiguration.class,
    AopConfiguration.class,
    SecurityConfiguration.class,
    MessagingConfiguration.class
})
public class MicroServiceTemplateApplication extends SpringBootServletInitializer {
    
    private ServletContext servletContext;
    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        ConfigurableEnvironment environment = new StandardServletEnvironment();
//        ApplicationListener<?> listeners = new ApplicationListenerImplementation();
        ApplicationContextInitializer<?> initializers = initializer();
        return application
                .contextClass(AnnotationConfigEmbeddedWebApplicationContext.class)
                .environment(environment)
//                .listeners(listeners)
                .initializers(initializers)
                .registerShutdownHook(true)
                .web(true)
                .logStartupInfo(true)
                .beanNameGenerator(new AnnotationBeanNameGenerator())
                .sources(MicroServiceTemplateApplication.class);
    }
    private ServletContextApplicationContextInitializer initializer() {
        ServletContextApplicationContextInitializer initializer = new ServletContextApplicationContextInitializer(servletContext);
        return initializer;
    }
    public static void main(String[] args) {
        // http://stackoverflow.com/questions/38802437/upgrading-spring-boot-from-1-3-7-to-1-4-0-causing-nullpointerexception-in-authen
        if (AuthConfigFactory.getFactory() == null) {
            AuthConfigFactory.setFactory(new AuthConfigFactoryImpl());
        }
        SpringApplication.run(MicroServiceTemplateApplication.class, args);
    }
}
