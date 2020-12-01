package com.activities;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.Adapters.SubsidioAdapter;
import com.R;
import com.model.Producto;
import java.util.LinkedList;
import java.util.List;

public class SubsidioActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_subsidio);

        RecyclerView recyclerView = findViewById(R.id.recyclerViewMenu);

        List<Producto> productoList = new LinkedList<>();
        productoList.add (new Producto ("Quintal de Harina", "Emapa, producto boliviano", "50", R.drawable.harina));
        productoList.add (new Producto ("Chocolate Breick", "Chocolate en tableta", "16", R.drawable.breick));
        productoList.add (new Producto ("Cereal Kellongs", "CornFlakes tradicional, hojuelas de maiz tostadas", "30", R.drawable.confleis));
        productoList.add (new Producto ("Maple de Huevo", "Maple con 30 unidades medianas", "26", R.drawable.huevo));
        productoList.add (new Producto ("Miel Irupana", "Pote de miel irupana con propoleo", "45", R.drawable.miel));
        productoList.add (new Producto ("Cereal Sublime", "Cereal tradicional de trigo con chocolate", "32", R.drawable.sublime));


        SubsidioAdapter adapter = new SubsidioAdapter(this, productoList);

        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }

}
