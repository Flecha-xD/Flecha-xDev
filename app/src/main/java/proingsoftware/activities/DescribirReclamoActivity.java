package proingsoftware.activities;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import com.R;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class DescribirReclamoActivity extends AppCompatActivity {
    Context context = getApplicationContext();
    Button reclamar;
    Intent enviarReclamo;
    private static final int CAMERA_REQUEST = 1888;
    private final static int SELECT_PHOTO = 12345;
    private ImageView imageView;
    private static final int MY_CAMERA_PERMISSION_CODE = 100;
    SharedPreferences sharedPreferences;
    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference myRef = database.getReference("message");
    ImageButton camara, galeria;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_describir_reclamo);
        this.imageView = (ImageView) this.findViewById(R.id.imagetoupload);
        sharedPreferences = getSharedPreferences("DatoCheckbox", MODE_PRIVATE);
        ;
        camara = (ImageButton) findViewById(R.id.camaraButton);
        galeria = (ImageButton) findViewById(R.id.galeriaButton);

        reclamar = findViewById(R.id.enviarreclamoButton);

        reclamar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String descrip = ((EditText) findViewById(R.id.descripqueja)).getText().toString();

                if (descrip != null) { //AQUI ENLAZAR LA BASE DE DATOS CON VALIDACIONES y que
                    if (sharedPreferences.getBoolean("DatoCheckbox", true)) {
                        descrip = "PRODUCTO SUBSIDIO:" + descrip;
                    }
                    enviarReclamo = new Intent(DescribirReclamoActivity.this, MenuConsumidorActivity.class);
                    startActivity(enviarReclamo);
                    CharSequence text = "Su reclamo será atendido lo antes posible";
                    Toast toast = Toast.makeText(context, text, Toast.LENGTH_LONG);
                    toast.show();
                } else {
                    CharSequence text = "Datos Incompletos";
                    Toast toast = Toast.makeText(context, text, Toast.LENGTH_SHORT);
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






        camara.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.M)
            @Override
            public void onClick(View v) {
                if (checkSelfPermission(Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
                    requestPermissions(new String[]{Manifest.permission.CAMERA}, MY_CAMERA_PERMISSION_CODE);
                } else {
                    Intent cameraIntent = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
                    startActivityForResult(cameraIntent, CAMERA_REQUEST);
                }
            }
        });
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == MY_CAMERA_PERMISSION_CODE) {
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                Toast.makeText(this, "Permiso de acceso", Toast.LENGTH_LONG).show();
                Intent cameraIntent = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(cameraIntent, CAMERA_REQUEST);
            } else {
                Toast.makeText(this, "Permiso denegado", Toast.LENGTH_LONG).show();
            }
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK && data != null) {
            if (requestCode == CAMERA_REQUEST ) {
                Bitmap photo = (Bitmap) data.getExtras().get("data");
                imageView.setImageBitmap(photo); //esta photo debe ser guardada en la bdd
            }
            if (requestCode == SELECT_PHOTO) {
                Uri pickedImage = data.getData();
                String[] filePath = {MediaStore.Images.Media.DATA};
                Cursor cursor = getContentResolver().query(pickedImage, filePath, null, null, null);
                cursor.moveToFirst();
                String imagePath = cursor.getString(cursor.getColumnIndex(filePath[0]));
                BitmapFactory.Options options = new BitmapFactory.Options();
                options.inPreferredConfig = Bitmap.Config.ARGB_8888;
                Bitmap photo = BitmapFactory.decodeFile(imagePath, options);
                imageView.setImageBitmap(photo); //IGUAL ESTA FOTO DEBERIA ALMACENARSE
                cursor.close();
            }
        }
    }
 }