package proingsoftware.activities;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import androidx.cardview.widget.CardView;
import android.view.View;
import android.widget.GridLayout;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.R;

public class ContactoConsumidorActivity extends AppCompatActivity { //en teoria, ya esta

    Intent intent;
    GridLayout menuGrid;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_seleccion_normativa);
        RecyclerView recyclerView = findViewById(R.id.recyclerViewMenu);
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
                        Uri uri = Uri.fromParts("telefono", "22141040", null);
                        Intent newIntent = new Intent(Intent.ACTION_CALL, uri);
                        newIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        startActivity(newIntent);
                    }else if(cardIndex == 1 ){
                        Intent intent=new Intent(Intent.ACTION_SEND);
                        intent.putExtra(Intent.EXTRA_EMAIL, "Minjusticia@Justicia.Gob.Bo");
                        intent.setType("text/html");
                        intent.setPackage("com.google.android.gm");
                        startActivity(Intent.createChooser(intent, "Enviar un Mail"));
                    }else if(cardIndex == 2){
                        Intent browserIntent = new Intent(Intent.ACTION_VIEW,Uri.parse("https://consumidor.justicia.gob.bo/"));
                        browserIntent.putExtra("info", "Redireccionando a Web....." );
                        startActivity(browserIntent);
                    }else if(cardIndex == 3){
                        Intent browserIntent = new Intent(Intent.ACTION_VIEW,Uri.parse("https://goo.gl/maps/Ut9UPaF5ike25XnS9"));
                        browserIntent.putExtra("info", "Localizando en Mapa....." );
                        startActivity(browserIntent);
                    }
                }
            });
        }
    }


}
