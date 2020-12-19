package proingsoftware.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import proingsoftware.model.ProductoFirebase;

public class SubsidioFirebaseAdapter extends RecyclerView.Adapter<SubsidioFirebaseAdapter.MyViewHolder> {

    Context context;
    ArrayList<ProductoFirebase> productos;

    public SubsidioFirebaseAdapter(Context c , ArrayList<ProductoFirebase> p)
    {
        context = c;
        productos = p;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new MyViewHolder(LayoutInflater.from(context).inflate(R.layout.producto_item,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.descripcion.setText(productos.get(position).getDescripcion());
        holder.nombre.setText(productos.get(position).getNombre());
        holder.precio.setText(productos.get(position).getPrecio());
        Picasso.get().load(productos.get(position).getFoto()).into(holder.fotoSub);
    }

    @Override
    public int getItemCount() {
        return productos.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder
    {
        TextView nombre, descripcion, precio;
        ImageView fotoSub;
        public MyViewHolder(View itemView) {
            super(itemView);
            nombre = (TextView) itemView.findViewById(R.id.nombreSub);
            descripcion = (TextView) itemView.findViewById(R.id.descSub);
            precio= (TextView) itemView.findViewById(R.id.precioSub);
           fotoSub = (ImageView) itemView.findViewById(R.id.fotoSub);
         }
    }
}