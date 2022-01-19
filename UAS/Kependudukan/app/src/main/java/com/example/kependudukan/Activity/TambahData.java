package com.example.kependudukan.Activity;

import static java.lang.Integer.parseInt;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.kependudukan.API.APIRequestData;
import com.example.kependudukan.API.retroServ;
import com.example.kependudukan.Model.ResponseModel;
import com.example.kependudukan.R;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class TambahData extends AppCompatActivity {
    private String gen[] = {"--Pilih--", "L", "P"};
    private ImageView img;
    private EditText edNik, edNama, edttl, edAlamat;
    private Spinner spGen;
    private Button btnSimpan;
    private int nik;
    private String nik2, nama, ttl, alamat, gender;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tambah_data);

        img = findViewById(R.id.idBack);

        edNik = findViewById(R.id.idTambahNik);
        edNama = findViewById(R.id.idTambahNama);
        edttl = findViewById(R.id.idTambahTtl);
        edAlamat = findViewById(R.id.idTambahAlamat);
        spGen = findViewById(R.id.idTambahGen);
        btnSimpan = findViewById(R.id.btnTambah);

        ArrayAdapter adapt = new ArrayAdapter(TambahData.this, R.layout.support_simple_spinner_dropdown_item, gen);

        spGen.setAdapter(adapt);
        img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(TambahData.this, Dashboard.class));
            }
        });

        btnSimpan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                nik2 = edNik.getText().toString();
                nama = edNama.getText().toString();
                ttl = edttl.getText().toString();
                alamat = edAlamat.getText().toString();
                gender = spGen.getSelectedItem().toString();

                if(nik2.trim().equals("")){
                    edNik.setError("Nik Harus Diisi");
                }else if(nama.trim().equals("")){
                    edNama.setError("Nama Harus Diisi");
                }else if(ttl.trim().equals("")){
                    edttl.setError("TTL Harus Diisi");
                }else if(alamat.trim().equals("")){
                    edAlamat.setError("Alamat Harus Diisi");
                }else if(gender.equals("--Pilih--")){
                    TextView eror = (TextView) spGen.getSelectedView();
                    eror.setError("Harap Pilih Gender");
                }else{
                    nik = Integer.parseInt(edNik.getText().toString());
                    createData();
                }
            }

            public void createData(){
                APIRequestData ardData = retroServ.konekRetro().create(APIRequestData.class);
                Call<ResponseModel> simpanData = ardData.ardCreateData(nik,nama,ttl,alamat,gender);
                simpanData.enqueue(new Callback<ResponseModel>() {
                    @Override
                    public void onResponse(Call<ResponseModel> call, Response<ResponseModel> response) {
                        int kode = response.body().getKode();
                        String pesan = response.body().getPesan();

                        Toast.makeText(TambahData.this, "Kode : "+kode+"| Pesan "+pesan, Toast.LENGTH_SHORT).show();
                        finish();
                    }

                    @Override
                    public void onFailure(Call<ResponseModel> call, Throwable t) {
                        Toast.makeText(TambahData.this, "Gagal Menghubungi Server |"+t.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });
    }
}