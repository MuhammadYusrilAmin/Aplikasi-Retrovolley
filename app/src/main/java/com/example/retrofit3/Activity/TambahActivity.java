package com.example.retrofit3.Activity;

import androidx.appcompat.app.AppCompatActivity;


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

public class TambahActivity extends AppCompatActivity {
    private EditText etNama, etEmail, etPassword;
    private Button btnSimpan;
    private String nama, email, password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tambah);


        etNama = findViewById(R.id.et_nama);
        etEmail = findViewById(R.id.et_Email);
        etPassword = findViewById(R.id.et_Password);
        btnSimpan = findViewById(R.id.btn_simpan);

        btnSimpan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                nama = etNama.getText().toString();
                email = etEmail.getText().toString();
                password = etPassword.getText().toString();

                if(nama.trim().equals("")){
                    etNama.setError("Nama Harus Diisi");
                }
                else if(email.trim().equals("")){
                    etEmail.setError("Email Harus Diisi");
                }
                else if(password.trim().equals("")){
                    etPassword.setError("Password Harus Diisi");
                }
                else{
                    createData();
                }
            }
        });
    }

    private void createData(){
        APIRequestData ardData = RetroServer.konekRetrofit().create(APIRequestData.class);
        Call<ResponseModel> simpanData = ardData.ardCreateData(nama, email, password);

        simpanData.enqueue(new Callback<ResponseModel>() {
            @Override
            public void onResponse(Call<ResponseModel> call, Response<ResponseModel> response) {
                int kode = response.body().getKode();
                String pesan = response.body().getPesan();

                Toast.makeText(TambahActivity.this, "Kode : "+kode+" | Pesan : "+pesan, Toast.LENGTH_SHORT).show();
                finish();
            }

            @Override
            public void onFailure(Call<ResponseModel> call, Throwable t) {
                Toast.makeText(TambahActivity.this, "Gagal Menghubungi Server | "+t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}