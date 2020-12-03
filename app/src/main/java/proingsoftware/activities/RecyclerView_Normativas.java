package proingsoftware.activities;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.R;

import java.util.List;
import proingsoftware.model.Normativa;

public class RecyclerView_Normativas { //en teoria ya esta

    private Context mContext;
    private NormativaAdapter normativaAdapter;


    public void setConfig (RecyclerView recyclerView, Context context, List<Normativa> normativas, List <String> keys ){
        mContext = context;
        normativaAdapter = new NormativaAdapter(normativas ,keys);
        recyclerView.setLayoutManager(new LinearLayoutManager(context));
        recyclerView.setAdapter(normativaAdapter);
    }

    class NormativaItemView  extends RecyclerView.ViewHolder{
        private TextView mnombre;
        private TextView minformacion;

        private String key;

        public NormativaItemView(ViewGroup parent){
            super(LayoutInflater.from(mContext).inflate(R.layout.normativa_item, parent,false));

            mnombre = (TextView) itemView.findViewById(R.id.nombre);
            minformacion = (TextView) itemView.findViewById(R.id.informacion);

        }

        public void  bind(Normativa normativa , String key){
            mnombre.setText(normativa.getNombre());
            minformacion.setText(normativa.getInformacion());
            this.key = key;
            }


    }

    class NormativaAdapter extends RecyclerView.Adapter<NormativaItemView> {
        private List <Normativa> mNormativaList;
        private List <String> mKeys;

        public NormativaAdapter(List<Normativa> mNormativaList, List<String> mKeys) {
            this.mNormativaList = mNormativaList;
            this.mKeys = mKeys;
        }

        @NonNull
        @Override
        public NormativaItemView onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            return new NormativaItemView(parent);
        }

        @Override
        public void onBindViewHolder(@NonNull NormativaItemView holder, int position) {
            holder.bind(mNormativaList.get(position),mKeys.get(position));
        }

        @Override
        public int getItemCount() {
            return mNormativaList.size();
        }
    }

}

