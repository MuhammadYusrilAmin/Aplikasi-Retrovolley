package com.example.retrofit3.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.retrofit3.API.APIRequestData;
import com.example.retrofit3.API.RetroServer;
import com.example.retrofit3.Model.ResponseModel;
import com.example.retrofit3.R;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class UbahActivity extends AppCompatActivity {
    private int xId;
    private String xNama, xEmail, xPassword;
    private EditText etNama, etEmail, etTelepon;
    private Button btnUbah;
    private String yNama, yEmail, yPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ubah);

        Intent terima = getIntent();
        xId = terima.getIntExtra("xId", -1);
        xNama = terima.getStringExtra("xNama");
        xEmail = terima.getStringExtra("xEmail");
        xPassword = terima.getStringExtra("xPassword");

        etNama = findViewById(R.id.et_nama);
        etEmail = findViewById(R.id.et_Email);
        etTelepon = findViewById(R.id.et_Password);
        btnUbah = findViewById(R.id.btn_ubah);

        etNama.setText(xNama);
        etEmail.setText(xEmail);
        etTelepon.setText(xPassword);

        btnUbah.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                yNama = etNama.getText().toString();
                yEmail = etEmail.getText().toString();
                yPassword = etTelepon.getText().toString();

                updateData();
            }
        });
    }

    private void updateData(){
        APIRequestData ardData = RetroServer.konekRetrofit().create(APIRequestData.class);
        Call<ResponseModel> ubahData = ardData.ardUpdateData(xId, yNama, yEmail, yPassword);

        ubahData.enqueue(new Callback<ResponseModel>() {
            @Override
            public void onResponse(Call<ResponseModel> call, Response<ResponseModel> response) {
                int kode = response.body().getKode();
                String pesan = response.body().getPesan();

                Toast.makeText(UbahActivity.this, "Kode : "+kode+" | Pesan : "+pesan, Toast.LENGTH_SHORT).show();
                finish();
            }

            @Override
            public void onFailure(Call<ResponseModel> call, Throwable t) {
                Toast.makeText(UbahActivity.this, "Gagal Menghubungi Server | "+t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}