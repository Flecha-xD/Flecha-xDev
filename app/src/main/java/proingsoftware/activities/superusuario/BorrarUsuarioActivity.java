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

import androidx.appcompat.app.AppCompatActivity;

import com.R;

public class BorrarUsuarioActivity  extends AppCompatActivity {
    Button borrar;
    Intent intent;
    Toast toast;

     //  FirebaseDatabase database = FirebaseDatabase.getInstance();
    // DatabaseReference myRef = database.getReference("message");

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

                if (passwordSuperUser.equals(passHardcodeo)) {
                    if (codigoFunc != null && codAdmin != null) { //todos los datos llenos
                        if (codHarcodeado.equals(codAdmin)) {
                            if (funcHArdcodeado.equals(codigoFunc)){
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
