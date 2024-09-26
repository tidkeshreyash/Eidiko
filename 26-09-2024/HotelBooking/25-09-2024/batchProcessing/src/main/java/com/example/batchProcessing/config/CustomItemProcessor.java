package com.example.batchProcessing.config;

import com.example.batchProcessing.model.Product;
import org.springframework.batch.item.ItemProcessor;

public class CustomItemProcessor implements ItemProcessor<Product, Product> {
    @Override
    public Product process(Product item) throws Exception {
        //        transform ka logic yaha likha jayega
        //        calulate karenge discounted price ko
        //        original  price
       //        discount

        try {
//            put the percentage logic
            System.out.println(item.getDescription());
            int discountPer = Integer.parseInt(item.getDiscount());
            double originalPrice = Double.parseDouble(item.getPrice());
            double discount = ((double) discountPer / 100) * originalPrice;
            double finalPrice = originalPrice - discount;
            item.setDiscountedPrice(String.valueOf(finalPrice));
        } catch (
                NumberFormatException ex
        ) {
            ex.printStackTrace();
        }

        return item;
    }
}
