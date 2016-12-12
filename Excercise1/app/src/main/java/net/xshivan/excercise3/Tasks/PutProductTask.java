package net.xshivan.excercise3.Tasks;

import android.os.AsyncTask;

import net.xshivan.excercise3.Models.Product;
import net.xshivan.excercise3.backend.productApi.ProductApi;
import net.xshivan.excercise3.backend.productApi.model.ProductBean;

import java.io.IOException;

public class PutProductTask extends AsyncTask<Product, Void, Product> {
    private ProductApi productApiService;
    private Product product;

    public PutProductTask() {
        productApiService = AsyncTaskHelpers.GetProductApi();
    }

    @Override
    protected Product doInBackground(Product... products) {
        product = products[0];

        ProductBean productBean = new ProductBean();
        productBean.setId(product.getId());
        productBean.setName(product.name);
        productBean.setIsPurchased(product.isPurchased);

        try {
            ProductBean updatedProductBean = productApiService.putProduct(productBean).execute();
            product.setId(updatedProductBean.getId());
            product.name = updatedProductBean.getName();
            product.isPurchased = updatedProductBean.getIsPurchased();

            return product;
        }
        catch (IOException e) {
            //throw e;
            return null;
        }
    }
}
