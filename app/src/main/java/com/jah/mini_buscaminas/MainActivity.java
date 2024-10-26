package com.jah.mini_buscaminas;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    Button btnMinas01, btnMinas02, btnMinas03, btnMinas04, btnMinas10, btnMinas11;
    Button btnMinas12, btnMinas13, btnMinas14, btnMinas20, btnMinas21, btnMinas22;
    Button btnMinas23, btnMinas24, btnMinas30, btnMinas31, btnMinas32, btnMinas33;
    Button btnMinas34, btnMinas40, btnMinas41, btnMinas42, btnMinas43, btnMinas44;
    Button btnMinas00, btnNuevo, btnResolver;
    TextView lblPuntos;
    Random rnd;
    Button[][] botones;
    ArrayList<Boolean> bombas;
    int puntos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        btnMinas00 = findViewById(R.id.btnMinas00);btnMinas01 = findViewById(R.id.btnMinas01);btnMinas02 = findViewById(R.id.btnMinas02);
        btnMinas03 = findViewById(R.id.btnMinas03);btnMinas04 = findViewById(R.id.btnMinas04);btnMinas10 = findViewById(R.id.btnMinas10);
        btnMinas11 = findViewById(R.id.btnMinas11);btnMinas12 = findViewById(R.id.btnMinas12);btnMinas13 = findViewById(R.id.btnMinas13);
        btnMinas14 = findViewById(R.id.btnMinas14);btnMinas20 = findViewById(R.id.btnMinas20);btnMinas21 = findViewById(R.id.btnMinas21);
        btnMinas22 = findViewById(R.id.btnMinas22);btnMinas23 = findViewById(R.id.btnMinas23);btnMinas24 = findViewById(R.id.btnMinas24);
        btnMinas30 = findViewById(R.id.btnMinas30);btnMinas31 = findViewById(R.id.btnMinas31);btnMinas32 = findViewById(R.id.btnMinas32);
        btnMinas33 = findViewById(R.id.btnMinas33);btnMinas34 = findViewById(R.id.btnMinas34);btnMinas40 = findViewById(R.id.btnMinas40);
        btnMinas41 = findViewById(R.id.btnMinas41);btnMinas42 = findViewById(R.id.btnMinas42);btnMinas43 = findViewById(R.id.btnMinas43);
        btnMinas44 = findViewById(R.id.btnMinas44);
        btnNuevo = findViewById(R.id.btnNuevo);
        btnResolver = findViewById(R.id.btnResolver);
        lblPuntos = findViewById(R.id.lblPuntos);
        bombas = new ArrayList<>();
        botones = new Button[][]{
                {btnMinas00, btnMinas01, btnMinas02, btnMinas03, btnMinas04},
                {btnMinas10, btnMinas11, btnMinas12, btnMinas13, btnMinas14},
                {btnMinas20, btnMinas21, btnMinas22, btnMinas23, btnMinas24},
                {btnMinas30, btnMinas31, btnMinas32, btnMinas33, btnMinas34},
                {btnMinas40, btnMinas41, btnMinas42, btnMinas43, btnMinas44}};
        rnd = new Random();
        puntos = 0;
        generarTablero();
        btnNuevo.setOnClickListener(view -> generarTablero());
        btnResolver.setOnClickListener(view -> resolver());
        btnMinas01.setOnClickListener(this::verValor);
        btnMinas02.setOnClickListener(this::verValor);
        btnMinas03.setOnClickListener(this::verValor);
        btnMinas04.setOnClickListener(this::verValor);
        btnMinas10.setOnClickListener(this::verValor);
        btnMinas11.setOnClickListener(this::verValor);
        btnMinas12.setOnClickListener(this::verValor);
        btnMinas13.setOnClickListener(this::verValor);
        btnMinas14.setOnClickListener(this::verValor);
        btnMinas20.setOnClickListener(this::verValor);
        btnMinas21.setOnClickListener(this::verValor);
        btnMinas22.setOnClickListener(this::verValor);
        btnMinas23.setOnClickListener(this::verValor);
        btnMinas24.setOnClickListener(this::verValor);
        btnMinas30.setOnClickListener(this::verValor);
        btnMinas31.setOnClickListener(this::verValor);
        btnMinas32.setOnClickListener(this::verValor);
        btnMinas33.setOnClickListener(this::verValor);
        btnMinas34.setOnClickListener(this::verValor);
        btnMinas40.setOnClickListener(this::verValor);
        btnMinas41.setOnClickListener(this::verValor);
        btnMinas42.setOnClickListener(this::verValor);
        btnMinas43.setOnClickListener(this::verValor);
        btnMinas44.setOnClickListener(this::verValor);

    }

    private void verValor(View miBoton) {
        Button boton = (Button) miBoton;
        boton.setEnabled(false);
        if(!boton.getText().toString().isEmpty()){
            if(!boton.getText().toString().equals("B")){
                boton.setTextColor(getColor(R.color.colorFondo));
                if(boton.getText().toString().equals("1"))
                    puntos += 10;
                else if(boton.getText().toString().equals("2"))
                    puntos += 20;
                else if(boton.getText().toString().equals("3"))
                    puntos += 30;

                lblPuntos.setText("Puntos: " + puntos);
            } else{
                lblPuntos.setText("Game over!!");
                resolver();
            }
        }else if(boton.getText().toString().isEmpty()){
            puntos += 100;
            for(int i = 0;i < botones.length;i++){
                for(int ind = 0;ind < botones.length;ind++){
                    if(botones[i][ind].getText().toString().isEmpty()){
                        botones[i][ind].setBackgroundColor(getColor(R.color.colorFondo));
                    }
                }
            }
        }
        lblPuntos.setText(bombas.size() >= 3 ? "Resuelto!, puntos: " + puntos  : "Puntos: " + puntos );
        bombas.clear();
    }

    private void resolver() {
        puntos = 0;
        for(int i = 0;i < botones.length;i++){
            for(int ind = 0;ind < botones.length;ind++){
                if(botones[i][ind].getText().toString().equals("B"))
                    botones[i][ind].setTextColor(getColor(R.color.btnResolver));
                else
                    botones[i][ind].setTextColor(getColor(R.color.black));

                botones[i][ind].setEnabled(false);
            }

        }
    }

    private void generarTablero() {
        lblPuntos.setText(getString(R.string.lblJugar));
        Random rnd = new Random();
        int contador = 0;
        for(int i = 0;i < botones.length;i++){
            for(int ind = 0;ind < botones.length;ind++){
                botones[i][ind].setText("");
                botones[i][ind].setTextColor(getColor(R.color.botonesMinas));
                botones[i][ind].setBackgroundColor(getColor(R.color.botonesMinas));
                botones[i][ind].setEnabled(true);
            }
        }
        while (contador < 3) {
            int numeroFila = rnd.nextInt(5);
            int numeroColumna = rnd.nextInt(5);
            String posicionBomba = String.format("%02d", numeroFila * 10 + numeroColumna);
            String nombreBoton = getResources().getResourceEntryName(botones[numeroFila][numeroColumna].getId());
            String comparar = nombreBoton.substring(nombreBoton.length() - 2);

            if(posicionBomba.equals(comparar) && botones[numeroFila][numeroColumna].getText().toString().isEmpty()){
                botones[numeroFila][numeroColumna].setText("B");
                actualizarColindantes(botones, numeroFila, numeroColumna);
                contador++;
            }
        }
    }

    private void actualizarColindantes(Button[][] botones, int numeroFila, int numeroColumna) {
        int[] direcciones = {-1, 0, 1};

        for (int i : direcciones) {
            for (int j : direcciones) {
                int nuevaFila = numeroFila + i;
                int nuevaColumna = numeroColumna + j;

                if (nuevaFila >= 0 && nuevaFila < 5 && nuevaColumna >= 0 && nuevaColumna < 5) {
                    Button boton = botones[nuevaFila][nuevaColumna];
                    if (boton.getText().toString().isEmpty() || !boton.getText().toString().equals("B")) {
                        if (boton.getText().toString().isEmpty()) {
                            boton.setText("1");
                        } else {
                            int num = Integer.parseInt(boton.getText().toString());
                            boton.setText(String.valueOf(num + 1));
                        }
                    }
                }
            }
        }
    }
}