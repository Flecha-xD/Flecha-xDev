package proingsoftware.activities.funcionario;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import com.R;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.squareup.picasso.Picasso;

public class VerReclamoActivity extends AppCompatActivity {
    Button atender, ignorar;
    Intent mainIntent;
    Toast toast;

    //Text views
    ImageView fotoReclamo;
    TextView reclamoNombre, propietario, clienteCI, clienteCel, clienteCorreo, clienteDept, reclamoDescripcion;

    //Firebase variables
    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference reclamoRef = database.getReference();

    //Del anterior activity
    Bundle extras ;
    private String id, foto, nombre, ci, ext, cel, correo, depto, producto, descripcion;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ver_reclamo);
        atender = (Button) findViewById(R.id.atenderButton);
        ignorar = (Button) findViewById(R.id.descartarButton);

        initViews();

        atender.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                mainIntent = new Intent(VerReclamoActivity.this, ContactoFuncionarioActivity.class);
                mainIntent.putExtra("id",id);
                mainIntent.putExtra("correo",correo);
                mainIntent.putExtra("cel",cel);
                startActivity(mainIntent);
            }
        });
        ignorar.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                reclamoRef.child("Reclamos").child(id).removeValue();
                toast = Toast.makeText(getApplicationContext(), "Reclamo eliminado", Toast.LENGTH_SHORT);
                toast.show();
                mainIntent = new Intent(VerReclamoActivity.this, MenuFuncionarioActivity.class);
                startActivity(mainIntent);
            }
        });

    }
    private void fillData(){
        extras = getIntent().getExtras();
        if (extras != null) {
            id = extras.getString("id");
            nombre = extras.getString("nombre");
            ci = extras.getString("ci");
            ext = extras.getString("ext");
            cel = extras.getString("cel");
            correo = extras.getString("correo");
            depto = extras.getString("dept");
            producto = extras.getString("producto");
            descripcion = extras.getString("descripcion");
            foto = extras.getString("foto");
        }
    }

    private void initViews(){
        fillData();
        reclamoNombre =findViewById(R.id.nombrereclamoVER);
        reclamoNombre.setText(producto);

        propietario = findViewById(R.id.propietarioVER);
        propietario.setText(nombre);

        clienteCI = findViewById(R.id.ciVER);
        clienteCI.setText(ci + " " + ext);

        clienteCel = findViewById(R.id.celVER);
        clienteCel.setText(cel);

        clienteCorreo = findViewById(R.id.correoVER);
        clienteCorreo.setText(correo);

        clienteDept = findViewById(R.id.deptoVER);
        clienteDept.setText(depto);

        reclamoDescripcion = findViewById(R.id.descVER);
        reclamoDescripcion.setText(descripcion);

        fotoReclamo = findViewById(R.id.imagereclamoVER);
        Picasso.get().load(foto).into(fotoReclamo);
    }
}