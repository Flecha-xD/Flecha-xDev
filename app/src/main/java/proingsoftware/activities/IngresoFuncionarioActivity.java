package proingsoftware.activities;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import com.R;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class IngresoFuncionarioActivity extends AppCompatActivity {
    Button login;
    Intent loginIntent;
    CheckBox checkBox;
    SharedPreferences sharedPreferences;
   //  FirebaseDatabase database = FirebaseDatabase.getInstance();
   // DatabaseReference myRef = database.getReference("message");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ingreso_funcionario);
        sharedPreferences = getSharedPreferences("DatoCheckbox", MODE_PRIVATE);
        boolean lastCheckBoxValue = sharedPreferences.getBoolean("checkBoxValue", false);
        if (lastCheckBoxValue) {
            loginIntent = new Intent(IngresoFuncionarioActivity.this, MenuFuncionarioActivity.class);
            startActivity(loginIntent);
        }

        final String sergio = "46125";
        final String fernanda = "4602546025";
        final String thepassword = "12345";

        checkBox = findViewById(R.id.recuerdameFuncionario);
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


                if ((codigo.equals(fernanda) || codigo.equals(sergio)) &&
                        (nombre != null && ci != null && ext != null && cel != null && correo != null ) //validacion momentanea
                ) { //AQUI ENLAZAR LA BASE DE DATOS CON VALIDACIONES y que
                    //compare todos los datos
                    if (thepassword.equals(password)) {
                        if (checkBox.isChecked()) {
                            SharedPreferences.Editor editor = sharedPreferences.edit();
                            editor.putBoolean("checkBoxValue", checkBox.isChecked());
                            editor.apply();
                            editor.commit();
                        }
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

    }

    public void ToastPasswordFail() {

        CharSequence text = "Contraseña equivocada";
        int duration = Toast.LENGTH_SHORT;
        Toast toast = Toast.makeText(getApplicationContext(), text, duration);
        toast.show();

    }

}
