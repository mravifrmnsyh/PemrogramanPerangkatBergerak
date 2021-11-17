package com.example.projectuts;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;

public class soal extends AppCompatActivity {
    TextView out;
    Dialog clear;
    int perc;

    Spinner grp1,grp2,grp3,grp4,grp5,grp6,grp7,grp8,grp9,grp10;

    public String jbwbn1 [] = {"--Pilih--","12","11","10","9"};
    public String jbwbn2 [] = {"--Pilih--","13","14","12","4"};
    public String jbwbn3 [] = {"--Pilih--","Biasa","Ukuran","Nisbi","Status"};
    public String jbwbn4 [] = {"--Pilih--","Pemagaran","Peningkatan","Pemugaran","Pembongkaran"};
    public String jbwbn5 [] = {"--Pilih--","Awam","Spesialis","Cendekia","Mahir"};
    public String jbwbn6 [] = {"--Pilih--","Pasif","Apatis","Statis","Kondusif"};
    public String jbwbn7 [] = {"--Pilih--","Siang : Sore : Malam","Makan : Tidur : Kenyang","Bayi : Anak-anak : Remaja","Telur : Kepompong : Kupu-kupu"};
    public String jbwbn8 [] = {"--Pilih--","Motor : Cuci : Bengkel","Saus : Cabai : Tomat","Bola : Karet : Basket","Dokter : Resep : Pil"};
    public String jbwbn9 [] = {"--Pilih--","Bulan","Juli","April","Juni"};
    public String jbwbn10 [] = {"--Pilih--","Panas : Hangat","Hujan : Awan","Arang : Abu","Panas : Terang"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_soal);

        out = findViewById(R.id.txtOut);

        clear = new Dialog(this);

        String nama = getIntent().getStringExtra("nama");

        out.setText("Halo, "+nama);

        grp1 = findViewById(R.id.jwb1);
        grp2 = findViewById(R.id.jwb2);
        grp3 = findViewById(R.id.jwb3);
        grp4 = findViewById(R.id.jwb4);
        grp5 = findViewById(R.id.jwb5);
        grp6 = findViewById(R.id.jwb6);
        grp7 = findViewById(R.id.jwb7);
        grp8 = findViewById(R.id.jwb8);
        grp9 = findViewById(R.id.jwb9);
        grp10 = findViewById(R.id.jwb10);

        ArrayAdapter adapt1 = new ArrayAdapter(soal.this, R.layout.support_simple_spinner_dropdown_item, jbwbn1);
        ArrayAdapter adapt2 = new ArrayAdapter(soal.this, R.layout.support_simple_spinner_dropdown_item, jbwbn2);
        ArrayAdapter adapt3 = new ArrayAdapter(soal.this, R.layout.support_simple_spinner_dropdown_item, jbwbn3);
        ArrayAdapter adapt4 = new ArrayAdapter(soal.this, R.layout.support_simple_spinner_dropdown_item, jbwbn4);
        ArrayAdapter adapt5 = new ArrayAdapter(soal.this, R.layout.support_simple_spinner_dropdown_item, jbwbn5);
        ArrayAdapter adapt6 = new ArrayAdapter(soal.this, R.layout.support_simple_spinner_dropdown_item, jbwbn6);
        ArrayAdapter adapt7 = new ArrayAdapter(soal.this, R.layout.support_simple_spinner_dropdown_item, jbwbn7);
        ArrayAdapter adapt8 = new ArrayAdapter(soal.this, R.layout.support_simple_spinner_dropdown_item, jbwbn8);
        ArrayAdapter adapt9 = new ArrayAdapter(soal.this, R.layout.support_simple_spinner_dropdown_item, jbwbn9);
        ArrayAdapter adapt10 = new ArrayAdapter(soal.this, R.layout.support_simple_spinner_dropdown_item, jbwbn10);

        grp1.setAdapter(adapt1);
        grp2.setAdapter(adapt2);
        grp3.setAdapter(adapt3);
        grp4.setAdapter(adapt4);
        grp5.setAdapter(adapt5);
        grp6.setAdapter(adapt6);
        grp7.setAdapter(adapt7);
        grp8.setAdapter(adapt8);
        grp9.setAdapter(adapt9);
        grp10.setAdapter(adapt10);
    }

    public void aksiSelesai(View view) {
        Button tutup,pros;
        clear.setContentView(R.layout.popselesai);

        tutup = clear.findViewById(R.id.btnTutup);
        tutup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                clear.dismiss();
            }
        });

        pros = clear.findViewById(R.id.btnClear);
        pros.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(user.perco==null){
                    perc = 0;
                    perc=perc+1;
                }else{
                    perc = getIntent().getExtras().getInt("pros");
                    perc=perc+1;
                }

                int hsl = 0;


                if(grp1.getSelectedItem().toString().toLowerCase().equals("10")){
                    hsl=hsl+10;
                }
                if(grp2.getSelectedItem().toString().toLowerCase().equals("4")){
                    hsl=hsl+10;
                }
                if(grp3.getSelectedItem().toString().toLowerCase().equals("nisbi")){
                    hsl=hsl+10;
                }
                if(grp4.getSelectedItem().toString().toLowerCase().equals("pemugaran")){
                    hsl=hsl+10;
                }
                if(grp5.getSelectedItem().toString().toLowerCase().equals("awam")){
                    hsl=hsl+10;
                }
                if(grp6.getSelectedItem().toString().toLowerCase().equals("statis")){
                    hsl=hsl+10;
                }
                if(grp7.getSelectedItem().toString().toLowerCase().equals("bayi : anak-anak : remaja")){
                    hsl=hsl+10;
                }
                if(grp8.getSelectedItem().toString().toLowerCase().equals("bola : karet : basket")){
                    hsl=hsl+10;
                }
                if(grp9.getSelectedItem().toString().toLowerCase().equals("april")){
                    hsl=hsl+10;
                }
                if(grp10.getSelectedItem().toString().toLowerCase().equals("panas : terang")){
                    hsl=hsl+10;
                }

                Intent intent = new Intent(soal.this, nilai.class);
                intent.putExtra("nama", getIntent().getStringExtra("nama"));
                intent.putExtra("umur", getIntent().getStringExtra("umur"));
                intent.putExtra("hsl", hsl);
                intent.putExtra("telp", getIntent().getStringExtra("telp"));
                intent.putExtra("gen", getIntent().getStringExtra("gen"));
                intent.putExtra("pros", perc);
                startActivity(intent);
            }
        });

        clear.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        clear.show();
    }

    public void aksiBack(View view) {
        Intent i = new Intent(soal.this, jenjang.class);
        i.putExtra("nama", getIntent().getStringExtra("nama"));
        i.putExtra("umur", getIntent().getStringExtra("umur"));
        i.putExtra("hsl", getIntent().getExtras().getInt("hsl"));
        i.putExtra("telp", getIntent().getStringExtra("telp"));
        i.putExtra("gen", getIntent().getStringExtra("gen"));
        i.putExtra("pros", getIntent().getExtras().getInt("pros"));
        startActivity(i);
    }
}