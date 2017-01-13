package net.xshivan.excercise4;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;

public class PlacesActivity extends AppCompatActivity {

    public void handleDeletePlacesClick(View view) {
        CoordinateManager.clearAll(getApplicationContext());
        initializePlaceList();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_places);
        initializePlaceList();
    }

    private void initializePlaceList() {
        PlaceListViewAdapter adapter = new PlaceListViewAdapter(this, CoordinateManager.getCoordinates());
        ListView listViewProducts = (ListView) findViewById(R.id.listViewPlaces);
        listViewProducts.setAdapter(adapter);
        adapter.notifyDataSetChanged();
    }
}
