package com.example.projectuts;

import static com.example.projectuts.MainActivity.umur;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;

public class jenjang extends AppCompatActivity {
    TextView out,hsl,umr;
    Dialog info;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_jenjang);

        info = new Dialog(this);

        String nama = getIntent().getStringExtra("nama");
        String umur = getIntent().getStringExtra("umur");

        out = findViewById(R.id.txtOut);
        hsl = findViewById(R.id.txtHsl);
        umr = findViewById(R.id.txtUmr);

        out.setText("Halo, "+nama);
        umr.setText("Umur   : "+umur);
        hsl.setText("Hasil  : "+getIntent().getExtras().getInt("hsl"));

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

    public void akksiMulai(View view) {
        Intent intent  = new Intent(jenjang.this, soal.class);
        intent.putExtra("nama", getIntent().getStringExtra("nama"));
        intent.putExtra("hsl", getIntent().getExtras().getInt("hsl"));
        intent.putExtra("umur", getIntent().getStringExtra("umur"));
        intent.putExtra("telp", getIntent().getStringExtra("telp"));
        intent.putExtra("gen", getIntent().getStringExtra("gen"));
        intent.putExtra("pros", getIntent().getExtras().getInt("pros"));
        startActivity(intent);
    }

    public void akksiBack(View view) {
        Intent i = new Intent(jenjang.this, MainActivity.class);
        startActivity(i);
    }

    public void aksiUser(View view) {
        Intent i = new Intent(jenjang.this, user.class);
        i.putExtra("nama", getIntent().getStringExtra("nama"));
        i.putExtra("umur", getIntent().getStringExtra("umur"));
        i.putExtra("hsl", getIntent().getExtras().getInt("hsl"));
        i.putExtra("telp", getIntent().getStringExtra("telp"));
        i.putExtra("gen", getIntent().getStringExtra("gen"));
        i.putExtra("pros", getIntent().getExtras().getInt("pros"));
        startActivity(i);
    }
}