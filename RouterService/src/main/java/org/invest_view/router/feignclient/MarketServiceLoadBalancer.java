package org.invest_view.router.feignclient;

import feign.Feign;
import org.invest_view.router.feignclient.configuration.MarketServiceLoadBalancerConfiguration;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.loadbalancer.annotation.LoadBalancerClient;
import org.springframework.context.annotation.Bean;

@LoadBalancerClient(name = "MARKET-SERVICE", configuration = MarketServiceLoadBalancerConfiguration.class)
public class MarketServiceLoadBalancer {

    @LoadBalanced
    @Bean
    public Feign.Builder feignBuilder() {
        return Feign.builder();
    }
}
