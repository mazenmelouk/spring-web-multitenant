
/*
 * To change this license header, choose License Headers in Project Properties. To change this
 * template file, choose Tools | Templates and open the template in the editor.
 */
package com.wms.multitenant.config;

/**
 *
 * @author Mazen
 * @company WeMake{}Stuff
 */
import com.wms.multitenant.tenant.interceptor.TenantIdentifierInterceptorAdapter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.JstlView;

@Configuration
@EnableWebMvc
@ComponentScan(basePackages = {"com.wms.multitenant"})
public class AppConfig extends WebMvcConfigurerAdapter {

   @Autowired
   private TenantIdentifierInterceptorAdapter multiTenancyInterceptor;

 

   @Bean
   public ViewResolver jspViewResolver() {
      InternalResourceViewResolver resolver = new InternalResourceViewResolver();
      resolver.setViewClass(JstlView.class);
      resolver.setPrefix("/WEB-INF/jsp/");
      resolver.setSuffix(".jsp");
      return resolver;
   }
    
   @Override
   public void addInterceptors(InterceptorRegistry registry) {
      registry.addInterceptor(multiTenancyInterceptor);
   }

}
