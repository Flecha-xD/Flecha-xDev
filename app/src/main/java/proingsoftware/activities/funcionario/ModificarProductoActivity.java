package proingsoftware.activities.funcionario;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.pm.PackageManager;
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
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.R;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
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

import java.util.HashMap;
import java.util.UUID;

import proingsoftware.activities.superusuario.ModificarFuncionarioActivity;

public class ModificarProductoActivity extends AppCompatActivity {
    private final static int SELECT_PHOTO = 12345;
    Button anadir, borrar;
    ImageView imagenElegida;
    ImageButton galeria;
    TextView codigoProducto;
    Intent anadirIntent;
    Toast toast;
    public Uri photoURI;
    //Firebase variables
    FirebaseStorage storage;
    StorageReference mStorageReference;
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
        this.imagenElegida = (ImageView)this.findViewById(R.id.imageact);
        codigoProducto = findViewById(R.id.codact);
        galeria.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent photoPickerIntent = new Intent(Intent.ACTION_PICK);
                photoPickerIntent.setType("image/*");
                startActivityForResult(photoPickerIntent, SELECT_PHOTO);
            }
        });
        final String contra = "46025"; //hardcodeado
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

                if (password.equals(passFuncionarioDB) ) {
                    if (! nombre.equals("") && !descripcion.equals("") && !precio.equals("") ) {
                        HashMap hashMap = new HashMap();
                        hashMap.put("nombre", nombre);
                        hashMap.put("descripcion", descripcion);
                        hashMap.put("precio", precio);
                        productoRef.child("Productos Subsidio").child("Producto: " + codigo).updateChildren(hashMap).addOnSuccessListener(new OnSuccessListener() {
                            @Override
                            public void onSuccess(Object o) {
                                Toast.makeText(ModificarProductoActivity.this, "Se actualizo la info", Toast.LENGTH_SHORT).show();
                            }
                        });
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
                if (password.equals(passFuncionarioDB) ) {
                    productoRef.child("Productos Subsidio").child("Producto: " + codigo).removeValue();
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
                uploadPicture();
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
