package com.example.projectuts;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.SimpleCursorAdapter;
import android.widget.Spinner;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    Spinner cmb;

    public String gen [] = {"--pilih--", "Laki-laki", "Perempuan"};

    static EditText nama,umur,tlp;
    Dialog info;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        info = new Dialog(this);

        nama = (EditText) findViewById(R.id.nama);
        umur = (EditText) findViewById(R.id.umur);
        tlp = (EditText) findViewById(R.id.telp);
        cmb = (Spinner) findViewById(R.id.gender);

        ArrayAdapter adapt = new ArrayAdapter(MainActivity.this, R.layout.support_simple_spinner_dropdown_item,gen);

        cmb.setAdapter(adapt);
    }

    public void aksiMasuk(View view) {
        if(nama.getText().toString().equals("")){
            Toast.makeText(this, "Nama belum diisi", Toast.LENGTH_LONG).show();
        }else if(umur.getText().toString().equals("")){
            Toast.makeText(this, "Umur belum diisi", Toast.LENGTH_LONG).show();
        }else if(tlp.getText().toString().equals("")){
            Toast.makeText(this, "Telepon belum diisi", Toast.LENGTH_LONG).show();
        }else if(cmb.getSelectedItem().toString().toLowerCase().equals("--pilih--")){
            Toast.makeText(this, "Mohon pilih gender anda", Toast.LENGTH_LONG).show();
        }else{
            Intent intent = new Intent(MainActivity.this, jenjang.class);
            intent.putExtra("nama", nama.getText().toString());
            intent.putExtra("umur", umur.getText().toString());
            intent.putExtra("telp", tlp.getText().toString());
            intent.putExtra("gen", cmb.getSelectedItem().toString());
            startActivity(intent);
        }
    }

    public void aksiInfo(View view) {
        Button tutup;
        info.setContentView(R.layout.popupinfo);

        tutup = info.findViewById(R.id.btnTutup);
        tutup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                info.dismiss();
            }
        });
        info.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        info.show();
    }

    public void aksiUser(View view) {
        Toast.makeText(this, "Silakan Masuk Terlebih Dahulu", Toast.LENGTH_LONG).show();
    }
}