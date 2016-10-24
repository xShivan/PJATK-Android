package net.xshivan.excercise1;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.InputType;
import android.view.View;
import android.widget.EditText;
import android.widget.ListView;

import net.xshivan.excercise1.Adapters.ProductListViewAdapter;
import net.xshivan.excercise1.Adapters.ProductListViewItemColumns;

import java.util.ArrayList;
import java.util.HashMap;

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
    }

    private void getInitialProductList() {
        for (HashMap productHashMap : DataAccess.getProducts())
            productList.add(productHashMap);
    }

    private void addProduct(String product) {
        HashMap temp = new HashMap();
        temp.put(ProductListViewItemColumns.COLUMN_PRODUCT_NAME, product);
        temp.put(ProductListViewItemColumns.COLUMN_IS_PURCHASED, false);
        productList.add(temp);
        productListViewAdapter.notifyDataSetChanged();
        DataAccess.saveProducts(productList);
    }
}
