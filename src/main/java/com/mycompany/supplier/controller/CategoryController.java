package com.mycompany.supplier.controller;

import com.mycompany.supplier.dto.ErrorDTO;
import com.mycompany.supplier.entity.CategoryEntity;
import com.mycompany.supplier.exception.BusinessException;
import com.mycompany.supplier.repository.CategoryRepository;
import jakarta.validation.Valid;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.BeanWrapper;
import org.springframework.beans.BeanWrapperImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.beans.FeatureDescriptor;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/v1/categories")
public class CategoryController {

    @Autowired
    private CategoryRepository categoryRepository;

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/add")
    public ResponseEntity<CategoryEntity> addCategory(@Valid @RequestBody CategoryEntity category){
        category.setCreatedAt(LocalDateTime.now());
        category.setUpdatedAt(LocalDateTime.now());
        category = categoryRepository.save(category);
        return new ResponseEntity<>(category, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<CategoryEntity>> allCategories(){
        return new ResponseEntity<>(categoryRepository.findAll(), HttpStatus.OK);
    }

    @PutMapping("/{categoryId}")
    public ResponseEntity<CategoryEntity> updateCategory(@Valid @RequestBody CategoryEntity category, @PathVariable Long categoryId){
        Optional<CategoryEntity> optCe = categoryRepository.findById(categoryId);
        if(optCe.isPresent()){
            CategoryEntity categoryDb = optCe.get();
            BeanUtils.copyProperties(category, categoryDb, getNullPropertyNames(category));
            categoryDb.setCreatedAt(LocalDateTime.now());
            categoryDb.setUpdatedAt(LocalDateTime.now());
            categoryRepository.save(categoryDb);
            return new ResponseEntity<>(categoryDb, HttpStatus.OK);
        }
        else{
            throw new BusinessException(List.of( new ErrorDTO("CAT_NOT_FOUND", "The category to be updated not found")));
        }
    }

    private String[] getNullPropertyNames(Object source) {
        final BeanWrapper wrappedSource = new BeanWrapperImpl(source);
        return Stream.of(wrappedSource.getPropertyDescriptors()).map(FeatureDescriptor::getName)
                .filter(propertyName -> wrappedSource.getPropertyValue(propertyName) == null).toArray(String[]::new);
    }
}
