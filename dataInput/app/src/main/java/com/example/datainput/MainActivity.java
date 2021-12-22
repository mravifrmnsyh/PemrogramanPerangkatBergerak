package com.example.datainput;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    Spinner Gender;
    EditText txtnama, txtnik, txtttl, txtalamat, txttelp;
    Button btnTambah, btnTampil, btnDelete, btnUpdate, btnReset;

    DbHelper bantuDb;

    public String gen [] = {"--Pilih--", "Laki-Laki", "Perempuan"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        bantuDb = new DbHelper(this);
        Gender = findViewById(R.id.idGender);
        txtnama = findViewById(R.id.idNama);
        txtnik = findViewById(R.id.idNik);
        txtttl = findViewById(R.id.idTtl);
        txtalamat = findViewById(R.id.idAlamat);
        txttelp = findViewById(R.id.idTelp);
        btnTambah = findViewById(R.id.idSimpan);
        btnTampil = findViewById(R.id.idTampil);
        btnDelete = findViewById(R.id.idDelete);
        btnUpdate = findViewById(R.id.idUpdate);
        btnReset = findViewById(R.id.idReset);


        ArrayAdapter adapt = new ArrayAdapter(MainActivity.this, R.layout.support_simple_spinner_dropdown_item,gen);

        Gender.setAdapter(adapt);

        vewiAll();

        btnTambah.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean IsInserted = bantuDb.insertData(txtnama.getText().toString(),txtnik.getText().toString(),txtttl.getText().toString(),txtalamat.getText().toString(),txttelp.getText().toString(),Gender.getSelectedItem().toString());

                if (IsInserted == true){
                    Toast.makeText(MainActivity.this, "Data Tersimpan", Toast.LENGTH_LONG).show();
                }else{
                    Toast.makeText(MainActivity.this, "Gagal Tersimpan", Toast.LENGTH_LONG).show();
                }
            }
        });

        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String namaTxt = txtnama.getText().toString();
                String nikTxt = txtnik.getText().toString();
                String ttlTxt = txtttl.getText().toString();
                String alamatTxt = txtalamat.getText().toString();
                String telpTxt = txttelp.getText().toString();
                String genTxt = Gender.getSelectedItem().toString();

                Boolean checkupdatedata = bantuDb.updateData(namaTxt,nikTxt,ttlTxt,alamatTxt,telpTxt,genTxt);
                if(checkupdatedata==true) {
                    Toast.makeText(MainActivity.this, "Data Terupdate", Toast.LENGTH_SHORT).show();
                }else {
                    Toast.makeText(MainActivity.this, "Data Tidak Terupdate", Toast.LENGTH_SHORT).show();
                }
            }
        });

        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String nikTxt = txtnik.getText().toString();
                Boolean checkudeletedata = bantuDb.deleteData(nikTxt);
                if(checkudeletedata==true) {
                    Toast.makeText(MainActivity.this, "Data Terhapus", Toast.LENGTH_SHORT).show();
                }else {
                    Toast.makeText(MainActivity.this, "Data Tidak Terhapus", Toast.LENGTH_SHORT).show();
                }
            }
        });

        btnReset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                txtnama.setText("");
                txtnik.setText("");
                txtttl.setText("");
                txtalamat.setText("");
                txttelp.setText("");
            }
        });
    }

    private void vewiAll() {

        btnTampil.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Cursor res = bantuDb.getAllData();

                if (res.getCount() == 0){
                    showMessage("Error", "Tidak Ditemukan");
                    return;
                }else {
                    StringBuffer buffer = new StringBuffer();
                    while (res.moveToNext()) {
                        buffer.append("Nama     : " + res.getString(0) + "\n");
                        buffer.append("NIK      : " + res.getString(1) + "\n");
                        buffer.append("TTL      : " + res.getString(2) + "\n");
                        buffer.append("Alamat   : " + res.getString(3) + "\n");
                        buffer.append("No. Telp : " + res.getString(4) + "\n");
                        buffer.append("Gender   : " + res.getString(5) + "\n\n");
                    }
                    showMessage("Data Penduduk", buffer.toString());
                }
            }
        });
    }

    public void showMessage(String judul, String Message){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);

        builder.setCancelable(true);
        builder.setTitle(judul);
        builder.setMessage(Message);
        builder.show();
    }
}