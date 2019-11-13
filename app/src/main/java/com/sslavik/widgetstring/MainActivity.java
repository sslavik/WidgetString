package com.sslavik.widgetstring;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewStub;
import android.webkit.WebView;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Arrays;

public class MainActivity extends AppCompatActivity {

    // CAMPOS

    //Views
    private AutoCompleteTextView atMes;
    private WebView wbInformation;
    private Spinner spCantEmpleados;
    private RadioGroup rgTypeClient;
    private ViewStub vstClient;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // INSTANCIAMOS
        atMes = findViewById(R.id.atMes);
        wbInformation = findViewById(R.id.wbInformation);
        spCantEmpleados = findViewById(R.id.spinner);
        rgTypeClient = findViewById(R.id.rgTipoCliente);
        vstClient = findViewById(R.id.vstCliente);
        //intialiceAtMes();
        //initialiceWbInformation();
        //initialicespEmpleados(); // SE TIENE QUE HACER CUANDO EL LAYOUT TENGA LA REFERENCIA
        initialiceRgTypeClient();

    }

    /**
     * Método que realiza una accion cuando se selecciona un RadioButton del grupo
     */
    private void initialiceRgTypeClient() {
        rgTypeClient.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                vstClient.
                switch (checkedId){
                    case R.id.rbEmpresa :
                        vstClient.setLayoutResource(R.layout.layout_bussiness);
                        break;
                    case R.id.rbParticular:
                        vstClient.setLayoutResource(R.layout.layout_particular);
                        break;
                }
                vstClient.inflate();
            }
        });
    }

    private void initialicespEmpleados() {
        spCantEmpleados.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String mensaje = spCantEmpleados.getResources().getQuantityString(R.plurals.numEmpleados,
                        Integer.parseInt(spCantEmpleados.getSelectedItem().toString()));
                mensaje = spCantEmpleados.getResources().getQuantityString(
                            R.plurals.numEmpleados,
                            Integer.parseInt(spCantEmpleados.getSelectedItem().toString()),
                            Integer.parseInt((spCantEmpleados.getSelectedItem().toString())));

                Toast.makeText(parent.getContext(), mensaje, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    /**
     * Inicializar el componetne WebView y añadir posibles eventos
     */
    private void initialiceWbInformation() {
        // From Data
         wbInformation.loadData(getResources().getString(R.string.wbInformation), "text/html", "UTF-8");
        // Url
        //wbInformation.loadUrl("https://google.com");
    }


    /**
     * Inicializa y añade posibles sugerencias a la vista
     */
    private void intialiceAtMes() {
        String months[] = {"Enero", "Febrero", "Marzo", "Abril", "Mayo", "Junio", "Julio", "Agosto", "Septiembre", "Octubre", "Noviembre", "Diciembre"};
        // Declarar listas, vectores en las clases es más eficiente que declarar un array
        ArrayList listMonths = new ArrayList(Arrays.asList(months));
        // Creamos adapter para el AutoCompleteTextView
        ArrayAdapter adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, listMonths);

        atMes.setAdapter(adapter);
     }
}
