package com.example.aplikasisqlite;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

	RecyclerView recyclerView;
	FloatingActionButton addButton;

	MyDatabaseHelper myDatabaseHelper;
	ArrayList<String> id_mhs, nama_mhs, nim_mhs, semester_mhs;
	RecyclerAdapter recyclerAdapter;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		recyclerView = findViewById(R.id.recyclerviewmahasiswa);
		addButton = findViewById(R.id.addbutton);

		addButton.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				Intent intent = new Intent(MainActivity.this, AddActivity.class);
				startActivity(intent);
			}
		});

		myDatabaseHelper = new MyDatabaseHelper(MainActivity.this);
		id_mhs = new ArrayList<>();
		nama_mhs = new ArrayList<>();
		nim_mhs = new ArrayList<>();
		semester_mhs = new ArrayList<>();

		storeDataInArrays();

		recyclerAdapter = new RecyclerAdapter(MainActivity.this, this, id_mhs, nama_mhs, nim_mhs, semester_mhs);
		recyclerView.setAdapter(recyclerAdapter);
		recyclerView.setLayoutManager(new LinearLayoutManager(MainActivity.this));

	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
		super.onActivityResult(requestCode, resultCode, data);
		if(requestCode == 1){
			recreate();
		}
	}

	void storeDataInArrays(){
		Cursor cursor = myDatabaseHelper.readAllData();
		if(cursor.getCount()==0){
			Toast.makeText(MainActivity.this, "Tidak ada data!", Toast.LENGTH_LONG).show();
		}else{
			while(cursor.moveToNext()){
				id_mhs.add(cursor.getString(0));
				nama_mhs.add(cursor.getString(1));
				nim_mhs.add(cursor.getString(2));
				semester_mhs.add(cursor.getString(3));
			}
		}
	}
}