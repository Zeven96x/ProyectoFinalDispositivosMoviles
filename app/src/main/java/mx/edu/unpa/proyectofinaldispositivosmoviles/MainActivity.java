package mx.edu.unpa.proyectofinaldispositivosmoviles;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.io.File;

import static android.widget.Toast.LENGTH_SHORT;

public class MainActivity extends AppCompatActivity {
    String DIRECTORY_NAME = "Solicitud_Empleo";
    String DOCUMENT_NAME;
    private static  final int WRITE_STORAGE_CODE=100;
    private EditText txtNombre, txtContenido;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        String permission;
        int result;
        switch (requestCode){
            case WRITE_STORAGE_CODE:
                permission = permissions[0];
                result = grantResults[0];
                if(permission.equals(Manifest.permission.WRITE_EXTERNAL_STORAGE)) {
                    if (result == PackageManager.PERMISSION_GRANTED) {
                        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
                            return;
                        }
                        //createPDF(txtNombre.getText().toString(),txtContenido.getText().toString());
                    } else {
                        Toast.makeText(this, "PERMISSION DENIED", Toast.LENGTH_LONG).show();
                    }
                }
                break;
            default:
                super.onRequestPermissionsResult(requestCode, permissions, grantResults);
                break;
        }
    }

    //metodo consultar
    public void  Consultar(View view){
        try {
            displayPDF();
        }catch (Exception e){
            Toast.makeText(this,"error al leer el archivo", LENGTH_SHORT).show();
        }
    }
    public void displayPDF() {
        File file = new File(getPath() + "/" + txtNombre.getText().toString()+".pdf");
        Toast.makeText(MainActivity.this, Uri.fromFile(file).toString() , Toast.LENGTH_LONG).show();
        if(file.exists()) {
            Intent target = new Intent(Intent.ACTION_VIEW);
            if (Build.VERSION.SDK_INT >=  Build.VERSION_CODES.N) {
                target.setDataAndType(Uri.parse(file.getPath()), "application/pdf");
            } else{
                target.setDataAndType(Uri.fromFile(file), "application/pdf");
            }
            target.setFlags(Intent.FLAG_ACTIVITY_NO_HISTORY | Intent.FLAG_GRANT_READ_URI_PERMISSION);
            Intent intent = Intent.createChooser(target, "Open File");
            try {
                startActivity(intent);
            } catch (ActivityNotFoundException e) {

            }
        }
        else
            Toast.makeText(getApplicationContext(), "File path is incorrect." , Toast.LENGTH_LONG).show();
    }
    public File createFile(String fileName) {
        File path = getPath();
        File file = null;
        if(path != null) {
            file = new File(path, fileName);
        }
        return file;
    }

    public File getPath() {
        File path = null;
        if(Environment.MEDIA_MOUNTED.equals(Environment.getExternalStorageState())) {
            path = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOCUMENTS), DIRECTORY_NAME);
            if(path != null) {
                if(!path.mkdirs()) {
                    if(!path.exists()) {
                        return null;
                    }
                }
            }
        }
        return path;
    }
}