package com.example.kependudukan.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.kependudukan.API.APIRequestData;
import com.example.kependudukan.API.retroServ;
import com.example.kependudukan.Model.ResponseModel;
import com.example.kependudukan.R;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class UbahActivity extends AppCompatActivity {
    private String gen[] = {"L", "P"};
    private ImageView img;
    private int xNik, yNik;
    private String xNama, xTtl, xAlamat,xGender;
    private EditText edNik,edNama,edTtl,edAlamat;
    private Spinner spGender;
    private Button btnUbah;
    private String yNama, yTtl, yAlamat, yGender;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ubah);

        Intent terima = getIntent();
        xNik = terima.getIntExtra("xnik", -1);
        xNama = terima.getStringExtra("xnama");
        xTtl = terima.getStringExtra("xttl");
        xAlamat = terima.getStringExtra("xalamat");
        xGender = terima.getStringExtra("xgender");

        edNik = findViewById(R.id.idUbahNik);
        edNama = findViewById(R.id.idUbahNama);
        edTtl = findViewById(R.id.idUbahTtl);
        edAlamat = findViewById(R.id.idUbahAlamat);
        spGender = findViewById(R.id.idUbahGender);
        btnUbah = findViewById(R.id.btnUbah);
        img = findViewById(R.id.idBack);

        ArrayAdapter adapt = new ArrayAdapter(UbahActivity.this, R.layout.support_simple_spinner_dropdown_item, gen);

        spGender.setAdapter(adapt);
        img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(UbahActivity.this, Dashboard.class));
            }
        });

        edNik.setText(String.valueOf(xNik));
        edNama.setText(xNama);
        edTtl.setText(xTtl);
        edAlamat.setText(xAlamat);
        spGender.setSelection(getIndeks(spGender,xGender));

        btnUbah.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                yNik = Integer.parseInt(edNik.getText().toString());
                yNama = edNama.getText().toString();
                yTtl = edTtl.getText().toString();
                yAlamat = edAlamat.getText().toString();
                yGender = spGender.getSelectedItem().toString();

                updateData();
            }

            private void updateData(){
                APIRequestData ardData = retroServ.konekRetro().create(APIRequestData.class);
                Call<ResponseModel> ubahData = ardData.ardUpdateData(xNik,yNama,yTtl,yAlamat,yGender);
                ubahData.enqueue(new Callback<ResponseModel>() {
                    @Override
                    public void onResponse(Call<ResponseModel> call, Response<ResponseModel> response) {
                        int kode = response.body().getKode();
                        String pesan = response.body().getPesan();

                        Toast.makeText(UbahActivity.this, "Kode : "+kode+"| Pesan "+pesan, Toast.LENGTH_SHORT).show();
                        finish();
                    }

                    @Override
                    public void onFailure(Call<ResponseModel> call, Throwable t) {
                        Toast.makeText(UbahActivity.this, "Gagal Menghubungi Server |"+t.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });

    }

    private int getIndeks(Spinner spGender, String xGender) {
        //looping
        for (int i=0; i<spGender.getCount(); i++){
            if(spGender.getItemAtPosition(i).toString().equalsIgnoreCase(xGender)){
                return i;
            }
        }
        return 0;
    }
}