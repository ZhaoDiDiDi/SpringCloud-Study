package springcloud.config;

import feign.Feign;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

//@Configuration
public class FeignConfiguration {

    //@Bean
    //@Scope("prototype")
    public Feign.Builder feignHystrixBuilder() {
        return new Feign.Builder();
    }
}
