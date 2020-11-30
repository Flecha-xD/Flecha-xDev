package com.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.R;
import com.model.Normativa;

import java.util.List;

public class NormativaAdapter extends RecyclerView.Adapter<NormativaAdapter.NormativaViewHolder> {

    private Context context;
    private List<Normativa> normativaList;
    private LayoutInflater inflater;

    public NormativaAdapter (Context context, List<Normativa> normativaList) {
        this.context = context;
        this.normativaList = normativaList;
        this.inflater = LayoutInflater.from(context);
    }


    @Override
    public NormativaViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = this.inflater.inflate(R.layout.normativa_item, null);
        return new NormativaViewHolder(view);
    }

    @Override
    public void onBindViewHolder( NormativaViewHolder normativaViewHolder, int position) {
        normativaViewHolder.nombre.setText(this.normativaList.get(position).getNombre());
        normativaViewHolder.info.setText(this.normativaList.get(position).getInformacion());
    }

    @Override
    public int getItemCount() {
        return this.normativaList.size();
    }

    public class NormativaViewHolder extends RecyclerView.ViewHolder {


        TextView nombre;
        TextView info;


        public NormativaViewHolder(View itemView) {
            super(itemView);
            this.nombre = itemView.findViewById(R.id.nombre);
            this.info= itemView.findViewById(R.id.informacion);


        }
    }
}
