package com.sonhoai.sonho.lab5.B1.B2;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.sonhoai.sonho.lab5.R;

public class AddNewToDoList extends AppCompatActivity {
    private Button btnAdd;
    private EditText edtName, edtDate;
    private String ID = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_new_to_do_list);

        edtDate = findViewById(R.id.edtDate);
        edtName = findViewById(R.id.edtContent);
        btnAdd = findViewById(R.id.btnAdd);

        Bundle bundle = getIntent().getExtras();
        if(bundle.getString("ID").equals("ADD")){
            ID = "ADD";
        }else if(!bundle.getString("ID").equals("ADD")){
            ID = "EDIT";
            edtDate.setText(bundle.getString("Date"));
            edtName.setText(bundle.getString("Content"));
        }
        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(AddNewToDoList.this, B1_todolist.class);
                intent.putExtra("CONTENT", edtName.getText().toString());
                intent.putExtra("DATE", edtDate.getText().toString());
                intent.putExtra("ID", ID);
                setResult(RESULT_OK, intent);
                finish();
            }
        });
    }
}
