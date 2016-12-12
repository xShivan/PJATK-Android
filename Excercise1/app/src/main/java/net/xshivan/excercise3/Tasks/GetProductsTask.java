package net.xshivan.excercise3.Tasks;

import android.os.AsyncTask;

import net.xshivan.excercise3.Models.Product;
import net.xshivan.excercise3.backend.productApi.ProductApi;
import net.xshivan.excercise3.backend.productApi.model.ProductBean;
import net.xshivan.excercise3.backend.productApi.model.ProductBeanCollection;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class GetProductsTask extends AsyncTask<Void, Void, List<Product>> {
    private ProductApi productApiService;

    public GetProductsTask() {
        productApiService = AsyncTaskHelpers.GetProductApi();
    }

    @Override
    protected List<Product> doInBackground(Void... voids) {
        ProductBeanCollection products;
        try {
            products = productApiService.fetchProducts().execute();
        } catch (IOException e) {
            return null;
        }

        ArrayList<Product> productList = new ArrayList<>();
        for (ProductBean productBean : products.getItems()) {
            Product product = new Product();
            product.setId(productBean.getId());
            product.name = productBean.getName();
            product.isPurchased = productBean.getIsPurchased();
            productList.add(product);
        }

        return productList;
    }
}
