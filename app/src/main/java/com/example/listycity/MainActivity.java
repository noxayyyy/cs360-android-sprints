package com.example.listycity;

import android.os.Bundle;
import android.util.SparseBooleanArray;
import android.view.ActionMode;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AbsListView;
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

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Locale;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class MainActivity extends AppCompatActivity {
    ListView city_list;
    ArrayAdapter<String> city_adp;
    ArrayList<String> data_list;
    Button add_city_btn;
    Button rmv_city_btn;
    LinearLayout confirm_add_view;
    Button confirm_add_btn;
    EditText city_name;
    LinearLayout confirm_rmv_view;
    Button confirm_rmv_btn;
    TextView selected_count;

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

        confirm_rmv_view = findViewById(R.id.confirm_rmv_view);
        confirm_rmv_btn = confirm_rmv_view.findViewById(R.id.confirm_rmv_btn);
        selected_count = confirm_rmv_view.findViewById(R.id.selected_count);

        String[] cities = {
                "Edmonton", "Vancouver", "Moscow", "Sydney", "Berlin",
                "Vienna", "Tokyo", "Beijing", "Osaka", "New Delhi"
        };

        data_list = new ArrayList<String>();
        data_list.addAll(Arrays.asList(cities));
        city_adp = new ArrayAdapter<String>(this, R.layout.content, data_list);
        city_list.setAdapter(city_adp);

        city_list.setOnItemClickListener((a, b, c, d) -> {
            selected_count.setText(String.format(Locale.ROOT, "Selected: %d", city_list.getCheckedItemCount()));
        });

        add_city_btn.setOnClickListener((View v) -> enableAddChoice());
        confirm_add_btn.setOnClickListener((View v) -> {
            data_list.add(city_name.getText().toString());
            city_adp.notifyDataSetChanged();
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
        for (int i = data_list.size() - 1; i >= 0; --i) {
            if (!check_selected.get(i)) continue;
            data_list.remove(i);
        }
    }

    private void enableAddChoice() {
        disableRmvChoice();
        confirm_add_view.setVisibility(View.VISIBLE);
    }

    private void enableRmvChoice() {
        disableAddChoice();
        city_adp = new ArrayAdapter<String>(this, R.layout.list_item_rmv_mode, data_list);
        city_list.setAdapter(city_adp);
        city_list.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE);
        selected_count.setText(String.format(Locale.ROOT, "Selected: %d", city_list.getCheckedItemCount()));
        confirm_rmv_view.setVisibility(View.VISIBLE);
    }

    private void disableAddChoice() {
        city_name.setText("");
        confirm_add_view.setVisibility(View.GONE);
    }

    private void disableRmvChoice() {
        city_adp = new ArrayAdapter<String>(this, R.layout.content, data_list);
        city_list.setAdapter(city_adp);
        city_list.setChoiceMode(ListView.CHOICE_MODE_NONE);
        confirm_rmv_view.setVisibility(View.GONE);
    }
}
