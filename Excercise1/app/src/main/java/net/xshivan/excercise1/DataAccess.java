package net.xshivan.excercise1;

import net.xshivan.excercise1.Models.Product;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class DataAccess {

    public static void saveProducts(List<HashMap> hashMapProducts) {
        Product.deleteAll(Product.class);

        for (HashMap hashMapProduct : hashMapProducts) {
            Product product = Product.fromHashMap(hashMapProduct);
            product.save();
        }
    }

    public static List<HashMap> getProducts() {
        List<Product> products = Product.listAll(Product.class);
        List<HashMap> productsHashMaps = new ArrayList<>();
        for (Product product : products)
            productsHashMaps.add(product.toHashMap());

        return productsHashMaps;
    }
}
