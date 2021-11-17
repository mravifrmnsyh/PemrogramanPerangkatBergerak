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
import android.widget.TextView;

import java.util.ArrayList;

public class user extends AppCompatActivity {
    TextView out,nm,umr,tlp,gdr;
    Dialog info;
    ListView lstPerc;
    String txt;

    public static ArrayList<String> perco;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user);

        info = new Dialog(this);
        if(perco==null){
            perco = new ArrayList<>();
        }

        out = findViewById(R.id.txtOut);
        nm = findViewById(R.id.txtNama);
        umr = findViewById(R.id.txtUmr);
        tlp = findViewById(R.id.txtNo);
        gdr = findViewById(R.id.txtGen);
        lstPerc = findViewById(R.id.txtPerc);

        String nama = getIntent().getStringExtra("nama");
        String umur = getIntent().getStringExtra("umur");
        String telp = getIntent().getStringExtra("telp");
        String gender = getIntent().getStringExtra("gen");
        String hasil = String.valueOf(getIntent().getExtras().getInt("hsl"));
        if(getIntent().getExtras().getInt("hsl")>=70){
            txt = "LULUS";
        } else if((getIntent().getExtras().getInt("hsl")>=0) && (getIntent().getExtras().getInt("hsl")<70)){
            txt = "TIDAK LULUS";
        }
        String pros = String.valueOf(getIntent().getExtras().getInt("pros")+". "+hasil+" - "+txt);

        int cek=0;
        for(int i=0;i<perco.size();i++){
            if(perco.get(i).equals(pros)){
                cek+=1;
                break;
            }
        }
        if((cek==0) && (getIntent().getExtras().getInt("pros")!=0)){
            perco.add(pros);
        }

        ArrayAdapter adapt = new ArrayAdapter(this, R.layout.support_simple_spinner_dropdown_item,perco);

        out.setText(nama);
        nm.setText(nama);
        umr.setText(umur);
        tlp.setText(telp);
        gdr.setText(gender);
        lstPerc.setAdapter(adapt);

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

    public void aksiBack(View view) {
        Intent i = new Intent(user.this, jenjang.class);
        i.putExtra("nama", getIntent().getStringExtra("nama"));
        i.putExtra("umur", getIntent().getStringExtra("umur"));
        i.putExtra("hsl", getIntent().getExtras().getInt("hsl"));
        i.putExtra("telp", getIntent().getStringExtra("telp"));
        i.putExtra("gen", getIntent().getStringExtra("gen"));
        i.putExtra("pros", getIntent().getExtras().getInt("pros"));
        startActivity(i);
    }
}