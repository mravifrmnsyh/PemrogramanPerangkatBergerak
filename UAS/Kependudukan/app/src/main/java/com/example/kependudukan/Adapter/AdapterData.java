package com.example.kependudukan.Adapter;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.RecyclerView;

import com.example.kependudukan.API.APIRequestData;
import com.example.kependudukan.API.retroServ;
import com.example.kependudukan.Activity.Dashboard;
import com.example.kependudukan.Activity.UbahActivity;
import com.example.kependudukan.Model.DataModel;
import com.example.kependudukan.Model.ResponseModel;
import com.example.kependudukan.R;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AdapterData extends RecyclerView.Adapter<AdapterData.HolderData>{
    private Context ctx;
    private List<DataModel> listData;
    private int idPend;
    private List<DataModel> listPend;

    public AdapterData(Context ctx, List<DataModel> listData) {
        this.ctx = ctx;
        this.listData = listData;
    }

    @NonNull
    @Override
    public HolderData onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View layout = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_item, parent,false);
        HolderData holder = new HolderData(layout);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull HolderData holder, int position) {
        DataModel dm = listData.get(position);

        holder.nik.setText(String.valueOf(dm.getNik()));
        holder.nama.setText(dm.getNama());
        holder.ttl.setText(dm.getTtl());
        holder.alamat.setText(dm.getAlamat());
        holder.gender.setText(dm.getGender());
    }

    @Override
    public int getItemCount() {
        return listData.size();
    }

    public class HolderData extends RecyclerView.ViewHolder{
        TextView nik, nama, ttl, alamat, gender;

        public HolderData(@NonNull View itemView) {
            super(itemView);

            nik = itemView.findViewById(R.id.idNik);
            nama = itemView.findViewById(R.id.idNama);
            ttl = itemView.findViewById(R.id.idLahir);
            alamat = itemView.findViewById(R.id.idAlamat);
            gender = itemView.findViewById(R.id.idGender);

            itemView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    AlertDialog.Builder dial = new AlertDialog.Builder(ctx);
                    dial.setMessage("Pilih operasi : ");
                    dial.setCancelable(true);
                    idPend = Integer.parseInt(nik.getText().toString());

                    dial.setPositiveButton("Hapus", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            deleteData();
                            dialog.dismiss();
                            ((Dashboard) ctx).retrieveData();
                        }
                    });

                    dial.setNegativeButton("Ubah", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            getData();
                            dialog.dismiss();
                        }
                    });

                    dial.show();

                    return false;
                }
            });
        }
        private void deleteData() {
            APIRequestData ardData = retroServ.konekRetro().create(APIRequestData.class);
            Call<ResponseModel> hapusData = ardData.ardDeleteData(idPend);
            hapusData.enqueue(new Callback<ResponseModel>() {
                @Override
                public void onResponse(Call<ResponseModel> call, Response<ResponseModel> response) {
                    int kode = response.body().getKode();
                    String pesan = response.body().getPesan();

                    Toast.makeText(ctx, "Kode : "+kode+"| Pesan : "+pesan, Toast.LENGTH_SHORT).show();
                }

                @Override
                public void onFailure(Call<ResponseModel> call, Throwable t) {
                    Toast.makeText(ctx, "Gagal Menghubungi Server | "+t.getMessage(), Toast.LENGTH_SHORT).show();
                }
            });
        }

        private void getData(){
            APIRequestData ardData = retroServ.konekRetro().create(APIRequestData.class);
            Call<ResponseModel> ambilData = ardData.ardGetData(idPend);
            ambilData.enqueue(new Callback<ResponseModel>() {
                @Override
                public void onResponse(Call<ResponseModel> call, Response<ResponseModel> response) {
                    int kode = response.body().getKode();
                    String pesan = response.body().getPesan();
                    listPend = response.body().getData();

                    int varNikPend = listPend.get(0).getNik();
                    String varNamaPend = listPend.get(0).getNama();
                    String varTtlPend = listPend.get(0).getTtl();
                    String varAlamatPend = listPend.get(0).getAlamat();
                    String varGenderPend = listPend.get(0).getGender();

                    Intent kirim = new Intent(ctx, UbahActivity.class);
                    kirim.putExtra("xnik",varNikPend);
                    kirim.putExtra("xnama",varNamaPend);
                    kirim.putExtra("xttl",varTtlPend);
                    kirim.putExtra("xalamat",varAlamatPend);
                    kirim.putExtra("xgender",varGenderPend);
                    ctx.startActivity(kirim);


                    Toast.makeText(ctx, "Kode : "+kode+"| Pesan : "+pesan+"| Data : "+varNikPend+"| "+varNamaPend+"| "+varTtlPend+"| "+varAlamatPend+"| "+varGenderPend, Toast.LENGTH_SHORT).show();
                }

                @Override
                public void onFailure(Call<ResponseModel> call, Throwable t) {
                    Toast.makeText(ctx, "Gagal Menghubungi Server | "+t.getMessage(), Toast.LENGTH_SHORT).show();
                }
            });
        }

    }
}
