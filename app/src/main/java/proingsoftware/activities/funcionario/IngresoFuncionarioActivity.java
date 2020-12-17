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

import proingsoftware.activities.consumidor.MenuConsumidorActivity;
import proingsoftware.activities.superusuario.MenuSuperUsuarioActivity;

public class IngresoFuncionarioActivity extends AppCompatActivity {
    Button login;
    Intent loginIntent;
    SharedPreferences sharedPreferences;
   //  FirebaseDatabase database = FirebaseDatabase.getInstance();
    private DatabaseReference mdataBase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ingreso_funcionario);
     /*   mdataBase = FirebaseDatabase.getInstance().getReference();
        mdataBase.child("Funcionarios").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.exists()){
                    String codigo = snapshot.child("codigoFuncionario").getValue().toString();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        }) */
          final String usuario = "46125";
        final String thepassword = "12345";
        final String superAdminHardcode = "111";

        login = findViewById(R.id.accederFuncionario);

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String nombre = ((EditText) findViewById(R.id.codigofunc)).getText().toString();
                String ci = ((EditText) findViewById(R.id.carnetfunc)).getText().toString();
                String ext = ((EditText) findViewById(R.id.extensionfunc)).getText().toString();
                String cel = ((EditText) findViewById(R.id.celularfunc)).getText().toString();
                String correo = ((EditText) findViewById(R.id.emailfunc)).getText().toString();
                String codigo = ((EditText) findViewById(R.id.codigofunc)).getText().toString();
                String password = ((EditText) findViewById(R.id.contrasenia)).getText().toString();
                if (codigo.equals(superAdminHardcode)){
                    loginIntent = new Intent(IngresoFuncionarioActivity.this, MenuSuperUsuarioActivity.class);
                    startActivity(loginIntent);
                }else if  ((codigo.equals(usuario)) &&
                        (nombre != null && ci != null && ext != null && cel != null && correo != null ) //validacion momentanea
                ) { //AQUI ENLAZAR LA BASE DE DATOS CON VALIDACIONES y que
                    //compare todos los datos
                    if (thepassword.equals(password)) {
                        loginIntent = new Intent(IngresoFuncionarioActivity.this, MenuFuncionarioActivity.class);
                        startActivity(loginIntent);
                    } else {
                        ToastPasswordFail();
                    }
                } else {
                    ToastLoginFail();
                }

            }
        })
        ;
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

}
