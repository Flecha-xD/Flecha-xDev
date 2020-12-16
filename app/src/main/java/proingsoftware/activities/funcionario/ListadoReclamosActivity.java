package proingsoftware.activities.funcionario;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import proingsoftware.Adapters.ReclamoAdapter;
import com.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import proingsoftware.Adapters.ReclamoFirebaseAdapter;
import proingsoftware.model.Reclamo;
import proingsoftware.model.ReclamoFirebase;

import java.util.LinkedList;
import java.util.List;

public class ListadoReclamosActivity extends AppCompatActivity {

    private ReclamoFirebaseAdapter adapter;
    Toast toast;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listado_reclamos);
        RecyclerView recyclerView = findViewById(R.id.recyclerViewMenu);
        Intent mIntent = getIntent();
        String previousActivity= mIntent.getStringExtra("FROM_ACTIVITY");

        List<ReclamoFirebase> reclamoList = new LinkedList<>();

        reclamoRef.child("Reclamos").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if(snapshot.exists()){
                    for(DataSnapshot snapshot1: snapshot.getChildren()){
                        ReclamoFirebase reclamoFirebase = snapshot1.getValue(ReclamoFirebase.class);
                        reclamoList.add(reclamoFirebase);
                    }
                    adapter = new ReclamoFirebaseAdapter(ListadoReclamosActivity.this, reclamoList);
                    recyclerView.setAdapter(adapter);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                toast = Toast.makeText(getApplicationContext(), "Error al conectar con la base de datos", Toast.LENGTH_SHORT);
                toast.show();
            }
        });
//        reclamoList.add (new Reclamo ("Vencido","Leche en Lata", "Subsidio", R.drawable.lechelata));
//        reclamoList.add (new Reclamo ("Abierto","Harina", "Subsidio", R.drawable.harina));
//        reclamoList.add (new Reclamo ("Sobreprecio","Hipermaxi", "Servicio", R.drawable.hipermaxi));
//        reclamoList.add (new Reclamo ("Sobreprecio","Vacuna de fiebre", "Servicio", R.drawable.salud));
//        ReclamoAdapter adapter = new ReclamoAdapter(this, reclamoList);
        List<Reclamo> reclamoList = new LinkedList<>();
        reclamoList.add (new Reclamo ("Vencido","Leche en Lata", "Subsidio", R.drawable.lechelata));
        reclamoList.add (new Reclamo ("Abierto","Harina", "Subsidio", R.drawable.harina));
        reclamoList.add (new Reclamo ("Sobreprecio","Hipermaxi", "Servicio", R.drawable.hipermaxi));
        reclamoList.add (new Reclamo ("Sobreprecio","Vacuna de fiebre", "Servicio", R.drawable.salud));
        ReclamoAdapter adapter = new ReclamoAdapter(this, reclamoList);

        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        if (previousActivity.equals("FUNCIONARIO")){
            adapter.setPermitido(true);
        } else if (previousActivity.equals("CONSUMIDOR")){
            adapter.setPermitido(false);
        }

    }

}
