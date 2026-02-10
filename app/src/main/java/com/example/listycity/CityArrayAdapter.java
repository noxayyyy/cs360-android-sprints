package com.example.listycity;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckedTextView;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.ArrayList;

public class CityArrayAdapter extends ArrayAdapter<City> {
    private int resource_id;

    public CityArrayAdapter(Context context, int resource, ArrayList<City> cities) {
        super(context, resource, cities);
        resource_id = resource;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup
            parent) {
        View view;
        if (convertView == null) {
            view = LayoutInflater.from(getContext()).inflate(resource_id, parent, false);
        } else {
            view = convertView;
        }

        City city = getItem(position);
        TextView cityName = view.findViewById(R.id.city_text);
        TextView provinceName = view.findViewById(R.id.province_text);
        cityName.setText(city.get_name());
        provinceName.setText(city.get_province());

        if (cityName instanceof CheckedTextView && ((ListView) parent).getChoiceMode() == ListView.CHOICE_MODE_MULTIPLE) {
            ((CheckedTextView) cityName).setChecked(((ListView) parent).isItemChecked(position));
        }

        return view;
    }
}
