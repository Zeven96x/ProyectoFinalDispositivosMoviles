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
public class Guardar_PDF extends Fragment implements View.OnClickListener{

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
    private EditText fechade_n,nacionalidad,peso,edad,estatura,institucion,titulo,estudioActual;
    private EditText idiomas,funcionesOficina,maquinaOficina,software,otrosTrabajos;
    private EditText anoE1,anoE2,anoE3,anoS1,anoS2,anoS3,empresa1,empresa2,empresa3;
    private CheckBox hombre,mujer,suspadres,sufamilia,susparientes,suotros,dephijos,depconyuge,deppadres,depotros;
    private CheckBox casado,solero,esaotro,primaria,secundaria,preparatoria,superior;

    private EditText curp,afore,nss,cartilla_militar,tipoilicencia,rcf;
    private CheckBox si_licencia,no_licencia;

    private EditText enfermedadesPA,metasp;
    private CheckBox salud_buena,salud_mala,salud_reglar,si_enfermo,no_enfermo;
    private EditText nRefe1,nRefe2,nRefe3,domi1,domi2,domi3,telef1,telef2,telef3,tc1,tc2,tc3,ocupa1,ocupa2,ocupa3;
    private EditText otroMedio,pariente,afianza,sindicato,seguro,razonesV,razonesC,fechaPresentarse;
    private CheckBox no1,no2,no3,no4,no5,no6,otrosMedios,anuncio,si1,si2,si3,si4,si5,si6;




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
        hombre.setOnClickListener(this);
        mujer= v.findViewById(R.id.Mujer);
        mujer.setOnClickListener(this);
        suspadres= v.findViewById(R.id.vivipadres);
        suspadres.setOnClickListener(this);
        sufamilia= v.findViewById(R.id.vivifamilia);
        sufamilia.setOnClickListener(this);
        susparientes= v.findViewById(R.id.viviparientes);
        susparientes.setOnClickListener(this);
        suotros= v.findViewById(R.id.vivisolo);
        suotros.setOnClickListener(this);
        dephijos= v.findViewById(R.id.depnedeHijos);
        dephijos.setOnClickListener(this);
        depconyuge= v.findViewById(R.id.depnedecontuge);
        depconyuge.setOnClickListener(this);
        deppadres= v.findViewById(R.id.depnedepadres);
        deppadres.setOnClickListener(this);
        depotros= v.findViewById(R.id.depnedeotros);
        depotros.setOnClickListener(this);
        casado= v.findViewById(R.id.estado_civilcasado);
        casado.setOnClickListener(this);
        solero= v.findViewById(R.id.estado_civilSoltero);
        solero.setOnClickListener(this);
        esaotro= v.findViewById(R.id.estado_civilotro);
        esaotro.setOnClickListener(this);
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
        si_licencia.setOnClickListener(this);
        no_licencia=(CheckBox) v.findViewById(R.id.no_licencia);
        no_licencia.setOnClickListener(this);
        //ESTADO DE SALUD Y HÁBITOS PERSONALES
        enfermedadesPA=(EditText) v.findViewById(R.id.txtTipo_enfermedad);
        metasp=(EditText) v.findViewById(R.id.txtmeta);
        salud_buena=(CheckBox) v.findViewById(R.id.saludbuena);
        salud_buena.setOnClickListener(this);
        salud_mala=(CheckBox) v.findViewById(R.id.saludmala);
        salud_mala.setOnClickListener(this);
        salud_reglar=(CheckBox) v.findViewById(R.id.saludregular);
        salud_reglar.setOnClickListener(this);
        si_enfermo=(CheckBox) v.findViewById(R.id.sienfe);
        si_enfermo.setOnClickListener(this);
        no_enfermo=(CheckBox) v.findViewById(R.id.noenfermo);
        no_enfermo.setOnClickListener(this);
        //ESCOLARIDAD
        primaria=(CheckBox) v.findViewById(R.id.primaria);
        primaria.setOnClickListener(this);
        secundaria=(CheckBox) v.findViewById(R.id.secundaria);
        secundaria.setOnClickListener(this);
        preparatoria=(CheckBox) v.findViewById(R.id.preparatoria);
        preparatoria.setOnClickListener(this);
        superior=(CheckBox) v.findViewById(R.id.superior);
        superior.setOnClickListener(this);
        institucion=(EditText) v.findViewById((R.id.txtNombre_Institucion));
        titulo=(EditText) v.findViewById((R.id.txtNombre_TituloEducativo));
        estudioActual=(EditText) v.findViewById((R.id.txtEstudio_Actual));
        //CONOCIMIENTOS GENERALES
        idiomas=v.findViewById((R.id.txtIdiomas));
        funcionesOficina=v.findViewById((R.id.txtFunciones_oficinas));
        maquinaOficina=v.findViewById((R.id.txtMaquinas_manejadas));
        software=v.findViewById((R.id.txtSoftware_conocido));
        otrosTrabajos=v.findViewById(R.id.txtOtros_Trabajos);
        //EMPLEO ACTUAL Y ANTERIORES
        anoE1=v.findViewById(R.id.txtAnoEntrada1);
        anoE2=v.findViewById(R.id.txtAnoEntrada2);
        anoE3=v.findViewById(R.id.txtAnoEntrada3);
        anoS1=v.findViewById(R.id.txtSAnoSalia1);
        anoS2=v.findViewById(R.id.txtSAnoSalia2);
        anoS3=v.findViewById(R.id.txtSAnoSalia3);
        empresa1=v.findViewById(R.id.txtEmpresa1);
        empresa2=v.findViewById(R.id.txtEmpresa2);
        empresa3=v.findViewById(R.id.txtEmpresa3);

        //REFERENCIAS PERSONALES
        nRefe1=v.findViewById(R.id.txtNombreReferencia1);
        nRefe2=v.findViewById(R.id.txtNombreReferencia2);
        nRefe3=v.findViewById(R.id.txtNombreReferencia3);
        domi1=v.findViewById(R.id.txtDomicilioR1);
        domi2=v.findViewById(R.id.txtDomicilioR2);
        domi3=v.findViewById(R.id.txtDomicilioR3);
        telef1=v.findViewById(R.id.txtTelefono1);
        telef2=v.findViewById(R.id.txtTelefono2);
        telef3=v.findViewById(R.id.txtTelefono3);
        tc1=v.findViewById(R.id.txtTiempoConocerR1);
        tc2=v.findViewById(R.id.txtTiempoConocerR2);
        tc3=v.findViewById(R.id.txtTiempoConocerR3);
        ocupa1=v.findViewById(R.id.txtOcupacionR1);
        ocupa2=v.findViewById(R.id.txtOcupacionR2);
        ocupa3=v.findViewById(R.id.txtOcupacionR3);
        //DATOS GENERALES
        // private EditText otroMedio,pariente,afianza,sindicato,seguro,razonesV,razonesC,fechaPresentarse;
        otroMedio=v.findViewById(R.id.txtOtro_medio);
        pariente=v.findViewById(R.id.txtNombre_Pariente);
        afianza=v.findViewById(R.id.txtNombre_Afianza);
        sindicato=v.findViewById(R.id.txtNombre_Sindicato);
        seguro=v.findViewById(R.id.txtNombre_Seguro);
        razonesV=v.findViewById(R.id.txtRazones);
        razonesC=v.findViewById(R.id.txtRazones_nocambiar);
        fechaPresentarse=v.findViewById(R.id.txtFecha_presentar_trabajo);
        //    private CheckBox no1,no2,no3,no4,no5,no6,otrosMedios,anuncio,si1,si2,si3,si4,si5,si6;
        no1=(CheckBox) v.findViewById(R.id.parientes_no);
        no1.setOnClickListener(this);
        no2=(CheckBox) v.findViewById(R.id.afianza_no);
        no2.setOnClickListener(this);
        no3=(CheckBox) v.findViewById(R.id.sindicatoNo);
        no3.setOnClickListener(this);
        no4=(CheckBox) v.findViewById(R.id.seguroV_no);
        no4.setOnClickListener(this);
        no5=(CheckBox) v.findViewById(R.id.viajar_no);
        no5.setOnClickListener(this);
        no6=(CheckBox) v.findViewById(R.id.cambiarResi_no);
        no6.setOnClickListener(this);
        si1=(CheckBox) v.findViewById(R.id.Parientes_si);
        si1.setOnClickListener(this);
        si2=(CheckBox) v.findViewById(R.id.afianza_si);
        si2.setOnClickListener(this);
        si3=(CheckBox) v.findViewById(R.id.sindicatoSi);
        si3.setOnClickListener(this);
        si4=(CheckBox) v.findViewById(R.id.seguroV_si);
        si4.setOnClickListener(this);
        si5=(CheckBox) v.findViewById(R.id.viajar_si);
        si5.setOnClickListener(this);
        si6=(CheckBox) v.findViewById(R.id.cambiarResi_si);
        si6.setOnClickListener(this);
        otrosMedios=v.findViewById(R.id.oMedio);
        otrosMedios.setOnClickListener(this);
        anuncio=v.findViewById(R.id.anuncio);
        anuncio.setOnClickListener(this);

        //button
        guarda= v.findViewById(R.id.guardar);
        guarda.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String nombre = txtNombre3.getText().toString();//guarda el nombr del archivo en la variable
                etfile = txtNombre3;
                File file = new File(getPath() + "/" + txtNombre3.getText().toString() + ".pdf");
                if(file.exists()) {
                    Toast.makeText(getActivity().getApplicationContext(), "El Archivo ya existe" , Toast.LENGTH_LONG).show();
                }else{
                    saveFile();
                    if (nombre != null && !nombre.isEmpty()) {
                            // Comprobar la versión actual de android del dispositivo
                            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                                if (checkPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE)) {
                                    // Permiso aceptado
                                    if (ActivityCompat.checkSelfPermission(getActivity(), Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
                                        return;
                                    }
                                    crearPdf(nombre);
                                    limpiarVariables();
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
                                olderVersions(nombre);
                            }
                    } else {
                        Toast.makeText(getActivity(), "Inserta el nombre para el pdf", Toast.LENGTH_LONG).show();
                    }
                }
            }
        });
        return v;
    }

    @Override
    public void onClick(View v) {
        if (hombre.isChecked()&&mujer.isChecked()){
            hombre.setChecked(false);
            mujer.setChecked(false);
            Toast.makeText(getActivity(),"Solo seleciona uno",Toast.LENGTH_LONG).show();
        }
        if (suspadres.isChecked()&&sufamilia.isChecked()||suspadres.isChecked()&&susparientes.isChecked()||suspadres.isChecked()&&suotros.isChecked()||susparientes.isChecked()&&suotros.isChecked()||susparientes.isChecked()&&sufamilia.isChecked()||suotros.isChecked()&&sufamilia.isChecked()){
            suspadres.setChecked(false);
            sufamilia.setChecked(false);
            susparientes.setChecked(false);
            suotros.setChecked(false);
            Toast.makeText(getActivity(),"Solo seleciona uno",Toast.LENGTH_LONG).show();
        }
        if (dephijos.isChecked()&&depconyuge.isChecked()||dephijos.isChecked()&&deppadres.isChecked()||dephijos.isChecked()&&depotros.isChecked()||deppadres.isChecked()&&depotros.isChecked()||deppadres.isChecked()&&depconyuge.isChecked()||depotros.isChecked()&&depconyuge.isChecked()){
            dephijos.setChecked(false);
            depconyuge.setChecked(false);
            deppadres.setChecked(false);
            depotros.setChecked(false);
            Toast.makeText(getActivity(),"Solo seleciona uno",Toast.LENGTH_LONG).show();
        }
        if (casado.isChecked()&&solero.isChecked()||casado.isChecked()&&esaotro.isChecked()||solero.isChecked()&&esaotro.isChecked()){
            casado.setChecked(false);
            solero.setChecked(false);
            esaotro.setChecked(false);
            Toast.makeText(getActivity(),"Solo seleciona uno",Toast.LENGTH_LONG).show();
        }
        if (si_licencia.isChecked()&&no_licencia.isChecked()){
            si_licencia.setChecked(false);
            no_licencia.setChecked(false);
            Toast.makeText(getActivity(),"Solo seleciona uno",Toast.LENGTH_LONG).show();
        }
        if (salud_buena.isChecked()&&salud_mala.isChecked()||salud_buena.isChecked()&&salud_reglar.isChecked()||salud_mala.isChecked()&&salud_reglar.isChecked()){
            salud_buena.setChecked(false);
            salud_mala.setChecked(false);
            salud_reglar.setChecked(false);
            Toast.makeText(getActivity(),"Solo seleciona uno",Toast.LENGTH_LONG).show();
        }
        if (si_enfermo.isChecked()&&no_enfermo.isChecked()){
            si_enfermo.setChecked(false);
            no_enfermo.setChecked(false);
            Toast.makeText(getActivity(),"Solo seleciona uno",Toast.LENGTH_LONG).show();
        }
        if (primaria.isChecked()&&secundaria.isChecked()||primaria.isChecked()&&preparatoria.isChecked()||primaria.isChecked()&&superior.isChecked()||preparatoria.isChecked()&&superior.isChecked()||preparatoria.isChecked()&&secundaria.isChecked()||superior.isChecked()&&secundaria.isChecked()){
            primaria.setChecked(false);
            secundaria.setChecked(false);
            preparatoria.setChecked(false);
            superior.setChecked(false);
            Toast.makeText(getActivity(),"Solo seleciona uno",Toast.LENGTH_LONG).show();
        }
        if (no1.isChecked()&&si1.isChecked()){
            no1.setChecked(false);
            si1.setChecked(false);
            Toast.makeText(getActivity(),"Solo seleciona uno",Toast.LENGTH_LONG).show();
        }
        if (no2.isChecked()&&si2.isChecked()){
            no2.setChecked(false);
            si2.setChecked(false);
            Toast.makeText(getActivity(),"Solo seleciona uno",Toast.LENGTH_LONG).show();
        }
        if (no3.isChecked()&&si3.isChecked()){
            no3.setChecked(false);
            si3.setChecked(false);
            Toast.makeText(getActivity(),"Solo seleciona uno",Toast.LENGTH_LONG).show();
        }
        if (no4.isChecked()&&si4.isChecked()){
            no4.setChecked(false);
            si4.setChecked(false);
            Toast.makeText(getActivity(),"Solo seleciona uno",Toast.LENGTH_LONG).show();
        }
        if (no5.isChecked()&&si5.isChecked()){
            no5.setChecked(false);
            si5.setChecked(false);
            Toast.makeText(getActivity(),"Solo seleciona uno",Toast.LENGTH_LONG).show();
        }
        if (no6.isChecked()&&si6.isChecked()){
            no6.setChecked(false);
            si6.setChecked(false);
            Toast.makeText(getActivity(),"Solo seleciona uno",Toast.LENGTH_LONG).show();
        }
        if (otrosMedios.isChecked()&&anuncio.isChecked()){
            otrosMedios.setChecked(false);
            anuncio.setChecked(false);
            Toast.makeText(getActivity(),"Solo seleciona uno",Toast.LENGTH_LONG).show();
        }
    }


    public  void  limpiarVariables(){
        txtNombre3.setText("");
        puestoSolicitado.setText("");
        diaf.setText("");
        mesf.setText("");
        anof.setText("");
        diafc.setText("");
        mesfc.setText("");
        anofc.setText("");
        sueldoD.setText("");
        sueldoA.setText("");
        //datos personales
        hombre.setChecked(false);
        mujer.setChecked(false);
        suspadres.setChecked(false);
        sufamilia.setChecked(false);
        susparientes.setChecked(false);
        suotros.setChecked(false);
        dephijos.setChecked(false);
        depconyuge.setChecked(false);
        deppadres.setChecked(false);
        depotros.setChecked(false);
        casado.setChecked(false);
        solero.setChecked(false);
        esaotro.setChecked(false);
        numero_telefono.setText("");
        nombreP.setText("");
        apelllidop.setText("");
        aellidom.setText("");
        domicilio.setText("");
        colonia.setText("");
        codigo_p.setText("");
        ciudad.setText("");
        estado.setText("");
        lugarde_n.setText("");
        fechade_n.setText("");
        nacionalidad.setText("");
        peso.setText("");
        edad.setText("");
        estatura.setText("");
        //DOCUMENTACIÓN
        curp.setText("");
        afore.setText("");
        nss.setText("");
        cartilla_militar.setText("");
        tipoilicencia.setText("");
        rcf.setText("");
        si_licencia.setChecked(false);
        no_licencia.setChecked(false);
        //ESTADO DE SALUD Y HÁBITOS PERSONALES
        enfermedadesPA.setText("");
        metasp.setText("");
        salud_buena.setChecked(false);
        salud_mala.setChecked(false);
        salud_reglar.setChecked(false);
        si_enfermo.setChecked(false);
        no_enfermo.setChecked(false);
        //ESCOLARIDAD
        primaria.setChecked(false);
        secundaria.setChecked(false);
        preparatoria.setChecked(false);
        superior.setChecked(false);
        institucion.setText("");
        titulo.setText("");
        estudioActual.setText("");
        //CONOCIMIENTOS GENERALES
        idiomas.setText("");
        funcionesOficina.setText("");
        maquinaOficina.setText("");
        software.setText("");
        otrosTrabajos.setText("");
        //EMPLEO ACTUAL Y ANTERIORES
        anoE1.setText("");
        anoE2.setText("");
        anoE3.setText("");
        anoS1.setText("");
        anoS2.setText("");
        anoS3.setText("");
        empresa1.setText("");
        empresa2.setText("");
        empresa3.setText("");
        //REFERENCIAS PERSONALES
        nRefe1.setText("");
        nRefe2.setText("");
        nRefe3.setText("");
        domi1.setText("");
        domi2.setText("");
        domi3.setText("");
        telef1.setText("");
        telef2.setText("");
        telef3.setText("");
        tc1.setText("");
        tc2.setText("");
        tc3.setText("");
        ocupa1.setText("");
        ocupa2.setText("");
        ocupa3.setText("");
        //DATOS GENERALES
        otroMedio.setText("");
        pariente.setText("");
        afianza.setText("");
        sindicato.setText("");
        seguro.setText("");
        razonesV.setText("");
        razonesC.setText("");
        fechaPresentarse.setText("");
        //    private CheckBox no1,no2,no3,no4,no5,no6,otrosMedios,anuncio,si1,si2,si3,si4,si5,si6;
        no1.setChecked(false);
        no2.setChecked(false);
        no3.setChecked(false);
        no4.setChecked(false);
        no5.setChecked(false);
        no6.setChecked(false);
        si1.setChecked(false);
        si2.setChecked(false);
        si3.setChecked(false);
        si4.setChecked(false);
        si5.setChecked(false);
        si6.setChecked(false);
        otrosMedios.setChecked(false);
        anuncio.setChecked(false);
    }
    public  void  crearPdf(String nombre){
        templatePDF.openDocument(nombre);
        templatePDF.createP("SOLICITUD DE EMPLEO", solicitud(puestoSolicitado.getText().toString(),diaf.getText().toString(),mesf.getText().toString(),anof.getText().toString(),diafc.getText().toString(),mesfc.getText().toString(),anofc.getText().toString(),sueldoD.getText().toString(),sueldoA.getText().toString()));
        templatePDF.createP2( "DATOS PERSONALES", datos_PERSONALES ());
        templatePDF.createP3( "DOCUMENTACIÓN", documentacion());
        templatePDF.createP4( "ESTADO DE SALUD Y HÁBITOS PERSONALES", estadoSalud());
        templatePDF.createP5( "ESCOLARIDAD", escolaridad());
        templatePDF.createP6( "CONOCIMIENTOS GENERALES", conocimientosGenerales());
        templatePDF.createP7( "EMPLEO ACTUAL Y ANTERIORES", empleos());
        templatePDF.createP8( "REFERENCIAS PERSONALES", referencias());
        templatePDF.createP9( "DATOS GENERALES", datosGenerales());
        templatePDF.createP10( "COMENTARIOS DEL ENTREVISTADOR", comentariosEntrevistador());
        templatePDF.closeDocument();
    }


    private ArrayList<String[]>solicitud(String gerente,String a,String b,String c,String d,String e,String f,String suel,String sueldoA){
        ArrayList<String[]>rows=new ArrayList<>();
        rows.add(new String[]{"Puesto solicitado",gerente,"\n\nNota: La informacion aquí proporcionada sera tratada confidencialmente"});
        rows.add(new String[]{"Fecha de Ingreso:",a+"-"+b+"-"+c  , "Fecha de entrada:  ",d+"-"+e+"-"+f ,"Sueldo Mensual Deseado:","$ "+suel,"Sueldo Mensual Autorizado:","$ "+sueldoA});
        rows.add(new String[]{"Foto"});
        return  rows;
    }

    private ArrayList<String[]>datos_PERSONALES( ){
        String sexo="0";
        if(hombre.isChecked()){
            sexo="F( )   M(X)";
        }else{
            if(mujer.isChecked()){
                sexo="F(X)   M()";
            }
        }

        String vive_con="0";
        if (suspadres.isChecked()){
            vive_con="Sus Padres(X) Su familia( ) Parientes( ) Solo( )";
        }else{
            if (sufamilia.isChecked()){
                vive_con="Sus Padres( ) Su familia(X) Parientes( ) Solo( )";
            }else{
                if (susparientes.isChecked()){
                    vive_con="Sus Padres( ) Su familia( ) Parientes(X) Solo( )";
                }else{
                    if (suotros.isChecked()){
                        vive_con="Sus Padres( ) Su familia( ) Parientes( ) Solo(X)";
                    }else{
                        vive_con="Sus Padres( ) Su familia( ) Parientes( ) Solo( )";
                    }
                }
            }
        }


        String personas_dependen="0";
        if (dephijos.isChecked()){
            personas_dependen="Hijos(X) Conyuge( ) Padres( ) Otros( )";
        }else{
            if (deppadres.isChecked()){
                personas_dependen="Hijos( ) Conyuge( ) Padres(X) Otros( )";
            }else{
                if (depconyuge.isChecked()){
                    personas_dependen="Hijos( ) Conyuge(X) Padres( ) Otros( )";
                }else{
                    if (depotros.isChecked()){
                        personas_dependen="Hijos( )  Conyuge( )  Padres( )  Otros(X)";
                    }else{
                        personas_dependen="Hijos( )  Conyuge( )  Padres( )  Otros( )";
                    }
                }
            }
        }

        String estado_Civil="0";
        if (solero.isChecked()){
            estado_Civil="Soltero(X)  Casado( )  Otro( )";
        }else{
            if (casado.isChecked()){
                estado_Civil="Soltero( )  Casado(X)  Otro( )";
            }else{
                if (esaotro.isChecked()){
                    estado_Civil="Soltero( )  Casado( )  Otro(X)";
                }else{
                    estado_Civil="Soltero( )  Casado( )  Otro( )";
                }
            }
        }

        ArrayList<String[]>rows=new ArrayList<>();
        rows.add(new String[]{"Apellido Paterno ","Apellido Materno","Nombre(s)","Edad","Sexo"});
        rows.add(new String[]{apelllidop.getText().toString(),aellidom.getText().toString(),nombreP.getText().toString(),edad.getText().toString(),sexo});
        rows.add(new String[]{"Domicilio (Calle y Número)","Colonia","Código postal"});
        rows.add(new String[]{domicilio.getText().toString(),colonia.getText().toString(),codigo_p.getText().toString()});
        rows.add(new String[]{"Lugar de Nacimiento","Estado","Ciudad"});
        rows.add(new String[]{lugarde_n.getText().toString(),estado.getText().toString(),ciudad.getText().toString()});
        rows.add(new String[]{"Nacionalidad ","Fecha de Nacimiento","Estatura ","Peso"});
        rows.add(new String[]{nacionalidad.getText().toString(),fechade_n.getText().toString(),estatura.getText().toString(),peso.getText().toString()});
        rows.add(new String[]{"Personas que dependen de usted ","Estado Civil"});
        rows.add(new String[]{personas_dependen,estado_Civil});
        rows.add(new String[]{"Numero de telefono","Vive con"});
        rows.add(new String[]{numero_telefono.getText().toString(),vive_con});
        return  rows;
    }


    private ArrayList<String[]>documentacion(){
        String licenciamanejo="0";
        if(si_licencia.isChecked()){
            licenciamanejo="Si(X)  No( )";
        }else{
            if(no_licencia.isChecked()){
                licenciamanejo="Si( )  No(X)";
            }else{
                licenciamanejo="Si( )  No( )";
            }
        }
        ArrayList<String[]>rows=new ArrayList<>();
        rows.add(new String[]{"clave única de registro de población(CURP)}","AFORE"});
        rows.add(new String[]{curp.getText().toString(),afore.getText().toString()});
        rows.add(new String[]{"Numero de seguridad social(NSS)","Numero de cartilla militar"});
        rows.add(new String[]{nss.getText().toString(),cartilla_militar.getText().toString()});
        rows.add(new String[]{"Licencia de manejo","Tipo","RFC"});
        rows.add(new String[]{licenciamanejo,tipoilicencia.getText().toString(),rcf.getText().toString()});
        return  rows;
    }
    private ArrayList<String[]>estadoSalud(){
        String estadoactual="0";
        if (salud_buena.isChecked()){
            estadoactual="Bueno(X) Malo( ) Regular( )";
        }else {
            if (salud_mala.isChecked()){
                estadoactual="Bueno( ) Malo(X) Regular( )";
            }else{
                if (salud_reglar.isChecked()){
                    estadoactual="Bueno( ) Malo( ) Regular(X)";
                }else{
                    estadoactual="Bueno( ) Malo( ) Regular( )";
                }
            }
        }

        String algunaEnfermedad="0";
        if(si_enfermo.isChecked()){
            algunaEnfermedad="Si(X) No( )";
        }else{
            if(no_enfermo.isChecked()){
                algunaEnfermedad="Si( ) No(X)";
            }else{
                algunaEnfermedad="Si( ) No( )";
            }
        }

        ArrayList<String[]>rows=new ArrayList<>();
        rows.add(new String[]{"¿Cómo consideras su estado de salud actual","¿Cúal es tu meta en la vida?"});
        rows.add(new String[]{estadoactual,metasp.getText().toString()});
        rows.add(new String[]{"¿Padece de alguna enfermedad cronica?","Explique cual(es)"});
        rows.add(new String[]{algunaEnfermedad,enfermedadesPA.getText().toString()});

        return  rows;
    }
    private ArrayList<String[]>escolaridad(){
        ArrayList<String[]>rows=new ArrayList<>();
        String alcanzado="";

        if (primaria.isChecked()){
            alcanzado="Primaria( X ) Secundaria(   )  Preparatoria(  )  Superior(  )";
        }else{
            if(secundaria.isChecked()){
                alcanzado="Primaria(  ) Secundaria( X )  Preparatoria(  )  Superior(  )";
            }else{
                if(preparatoria.isChecked()) {
                    alcanzado = "Primaria(  ) Secundaria(  )  Preparatoria( X )  Superior(  )";
                }else{
                    if(superior.isChecked()) {
                        alcanzado = "Primaria(  ) Secundaria(  )  Preparatoria(  )  Superior( X )";
                    }else{
                        alcanzado = "Primaria(  ) Secundaria(  )  Preparatoria(  )  Superior(  )";
                    }
                }
            }

        }

        rows.add(new String[]{"Estudios alcanzado:",alcanzado});
        rows.add(new String[]{"Nombre de la institución:", String.valueOf(institucion.getText())});
        rows.add(new String[]{"título obtenido:",String.valueOf(titulo.getText())});
        rows.add(new String[]{"Estudios que efectua en la actualidad:",String.valueOf(estudioActual.getText())});

        return  rows;
    }
    private ArrayList<String[]>conocimientosGenerales(){
        ArrayList<String[]>rows=new ArrayList<>();
        rows.add(new String[]{"¿Qué idioma hablas?(50%,75%,100%)","Funciones de oficina que domina:"});
        rows.add(new String[]{String.valueOf(idiomas.getText()), String.valueOf(funcionesOficina.getText())});
        rows.add(new String[]{"Maquina de oficina o taller que sepa manejar:","Software que conoce:"});
        rows.add(new String[]{String.valueOf(maquinaOficina.getText()), String.valueOf(software.getText())});
        rows.add(new String[]{"Otros trabajos o funciones que domina:"});
        rows.add(new String[]{String.valueOf(otrosTrabajos.getText())});

        return  rows;
    }
    private ArrayList<String[]>empleos(){
        ArrayList<String[]>rows=new ArrayList<>();
        rows.add(new String[]{" ","Empleo anterior","Empleo anterior","Empleo anterior"});
        rows.add(new String[]{"Nombre de la empresa:",String.valueOf(empresa1.getText()),String.valueOf(empresa2.getText()),String.valueOf(empresa3.getText())});
        rows.add(new String[]{"Del año:",String.valueOf(anoE1.getText()),String.valueOf(anoE2.getText()),String.valueOf(anoE3.getText())});
        rows.add(new String[]{"Al año:",String.valueOf(anoS1.getText()),String.valueOf(anoS2.getText()),String.valueOf(anoS3.getText())});

        return  rows;
    }
    private ArrayList<String[]>referencias(){

        //nRefe1 domi1 telef1 tc1 ocupa1
        ArrayList<String[]>rows=new ArrayList<>();
        rows.add(new String[]{"Nombre","Telefono","Domicilio","Ocupación","Tiempo de conocerlo"});
        rows.add(new String[]{String.valueOf(nRefe1.getText()),String.valueOf(telef1.getText()),String.valueOf(domi1.getText()),String.valueOf(ocupa1.getText()),String.valueOf(tc1.getText())});
        rows.add(new String[]{String.valueOf(nRefe2.getText()),String.valueOf(telef2.getText()),String.valueOf(domi2.getText()),String.valueOf(ocupa2.getText()),String.valueOf(tc2.getText())});
        rows.add(new String[]{String.valueOf(nRefe3.getText()),String.valueOf(telef3.getText()),String.valueOf(domi3.getText()),String.valueOf(ocupa3.getText()),String.valueOf(tc3.getText())});

        return  rows;
    }
    private ArrayList<String[]>datosGenerales(){

        String aux1,aux2,aux3,aux4,aux5,aux6,aux7;
        if(anuncio.isChecked()){
            aux7="Anuncio( X )  Otro medios(  )";
        }else{
            if(otrosMedios.isChecked()){
                aux7="Anuncio(  )  Otro medios( X )";
            }else{
                aux7="Anuncio(  )  Otro medios(  )";

            }
        }
        if(si1.isChecked()){
            aux1="No(  )  Si( X )";
        }else{
            if(no1.isChecked()){
                aux1="No( X )  Si(  )";
            }else{
                aux1="No(  )  Si(  )";

            }
        }
        if(si2.isChecked()){
            aux2="No(  )  Si( X )";
        }else{
            if(no2.isChecked()){
                aux2="No( X )  Si(  )";
            }else{
                aux2="No(  )  Si(  )";

            }
        }
        if(si3.isChecked()){
            aux3="No(  )  Si( X )";
        }else{
            if(no3.isChecked()){
                aux3="No( X )  Si(  )";
            }else{
                aux3="No(  )  Si(  )";

            }
        }
        if(si4.isChecked()){
            aux4="No(  )  Si( X )";
        }else{
            if(no4.isChecked()){
                aux4="No( X )  Si(  )";
            }else{
                aux4="No(  )  Si(  )";

            }
        }
        if(si5.isChecked()){
            aux5="No(  )  Si( X )";
        }else{
            if(no5.isChecked()){
                aux5="No( X )  Si(  )";
            }else{
                aux5="No(  )  Si(  )";

            }
        }
        if(si6.isChecked()){
            aux6="No(  )  Si( X )";
        }else{
            if(no6.isChecked()){
                aux6="No( X )  Si(  )";
            }else{
                aux6="No(  )  Si(  )";

            }
        }



// private EditText otroMedio,pariente,afianza,sindicato,seguro,razonesV,razonesC,fechaPresentarse;
        ArrayList<String[]>rows=new ArrayList<>();
        rows.add(new String[]{"¿Cómo supo de este empleo?"});
        rows.add(new String[]{aux7,"Este fue el otro medio: "+ String.valueOf(otroMedio.getText())});
        rows.add(new String[]{"¿Tiene parientes trabajando en esta empresa?"});
        rows.add(new String[]{aux1,"Nomre del pariente:"+ pariente.getText().toString()});
        rows.add(new String[]{"¿Has estado afianzado?"});
        rows.add(new String[]{aux2,"Nombre de la CIA: "+afianza.getText().toString()});
        rows.add(new String[]{"¿has estado afiliado a algun sindicato?"});
        rows.add(new String[]{aux3,"¿A cual? "+sindicato.getText().toString()});
        rows.add(new String[]{"¿Tienes seguro de vida?"});
        rows.add(new String[]{aux4,"Nombre de la CIA: "+seguro.getText().toString()});
        rows.add(new String[]{"Disposición de viajar"});
        rows.add(new String[]{aux5,"Razon: "+razonesV.getText().toString()});
        rows.add(new String[]{"¿Disponibilidad a cambiar su residencia"});
        rows.add(new String[]{aux6,"Razon: "+razonesC.getText().toString()});
        rows.add(new String[]{"¿Fechha en que podria presentarse a trabajar?"});
        rows.add(new String[]{String.valueOf(fechaPresentarse.getText())});
        rows.add(new String[]{"Firma Solicitante"," "});


        return  rows;
    }
    private ArrayList<String[]>comentariosEntrevistador(){
        ArrayList<String[]>rows=new ArrayList<>();
        rows.add(new String[]{"  "});
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

    private void olderVersions(String nombre){
        if(checkPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE)){
            crearPdf(nombre);
            limpiarVariables();
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