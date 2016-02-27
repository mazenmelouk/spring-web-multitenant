/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.wms.multitenant.repository.master;

import com.wms.multitenant.model.master.Tenant;
import java.io.Serializable;
import java.util.List;
import java.util.Optional;
import org.springframework.data.repository.CrudRepository;

/**
 *
 * @author Mazen
 * @company WeMake{}Stuff
 */
public interface TenantRepository extends CrudRepository<Tenant, Serializable> {

    public Optional<Tenant> findByTenantKey(String tenantKey);

    @Override
    public List<Tenant> findAll();
}
