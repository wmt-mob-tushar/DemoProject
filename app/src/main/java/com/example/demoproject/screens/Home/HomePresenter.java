package com.example.demoproject.screens.Home;

import com.example.demoproject.database.SQLiteDbHelper;

import java.util.ArrayList;

public class HomePresenter implements HomeInterface.Presenter{
    static HomeActivity homeActivity;
    static SQLiteDbHelper database;
    ArrayList<TodoModel> arrayList;

    public HomePresenter(HomeActivity homeActivity) {
        HomePresenter.homeActivity = homeActivity;
        database = new SQLiteDbHelper(homeActivity);
    }

    @Override
    public void loadTodoList() {
        arrayList = database.getAllTodo();
        homeActivity.showTodoList(arrayList);
    }

    @Override
    public void addTodo(TodoModel todomodel) {
        if(todomodel.isValid()){
            database.insertTodo(todomodel);
            loadTodoList();
            homeActivity.onSuccess("Todo Added Successfully");
        }else{
            homeActivity.onError("Please fill all the fields");
        }
    }

    @Override
    public void updateTodo(TodoModel todo) {
        if(todo.isValid()){
            database.updateTodo(todo);
            loadTodoList();
            homeActivity.onSuccess("Todo Updated Successfully");
        }else{
            homeActivity.onError("Please fill all the fields");
        }
    }

    @Override
    public void deleteTodo(TodoModel todo) {
        database.deleteTodo(todo.getId());
        loadTodoList();
        homeActivity.onSuccess("Todo Deleted");
    }
}
