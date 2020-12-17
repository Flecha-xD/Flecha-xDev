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

public class ModificarProductoActivity extends AppCompatActivity {
    private final static int SELECT_PHOTO = 12345;
    Button anadir, borrar;
    ImageView imagenElegida;
    ImageButton galeria;
    Intent anadirIntent;
    Toast toast;
    //Firebase variables
    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference productoRef = database.getReference();
    private String codigoFuncionarioDB;
    private String passFuncionarioDB;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_modificar_producto);
        anadir = findViewById(R.id.actprod);
        borrar = findViewById(R.id.delprod);
        galeria = findViewById(R.id.galeria3Button);
        imagenElegida = findViewById(R.id.imageact);
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
                String nombre = ((EditText) findViewById(R.id.nombreprodact)).getText().toString();
                String descripcion = ((EditText) findViewById(R.id.descact)).getText().toString();
                String precio = ((EditText) findViewById(R.id.precioact)).getText().toString();
                String codigo = ((EditText) findViewById(R.id.codact)).getText().toString();
                String codigoFunc =  ((EditText) findViewById(R.id.codigofuncEP)).getText().toString();
                String password = ((EditText) findViewById(R.id.contraseniaact)).getText().toString();

                //get CODIGO func from db
                productoRef.child("Funcionarios").child("Funcionario: " + codigoFunc).child("codigo").addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        //Si existe el superadmin y si el boolean "esAdmin" es true
                        if(snapshot.exists()){
                            codigoFuncionarioDB = snapshot.getValue().toString();
                        }
                    }
                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {
                        toast = Toast.makeText(getApplicationContext(), "Error al conectar con la base de datos", Toast.LENGTH_SHORT);
                        toast.show();
                    }
                });

                //get PASSWORD SUPERADMIN from db
                productoRef.child("Funcionarios").child("Funcionario: " + codigoFunc).child("password").addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        //Si existe el superadmin y si el boolean "esAdmin" es true
                        if(snapshot.exists() ){
                            passFuncionarioDB = snapshot.getValue().toString();
                        }
                    }
                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {
                        toast = Toast.makeText(getApplicationContext(), "Error al conectar con la base de datos", Toast.LENGTH_SHORT);
                        toast.show();
                    }
                });

                if (password.equals(passFuncionarioDB) ) { //AQUI ENLAZAR LA BASE DE DATOS CON VALIDACIONES y que
                    if (nombre != null && descripcion != null && precio != null && codigo != null ) {//validacion momentanea)
                        //compare todos los datos
                        anadirIntent = new Intent(ModificarProductoActivity.this, MenuFuncionarioActivity.class);
                        startActivity(anadirIntent);
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

        borrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String password = ((EditText) findViewById(R.id.contraseniaact)).getText().toString();

                if (password.equals(contra) ) { //AQUI ENLAZAR LA BASE DE DATOS CON VALIDACIONES y que
                    Toast toast = Toast.makeText(getApplicationContext(), "Producto borrado", Toast.LENGTH_SHORT);
                    toast.show();
                    anadirIntent = new Intent(ModificarProductoActivity.this, MenuFuncionarioActivity.class);
                    startActivity(anadirIntent);
                } else {

                    Toast toast = Toast.makeText(getApplicationContext(), "Introduzca la contraseña", Toast.LENGTH_SHORT);
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
