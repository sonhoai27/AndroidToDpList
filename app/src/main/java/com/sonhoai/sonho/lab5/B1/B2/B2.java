package com.sonhoai.sonho.lab5.B1.B2;
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

public class B2 extends AppCompatActivity {
    private ListView lvToDoList;
    private ListViewAdapter listViewAdapter;
    private ArrayList<Employee> employees = new ArrayList<>();
    private DB db;
    private int Id;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.b2_activity);
        init();
        getData();
        registerForContextMenu(lvToDoList);
    }
    private void init(){
        lvToDoList = findViewById(R.id.lvEmployee);
        listViewAdapter = new ListViewAdapter(getApplicationContext(), employees);

        db = new DB(
                getApplicationContext(),
                "EMPLOYEE.sqlite",
                null,
                1
        );
        db.query("create table if not exists " +
                "Employee(Id integer primary key autoincrement, FirstName varchar, LastName varchar, Gender varchar, Hire varchar, Dept varchar)");
    }

    public void getData(){
        Cursor person = db.getData("select * from Employee");
        while (person.moveToNext()){
            employees.add(
                    new Employee(
                            person.getInt(0),
                            person.getString(1),
                            person.getString(2),
                            person.getString(3),
                            person.getString(4),
                            person.getString(5)
                    ));
        }

        listViewAdapter = new ListViewAdapter(
                getApplicationContext(),
                employees
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
            Intent intent = new Intent(B2.this, AddUpdate.class);
            intent.putExtra("ID", "ADD");
            startActivityForResult(intent, 1234);
        }
        return false;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        if(requestCode == 1234 && resultCode == 1333 && data != null){
            String status = data.getStringExtra("ID");
            String firstname = data.getStringExtra("First");
            String lastname = data.getStringExtra("LastName");
            String gender = data.getStringExtra("Gender");
            String hire = data.getStringExtra("Hire");
            String dept = data.getStringExtra("Dept");
            if(status.equals("ADD")){
                Log.i("AAAAA", data.getStringExtra("ID"));
                db.query("insert into Employee values(null, '"+firstname+"', '"+lastname+"', '"+gender+"', '"+hire+"', '"+dept+"')");
                employees.clear();
                getData();
            }else if(!status.equals("ADD")){
                update(Id, firstname,lastname,gender,hire,dept);
            }
        }
        super.onActivityResult(requestCode, resultCode, data);
    }
    public void update(int id, String first, String last, String gender, String hire, String dept){
        db.query("update Employee set FirstName = '"+first+"', LastName = '"+last+"', Gender ='"+gender+"', Hire='"+hire+"',Dept='"+dept+"' where Id = "+id);
        employees.clear();
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
        Id = employees.get(position).getID();
        if(item.getItemId() == R.id.cmEdit){
            Intent intent = new Intent(B2.this, AddUpdate.class);
            intent.putExtra("ID", "EDIT");
            intent.putExtra("First", employees.get(position).getFistName());
            intent.putExtra("LastName", employees.get(position).getLastName());
            intent.putExtra("Gender", employees.get(position).getGender());
            intent.putExtra("Hire", employees.get(position).getHireDate());
            intent.putExtra("Dept", employees.get(position).getDept());
            startActivityForResult(intent, 1234);
            return true;
        }else if(item.getItemId() == R.id.cmDelete){
            db.query("delete from Employee where Id ="+ employees.get(position).getID());
            employees.clear();
            getData();
            Toast.makeText(getApplicationContext(), "Xoa Thanh Cong", Toast.LENGTH_SHORT).show();
        }else if(item.getItemId() == R.id.cmDeleteAll){
            db.query("delete from Employee");
            employees.clear();
            getData();
            Toast.makeText(getApplicationContext(), "Xoa Thanh Cong", Toast.LENGTH_SHORT).show();
        }
        return false;
    }
}
