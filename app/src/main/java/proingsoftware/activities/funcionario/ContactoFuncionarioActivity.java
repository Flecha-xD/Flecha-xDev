package proingsoftware.activities.funcionario;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import androidx.cardview.widget.CardView;
import android.view.View;
import android.widget.GridLayout;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.R;

public class ContactoFuncionarioActivity extends AppCompatActivity { //en teoria, ya esta

    Intent intent;
    GridLayout menuGrid;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contacto_funcionario);
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
                        Intent intent = new Intent(Intent.ACTION_DIAL);
                        intent.setData(Uri.parse("tel:70576881"));
                        startActivity(intent);
                    }else if(cardIndex == 1 ){
                        Uri uri = Uri.fromParts("Buscando Distribuidor", "2240006", null); //poner una variable para el unmerodesde bdd
                        Intent newIntent = new Intent(Intent.ACTION_CALL, uri);
                        newIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        startActivity(newIntent);
                    }else if(cardIndex == 2){
                        Intent intent=new Intent(Intent.ACTION_SEND);
                        intent.putExtra(Intent.EXTRA_EMAIL, "f.l.m.m.0027@gmail.com"); //poner variable que obtenga desde bdd
                        intent.setType("text/html");
                        intent.setPackage("com.google.android.gm");
                        startActivity(Intent.createChooser(intent, "Enviar un Mail"));
                    }else if(cardIndex == 3){
                        intent = new Intent(ContactoFuncionarioActivity.this, MenuFuncionarioActivity.class);
                        intent.putExtra("info", "Asociado en Base de Datos....." ); //cambiar estado del reclamo en base de datos.
                        startActivity(intent);
                    }
                }
            });
        }
    }


}
