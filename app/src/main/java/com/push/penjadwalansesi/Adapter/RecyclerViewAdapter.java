package com.push.penjadwalansesi.Adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.push.penjadwalansesi.R;
import com.push.penjadwalansesi.Result;
import com.push.penjadwalansesi.UpdateActivity;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder> {
    private Context context;
    private List<Result> results;

    public RecyclerViewAdapter(Context context, List<Result> results){
        this.context = context;
        this.results = results;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType){
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_view, parent, false);
        ViewHolder holder = new ViewHolder(v);

        return holder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position){
        Result result = results.get(position);
        holder.teksNIM.setText(result.getNim());
        holder.teksNama.setText(result.getNama());
        holder.teksKelas.setText(result.getKelas());
        holder.teksSesi.setText(result.getSesi());
    }

    @Override
    public int getItemCount(){
        return results.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        @BindView(R.id.teksNIM) TextView teksNIM;
        @BindView(R.id.teksNama) TextView teksNama;
        @BindView(R.id.teksKelas) TextView teksKelas;
        @BindView(R.id.teksSesi) TextView teksSesi;

        public ViewHolder(View itemView){
            super(itemView);
            ButterKnife.bind(this, itemView);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view){
            String nim = teksNIM.getText().toString();
            String nama = teksNama.getText().toString();
            String kelas = teksKelas.getText().toString();
            String sesi = teksSesi.getText().toString();

            Intent i = new Intent(context, UpdateActivity.class);
            i.putExtra("nim", nim);
            i.putExtra("nama", nama);
            i.putExtra("kelas", kelas);
            i.putExtra("sesi", sesi);
            context.startActivity(i);
        }


    }
}
