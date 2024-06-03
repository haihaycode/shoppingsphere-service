package com.shoppingsphere.shoppingsphereservice.service;

import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;


public interface DTOService<DTO> {
    Page<DTO> getPages(int page, int size);

    Page<DTO> getPages(String search, int page, int size);
}
