package net.xshivan.excercise1.Adapters;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;

import net.xshivan.excercise1.R;

import java.util.ArrayList;
import java.util.HashMap;

public class ProductListViewAdapter extends BaseAdapter {

    private Activity activity;

    public ArrayList<HashMap> list;

    public ProductListViewAdapter(Activity activity, ArrayList<HashMap> list) {
        super();

        this.activity = activity;
        this.list = list;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int i) {
        return list.get(i);
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup viewGroup) {
        final int internalPostion = position;
        final ProductListItemViewModel productListItemViewModel;
        LayoutInflater inflater =  activity.getLayoutInflater();

        if (convertView == null)
        {
            convertView = inflater.inflate(R.layout.product_list_item, null);
            productListItemViewModel = new ProductListItemViewModel();
            productListItemViewModel.textViewProductName = (TextView) convertView.findViewById(R.id.textViewProductName);
            productListItemViewModel.checkBoxIsPurchased = (CheckBox) convertView.findViewById(R.id.checkBoxIsPurchased);
            productListItemViewModel.buttonDeleteProduct = (Button) convertView.findViewById(R.id.buttonDeleteProduct);
            convertView.setTag(productListItemViewModel);
        }
        else
            productListItemViewModel = (ProductListItemViewModel) convertView.getTag();

        final HashMap map = list.get(position);

        final ProductListViewAdapter instance = this;
        productListItemViewModel.buttonDeleteProduct.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                list.remove(internalPostion);
                instance.notifyDataSetChanged();
            }
        });

        productListItemViewModel.textViewProductName.setText((String) map.get(ProductListViewItemColumns.COLUMN_PRODUCT_NAME));

        productListItemViewModel.checkBoxIsPurchased.setChecked((Boolean) map.get(ProductListViewItemColumns.COLUMN_IS_PURCHASED));
        productListItemViewModel.checkBoxIsPurchased.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
            map.put(ProductListViewItemColumns.COLUMN_IS_PURCHASED, b);
            }
        });

        return convertView;
    }

    private class ProductListItemViewModel {
        public CheckBox checkBoxIsPurchased;
        public TextView textViewProductName;
        public Button buttonDeleteProduct;
    }
}
