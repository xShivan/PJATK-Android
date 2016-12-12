package net.xshivan.excercise3;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.InputType;
import android.util.Pair;
import android.view.View;
import android.widget.EditText;
import android.widget.ListView;

import net.xshivan.excercise3.Adapters.ProductListViewAdapter;
import net.xshivan.excercise3.Adapters.ProductListViewItemColumns;
import net.xshivan.excercise3.Models.Product;
import net.xshivan.excercise3.Tasks.GetProductsTask;
import net.xshivan.excercise3.Tasks.PutProductTask;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.ExecutionException;

public class ProductListActivity extends AppCompatActivity {

    private ArrayList<HashMap> productList = new ArrayList<HashMap>();

    private ProductListViewAdapter productListViewAdapter = new ProductListViewAdapter(this, productList);

    public void handleBtnAddProductClick(View view) {
        final EditText input = new EditText(this);
        input.setInputType(InputType.TYPE_CLASS_TEXT);

        new AlertDialog.Builder(this)
            .setMessage("Type in your product:")
            .setView(input)
            .setCancelable(true)
            .setPositiveButton("Add", new OnClickListener() {
                public void onClick(DialogInterface dialog, int id) {
                    String product = input.getText().toString();
                    addProduct(product);
                    dialog.cancel();
                }
            })
            .setNegativeButton("Cancel", new OnClickListener() {
                public void onClick(DialogInterface dialog, int id) {
                    dialog.cancel();
                }
            })
            .create().show();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_product_list);

        getInitialProductList();
        ListView listViewProducts = (ListView) findViewById(R.id.listViewProducts);

        listViewProducts.setAdapter(productListViewAdapter);
        productListViewAdapter.notifyDataSetChanged();

        ApplicationSettings applicationSettings = SharedPreferencesAccess.loadPreferences(this);
        if (applicationSettings.navigateToProductsOnStart && applicationSettings.promptToSeeWebsite)
            AuthorWebsiteNavigator.promptToVisit(this);
    }

    private void getInitialProductList() {
        List<Product> products;

        try {
            GetProductsTask getProductsTask = new GetProductsTask();
            products = getProductsTask.execute().get();
        } catch (InterruptedException e) {
            e.printStackTrace();
            return;
        } catch (ExecutionException e) {
            e.printStackTrace();
            return;
        }

        for (Product productHashMap : products)
            productList.add(productHashMap.toHashMap());
    }

    private void addProduct(String product) {
        HashMap temp = new HashMap();
        temp.put(ProductListViewItemColumns.COLUMN_PRODUCT_NAME, product);
        temp.put(ProductListViewItemColumns.COLUMN_IS_PURCHASED, false);
        productList.add(temp);
        productListViewAdapter.notifyDataSetChanged();
        DataAccess.saveProducts(productList);

        PutProductTask putProductTask = new PutProductTask();
        putProductTask.execute(Product.fromHashMap(temp));

    }
}
