package com.ecom.product.controller;


import com.ecom.product.dto.ProductRequest;
import com.ecom.product.dto.ProductResponse;
import com.ecom.product.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import lombok.extern.slf4j.Slf4j;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/product")
@Slf4j
public class ProductController {
    private final ProductService service;
    @PostMapping
    public ResponseEntity<ProductResponse> addProduct(@RequestBody ProductRequest product){
        ProductResponse productResponse  = service.addProduct(product);
        return new ResponseEntity<>(productResponse , HttpStatus.CREATED);

    }

    @PutMapping("/{id}")
    public ResponseEntity<ProductResponse> updateProduct(@PathVariable long id, @RequestBody ProductRequest product){
        ProductResponse productResponse  = service.updateProduct(id,product);
        ResponseEntity<ProductResponse> productResponseResponseEntity = productResponse != null ?
                new ResponseEntity<>(productResponse, HttpStatus.CREATED) :
                ResponseEntity.notFound().build();
        return productResponseResponseEntity;

    }

    @GetMapping
    public ResponseEntity<List<ProductResponse>> getProducts(){
        return ResponseEntity.ok(service.getAllPRoducts());
    }

    @GetMapping("/search")
    public ResponseEntity<List<ProductResponse>> searchProducts(@RequestParam String keyword){
        List<ProductResponse> allPRoducts = service.searchProduct(keyword);
        return allPRoducts.size()!=0 ?  ResponseEntity.ok(allPRoducts): ResponseEntity.notFound().build();

    }
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteProduct(@PathVariable Long id ){
        boolean isdeleted = service.removeProduct( id);
        return isdeleted ?  ResponseEntity.ok("Product deleted"): ResponseEntity.notFound().build();

    }
    @GetMapping("/{id}")
    public  ResponseEntity<ProductResponse> getproductDetails(@PathVariable Long id){
        log.info("Id {}", id);
        log.info("product ->productId :-" + id);
        ProductResponse   productResponse =  service.getProductById(id);
        log.info("product details {}", productResponse);
        ResponseEntity<ProductResponse> productResponseResponseEntity=  productResponse != null ?
                new ResponseEntity<>(productResponse, HttpStatus.CREATED) :
                ResponseEntity.notFound().build();
      return productResponseResponseEntity;

    }

}
