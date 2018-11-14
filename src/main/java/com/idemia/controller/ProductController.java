/**
 * 
 */
package com.idemia.controller;



import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.idemia.model.Product;

/**
 * @author G521980
 *
 */

@RestController
@RequestMapping("/redis")
public class ProductController {

	private static final String REDIS_INDEX_KEY="PRODUCT";
	
	@Autowired
	RedisTemplate<String, Object> redistemplate;
	
	 // @Resource(name="redisTemplate")
	 /* private HashOperations hashOps;	
	  
	  ProductController(){
		  hashOps=redistemplate.opsForHash();
	  }*/
	
	
	@RequestMapping(value="/product", method=RequestMethod.POST)
	public String createProduct(@RequestBody Product product) {
		
		redistemplate.opsForHash().put(REDIS_INDEX_KEY, product.getProduct_id(), product.toString());
		return "Product is saved successfully";
	}
	
	
	
	@RequestMapping(value="products",method=RequestMethod.GET)
	public ResponseEntity<Object> getProducts(){
		
		return  new ResponseEntity<>(redistemplate.opsForHash().entries(REDIS_INDEX_KEY),HttpStatus.OK);
	}
	
	@RequestMapping(value="products/{product_id}" ,method=RequestMethod.GET)
	public String getProduct(@PathVariable("product_id") String product_id) {
		
		return  (String) redistemplate.opsForHash().get(REDIS_INDEX_KEY,product_id);
		
	}
	
}
