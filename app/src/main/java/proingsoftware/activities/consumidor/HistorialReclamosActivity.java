package proingsoftware.activities.consumidor;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

import proingsoftware.Adapters.MisReclamoFirebaseAdapter;
import proingsoftware.Adapters.ReclamoFirebaseAdapter;
import proingsoftware.model.ReclamoFirebase;

public class HistorialReclamosActivity extends AppCompatActivity {
    private MisReclamoFirebaseAdapter adapter;
    DatabaseReference reference;
    RecyclerView recyclerView;
    ArrayList<ReclamoFirebase> list;
    FirebaseDatabase database = FirebaseDatabase.getInstance();
    TextView titulo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_historial_reclamos_consumidor);
        recyclerView = (RecyclerView) findViewById(R.id.recyclerViewMisReclamos);
        titulo = (TextView)findViewById(R.id.titulo);
        recyclerView.setLayoutManager( new LinearLayoutManager(this));
        Intent intent = getIntent();
        String activity = intent.getStringExtra("FROM_ACTIVITY");
        Query query;
        reference = FirebaseDatabase.getInstance().getReference().child("Reclamos");
        if (activity.equals("FUNCIONARIO")){
            titulo.setText("RECLAMOS ATENDIDOS");
            query = reference.orderByChild("fueAtendido").equalTo(true);
            query.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    list = new ArrayList<ReclamoFirebase>();
                    for(DataSnapshot dataSnapshot1: dataSnapshot.getChildren())
                    {
                        ReclamoFirebase p = dataSnapshot1.getValue(ReclamoFirebase.class);
                        list.add(p);
                    }
                    adapter = new MisReclamoFirebaseAdapter(HistorialReclamosActivity.this,list);
                    recyclerView.setAdapter(adapter);
                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {
                    Toast.makeText(HistorialReclamosActivity.this, "Algo salió mal", Toast.LENGTH_SHORT).show();
                }
            });
        } else if (activity.equals("CONSUMIDOR")){
            titulo.setText("MIS RECLAMOS");
             query = reference.orderByChild("dept").equalTo("La Paz");
            query.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    list = new ArrayList<ReclamoFirebase>();
                    for(DataSnapshot dataSnapshot1: dataSnapshot.getChildren())
                    {
                        ReclamoFirebase p = dataSnapshot1.getValue(ReclamoFirebase.class);
                        list.add(p);
                    }
                    adapter = new MisReclamoFirebaseAdapter(HistorialReclamosActivity.this,list);
                    recyclerView.setAdapter(adapter);
                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {
                    Toast.makeText(HistorialReclamosActivity.this, "Algo salió mal", Toast.LENGTH_SHORT).show();
                }
            });
        }
    }
}