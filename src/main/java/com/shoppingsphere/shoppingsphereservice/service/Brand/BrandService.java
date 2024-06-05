package com.shoppingsphere.shoppingsphereservice.service.Brand;

import com.shoppingsphere.shoppingsphereservice.entity.Brand;
import com.shoppingsphere.shoppingsphereservice.service.BaseService;
import com.shoppingsphere.shoppingsphereservice.service.DTOService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface BrandService extends BaseService<Brand, Integer>, DTOService<Brand> {
    List<Brand> getAll(boolean enable);
    boolean disableBrand(int id, boolean enabled);
}
