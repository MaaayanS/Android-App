package com.example.myapplication.UI;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProviders;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.Toast;

import com.example.myapplication.R;
import com.example.myapplication.data.models.ParcelFireBaseViewModel;
import com.example.myapplication.data.models.ParcelViewModel;
import com.example.myapplication.entities.*;
import com.example.myapplication.entities.StatusPackege;
import com.example.myapplication.entities.Type;
import com.example.myapplication.entities.Waight;
import com.example.myapplication.entities.dateTime;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

//Activity where a package is added

public class MainActivity extends AppCompatActivity implements View.OnClickListener, TextWatcher {
    private EditText package_Id_edit_text;
    private EditText owner_name_edit_text;
    private EditText location_edit_text;
    private EditText DD_delivery_date_edit_text;
    private EditText MM_delivery_date_edit_text;
    private EditText YYYY_delivery_date_edit_text;
    private EditText email_edit_text;
    private EditText city_edit_text;
    private EditText street_edit_text;
    private EditText number_edit_text;
    private EditText phone_edit_text;
    private EditText deliver_name_edit_text;
    private Spinner package_type_spinner;
    private Spinner status_spinner;
    private Spinner waight_spinner;
    private Switch fragile_Switch;
    private Button saveButton;
    private ParcelFireBaseViewModel viewModel;
    private String location = "31.554";
    private ParcelViewModel parcelViewModel;
    private FloatingActionButton returnFab;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        returnFab = findViewById(R.id.returnFab);
        returnFab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this, EnterActivity.class);
                startActivity(i);
            }
        });

        // room
        parcelViewModel = ViewModelProviders.of(this).get(ParcelViewModel.class);
        //room

        Intent i = getIntent();
        location = i.getStringExtra(LocationActivity.EXTRA_TEXT);

        viewModel = ViewModelProviders.of(this).get(ParcelFireBaseViewModel.class); //ViewModelProviders.of(this).get(ParcelFireBaseViewModel.class);

        //Entering the values of the ENUM
        package_type_spinner = (Spinner) findViewById(R.id.spinner);
        package_type_spinner.setAdapter(new ArrayAdapter<Type>(this, android.R.layout.simple_spinner_item, Type.values()));
        status_spinner = (Spinner) findViewById(R.id.spinner3);
        status_spinner.setAdapter(new ArrayAdapter<StatusPackege>(this, android.R.layout.simple_spinner_item, StatusPackege.values()));
        waight_spinner = (Spinner) findViewById(R.id.spinner5);
        waight_spinner.setAdapter(new ArrayAdapter<Waight>(this, android.R.layout.simple_spinner_item, Waight.values()));

        saveButton = (Button) findViewById(R.id.saveButton);

        saveButton.setOnClickListener(this);

        //Input integrity check
        DD_delivery_date_edit_text = (EditText) findViewById(R.id.editText13);
        DD_delivery_date_edit_text.addTextChangedListener(this);
        MM_delivery_date_edit_text = (EditText) findViewById(R.id.editText11);
        MM_delivery_date_edit_text.addTextChangedListener(this);
        YYYY_delivery_date_edit_text = (EditText) findViewById(R.id.editText12);
        YYYY_delivery_date_edit_text.addTextChangedListener(this);
        phone_edit_text = (EditText) findViewById(R.id.editText2);
        phone_edit_text.addTextChangedListener(this);

    }

    @Override
    public void onClick(View v) {
        onClickFunk();
    }

    protected void onClickFunk() {

        //Gets the values that the user entered into values
        owner_name_edit_text = (EditText) findViewById(R.id.editText4);
        DD_delivery_date_edit_text = (EditText) findViewById(R.id.editText13);
        MM_delivery_date_edit_text = (EditText) findViewById(R.id.editText11);
        YYYY_delivery_date_edit_text = (EditText) findViewById(R.id.editText12);
        email_edit_text = (EditText) findViewById(R.id.editText);
        phone_edit_text = (EditText) findViewById(R.id.editText2);
        package_type_spinner = (Spinner) findViewById(R.id.spinner);
        status_spinner = (Spinner) findViewById(R.id.spinner3);
        waight_spinner = (Spinner) findViewById(R.id.spinner5);
        fragile_Switch = (Switch) findViewById(R.id.switch1);

        //Entering the details of the package
        String packageTypeString = package_type_spinner.getSelectedItem().toString();
        Type packageType;
        if (packageTypeString == "SMALL_PACKAGE")
            packageType = Type.SMALL_PACKAGE;
        else if (packageTypeString == "LARGE_PACKAGE")
            packageType = Type.LARGE_PACKAGE;
        else packageType = Type.ENVELOPE;

        String waightString = waight_spinner.getSelectedItem().toString();
        Waight packageWaight;
        if (waightString == "UP_TO_1_P")
            packageWaight = Waight.UP_TO_1_P;
        else if (waightString == "UP_TO_5_P")
            packageWaight = Waight.UP_TO_5_P;
        else if (waightString == "UP_TO_20_P")
            packageWaight = Waight.UP_TO_20_P;
        else packageWaight = Waight.UP_TO_500_G;

        dateTime deliveryDate = new dateTime(Integer.parseInt(YYYY_delivery_date_edit_text.getText().toString()),
                Integer.parseInt(MM_delivery_date_edit_text.getText().toString()),
                Integer.parseInt(DD_delivery_date_edit_text.getText().toString()), 00, 00); //defult hour and minute

        String statusString = status_spinner.getSelectedItem().toString();
        StatusPackege status;
        if (statusString == "Sent")
            status = StatusPackege.Sent;
        else if (statusString == "Someone_offered_to_pick_her_up")
            status = StatusPackege.Someone_offered_to_pick_her_up;
        else if (statusString == "On_the_way")
            status = StatusPackege.On_the_way;
        else status = StatusPackege.Was_accepted;

        //Create a new package with all the details entered
        Parcel newParcel = new Parcel();
        newParcel.setPackageType(packageType);
        newParcel.setFragile(fragile_Switch.isChecked());
        newParcel.setPackageWaight(packageWaight);
        newParcel.setDeliveryDate(deliveryDate);
        newParcel.setEmail(email_edit_text.getText().toString());
        newParcel.setStatus(status);
        newParcel.setOwnerName(owner_name_edit_text.getText().toString());
        newParcel.setOwnerPhoneNum(phone_edit_text.getText().toString());
        newParcel.setOwnerAddress(location);

        //Convert the package to ParcelAdapter
        ParcelAdapter parcelAdapter = new ParcelAdapter(newParcel);

        //insert to the FierBase
        String parcelFID = viewModel.insert(newParcel);
        //insert to the room
        parcelAdapter.setPackageId(parcelFID);
        parcelViewModel.insert(parcelAdapter);

    }

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {

    }

    //Input integrity check
    @Override
    public void afterTextChanged(Editable s) {
        try {

            DD_delivery_date_edit_text = (EditText) findViewById(R.id.editText13);
            if (DD_delivery_date_edit_text.getText().length() > 2) {
                Toast.makeText(MainActivity.this, "A day should be with 2 numbers\n", Toast.LENGTH_SHORT).show();
                DD_delivery_date_edit_text.setText("");
            }

            if (Integer.parseInt(DD_delivery_date_edit_text.getText().toString()) > 31) {
                Toast.makeText(MainActivity.this, "The day number should be between 1 and 31\n", Toast.LENGTH_SHORT).show();
                DD_delivery_date_edit_text.setText("");
            }

            MM_delivery_date_edit_text = (EditText) findViewById(R.id.editText11);
            if (MM_delivery_date_edit_text.getText().length() > 2) {
                Toast.makeText(MainActivity.this, "A month should be with 2 numbers\n", Toast.LENGTH_SHORT).show();
                MM_delivery_date_edit_text.setText("");
            }

            if (Integer.parseInt(MM_delivery_date_edit_text.getText().toString()) > 12) {
                Toast.makeText(MainActivity.this, "The month number should be between 1 and 12\n", Toast.LENGTH_SHORT).show();
                MM_delivery_date_edit_text.setText("");
            }

            YYYY_delivery_date_edit_text = (EditText) findViewById(R.id.editText12);
            if (YYYY_delivery_date_edit_text.getText().length() > 4) {
                Toast.makeText(MainActivity.this, "A year should be with 4 numbers\n", Toast.LENGTH_SHORT).show();
                YYYY_delivery_date_edit_text.setText("");
            }

            phone_edit_text = (EditText) findViewById(R.id.editText2);
            if (phone_edit_text.getText().length() > 10) {
                Toast.makeText(MainActivity.this, "A phone number should have 10 digits\n", Toast.LENGTH_SHORT).show();
                phone_edit_text.setText("");
            }
        } catch (Exception e) {
            //Toast.makeText(MainActivity.this, "Exception: " + e.getMessage(), Toast.LENGTH_LONG).show();
        }

    }
}