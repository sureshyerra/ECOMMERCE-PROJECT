package com.example.Ecommerce.service.impl;

import com.example.Ecommerce.DTO.requestDto.ProductRequestDto;
import com.example.Ecommerce.DTO.responseDto.ProductResponseDto;
import com.example.Ecommerce.Enum.ProductCategory;
import com.example.Ecommerce.Enum.ProductStatus;
import com.example.Ecommerce.entity.Item;
import com.example.Ecommerce.entity.Ordered;
import com.example.Ecommerce.entity.Product;
import com.example.Ecommerce.entity.Seller;
import com.example.Ecommerce.exception.InvalidSellerException;
import com.example.Ecommerce.repository.ProductRepository;
import com.example.Ecommerce.repository.SellerRepository;
import com.example.Ecommerce.service.ProductService;
import com.example.Ecommerce.transformer.ProductTransformer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProductServiceimpl implements ProductService {
    @Autowired
    SellerRepository sellerRepository;


    @Autowired
    ProductRepository productRepository;
    @Override
    public ProductResponseDto addProduct(ProductRequestDto productRequestDto) throws InvalidSellerException {
        Seller seller;
        try{
             seller = sellerRepository.findById(productRequestDto.getSellerId()).get();
        }catch (Exception e){
            throw new InvalidSellerException("seller doesn't exist");
        }

        Product product = ProductTransformer.ProductRequestDtoToProduct(productRequestDto);
        product.setSeller(seller);
        // add product to current
        seller.getProducts().add(product);

        sellerRepository.save(seller);// saves both seller and product


        //prepare productresponse
        return ProductTransformer.ProductToPublicResponseDto(product);


    }

    @Override
    public List<ProductResponseDto> getAllProductsByCategory(ProductCategory category) {
        List<Product> products = productRepository.findByProductCategory(category);
        List<ProductResponseDto> productResponseDtos = new ArrayList<>();

        for(Product product : products){
            productResponseDtos.add(ProductTransformer.ProductToPublicResponseDto(product));
        }
        return productResponseDtos;
    }

    @Override
    public List<ProductResponseDto> getAllProductsByPriceAndCategory(int price, String category) {
        List<Product>products = productRepository.getAllProductsByPriceAndCategory(price,category);

        List<ProductResponseDto>productResponseDtos = new ArrayList<>();

        for (Product product : products){
            productResponseDtos.add(ProductTransformer.ProductToPublicResponseDto(product));
        }
        return productResponseDtos;
    }

    @Override
    public void decreaseProductQuantity(Item item) throws Exception {

            Product product = item.getProduct();

            int quantity = item.getRequiredQuantity();
            int currentQuantity = product.getQuantity();
            if(quantity>currentQuantity){
                throw new Exception("out of stock");
            }

            product.setQuantity(currentQuantity-quantity);
            if (product.getQuantity() == 0){
                product.setProductStatus(ProductStatus.OUT_OF_STOCK);
            }

    }
}

