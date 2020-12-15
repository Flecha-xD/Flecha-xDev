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
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class BorrarUsuarioActivity  extends AppCompatActivity {
    Button borrar;
    Intent intent;
    Toast toast;

    private String codigoSuperAdminDB ="-1";
    private String contrasenaSuperAdminDB="-1";
    private String codigoFuncionarioDB="-1";

    //Firebase variables
    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference adminRef = database.getReference();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_eliminar_funcionario);
        final String passHardcodeo = "1234";
        final String codHarcodeado = "666";
        final String funcHArdcodeado = "777";


        borrar = findViewById(R.id.eliminar);
        borrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String codigoFunc = ((EditText) findViewById(R.id.sucodfunborrar)).getText().toString();
                String codAdmin = ((EditText) findViewById(R.id.sucodsuborrar)).getText().toString();
                String passwordSuperUser = ((EditText) findViewById(R.id.supasssuborrar)).getText().toString();

                //get CODIGO FUNCIONARIO from db
                adminRef.child("Funcionarios").child("Funcionario: " + codigoFunc).child("codigo").addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        //Si existe el funcionario y si el boolean "esAdmin" es false
                        if(snapshot.exists() ){
                            codigoFuncionarioDB = snapshot.getValue().toString();
                        }
                    }
                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {
                        toast = Toast.makeText(getApplicationContext(), "Error al conectar con la base de datos", Toast.LENGTH_SHORT);
                        toast.show();
                    }
                });

                //get CODIGO SUPERADMIN from db
                adminRef.child("Funcionarios").child("Funcionario: " + codAdmin).child("codigo").addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        //Si existe el superadmin y si el boolean "esAdmin" es true
                        if(snapshot.exists()){
                            codigoSuperAdminDB = snapshot.getValue().toString();
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
                            contrasenaSuperAdminDB = snapshot.getValue().toString();
                        }
                    }
                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {
                        toast = Toast.makeText(getApplicationContext(), "Error al conectar con la base de datos", Toast.LENGTH_SHORT);
                        toast.show();
                    }
                });
//prueba

//                COND: passwordSuperUser.equals(contrasenaSuperAdminDB)
                if (passwordSuperUser.equals(contrasenaSuperAdminDB)) {
                    if (codigoFunc != null && codAdmin != null) { //todos los datos llenos
//                        COND: codAdmin.equals(codigoSuperAdminDB)
                        if (codAdmin.equals(codigoSuperAdminDB)) {
//                            COND: codigoFunc.equals(codigoFuncionarioDB)
                            if (codigoFunc.equals(codigoFuncionarioDB)){
                                //Borrar funcionario
                                adminRef.child("Funcionarios").child("Funcionario: " + codigoFunc).removeValue();

                                toast = Toast.makeText(getApplicationContext(), "Funcionario desvinculado", Toast.LENGTH_SHORT);
                                toast.show();
                                intent = new Intent(BorrarUsuarioActivity.this, MenuSuperUsuarioActivity.class);
                                startActivity(intent);
                            } else{
                                toast = Toast.makeText(getApplicationContext(), "Este funcionario no existe", Toast.LENGTH_SHORT);
                                toast.show();
                            }
                        } else{
                            toast = Toast.makeText(getApplicationContext(), "Código inexistente", Toast.LENGTH_SHORT);
                            toast.show();
                        }
                    } else {
                        toast = Toast.makeText(getApplicationContext(), "Todos los campos obligatorios", Toast.LENGTH_LONG);
                        toast.show();
                    }
                } else {
                    toast = Toast.makeText(getApplicationContext(), "Contraseña equivocada", Toast.LENGTH_SHORT);
                    toast.show();
                }
            }

        });
    }

}
