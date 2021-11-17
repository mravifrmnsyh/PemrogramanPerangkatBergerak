package com.example.projectuts;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class nilai extends AppCompatActivity {

    TextView out,hsl,txtucpn;
    Dialog info;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nilai);

        info = new Dialog(this);

        String nama = getIntent().getStringExtra("nama");
        int nil = getIntent().getExtras().getInt("hsl");

        if(nil>=70){
            String ucp = "LULUS";
            txtucpn = findViewById(R.id.ucpn);
            txtucpn.setText(ucp);
        } else if((nil>=0) && (nil<70)){
            String ucp = "TIDAK LULUS";
            txtucpn = findViewById(R.id.ucpn);
            txtucpn.setText(ucp);
        }

        out = findViewById(R.id.txtOut);
        hsl = findViewById(R.id.txtHsl);

        out.setText("Halo, "+nama);
        hsl.setText(String.valueOf(nil));
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
        Intent i = new Intent(nilai.this, user.class);
        i.putExtra("nama", getIntent().getStringExtra("nama"));
        i.putExtra("umur", getIntent().getStringExtra("umur"));
        i.putExtra("hsl", getIntent().getExtras().getInt("hsl"));
        i.putExtra("telp", getIntent().getStringExtra("telp"));
        i.putExtra("gen", getIntent().getStringExtra("gen"));
        i.putExtra("pros", getIntent().getExtras().getInt("pros"));
        startActivity(i);
    }

    public void aksiBack(View view) {
        Intent intent = new Intent(nilai.this, jenjang.class);
        intent.putExtra("nama", getIntent().getStringExtra("nama"));
        intent.putExtra("umur", getIntent().getStringExtra("umur"));
        intent.putExtra("hsl", getIntent().getExtras().getInt("hsl"));
        intent.putExtra("telp", getIntent().getStringExtra("telp"));
        intent.putExtra("gen", getIntent().getStringExtra("gen"));
        intent.putExtra("pros", getIntent().getExtras().getInt("pros"));
        startActivity(intent);
    }
}