package com.push.penjadwalansesi;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    public static final String URL = "http://farhanaffandi.000webhostapp.com/";
    private RadioButton radioSesiBtn;
    private ProgressDialog progress;

    @BindView(R.id.radioSesi) RadioGroup radioGroup;
    @BindView(R.id.editNim) EditText editNim;
    @BindView(R.id.editNama) EditText editNama;
    @BindView(R.id.editKelas) EditText editKelas;

    @OnClick(R.id.btnDaftar) void daftar(){
        progress = new ProgressDialog(this);
        progress.setCancelable(false);
        progress.setMessage("Memuat ...");
        progress.show();

        String nim = editNim.getText().toString();
        String nama = editNama.getText().toString();
        String kelas = editKelas.getText().toString();

        int selectedId = radioGroup.getCheckedRadioButtonId();

        radioSesiBtn = (RadioButton) findViewById(selectedId);
        String sesi = radioSesiBtn.getText().toString();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        RegisterAPI api = retrofit.create(RegisterAPI.class);
        Call<Value> call = api.daftar(nim, nama, kelas, sesi);
        call.enqueue(new Callback<Value>() {
            @Override
            public void onResponse(Call<Value> call, Response<Value> response) {
                String value = response.body().getValue();
                String message = response.body().getMessage();
                progress.dismiss();

                if (value.equals("1")){
                    Toast.makeText(MainActivity.this, message, Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(MainActivity.this, message, Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<Value> call, Throwable t) {
                progress.dismiss();
                Toast.makeText(MainActivity.this,"Koneksi Gagal!", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @OnClick(R.id.btnLihat) void lihat(){
        startActivity(new Intent(MainActivity.this, ViewActivity.class));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
    }
}
