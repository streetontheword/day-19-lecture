package vttp.ssf.Day19.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
import org.springframework.data.redis.connection.jedis.JedisClientConfiguration;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.StringRedisSerializer;

import vttp.ssf.Day19.model.Employee;



@Configuration
public class RedisConfig {


    //env variable on Railway: REDIS_HOST 
    @Value("${redis.host}")
    private String redisHost;

    //env variable on Railway: REDIS_PORT 
    @Value("${redis.port}")
    private Integer redisPort;

    //env variable on Railway: REDIS_USERNAME 
    @Value("${redis.username}")
    private String redisUsername;

    //env variable on Railway: REDIS_PASSWORD 
    @Value("${redis.password}")
    private String redisPassword;

    public JedisConnectionFactory jedisConnFactory(){
        RedisStandaloneConfiguration rsc = new RedisStandaloneConfiguration(redisHost, redisPort);
        
        if(redisUsername != null && !redisUsername.isEmpty()){
            rsc.setUsername(redisUsername);
        }
        
        if(redisPassword != null && !redisPassword.isEmpty()){
            rsc.setPassword(redisPassword);
        }

        JedisClientConfiguration jedisClient = JedisClientConfiguration.builder().build();
        JedisConnectionFactory jedisFac = new JedisConnectionFactory(rsc, jedisClient);
        jedisFac.afterPropertiesSet();

        return jedisFac;


    }

    @Bean
    public RedisTemplate<String, Object> redisObjectTemplate(){

        RedisTemplate<String, Object> redisTemplate = new RedisTemplate<>();
        //need to inject the connection string 
        redisTemplate.setConnectionFactory(jedisConnFactory());

        //need to include serialisation, so must use serializer
        redisTemplate.setKeySerializer(new StringRedisSerializer());
        redisTemplate.setValueSerializer(new StringRedisSerializer());

        return redisTemplate;
    }

    @Bean
    public RedisTemplate<String, Employee> redisEmployeeTemplate(){

        RedisTemplate<String, Employee> redisTemplate = new RedisTemplate<>();
        redisTemplate.setConnectionFactory(jedisConnFactory());

        return redisTemplate;
    }


}
