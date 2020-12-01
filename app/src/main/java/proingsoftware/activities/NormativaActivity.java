package proingsoftware.activities;
import android.content.Intent;
import android.os.Bundle;
import androidx.cardview.widget.CardView;
import android.view.View;
import android.widget.GridLayout;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import proingsoftware.Adapters.NormativaAdapter;

import com.R;

import proingsoftware.model.Normativa;
import java.util.LinkedList;
import java.util.List;

public class NormativaActivity extends AppCompatActivity { // falta acomodar a la pantalla de información

    Intent intent;
    GridLayout menuGrid;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_seleccion_normativa);
        RecyclerView recyclerView = findViewById(R.id.recyclerViewMenu);

        List<Normativa> normativaList = new LinkedList<>();
        normativaList.add (new Normativa ("Ley N° 456","La presente " +
                "Ley tiene por objeto regular los derechos y garantías de las usuarias y" +
                " los usuarios, las consumidoras y los consumidores. "));
        normativaList.add (new Normativa ("Reglamento de Regulación de Supermercados",
                " El presente decreto supremo tiene por objeto la regulación del" +
                        " establecimiento y funcionamiento de las bolsas de productos, los mercados " +
                        "por ellas organizados, los sujetos que participen en los mismos, los actos y " +
                        "contratos que se celebren, así como los productos negociables en dichos mercados, " +
                        "de acuerdo con el Capítulo II del Título IV de la Ley del Mercado de Valores."));
        normativaList.add (new Normativa ("Reglamento de Publicidad Engañosa y Abuso",
                "El presente Reglamento tiene por objeto reglamentar el proceso de verificación y" +
                        " restauración en protección a los Derechos a la Información y a la libre elección, " +
                        "previniendo el incumplimiento del servicio ofertado y la utilización de la Publicidad " +
                        "Engañosa o Abusiva de productos y servicios, así como la aplicación de sanciones administrativas. "));
        NormativaAdapter adapter = new NormativaAdapter(this, normativaList);

        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));


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
