package proingsoftware.activities;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.GridLayout;
import android.widget.ImageButton;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import com.R;

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
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putBoolean("checkBoxValue", false);
                editor.apply();
                editor.commit();
                mainIntent = new Intent(MenuConsumidorActivity.this, ConfiguracionActivity.class);
                startActivity(mainIntent);
            }
        });
        sharedPreferences = getSharedPreferences("DatoCheckbox", MODE_PRIVATE);
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
                        intent.putExtra("info", "Accediendo asistencia....." );
                        startActivity(intent);
                    }else if(cardIndex == 1 ){
                        intent = new Intent(MenuConsumidorActivity.this, ProductosSubsidioActivity.class);
                        intent.putExtra("info", "Accediendo Parqueo....." );
                        startActivity(intent);

                    }else if(cardIndex == 2){
                        intent = new Intent(MenuConsumidorActivity.this, ContactoConsumidorActivity.class);
                        intent.putExtra("info", "Accediendo A Notas....." );
                        startActivity(intent);
                    }else if (cardIndex == 3){
                        intent = new Intent(MenuConsumidorActivity.this, NormativaActivity.class);
                        intent.putExtra("info", "Accediendo A Biblioteda....." );
                        startActivity(intent);
                    }
                }
            });
        }
    }


}