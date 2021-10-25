package com.example.aplikasisqlite;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.MyViewHolder> {

	private Context context;
	private ArrayList mhs_id, mhs_nama, mhs_nim, mhs_semester;
	int position;
	Activity activity;

	RecyclerAdapter(Activity activity, Context context, ArrayList mhs_id, ArrayList mhs_nama, ArrayList mhs_nim, ArrayList mhs_semester){
		this.activity = activity;
		this.context = context;
		this.mhs_id = mhs_id;
		this.mhs_nama = mhs_nama;
		this.mhs_nim = mhs_nim;
		this.mhs_semester = mhs_semester;
	}


	@NonNull
	@Override
	public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
		LayoutInflater inflater = LayoutInflater.from(context);
		View view = inflater.inflate(R.layout.my_row, parent, false);
		return new MyViewHolder(view);
	}

	@Override
	public void onBindViewHolder(@NonNull RecyclerAdapter.MyViewHolder holder, @SuppressLint("RecyclerView") int position) {
		this.position = position;

		holder.mhs_id.setText(String.valueOf(mhs_id.get(position)));
		holder.mhs_nama.setText(String.valueOf(mhs_nama.get(position)));
		holder.mhs_nim.setText(String.valueOf(mhs_nim.get(position)));
		holder.mhs_semester.setText("Semester " + String.valueOf(mhs_semester.get(position)));
		holder.rowLayout.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				Intent intent = new Intent(context, UpdateActivity.class);
				intent.putExtra("id", String.valueOf(mhs_id.get(position)));
				intent.putExtra("nama", String.valueOf(mhs_nama.get(position)));
				intent.putExtra("nim", String.valueOf(mhs_nim.get(position)));
				intent.putExtra("semester", String.valueOf(mhs_semester.get(position)));
				activity.startActivityForResult(intent, 1);
//				context.startActivity(intent);
			}
		});

	}

	@Override
	public int getItemCount() {
		return mhs_id.size();
	}

	public class MyViewHolder extends RecyclerView.ViewHolder{

		TextView mhs_id, mhs_nama, mhs_nim, mhs_semester;
		LinearLayout rowLayout;

		public MyViewHolder(@NonNull View itemView) {
			super(itemView);
			mhs_id = itemView.findViewById(R.id.mhs_id);
			mhs_nama = itemView.findViewById(R.id.mhs_nama);
			mhs_nim = itemView.findViewById(R.id.mhs_nim);
			mhs_semester = itemView.findViewById(R.id.mhs_semester);
			rowLayout = itemView.findViewById(R.id.rowLayout);

		}
	}
}
