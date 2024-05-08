package com.example.quiss2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private RadioButton rbtnPS5, rbtnPS4, rbtnPS3, rbtnPSvr, rbtnIndomie, rbtnMieAyam, rbtnSomay;
    private EditText etjam;
    private Button btnProses;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        rbtnPS5 = findViewById(R.id.rbtnps5);
        rbtnPS4 = findViewById(R.id.rbtnps4);
        rbtnPS3 = findViewById(R.id.rbtnps3);
        rbtnPSvr = findViewById(R.id.rbtnpsvr);
        rbtnIndomie = findViewById(R.id.rbtnindomie);
        rbtnMieAyam = findViewById(R.id.rbtnmieayam);
        rbtnSomay = findViewById(R.id.rbtnsomay);
        etjam = findViewById(R.id.etjam);
        btnProses = findViewById(R.id.btnProces);

        btnProses.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                calculateRent();
            }
        });
    }

    private void calculateRent() {
        int psType = 0;
        int hours = 0;

        try {
            hours = Integer.parseInt(etjam.getText().toString());
        } catch (NumberFormatException e) {
            Toast.makeText(MainActivity.this, "Masukkan waktu dengan benar", Toast.LENGTH_SHORT).show();
            return;
        }

        if (rbtnPS5.isChecked()) {
            psType = 1;
        } else if (rbtnPS4.isChecked()) {
            psType = 2;
        } else if (rbtnPS3.isChecked()) {
            psType = 3;
        } else if (rbtnPSvr.isChecked()) {
            psType = 4;
        }

        if (hours <= 0) {
            Toast.makeText(MainActivity.this, "Waktu sewa harus lebih dari 0", Toast.LENGTH_SHORT).show();
            return;
        }

        int cost = calculateCost(psType, hours);
        cost += calculateAdditionalCost();

        Intent intent = new Intent(MainActivity.this, InvoiceActivity.class);
        intent.putExtra("type", getTypeString(psType));
        intent.putExtra("time", hours);
        intent.putExtra("total", cost);
        intent.putExtra("additional", calculateAdditionalCost());
        startActivity(intent);
    }

    private int calculateCost(int psType, int hours) {
        int cost = 0;
        switch (psType) {
            case 1:
                cost = hours * 10000;
                break;
            case 2:
                cost = hours * 8000;
                break;
            case 3:
                cost = hours * 5000;
                break;
            case 4:
                cost = hours * 20000;
                break;
        }
        return cost;
    }

    private int calculateAdditionalCost() {
        int additionalCost = 0;
        if (rbtnIndomie.isChecked()) {
            additionalCost += 7000;
        }
        if (rbtnMieAyam.isChecked()) {
            additionalCost += 10000;
        }
        if (rbtnSomay.isChecked()) {
            additionalCost += 5000;
        }
        return additionalCost;
    }

    private String getTypeString(int psType) {
        switch (psType) {
            case 1:
                return "PS5";
            case 2:
                return "PS4";
            case 3:
                return "PS3";
            case 4:
                return "PSVR";
            default:
                return "";
        }
    }
}
