package proingsoftware.Adapters;

import  android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.R;

import proingsoftware.activities.funcionario.ModificarProductoActivity;
import proingsoftware.activities.funcionario.VerReclamoActivity;
import proingsoftware.model.Reclamo;

import java.util.List;

public class ReclamoAdapter extends RecyclerView.Adapter<ReclamoAdapter.ReclamoViewHolder> {
    public void setPermitido(boolean permitido) {
        this.permitido = permitido;
    }

    public boolean isPermitido() {
        return permitido;
    }

    public boolean permitido;
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

        if (permitido)
        reclamoViewHolder.tarjeta.setOnClickListener(new View.OnClickListener(){
            public void onClick(View view) {
                context.startActivity(new
                        Intent(context, VerReclamoActivity.class));
            }

        });
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
        LinearLayout tarjeta;


        public ReclamoViewHolder(View itemView) {
            super(itemView);
            this.imageView = itemView.findViewById(R.id.fotoRec);
            this.textView1 = itemView.findViewById(R.id.nombreRec);
            this.textView2 = itemView.findViewById(R.id.prodservRec);
            this.textView3 = itemView.findViewById(R.id.descripcionRec);
            this.tarjeta = itemView.findViewById(R.id.tarjetaReclamo);


        }
    }
}
