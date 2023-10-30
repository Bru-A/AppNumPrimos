package com.example.appnumprimos;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.ArrayList;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.Toast;
import android.widget.ToggleButton;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity
{
private EditText quantidadePrimos;
private EditText multilineResultados;
private List<Integer> listaPrimos;

private ListView listaView;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        quantidadePrimos = findViewById(R.id.editTextQntPrimos);
        Button botaoCalcular = findViewById(R.id.buttonCalcular);
        //multilineResultados = findViewById(R.id.editTextTextMultiLine);
        RadioButton botaoDesc = findViewById(R.id.radioButtonDesc);
        RadioButton botaoAsc = findViewById(R.id.radioButtonAsc);

        listaView = findViewById(R.id.listView);


        botaoDesc.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener()
        {
            @Override //chamando o método Desc

            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked)
                {
                    Desc();

                }

            }
        });
        botaoAsc.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override

            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked)
                {
                    Asc();

                }

            }
        });





        botaoCalcular.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                listaPrimos = calcularQuantidadePrimos();
                StringBuilder resultado = new StringBuilder();
                //atualizarEditText();

                for(int primos:listaPrimos)
                {
                    resultado.append(String.valueOf(primos)).append("\n");
                }

                //multilineResultados.setText(resultado.toString());

                //Criar o adaptador para lista, tem que ir dentro do metodo botao
                ArrayAdapter<Integer> adaptador = new ArrayAdapter<Integer>(
                        getApplicationContext(), android.R.layout.simple_list_item_1,
                        android.R.id.text1,listaPrimos
                );
                //Add o adaptador a listView
                listaView.setAdapter(adaptador);

                listaView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override //evento listener para detectar na lista onde eu cliquei
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        String valorSelecionado = listaView.getItemAtPosition(position).toString();
                        Toast.makeText(getApplicationContext(),valorSelecionado, Toast.LENGTH_LONG).show();

                    }
                });

            }


        });
            }
    /*private void atualizarEditText() {
        StringBuilder resultado = new StringBuilder();

        for (int primo : listaPrimos) {
            resultado.append(String.valueOf(primo)).append("\n");
        }

        multilineResultados.setText(resultado.toString());
    }*/

    public List<Integer> calcularQuantidadePrimos()
    {
        String texto = quantidadePrimos.getText().toString();
        int valorInteiro = Integer.parseInt(texto);
        List<Integer> primos = new ArrayList<>();
        for(int x=2; x<= valorInteiro;x++)
        {
            if (calcularPrimos(x))
            {
                primos.add(x);
            }
        }
        return primos;
    }

    public boolean calcularPrimos(int numero)
    {

        for( int x =2; x < numero;x++)
        {
            if(numero % x == 0 || numero <=1)
                return false;
        }
        return true;
    }

    public void Desc()
    {
        //List<Integer> listaPrimos = calcularQuantidadePrimos();
        Collections.sort(listaPrimos, Collections.reverseOrder());
        ArrayAdapter<Integer> adaptador = new ArrayAdapter<Integer>(
                getApplicationContext(), android.R.layout.simple_list_item_1,
                android.R.id.text1, listaPrimos
        );
        listaView.setAdapter(adaptador);
    }
    public void Asc()
    {
        //List<Integer> listaPrimos = calcularQuantidadePrimos();
        Collections.sort(listaPrimos);
        ArrayAdapter<Integer> adaptador = new ArrayAdapter<Integer>(
                getApplicationContext(), android.R.layout.simple_list_item_1,
                android.R.id.text1, listaPrimos);
        listaView.setAdapter(adaptador);
    }
}