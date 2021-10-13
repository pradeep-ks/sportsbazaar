package com.sportsbazaar.web.jsp.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.webflow.config.AbstractFlowConfiguration;
import org.springframework.webflow.definition.registry.FlowDefinitionRegistry;
import org.springframework.webflow.executor.FlowExecutor;
import org.springframework.webflow.mvc.servlet.FlowHandlerAdapter;
import org.springframework.webflow.mvc.servlet.FlowHandlerMapping;

@Configuration
public class WebFlowConfig extends AbstractFlowConfiguration {

    @Bean
    public FlowHandlerMapping getFlowHandlerMapping() {
	FlowHandlerMapping mapping = new FlowHandlerMapping();
	mapping.setOrder(0);
	mapping.setFlowRegistry(flowRegistry());
	return mapping;
    }

    @Bean
    public FlowHandlerAdapter getFlowHandlerAdapter() {
	FlowHandlerAdapter adapter = new FlowHandlerAdapter();
	adapter.setFlowExecutor(flowExecutor());
	return adapter;
    }

    @Bean
    public FlowExecutor flowExecutor() {
	return getFlowExecutorBuilder(flowRegistry()).build();
    }

    @Bean
    public FlowDefinitionRegistry flowRegistry() {
	return getFlowDefinitionRegistryBuilder()
		.addFlowLocation("/WEB-INF/flow/checkout-flow.xml", "processOrder")
		.build();
    }
}
