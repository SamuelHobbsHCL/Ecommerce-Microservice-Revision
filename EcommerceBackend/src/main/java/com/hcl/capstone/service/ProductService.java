package com.hcl.capstone.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import com.hcl.capstone.dto.ProductDto;
import com.hcl.capstone.model.Category;
import com.hcl.capstone.model.Product;
import com.hcl.capstone.repository.CategoryRepository;
import com.hcl.capstone.repository.ProductRepository;

import reactor.core.publisher.Mono;

@Service
public class ProductService {
	
	@Autowired
	private ProductRepository productRepository;
	
	@Autowired
	private CategoryRepository categoryRepository;
	
	private final WebClient localClient = WebClient.create("http://localhost:8081");
	
	
	//@Autowired
	//ProductService(WebClient localClient) {
	//	this.localClient = localClient;
	//}
	
	// ================= PRODUCT CONTROLLER =================
	
	// Fetch all products (call to /api/add-product not initially used by FE - admin call was)
	@SuppressWarnings("unchecked")
	public List<Product> getAllProducts() {
		//return productRepository.findAll();
		return localClient
				.get()
				.uri("/api/products")
				//.headers(h -> h.setBearerAuth("PLACEHOLDER"))
		        .retrieve()
		        .bodyToMono(List.class)
		        .block();
	}
	
	public Product getProductById(long id) {
		//return productRepository.findById(id);
		return localClient
				.get()
				.uri("/api/product/{id}",id)
				//.headers(h -> h.setBearerAuth("PLACEHOLDER"))
		        .retrieve()
		        .bodyToMono(Product.class)
		        .block();
	}
		
	// Used by searchProductsPages
	@SuppressWarnings("unchecked")
	public List<Product> searchProducts(String searchStr, int index, int count) {
		Pageable pageable = PageRequest.of(index, count);
		// Returns top <count> results starting from <index>
		//return productRepository.findAllByProductNameContaining(searchStr,pageable);
		return localClient
				.get()
				.uri(uriBuilder -> uriBuilder
						.path("/api/product/search/page")
						.queryParam("searchStr",searchStr)
						.queryParam("index",index)
						.queryParam("count", count)
						.build())
				//.headers(h -> h.setBearerAuth("PLACEHOLDER"))
				.retrieve()
				.bodyToMono(List.class)
				.block();
	}
		
	@SuppressWarnings("unchecked")
	public List<Product> searchProducts(String searchStr) {
		//return productRepository.findAllByProductNameContaining(searchStr);
		return localClient
				.get()
				.uri(uriBuilder -> uriBuilder
						.path("/api/product/search")
						.queryParam("searchStr",searchStr)
						.build())
				//.headers(h -> h.setBearerAuth("PLACEHOLDER"))
				.retrieve()
				.bodyToMono(List.class)
				.block();
	}

	@SuppressWarnings("unchecked")
	public List<Category> getProductCategories() {
		//return categoryRepository.findAll();
		return localClient
				.get()
				.uri("/api/product/categories")
				//.headers(h -> h.setBearerAuth("PLACEHOLDER"))
				.retrieve()
				.bodyToMono(List.class)
				.block();
	}
	
	public Product updateProductImage(long productId, String imageUrl) {
		/*
		Product product = productRepository.findById(productId);				
		
		if(product == null) {
			return null;
		} else {
			product.setProductImage(imageUrl);
			return productRepository.save(product);
		}
		*/
		return localClient
				.put()
				.uri(uriBuilder -> uriBuilder
						.path("/api/product/update-image/"+productId)
						.queryParam("imageUrl",imageUrl)
						.build())
				//.headers(h -> h.setBearerAuth("PLACEHOLDER"))
				.retrieve()
				.bodyToMono(Product.class)
				.block();
	}
	
	// ==================== ADMIN CONTROLLER ====================

	// getAllProducts is excluded - non-admin version is reused above
	
	// Post to admin's addProduct
	public Product addProduct(Product product, String authHeader) {
		//return productRepository.save(product);
		String bearerToken;
		if (!authHeader.startsWith("Bearer "))
			return null;
		bearerToken = authHeader.substring(7,authHeader.length());
		
		return localClient
				.post()
				.uri("/admin/add-product")
				.headers(h -> h.setBearerAuth(bearerToken))
                .contentType(MediaType.APPLICATION_JSON)
                .body(Mono.just(product),Product.class)
                .retrieve()
                .bodyToMono(Product.class)
                .block();				
	}
	
	public Product deleteProductById(long id, String authHeader) {
		//productRepository.deleteById(id);
		String bearerToken;
		if (!authHeader.startsWith("Bearer "))
			return null;
		bearerToken = authHeader.substring(7,authHeader.length());
		return localClient
			.delete()
			.uri("/admin/delete-product/{id}",id)
			.headers(h -> h.setBearerAuth(bearerToken))
			.retrieve()
            .bodyToMono(Product.class)
            .block();
	}
	
	public Product updateProduct(ProductDto productDTO, String authHeader) {		
		/*
		Optional<Product> productRepo = Optional.ofNullable(productRepository.findById(productDTO.getProductIdDto()));
		
		if(!productRepo.isPresent()) {
			return null;
		}
		
		Product update = new Product(productDTO);
		
		productRepository.save(update);
		
		return productRepository.findById(productDTO.getProductIdDto());
		*/
		String bearerToken;
		if (!authHeader.startsWith("Bearer "))
			return null;
		bearerToken = authHeader.substring(7,authHeader.length());
		return localClient
				.put()
				.uri("/admin/product")
                .contentType(MediaType.APPLICATION_JSON)
                .body(Mono.just(productDTO),ProductDto.class)
				.headers(h -> h.setBearerAuth(bearerToken))
				.retrieve()
				.bodyToMono(Product.class)
				.block();
	}
	
	// =================== OTHER ====================
	// Called by OrderService
	public Product updateProductStock(Product product) {
		//return productRepository.save(product);
		return localClient
				.put()
				.uri(uriBuilder -> uriBuilder
					.path("/api/product/update-stock/"+product.getProductId())
					.queryParam("stock",product.getProductStock())
					.build())
	            .retrieve()
	            .bodyToMono(Product.class)
	            .block();
	}

}
