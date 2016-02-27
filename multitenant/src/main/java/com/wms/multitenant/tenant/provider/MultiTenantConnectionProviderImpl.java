/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.wms.multitenant.tenant.provider;

import com.wms.multitenant.model.master.Tenant;
import com.wms.multitenant.repository.master.TenantRepository;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.sql.DataSource;
import org.hibernate.engine.jdbc.connections.spi.AbstractDataSourceBasedMultiTenantConnectionProviderImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.stereotype.Component;

/**
 *
 * @author Mazen
 * @company WeMake{}Stuff
 */
@Component
@PropertySource("classpath:application.properties")
public class MultiTenantConnectionProviderImpl extends AbstractDataSourceBasedMultiTenantConnectionProviderImpl implements ApplicationListener<ContextRefreshedEvent> {

   @Autowired
   private Environment springEnvironment;
   @Autowired
   private TenantRepository tenantRepo;
   @Autowired
   DataSource masterDataSource;
   private final Map<String, DataSource> map = new HashMap<>();

   @Override
   public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
      init();
   }

   private void init() {
      List<Tenant> tenants = tenantRepo.findAll();
      for (Tenant tenant : tenants) {

         map.put(tenant.getTenantKey(), constructDataSource(tenant.getTenantKey()));
      }
   }

   private DataSource constructDataSource(String dbName) {
      DriverManagerDataSource dataSource = new DriverManagerDataSource();
      dataSource.setDriverClassName(springEnvironment.getProperty("tenant.datasource.classname",
              "com.mysql.jdbc.Driver"));
      dataSource.setUrl(springEnvironment.getProperty("tenant.datasource.url", 
              "jdbc:mysql://localhost:3306/") + dbName+"?createDatabaseIfNotExist=true");
      dataSource.setUsername(springEnvironment.getProperty("tenant.datasource.user", "root"));
      dataSource.setPassword(springEnvironment.getProperty("tenant.datasource.password", "root"));
//      ResourceDatabasePopulator rdp = new ResourceDatabasePopulator();
//      rdp.populate(dataSource.getConnection());
      try {
         dataSource.getConnection().createStatement().execute("CREATE DATABASE IF NOT EXISTS " + dbName);
         dataSource.getConnection().createStatement().execute("CREATE TABLE  IF NOT EXISTS `product` (\n"
                 + "  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT,\n"
                 + "  `name` varchar(128) NOT NULL,\n"
                 + "  `product_id` varchar(128) DEFAULT NULL,\n"
                 + "  `price` double DEFAULT NULL,\n"
                 + "  `description` varchar(256) DEFAULT NULL,\n"
                 + "  `created` timestamp NULL DEFAULT NULL,\n"
                 + "  `updated` timestamp NULL DEFAULT NULL,\n"
                 + "  `deleted` timestamp NULL DEFAULT NULL,\n"
                 + "  PRIMARY KEY (`id`),\n"
                 + "  UNIQUE KEY `name` (`name`)\n"
                 + ") ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=latin1;");
      } catch (Exception ex) {
         System.out.println(ex);
      }
      return dataSource;
   }

   @Override
   protected DataSource selectAnyDataSource() {
      return masterDataSource;
   }

   @Override
   protected DataSource selectDataSource(String key) {
      return map.get(key);
   }

   public void addTenant(String tenantKey) {
      
      map.put(tenantKey, constructDataSource(tenantKey));
   }
}
