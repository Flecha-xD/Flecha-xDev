package proingsoftware.activities.funcionario;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;
import com.R;

public class VerReclamoActivity extends AppCompatActivity {
    Button atender, ignorar;
    Intent mainIntent;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ver_reclamo);
        atender = (Button) findViewById(R.id.adminButton);
        ignorar = (Button) findViewById(R.id.historialButton);
        atender.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                mainIntent = new Intent(VerReclamoActivity.this, ContactoFuncionarioActivity.class);
                startActivity(mainIntent);
            }
        });
        ignorar.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                mainIntent = new Intent(VerReclamoActivity.this, MenuFuncionarioActivity.class);
                startActivity(mainIntent);
            }
        });

    }
}