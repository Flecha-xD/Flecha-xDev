package com.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.R;
import com.model.Reclamo;

import java.util.List;

public class ReclamoAdapter extends RecyclerView.Adapter<ReclamoAdapter.ReclamoViewHolder> {

    private Context context;
    private List<Reclamo> reclamoList;
    private LayoutInflater inflater;

    public ReclamoAdapter (Context context, List<Reclamo> reclamoList) {
        this.context = context;
        this.reclamoList = reclamoList;
        this.inflater = LayoutInflater.from(context);
    }


    @Override
    public ReclamoViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = this.inflater.inflate(R.layout.reclamo_item, null);
        return new ReclamoViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ReclamoViewHolder reclamoViewHolder, int position) {
        reclamoViewHolder.imageView.setImageResource(this.reclamoList.get(position).getFoto());
        reclamoViewHolder.textView1.setText(this.reclamoList.get(position).getNombre());
        reclamoViewHolder.textView2.setText(this.reclamoList.get(position).getDescripcion());
        reclamoViewHolder.textView3.setText(this.reclamoList.get(position).getProdServ());
    }

    @Override
    public int getItemCount() {
        return this.reclamoList.size();
    }

    public class ReclamoViewHolder extends RecyclerView.ViewHolder {

        ImageView imageView;
        TextView textView1;
        TextView textView2;
        TextView textView3;


        public ReclamoViewHolder(View itemView) {
            super(itemView);
            this.imageView = itemView.findViewById(R.id.imageView);
            this.textView1 = itemView.findViewById(R.id.textView1);
            this.textView2 = itemView.findViewById(R.id.textView2);
            this.textView3 = itemView.findViewById(R.id.textView3);


        }
    }
}
