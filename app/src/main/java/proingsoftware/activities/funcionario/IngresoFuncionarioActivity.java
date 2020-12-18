package proingsoftware.activities.funcionario;

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
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import proingsoftware.activities.consumidor.MenuConsumidorActivity;
import proingsoftware.activities.superusuario.MenuSuperUsuarioActivity;

public class IngresoFuncionarioActivity extends AppCompatActivity {
    Button login;
    Intent loginIntent;
    Intent superIntent;
    Toast toast;
    SharedPreferences sharedPreferences;
    //Firebase variables
    FirebaseStorage storage;
    StorageReference mStorageReference;
    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference funcionarioRef = database.getReference();
    private String funcionarioNameDB;
    private String funcionarioCIDB;
    private String funcionarioExtDB;
    private String funcionarioCelDB;
    private String funcionarioCodigoDB;
    private String funcionarioCorreoDB;
    private String funcionarioPassDB;

    private boolean validadorIF ;
    private Boolean esAdminDB;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ingreso_funcionario);
        login = findViewById(R.id.accederFuncionario);
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String nombre = ((EditText) findViewById(R.id.nombrefunc)).getText().toString();
                String ci = ((EditText) findViewById(R.id.carnetfunc)).getText().toString();
                String ext = ((EditText) findViewById(R.id.extensionfunc)).getText().toString();
                String cel = ((EditText) findViewById(R.id.celularfunc)).getText().toString();
                String correo = ((EditText) findViewById(R.id.emailfunc)).getText().toString();
                String codigo = ((EditText) findViewById(R.id.codigofunc)).getText().toString();
                String password = ((EditText) findViewById(R.id.contrasenia)).getText().toString();

                getFromDB(codigo);
                //Datos llenos (codigo,nombre,ci,ext,cel,correo,password)
//                if (codigo != null && nombre != null&& ci != null&& ext != null && cel != null&& correo != null && password != null){
               if ((codigo.equals(funcionarioCodigoDB)) && (nombre.equals(funcionarioNameDB)&& ci.equals(funcionarioCIDB) && ext.equals(funcionarioExtDB) && cel.equals(funcionarioCelDB)&& correo.equals(funcionarioCorreoDB) && password.equals(funcionarioPassDB))){
                    if(validadorIF){
                        superIntent = new Intent(IngresoFuncionarioActivity.this, MenuSuperUsuarioActivity.class);
                        startActivity(superIntent);
                    } else {
                        loginIntent = new Intent(IngresoFuncionarioActivity.this, MenuFuncionarioActivity.class);
                        startActivity(loginIntent);
                    }
                }
            }
        });
   }

    public void ToastLoginFail() {
        CharSequence text = "Datos Equivocados";
        int duration = Toast.LENGTH_SHORT;
        Toast toast = Toast.makeText(getApplicationContext(), text, duration);
        toast.show();
        login.setEnabled(false);
        loginIntent = new Intent(IngresoFuncionarioActivity.this, MenuConsumidorActivity.class);
        startActivity(loginIntent);
    }

    public void ToastPasswordFail() {

        CharSequence text = "Contraseña equivocada";
        int duration = Toast.LENGTH_SHORT;
        Toast toast = Toast.makeText(getApplicationContext(), text, duration);
        toast.show();
        loginIntent = new Intent(IngresoFuncionarioActivity.this, MenuConsumidorActivity.class);
        startActivity(loginIntent);

    }

    public void getFromDB(String codigoFunc){
        //Get Codigo from DB
        funcionarioRef.child("Funcionarios").child("Funcionario: " + codigoFunc).child("codigo").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                //Si existe el funcionario
                if(snapshot.exists() ){
                    funcionarioCodigoDB = snapshot.getValue().toString();
                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                toast = Toast.makeText(getApplicationContext(), "Error al conectar con la base de datos", Toast.LENGTH_SHORT);
                toast.show();
            }
        });

        //Get BOOLEAN from DB
        funcionarioRef.child("Funcionarios").child("Funcionario: " + codigoFunc).child("esAdmin").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                //Si existe el funcionario
                if(snapshot.exists() ){
                    esAdminDB = (Boolean) snapshot.getValue();
                    validadorIF = esAdminDB.booleanValue();

                    if (validadorIF){
                        toast = Toast.makeText(getApplicationContext(), "ES SUPERUSUARIO", Toast.LENGTH_SHORT);
                    }else {
                        toast = Toast.makeText(getApplicationContext(), "ES FUNCIONARIO NORMAL", Toast.LENGTH_SHORT);
                    }
                    toast.show();
                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                toast = Toast.makeText(getApplicationContext(), "Error al conectar con la base de datos", Toast.LENGTH_SHORT);
                toast.show();
            }
        });

        //Get nombre from DB
        funcionarioRef.child("Funcionarios").child("Funcionario: " + codigoFunc).child("nombre").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                //Si existe el funcionario
                if(snapshot.exists() ){
                    funcionarioNameDB = snapshot.getValue().toString();
                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                toast = Toast.makeText(getApplicationContext(), "Error al conectar con la base de datos", Toast.LENGTH_SHORT);
                toast.show();
            }
        });

        //Get ci from DB
        funcionarioRef.child("Funcionarios").child("Funcionario: " + codigoFunc).child("ci").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                //Si existe el funcionario
                if(snapshot.exists() ){
                    funcionarioCIDB = snapshot.getValue().toString();
                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                toast = Toast.makeText(getApplicationContext(), "Error al conectar con la base de datos", Toast.LENGTH_SHORT);
                toast.show();
            }
        });

        //Get EXT from DB
        funcionarioRef.child("Funcionarios").child("Funcionario: " + codigoFunc).child("ext").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                //Si existe el funcionario
                if(snapshot.exists() ){
                    funcionarioExtDB = snapshot.getValue().toString();
                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                toast = Toast.makeText(getApplicationContext(), "Error al conectar con la base de datos", Toast.LENGTH_SHORT);
                toast.show();
            }
        });

        //Get CEL from DB
        funcionarioRef.child("Funcionarios").child("Funcionario: " + codigoFunc).child("phone").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                //Si existe el funcionario
                if(snapshot.exists() ){
                    funcionarioCelDB = snapshot.getValue().toString();
                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                toast = Toast.makeText(getApplicationContext(), "Error al conectar con la base de datos", Toast.LENGTH_SHORT);
                toast.show();
            }
        });

        //Get Correo from DB
        funcionarioRef.child("Funcionarios").child("Funcionario: " + codigoFunc).child("correo").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                //Si existe el funcionario
                if(snapshot.exists() ){
                    funcionarioCorreoDB = snapshot.getValue().toString();
                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                toast = Toast.makeText(getApplicationContext(), "Error al conectar con la base de datos", Toast.LENGTH_SHORT);
                toast.show();
            }
        });

        //Get Password from DB
        funcionarioRef.child("Funcionarios").child("Funcionario: " + codigoFunc).child("password").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                //Si existe el funcionario
                if(snapshot.exists() ){
                    funcionarioPassDB = snapshot.getValue().toString();
                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                toast = Toast.makeText(getApplicationContext(), "Error al conectar con la base de datos", Toast.LENGTH_SHORT);
                toast.show();
            }
        });
    }

}
