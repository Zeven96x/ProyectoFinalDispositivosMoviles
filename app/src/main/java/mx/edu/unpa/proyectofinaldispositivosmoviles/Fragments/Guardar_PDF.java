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
import android.widget.CheckBox;
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
    private static  final int WRITE_STORAGE_CODE=100;
    String DIRECTORY_NAME = "MyPDFs";
    private EditText txtNombre3, puestoSolicitado,diaf,mesf,anof,diafc,mesfc,anofc,sueldoD,sueldoA;

    private EditText numero_telefono,nombreP,apelllidop,aellidom,domicilio,colonia,codigo_p,ciudad,estado,lugarde_n;
    private EditText fechade_n,nacionalidad,peso,edad,estatura;
    CheckBox hombre,mujer,suspadres,sufamilia,susparientes,suotros,dephijos,depconyuge,deppadres,depotros;
    CheckBox casado,solero,esaotro;

    private EditText curp,afore,nss,cartilla_militar,tipoilicencia,rcf;
    CheckBox si_licencia,no_licencia;

    private EditText enfermedadesPA,metasp;
    CheckBox salud_buena,salud_mala,salud_reglar,si_enfermo,no_enfermo;




    Button guarda;


    //esto es para crear un txt con lo nombres de los pdf
    private EditText etfile;
    private static  final  String FILE_NAME="Lista_pdf.txt";


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    TemplatePDF templatePDF= new TemplatePDF();
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.guardar__p_d, container, false);
        txtNombre3= (EditText) v.findViewById(R.id.txtNombre_Archivo);
        puestoSolicitado= (EditText) v.findViewById(R.id.txtPuesto_solicitado);
        diaf=(EditText) v.findViewById(R.id.txtDia);
        mesf=(EditText) v.findViewById(R.id.txtMes);
        anof=(EditText) v.findViewById(R.id.txtaño);
        diafc=(EditText) v.findViewById(R.id.txtDiac);
        mesfc=(EditText) v.findViewById(R.id.txtMesc);
        anofc=(EditText) v.findViewById(R.id.txtañoc);
        sueldoD=(EditText) v.findViewById(R.id.txtSueldoDeseado);
        sueldoA=(EditText) v.findViewById(R.id.txtSueldoAprobado);
        //datos personales
        hombre= v.findViewById(R.id.Hombre);
        mujer= v.findViewById(R.id.Mujer);
        suspadres= v.findViewById(R.id.vivipadres);
        sufamilia= v.findViewById(R.id.vivifamilia);
        susparientes= v.findViewById(R.id.viviparientes);
        suotros= v.findViewById(R.id.vivisolo);
        dephijos= v.findViewById(R.id.depnedeHijos);
        depconyuge= v.findViewById(R.id.depnedecontuge);
        deppadres= v.findViewById(R.id.depnedepadres);
        depotros= v.findViewById(R.id.depnedeotros);
        casado= v.findViewById(R.id.estado_civilcasado);
        solero= v.findViewById(R.id.estado_civilSoltero);
        esaotro= v.findViewById(R.id.estado_civilotro);
        numero_telefono=(EditText) v.findViewById(R.id.txtNumeroTELEFENO);
        nombreP=(EditText) v.findViewById(R.id.txtNombrePersona);
        apelllidop=(EditText) v.findViewById(R.id.txtapellidoP);
        aellidom=(EditText) v.findViewById(R.id.txtApellidoM);
        domicilio=(EditText) v.findViewById(R.id.txtDomicilio);
        colonia=(EditText) v.findViewById(R.id.txtColonia);
        codigo_p=(EditText) v.findViewById(R.id.txtCodigoPostal);
        ciudad=(EditText) v.findViewById(R.id.txtCiudad);
        estado=(EditText) v.findViewById(R.id.txtEstado);
        lugarde_n=(EditText) v.findViewById(R.id.txtLugar_de_nacimineto);
        fechade_n=(EditText) v.findViewById(R.id.txtFecha_de_N);
        nacionalidad=(EditText) v.findViewById(R.id.txtNacionalidad);
        peso=(EditText) v.findViewById(R.id.txtPeso);
        edad=(EditText) v.findViewById(R.id.txtedad);
        estatura=(EditText) v.findViewById(R.id.txtEstatura);
        //DOCUMENTACIÓN
        curp=(EditText) v.findViewById(R.id.txtcurp);
        afore=(EditText) v.findViewById(R.id.txtAfore);
        nss=(EditText) v.findViewById(R.id.txtNss);
        cartilla_militar=(EditText) v.findViewById(R.id.txtCartillaMilitar);
        tipoilicencia=(EditText) v.findViewById(R.id.txtTipoLicencia);
        rcf=(EditText) v.findViewById(R.id.txtRFC);
        si_licencia=(CheckBox) v.findViewById(R.id.si_licencia);
        no_licencia=(CheckBox) v.findViewById(R.id.no_licencia);
        //ESTADO DE SALUD Y HÁBITOS PERSONALES
        enfermedadesPA=(EditText) v.findViewById(R.id.txtTipo_enfermedad);
        metasp=(EditText) v.findViewById(R.id.txtmeta);
        salud_buena=(CheckBox) v.findViewById(R.id.saludbuena);
        salud_mala=(CheckBox) v.findViewById(R.id.saludmala);
        salud_reglar=(CheckBox) v.findViewById(R.id.saludregular);
        si_enfermo=(CheckBox) v.findViewById(R.id.sienfe);
        no_enfermo=(CheckBox) v.findViewById(R.id.noenfermo);


            //button
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
                                    templatePDF.createP("SOLICITUD DE EMPLEO", solicitud(puestoSolicitado.getText().toString(),diaf.getText().toString(),mesf.getText().toString(),anof.getText().toString(),diafc.getText().toString(),mesfc.getText().toString(),anofc.getText().toString(),sueldoD.getText().toString(),sueldoA.getText().toString()));
                                    String sexo="0";
                                    if(hombre.isChecked()){
                                        sexo="1";
                                    }else{
                                        if(mujer.isChecked()){
                                            sexo="2";
                                        }
                                    }
                                    String vive_con="0";
                                    if (suspadres.isChecked()){
                                        vive_con="1";
                                    }
                                    if (sufamilia.isChecked()){
                                        vive_con="2";
                                    }
                                    if (susparientes.isChecked()){
                                        vive_con="3";
                                    }
                                    if (suotros.isChecked()){
                                        vive_con="4";
                                    }

                                    String personas_dependen="0";
                                    if (dephijos.isChecked()){
                                        personas_dependen="1";
                                    }
                                    if (deppadres.isChecked()){
                                        personas_dependen="2";
                                    }
                                    if (depconyuge.isChecked()){
                                        personas_dependen="3";
                                    }
                                    if (depotros.isChecked()){
                                        personas_dependen="4";
                                    }

                                    String estado_Civil="0";
                                    if (solero.isChecked()){
                                        estado_Civil="1";
                                    }
                                    if (casado.isChecked()){
                                        estado_Civil="2";
                                    }
                                    if (esaotro.isChecked()){
                                        estado_Civil="3";
                                    }
                                    templatePDF.createP2( "DATOS PERSONALES", datos_PERSONALES (apelllidop.getText().toString(),apelllidop.getText().toString(),nombreP.getText().toString(),edad.getText().toString(),sexo,domicilio.getText().toString()
                                            ,colonia.getText().toString(),codigo_p.getText().toString(),lugarde_n.getText().toString(),estado.getText().toString(),ciudad.getText().toString(),vive_con,
                                            nacionalidad.getText().toString() ,fechade_n.getText().toString(),estatura.getText().toString(),peso.getText().toString(),personas_dependen, estado_Civil,numero_telefono.getText().toString() ));

                                    String licenciamanejo="0";
                                    if(si_licencia.isChecked()){
                                        licenciamanejo="1";
                                    }else{
                                        if(no_licencia.isChecked()){
                                            licenciamanejo="2";
                                        }
                                    }
                                    CheckBox si_licencia,no_licencia;
                                    templatePDF.createP3( "DOCUMENTACIÓN", documentacion(curp.getText().toString(),afore.getText().toString(),nss.getText().toString(),
                                            cartilla_militar.getText().toString(),licenciamanejo,tipoilicencia.getText().toString(),rcf.getText().toString()));


                                    //(String estadoactual,String metaVida,String algunaEnfermedad,String cualEn)
                                    String estadoactual="0";
                                    if (salud_buena.isChecked()){
                                        estadoactual="1";
                                    }
                                    if (salud_mala.isChecked()){
                                        estadoactual="2";
                                    }
                                    if (salud_reglar.isChecked()){
                                        estadoactual="3";
                                    }

                                    String algunaEnfermedad="0";
                                    if(si_enfermo.isChecked()){
                                        algunaEnfermedad="1";
                                    }else{
                                        if(no_enfermo.isChecked()){
                                            algunaEnfermedad="2";
                                        }
                                    }
                                    CheckBox si_enfermo,no_enfermo;
                                    templatePDF.createP4( "ESTADO DE SALUD Y HÁBITOS PERSONALES", estadoSalud( estadoactual,metasp.getText().toString(),algunaEnfermedad,enfermedadesPA.getText().toString()));
                                    templatePDF.createP5( "ESCOLARIDAD", escolaridad());
                                    templatePDF.createP6( "CONOCIMIENTOS GENERALES", conocimientosGenerales());
                                    templatePDF.createP7( "EMPLEO ACTUAL Y ANTERIORES", empleos());
                                    templatePDF.createP8( "REFERENCIAS PERSONALES", referencias());
                                    templatePDF.createP9( "DATOS GENERALES", datosGenerales());
                                    templatePDF.createP10( "COMENTARIOS DEL ENTREVISTADOR", comentariosEntrevistador());
                                    templatePDF.closeDocument();
                                    txtNombre3.setText("");puestoSolicitado.setText("");diaf.setText("");mesf.setText("");anof.setText("");diafc.setText("");mesfc.setText("");anofc.setText("");sueldoD.setText("");sueldoA.setText("");
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


    private ArrayList<String[]>solicitud(String gerente,String a,String b,String c,String d,String e,String f,String suel,String sueldoA){
        ArrayList<String[]>rows=new ArrayList<>();
        rows.add(new String[]{"puesto solicitado",gerente,"\n\nNota: La informacion aquí proporcionada sera tratada confidencialmente"});
        rows.add(new String[]{"fecha de Ingreso:",a+"-"+b+"-"+c  , "fecha de entrada:  ",d+"-"+e+"-"+f ,"Sueldo Mensual Deseado:","$ "+suel,"Sueldo Mensual Autorizado:","$ "+sueldoA});
        rows.add(new String[]{"foto"});
        return  rows;
    }

    private ArrayList<String[]>datos_PERSONALES(String Apellido_p,String Apellido_m,String nombreE,String edad,String sexo,String domicilio
                                               ,String colonia,String codigop,String lugar_n,String estado,String ciudad,String vive_con,
                                                String nacionalidad ,String fecha_n,String estatura ,String peso,String personas_dependen,
                                                String estado_Civil,String numero_telefono ){
        if(sexo.equals("1")){
            sexo="F( )  M(X)";
        }else{
            sexo="F(X)  M( )";
        }
        if (vive_con.equals("1")){
            vive_con="Sus padres(X) Familia( ) Parienetes( ) Solo( )";
        }
        if (vive_con.equals("2")){
            vive_con="Sus padres( ) Familia(X) Parienetes( ) Solo( )";
        }
        if (vive_con.equals("3")){
            vive_con="Sus padres( ) Familia( ) Parienetes(X) Solo( )";
        }
        if (vive_con.equals("4")){
            vive_con="Sus padres( ) Familia( ) Parienetes( ) Solo(X)";
        }
        if (personas_dependen.equals("1")){
            personas_dependen="Hijos(X) Padres( ) Conyugue( ) Otros( )";
        }
        if (personas_dependen.equals("2")){
            personas_dependen="Hijos( ) Padres(X) Conyugue( ) Otros( )";
        }
        if (personas_dependen.equals("3")){
            personas_dependen="Hijos( ) Padres( ) Conyugue(X) Otros( )";
        }
        if (personas_dependen.equals("4")){
            personas_dependen="Hijos( ) Padres( ) Conyugue( ) Otros(X)";
        }

        if (estado_Civil.equals("1")){
            estado_Civil=" Soltero(X) Casado( ) Otro( )";
        }
        if (estado_Civil.equals("2")){
            estado_Civil=" Soltero( ) Casado(X) Otro( )";
        }
        if (estado_Civil.equals("3")){
            estado_Civil=" Soltero( ) Casado( ) Otro(X)";
        }

        ArrayList<String[]>rows=new ArrayList<>();
        rows.add(new String[]{"Apellido Paterno ","Apellido Materno","Nombre (s) ","Edad","Sexo"});
        rows.add(new String[]{Apellido_p,Apellido_m,nombreE,edad,sexo});
        rows.add(new String[]{"Domicilio (calle y número)","Colonia","Código postal"});
        rows.add(new String[]{domicilio,colonia,codigop});
        rows.add(new String[]{"Lugar de Nacimiento","Estado","Ciudad"});
        rows.add(new String[]{lugar_n,estado,ciudad});
        rows.add(new String[]{"Vive con","Nacionalidad ","Fecha de Nacimiento","Estatura ","Peso"});
        rows.add(new String[]{vive_con,nacionalidad,fecha_n,estatura,peso});
        rows.add(new String[]{"Personas que dependen de usted ","Estado Civil"});
        rows.add(new String[]{personas_dependen,estado_Civil});
        rows.add(new String[]{"Numero de telefono"});
        rows.add(new String[]{numero_telefono});

        return  rows;
    }


    private ArrayList<String[]>documentacion(String curp,String AFORE,String Numero_seguridad_social,String cartill_militar,String licenciamanejo,String Tipo,String RFC){
        if(licenciamanejo.equals("1")){
            licenciamanejo="SI(X)  NO( )";
        }else{
            licenciamanejo="SI( )  NO(X)";
        }
        ArrayList<String[]>rows=new ArrayList<>();
        rows.add(new String[]{"clave única de registro de población(CURP}","AFORE"});
        rows.add(new String[]{curp,AFORE});
        rows.add(new String[]{"Numero de seguridad social","Numero de cartilla militar"});
        rows.add(new String[]{Numero_seguridad_social,cartill_militar});
        rows.add(new String[]{"licencia de manejo","Tipo","RFC"});
        rows.add(new String[]{licenciamanejo,Tipo,RFC});
        return  rows;
    }
    private ArrayList<String[]>estadoSalud(String estadoactual,String metaVida,String algunaEnfermedad,String cualEn){
        if (estadoactual.equals("1")){
            estadoactual="Bueno (X)  Malo( ) Regular( )";
        }
        if (estadoactual.equals("2")){
            estadoactual="Bueno ( )  Malo(X) Regular( )";
        }
        if (estadoactual.equals("3")){
            estadoactual="Bueno ( )  Malo( ) Regular(X)";
        }

        if (algunaEnfermedad.equals("1")){
            algunaEnfermedad="SI(X)  NO( )";
        }
        if (algunaEnfermedad.equals("2")){
            algunaEnfermedad="SI( )  NO(X)";
        }
        ArrayList<String[]>rows=new ArrayList<>();
        rows.add(new String[]{"¿Cómo consideras tu estado de salud actual","¿Cúal es tu meta en la vida?"});
        rows.add(new String[]{estadoactual,metaVida});
        rows.add(new String[]{"¿Padece de alguna enfermedad cronica?","Explique cual(es)"});
        rows.add(new String[]{algunaEnfermedad,cualEn});

        return  rows;
    }
    private ArrayList<String[]>escolaridad(){
        ArrayList<String[]>rows=new ArrayList<>();
        rows.add(new String[]{"Estudios alcanzado","Primaria(  ) Secundaria(   )  Preparatoria(  )  Superior( X )"});
        rows.add(new String[]{"Nombre de la institución","UNPA"});
        rows.add(new String[]{"Nombre del título obtenido","Inenieria en computación"});
        rows.add(new String[]{"Estudios que efectua en la actualidad"," Lincenciatura "});

        return  rows;
    }
    private ArrayList<String[]>conocimientosGenerales(){
        ArrayList<String[]>rows=new ArrayList<>();
        rows.add(new String[]{"¿Qué idioma hablas?(50%,75%,100%)","Funciones de oficina que domina"});
        rows.add(new String[]{"ingles 75%","ninguno"});
        rows.add(new String[]{"Maquina de oficina o taller que sepa manejar","Software que domina"});
        rows.add(new String[]{"maquina de escribir"," la paqueteria de office"});
        rows.add(new String[]{"Otros trabajos o funciones que domina"});
        rows.add(new String[]{"Por el momento ninguna"});

        return  rows;
    }
    private ArrayList<String[]>empleos(){
        ArrayList<String[]>rows=new ArrayList<>();
        rows.add(new String[]{" ","Empleo anterior","Empleo anterior","Empleo anterior"});
        rows.add(new String[]{"Nombre de la empresa","NASA","TESLA","Ninguno"});
        rows.add(new String[]{"Del año","2018","2018"," "});
        rows.add(new String[]{"Al año","2020","2016"," "});

        return  rows;
    }
    private ArrayList<String[]>referencias(){
        ArrayList<String[]>rows=new ArrayList<>();
        rows.add(new String[]{"Nombre","Telefono","Domicilio","Ocupación","Tiempo de conocerlo"});
        rows.add(new String[]{"suriel quevedo ortiz","878887","san bartolo","EStudiante","4 años"});
        rows.add(new String[]{"Jahir corral","2656545","su casa","estudiante","3 años"});
        rows.add(new String[]{"José Domingo","45656456","UNPA","Profesor","4 años"});

        return  rows;
    }
    private ArrayList<String[]>datosGenerales(){
        ArrayList<String[]>rows=new ArrayList<>();
        rows.add(new String[]{"¿Cómo supo del empleo?"});
        rows.add(new String[]{"Anuncio( X )  Internet( )  Otro( )"," lo vi en la tele"});
        rows.add(new String[]{"¿Tiene parientes trabajando en esta empresa?"});
        rows.add(new String[]{"No( X )  Si( ) ","  "});
        rows.add(new String[]{"¿Has estado afianzado?"});
        rows.add(new String[]{"No( X )  Si( )"," "});
        rows.add(new String[]{"¿has estado afiliado a algun sindicato?"});
        rows.add(new String[]{"No( )  Si(X)"," al CTM"});
        rows.add(new String[]{"¿Tienes seguro de vida?"});
        rows.add(new String[]{"No( X )  Si( )"," "});
        rows.add(new String[]{"Disposición de viajar"});
        rows.add(new String[]{"No( X )  Si( )"," porque tengo presión alta"});
        rows.add(new String[]{"¿Disponibilidad a cambiar su residencia"});
        rows.add(new String[]{"No( )  Si( X )"," no tengo ningun problema"});
        rows.add(new String[]{"¿Fechha en que podria presentarse a trabajar?"});
        rows.add(new String[]{"la semana siguiente"});
        rows.add(new String[]{"Firma Solicitante"," "});


        return  rows;
    }
    private ArrayList<String[]>comentariosEntrevistador(){
        ArrayList<String[]>rows=new ArrayList<>();
        rows.add(new String[]{"pues he de decir que no creo que valgas la pena en nuestra empresa"});
        rows.add(new String[]{"Firma del entrevistador"});
        rows.add(new String[]{"   "});


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
            //createpdf(nombre);
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