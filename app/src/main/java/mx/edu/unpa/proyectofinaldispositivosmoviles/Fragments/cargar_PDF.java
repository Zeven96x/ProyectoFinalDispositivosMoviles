package mx.edu.unpa.proyectofinaldispositivosmoviles.Fragments;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.os.Environment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.io.File;

import mx.edu.unpa.proyectofinaldispositivosmoviles.R;

import static android.widget.Toast.LENGTH_SHORT;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link cargar_PDF#newInstance} factory method to
 * create an instance of this fragment.
 */
public class cargar_PDF extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public cargar_PDF() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment cargar_PDF.
     */
    // TODO: Rename and change types and number of parameters
    public static cargar_PDF newInstance(String param1, String param2) {
        cargar_PDF fragment = new cargar_PDF();
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

    String DIRECTORY_NAME = "MySolicitud";
    private EditText txtNom;
    Button busca;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.busca_pdf, container, false);
        txtNom= (EditText) v.findViewById(R.id.txtNombre);
        busca= (Button) v.findViewById(R.id.busca);
        busca.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    displayPDF();
                }catch (Exception e){
                    Toast.makeText(getActivity(),"error al leer el archivo", LENGTH_SHORT).show();
                }
            }
        });
        return v;
    }

    public void displayPDF() {
        File file = new File(getPath() + "/" + txtNom.getText().toString()+".pdf");
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