package proingsoftware.activities.consumidor;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.UUID;

import proingsoftware.firebase.Firebase;
import proingsoftware.model.ReclamoFirebase;

public class HacerReclamoActivity extends AppCompatActivity {
    Button enviar;
    Intent seguirIntent;
    CheckBox checkBox;
    SharedPreferences sharedPreferences;

    //Firebase variables
    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference reclamoRef = database.getReference();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hacer_reclamo);
        sharedPreferences = getSharedPreferences("DatoCheckbox", MODE_PRIVATE);
        checkBox = findViewById(R.id.essubsi);

        enviar = findViewById(R.id.siguienteButton);
        enviar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String nombre = ((EditText) findViewById(R.id.nombre)).getText().toString();
                String ci = ((EditText) findViewById(R.id.carnet)).getText().toString();
                String ext = ((EditText) findViewById(R.id.extension)).getText().toString();
                String cel = ((EditText) findViewById(R.id.celular)).getText().toString();
                String correo = ((EditText) findViewById(R.id.email)).getText().toString();
                String depto = ((EditText) findViewById(R.id.dpto)).getText().toString();
                String razon = ((EditText) findViewById(R.id.prodserv)).getText().toString();
                String descripcion = ((EditText) findViewById(R.id.descripQueja)).getText().toString();

                if (nombre != null && ci != null && ext != null && cel != null &&
                        correo != null && depto != null && razon != null && descripcion != null) {
                    //AQUI ENLAZAR LA BASE DE DATOS CON VALIDACIONES y que
                    String id = UUID.randomUUID().toString();
                    id = UUID.randomUUID().toString();
                    if (checkBox.isChecked()) {
                        CharSequence text = "Producto del Subsidio";
                        Toast toast = Toast.makeText(getApplicationContext(), text, Toast.LENGTH_SHORT);
                        toast.show();
                    }
                    seguirIntent = new Intent(HacerReclamoActivity.this, DescribirReclamoActivity.class);
                    seguirIntent.putExtra("id", id);
                    seguirIntent.putExtra("nombre", nombre);
                    seguirIntent.putExtra("ci", ci);
                    seguirIntent.putExtra("ext", ext);
                    seguirIntent.putExtra("cel", cel);
                    seguirIntent.putExtra("correo", correo);
                    seguirIntent.putExtra("dept", depto);
                    seguirIntent.putExtra("producto", razon);
                    seguirIntent.putExtra("descripcion", descripcion);
                    startActivity(seguirIntent);
                } else {
                    CharSequence text = "Datos Incompletos";
                    Toast toast = Toast.makeText(getApplicationContext(), text, Toast.LENGTH_SHORT);
                    toast.show();
                }

            }
        })
        ;

    }
}
