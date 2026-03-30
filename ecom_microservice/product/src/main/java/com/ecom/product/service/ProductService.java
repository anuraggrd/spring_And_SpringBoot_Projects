package com.ecom.product.service;

import com.ecom.product.dto.ProductRequest;
import com.ecom.product.dto.ProductResponse;
import com.ecom.product.entity.Product;
import com.ecom.product.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProductService {
    private final ProductRepository productDb;

    public ProductResponse addProduct(ProductRequest productreq) {
        Product product = new Product();
        mapToEntity(productreq, product);
        Product save = productDb.save(product);
        return mapToResponse(save);
        //return save !=null ?true:false;
    }

    private static void mapToEntity(ProductRequest request, Product entity) {
        entity.setName(request.getName());
        entity.setDescription(request.getDescription());
        entity.setPrice(request.getPrice());
        entity.setStockQuantity(request.getStockQuantity());
        entity.setCategory(request.getCategory());
        entity.setImageUrl(request.getImageUrl());
    }

    private static ProductResponse mapToResponse(Product entity) {
        ProductResponse response = new ProductResponse();
        response.setName(entity.getName());
        response.setDescription(entity.getDescription());
        response.setPrice(entity.getPrice());
        response.setStockQuantity(entity.getStockQuantity());
        response.setCategory(entity.getCategory());
        response.setImageUrl(entity.getImageUrl());
        response.setActive(entity.getActive());
        return response;
    }

    public ProductResponse updateProduct(long id, ProductRequest updatedproduct) {
      return  productDb.findById(id).map(existingproduct -> {
            mapToEntity(updatedproduct, existingproduct);
            productDb.save(existingproduct);
            return mapToResponse(existingproduct);
        }).orElse(null);
    }
    public List<ProductResponse> getAllPRoducts(){
        return productDb.findByActiveTrue().stream().map(ProductService::mapToResponse)
                .collect(Collectors.toList());
    }

    public List<ProductResponse> searchProduct(String keyword) {
        return productDb.searchProductBykeyword(keyword).stream().map(ProductService::mapToResponse)
                .collect(Collectors.toList());

    }

    public boolean removeProduct(long id) {
        return  productDb.findById(id).map(existingproduct -> {
            existingproduct.setActive(false);
            productDb.save(existingproduct);
            return true;
        }).orElse(false);
    }
}
