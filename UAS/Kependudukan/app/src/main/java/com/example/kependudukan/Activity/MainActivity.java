package com.example.kependudukan.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.kependudukan.R;

public class MainActivity extends AppCompatActivity {
    Button masuk;
    EditText nm,pwd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        masuk = findViewById(R.id.idMasuk);
        nm = findViewById(R.id.idNmLog);
        pwd = findViewById(R.id.idPassLog);
        masuk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (nm.getText().toString().trim().equals("Admin")){
                    if (pwd.getText().toString().trim().equals("admin")){
                        Intent intent = new Intent(MainActivity.this, Dashboard.class);
                        startActivity(intent);
                    }else{
                        pwd.setError("Password Salah");
                    }
                } else{
                    nm.setError("Username salah");
                }

            }
        });
    }
}