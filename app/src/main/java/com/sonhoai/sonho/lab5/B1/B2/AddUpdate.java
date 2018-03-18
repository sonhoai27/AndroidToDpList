package com.sonhoai.sonho.lab5.B1.B2;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.sonhoai.sonho.lab5.B1.B1_todolist;
import com.sonhoai.sonho.lab5.R;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

public class AddUpdate extends AppCompatActivity {
    private Button btnAdd;
    private EditText edtFirst, edtLast, edtHire, edtDept;
    private RadioGroup edtGender;
    private String ID = null;
    private RadioButton btnGender;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_update);

        edtFirst = findViewById(R.id.edtFirst);
        edtLast = findViewById(R.id.edtLast);
        edtGender = findViewById(R.id.edtGender);
        edtHire = findViewById(R.id.edtHire);
        edtDept = findViewById(R.id.edtDept);
        btnAdd = findViewById(R.id.btnAdd2);
        chooseBirthday();

        Bundle bundle = getIntent().getExtras();
        if(bundle.getString("ID").equals("ADD")){
            ID = "ADD";
        }else if(!bundle.getString("ID").equals("ADD")){
            ID = "EDIT";
            edtFirst.setText(bundle.getString("First"));
            edtLast.setText(bundle.getString("LastName"));
            edtHire.setText(bundle.getString("Hire"));
            edtDept.setText(bundle.getString("Dept"));
        }
        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(AddUpdate.this, B2.class);
                int selectedId = edtGender.getCheckedRadioButtonId();
                // find the radiobutton by returned id
                btnGender = findViewById(selectedId);
                intent.putExtra("ID", ID);
                intent.putExtra("First", edtFirst.getText().toString());
                intent.putExtra("LastName", edtLast.getText().toString());
                intent.putExtra("Gender",  btnGender.getText().toString());
                intent.putExtra("Hire", edtHire.getText().toString());
                intent.putExtra("Dept", edtDept.getText().toString());
                setResult(1333, intent);
                finish();
            }
        });
    }

    private void chooseBirthday(){
        final Calendar calendar = Calendar.getInstance();
        final DatePickerDialog.OnDateSetListener date = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                calendar.set(Calendar.YEAR, year);
                calendar.set(Calendar.MONTH, month);
                calendar.set(Calendar.DAY_OF_MONTH, day);
                String calenderFormat = "yyyy-MM-dd";
                SimpleDateFormat sdf = new SimpleDateFormat(calenderFormat, Locale.US);
                edtHire.setText(sdf.format(calendar.getTime()));
            }
        };
        edtHire.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.N)
            @Override
            public void onClick(View view) {
                new DatePickerDialog(
                        view.getContext(),
                        date,
                        calendar.get(Calendar.YEAR),
                        calendar.get(Calendar.MONTH),
                        calendar.get(Calendar.DAY_OF_MONTH)
                ).show();
            }
        });
    }
}
