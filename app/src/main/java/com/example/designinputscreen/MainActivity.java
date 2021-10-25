package com.example.designinputscreen;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputLayout;

import java.util.Calendar;

public class MainActivity extends AppCompatActivity {
    private TextInputLayout textMonthlyRentPrice, textNotes, textNameReporter;
    private Button BtnConfirm, dateButton;
    private DatePickerDialog datePickerDialog;
    String[] items_property = {"Flat", "House", "Bungalow"};
    String[] items_furniture = {"Furnished", "Unfurnished", "Part Furnished"};
    String[] items_bedroom = {"One", "Two", "Studio"};
    AutoCompleteTextView autoCompleteTextView_Property, autoCompleteTextView_Furniture,
                         autoCompleteTextView_Bedroom;
    ArrayAdapter<String> adapterItems_Property, adapterItems_Furniture, adapterItems_Bedroom;
    TextView SelectedDate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);
        textMonthlyRentPrice = findViewById(R.id.month);
        textNotes = findViewById(R.id.notes);
        textNameReporter = findViewById(R.id.name_of_the_reporter);
        BtnConfirm = findViewById(R.id.confirm_button);
        SelectedDate = findViewById(R.id.selected_date);

        //Initialize calendar
        Calendar calendar = Calendar.getInstance();

        //Get year
        int year = calendar.get(Calendar.YEAR);

        //Get month
        int month = calendar.get(Calendar.MONTH);

        //Get day
        int day = calendar.get(Calendar.DAY_OF_MONTH);

        SelectedDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Initialize date picker dialog
                DatePickerDialog datePickerDialog = new DatePickerDialog(
                        MainActivity.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker datePicker, int year, int month, int dayOfmonth) {

                        //Store date in string
                        String sDate = dayOfmonth + "/" + (month + 1)+ "/" + year;

                        //Set date on text view
                        SelectedDate.setText(sDate);
                    }
                },year,month,day);

                //Disable past date
                datePickerDialog.getDatePicker().setMinDate(System.currentTimeMillis()-1000);
                datePickerDialog.getDatePicker().setMaxDate(System.currentTimeMillis());

                //Show date picker dialog
                datePickerDialog.show();
            }
        });


        autoCompleteTextView_Property = findViewById(R.id.property_type);
        adapterItems_Property = new ArrayAdapter<String>(this, R.layout.list_item, items_property);
        autoCompleteTextView_Property.setAdapter(adapterItems_Property);
        autoCompleteTextView_Property.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
                String item = adapterView.getItemAtPosition(position).toString();
                Toast.makeText(getApplicationContext(), "Property: " + item, Toast.LENGTH_SHORT).show();
            }
        });

        autoCompleteTextView_Furniture = findViewById(R.id.furniture_type);
        adapterItems_Furniture = new ArrayAdapter<String>(this, R.layout.list_item, items_furniture);
        autoCompleteTextView_Furniture.setAdapter(adapterItems_Furniture);
        autoCompleteTextView_Furniture.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
                String item = adapterView.getItemAtPosition(position).toString();
                Toast.makeText(getApplicationContext(), "Furniture: " + item, Toast.LENGTH_SHORT).show();
            }
        });

        autoCompleteTextView_Bedroom = findViewById(R.id.bedroom_type);
        adapterItems_Bedroom = new ArrayAdapter<String>(this, R.layout.list_item, items_bedroom);
        autoCompleteTextView_Bedroom.setAdapter(adapterItems_Bedroom);
        autoCompleteTextView_Bedroom.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
                String item = adapterView.getItemAtPosition(position).toString();
                Toast.makeText(getApplicationContext(), "Bedroom: " + item, Toast.LENGTH_SHORT).show();
            }
        });


    }

    private boolean validateDateAndMonth(){
        String val = SelectedDate.getText().toString().trim();

        if (val.isEmpty()){
            SelectedDate.setError("");
            SelectedDate.setText("Field can not be empty!");
            SelectedDate.setTextColor(Color.RED);
            return false;
        }
        else {
            SelectedDate.setError(null);
            return true;
        }
    }

    private boolean validateMonthlyRentPrice(){
        String val = textMonthlyRentPrice.getEditText().getText().toString().trim();

        if (val.isEmpty()){
            textMonthlyRentPrice.setError("Field can not be empty!");
            return false;
        }
        else {
            textMonthlyRentPrice.setError(null);
            return true;
        }
    }

    private boolean validateNameOfTheReporter(){
        String val = textNameReporter.getEditText().getText().toString().trim();

        if (val.isEmpty()){
            textNameReporter.setError("Field can not be empty!");
            return false;
        }
        else {
            textNameReporter.setError(null);
            return true;
        }
    }


    public void confirmInput(View v){
        if (!validateDateAndMonth() && !validateMonthlyRentPrice() && !validateNameOfTheReporter())
        {
            return;
        }
    }

    public void openDatePicker(View view) {
        datePickerDialog.show();
    }
}

