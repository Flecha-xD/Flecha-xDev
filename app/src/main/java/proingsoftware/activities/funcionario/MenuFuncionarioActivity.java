package proingsoftware.activities.funcionario;

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
import proingsoftware.activities.consumidor.ProductosSubsidioActivity;

public class MenuFuncionarioActivity extends AppCompatActivity {

    Intent intent;
    GridLayout menuGrid;
    Button logout;
    SharedPreferences sharedPreferences;
    Intent mainIntent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_funcionario);

        sharedPreferences = getSharedPreferences("DatoCheckbox", MODE_PRIVATE);

        logout= (Button) findViewById(R.id.logoutButton);
        logout.setOnClickListener(new View.OnClickListener(){
            public void onClick(View view){
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putBoolean("checkBoxValue", false);
                editor.apply();
                editor.commit();
                mainIntent = new Intent(MenuFuncionarioActivity.this, MenuConsumidorActivity.class);
                startActivity(mainIntent);
            }
        });
        menuGrid = (GridLayout) findViewById(R.id.menuGrid);
        setSingleEvent(menuGrid);

    }
    private void setSingleEvent(GridLayout mainGrid) {
        for (int i = 0; i < mainGrid.getChildCount(); i++) {
            CardView cardView = (CardView) mainGrid.getChildAt(i);
            final int cardIndex = i;
            cardView.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View view) {

                    if (cardIndex == 0) {

                        intent = new Intent(MenuFuncionarioActivity.this, ListadoReclamosActivity.class); //muestra todos los no resueltos
                        intent.putExtra("FROM_ACTIVITY", "FUNCIONARIO");
                        startActivity(intent);
                    }else if(cardIndex == 1 ){
                        intent = new Intent(MenuFuncionarioActivity.this, AnadirProductoActivity.class);
                        intent.putExtra("FROM_ACTIVITY", "FUNCIONARIO");
                        startActivity(intent);

                    }else if(cardIndex == 2){
                        intent = new Intent(MenuFuncionarioActivity.this, ModificarProductoActivity.class);
                        intent.putExtra("FROM_ACTIVITY", "FUNCIONARIO");
                        startActivity(intent);
                    }else if (cardIndex == 3){
                        intent = new Intent(MenuFuncionarioActivity.this, ListadoReclamosActivity.class); //Filtra con firebase
                        intent.putExtra("FROM_ACTIVITY", "CONSUMIDOR");
                        startActivity(intent);
                    }
                }
            });
        }
    }


}