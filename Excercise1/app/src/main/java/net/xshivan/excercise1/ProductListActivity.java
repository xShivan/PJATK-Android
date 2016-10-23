package net.xshivan.excercise1;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class ProductListActivity extends AppCompatActivity {

    private List<String> productList = new ArrayList<String>();

    public void handleBtnAddProductClick(View view) {
        final EditText input = new EditText(this);
        input.setInputType(InputType.TYPE_CLASS_TEXT);


        new AlertDialog.Builder(this)
            .setMessage("Type in your product:")
            .setView(input)
            .setCancelable(true)
            .setPositiveButton("Add", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int id) {
                    String product = input.getText().toString();
                    addProduct(product);
                    dialog.cancel();
                }
            })
            .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int id) {
                    dialog.cancel();
                }
            })
            .create().show();
    }

    public void removeProduct() {

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_list);
    }

    private void addProduct(String product) {
        ListView listView = getProductListView();
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this, R.layout.product_list_item, productList);
        listView.setAdapter(arrayAdapter);

        arrayAdapter.add(product);
        arrayAdapter.notifyDataSetChanged();
    }

    private ListView getProductListView() {
        return (ListView) findViewById(R.id.listViewProducts);
    }
}
