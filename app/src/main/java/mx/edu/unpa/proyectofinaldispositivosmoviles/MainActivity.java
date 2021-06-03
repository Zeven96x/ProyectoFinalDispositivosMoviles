package mx.edu.unpa.proyectofinaldispositivosmoviles;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.FragmentTransaction;

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

import mx.edu.unpa.proyectofinaldispositivosmoviles.Fragments.BlueFragment;
import mx.edu.unpa.proyectofinaldispositivosmoviles.Fragments.RedFragment;

import static android.widget.Toast.LENGTH_SHORT;

public class MainActivity extends AppCompatActivity {
    FragmentTransaction transaction;
    BlueFragment blueFragment;
    RedFragment redFragment;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //
        blueFragment = new BlueFragment();
        redFragment  = new RedFragment();
        getSupportFragmentManager().beginTransaction().add(R.id.idFragments, blueFragment).commit();

    }
    public void OnClik(View view){
        transaction = getSupportFragmentManager().beginTransaction();
        switch(view.getId()){
            case R.id.blue:
                transaction.replace(R.id.idFragments,blueFragment).commit();
                break;
            case R.id.red:
                transaction.replace(R.id.idFragments,redFragment).commit();
                break;
            default:
        }
    }

}