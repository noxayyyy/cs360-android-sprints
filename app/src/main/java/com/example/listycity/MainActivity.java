package com.example.listycity;

import android.os.Bundle;
import android.util.Log;
import android.util.SparseBooleanArray;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.WriteBatch;

import java.util.ArrayList;
import java.util.Locale;

public class MainActivity extends AppCompatActivity implements EditCityFragment.EditCityDialogListener {
    FirebaseFirestore db;
    CollectionReference cities_ref;
    ListView city_list;
    ArrayAdapter<City> city_adp;
    ArrayList<City> data_list;
    Button add_city_btn;
    Button rmv_city_btn;
    LinearLayout confirm_add_view;
    Button confirm_add_btn;
    EditText city_name;
    EditText province_name;
    LinearLayout confirm_rmv_view;
    Button confirm_rmv_btn;
    TextView selected_count;
    int selected_position;

    @Override
    public void editCity(City city) {
        data_list.set(selected_position, city);
        city_adp.notifyDataSetChanged();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        city_list = findViewById(R.id.city_list);
        add_city_btn = findViewById(R.id.add_city_btn);
        rmv_city_btn = findViewById(R.id.rmv_city_btn);

        confirm_add_view = findViewById(R.id.confirm_add_view);
        confirm_add_btn = confirm_add_view.findViewById(R.id.confirm_add_btn);
        city_name = confirm_add_view.findViewById(R.id.city_name);
        province_name = confirm_add_view.findViewById(R.id.province_name);

        confirm_rmv_view = findViewById(R.id.confirm_rmv_view);
        confirm_rmv_btn = confirm_rmv_view.findViewById(R.id.confirm_rmv_btn);
        selected_count = confirm_rmv_view.findViewById(R.id.selected_count);


//        String[] cities = {"Edmonton", "Vancouver", "Toronto"};
//        String[] provinces = {"AB", "BC", "ON"};

        data_list = new ArrayList<City>();
        city_adp = new CityArrayAdapter(this, R.layout.content, data_list);
        city_list.setAdapter(city_adp);

        city_list.setOnItemClickListener((a, b, pos, d) -> {
            selected_count.setText(String.format(Locale.ROOT, "Selected: %d", city_list.getCheckedItemCount()));
            if (city_list.getChoiceMode() != ListView.CHOICE_MODE_MULTIPLE) {
                selected_position = pos;
                EditCityFragment.newInstance(data_list.get(pos)).show(getSupportFragmentManager(), "EDIT_CITY");
            }
            city_adp.notifyDataSetChanged();
        });

        db = FirebaseFirestore.getInstance();
        cities_ref = db.collection("cities");

        cities_ref.addSnapshotListener(this, (value, error) -> {
            if (error != null) {
                Log.e("Firestore", error.toString());
                return;
            }
            if (value == null) return;

            data_list.clear();
            for (QueryDocumentSnapshot snapshot : value) {
                data_list.add(new City(snapshot.getString("_name"), snapshot.getString("_province")));
            }
            city_adp.notifyDataSetChanged();
        });

        add_city_btn.setOnClickListener((View v) -> enableAddChoice());
        confirm_add_btn.setOnClickListener((View v) -> {
            City city = new City(city_name.getText().toString(), province_name.getText().toString());

            cities_ref.document(city.get_name()).set(city).addOnFailureListener((error) -> {
                Log.w("Firestore", error.toString());
            });
            disableAddChoice();
        });

        rmv_city_btn.setOnClickListener((View v) -> enableRmvChoice());
        confirm_rmv_btn.setOnClickListener((View v) -> {
            SparseBooleanArray check_selected = city_list.getCheckedItemPositions();
            deleteCheckedItems(check_selected);
            city_adp.notifyDataSetChanged();
            disableRmvChoice();
        });
    }

    private void deleteCheckedItems(SparseBooleanArray check_selected) {
        WriteBatch batch = db.batch();
        for (int i = 0; i < data_list.size(); ++i) {
            if (!check_selected.get(i)) continue;
            batch.delete(cities_ref.document(data_list.get(i).get_name()));
        }

        batch.commit().addOnFailureListener((error) -> {
            Log.w("Firestore", error.toString());
        });
    }

    private void enableAddChoice() {
        disableRmvChoice();
        confirm_add_view.setVisibility(View.VISIBLE);
    }

    private void enableRmvChoice() {
        disableAddChoice();
        city_adp = new CityArrayAdapter(this, R.layout.list_item_rmv_mode, data_list);
        city_list.setAdapter(city_adp);
        city_list.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE);
        selected_count.setText(String.format(Locale.ROOT, "Selected: %d", city_list.getCheckedItemCount()));
        confirm_rmv_view.setVisibility(View.VISIBLE);
    }

    private void disableAddChoice() {
        city_name.setText("");
        province_name.setText("");
        confirm_add_view.setVisibility(View.GONE);
    }

    private void disableRmvChoice() {
        city_adp = new CityArrayAdapter(this, R.layout.content, data_list);
        city_list.setAdapter(city_adp);
        city_list.setChoiceMode(ListView.CHOICE_MODE_NONE);
        confirm_rmv_view.setVisibility(View.GONE);
    }
}
