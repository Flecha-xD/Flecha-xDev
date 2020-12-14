package proingsoftware.activities.superusuario;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import com.R;

public class CrearCambiarFuncionarioActivity extends AppCompatActivity{
        Button guardar;
        Intent intent, mIntent;
        CheckBox checkBox;
        TextView encabezado ;
        Toast toast;
        SharedPreferences sharedPreferences;
        //  FirebaseDatabase database = FirebaseDatabase.getInstance();
        // DatabaseReference myRef = database.getReference("message");

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_crearcambiar_funcionario);
            final String passHardcodeo = "1234";
            mIntent = getIntent();
            String previousActivity= mIntent.getStringExtra("FROM_ACTIVITY");
            SharedPreferences settings = getSharedPreferences("mysettings", 0);
            SharedPreferences.Editor editor = settings.edit();
            checkBox = findViewById(R.id.sumodoAdmin);
            encabezado = findViewById(R.id.SUtag);
            boolean checkBoxValue = checkBox.isChecked();
            editor.putBoolean("Modo Admin", checkBoxValue);
            editor.commit();;
            if (previousActivity.equals("CREAR")){
                encabezado.setText("CREAR FUNCIONARIO");
            } else if (previousActivity.equals("CAMBIAR")){
                encabezado.setText("MODIFICAR FUNCIONARIO");
            }
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

                    if (passwordSuperUser.equals(passHardcodeo)) {
                        if (nombre != null && ci != null && ext != null && cel != null && correo != null && codigo != null) { //todos los datos llenos
                            //idealmente aqui una linea compara que el codigo que da el ministerio sea unico pero sino nayproblema
                            if (checkBox.isChecked()) {
                                SharedPreferences.Editor editor = sharedPreferences.edit();
                                editor.putBoolean("checkBoxValue", checkBox.isChecked());
                                editor.apply();
                                editor.commit();
                                //aca se debe guardar el valor del checkbox
                                toast = Toast.makeText(getApplicationContext(), "Otorgados Permisos Administrativos", Toast.LENGTH_SHORT);
                                toast.show();
                            }
                            toast = Toast.makeText(getApplicationContext(), "Funcionario creado", Toast.LENGTH_SHORT);
                            toast.show();
                            intent = new Intent(CrearCambiarFuncionarioActivity.this, MenuSuperUsuarioActivity.class);
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

