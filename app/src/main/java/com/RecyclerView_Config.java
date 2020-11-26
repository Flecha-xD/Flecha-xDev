package com;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

public class RecyclerView_Config {

    private Context mContext;
    private ProductoAdapter mProductoAdapter;


    public void setConfig (RecyclerView recyclerView,Context context,List<Producto> producto, List <String> keys ){
        mContext = context;
        mSubsidioAdapter = new SubsidioAdapter(producto ,keys);
        recyclerView.setLayoutManager(new LinearLayoutManager(context));
        recyclerView.setAdapter(mSubsidioAdapter);
    }

    class ProductoItemView  extends RecyclerView.ViewHolder{
        private TextView mnombre;
        private TextView mdescripcion;
        private TextView mprecio;
        private Image mfoto;

        private String key;

        public ProductoItemView(ViewGroup parent){
            super(LayoutInflater.from(mContext).inflate(R.layout.producto_item, parent,false));

            mnombre = (TextView) itemView.findViewById(R.id.nombre);
            mdescripcion = (TextView) itemView.findViewById(R.id.descripcion);
            mprecio = (TextView) itemView.findViewById(R.id.precio);
            mfoto = (TextView) itemView.findViewById(R.id.foto);

        }

        public void  bind(Producto producto , String key){
            mprecio.setText(producto.getPrecio());
            mnombre.setText(producto.getNombre);
            mdescripcion.setText(producto.getDescripcion);
            mfoto.setImage(producto.getFoto);
            this.key = key;
            }


    }

    class SubsidioAdapter extends RecyclerView.Adapter<SubsidioItemView> {
        private List <Producto> mProductoList;
        private List <String> mKeys;

        public SubsidioAdapter(List<Producto> mProductoList, List<String> mKeys) {
            this.mProductoList = mProductoList;
            this.mKeys = mKeys;
        }

        @NonNull
        @Override
        public SubsidioItemView onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            return new SubsidioItemView(parent);
        }

        @Override
        public void onBindViewHolder(@NonNull SubsidioItemView holder, int position) {
            holder.bind(mProductoList.get(position),mKeys.get(position));
        }

        @Override
        public int getItemCount() {
            return mProductoList.size();
        }
    }

}

