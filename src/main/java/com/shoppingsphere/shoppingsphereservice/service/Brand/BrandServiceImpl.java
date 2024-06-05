package com.shoppingsphere.shoppingsphereservice.service.Brand;

import com.shoppingsphere.shoppingsphereservice.entity.Brand;
import com.shoppingsphere.shoppingsphereservice.repository.BrandRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class BrandServiceImpl implements BrandService{
    @Autowired
    BrandRepository brandRepository;
    @Override
    public Brand create(Brand object) {
        return null;
    }

    @Override
    public Brand update(Brand object) {
        return null;
    }

    @Override
    public boolean remove(Brand object) {
        return false;
    }

    @Override
    public Brand findById(Integer integer) {
        return null;
    }

    @Override
    public List<Brand> findAll() {
        return null;
    }

    @Override
    public List<Brand> getAll(boolean enable) {
        return brandRepository.findAllByEnabled(enable);
    }

    @Override
    public boolean disableBrand(int id, boolean enable) {
         brandRepository.disableBrand(id, enable);
         return true;
    }

    @Override
    public Page<Brand> getPages(int page, int size) {
        return null;
    }

    @Override
    public Page<Brand> getPages(String search, int page, int size) {
        return null;
    }
}
