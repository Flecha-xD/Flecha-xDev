package com;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.Adapters.ReclamoAdapter;
import com.R;
import com.model.Reclamo;

import java.util.LinkedList;
import java.util.List;

public class ReclamoActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reclamo);

        RecyclerView recyclerView = findViewById(R.id.recyclerViewMenu);

        List<Reclamo> reclamoList = new LinkedList<>();
        reclamoList.add (new Reclamo ("Vencido","Leche en Lata", "Subsidio", R.drawable.lechelata));
        reclamoList.add (new Reclamo ("Abierto","Harina", "Subsidio", R.drawable.harina));
        reclamoList.add (new Reclamo ("Sobreprecio","Hipermaxi", "Servicio", R.drawable.hipermaxi));
        reclamoList.add (new Reclamo ("Sobreprecio","Vacuna de fiebre", "Servicio", R.drawable.salud));
        ReclamoAdapter adapter = new ReclamoAdapter(this, reclamoList);

        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }

}
