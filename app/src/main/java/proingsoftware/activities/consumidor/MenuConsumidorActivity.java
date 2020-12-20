package proingsoftware.activities.consumidor;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.GridLayout;
import android.widget.ImageButton;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import com.R;

import proingsoftware.activities.funcionario.AnadirProductoActivity;
import proingsoftware.activities.funcionario.ModificarProductoActivity;

public class MenuConsumidorActivity extends AppCompatActivity { //reparar el xml
    Intent intent;
    GridLayout menuGrid;
    ImageButton config;
    SharedPreferences sharedPreferences;
    Intent mainIntent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_consumidor);
        config= (ImageButton) findViewById(R.id.configbutton);
        config.setOnClickListener(new View.OnClickListener(){
            public void onClick(View view){
                mainIntent = new Intent(MenuConsumidorActivity.this, ConfiguracionActivity.class);
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

                        intent = new Intent(MenuConsumidorActivity.this, HacerReclamoActivity.class);
                        intent.putExtra("FROM_ACTIVITY", "CONSUMIDOR");
                        startActivity(intent);
                    }else if(cardIndex == 1 ){
                        intent = new Intent(MenuConsumidorActivity.this, ProductosSubsidioActivity.class);
                        intent.putExtra("FROM_ACTIVITY", "CONSUMIDOR");
                        startActivity(intent);

                    }else if(cardIndex == 2){
                        intent = new Intent(MenuConsumidorActivity.this, ContactoConsumidorActivity.class);
                        intent.putExtra("FROM_ACTIVITY", "CONSUMIDOR");
                        startActivity(intent);
                    }else if (cardIndex == 3){
                        intent = new Intent(MenuConsumidorActivity.this, SeleccionNormativaActivity.class);
                        intent.putExtra("FROM_ACTIVITY", "CONSUMIDOR");
                        startActivity(intent);
                    }
                }
            });
        }
    }
    @Override
    public void onBackPressed() {
        moveTaskToBack(true);
    }

}