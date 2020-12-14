package proingsoftware.activities.superusuario;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.GridLayout;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import com.R;
import proingsoftware.activities.consumidor.MenuConsumidorActivity;
import proingsoftware.activities.funcionario.ListadoReclamosActivity;

public class MenuSuperUsuarioActivity extends AppCompatActivity {
    Intent intent;
    GridLayout sumenuGrid;
    Button logoutSU;
    SharedPreferences sharedPreferences;
    Intent mainIntent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_superusuarios);
        logoutSU= (Button) findViewById(R.id.SUlogoutButton);
        logoutSU.setOnClickListener(new View.OnClickListener(){
            public void onClick(View view){
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putBoolean("checkBoxValue", false);
                editor.apply();
                editor.commit();
                mainIntent = new Intent(MenuSuperUsuarioActivity.this, MenuConsumidorActivity.class);
                startActivity(mainIntent);
            }
        });
        sumenuGrid = (GridLayout) findViewById(R.id.SUmenuGrid);
        setSingleEvent(sumenuGrid);

    }
    private void setSingleEvent(GridLayout mainGrid) {
        for (int i = 0; i < mainGrid.getChildCount(); i++) {
            CardView cardView = (CardView) mainGrid.getChildAt(i);
            final int cardIndex = i;
            cardView.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View view) {

                    if (cardIndex == 0) {

                        intent = new Intent(MenuSuperUsuarioActivity.this, CrearFuncionarioActivity.class);
                        intent.putExtra("FROM_ACTIVITY", "CREAR");
                        startActivity(intent);
                    }else if(cardIndex == 1 ){
                        intent = new Intent(MenuSuperUsuarioActivity.this, ModificarFuncionarioActivity.class);
                        intent.putExtra("FROM_ACTIVITY", "CAMBIAR");
                        startActivity(intent);

                    }else if(cardIndex == 2){
                        intent = new Intent(MenuSuperUsuarioActivity.this, BorrarUsuarioActivity.class);
                        intent.putExtra("FROM_ACTIVITY", "SUPERUSER");
                        startActivity(intent);
                    }else if (cardIndex == 3){
                        intent = new Intent(MenuSuperUsuarioActivity.this, ListadoReclamosActivity.class);
                        intent.putExtra("FROM_ACTIVITY", "CONSUMIDOR");
                        startActivity(intent);
                    }
                }
            });
        }
    }


}