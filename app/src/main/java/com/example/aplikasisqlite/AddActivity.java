package com.example.aplikasisqlite;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class AddActivity extends AppCompatActivity {

	EditText inputNamaMhs, inputNIMMhs, inputSemesterMhs;
	Button addButton;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_add);

		inputNamaMhs = findViewById(R.id.inputNamaMhs);
		inputNIMMhs = findViewById(R.id.inputNIMMhs);
		inputSemesterMhs = findViewById(R.id.inputSemesterMhs);

		addButton = findViewById(R.id.buttonAddMhs);

		addButton.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				MyDatabaseHelper myHelper = new MyDatabaseHelper(AddActivity.this);
				myHelper.addMahasiswa(inputNamaMhs.getText().toString().trim(),
						Integer.valueOf(inputNIMMhs.getText().toString().trim()),
						Integer.valueOf(inputSemesterMhs.getText().toString().trim())
					);
			}
		});

	}
}