package proingsoftware.activities.funcionario;

import android.app.ProgressDialog;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import com.R;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

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
    String foto, nombre, descripcion, precio, codigo;
    public Uri photoURI;
    //Firebase variables
    FirebaseStorage storage;
    StorageReference mStorageReference;
    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference subsidioRef = database.getReference();
    private String funcionarioPass;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_anadir_producto);
        storage = FirebaseStorage.getInstance();
        mStorageReference = FirebaseStorage.getInstance().getReference();

        anadir = findViewById(R.id.addprod1);
        galeria = findViewById(R.id.galeria2Button);
        imagenElegida = findViewById(R.id.imagetoupload2);
        final String contra = "4602546025"; //hardcodeado
        anadir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                 nombre = ((EditText) findViewById(R.id.nombreprod)).getText().toString();
                 descripcion = ((EditText) findViewById(R.id.descProd)).getText().toString();
                 precio = ((EditText) findViewById(R.id.precio)).getText().toString();
                 codigo = ((EditText) findViewById(R.id.codprod)).getText().toString();
                String codigofunc = ((EditText) findViewById(R.id.codigofuncprod)).getText().toString();
                String password = ((EditText) findViewById(R.id.contraseniaAdd)).getText().toString();
                subsidioRef.child("Funcionarios").child("Funcionario: " + codigofunc).child("password").addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
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
                        uploadPicture();
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


    //mostar foto en pantalla

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK && data != null) {
            if (requestCode == SELECT_PHOTO) {
                Uri pickedImage = data.getData();
                String[] filePath = {MediaStore.Images.Media.DATA};
                photoURI = pickedImage;
                imagenElegida.setClipToOutline(true);
                imagenElegida.setImageURI(photoURI);
            }
        }
    }
    //codigo para subir la fotografía al storage de producots/images

    private void uploadPicture() {

        final ProgressDialog pd = new ProgressDialog(this);
        pd.setTitle("Cargando Imagen...");
        pd.show();
        final String randomName = UUID.randomUUID().toString();
        StorageReference riversRef = mStorageReference.child("productos/images/" + randomName);

        riversRef.putFile(photoURI)
                .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                    @Override
                    public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                        pd.dismiss();
                        Snackbar.make(findViewById(android.R.id.content), "Imagen cargada", Snackbar.LENGTH_SHORT).show();
                        foto= "https://firebasestorage.googleapis.com/v0/b/flecha-xd.appspot.com/o/productos%2Fimages%2F" +randomName+ "?alt=media&token=43960bb8-c62a-45d4-9110-1a93b1370e94";
                        ProductoFirebase producto = new ProductoFirebase(nombre,descripcion,precio,codigo, foto);
                        subsidioRef.child("Productos Subsidio").child("Producto: " + codigo).setValue(producto);

                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception exception) {
                        pd.dismiss();
                        Toast.makeText(getApplicationContext(),  "Error al cargar imagen", Toast.LENGTH_LONG).show();
                    }
                })
                .addOnProgressListener(new OnProgressListener<UploadTask.TaskSnapshot>() {
                    @Override
                    public void onProgress(@NonNull UploadTask.TaskSnapshot snapshot) {
                        double porcentaje =  (100.00 * snapshot.getBytesTransferred() / snapshot.getTotalByteCount());
                        pd.setMessage("Porcentaje: " + (int) porcentaje + "%");
                    }
                });
    }

}
