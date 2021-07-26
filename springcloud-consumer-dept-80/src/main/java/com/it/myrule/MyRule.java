package com.it.myrule;

import com.netflix.loadbalancer.IRule;
import com.netflix.loadbalancer.RandomRule;
import org.springframework.context.annotation.Bean;

public class MyRule {
    @Bean
    public IRule MyIRule() {
        return new RandomRule();
    }
}
