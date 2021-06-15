package mx.edu.unpa.proyectofinaldispositivosmoviles;

import android.os.Bundle;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import mx.edu.unpa.proyectofinaldispositivosmoviles.Fragments.Guardar_PDF;
import mx.edu.unpa.proyectofinaldispositivosmoviles.Fragments.Listas;
import mx.edu.unpa.proyectofinaldispositivosmoviles.Fragments.cargar_PDF;

public class MainActivity extends AppCompatActivity {
    Guardar_PDF firstFragment = new Guardar_PDF();
    cargar_PDF secondFragment = new cargar_PDF();
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


}