package com.example.loginapps;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    EditText txt_angka1;
    EditText txt_angka2;
    static SharedPreferences sharedPreferences;
    TextView tv_hasil, tv_nama;
    Button btn_tambah;
    Button btn_kurang;
    Button btn_kali;
    Button btn_bagi;
    Button btn_logout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        txt_angka1 = findViewById(R.id.txt_angka1);
        txt_angka2 = findViewById(R.id.txt_angka2);
        tv_hasil = findViewById(R.id.tv_hasil);
        tv_nama = findViewById(R.id.tv_nama);
        btn_tambah = findViewById(R.id.btn_tambah);
        btn_kurang = findViewById(R.id.btn_kurang);
        btn_kali = findViewById(R.id.btn_kali);
        btn_logout = findViewById(R.id.btn_logout);
        btn_bagi = findViewById(R.id.btn_bagi);
        btn_tambah.setOnClickListener(this);
        btn_kurang.setOnClickListener(this);
        btn_logout.setOnClickListener(this);
        btn_kali.setOnClickListener(this);
        btn_bagi.setOnClickListener(this);

        String name;
        Bundle bd = getIntent().getExtras();
        if(bd == null){
            name = "alif";
        }else{
            name = bd.getString("username");
        } tv_nama.setText(name);

    }

    @Override
    public void onClick(View v) {
        try {
            switch (v.getId()){
                case R.id.btn_tambah:
                    String angkapertama = txt_angka1.getText().toString();
                    String angkakedua = txt_angka2.getText().toString();
                    int angka1 = Integer.parseInt(angkapertama);
                    int angka2 = Integer.parseInt(angkakedua);
                    tv_hasil.setText("Hasil: "+(angka1 + angka2));
                    break;
                case R.id.btn_kurang:
                    String angkapertama1 = txt_angka1.getText().toString();
                    String angkakedua1 = txt_angka2.getText().toString();
                    int angka1_1 = Integer.parseInt(angkapertama1);
                    int angka2_1 = Integer.parseInt(angkakedua1);
                    tv_hasil.setText("Hasil: "+(angka1_1 - angka2_1));
                    break;
                case R.id.btn_kali:
                    String angkapertama2 = txt_angka1.getText().toString();
                    String angkakedua2 = txt_angka2.getText().toString();
                    int angka1_2 = Integer.parseInt(angkapertama2);
                    int angka2_2 = Integer.parseInt(angkakedua2);
                    tv_hasil.setText("Hasil: "+(angka1_2 * angka2_2));
                    break;
                case R.id.btn_bagi:
                    String angkapertama3 = txt_angka1.getText().toString();
                    String angkakedua3 = txt_angka2.getText().toString();
                    float angka1_3 = Float.parseFloat(angkapertama3);
                    float angka2_3 = Float.parseFloat(angkakedua3);
                    tv_hasil.setText("Hasil: "+(angka1_3 / angka2_3));
                    break;
                case R.id.btn_logout:
                    sharedPreferences = getSharedPreferences("login", MODE_PRIVATE);
                    sharedPreferences.edit().clear().commit();
                    Intent intent = new Intent(MainActivity.this, ClassControl.class);
                    finish();
                    startActivity(intent);
                    break;
            }
        } catch (Exception e){
            Toast.makeText(MainActivity.this, "Isi angkanya", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onBackPressed() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setCancelable(false);
        builder.setMessage("Apakah kamu ingin meninggalkan aplikasi?");
        builder.setPositiveButton("Ya", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                //if user pressed "yes", then they're  allowed to exit from application
                finish();
            }
        });
        builder.setNegativeButton("Tidak", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                //if user select "No", just cancel this dialog and continue with app
                dialog.cancel();
            }
        });
        AlertDialog alert = builder.create();
        alert.show();
    }
}
