/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.wms.multitenant.tenant.interceptor;


import com.wms.multitenant.model.master.Tenant;
import com.wms.multitenant.repository.master.TenantRepository;
import java.util.Map;
import java.util.Optional;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerMapping;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

/**
 *
 * @author Mazen
 * @company WeMake{}Stuff
 */
@Component
public class TenantIdentifierInterceptorAdapter extends HandlerInterceptorAdapter {

   @Autowired
   private TenantRepository tenantRepo;

   @Override
   public boolean preHandle(HttpServletRequest req, HttpServletResponse res, Object handler) throws Exception {
      
      Map<String, Object> pathVars
              = (Map<String, Object>) req.getAttribute(HandlerMapping.URI_TEMPLATE_VARIABLES_ATTRIBUTE);
      if (pathVars.containsKey("tenantId")) {
         String tenantId = pathVars.get("tenantId").toString();
         Optional<Tenant> thisTenant = tenantRepo.findByTenantKey(tenantId);
         if (thisTenant.isPresent()) {
            req.setAttribute("Current_Tenant", thisTenant.get().getTenantKey());
            return true;
         } else {
            res.sendRedirect(req.getContextPath() + "/signUp");
            return false;
         }
      } else {
         return true;
      }

   }
}
