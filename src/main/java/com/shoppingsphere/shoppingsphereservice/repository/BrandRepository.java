package com.shoppingsphere.shoppingsphereservice.repository;

import com.shoppingsphere.shoppingsphereservice.entity.Brand;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface BrandRepository extends JpaRepository<Brand,Integer> {
  List<Brand> findAllByEnabled(boolean enable);



  @Modifying
  @Query("UPDATE Brand u SET u.enabled = :disable WHERE u.id = :id")
  void disableBrand(@Param("id") int id, @Param("disable") boolean disable);
}
