package proingsoftware.activities.funcionario;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import androidx.cardview.widget.CardView;
import android.view.View;
import android.widget.GridLayout;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.R;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class ContactoFuncionarioActivity extends AppCompatActivity { //en teoria, ya esta

    Intent intent;
    GridLayout menuGrid;
    Toast toast;
    Bundle extras ;
    private String id, correo, cel;
    //Firebase variables
    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference reclamoRef = database.getReference();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contacto_funcionario);
        menuGrid = (GridLayout) findViewById(R.id.menuGrid);
        setSingleEvent(menuGrid);

        extras = getIntent().getExtras();
        id = extras.getString("id");
        cel = extras.getString("cel");
        correo = extras.getString("correo");
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
                        intent.setData(Uri.parse("tel:"+cel));
                        startActivity(intent);
                    }else if(cardIndex == 1 ){
                        Intent intent = new Intent(Intent.ACTION_DIAL);
                        intent.setData(Uri.parse("tel:22158900"));
                        startActivity(intent);
                    }else if(cardIndex == 2){
                        Intent intent=new Intent(Intent.ACTION_SEND);
                        intent.putExtra(Intent.EXTRA_EMAIL, correo);
                        //get del correo del reclamador
                        intent.setType("text/html");
                        intent.setPackage("com.google.android.gm");
                        startActivity(Intent.createChooser(intent, "Enviar un Mail"));
                    }else if(cardIndex == 3){
                        intent = new Intent(ContactoFuncionarioActivity.this, MenuFuncionarioActivity.class);
                        //Cambiar el boolean "fueAtendido" a true
                        reclamoRef.child("Reclamos").child(id).child("fueAtendido").setValue(true);
                        toast = Toast.makeText(getApplicationContext(), "Reclamo Atendido", Toast.LENGTH_SHORT);
                        toast.show();
                        //cambiar estado del reclamo en base de datos.
                        startActivity(intent);
                    }
                }
            });
        }
    }


}
