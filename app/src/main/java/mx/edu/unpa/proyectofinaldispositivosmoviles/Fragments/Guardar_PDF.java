package mx.edu.unpa.proyectofinaldispositivosmoviles.Fragments;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.provider.Settings;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Toast;

import androidx.core.app.ActivityCompat;
import androidx.fragment.app.Fragment;

import com.lowagie.text.Document;
import com.lowagie.text.DocumentException;
import com.lowagie.text.Paragraph;
import com.lowagie.text.pdf.PdfWriter;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.MalformedInputException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import mx.edu.unpa.proyectofinaldispositivosmoviles.R;
import mx.edu.unpa.proyectofinaldispositivosmoviles.TemplatePDF;

import static android.content.Context.MODE_PRIVATE;
import static android.widget.Toast.LENGTH_SHORT;


/**
 * A simple {@link Fragment} subclass.
 * Use the {@link Guardar_PDF#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Guardar_PDF extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public Guardar_PDF() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment Guardar_PDF.
     */
    // TODO: Rename and change types and number of parameters
    public static Guardar_PDF newInstance(String param1, String param2) {
        Guardar_PDF fragment = new Guardar_PDF();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }
    String DOCUMENT_NAME;
    private static  final int WRITE_STORAGE_CODE=100;
    String DIRECTORY_NAME = "MyPDFs";
    private EditText txtNombre3, txtContenido;
    Button guarda;


    //esto es para crear un txt con lo nombres de los pdf
    private EditText etfile;
    private Button btGuardar;
    private Button btRead;
    private static  final  String FILE_NAME="Lista_pdf.txt";

    ListView _listView;
    List<String> studentsList;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }
//
    //android:clickable="false"
    //android:cursorVisible="false"
    //android:focusable="false"
    //android:focusableInTouchMode="false"
    private  String[] header={"Solicitu De empleo"};
    private  String shortText="hola";
    private String longtext="mmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmm";
    TemplatePDF templatePDF= new TemplatePDF();
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.guardar__p_d, container, false);
        txtNombre3= (EditText) v.findViewById(R.id.txtNombre_Archivo);
        guarda= v.findViewById(R.id.guardar);
        guarda.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String nombre = txtNombre3.getText().toString();//guarda el nombr del archivo en la variable
                String contenido = "hola mundo";//guarda lo que va dentro deñ archivo en la variable
                etfile = txtNombre3;
                File file = new File(getPath() + "/" + txtNombre3.getText().toString() + ".pdf");
                if(file.exists()) {
                    Toast.makeText(getActivity().getApplicationContext(), "El Archivo ya existe" , Toast.LENGTH_LONG).show();
                }else{
                    saveFile();
                    if (nombre != null && !nombre.isEmpty()) {
                        if (contenido != null && !contenido.isEmpty()) {
                            // Comprobar la versión actual de android del dispositivo
                            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                                if (checkPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE)) {
                                    // Permiso aceptado
                                    if (ActivityCompat.checkSelfPermission(getActivity(), Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
                                        return;
                                    }
                                    templatePDF.openDocument(nombre);
                                    //templatePDF.addMetaData("clientes", "ventas", "DANI");
                                    //templatePDF.addTitles("tiend codigo", "clientes", "6/12/2021");
                                    //templatePDF.addParagraph(shortText);
                                    //templatePDF.addParagraph(longtext);
                                    templatePDF.createP("SOLICITU DE EMPLEO", solicitud());
                                    templatePDF.createP2( "DATOS PERSONALES", datos_PERSONALES());
                                    templatePDF.closeDocument();
                                    txtNombre3.setText("");
                                } else {
                                    // Permiso no aceptado / Se pregunta por primera vez
                                    if (!shouldShowRequestPermissionRationale(Manifest.permission.WRITE_EXTERNAL_STORAGE)) {
                                        // No se ha preguntado
                                        requestPermissions(new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, WRITE_STORAGE_CODE);
                                    } else {
                                        // Permiso denegado
                                        Toast.makeText(getActivity(), "Please, enable the request permission", Toast.LENGTH_LONG).show();
                                        Intent intentSettings = new Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
                                        intentSettings.addCategory(Intent.CATEGORY_DEFAULT);
                                        intentSettings.setData(Uri.parse("package:" + getActivity().getPackageName()));
                                        intentSettings.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                                        intentSettings.addFlags(Intent.FLAG_ACTIVITY_NO_HISTORY);
                                        intentSettings.addFlags(Intent.FLAG_ACTIVITY_EXCLUDE_FROM_RECENTS);
                                        startActivity(intentSettings);
                                    }
                                }
                            } else {
                                olderVersions(nombre, contenido);
                            }
                        } else {
                            Toast.makeText(getActivity(), "pdf con contenido vacio", Toast.LENGTH_LONG).show();
                        }
                    } else {
                        Toast.makeText(getActivity(), "Inserta el nombre para el pdf", Toast.LENGTH_LONG).show();
                    }
                }
            }
        });
        return v;
    }

    private ArrayList<String[]>solicitud(){
        ArrayList<String[]>rows=new ArrayList<>();
        rows.add(new String[]{"puesto solicitado","gerente","\n\nNota: La informacion aquí proporcionada sera tratada confidencialmente"});
        rows.add(new String[]{"fecha de Ingreso:","12-12-1222"  , "fecha de entrada:  "," 22-22-2222","Sueldo Mensual Deseado:","$ 128930","Sueldo Mensual Autorizado:","$ 28390"});
        rows.add(new String[]{"foto"});
        return  rows;
    }
    private ArrayList<String[]>datos_PERSONALES(){
        ArrayList<String[]>rows=new ArrayList<>();
        rows.add(new String[]{"Apellido Paterno ","Apellido Materno","Nombre (s) ","Edad","Sexo"});
        rows.add(new String[]{"reyes","garcia","luis daniel ","33","F( )  M(X)"});
        rows.add(new String[]{"Domicilio (calle y número)","Colonia","Código postal"});
        rows.add(new String[]{"DESCONOCIDO","DESCONOCIDA","2321"});
        rows.add(new String[]{"Lugar de Nacimiento","Estado","Ciudad"});
        rows.add(new String[]{"DESCONOCIDO","VERACRUZ","CORDOBA"});
        rows.add(new String[]{"Vive con","Nacionalidad ","Fecha de Nacimiento","Estatura ","Peso"});
        rows.add(new String[]{"Padres(X) Familia() Parientes()Solo()","mexicano","8-11-1998","1.75cm","80"});
        rows.add(new String[]{"Personas que dependen de usted ","Estado Civil"});
        rows.add(new String[]{"Hijos() Padres() Conyugue() Otros()"," Soltero(X) Casado() Otro()"});
        return  rows;
    }

    List<String> list = new ArrayList<String>();
    private void saveFile(){
        ver();
        String nombreS= etfile.getText().toString();
        FileOutputStream fileOutputStream= null;
        try {
            fileOutputStream= getActivity().openFileOutput(FILE_NAME,MODE_PRIVATE);
            list.add(nombreS+"/");
            for (int i = 0; i <list.size() ; i++) {
                fileOutputStream.write(list.get(i).getBytes());
            }
            Toast.makeText(getActivity(),getActivity().getFilesDir()+"/"+FILE_NAME,Toast.LENGTH_LONG).show();
            Log.d("TAG1","FICHERO SALVADO EN LA RUTA: "+getActivity().getFilesDir()+"/"+FILE_NAME);
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            if (fileOutputStream!=null){
                try {
                    fileOutputStream.close();
                }catch (Exception e){
                    e.printStackTrace();
                }
            }
        }
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

    private void olderVersions(String nombre, String contenido){
        if(checkPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE)){
            templatePDF.openDocument(nombre);
            templatePDF.addMetaData("clientes","ventas","DANI");
            templatePDF.addTitles("tiend codigo","clientes","6/12/2021");
            templatePDF.addParagraph(shortText);
            templatePDF.addParagraph(longtext);
            templatePDF.createTable(header,solicitud());
            templatePDF.closeDocument();
        }else{
            Toast.makeText(getActivity(),"PERMISSION_DENIED",Toast.LENGTH_LONG).show();
        }
    }

    private  boolean  checkPermission(String permission){
        int result= getActivity().checkCallingOrSelfPermission(permission);
        return result == PackageManager.PERMISSION_GRANTED;
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