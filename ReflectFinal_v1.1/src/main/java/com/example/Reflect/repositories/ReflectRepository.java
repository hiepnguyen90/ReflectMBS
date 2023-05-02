package com.example.Reflect.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.Reflect.models.ReflectModel;

@Repository
public interface ReflectRepository extends JpaRepository<ReflectModel, Long> {
    
}
