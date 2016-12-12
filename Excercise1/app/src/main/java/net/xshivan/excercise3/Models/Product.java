package net.xshivan.excercise3.Models;

import com.orm.SugarRecord;

import net.xshivan.excercise3.Adapters.ProductListViewItemColumns;

import java.util.HashMap;

public class Product {
    private long id;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String name;

    public Boolean isPurchased;

    public Product() { }

    public Product(String name, Boolean isPurchased) {
        this.id = 0;
        this.name = name;
        this.isPurchased = isPurchased;

    }

    public static Product fromHashMap(HashMap hashMap) {
        String name = (String)hashMap.get(ProductListViewItemColumns.COLUMN_PRODUCT_NAME);
        Boolean isPurchased = (Boolean)hashMap.get(ProductListViewItemColumns.COLUMN_IS_PURCHASED);

        Product product = new Product(name, isPurchased);
        return product;
    }

    public HashMap toHashMap() {
        HashMap hashMap = new HashMap();
        hashMap.put(ProductListViewItemColumns.COLUMN_PRODUCT_NAME, name);
        hashMap.put(ProductListViewItemColumns.COLUMN_IS_PURCHASED, isPurchased);
        hashMap.put(ProductListViewItemColumns.COLUMN_ID, getId());
        return hashMap;
    }
}
