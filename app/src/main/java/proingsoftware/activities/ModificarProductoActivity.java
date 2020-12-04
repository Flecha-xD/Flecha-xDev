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

public class ModificarProductoActivity extends AppCompatActivity {
    private final static int SELECT_PHOTO = 12345;
    Button cambiar;
    ImageView imagenElegida;
    ImageButton galeria;
    Intent cambiarIntent;
   //  FirebaseDatabase database = FirebaseDatabase.getInstance();
   //  DatabaseReference myRef = database.getReference("message");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_modificar_producto);
        cambiar = findViewById(R.id.actprod);
        galeria = findViewById(R.id.galeria3Button);
        imagenElegida = findViewById(R.id.imagetoupload3);
        galeria.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent photoPickerIntent = new Intent(Intent.ACTION_PICK);
                photoPickerIntent.setType("image/*");
                startActivityForResult(photoPickerIntent, SELECT_PHOTO);
            }
        });
        final String contra = "4602546025"; //hardcodeado
        cambiar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String nombre = ((EditText) findViewById(R.id.nombreprod)).getText().toString();
                String descripcion = ((EditText) findViewById(R.id.descProd)).getText().toString();
                String precio = ((EditText) findViewById(R.id.precio)).getText().toString();
                String codigo = ((EditText) findViewById(R.id.codprod)).getText().toString();
                String password = ((EditText) findViewById(R.id.contrasenia2)).getText().toString();

                if (password.equals(contra) ) { //AQUI ENLAZAR LA BASE DE DATOS CON VALIDACIONES y que
                    if (nombre != null && descripcion != null && precio != null && codigo != null ) {//validacion momentanea)
                        //compare todos los datos
                        Toast toast = Toast.makeText(getApplicationContext(), "Producto Añadido", Toast.LENGTH_LONG);
                        toast.show();
                        cambiarIntent = new Intent(ModificarProductoActivity.this, MenuFuncionarioActivity.class);
                        startActivity(cambiarIntent);
                    } else {

                        Toast toast = Toast.makeText(getApplicationContext(), "Algún dato vacío", Toast.LENGTH_LONG);
                        toast.show();

                    }
                } else {
                    Toast toast = Toast.makeText(getApplicationContext(), "Contraseña equivocada", Toast.LENGTH_LONG);
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