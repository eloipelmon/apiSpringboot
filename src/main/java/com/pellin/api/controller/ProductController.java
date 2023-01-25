package com.pellin.api.controller;

import com.pellin.api.model.Product;
import com.pellin.api.service.ProductService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequiredArgsConstructor
public class ProductController {
    private final ProductService productService;
    
    @GetMapping("/products")
    public List<Product> listProducts() {
        return productService.listProducts();
    }
    
    @GetMapping("/products/{id}")
    public ResponseEntity<Product> getProduct(@PathVariable Integer id){
        try{
            Product product =productService.getProductById(id);
            return ResponseEntity.ok(product);
        }catch(Exception e){
            return ResponseEntity.notFound().build();
        }
    }
    
    @PostMapping("/products")
    public void newProduct (@RequestBody Product product){
        productService.saveProduct(product);
    }
    
    @PutMapping("/products/{id}")
    public ResponseEntity<?> editProduct(@RequestBody Product product,@PathVariable Integer id){
        try{
            Product ExistingProduct =productService.getProductById(id);
            ExistingProduct.setName(product.getName());
            ExistingProduct.setPrice(product.getPrice());
            
            productService.saveProduct(ExistingProduct);
            return new ResponseEntity<Product>(product,HttpStatus.OK);
        }catch(Exception e){
            return new ResponseEntity<Product>(HttpStatus.NOT_FOUND);
        }
    }
    
    @DeleteMapping("/products/{id}")
    public void deleteProduct(@PathVariable Integer id){
        productService.deleteProduct(id);
    }
}