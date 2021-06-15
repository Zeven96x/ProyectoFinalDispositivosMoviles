package mx.edu.unpa.proyectofinaldispositivosmoviles.Fragments;

import android.app.usage.UsageEvents;
import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;

import android.os.Environment;
import android.provider.CalendarContract;
import android.provider.DocumentsContract;
import android.util.EventLog;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.lowagie.text.Document;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import mx.edu.unpa.proyectofinaldispositivosmoviles.R;

import static android.Manifest.permission.READ_EXTERNAL_STORAGE;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link Listas#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Listas extends Fragment  {

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


    //esto es para crear un txt con lo nombres de los pdf
    private static  final  String FILE_NAME="Lista_pdf.txt";
    ListView _listView;
    List<String> studentsList;
    List<String> list = new ArrayList<String>();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v= inflater.inflate(R.layout.fragment_listas, container, false);
        _listView = (ListView) v.findViewById(R.id.listViewStudents);
        studentsList = new ArrayList<String>();
        ver();
        String[] aux= list.get(0).split("/");
        for (int i = 0; i < aux.length; i++) {
            studentsList.add(aux[i]);
        }

        ArrayAdapter<String> _arrayAdapter =
                new ArrayAdapter<String>(getActivity(), android.R.layout.simple_list_item_1, studentsList);

        _listView.setAdapter(_arrayAdapter);

        _listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                displayPDF(studentsList.get(position));
            }
        });

        return v;
    }

    private  void ver(){
        list.clear();
        FileInputStream fileInputStream= null;
        StringBuilder stringBuilder= new StringBuilder();
        try {
            fileInputStream= getActivity().openFileInput(FILE_NAME);
            InputStreamReader inputStreamReader= new InputStreamReader(fileInputStream);
            BufferedReader bufferedReader= new BufferedReader(inputStreamReader);
            String linetexto;
            while ((linetexto= bufferedReader.readLine())!= null){
                stringBuilder.append(linetexto).append("\n");
                list.add(linetexto);
            }
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            if (fileInputStream!=null){
                try {
                    fileInputStream.close();
                }catch (Exception e){
                    e.printStackTrace();
                }
            }
        }
    }


    public void displayPDF(String nombrepd) {
        File file = new File(getPath() + "/" + nombrepd+".pdf");
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
            Toast.makeText(getActivity().getApplicationContext(), "El Archivo no existe" , Toast.LENGTH_LONG).show();
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