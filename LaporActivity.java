package com.example.smartcity;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.text.method.ScrollingMovementMethod;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

public class LaporActivity extends AppCompatActivity {
    Spinner spLokasi,spKategori;
    RadioGroup rgPilih;
    RadioButton rbYes, rbNo;
    Button btSubmitLaporan, btnUpload;
    Calendar myCalendar;
    DatePickerDialog.OnDateSetListener date;
    TextView etDate;
    ImageView fotoUpload;

    final int kodeGallery = 100, kodeKamera = 99;
    Uri imageUri;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lapor);

        fotoUpload = (ImageView) findViewById(R.id.foto_upload);
        btnUpload = (Button) findViewById(R.id.btn_Upload_Foto);

        btnUpload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(intent, kodeGallery);
            }
        });



        etDate = (TextView) findViewById(R.id.et_Date);
        myCalendar = (Calendar) Calendar.getInstance();
        date = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int monthofYear, int dayOfMonth) {
                myCalendar.set(Calendar.YEAR,year);
                myCalendar.set(Calendar.MONTH, monthofYear);
                myCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);

                TextView tanggal = (TextView) findViewById(R.id.et_Date);
                String myFormat = "dd-MMMM-yyyy";
                SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);

                tanggal.setText(sdf.format(myCalendar.getTime()));
            }
        };
        etDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new DatePickerDialog(LaporActivity.this, date,
                        myCalendar.get(Calendar.YEAR),
                        myCalendar.get(Calendar.MONTH),
                        myCalendar.get(Calendar.DAY_OF_MONTH)).show();
            }
        });

        spLokasi = (Spinner) findViewById(R.id.spLokasi);
        spKategori = (Spinner) findViewById(R.id.spKategori);

        btSubmitLaporan = (Button) findViewById(R.id.bt_submitLaporan);

        rgPilih = (RadioGroup) findViewById(R.id.rg_pilih);
        rbYes = (RadioButton) findViewById(R.id.rb_Yes);
        rbNo = (RadioButton) findViewById(R.id.rb_No);

        btSubmitLaporan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               if (rbYes.isChecked()){
                   Toast.makeText(getApplicationContext(), "CLICKED YES", Toast.LENGTH_SHORT).show();
               }else if (rbNo.isChecked()){
                   Toast.makeText(getApplicationContext(), "CLICKED NO", Toast.LENGTH_SHORT).show();
               }
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == kodeGallery && resultCode == RESULT_OK){
            imageUri = data.getData();
            fotoUpload.setImageURI(imageUri);
        }
    }
}