/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.wms.multitenant.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 *
 * @author Mazen
 * @company WeMake{}Stuff
 */
@Controller
public class Root {
   
     @RequestMapping("/")
     public String root(){
        return "index";
     }
   
}
