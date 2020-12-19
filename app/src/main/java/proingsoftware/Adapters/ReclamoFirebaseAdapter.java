package proingsoftware.Adapters;

import  android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.R;

import proingsoftware.activities.funcionario.ContactoFuncionarioActivity;
import proingsoftware.activities.funcionario.ModificarProductoActivity;
import proingsoftware.activities.funcionario.VerReclamoActivity;
import proingsoftware.model.ReclamoFirebase;

import java.util.ArrayList;

public class ReclamoFirebaseAdapter extends RecyclerView.Adapter<ReclamoFirebaseAdapter.MyViewHolder> {

    Context context;
    ArrayList<ReclamoFirebase> reclamos;
    ReclamoFirebase reclamoFirebase;
    Toast toast;
    Intent intent;

    //Firebase variables
    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference reclamoRef = database.getReference();

    public ReclamoFirebaseAdapter(Context c , ArrayList<ReclamoFirebase> p)
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
        holder.descripcion.setText(reclamos.get(position).getDescripcion());
        holder.depto.setText(reclamos.get(position).getDept());
        holder.prodserv.setText(reclamos.get(position).getProducto());
        Picasso.get().load(reclamos.get(position).getFoto()).into(holder.fotoRec);
            holder.tarjeta.setOnClickListener(new View.OnClickListener(){
                public void onClick(View view) {
                    intent = new Intent(context, VerReclamoActivity.class);
                    intent.putExtra("id", reclamos.get(position).getId());
                    intent.putExtra("nombre", reclamos.get(position).getNombre());
                    intent.putExtra("ci", reclamos.get(position).getCi());
                    intent.putExtra("ext", reclamos.get(position).getExt());
                    intent.putExtra("cel", reclamos.get(position).getCel());
                    intent.putExtra("correo", reclamos.get(position).getCorreo());
                    intent.putExtra("dept", reclamos.get(position).getDept());
                    intent.putExtra("producto", reclamos.get(position).getProducto());
                    intent.putExtra("descripcion", reclamos.get(position).getDescripcion());

                    intent.putExtra("foto", reclamos.get(position).getFoto());
                    context.startActivity(intent);
                }
            });
    }

    @Override
    public int getItemCount() {
        return reclamos.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder
    {
        String id;
        String nombre;
        String ci;
        String ext;
        String cel;
        String correo;
        TextView depto, descripcion, prodserv;
        LinearLayout tarjeta;
        ImageView fotoRec;
        public MyViewHolder(View itemView) {
            super(itemView);
            depto = (TextView) itemView.findViewById(R.id.deptoRec);
            descripcion = (TextView) itemView.findViewById(R.id.descripcionRec);
            prodserv= (TextView) itemView.findViewById(R.id.prodservRec);
            fotoRec = (ImageView) itemView.findViewById(R.id.fotoRec);
            tarjeta= (LinearLayout)itemView.findViewById(R.id.tarjetaReclamo);
         }
    }
    public void mandarReclamo(ReclamoFirebase reclamo){

    }
}