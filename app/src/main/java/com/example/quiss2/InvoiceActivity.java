package com.example.quiss2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class InvoiceActivity extends AppCompatActivity {

    private TextView tvType, tvTime, tvTotal, tvAdditional;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_invoice);

        tvType = findViewById(R.id.tvtype);
        tvTime = findViewById(R.id.tvwaktu);
        tvTotal = findViewById(R.id.tvtotal);
        tvAdditional = findViewById(R.id.tvtambah);

        Intent intent = getIntent();
        String type = intent.getStringExtra("type");
        int time = intent.getIntExtra("time", 0);
        int total = intent.getIntExtra("total", 0);
        int additional = intent.getIntExtra("additional", 0);

        tvType.setText("Tipe PS: " + type);
        tvTime.setText("Waktu Sewa: " + time + " jam");
        tvTotal.setText("Total Biaya : Rp " + total);

        // Menampilkan informasi tambahan jika ada
        String additionalInfo = "";
        if ((additional & 7000) == 7000) {
            additionalInfo += "Indomie: Rp 7.000\n";
        }
        if ((additional & 10000) == 10000) {
            additionalInfo += "Mie Ayam: Rp 10.000\n";
        }
        if ((additional & 5000) == 5000) {
            additionalInfo += "Somay: Rp 5.000\n";
        }

        tvAdditional.setText(additionalInfo);
    }
}
