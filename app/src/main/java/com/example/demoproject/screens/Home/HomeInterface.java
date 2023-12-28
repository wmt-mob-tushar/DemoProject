package com.example.demoproject.screens.Home;

import java.util.ArrayList;

public interface HomeInterface {
    interface View {
        void showTodoList(ArrayList<TodoModel> todoList);
        void onSuccess(String message);
        void onError(String message);
    }
    interface Presenter {
        void loadTodoList();
        void addTodo(TodoModel todo);
        void updateTodo(TodoModel todo);
        void deleteTodo(TodoModel todo);
    }
}
