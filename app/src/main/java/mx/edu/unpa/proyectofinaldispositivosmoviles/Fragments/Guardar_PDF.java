package mx.edu.unpa.proyectofinaldispositivosmoviles.Fragments;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.provider.Settings;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.core.app.ActivityCompat;
import androidx.fragment.app.Fragment;

import com.lowagie.text.Document;
import com.lowagie.text.DocumentException;
import com.lowagie.text.Paragraph;
import com.lowagie.text.pdf.PdfWriter;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import mx.edu.unpa.proyectofinaldispositivosmoviles.R;

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
    String DIRECTORY_NAME = "MySolicitud";
    private EditText txtNombre3, txtContenido;
    Button guarda;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.guardar__p_d, container, false);
        txtNombre3= (EditText) v.findViewById(R.id.txtNombre2);
        txtContenido =  (EditText) v.findViewById(R.id.txtContenido2);
        guarda= v.findViewById(R.id.guardar2);
        guarda.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String nombre = txtNombre3.getText().toString();//guarda el nombr del archivo en la variable
                String contenido = txtContenido.getText().toString();//guarda lo que va dentro deñ archivo en la variable
                if(nombre!= null && !nombre.isEmpty()) {
                    if (contenido != null && !contenido.isEmpty()) {
                        // Comprobar la versión actual de android del dispositivo
                        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.M){
                            if(checkPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE)){
                                // Permiso aceptado
                                if(ActivityCompat.checkSelfPermission(getActivity(),Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED){
                                    return;
                                }
                                createPDF(nombre,contenido);
                            }else{
                                // Permiso no aceptado / Se pregunta por primera vez
                                if (!shouldShowRequestPermissionRationale(Manifest.permission.WRITE_EXTERNAL_STORAGE)) {
                                    // No se ha preguntado
                                    requestPermissions(new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, WRITE_STORAGE_CODE);
                                }else{
                                    // Permiso denegado
                                    Toast.makeText(getActivity(),"Please, enable the request permission",Toast.LENGTH_LONG).show();
                                    Intent intentSettings = new Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
                                    intentSettings.addCategory(Intent.CATEGORY_DEFAULT);
                                    intentSettings.setData(Uri.parse("package:" + getActivity().getPackageName()));
                                    intentSettings.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                                    intentSettings.addFlags(Intent.FLAG_ACTIVITY_NO_HISTORY);
                                    intentSettings.addFlags(Intent.FLAG_ACTIVITY_EXCLUDE_FROM_RECENTS);
                                    startActivity(intentSettings);
                                }
                            }
                        }else{
                            olderVersions(nombre,contenido);
                        }
                    } else {
                        Toast.makeText(getActivity(),"pdf con contenido vacio",Toast.LENGTH_LONG).show();
                    }
                }else{
                    Toast.makeText(getActivity(), "Inserta el nombre para el pdf", Toast.LENGTH_LONG).show();
                }
            }
        });
        return v;
    }

    //region Generate PDF file
    public void createPDF(String nombre, String contenido) {
        Document document = new Document();
        try {
            DOCUMENT_NAME = nombre + ".pdf";
            String TEXT_CONTENT = contenido;
            File file = createFile(DOCUMENT_NAME);

            FileOutputStream PDFFile = new FileOutputStream(file.getAbsolutePath());
            PdfWriter writer = PdfWriter.getInstance(document, PDFFile);
            document.open();
            // document.add(new Paragraph( "Jose Domingo juarez hernandez"+"\n\n"));
            document.add(new Paragraph( TEXT_CONTENT + "\n\n"));
            txtNombre3.setText("");//limpia el txt
            txtContenido.setText("");//limpia el txt
            Toast.makeText(getActivity(), "se guardo correctamente", LENGTH_SHORT).show();//confirmacion de guardado
        } catch(DocumentException e) {
            Toast.makeText(getActivity(), "no se pudo guardar el archivo", LENGTH_SHORT).show();//en caso de error
        }catch(IOException e) {
            Toast.makeText(getActivity(), "no se pudo guardar el archivo", LENGTH_SHORT).show();//en caso de error
        }finally {
            document.close();
        }
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


    private void olderVersions(String nombre, String contenido){
        if(checkPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE)){
            createPDF(nombre,contenido);
        }else{
            Toast.makeText(getActivity(),"PERMISSION_DENIED",Toast.LENGTH_LONG).show();
        }
    }

    private  boolean  checkPermission(String permission){
        int result= getActivity().checkCallingOrSelfPermission(permission);
        return result == PackageManager.PERMISSION_GRANTED;
    }
}