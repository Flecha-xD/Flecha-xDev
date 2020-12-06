package proingsoftware.activities;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.R;

import proingsoftware.model.Producto;

import java.util.List;

public class RecyclerView_ProductosSubsidio { //en teoria ya esta

    private Context mContext;
    private SubsidioAdapter mSubsidioAdapter;
    private RecyclerView productList;


    public void setConfig (RecyclerView recyclerView, Context context, List<Producto> producto, List <String> keys ){
        mContext = context;
        mSubsidioAdapter = new SubsidioAdapter(producto ,keys);
        recyclerView.setLayoutManager(new LinearLayoutManager(context));
        recyclerView.setAdapter(mSubsidioAdapter);
    }

    class ProductoItemView  extends RecyclerView.ViewHolder{
        private TextView mnombre;
        private TextView mdescripcion;
        private TextView mprecio;
        private ImageView mfoto;

        private String key;

        public ProductoItemView(ViewGroup parent){
            super(LayoutInflater.from(mContext).inflate(R.layout.producto_item, parent,false));

            mnombre = (TextView) itemView.findViewById(R.id.nombre);
            mdescripcion = (TextView) itemView.findViewById(R.id.descripcion);
            mprecio = (TextView) itemView.findViewById(R.id.precio);
            mfoto = (ImageView) itemView.findViewById(R.id.foto);

        }

        public void  bind(Producto producto , String key){
            mprecio.setText(producto.getPrecio());
            mnombre.setText(producto.getNombre());
            mdescripcion.setText(producto.getDescripcion());
            mfoto.setImageResource(producto.getFoto());
            this.key = key;
            }


    }

    class SubsidioAdapter extends RecyclerView.Adapter<ProductoItemView> {
        private List <Producto> mProductoList;
        private List <String> mKeys;

        public SubsidioAdapter(List<Producto> mProductoList, List<String> mKeys) {
            this.mProductoList = mProductoList;
            this.mKeys = mKeys;
        }

        @NonNull
        @Override
        public ProductoItemView onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            return new ProductoItemView(parent);
        }

        @Override
        public void onBindViewHolder(@NonNull ProductoItemView holder, int position) {
            holder.bind(mProductoList.get(position),mKeys.get(position));
        }

        @Override
        public int getItemCount() {
            return mProductoList.size();
        }
    }

}

