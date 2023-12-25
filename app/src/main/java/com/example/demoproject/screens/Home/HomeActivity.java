package com.example.demoproject.screens.Home;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ActionBar;
import android.app.Dialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import com.example.demoproject.Adapter.RecycleTodoAdapter;
import com.example.demoproject.R;
import com.example.demoproject.database.MyTodoDbHelper;
import com.example.demoproject.screens.SignUp.MainActivity;
import com.example.demoproject.screens.Update.UpdateActivity;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class HomeActivity extends AppCompatActivity {

    Toolbar toolbar;

    ArrayList<TodoModel> arrayList = new ArrayList<>();
    RecyclerView recyclerView;
    FloatingActionButton btnAddContact;
    RecycleTodoAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        //create a custom toolbar (title and user profile button)
        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("To do list");
        ImageButton profilebtn = findViewById(R.id.btn_profile);

//        get data from login activity
        Intent intent = getIntent();
        String email = intent.getStringExtra("email");

        profilebtn.setOnClickListener(view -> {
            Intent hometoprofile = new Intent(HomeActivity.this, UpdateActivity.class);

            startActivity(hometoprofile);
        });


        // todo logic

        //Recycle view declaration
        recyclerView = (RecyclerView) findViewById(R.id.rv_todolist);

        //float btn to add new contact and create dilog to add new contact
        btnAddContact = findViewById(R.id.btn_float_dialog);

        btnAddContact.setOnClickListener(v -> {

            Dialog dialog = new Dialog(HomeActivity.this);
            dialog.setContentView(R.layout.add_update_dialog);

            EditText etTitle = dialog.findViewById(R.id.et_title);
            EditText etDescription = dialog.findViewById(R.id.et_description);
            Button btnAdd = dialog.findViewById(R.id.btn_add_update);

            btnAdd.setOnClickListener(v1 -> {

                String title = "",description = "";

                if(etTitle.getText().toString().isEmpty()){
                    Toast.makeText(this, "Please enter name", Toast.LENGTH_SHORT).show();
                }else {
                    title= etTitle.getText().toString();
                }

                if(etDescription.getText().toString().isEmpty()){
                    Toast.makeText(this, "Please enter number", Toast.LENGTH_SHORT).show();
                }else {
                    description = etDescription.getText().toString();
                }

                if(!title.isEmpty() && !description.isEmpty()){
                    MyTodoDbHelper database = new  MyTodoDbHelper(this);
                    database.insertData(title,description);
                    arrayList = database.getAllData();
                    adapter = new RecycleTodoAdapter(this,arrayList);
                    recyclerView.setAdapter(adapter);
                    dialog.dismiss();
                }
            });

            dialog.show();
        });

        recyclerView.setLayoutManager(new LinearLayoutManager(this));// define layout of recycle views

        //get data from database
        MyTodoDbHelper myDbHelper = new  MyTodoDbHelper(this);
        arrayList = myDbHelper.getAllData();

        adapter = new RecycleTodoAdapter(this,arrayList);
        recyclerView.setAdapter(adapter);
    }
}