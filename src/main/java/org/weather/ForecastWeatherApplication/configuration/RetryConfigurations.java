package org.weather.ForecastWeatherApplication.configuration;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.retry.annotation.EnableRetry;
import org.springframework.retry.backoff.BackOffPolicy;
import org.springframework.retry.backoff.ExponentialBackOffPolicy;
import org.springframework.retry.policy.SimpleRetryPolicy;
import org.springframework.retry.support.RetryTemplate;

@EnableRetry
@Configuration
public class RetryConfigurations {

    @Bean
    public SimpleRetryPolicy retryPolicy(@Value("${ops.retry.max.attempts}") int maxAttempts) {
        SimpleRetryPolicy retryPolicy = new SimpleRetryPolicy();
        retryPolicy.setMaxAttempts(maxAttempts);
        return retryPolicy;
    }

    @Bean
    public BackOffPolicy backOffPolicy(
            @Value("${ops.retry.initial.interval}") long initialInterval,
            @Value("${ops.retry.interval.multiplier}") double multiplier) {
        ExponentialBackOffPolicy exponentialBackOffPolicy = new ExponentialBackOffPolicy();
        exponentialBackOffPolicy.setInitialInterval(initialInterval);
        exponentialBackOffPolicy.setMultiplier(multiplier);
        return exponentialBackOffPolicy;
    }

    @Bean
    public RetryTemplate retryTemplate(
            SimpleRetryPolicy simpleRetryPolicy, BackOffPolicy backOffPolicy) {
        RetryTemplate template = new RetryTemplate();
        template.setRetryPolicy(simpleRetryPolicy);
        template.setBackOffPolicy(backOffPolicy);
        return template;
    }
}
