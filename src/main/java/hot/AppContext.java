package hot;

import java.util.concurrent.Executor;
import javax.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.core.task.SimpleAsyncTaskExecutor;
import org.springframework.core.task.TaskExecutor;
import org.springframework.scheduling.TaskScheduler;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.concurrent.ConcurrentTaskScheduler;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

@Configuration
@ComponentScan(basePackages = {"hot"})
//@PropertySource(value = {"file:cfg/hofat.properties"})
@PropertySource(value = {"classpath:hofat.properties"})
@EnableAsync
@EnableScheduling
//@EnableTransactionManagement
//@EnableLoadTimeWeaving
//@EnableCaching
//@EnableWebMvc
//@Import(Ch4Configuration.class)
//@ImportResource("classpath:/beans-tx.xml")
public class AppContext {
    
    /*@Value("${hash}")
    private String HashTip; 
    
    @PostConstruct
    public void checkValue(){
            System.out.println(HashTip);        
    }*/
    
    @Bean
    public static PropertySourcesPlaceholderConfigurer propertySourcesPlaceholderConfigurer() {
        return new PropertySourcesPlaceholderConfigurer();
    }
    
    @Bean
    public TaskScheduler taskScheduler() {
        return new ConcurrentTaskScheduler();
    }    
    
    @Bean
    public TaskExecutor taskExecutor() {
        return new SimpleAsyncTaskExecutor();
    }
    
    /*@Bean
    public TaskExecutor taskExecutor() {
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        executor.setCorePoolSize(99);
        executor.setMaxPoolSize(111);
        executor.setQueueCapacity(99);
        executor.setThreadNamePrefix("async_task_executor");
        executor.setAllowCoreThreadTimeOut(true);
        executor.setKeepAliveSeconds(11);
        executor.initialize();
        return executor;
    }*/
    
    /*@Bean
    public static PropertySourcesPlaceholderConfigurer propertySourcesPlaceholderConfigurer() {
        PropertySourcesPlaceholderConfigurer pspc
                = new PropertySourcesPlaceholderConfigurer();
        Resource[] resources = new ClassPathResource[]{new ClassPathResource("hofat.properties")};
        pspc.setLocations(resources);
        pspc.setIgnoreUnresolvablePlaceholders(true);
        return pspc;
    }  */  
    
}
