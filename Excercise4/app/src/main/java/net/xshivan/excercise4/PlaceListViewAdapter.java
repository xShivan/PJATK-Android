package net.xshivan.excercise4;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class PlaceListViewAdapter extends BaseAdapter {

    private Activity activity;

    public ArrayList<Coordinates> list;

    public PlaceListViewAdapter(Activity activity, ArrayList<Coordinates> list) {
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
        final PlaceListItemViewModel productListItemViewModel;
        LayoutInflater inflater =  activity.getLayoutInflater();

        if (convertView == null)
        {
            convertView = inflater.inflate(R.layout.place_list_item, null);
            productListItemViewModel = new PlaceListItemViewModel();
            productListItemViewModel.textViewPlaceName = (TextView) convertView.findViewById(R.id.textViewPlaceName);
            productListItemViewModel.textViewPlaceDescription = (TextView) convertView.findViewById(R.id.textViewPlaceDescription);
            convertView.setTag(productListItemViewModel);
        }
        else
            productListItemViewModel = (PlaceListItemViewModel) convertView.getTag();

        final Coordinates coordinates = list.get(position);

        productListItemViewModel.textViewPlaceName.setText(coordinates.name + " (" + String.format("%.2f",coordinates.latitude) + ", " + String.format("%.2f", coordinates.longitude) + ")");
        productListItemViewModel.textViewPlaceDescription.setText(coordinates.description);

        return convertView;
    }

    private class PlaceListItemViewModel {
        public TextView textViewPlaceName;
        public TextView textViewPlaceDescription;
    }
}
