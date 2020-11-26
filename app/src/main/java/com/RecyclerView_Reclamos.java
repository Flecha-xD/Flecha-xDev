package com;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

public class RecyclerView_Reclamos {

    private Context mContext;
    private ReclamoAdapter mReclamoAdapter;


    public void setConfig (RecyclerView recyclerView,Context context,List<Reclamos> reclamos, List <String> keys ){
        mContext = context;
        mReclamoAdapter = new ReclamoAdapter(reclamos ,keys);
        recyclerView.setLayoutManager(new LinearLayoutManager(context));
        recyclerView.setAdapter(mReclamoAdapter);
    }

    class ReclamoItemView  extends RecyclerView.ViewHolder{
        private TextView mnombre;
        private TextView mdescripcion;
        private TextView mprodserv;
        private Image mfoto;
        private String key;

        public ReclamoItemView(ViewGroup parent){
            super(LayoutInflater.from(mContext).inflate(R.layout.reclamo_item, parent,false));

            mnombre = (TextView) itemView.findViewById(R.id.nombre);
            mdescripcion = (TextView) itemView.findViewById(R.id.descripcion);
            mprodserv= (TextView) itemView.findViewById(R.id.prodserv);
            mfoto = (ImageView) itemView.findViewById(R.id.foto);

        }

        public void  bind(Reclamo reclamo , String key){
            mnombre.setText(reclamo.getNombre());
            mdescripcion.setText(reclamo.getDescripcion());
            mprodserv.setText(reclamo.getProdServ());
            mfoto.setText(reclamo.getFoto());
            this.key = key;
             }


    }

    class ReclamoAdapter extends RecyclerView.Adapter<ReclamoItemView> {
        private List <Reclamo> mReclamoList;
        private List <String> mKeys;

        public ReclamoAdapter(List<Reclamo> mReclamoList, List<String> mKeys) {
            this.mReclamoList = mReclamoList;
            this.mKeys = mKeys;
        }

        @NonNull
        @Override
        public ReclamoItemView onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            return new ReclamoItemView(parent);
        }

        @Override
        public void onBindViewHolder(@NonNull ReclamoItemView holder, int position) {
            holder.bind(mReclamoList.get(position),mKeys.get(position));
        }

        @Override
        public int getItemCount() {
            return mReclamoList.size();
        }
    }

}

