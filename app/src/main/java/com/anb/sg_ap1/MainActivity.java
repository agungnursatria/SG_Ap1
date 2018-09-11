package com.anb.sg_ap1;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.anb.sg_ap1.model.User;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    TextInputLayout til_nama;
    EditText et_umur;
    Spinner spinner_gender;
    Button btn_process;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        // Inisialiasi pengenalan widget pada layout
        til_nama = findViewById(R.id.til_nama);
        et_umur = findViewById(R.id.et_umur);
        spinner_gender = findViewById(R.id.spinner_gender);
        btn_process = findViewById(R.id.btn_process);

        btn_process.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_process :
                validateData();
                break;
        }
    }

    void validateData(){
        if (til_nama.getEditText() != null){
            // Jika Nama dan Umur tidak kosong
            if (!til_nama.getEditText().getText().toString().equals("") && !et_umur.getText().toString().equals("")){
                User user = new User();
                user.nama = til_nama.getEditText().getText().toString();
                user.umur = Integer.parseInt(et_umur.getText().toString());
                user.gender = spinner_gender.getSelectedItem().toString();

                processData(user);
            }else{
                Toast.makeText(this, "Data Tidak Boleh Kosong", Toast.LENGTH_SHORT).show();
            }
        }
    }

    private void processData(User user) {
        Intent intent = new Intent(this, ListActivity.class);
        intent.putExtra("nama", user.nama);
        intent.putExtra("umur", user.umur);
        intent.putExtra("gender", user.gender);
        startActivity(intent);
    }
}
