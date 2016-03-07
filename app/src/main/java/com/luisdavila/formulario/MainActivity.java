package com.luisdavila.formulario;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.Calendar;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MainActivity extends AppCompatActivity {

    private TextView lblMensaje;
    private Spinner cmbOpciones;
    TextView eText;
    String op="";
    String op1 = "";
    String op2 ="";
    String op3 ="";
    String op4 ="";
    int dia, mes, anyo;
    String imprimir, loggin, pass, rpass, correo, ciudad;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final DatePicker fecha= (DatePicker) findViewById(R.id.date);
        lblMensaje = (TextView)findViewById(R.id.LblMensaje);
        cmbOpciones = (Spinner)findViewById(R.id.CmbOpciones);
        final EditText eLoggin = (EditText) findViewById(R.id.eLoggin);
        final EditText eCorreo = (EditText) findViewById(R.id.eCorreo);
        final EditText ePaass = (EditText) findViewById(R.id.ePass);
        final EditText eRpass = (EditText) findViewById(R.id.eRpass);
        eText = (TextView) findViewById(R.id.eVista);
        Button bSend = (Button) findViewById(R.id.aceptar);








        final String[] datos =
                new String[]{"Medellín","Montería","Bogota","Cali","Manizales"};


        ArrayAdapter<String> adaptador =
                new ArrayAdapter<String>(this,
                        android.R.layout.simple_spinner_item, datos);


        adaptador.setDropDownViewResource(
                android.R.layout.simple_spinner_dropdown_item);


        cmbOpciones.setAdapter(adaptador);



        cmbOpciones.setOnItemSelectedListener(
                new AdapterView.OnItemSelectedListener() {
                    public void onItemSelected(AdapterView<?> parent,
                                               android.view.View v, int position, long id) {
                        lblMensaje.setText("Seleccionado: " +
                                parent.getItemAtPosition(position));
                        ciudad = parent.getItemAtPosition(position).toString();

                    }

                    public void onNothingSelected(AdapterView<?> parent) {
                        lblMensaje.setText("");
                    }


                });


        bSend.setOnClickListener(new View.OnClickListener() {
                                     @Override
                                     public void onClick(View v) {
                                         loggin = eLoggin.getText().toString();
                                         pass = ePaass.getText().toString();
                                         rpass = eRpass.getText().toString();
                                         correo = eCorreo.getText().toString();
                                         dia=fecha.getDayOfMonth();
                                         mes=fecha.getMonth();
                                         anyo=fecha.getYear();


                                         if (eLoggin.getText().toString().trim().length() > 0 &&
                                                 ePaass.getText().toString().trim().length() > 0 &&
                                                 eRpass.getText().toString().trim().length() > 0 &&
                                                 eCorreo.getText().toString().trim().length() > 0 &&
                                                 isEmailValid(eCorreo.getText().toString()) == true &&
                                                 op.length() > 0 && ciudad.length() > 0 && pass.equals(rpass)
                                                 &&(op1.length() >0 || op2.length() >0 || op3.length() >0 || op4.length() >0)

                                                 ) {
                                             if (anyo < 2016) {
                                                 imprimir = "Loggin: " + loggin + "\n" + "Password: " + pass + "\n" +
                                                         "Correo electronico:  " + correo + "\n" + "Sexo: " +
                                                         op + "\n" + "Fecha de nacimiento:\n    Día: " + dia + " Mes: " +
                                                         mes + " Año: " + anyo + "\nCiudad: " + ciudad+ "\nHobbies: " + op1 +
                                                        ", " +op2 +", " +op3 +", " +op4;
                                             } else {
                                                 if (anyo >= 2016) {
                                                     if (anyo > 2016) {
                                                         imprimir = "Fecha no valida";
                                                     } else {
                                                         if (anyo == 2016) {
                                                             if (mes > 3) {
                                                                 imprimir = "Fecha no valida";
                                                             } else {
                                                                 if (mes == 3) {
                                                                     if (dia > 7) {
                                                                         imprimir = "Fecha no valida";
                                                                     } else {
                                                                         imprimir = "Loggin: " + loggin + "\n" + "Password: " + pass + "\n" +
                                                                                 "Correo electronico:  " + correo + "\n" + "Sexo: " +
                                                                                 op + "\n" + "Fecha de nacimiento:\n    Día: " + dia + " Mes: " +
                                                                                 mes + " Año: " + anyo + "\nCiudad: " + ciudad+ "\nHobbies: " + op1 +
                                                                                 ", " +op2 +", " +op3 +", " +op4;
                                                                     }
                                                                 }
                                                                 imprimir = "Loggin: " + loggin + "\n" + "Password: " + pass + "\n" +
                                                                         "Correo electronico:  " + correo + "\n" + "Sexo: " +
                                                                         op + "\n" + "Fecha de nacimiento:\n    Día: " + dia + " Mes: " +
                                                                         mes + " Año: " + anyo + "\nCiudad: " + ciudad+ "\nHobbies: " + op1 +
                                                                         ", " +op2 +", " +op3 +", " +op4;
                                                             }

                                                         }


                                                     }

                                                 }
                                             }
                                         }else{
                                                 if (pass.equals(rpass) == false) {
                                                     imprimir = "No coinciden los passwords";
                                                 }else {
                                                     if (isEmailValid(eCorreo.getText().toString()) == false) {
                                                         imprimir = "Email no valido";
                                                     }else {
                                                         imprimir = "Faltan datos";
                                                     }

                                                 }


                                             }


                                                 eText.setText(imprimir);


                                     }



        });



    }

    public void onCheckboxClicked(View view) {
        // Is the view now checked?
        boolean checked = ((CheckBox) view).isChecked();

        // Check which checkbox was clicked
        switch(view.getId()) {
            case R.id.checkbox_tv:
                if(checked)
                    op1="Ver TV";
                else
                    op1="";
                break;
            case R.id.checkbox_est:
                if(checked)
                    op2="Estudiar";
                else
                    op2="";
                break;

            case R.id.checkbox_game:
                if(checked)
                    op3="Videojuegos";
                else
                    op3="";
                break;
            case R.id.checkbox_soc:
                if(checked)
                    op4="Fútbol";
                else
                    op4="";
                break;
            // TODO: Veggie sandwich
        }
    }


    public void onRadioButtonClicked(View view) {
        // Is the button now checked?
        boolean checked = ((RadioButton) view).isChecked();

        // Check which radio button was clicked
        switch(view.getId()) {
            case R.id.radioMas:
                if (checked)
                    op="Masculino";
                break;
            case R.id.radioFem:
                if (checked)
                    op="Femenino";
                break;

        }
    }

    public boolean isEmailValid(String email){
        String regExpn =
                "^(([\\w-]+\\.)+[\\w-]+|([a-zA-Z]{1}|[\\w-]{2,}))@"
                        +"((([0-1]?[0-9]{1,2}|25[0-5]|2[0-4][0-9])\\.([0-1]?"
                        +"[0-9]{1,2}|25[0-5]|2[0-4][0-9])\\."
                        +"([0-1]?[0-9]{1,2}|25[0-5]|2[0-4][0-9])\\.([0-1]?"
                        +"[0-9]{1,2}|25[0-5]|2[0-4][0-9])){1}|"
                        +"([a-zA-Z]+[\\w-]+\\.)+[a-zA-Z]{2,4})$";

        CharSequence inputStr = email;

        Pattern pattern = Pattern.compile(regExpn,Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(inputStr);

        if(matcher.matches())
            return true;
        else
            return false;
    }




}
