/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.wms.multitenant.controller;

import com.wms.multitenant.model.tenant.Product;
import com.wms.multitenant.repository.master.TenantRepository;
import com.wms.multitenant.repository.tenant.ProductRepository;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 *
 * @author Mazen
 * @company WeMake{}Stuff
 */
@Controller
public class Tenant {

   @Autowired
   ProductRepository productRepo;
   @Autowired
   TenantRepository tenantRepo;

   @RequestMapping("/{tenantId}")
   public String index(@PathVariable String tenantId, ModelMap model) {
      String companyName = tenantRepo.findByTenantKey(tenantId).get().getName();
      model.addAttribute("tenantName", companyName);
      return "tenant/index";
   }

   @RequestMapping(value = "/{tenantId}/add", method = RequestMethod.GET)
   public String viewAddProduct(Map<String, Object> model) {
      Product productForm = new Product();
      model.put("productForm", productForm);
      return "tenant/product/form";
   }
    @RequestMapping(value = "/{tenantId}/add", method = RequestMethod.POST)
   public String addProduct(@ModelAttribute("productForm")  Product productForm,
           Map<String, Object> model) {
      productRepo.save(productForm);
      return "tenant/index";
   }
   

   @ResponseBody
   @RequestMapping("/{tenantId}/products")
   public String products(@PathVariable String tenantId, Model model) {
      String st = "";
      List<Product> products = productRepo.findAll();
      for (Product p : products) {
         st +=  p.getName()+"\n";
      }
      return "Products "
              + ":\n" + st;
   }
}
