package proingsoftware.activities.recyclerviews;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.R;

import proingsoftware.model.Producto;

import java.util.List;

public class RecyclerView_ProductosSubsidio { //en teoria ya esta

    private Context mContext;
    private RecyclerView_ProductosSubsidio.SubsidioAdapter mSubsidioAdapter;


    public void setConfig (RecyclerView recyclerView, Context context, List<Producto> productos, List <String> keys ){
        mContext = context;
        mSubsidioAdapter = new RecyclerView_ProductosSubsidio.SubsidioAdapter(productos ,keys);
        recyclerView.setLayoutManager(new LinearLayoutManager(context));
        recyclerView.setAdapter(mSubsidioAdapter);
    }

    class SubsidioItemView extends RecyclerView.ViewHolder{
        private TextView mnombre;
        private TextView mdescripcion;
        private TextView mprecio;
        private ImageView mfoto;
        private String key;

        public SubsidioItemView(ViewGroup parent){
            super(LayoutInflater.from(mContext).inflate(R.layout.producto_item, parent,false));

            mnombre = (TextView) itemView.findViewById(R.id.nombreSub);
            mdescripcion = (TextView) itemView.findViewById(R.id.descSub);
            mprecio= (TextView) itemView.findViewById(R.id.precioSub);
            mfoto = (ImageView) itemView.findViewById(R.id.fotoSub);

        }

        public void  bind(Producto producto, String key){
            mnombre.setText(producto.getNombre());
            mdescripcion.setText(producto.getDescripcion());
            mprecio.setText(producto.getPrecio());
            mfoto.setImageResource(producto.getFoto());
            this.key = key;
        }


    }

    class SubsidioAdapter extends RecyclerView.Adapter<RecyclerView_ProductosSubsidio.SubsidioItemView> {
        private List <Producto> mProductoList;
        private List <String> mKeys;

        public SubsidioAdapter(List<Producto> mProductoList, List<String> mKeys) {
            this.mProductoList = mProductoList;
            this.mKeys = mKeys;
        }

        @NonNull
        @Override
        public RecyclerView_ProductosSubsidio.SubsidioItemView onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            return new SubsidioItemView(parent);
        }

        @Override
        public void onBindViewHolder(@NonNull RecyclerView_ProductosSubsidio.SubsidioItemView holder, int position) {
            holder.bind(mProductoList.get(position),mKeys.get(position));
        }

        @Override
        public int getItemCount() {
            return mProductoList.size();
        }
    }

}