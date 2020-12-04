package proingsoftware.activities;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import com.R;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class AnadirProductoActivity extends AppCompatActivity {
    private final static int SELECT_PHOTO = 12345;
    Context context = getApplicationContext();
    Button anadir, borrar;
    ImageView imagenElegida;
    ImageButton galeria;
    Intent anadirIntent;
    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference myRef = database.getReference("message");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_anadir_producto);
        anadir = findViewById(R.id.addprod);
        borrar = findViewById(R.id.delprod);
        galeria = findViewById(R.id.galeria2Button);
        imagenElegida = findViewById(R.id.imagetoupload2);
        galeria.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent photoPickerIntent = new Intent(Intent.ACTION_PICK);
                photoPickerIntent.setType("image/*");
                startActivityForResult(photoPickerIntent, SELECT_PHOTO);
            }
        });
        final String contra = "4602546025"; //hardcodeado
        anadir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String nombre = ((EditText) findViewById(R.id.nombreprod)).getText().toString();
                String descripcion = ((EditText) findViewById(R.id.descProd)).getText().toString();
                String precio = ((EditText) findViewById(R.id.precio)).getText().toString();
                String codigo = ((EditText) findViewById(R.id.codprod)).getText().toString();
                String password = ((EditText) findViewById(R.id.contraseniaAdd)).getText().toString();

                if (password.equals(contra) ) { //AQUI ENLAZAR LA BASE DE DATOS CON VALIDACIONES y que
                    if (nombre != null && descripcion != null && precio != null && codigo != null ) {//validacion momentanea)
                    //compare todos los datos
                        anadirIntent = new Intent(AnadirProductoActivity.this, MenuFuncionarioActivity.class);
                        startActivity(anadirIntent);
                    } else {

                        Toast toast = Toast.makeText(context, "Algún dato vacío", Toast.LENGTH_LONG);
                        toast.show();

                    }
                } else {
                    Toast toast = Toast.makeText(context, "Contraseña equivocada", Toast.LENGTH_LONG);
                    toast.show();

                }

            }
        })
        ;

        borrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String password = ((EditText) findViewById(R.id.contraseniaAdd)).getText().toString();

                if (password.equals(contra) ) { //AQUI ENLAZAR LA BASE DE DATOS CON VALIDACIONES y que
                    Toast toast = Toast.makeText(context, "Producto borrado", Toast.LENGTH_SHORT);
                    toast.show();
                         anadirIntent = new Intent(AnadirProductoActivity.this, MenuFuncionarioActivity.class);
                        startActivity(anadirIntent);
                    } else {

                        Toast toast = Toast.makeText(context, "Introduzca la contraseña", Toast.LENGTH_SHORT);
                        toast.show();

                    }


            }
        })
        ;

    }



    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == SELECT_PHOTO && resultCode == RESULT_OK && data != null) {
            Uri pickedImage = data.getData();
            String[] filePath = { MediaStore.Images.Media.DATA };
            Cursor cursor = getContentResolver().query(pickedImage, filePath, null, null, null);
            cursor.moveToFirst();
            String imagePath = cursor.getString(cursor.getColumnIndex(filePath[0]));

            BitmapFactory.Options options = new BitmapFactory.Options();
            options.inPreferredConfig = Bitmap.Config.ARGB_8888;
            Bitmap bitmap = BitmapFactory.decodeFile(imagePath, options);
            imagenElegida.setImageBitmap(bitmap);
            cursor.close();
        }
    }

}