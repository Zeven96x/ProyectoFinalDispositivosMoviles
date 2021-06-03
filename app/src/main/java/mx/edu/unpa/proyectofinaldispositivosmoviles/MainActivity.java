package mx.edu.unpa.proyectofinaldispositivosmoviles;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.View;

import mx.edu.unpa.proyectofinaldispositivosmoviles.Fragments.GuardarPDF;
import mx.edu.unpa.proyectofinaldispositivosmoviles.Fragments.cargarPDF;

public class MainActivity extends AppCompatActivity {
    FragmentTransaction transaction;
    cargarPDF cargarPDF;
    GuardarPDF guardarPDF;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //
        guardarPDF = new GuardarPDF();
        cargarPDF  = new cargarPDF();

    }
    public void OnClik(View view){
        transaction = getSupportFragmentManager().beginTransaction();
        switch(view.getId()){
            case R.id.guardar:
                transaction.replace(R.id.idFragments,guardarPDF).commit();
                transaction.addToBackStack(null);
                break;
            case R.id.buscar:
                transaction.replace(R.id.idFragments,cargarPDF).commit();
                transaction.addToBackStack(null);
                break;
            default:
        }
    }

}