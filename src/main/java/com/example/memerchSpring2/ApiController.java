package com.example.memerchSpring2;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ApiController {

  private final ProductService productService;
  private final ClientService clientService;

  public ApiController(ProductService productService, ClientService clientService) {
    this.productService = productService;
    this.clientService = clientService;
  }

  @GetMapping("/api/products")
  public List<ProductEntity> allProducts() {
    return productService.getProducts();
  }

  @GetMapping("/api/clients")
  public List<ClientEntity> allClients() {
    return clientService.getClients();
  }

  @PostMapping("/api/products")
  public ProductEntity newProduct(@RequestBody NewProduct newProduct) {
    return productService.create(newProduct);
  }

  @PostMapping("/api/clients")
  public ClientEntity newClient(@RequestBody NewClient newClient) {
    return clientService.create(newClient);
  }
}