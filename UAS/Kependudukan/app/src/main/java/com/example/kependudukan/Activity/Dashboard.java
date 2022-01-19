package com.example.kependudukan.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.kependudukan.API.APIRequestData;
import com.example.kependudukan.API.retroServ;
import com.example.kependudukan.Adapter.AdapterData;
import com.example.kependudukan.Model.DataModel;
import com.example.kependudukan.Model.ResponseModel;
import com.example.kependudukan.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Dashboard extends AppCompatActivity {
    private RecyclerView rvData;
    private RecyclerView.Adapter adaptData;
    private RecyclerView.LayoutManager lmData;
    private int count;

    private List<DataModel> listData = new ArrayList<>();

    private SwipeRefreshLayout srl;
    private TextView jml;
    private ProgressBar progres;

    private FloatingActionButton fabTambah;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);

        rvData = findViewById(R.id.idRecviData);
        srl = findViewById(R.id.idSrl);
        progres = findViewById(R.id.idBar);
        fabTambah = findViewById(R.id.idTambah);
//        desa = findViewById(R.id.idDesaKec);

//        Intent dapat = getIntent();
//        desa.setText("Desa "+dapat.getStringExtra("desa")+" Kec. Kalinyamatan");

        lmData = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL,false);
        rvData.setLayoutManager(lmData);
        retrieveData();
        srl.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                srl.setRefreshing(true);
                retrieveData();
                srl.setRefreshing(false);
            }
        });

        jml = findViewById(R.id.idJuml);

        fabTambah.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Dashboard.this, TambahData.class));
            }
        });

    }

    public void retrieveData(){
        progres.setVisibility(View.VISIBLE);
        APIRequestData ardData = retroServ.konekRetro().create(APIRequestData.class);
        Call<ResponseModel> tampilDat = ardData.ardRetrieveData();
        tampilDat.enqueue(new Callback<ResponseModel>() {
            @Override
            public void onResponse(Call<ResponseModel> call, Response<ResponseModel> response) {
                int kode = response.body().getKode();
                String pesan = response.body().getPesan();

                Toast.makeText(Dashboard.this, "Kode : "+kode+"| Pesan : "+pesan, Toast.LENGTH_SHORT).show();

                listData = response.body().getData();
                adaptData = new AdapterData(Dashboard.this, listData);
                count = listData.size();
                jml.setText(String.valueOf(count));
                rvData.setAdapter(adaptData);
                adaptData.notifyDataSetChanged();
                progres.setVisibility(View.INVISIBLE);
            }

            @Override
            public void onFailure(Call<ResponseModel> call, Throwable t) {
                Toast.makeText(Dashboard.this, "Gagal Terhubung ke Server", Toast.LENGTH_SHORT).show();
                progres.setVisibility(View.INVISIBLE);
            }
        });
    }
}