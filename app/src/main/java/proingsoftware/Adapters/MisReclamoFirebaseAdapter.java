package proingsoftware.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import proingsoftware.model.ReclamoFirebase;

public class MisReclamoFirebaseAdapter extends RecyclerView.Adapter<MisReclamoFirebaseAdapter.MyViewHolder> {

    Context context;
    ArrayList<ReclamoFirebase> reclamos;

    public MisReclamoFirebaseAdapter(Context c , ArrayList<ReclamoFirebase> p)
    {
        context = c;
        reclamos = p;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new MyViewHolder(LayoutInflater.from(context).inflate(R.layout.reclamo_item,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.descripcion.setText(reclamos.get(position).getExt());
        holder.nombre.setText(reclamos.get(position).getNombre());
        holder.prodserv.setText(reclamos.get(position).getProducto());
        Picasso.get().load(reclamos.get(position).getFoto()).into(holder.fotoRec);
    }

    @Override
    public int getItemCount() {
        return reclamos.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder
    {
        TextView nombre, descripcion, prodserv;
        ImageView fotoRec;
        public MyViewHolder(View itemView) {
            super(itemView);
            nombre = (TextView) itemView.findViewById(R.id.nombreRec);
            descripcion = (TextView) itemView.findViewById(R.id.descripcionRec);
            prodserv= (TextView) itemView.findViewById(R.id.prodservRec);
            fotoRec = (ImageView) itemView.findViewById(R.id.fotoRec);
         }
    }
}