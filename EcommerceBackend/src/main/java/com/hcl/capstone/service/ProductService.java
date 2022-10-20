package com.hcl.capstone.service;

import java.util.List;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import com.hcl.capstone.dto.ProductDto;
import com.hcl.capstone.model.Category;
import com.hcl.capstone.model.Product;
import reactor.core.publisher.Mono;

@Service
public class ProductService {	
	private final WebClient localClient = WebClient.create("http://localhost:8081");
	
	// ================= PRODUCT CONTROLLER =================
	
	// Fetch all products (call to /api/add-product not initially used by FE - admin call was)
	@SuppressWarnings("unchecked")
	public List<Product> getAllProducts() {
		return localClient
				.get()
				.uri("/api/products")
		        .retrieve()
		        .bodyToMono(List.class)
		        .block();
	}
	
	public Product getProductById(long id) {
		return localClient
				.get()
				.uri("/api/product/{id}",id)
		        .retrieve()
		        .bodyToMono(Product.class)
		        .block();
	}
		
	// Used by searchProductsPages
	@SuppressWarnings("unchecked")
	public List<Product> searchProducts(String searchStr, int index, int count) {
		Pageable pageable = PageRequest.of(index, count);
		// Returns top <count> results starting from <index>
		return localClient
				.get()
				.uri(uriBuilder -> uriBuilder
						.path("/api/product/search/page")
						.queryParam("searchStr",searchStr)
						.queryParam("index",index)
						.queryParam("count", count)
						.build())
				.retrieve()
				.bodyToMono(List.class)
				.block();
	}
		
	@SuppressWarnings("unchecked")
	public List<Product> searchProducts(String searchStr) {
		return localClient
				.get()
				.uri(uriBuilder -> uriBuilder
						.path("/api/product/search")
						.queryParam("searchStr",searchStr)
						.build())
				.retrieve()
				.bodyToMono(List.class)
				.block();
	}

	@SuppressWarnings("unchecked")
	public List<Category> getProductCategories() {
		return localClient
				.get()
				.uri("/api/product/categories")
				.retrieve()
				.bodyToMono(List.class)
				.block();
	}
	
	public Product updateProductImage(long productId, String imageUrl) {
		return localClient
				.put()
				.uri(uriBuilder -> uriBuilder
						.path("/api/product/update-image/"+productId)
						.queryParam("imageUrl",imageUrl)
						.build())
				.retrieve()
				.bodyToMono(Product.class)
				.block();
	}
	
	// ==================== ADMIN CONTROLLER ====================

	// getAllProducts is excluded - non-admin version is reused above
	
	// Post to admin's addProduct
	public Product addProduct(Product product, String authHeader) {
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
