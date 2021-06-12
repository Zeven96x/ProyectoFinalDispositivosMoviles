package mx.edu.unpa.proyectofinaldispositivosmoviles.Fragments;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.os.Environment;
import android.provider.DocumentsContract;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import mx.edu.unpa.proyectofinaldispositivosmoviles.R;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link Listas#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Listas extends Fragment implements AdapterView.OnItemClickListener {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public Listas() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment Listas.
     */
    // TODO: Rename and change types and number of parameters
    public static Listas newInstance(String param1, String param2) {
        Listas fragment = new Listas();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    String DIRECTORY_NAME = "MyPDFs";
    private  List<String> nombresArchivos;
    private  List<String> rutasArchivos;
    private ArrayAdapter<String> adaptador;
    private  String directorioRaiz;
    private TextView carpetaActual;
    ListView listas;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v= inflater.inflate(R.layout.fragment_listas, container, false);

        carpetaActual= v.findViewById(R.id.Ruta_Actual);
        listas=v.findViewById(R.id.listview_list);
        //directorio raiz
        directorioRaiz=getPath()+"";

        listas.setOnItemClickListener(this);
        verDirectorio(directorioRaiz);
        return v;
    }

    private void verDirectorio(String rutaDirectorio){
        nombresArchivos = new ArrayList<String>();
        rutasArchivos= new ArrayList<String>();
        int count=0;
        //
        File directorioActual= new File(rutaDirectorio);
        File[] listaArchivos = directorioActual.listFiles();



        if(!rutaDirectorio.equals(directorioRaiz)){
            nombresArchivos.add("../");
            rutasArchivos.add(directorioActual.getParent());
            count=1;
        }

        //ALMACENA LA RUTA DE LOS ARCHIVOS Y LAS CARPETAS DEL DIRECTORIO
        for (File file:listaArchivos) {
            rutasArchivos.add(file.getPath());
        }
        //ORDENA LOS ARCHIVOS DE MANERA ALFABETICA
        Collections.sort(rutasArchivos,String.CASE_INSENSITIVE_ORDER);
        //recorremos la lista de archivos para mostrar el el listview
        for (int i = count; i <rutasArchivos.size() ; i++) {
            File archivo= new File(rutasArchivos.get(i));
            if(archivo.isFile()){
                nombresArchivos.add(archivo.getName());
            }else{
                nombresArchivos.add("/"+archivo.getName());
            }
        }

        if (listaArchivos.length<1){
            nombresArchivos.add("no hay ningun archivo");
            rutasArchivos.add(rutaDirectorio);
        }

        adaptador= new ArrayAdapter<String>(getActivity(),R.layout.lista_archivos,nombresArchivos);
        listas.setAdapter(adaptador);

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

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
           File archivo= new File(rutasArchivos.get(position));
            if (archivo.isFile()){
                Toast.makeText(getActivity(),"has selecionado el archivo "+archivo.getName(),Toast.LENGTH_LONG).show();
            }else{
                verDirectorio(rutasArchivos.get(position));
            }

    }
}