package com.sportsbazaar.web.jsp.service;

import com.sportsbazaar.persistence.model.ShippingAddress;

public interface ShippingAddressService {

    void save(ShippingAddress address, Long userId);
    
}
