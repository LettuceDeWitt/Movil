package com.example.pokeapijavaapp;

import android.app.Activity;
import android.os.Bundle;
import android.widget.Button;

public class CreditsActivity extends Activity {

    Button btnRegresar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_credits);

        btnRegresar = findViewById(R.id.btnRegresar);
        btnRegresar.setOnClickListener(v -> finish());
    }
}
