package com.sonhoai.sonho.lab5.B1.B2;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.sonhoai.sonho.lab5.B1.B1_todolist;
import com.sonhoai.sonho.lab5.R;

public class AddUpdate extends AppCompatActivity {
    private Button btnAdd;
    private EditText edtFirst, edtLast, edtGender, edtHire, edtDept;
    private String ID = null;

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

        Bundle bundle = getIntent().getExtras();
        if(bundle.getString("ID").equals("ADD")){
            ID = "ADD";
        }else if(!bundle.getString("ID").equals("ADD")){
            ID = "EDIT";
            edtFirst.setText(bundle.getString("First"));
            edtLast.setText(bundle.getString("LastName"));
            edtGender.setText(bundle.getString("Gender"));
            edtHire.setText(bundle.getString("Hire"));
            edtDept.setText(bundle.getString("Dept"));
        }
        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(AddUpdate.this, B2.class);
                intent.putExtra("ID", ID);
                intent.putExtra("First", edtFirst.getText());
                intent.putExtra("LastName", edtLast.getText());
                intent.putExtra("Gender", edtGender.getText());
                intent.putExtra("Hire", edtHire.getText());
                intent.putExtra("Dept", edtDept.getText());
                setResult(RESULT_OK, intent);
                finish();
            }
        });
    }
}
