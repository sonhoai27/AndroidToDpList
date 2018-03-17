package com.sonhoai.sonho.lab5.B1;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.sonhoai.sonho.lab5.R;

import java.util.ArrayList;

public class B1_todolist extends AppCompatActivity {
    private ListView lvToDoList;
    private ListViewAdapter listViewAdapter;
    private ArrayList<GhiChu> ghiChus = new ArrayList<>();
    private DB db;
    private int Id;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.b1_todolist);
        init();
        getData();
        registerForContextMenu(lvToDoList);
    }
    private void init(){
        lvToDoList = findViewById(R.id.lvToDoList);
        listViewAdapter = new ListViewAdapter(getApplicationContext(), ghiChus);

        db = new DB(
                getApplicationContext(),
                "GHICHU.sqlite",
                null,
                1
        );
        db.query("create table if not exists " +
                "Employee(Id integer primary key autoincrement, Content varchar, Date varchar)");
    }

    public void getData(){
        Cursor note = db.getData("select * from Employee");
        while (note.moveToNext()){
            ghiChus.add(new GhiChu(note.getInt(0),note.getString(1),note.getString(2)));
        }

        listViewAdapter = new ListViewAdapter(
                getApplicationContext(),
                ghiChus
        );
        lvToDoList.setAdapter(listViewAdapter);
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.option_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId() == R.id.add_new){
            Intent intent = new Intent(B1_todolist.this, AddNewToDoList.class);
            intent.putExtra("ID", "ADD");
            startActivityForResult(intent, 123);
        }
        return false;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(requestCode == 123 && resultCode == RESULT_OK && data != null){
            String status = data.getStringExtra("ID");
            String content = data.getStringExtra("CONTENT");
            String date = data.getStringExtra("DATE");
            if(status.equals("ADD")){
                db.query("insert into Employee values(null, '"+content+"', '"+date+"')");
                ghiChus.clear();
                getData();
            }else if(!status.equals("ADD")){
                update(Id, content,date);
            }
        }
        super.onActivityResult(requestCode, resultCode, data);
    }
    public void update(int id, String content, String date){
        db.query("update Employee set Content = '"+content+"', Date = '"+date+"'  where Id = "+id);
        ghiChus.clear();
        getData();
    }
    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        MenuInflater  inflater = getMenuInflater();
        inflater.inflate(R.menu.context_menu, menu);
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
        int position = info.position;
        Id = ghiChus.get(position).getID();
        if(item.getItemId() == R.id.cmEdit){
            Intent intent = new Intent(B1_todolist.this, AddNewToDoList.class);
            intent.putExtra("ID", "EDIT");
            intent.putExtra("Content", ghiChus.get(position).getContent());
            intent.putExtra("Date", ghiChus.get(position).getDate());
            startActivityForResult(intent, 123);
            return true;
        }else if(item.getItemId() == R.id.cmDelete){
            db.query("delete from Employee where Id ="+ghiChus.get(position).getID());
            ghiChus.clear();
            getData();
            Toast.makeText(getApplicationContext(), "Xoa Thanh Cong", Toast.LENGTH_SHORT).show();
        }else if(item.getItemId() == R.id.cmDeleteAll){
            db.query("delete from Employee");
            ghiChus.clear();
            getData();
            Toast.makeText(getApplicationContext(), "Xoa Thanh Cong", Toast.LENGTH_SHORT).show();
        }
        return false;
    }
}
