package spring.hiber.config;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

@WebListener
public class SpringContextLoaderListener implements ServletContextListener {
    @Override
    public void contextInitialized(ServletContextEvent sce) {
        sce.getServletContext().setAttribute("springContext", new AnnotationConfigApplicationContext(SpringConfig.class));
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        ((AnnotationConfigApplicationContext) sce.getServletContext().getAttribute("springContext")).close();
    }
}
