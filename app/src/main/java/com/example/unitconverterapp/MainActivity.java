package com.example.unitconverterapp;

import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import android.text.TextUtils;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Spinner;
import android.widget.EditText;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    String FromSelectedUnit, ToSelectedUnit, FinalResult;
    EditText EnteredValue;
    Button ConvertButton;
    TextView ResultText;
    double Result = 0.0;

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

        Spinner FromSpinner = (Spinner) findViewById(R.id.UnitFrom);
        Spinner ToSpinner = (Spinner) findViewById(R.id.UnitTo);
        EnteredValue = findViewById(R.id.EnteredValue);
        ConvertButton = findViewById(R.id.ConvertButton);
        ResultText = findViewById(R.id.ResultText);

        FromSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> arg0, View view, int arg2, long arg3) {
                FromSelectedUnit = FromSpinner.getSelectedItem().toString().toLowerCase();
            }
            @Override
            public void onNothingSelected(AdapterView<?> arg0) {
                FromSelectedUnit = "inch";
            }
        });

        ToSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> arg0, View view, int arg2, long arg3) {
                ToSelectedUnit = ToSpinner.getSelectedItem().toString().toLowerCase();
            }
            @Override
            public void onNothingSelected(AdapterView<?> arg0) {
                ToSelectedUnit = "inch";
            }
        });

        ConvertButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String input = EnteredValue.getText().toString().trim();

                if(TextUtils.isEmpty(input)) {
                    Toast.makeText(MainActivity.this, "Empty input! Enter a value...", Toast.LENGTH_SHORT).show();
                    return;
                }

                if (FromSelectedUnit.equals(ToSelectedUnit)) {
                    Toast.makeText(MainActivity.this, "Conversion units are same! Please change an input...", Toast.LENGTH_SHORT).show();
                }
                else {
                    try {
                        double inputNumber = Double.parseDouble(input);

                        switch (FromSelectedUnit) {
                            case "foot":
                                switch (ToSelectedUnit) {
                                    case "inch":
                                        Result = inputNumber * 12.0;
                                        FinalResult = Double.toString(Result);
                                        break;

                                    case "yard":
                                        Result = inputNumber / 3.0;
                                        FinalResult = Double.toString(Result);
                                        break;

                                    case "mile":
                                        Result = inputNumber / 5280.0;
                                        FinalResult = Double.toString(Result);
                                        break;

                                    default:
                                        Result = inputNumber;
                                        FinalResult = Double.toString(Result);
                                        break;
                                }
                                break;

                            case "inch":
                                switch (ToSelectedUnit) {
                                    case "foot":
                                        Result = inputNumber / 12.0;
                                        FinalResult = Double.toString(Result);
                                        break;

                                    case "yard":
                                        Result = inputNumber / 36.0;
                                        FinalResult = Double.toString(Result);
                                        break;

                                    case "mile":
                                        Result = inputNumber / 63360.0;
                                        FinalResult = Double.toString(Result);
                                        break;

                                    default:
                                        Result = inputNumber;
                                        FinalResult = Double.toString(Result);
                                        break;
                                }
                                break;

                            case "yard":
                                switch (ToSelectedUnit) {
                                    case "foot":
                                        Result = inputNumber * 3.0;
                                        FinalResult = Double.toString(Result);
                                        break;

                                    case "inch":
                                        Result = inputNumber * 36.0;
                                        FinalResult = Double.toString(Result);
                                        break;

                                    case "mile":
                                        Result = inputNumber / 1760.0;
                                        FinalResult = Double.toString(Result);
                                        break;

                                    default:
                                        Result = inputNumber;
                                        FinalResult = Double.toString(Result);
                                        break;
                                }
                                break;

                            case "mile":
                                switch (ToSelectedUnit) {
                                    case "foot":
                                        Result = inputNumber * 5280.0;
                                        FinalResult = Double.toString(Result);
                                        break;

                                    case "inch":
                                        Result = inputNumber * 63360.0;
                                        FinalResult = Double.toString(Result);
                                        break;

                                    case "yard":
                                        Result = inputNumber * 1760.0;
                                        FinalResult = Double.toString(Result);
                                        break;

                                    default:
                                        Result = inputNumber;
                                        FinalResult = Double.toString(Result);
                                        break;
                                }
                                break;

                            default:
                                Result = inputNumber;
                                FinalResult = Double.toString(Result);
                                break;
                        }

                    } catch (NumberFormatException a) {
                        Toast.makeText(MainActivity.this, "Enter a valid input!", Toast.LENGTH_SHORT).show();
                    }

                    ResultText.setText("Converted Result: " + FinalResult + " " + ToSelectedUnit);
                    Toast.makeText(MainActivity.this, "Converted from " + FromSelectedUnit + " to " + ToSelectedUnit, Toast.LENGTH_SHORT).show();
                }
            }
        });

    }
}