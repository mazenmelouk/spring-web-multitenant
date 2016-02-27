/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.wms.multitenant.controller;

import com.wms.multitenant.model.master.Tenant;
import com.wms.multitenant.repository.master.TenantRepository;
import com.wms.multitenant.tenant.provider.MultiTenantConnectionProviderImpl;
import java.util.Map;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 *
 * @author Mazen
 * @company WeMake{}Stuff
 */
@Controller
@RequestMapping(value = "/register")
public class Register {

   @Autowired
   TenantRepository tenantRepo;
   @Autowired
   MultiTenantConnectionProviderImpl multiTenantConnectionProviderImpl;

   @RequestMapping(method = RequestMethod.GET)
   public String viewRegistration(Map<String, Object> model) {
      Tenant tenantForm = new Tenant();
      model.put("tenantForm", tenantForm);
      return "register/form";
   }

   @RequestMapping(method = RequestMethod.POST)
   public String processRegistration(@ModelAttribute("tenantForm") Tenant tenantForm,
           Map<String, Object> model, ModelMap view) {
      String tenantKey = tenantForm.getName().replaceAll("[^a-zA-Z]+", "").toLowerCase().trim();
      Optional<Tenant> previouslyStored = tenantRepo.findByTenantKey(tenantKey);
      String response="Sorry your company name ("+tenantForm.getName()+")"+" is already taken";
      if (!previouslyStored.isPresent()) {
         tenantForm.setTenantKey(tenantKey);
         tenantRepo.save(tenantForm);
         multiTenantConnectionProviderImpl.addTenant(tenantKey);
         response = "Successfully registered, your key is " + tenantKey;
      }
      view.addAttribute("response", response);
      return "register/response";
   }
}
