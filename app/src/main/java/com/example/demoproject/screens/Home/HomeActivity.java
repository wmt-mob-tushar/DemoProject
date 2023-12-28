package com.example.demoproject.screens.Home;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import com.example.demoproject.Adapter.RecycleTodoAdapter;
import com.example.demoproject.R;
import com.example.demoproject.database.SQLiteDbHelper;
import com.example.demoproject.screens.Update.UpdateActivity;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class HomeActivity extends AppCompatActivity implements HomeInterface.View, View.OnClickListener{
    Toolbar toolbar;
    ArrayList<TodoModel> arrayList = new ArrayList<>();
    RecyclerView recyclerView;
    EditText etTitle,etDescription;
    Button btnAdd; 
    FloatingActionButton btnAddTodo;
    RecycleTodoAdapter adapter;
    SQLiteDbHelper database;
    ImageButton profilebtn;
    Intent intent;
    HomePresenter homePresenter;
    TodoModel todoModel;
    Dialog dialog;
    String title,description;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        database = new SQLiteDbHelper(this);
        homePresenter = new HomePresenter(this);

        this.initializeView();
        CustomeToolbar();

        btnAddTodo.setOnClickListener(this);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        arrayList = database.getAllTodo();
        adapter = new RecycleTodoAdapter(this,arrayList,homePresenter);
        recyclerView.setAdapter(adapter);
    }

    private void initializeView() {
        btnAddTodo = findViewById(R.id.btn_float_dialog);
        recyclerView = findViewById(R.id.rv_todolist);
        profilebtn = findViewById(R.id.btn_profile);
        toolbar = findViewById(R.id.toolbar);
        //Recycle view declaration
        recyclerView = (RecyclerView) findViewById(R.id.rv_todolist);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_float_dialog:
                OpenDialog();
                break;
            case R.id.btn_add_update:
                HandleTodoDialog();
                break;
        }
    }

    private void OpenDialog(){
        dialog = new Dialog(HomeActivity.this);
        dialog.setContentView(R.layout.add_update_dialog);

        etTitle = dialog.findViewById(R.id.et_title);
        etDescription = dialog.findViewById(R.id.et_description);
        btnAdd = dialog.findViewById(R.id.btn_add_update);

        btnAdd.setOnClickListener(this);

        dialog.show();
    }

    private void HandleTodoDialog() {
        title = etTitle.getText().toString();
        description = etDescription.getText().toString();

        todoModel = new TodoModel();
        todoModel.setTitle(title);
        todoModel.setDescription(description);

        homePresenter.addTodo(todoModel);
        showTodoList(arrayList);
        dialog.dismiss();
    }

    @Override
    public void showTodoList(ArrayList<TodoModel> todoList) {
        adapter = new RecycleTodoAdapter(this, todoList, homePresenter);
        recyclerView.setAdapter(adapter);
    }

    private void CustomeToolbar() {
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Todo List");
        toolbar.setTitleTextColor(getResources().getColor(R.color.white));

        profilebtn.setOnClickListener(view -> {
            intent = new Intent(HomeActivity.this, UpdateActivity.class);
            startActivity(intent);
        });
    }

    @Override
    public void onSuccess(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
        arrayList = database.getAllTodo();
        adapter = new RecycleTodoAdapter(this,arrayList,homePresenter);
        recyclerView.setAdapter(adapter);
    }
    @Override
    public void onError(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }

}