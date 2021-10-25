package com.example.aplikasisqlite;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class UpdateActivity extends AppCompatActivity {

	EditText inputMhsNama_edit, inputMhsNIM_edit, inputMhsSemester_edit;
	Button buttonUpdateMhs, buttonDeleteMhs;

	String id, nama, nim, semester;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_update);

		inputMhsNama_edit = findViewById(R.id.inputNamaMhs_edit);
		inputMhsNIM_edit = findViewById(R.id.inputNIMMhs_edit);
		inputMhsSemester_edit = findViewById(R.id.inputSemesterMhs_edit);

		buttonUpdateMhs = findViewById(R.id.buttonUpdateMhs);
		buttonDeleteMhs = findViewById(R.id.buttonDeleteMhs);

		getAndSetIntentData();

		buttonUpdateMhs.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				MyDatabaseHelper myHelper = new MyDatabaseHelper(UpdateActivity.this);
				nama = inputMhsNama_edit.getText().toString().trim();
				nim = inputMhsNIM_edit.getText().toString().trim();
				semester = inputMhsSemester_edit.getText().toString().trim();
				myHelper.updateData(id, nama, nim, semester);
			}
		});

		buttonDeleteMhs.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				MyDatabaseHelper myHelper = new MyDatabaseHelper(UpdateActivity.this);
				myHelper.deleteRow(id);
				finish();
			}
		});

	}

	void getAndSetIntentData(){
		if(getIntent().hasExtra("id") && getIntent().hasExtra("nama") && getIntent().hasExtra("nim") && getIntent().hasExtra("semester")){
			id = getIntent().getStringExtra("id");
			nama = getIntent().getStringExtra("nama");
			nim = getIntent().getStringExtra("nim");
			semester = getIntent().getStringExtra("semester");

			inputMhsNama_edit.setText(nama);
			inputMhsNIM_edit.setText(nim);
			inputMhsSemester_edit.setText(semester);

		}else{
			Toast.makeText(this, "Data tidak ditemukan!", Toast.LENGTH_SHORT).show();
		}
	}
}