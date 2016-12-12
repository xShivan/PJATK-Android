package net.xshivan.excercise3;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Pair;

import com.google.api.client.extensions.android.http.AndroidHttp;
import com.google.api.client.extensions.android.json.AndroidJsonFactory;
import com.google.api.client.googleapis.services.AbstractGoogleClientRequest;
import com.google.api.client.googleapis.services.GoogleClientRequestInitializer;

import net.xshivan.excercise3.Models.Product;
import net.xshivan.excercise3.backend.productApi.ProductApi;
import net.xshivan.excercise3.backend.productApi.model.ProductBean;

import java.io.IOException;

public class ProductEndpointsAsyncTask extends AsyncTask<Pair<Context, String>, Void, String> {
    private static ProductApi productApiService = null;
    private Context context;

    public ProductEndpointsAsyncTask() {
        if(productApiService == null) {  // Only do this once
            ProductApi.Builder builder = new ProductApi.Builder(AndroidHttp.newCompatibleTransport(),
                    new AndroidJsonFactory(), null)
                    // options for running against local devappserver
                    // - 10.0.2.2 is localhost's IP address in Android emulator
                    // - turn off compression when running against local devappserver
                    .setRootUrl("http://10.0.2.2:8080/_ah/api/")
                    .setGoogleClientRequestInitializer(new GoogleClientRequestInitializer() {
                        @Override
                        public void initialize(AbstractGoogleClientRequest<?> abstractGoogleClientRequest) throws IOException {
                            abstractGoogleClientRequest.setDisableGZipContent(true);
                        }
                    });
            // end options for devappserver

            productApiService = builder.build();
        }
    }

    @Override
    protected String doInBackground(Pair<Context, String>... params) {
        Pair<Context, String>[] a = params;

        context = params[0].first;
        String name = params[0].second;

        try {
            return productApiService.sayHi(name).execute().getData();
        } catch (IOException e) {
            return e.getMessage();
        }
    }

    protected Product putProduct(Product product) {
        //productApiService.addProduct()

        ProductBean productBean = new ProductBean();
        productBean.setId(product.getId());
        productBean.setName(product.name);
        productBean.setIsPurchased(product.isPurchased);

        try {
            ProductBean updatedProductBean = productApiService.addProduct(productBean).execute();
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

    @Override
    protected void onPostExecute(String result) {
        //Toast.makeText(context, result, Toast.LENGTH_LONG).show();
    }
}