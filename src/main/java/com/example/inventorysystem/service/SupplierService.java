
package com.example.inventorysystem.service;

import com.example.inventorysystem.dto.SupplierDTO;
import com.example.inventorysystem.entity.Supplier;
import com.example.inventorysystem.repository.SupplierRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SupplierService {

    private final SupplierRepository supplierRepository;

    public SupplierService(SupplierRepository supplierRepository) {
        this.supplierRepository = supplierRepository;
    }

    public SupplierDTO addSupplier(SupplierDTO dto) {
        Supplier supplier = new Supplier(
                dto.name(),
                dto.contact(),
                dto.location()
        );

        return convertToDTO(supplierRepository.save(supplier));
    }

    public List<SupplierDTO> getAllSuppliers() {
        return supplierRepository.findAll()
                .stream()
                .map(this::convertToDTO)
                .toList();
    }

    public SupplierDTO getSupplierById(Long id) {
        return convertToDTO(supplierRepository.findById(id).get());
    }

    public SupplierDTO updateSupplier(Long id, SupplierDTO dto) {
        Supplier supplier = supplierRepository.findById(id).get();

        supplier.setName(dto.name());
        supplier.setContact(dto.contact());
        supplier.setLocation(dto.location());

        return convertToDTO(supplierRepository.save(supplier));
    }

    public void deleteSupplier(Long id) {
        supplierRepository.deleteById(id);
    }

    private SupplierDTO convertToDTO(Supplier supplier) {
        return new SupplierDTO(
                supplier.getId(),
                supplier.getName(),
                supplier.getContact(),
                supplier.getLocation()
        );
    }
}
