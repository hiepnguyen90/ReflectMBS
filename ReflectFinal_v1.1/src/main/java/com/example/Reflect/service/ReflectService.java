package com.example.Reflect.service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.Reflect.models.ReflectModel;
import com.example.Reflect.repositories.ReflectRepository;


@Service
public class ReflectService {
    @Autowired
    private ReflectRepository repository;

    public ReflectService(ReflectRepository repository) {
        this.repository = repository;
    }

    public Iterable<ReflectModel> index() {
        return repository.findAll();
    }

    public ReflectModel show(Long id) {
        return repository.findById(id).get();
    }

    public ReflectModel create(ReflectModel reflect) {
        return repository.save(reflect);
    }

    public ReflectModel update(Long id, ReflectModel newReflectData) {
        ReflectModel originalReflect = repository.findById(id).get();
        originalReflect.setDate(newReflectData.getDate());
        originalReflect.setAnswers(newReflectData.getAnswers());
        return repository.save(originalReflect);
    }

    public Boolean delete(Long id) {
        repository.deleteById(id);
        return true;
    }
}
