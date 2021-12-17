package workout.workoutapp.transport.converter;

import workout.workoutapp.database.entities.Products;
import workout.workoutapp.transport.dto.ProductsDto;

public class ProductsConverter {

    public static Products toEntity(ProductsDto productsDto){
        return new Products(productsDto.getProductName(), productsDto.getKcal(), productsDto.getProtein(), productsDto.getFat(), productsDto.getCarbohydrate());
    }

    public static ProductsDto toDto(Products products){
        return new ProductsDto(products.getProductName(), products.getKcal(), products.getProtein(), products.getFat(), products.getCarbohydrate());
    }
}
