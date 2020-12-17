package proingsoftware.activities.funcionario;

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

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import com.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.UUID;

import proingsoftware.model.ProductoFirebase;
import proingsoftware.model.ReclamoFirebase;

public class AnadirProductoActivity extends AppCompatActivity {
    private final static int SELECT_PHOTO = 12345;
    Button anadir;
    ImageView imagenElegida;
    ImageButton galeria;
    Intent cambiarIntent;
    Toast toast;

    //Firebase variables
    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference subsidioRef = database.getReference();
    private String funcionarioPass;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_anadir_producto);
        anadir = findViewById(R.id.addprod1);
        galeria = findViewById(R.id.galeria2Button);
        imagenElegida = findViewById(R.id.imagetoupload2);
        final String contra = "4602546025"; //hardcodeado
        anadir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String nombre = ((EditText) findViewById(R.id.nombreprod)).getText().toString();
                String descripcion = ((EditText) findViewById(R.id.descProd)).getText().toString();
                String precio = ((EditText) findViewById(R.id.precio)).getText().toString();
                String codigo = ((EditText) findViewById(R.id.codprod)).getText().toString();
                String codigofunc = ((EditText) findViewById(R.id.codigofuncprod)).getText().toString();
                String password = ((EditText) findViewById(R.id.contraseniaAdd)).getText().toString();

                //TODO arreglar y verificar condicion
                subsidioRef.child("Funcionarios").child("Funcionario: " + codigofunc).child("password").addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        //Si existe el funcionario
                        if(snapshot.exists() ){
                            funcionarioPass = snapshot.getValue().toString();
                        }
                    }
                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {
                        toast = Toast.makeText(getApplicationContext(), "Error al conectar con la base de datos", Toast.LENGTH_SHORT);
                        toast.show();
                    }
                });
                if (password.equals(funcionarioPass)) { //AQUI ENLAZAR LA BASE DE DATOS CON VALIDACIONES y que
                    if (nombre != null && descripcion != null && precio != null && codigo != null ) {//validacion momentanea)
                        //compare todos los datos

                        ProductoFirebase producto = new ProductoFirebase(nombre,descripcion,precio,codigo);
                        subsidioRef.child("Productos Subsidio").child("Producto: " + codigo).setValue(producto);

                        Toast toast = Toast.makeText(getApplicationContext(), "Producto Añadido", Toast.LENGTH_LONG);
                        toast.show();
                        cambiarIntent = new Intent(AnadirProductoActivity.this, MenuFuncionarioActivity.class);
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
        galeria.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent photoPickerIntent = new Intent(Intent.ACTION_PICK);
                photoPickerIntent.setType("image/*");
                startActivityForResult(photoPickerIntent, SELECT_PHOTO);
            }
        });
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