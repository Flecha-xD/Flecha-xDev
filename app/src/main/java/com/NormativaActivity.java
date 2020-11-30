package com;
import android.content.Intent;
import android.os.Bundle;
import androidx.cardview.widget.CardView;
import android.view.View;
import android.widget.GridLayout;

import androidx.appcompat.app.AppCompatActivity;

public class NormativaActivity extends AppCompatActivity {

    Intent intent;
    GridLayout menuGrid;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_normativa);
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

                        intent = new Intent(NormativaActivity.this, InformacionActivity.class);
                        intent.putExtra("info", "Accediendo a Código de Ley....." );
                        startActivity(intent);
                    }else if(cardIndex == 1 ){
                        intent = new Intent(NormativaActivity.this, InformacionActivity.class);
                        intent.putExtra("info", "Accediendo Reglamento de Supermercados....." );
                        startActivity(intent);

                    }else if(cardIndex == 2){
                        intent = new Intent(NormativaActivity.this, InformacionActivity.class);
                        intent.putExtra("info", "Accediendo A Reglamento de Publicidad....." );
                        startActivity(intent);
                    }
                }
            });
        }
    }


}
