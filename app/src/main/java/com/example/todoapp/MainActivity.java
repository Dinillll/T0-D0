package com.example.todoapp;

import android.content.Intent;
import android.os.Bundle;


import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.view.View;

import androidx.navigation.ui.AppBarConfiguration;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.todoapp.databinding.ActivityMainBinding;

import android.view.Menu;
import android.view.MenuItem;

import java.util.List;

import adapters.RecyclerItemTouchHelper;
import adapters.TodoAdapter;
import model.DBModel;
import model.IModel;
import model.ToDoItem;

public class MainActivity extends AppCompatActivity {

    public static final int RQC_NEW = 1;
    public static final int RQC_EDIT = 2;
    private AppBarConfiguration appBarConfiguration;
    private ActivityMainBinding binding;
    private List<ToDoItem> items;
    private RecyclerView rvItems;
    private TodoAdapter adapter;
    private IModel model;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        setSupportActionBar(binding.toolbar);

        binding.fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AddToDo();
            }
        });
        model = new DBModel(this);
        items=model.getAllToDoItem();
                rvItems = findViewById(R.id.rvItems);
        adapter = new TodoAdapter(items, R.layout.list_item, this, model);

        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(new RecyclerItemTouchHelper(adapter));
        itemTouchHelper.attachToRecyclerView(rvItems);

        rvItems.setAdapter(adapter);
        rvItems.setLayoutManager(new LinearLayoutManager(this));

    }

    public void AddToDo() {
        Intent intent = new Intent(this, ToDoActivity.class);
        startActivityForResult(intent, RQC_NEW);

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            ToDoItem tdi = (ToDoItem) data.getSerializableExtra("tdi");
            if (requestCode == RQC_NEW) {
                items.add(tdi);
                model.saveOrUdpate(tdi);
                adapter.notifyItemInserted(items.size() - 1);
            } else if (requestCode == RQC_EDIT) {
                int position = data.getIntExtra("index", -1);
                items.set(position, tdi);
                model.saveOrUdpate(tdi);
                adapter.notifyItemChanged(position);
            }
        }

    }

}