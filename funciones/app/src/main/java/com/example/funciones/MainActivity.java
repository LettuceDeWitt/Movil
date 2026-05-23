package com.example.funciones;

import android.graphics.Color;
import android.os.Bundle;
import android.app.Activity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity implements View.OnClickListener {

    EditText et1;

    @Override
    protected void onCreate(Bundle si) {
        super.onCreate(si);

        LinearLayout li = new LinearLayout(this);
        li.setOrientation(LinearLayout.VERTICAL);
        li.setBackgroundColor(Color.GREEN);

        TextView tv1 = new TextView(this);
        tv1.setText("Dame tu nombre");
        tv1.setTextSize(20);
        tv1.setPadding(30, 50, 30, 50);

        et1 = new EditText(this);

        Button b1 = new Button(this);
        b1.setText("Saludame");
        b1.setOnClickListener(this);

        Button btnBorrar = new Button(this);
        btnBorrar.setText("Limpiar");

        btnBorrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                et1.setText("");
            }
        });

        Button btnCambiarColor = new Button(this);
        btnCambiarColor.setText("Cambiar a Rojo");

        btnCambiarColor.setOnClickListener(v -> {
            cambiaColor();
        });

        li.addView(tv1);
        li.addView(et1);
        li.addView(b1);
        li.addView(btnBorrar);
        li.addView(btnCambiarColor);

        setContentView(li);
    }

    @Override
    public void onClick(View view) {
        String nombreDado = et1.getText() + "";
        Toast.makeText(this, "hola " + nombreDado, Toast.LENGTH_SHORT).show();
    }

    public void cambiaColor() {
        Toast.makeText(this, "cambio color", Toast.LENGTH_SHORT).show();
        String txtAntes = et1.getText() + "";
        et1.setTextColor(Color.RED);
        et1.setText("");
        et1.setText(txtAntes);
    }
}