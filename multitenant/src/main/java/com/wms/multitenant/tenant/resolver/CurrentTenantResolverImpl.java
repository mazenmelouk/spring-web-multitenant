/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.wms.multitenant.tenant.resolver;

import org.hibernate.context.spi.CurrentTenantIdentifierResolver;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;

/**
 *
 * @author Mazen
 * @company WeMake{}Stuff
 */
@Component
public class CurrentTenantResolverImpl implements CurrentTenantIdentifierResolver {

    @Override
    public String resolveCurrentTenantIdentifier() {
        RequestAttributes requestAttributes = RequestContextHolder.getRequestAttributes();
        if (requestAttributes != null) {
            String identifier = (String) requestAttributes.getAttribute("Current_Tenant",
                    RequestAttributes.SCOPE_REQUEST);
            if (identifier != null) {
                return identifier;
            }
        }
        return "";
    }

    @Override
    public boolean validateExistingCurrentSessions() {
        return true;
    }

}
