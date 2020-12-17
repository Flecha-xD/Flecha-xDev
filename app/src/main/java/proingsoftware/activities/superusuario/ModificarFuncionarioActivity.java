package proingsoftware.activities.superusuario;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.R;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;

public class ModificarFuncionarioActivity extends AppCompatActivity {
    Button guardar;
    Intent intent, mIntent;
    CheckBox checkBox;
    TextView encabezado ;
    Toast toast;
    SharedPreferences sharedPreferences;

    //Firebase variables
    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference adminRef = database.getReference();
    private String codigoSuperDB ="-1";
    private String passSuperDB;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_modificar_funcionario);
        final String passHardcodeo = "1234";
        mIntent = getIntent();
        checkBox = findViewById(R.id.sumodoAdmin);
        encabezado = findViewById(R.id.SUtag);
//        String previousActivity= mIntent.getStringExtra("FROM_ACTIVITY");
//        if (previousActivity.equals("CREAR")){
//            encabezado.setText("CREAR FUNCIONARIO");
//        } else if (previousActivity.equals("CAMBIAR")){
//            encabezado.setText("MODIFICAR FUNCIONARIO");
//        }
        guardar = findViewById(R.id.suguardar);
        guardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String nombre = ((EditText) findViewById(R.id.sunombre)).getText().toString();
                String ci = ((EditText) findViewById(R.id.suci)).getText().toString();
                String ext = ((EditText) findViewById(R.id.suext)).getText().toString();
                String cel = ((EditText) findViewById(R.id.sucel)).getText().toString();
                String correo = ((EditText) findViewById(R.id.sumail)).getText().toString();
                String codigo = ((EditText) findViewById(R.id.sucodigo)).getText().toString();
                String password = ((EditText) findViewById(R.id.supasswordfuncionario)).getText().toString();
                String passwordSuperUser = ((EditText) findViewById(R.id.supassword)).getText().toString();
               String codAdmin = ((EditText) findViewById(R.id.SUCodEditFunc)).getText().toString();
                //get CODIGO func from db
                adminRef.child("Funcionarios").child("Funcionario: " + codAdmin).child("codigo").addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        //Si existe el superadmin y si el boolean "esAdmin" es true
                        if(snapshot.exists()){
                            codigoSuperDB = snapshot.getValue().toString();
                        }
                    }
                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {
                        toast = Toast.makeText(getApplicationContext(), "Error al conectar con la base de datos", Toast.LENGTH_SHORT);
                        toast.show();
                    }
                });

                //get PASSWORD SUPERADMIN from db
                adminRef.child("Funcionarios").child("Funcionario: " + codAdmin).child("password").addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        //Si existe el superadmin y si el boolean "esAdmin" es true
                        if(snapshot.exists() ){
                            passSuperDB = snapshot.getValue().toString();
                        }
                    }
                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {
                        toast = Toast.makeText(getApplicationContext(), "Error al conectar con la base de datos", Toast.LENGTH_SHORT);
                        toast.show();
                    }
                });

                if (passwordSuperUser.equals(passSuperDB)) {
                    if (nombre != null && ci != null && ext != null && cel != null && correo != null && codigo != null) { //todos los datos llenos
                        //idealmente aqui una linea compara que el codigo que da el ministerio sea unico pero sino nayproblem
                        // update info
                        HashMap hashMap = new HashMap();
                        hashMap.put("nombre", nombre);
                        hashMap.put("ci", ci);
                        hashMap.put("ext", ext);
                        hashMap.put("phone", cel);
                        hashMap.put("correo", correo);
                        hashMap.put("password", password);

                        adminRef.child("Funcionarios").child("Funcionario: " + codigo).updateChildren(hashMap).addOnSuccessListener(new OnSuccessListener() {
                            @Override
                            public void onSuccess(Object o) {
                                Toast.makeText(ModificarFuncionarioActivity.this, "Se actualizo la info", Toast.LENGTH_SHORT).show();
                            }
                        });


                        toast = Toast.makeText(getApplicationContext(), "Otorgados Permisos Administrativos", Toast.LENGTH_SHORT);
                        toast.show();

                        toast = Toast.makeText(getApplicationContext(), "Funcionario editado", Toast.LENGTH_SHORT);
                        toast.show();
                        intent = new Intent(ModificarFuncionarioActivity.this, MenuSuperUsuarioActivity.class);
                        startActivity(intent);
                    } else {
                        toast = Toast.makeText(getApplicationContext(), "Todos los campos son obligatorios", Toast.LENGTH_SHORT);
                        toast.show();
                    }
                } else {
                    toast = Toast.makeText(getApplicationContext(), "Contraseña equivocada", Toast.LENGTH_LONG);
                    toast.show();
                }
            }


            
        });
    }
}