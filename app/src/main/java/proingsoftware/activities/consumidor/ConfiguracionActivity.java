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
import proingsoftware.activities.funcionario.MenuFuncionarioActivity;
import proingsoftware.activities.funcionario.VerTodosReclamosActivity;
import proingsoftware.activities.superusuario.MenuSuperUsuarioActivity;

public class ConfiguracionActivity extends AppCompatActivity {
    Button admin, historialReclamos;
    Intent intent;
    Switch modoEmo, saveData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_configuracion);
        modoEmo = (Switch) findViewById(R.id.nightMode);
        saveData = (Switch) findViewById(R.id.saveInfo);
        modoEmo.setOnCheckedChangeListener(
                (buttonView, isChecked) -> {
                    if (isChecked) {
                        Toast.makeText(ConfiguracionActivity.this,
                                "Disponible desde la siguiente versión", Toast.LENGTH_SHORT).show();
                    }
                });
        saveData.setOnCheckedChangeListener(
                (buttonView, isChecked) -> {
                    if (isChecked) {
                        Toast.makeText(ConfiguracionActivity.this,
                                "Disponible desde la siguiente versión", Toast.LENGTH_SHORT).show();
                    }
                });
        admin = (Button) findViewById(R.id.adminButton);
        admin.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                intent = new Intent(ConfiguracionActivity.this, IngresoFuncionarioActivity.class);
                startActivity(intent);
            }
        });
        historialReclamos = (Button) findViewById(R.id.historialButton);
        historialReclamos.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                intent = new Intent(ConfiguracionActivity.this, HistorialReclamosActivity.class);
                intent.putExtra("FROM_ACTIVITY", "CONSUMIDOR");
                startActivity(intent);
                finish();
            }
        });

    }
}