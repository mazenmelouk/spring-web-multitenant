/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.wms.multitenant.repository.tenant;

import com.wms.multitenant.model.tenant.Product;
import java.io.Serializable;
import java.util.List;
import org.springframework.data.repository.CrudRepository;

/**
 *
 * @author Mazen
 * @company WeMake{}Stuff
 */
public interface ProductRepository extends CrudRepository<Product, Serializable> {

    @Override
    public List<Product> findAll();
}
