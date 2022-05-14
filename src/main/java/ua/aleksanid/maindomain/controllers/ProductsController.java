package ua.aleksanid.maindomain.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ua.aleksanid.maindomain.models.Product;

import java.util.Collections;
import java.util.List;

@RestController
@RequestMapping("/product")
public class ProductsController {

    @GetMapping("/")
    public List<Product> getProducts(){
        return Collections.singletonList(new Product(1L,"Test","Test product"));
    }
}
