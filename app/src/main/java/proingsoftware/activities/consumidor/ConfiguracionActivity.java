package proingsoftware.activities.consumidor;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Switch;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import com.R;

import proingsoftware.activities.funcionario.IngresoFuncionarioActivity;
import proingsoftware.activities.funcionario.ListadoReclamosActivity;

public class ConfiguracionActivity extends AppCompatActivity {

    Button admin, historialReclamos;
    SharedPreferences sharedPreferences;
    Intent mainIntent;
    Switch modoEmo, saveData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_configuracion);
        modoEmo = (Switch) findViewById(R.id.nightMode);
        saveData = (Switch) findViewById(R.id.nightMode);
        modoEmo.setOnCheckedChangeListener(
                (buttonView, isChecked) -> {
                    if (isChecked) {
                        Toast.makeText(ConfiguracionActivity.this,
                                "Modo oscuro activado", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(ConfiguracionActivity.this,
                                "Modo oscuro desactivado", Toast.LENGTH_SHORT).show();
                    }
                }); //probar no hay certeza de que funcione
        saveData.setOnCheckedChangeListener(
                (buttonView, isChecked) -> {
                    if (isChecked) {
                        Toast.makeText(ConfiguracionActivity.this,
                                "Sus datos se cargarán automáticamente", Toast.LENGTH_LONG).show();
                    } else {
                        Toast.makeText(ConfiguracionActivity.this,
                                "Sus datos no se cargarán automáticamente", Toast.LENGTH_LONG).show();
                    }
                });
        admin = (Button) findViewById(R.id.adminButton);
        historialReclamos = (Button) findViewById(R.id.historialButton);
        admin.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                mainIntent = new Intent(ConfiguracionActivity.this, IngresoFuncionarioActivity.class);
                startActivity(mainIntent);
            }
        });
        historialReclamos.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                //SharedPreferences.Editor editor = sharedPreferences.edit();
                //editor.apply();
                //editor.commit();
                mainIntent = new Intent(ConfiguracionActivity.this, ListadoReclamosActivity.class);
                startActivity(mainIntent);
                finish();
            }
        });

    }
}