package com.example.listycity;

import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import android.view.View;
import android.widget.ArrayAdapter;
import java.util.ArrayList;
import java.util.Arrays;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.AdapterView;




public class MainActivity extends AppCompatActivity {


//    Declaring Variables

    ArrayList<String> cityList;        // Holds city names
    ArrayAdapter<String> cityAdapter;  // Feeds cityList into the ListView
    ListView cityListView;
    Button addCityButton;
    Button deleteCityButton;
    Button confirmCityButton;
    EditText inputCity;

    int selectedPosition = -1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

//       Own code:

//        Similar to getting element by dom in Javascript, we find by the ID
//        R is the resource class collecting all resource IDS
//        id is from all the id in the xml
//        the attribute is the specific ID which will grab that widget
        cityListView = findViewById(R.id.city_list);
        addCityButton = findViewById(R.id.add_city_button);
        deleteCityButton = findViewById(R.id.delete_city_button);
        confirmCityButton = findViewById(R.id.confirm_city_button);
        inputCity = findViewById(R.id.input_city);

//        Dynamic Array
        cityList = new ArrayList<>();
//        cityList.add("Edmonton");
//        cityList.add("Vancouver");


//        Takes an item and turns it into a view for ListView component
        cityAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, cityList);
        cityListView.setAdapter(cityAdapter);

//        Setting the event of having a click of a button as a function
        addCityButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                inputCity.setText("");
            }
        });

        confirmCityButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Setting text inputted from the EditText element to a string
                String newCity = inputCity.getText().toString().trim();

                if (!newCity.isEmpty()) {
                    cityList.add(newCity);
                    cityAdapter.notifyDataSetChanged();
                    inputCity.setText("");
                }
            }
        });

        cityListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent,
                                    View view,
                                    int position,
                                    long id) {
                // Record which item is selected
                selectedPosition = position;
            }
        });

        deleteCityButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                cityList.remove(selectedPosition);
                cityAdapter.notifyDataSetChanged();
            }
        });


        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }
}