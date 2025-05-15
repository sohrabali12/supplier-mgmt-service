package com.mycompany.supplier.service.impl;

import com.mycompany.supplier.dto.ErrorDTO;
import com.mycompany.supplier.entity.CategoryEntity;
import com.mycompany.supplier.entity.SupplierEntity;
import com.mycompany.supplier.exception.BusinessException;
import com.mycompany.supplier.repository.SupplierRepository;
import com.mycompany.supplier.service.SupplierService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.BeanWrapper;
import org.springframework.beans.BeanWrapperImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.beans.FeatureDescriptor;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

@Service
public class SupplierServiceImpl implements SupplierService {

    @Autowired
    private SupplierRepository repository;

    @Override
    public List<SupplierEntity> getSuppliersByCategory(String categoryName) {
        return repository.findByCategory_Name(categoryName);
    }

    @Override
    public ResponseEntity<SupplierEntity> updateSupplier(SupplierEntity supplier) {
        Optional<SupplierEntity> optnById = repository.findById(supplier.getId());
        if(optnById.isPresent()){
            SupplierEntity supplierDb = optnById.get();
            BeanUtils.copyProperties(supplier, supplierDb, getNullPropertyNames(supplier));
            supplierDb.setName(supplier.getName());
            supplierDb.setEmail(supplier.getEmail());
            repository.save(supplierDb);
            return new ResponseEntity<>(supplierDb, HttpStatus.OK);
        }
        else{
            throw new BusinessException(List.of( new ErrorDTO("SUPPLIER_NOT_FOUND", "The supplier to be updated not found")));
        }
    }

    @Override
    public ResponseEntity<SupplierEntity> addSupplier(SupplierEntity supplier) {
        return new ResponseEntity<>(repository.save(supplier), HttpStatus.CREATED);
    }

    private String[] getNullPropertyNames(Object source) {
        final BeanWrapper wrappedSource = new BeanWrapperImpl(source);
        return Stream.of(wrappedSource.getPropertyDescriptors()).map(FeatureDescriptor::getName)
                .filter(propertyName -> wrappedSource.getPropertyValue(propertyName) == null).toArray(String[]::new);
    }
}