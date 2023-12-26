package com.example.demoproject.Adapter;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.demoproject.R;
import com.example.demoproject.database.MyTodoDbHelper;
import com.example.demoproject.screens.Home.TodoModel;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.sql.Date;
import java.util.ArrayList;

public class RecycleTodoAdapter extends RecyclerView.Adapter<RecycleTodoAdapter.ViewHolder>{

    Context context;
    ArrayList<TodoModel> arrayList;

    public RecycleTodoAdapter(Context context, ArrayList<TodoModel> arrayList){
        this.context = context;
        this.arrayList = arrayList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.todoitems_row,parent,false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        TodoModel todoModel = arrayList.get(position);
        holder.title.setText(arrayList.get(position).title);
        holder.description.setText(arrayList.get(position).description);
        holder.date.setText(arrayList.get(position).date);

        holder.btnUpdate.setOnClickListener(v -> {
            MyTodoDbHelper database = new MyTodoDbHelper(context);

            Dialog dialog = new Dialog(context);
            dialog.setContentView(R.layout.add_update_dialog);

            EditText etTitle = dialog.findViewById(R.id.et_title);
            EditText etDescription = dialog.findViewById(R.id.et_description);
            Button btnAdd = dialog.findViewById(R.id.btn_add_update);
            TextView tvTitle = dialog.findViewById(R.id.tv_title);

            tvTitle.setText("Update Todo");
            etTitle.setText(todoModel.title);
            etDescription.setText(todoModel.description);
            btnAdd.setText("Update Todo");

            btnAdd.setOnClickListener(v1 -> {

                String title = "",description = "";

                if(etTitle.getText().toString().isEmpty()){
                    title = todoModel.title;
                }else {
                    title= etTitle.getText().toString();
                }

                if(etDescription.getText().toString().isEmpty()){
                    description = todoModel.description;
                }else {
                    description = etDescription.getText().toString();
                }

                if(!title.isEmpty() && !description.isEmpty()){
                    database.updateData(title,description, todoModel.id);
                    arrayList.clear();
                    arrayList.addAll(database.getAllData());
                    notifyDataSetChanged();
                    dialog.dismiss();
                }else {
                    Toast.makeText(context, "Please enter all fields", Toast.LENGTH_SHORT).show();
                }
            });

            dialog.show();
        });

        holder.btnDelete.setOnClickListener(v -> {
            MyTodoDbHelper database = new MyTodoDbHelper(context);

            AlertDialog.Builder builder = new AlertDialog.Builder(context);
            builder.setTitle("Delete Todo");
            builder.setMessage("Are you sure you want to delete this todo?");

            builder.setPositiveButton("Yes", (dialog, which) -> {
                database.deleteData(todoModel.id);
                arrayList.clear();
                arrayList.addAll(database.getAllData());
                notifyDataSetChanged();
            });
            builder.setNegativeButton("No", (dialog, which) -> dialog.dismiss());
            builder.show();
        });
    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder{
        TextView title,description,date;
        ImageButton btnUpdate,btnDelete;

        FloatingActionButton btn_filter;
        public ViewHolder(View itemView) {
            super(itemView);

            title = itemView.findViewById(R.id.tv_title);
            description = itemView.findViewById(R.id.tv_description);
            date = itemView.findViewById(R.id.tv_date);
            btnUpdate = itemView.findViewById(R.id.btn_update);
            btnDelete = itemView.findViewById(R.id.btn_delete);

        }
    }
}
