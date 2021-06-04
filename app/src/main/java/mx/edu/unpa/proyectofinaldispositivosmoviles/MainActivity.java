package mx.edu.unpa.proyectofinaldispositivosmoviles;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;
import android.os.Bundle;
import android.view.View;
import mx.edu.unpa.proyectofinaldispositivosmoviles.Fragments.Guardar_PDF;
import mx.edu.unpa.proyectofinaldispositivosmoviles.Fragments.cargar_PDF;

public class MainActivity extends AppCompatActivity {
    FragmentTransaction transaction;
    cargar_PDF cargarPDF;
    Guardar_PDF guardarPDF;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //
        guardarPDF = new Guardar_PDF();
        cargarPDF  = new cargar_PDF();

    }
    public void OnClik(View view){
        transaction = getSupportFragmentManager().beginTransaction();
        switch(view.getId()){
            case R.id.guardar:
                transaction.replace(R.id.idFragments,guardarPDF);
                transaction.addToBackStack(null);
                break;
            case R.id.buscar:
                transaction.replace(R.id.idFragments,cargarPDF);
                transaction.addToBackStack(null);
                break;
            default:
        }
        transaction.commit();
    }

}