package mx.edu.unpa.proyectofinaldispositivosmoviles;

import android.os.Bundle;
import android.os.Environment;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.io.File;

import mx.edu.unpa.proyectofinaldispositivosmoviles.Fragments.Guardar_PDF;
import mx.edu.unpa.proyectofinaldispositivosmoviles.Fragments.Informacion;
import mx.edu.unpa.proyectofinaldispositivosmoviles.Fragments.Listas;
import mx.edu.unpa.proyectofinaldispositivosmoviles.Fragments.cargar_PDF;

public class MainActivity extends AppCompatActivity {
    Guardar_PDF firstFragment = new Guardar_PDF();
    cargar_PDF secondFragment = new cargar_PDF();
    Informacion informacion = new Informacion();
    Listas thirdFragment = new Listas();

    /*FragmentTransaction transaction;
    cargar_PDF cargar;
    Guardar_PDF guardarPDF;*/
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        BottomNavigationView navigation = findViewById(R.id.bottom_navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
        loadFragment(firstFragment);

    }


    String DIRECTORY_NAME = "MyPDFs";
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
    private final BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener= new BottomNavigationView.OnNavigationItemSelectedListener() {
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()){
                case R.id.firstFragment:
                    loadFragment(firstFragment);
                    return true;
                case R.id.secondFragment:
                    loadFragment(secondFragment);
                    return true;
                case R.id.thirdFragment:
                    loadFragment(thirdFragment);
                    return true;
            }
            return false;
        }
    };

    public  void  loadFragment(Fragment fragment){
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.frame_container,fragment);
        transaction.commit();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater= getMenuInflater();
        inflater.inflate(R.menu.action_bar_menu,menu);
        return true; //super.onCreateOptionsMenu(menu);
    }
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        switch (item.getItemId()){
            case R.id.help:
                loadFragment(informacion);
                Toast.makeText(MainActivity.this,"información",Toast.LENGTH_SHORT).show();
                return true;

            default:

                return super.onOptionsItemSelected(item);
        }



    }



}