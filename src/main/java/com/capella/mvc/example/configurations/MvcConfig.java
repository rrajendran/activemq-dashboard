package com.capella.mvc.example.configurations;

import com.mitchellbosecke.pebble.PebbleEngine;
import com.mitchellbosecke.pebble.loader.Loader;
import com.mitchellbosecke.pebble.spring4.extension.SpringExtension;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.Session;
import java.util.Properties;

import static org.slf4j.LoggerFactory.getLogger;

@Configuration
public class MvcConfig extends WebMvcConfigurerAdapter {
    public static final Logger LOGGER = getLogger(MvcConfig.class);

    @Autowired
    private Loader templateLoader;

    @Autowired
    private SpringExtension springExtension;

    @Autowired
    private Properties properties;



    @Bean
    public PebbleEngine pebbleEngine() {
        return new PebbleEngine.Builder()
                        .loader(this.templateLoader)
                        .extension(this.springExtension)
                        .build();
    }


    @Bean
    public ConnectionFactory getConnectionFactory(){
        return new org.apache.activemq.ActiveMQConnectionFactory(properties.getProperty("activemq.url", "tcp://localhost:61616"));
    }

    @Bean
    public Connection getConnection(ConnectionFactory connectionFactory ) throws javax.jms.JMSException {
        return connectionFactory.createConnection(properties.getProperty("activemq.username", "admin"),
                properties.getProperty("activemq.password", "admin"));
    }

    @Bean
    public Session getConnection(Connection connection ) throws javax.jms.JMSException {
        return connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
    }
}