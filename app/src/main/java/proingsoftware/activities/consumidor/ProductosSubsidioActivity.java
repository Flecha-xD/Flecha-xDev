package proingsoftware.activities.consumidor;

import android.os.Bundle;
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
import com.google.firebase.database.ValueEventListener;

import proingsoftware.Adapters.MisReclamoFirebaseAdapter;
import proingsoftware.Adapters.SubsidioFirebaseAdapter;
import proingsoftware.model.ProductoFirebase;
import proingsoftware.model.ReclamoFirebase;

import java.util.ArrayList;

public class ProductosSubsidioActivity extends AppCompatActivity { //en teoria ya esta
    private SubsidioFirebaseAdapter adapter;
    DatabaseReference reference;
    RecyclerView recyclerView;
    ArrayList<ProductoFirebase> list;
    FirebaseDatabase database = FirebaseDatabase.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_subsidio);
        recyclerView = (RecyclerView) findViewById(R.id.recyclerViewSubsidio);
        recyclerView.setLayoutManager( new LinearLayoutManager(this));
        reference = FirebaseDatabase.getInstance().getReference().child("Productos Subsidio");
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                list = new ArrayList<ProductoFirebase>();
                for(DataSnapshot dataSnapshot1: dataSnapshot.getChildren())
                {
                    ProductoFirebase p = dataSnapshot1.getValue(ProductoFirebase.class);
                    list.add(p);
                }
                adapter = new SubsidioFirebaseAdapter(ProductosSubsidioActivity.this,list);
                recyclerView.setAdapter(adapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText(ProductosSubsidioActivity.this, "Algo salió mal", Toast.LENGTH_SHORT).show();
            }
        });
    }
}