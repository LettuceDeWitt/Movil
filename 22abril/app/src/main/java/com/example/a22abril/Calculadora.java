package com.example.a22abril;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

public class Calculadora extends Activity implements View.OnClickListener {

    Button[] btnDigitos = new Button[10];

    Button btnSuma, btnResta, btnMultiplicacion, btnDivision;
    Button btnPunto, btnIgual, btnLimpiar;

    EditText pantalla;

    double op1, op2, res;
    String operacion = "";
    boolean pintarPunto = true;

    @Override
    protected void onCreate(Bundle b) {
        super.onCreate(b);

        LinearLayout panelPrincipal = new LinearLayout(this);
        panelPrincipal.setOrientation(LinearLayout.VERTICAL);
        panelPrincipal.setBackgroundColor(Color.BLACK);
        panelPrincipal.setPadding(20, 20, 20, 20);

        TextView titulo = new TextView(this);
        titulo.setText("Calculadora");
        titulo.setTextColor(Color.WHITE);
        titulo.setTextSize(24);
        titulo.setGravity(Gravity.CENTER);

        pantalla = new EditText(this);
        pantalla.setTextColor(Color.WHITE);
        pantalla.setTextSize(36);
        pantalla.setGravity(Gravity.RIGHT);
        pantalla.setMaxLines(1);
        pantalla.setEnabled(false);
        pantalla.setBackgroundColor(Color.DKGRAY);
        pantalla.setPadding(20, 20, 20, 20);

        LinearLayout.LayoutParams parametrosPantalla = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                220
        );

        LinearLayout fila1 = crearFila();
        LinearLayout fila2 = crearFila();
        LinearLayout fila3 = crearFila();
        LinearLayout fila4 = crearFila();
        LinearLayout fila5 = crearFila();

        for (int i = 0; i <= 9; i++) {
            btnDigitos[i] = crearBoton(String.valueOf(i));
            btnDigitos[i].setOnClickListener(this);
        }

        btnDivision = crearBoton("/");
        btnMultiplicacion = crearBoton("*");
        btnResta = crearBoton("-");
        btnSuma = crearBoton("+");
        btnPunto = crearBoton(".");
        btnIgual = crearBoton("=");
        btnLimpiar = crearBoton("C");

        btnDivision.setOnClickListener(this);
        btnMultiplicacion.setOnClickListener(this);
        btnResta.setOnClickListener(this);
        btnSuma.setOnClickListener(this);
        btnPunto.setOnClickListener(this);
        btnIgual.setOnClickListener(this);
        btnLimpiar.setOnClickListener(this);

        fila1.addView(btnDigitos[7]);
        fila1.addView(btnDigitos[8]);
        fila1.addView(btnDigitos[9]);
        fila1.addView(btnDivision);

        fila2.addView(btnDigitos[4]);
        fila2.addView(btnDigitos[5]);
        fila2.addView(btnDigitos[6]);
        fila2.addView(btnMultiplicacion);

        fila3.addView(btnDigitos[1]);
        fila3.addView(btnDigitos[2]);
        fila3.addView(btnDigitos[3]);
        fila3.addView(btnResta);

        fila4.addView(btnDigitos[0]);
        fila4.addView(btnPunto);
        fila4.addView(btnIgual);
        fila4.addView(btnSuma);

        LinearLayout.LayoutParams parametrosLimpiar = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.MATCH_PARENT
        );

        parametrosLimpiar.setMargins(5, 5, 5, 5);
        btnLimpiar.setLayoutParams(parametrosLimpiar);
        fila5.addView(btnLimpiar);

        panelPrincipal.addView(titulo);
        panelPrincipal.addView(pantalla, parametrosPantalla);
        panelPrincipal.addView(fila1);
        panelPrincipal.addView(fila2);
        panelPrincipal.addView(fila3);
        panelPrincipal.addView(fila4);
        panelPrincipal.addView(fila5);

        setContentView(panelPrincipal);
    }

    public LinearLayout crearFila() {
        LinearLayout fila = new LinearLayout(this);
        fila.setOrientation(LinearLayout.HORIZONTAL);
        fila.setGravity(Gravity.CENTER);

        LinearLayout.LayoutParams parametrosFila = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                150
        );

        fila.setLayoutParams(parametrosFila);

        return fila;
    }

    public Button crearBoton(String texto) {
        Button boton = new Button(this);
        boton.setText(texto);
        boton.setTextSize(28);
        boton.setTextColor(Color.BLACK);

        LinearLayout.LayoutParams parametrosBoton = new LinearLayout.LayoutParams(
                0,
                LinearLayout.LayoutParams.MATCH_PARENT,
                1
        );

        parametrosBoton.setMargins(5, 5, 5, 5);
        boton.setLayoutParams(parametrosBoton);

        return boton;
    }

    @Override
    public void onClick(View v) {

        for (int i = 0; i <= 9; i++) {
            if (v.equals(btnDigitos[i])) {
                pantalla.setText(pantalla.getText().toString() + i);
            }
        }

        if (v.equals(btnPunto)) {
            if (pintarPunto) {
                pantalla.setText(pantalla.getText().toString() + ".");
                pintarPunto = false;
            }
        }

        if (v.equals(btnSuma)) {
            guardarOperacion("+");
        }

        if (v.equals(btnResta)) {
            guardarOperacion("-");
        }

        if (v.equals(btnMultiplicacion)) {
            guardarOperacion("*");
        }

        if (v.equals(btnDivision)) {
            guardarOperacion("/");
        }

        if (v.equals(btnIgual)) {
            calcularResultado();
        }

        if (v.equals(btnLimpiar)) {
            pantalla.setText("");
            op1 = 0;
            op2 = 0;
            res = 0;
            operacion = "";
            pintarPunto = true;
        }
    }

    public void guardarOperacion(String op) {
        if (!pantalla.getText().toString().equals("")) {
            op1 = Double.parseDouble(pantalla.getText().toString());
            operacion = op;
            pantalla.setText("");
            pintarPunto = true;
        }
    }

    public void calcularResultado() {
        if (!pantalla.getText().toString().equals("")) {
            op2 = Double.parseDouble(pantalla.getText().toString());

            if (operacion.equals("+")) {
                res = op1 + op2;
            } else if (operacion.equals("-")) {
                res = op1 - op2;
            } else if (operacion.equals("*")) {
                res = op1 * op2;
            } else if (operacion.equals("/")) {
                if (op2 != 0) {
                    res = op1 / op2;
                } else {
                    pantalla.setText("Error");
                    return;
                }
            }

            pantalla.setText(String.valueOf(res));
            pintarPunto = true;
        }
    }
}