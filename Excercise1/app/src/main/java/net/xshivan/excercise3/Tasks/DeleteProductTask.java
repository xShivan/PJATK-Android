package net.xshivan.excercise3.Tasks;

import android.os.AsyncTask;

import net.xshivan.excercise3.backend.productApi.ProductApi;

import java.io.IOException;

public class DeleteProductTask extends AsyncTask<Long, Void, Void> {
    private ProductApi productApiService;

    public DeleteProductTask() {
        productApiService = AsyncTaskHelpers.GetProductApi();
    }

    @Override
    protected Void doInBackground(Long... args) {
        long id = args[0];

        try {
            productApiService.deleteProduct(id).execute();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }
}
