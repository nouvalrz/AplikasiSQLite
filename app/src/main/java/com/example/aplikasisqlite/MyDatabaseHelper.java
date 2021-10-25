package com.example.aplikasisqlite;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class MyDatabaseHelper extends SQLiteOpenHelper {

	private Context context;
	private static final String DATABASE_NAME = "db.mahasiswa";
	private static final int DATABASE_VERSION = 1;

	private static final String TABLE_NAME = "tb_mahasiswa";
	private static final String COLUMN_ID = "id";
	private static final String COLUMN_NAME = "nama_mahasiswa";
	private static final String COLUMN_NIM = "nim_mahasiswa";
	private static final String COLUMN_SEMESTER = "semester_mahasiswa";



	public MyDatabaseHelper(@Nullable Context context) {
		super(context, DATABASE_NAME, null, DATABASE_VERSION);
		this.context = context;
	}



	@Override
	public void onCreate(SQLiteDatabase sqLiteDatabase) {
		String query =
			"CREATE TABLE " + TABLE_NAME +
				" (" + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
				COLUMN_NAME + " TEXT, " + COLUMN_NIM + " TEXT, " + COLUMN_SEMESTER + " INTEGER);";
		sqLiteDatabase.execSQL(query);
	}

	@Override
	public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
		sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
		onCreate(sqLiteDatabase);
	}

	void addMahasiswa(String nama, int nim, int semester){
		SQLiteDatabase db = this.getWritableDatabase();
		ContentValues cv = new ContentValues();

		cv.put(COLUMN_NAME, nama);
		cv.put(COLUMN_NIM, nim);
		cv.put(COLUMN_SEMESTER, semester);

		long result = db.insert(TABLE_NAME, null, cv);

		if (result==-1){
			Toast.makeText(context, "Input data gagal!", Toast.LENGTH_LONG).show();
		}else{
			Toast.makeText(context, "Input data berhasil!", Toast.LENGTH_LONG).show();
		}
	}

	Cursor readAllData(){
		String query = "SELECT * FROM " + TABLE_NAME;
		SQLiteDatabase db = this. getReadableDatabase();

		Cursor cursor = null;
		if(db != null){
			cursor = db.rawQuery(query, null);
		}
		return cursor;
	}

	void updateData(String row_id, String namaMhs, String nimMhs, String semesterMhs){
		SQLiteDatabase db = this.getWritableDatabase();
		ContentValues cv = new ContentValues();
		cv.put(COLUMN_NAME, namaMhs);
		cv.put(COLUMN_NIM, nimMhs);
		cv.put(COLUMN_SEMESTER, semesterMhs);

		long result = db.update(TABLE_NAME, cv, "id=?", new String[]{row_id});
		if (result == -1){
			Toast.makeText(context, "Update data gagal!", Toast.LENGTH_LONG).show();
		}else{
			Toast.makeText(context, "Update berhasil!", Toast.LENGTH_LONG).show();
		}

	}

	void deleteRow(String row_id){
		SQLiteDatabase db = this.getWritableDatabase();
		long result = db.delete(TABLE_NAME, "id=?", new String[]{row_id});
		if (result == -1){
			Toast.makeText(context, "Delete data gagal!", Toast.LENGTH_LONG).show();
		}else{
			Toast.makeText(context, "Delete berhasil!", Toast.LENGTH_LONG).show();
		}


	}
}
