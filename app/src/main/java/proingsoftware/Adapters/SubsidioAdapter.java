package proingsoftware.Adapters;

import android.content.Context;
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
import proingsoftware.model.Producto;

import java.util.List;

public class SubsidioAdapter extends RecyclerView.Adapter<SubsidioAdapter.SubsidioViewHolder> {

    public void setPermitido(boolean permitido) {
        this.permitido = permitido;
    }

    private boolean permitido;
    private Context context;
    private List<Producto> productoList;
    private LayoutInflater inflater;

    public SubsidioAdapter (Context context, List<Producto> productoList) {
        this.context = context;
        this.productoList = productoList;
        this.inflater = LayoutInflater.from(context);
    }


    @Override
    public SubsidioViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = this.inflater.inflate(R.layout.producto_item, null);
        return new SubsidioViewHolder(view);
    }

    @Override
    public void onBindViewHolder(SubsidioViewHolder subsidioViewHolder, int position) {
        subsidioViewHolder.imageView.setImageResource(this.productoList.get(position).getFoto());
        subsidioViewHolder.textView1.setText(this.productoList.get(position).getNombre());
        subsidioViewHolder.textView2.setText(this.productoList.get(position).getDescripcion());
        subsidioViewHolder.textView3.setText(this.productoList.get(position).getPrecio());
        if (permitido){
        subsidioViewHolder.carta.setOnClickListener(new View.OnClickListener(){
            public void onClick(View view) {
                context.startActivity(new
                        Intent(context, ModificarProductoActivity.class));
            }
        });}
    }

    @Override
    public int getItemCount() {
        return this.productoList.size();
    }

    public class SubsidioViewHolder extends RecyclerView.ViewHolder {

        ImageView imageView;
        TextView textView1;
        TextView textView2;
        TextView textView3;
        LinearLayout carta;


        public SubsidioViewHolder(View itemView) {
            super(itemView);
            this.imageView = itemView.findViewById(R.id.fotoSub);
            this.textView1 = itemView.findViewById(R.id.descSub);
            this.textView2 = itemView.findViewById(R.id.nombreSub);
            this.textView3 = itemView.findViewById(R.id.precioSub);
            this.carta = itemView.findViewById(R.id.carta);

        }
    }
}