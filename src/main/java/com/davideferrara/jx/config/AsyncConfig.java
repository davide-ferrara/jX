package com.davideferrara.jx.config;

import com.davideferrara.jx.JxApplication;
import com.davideferrara.jx.classes.XmlConfig;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import java.util.concurrent.Executor;

@Configuration
public class AsyncConfig {

    private static final XmlConfig configXml = JxApplication.getXmlConfig();
    private final int corePoolSize = Integer.parseInt(configXml.getValueFromElement("asyncConfig", "corePoolSize"));
    private final int maxPoolSize = Integer.parseInt(configXml.getValueFromElement("asyncConfig", "maxPoolSize"));
    private final int queueCapacity = Integer.parseInt(configXml.getValueFromElement("asyncConfig", "queueCapacity"));
    private final String threadNamePrefix = configXml.getValueFromElement("asyncConfig", "threadNamePrefix");

    @Bean(name = "taskExecutor")
    public Executor taskExecutor() {
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        executor.setCorePoolSize(corePoolSize);
        executor.setMaxPoolSize(maxPoolSize);
        executor.setQueueCapacity(queueCapacity);
        executor.setThreadNamePrefix(threadNamePrefix);
        executor.initialize();
        return executor;
    }
}
