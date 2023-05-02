package com.example.Reflect.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.Reflect.models.ReflectModel;
import com.example.Reflect.service.ReflectService;

@RestController
@RequestMapping("/reflect")
public class ReflectController {
    @Autowired
    private ReflectService service;

    public ReflectController(ReflectService service) {
        this.service = service;
    }

    @GetMapping("")
    public ResponseEntity<Iterable<ReflectModel>> index() {
        return new ResponseEntity<>(service.index(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ReflectModel> show(@PathVariable Long id) {
        return new ResponseEntity<>(service.show(id), HttpStatus.OK);
    }

    @PostMapping("/create")
    public ResponseEntity<ReflectModel> create(@RequestBody ReflectModel reflect) {
        return new ResponseEntity<>(service.create(reflect), HttpStatus.CREATED);
    }
    @PutMapping("/{id}")
    public ResponseEntity<ReflectModel> update(@PathVariable("id") Long id, @RequestBody ReflectModel reflect) {
        return new ResponseEntity<>(service.update(id, reflect), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Boolean> destroy(@PathVariable("id") Long id) {
        return new ResponseEntity<>(service.delete(id), HttpStatus.OK);
    }
}
