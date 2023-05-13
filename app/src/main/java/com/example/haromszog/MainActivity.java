package com.example.haromszog;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    ConstraintLayout main;
    EditText aNumb;
    EditText bNumb;
    EditText cNumb;
    EditText alfaNumb;
    EditText betaNumb;
    EditText gammaNumb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initGui();
        main.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                if (imm != null) {
                    imm.hideSoftInputFromWindow(bNumb.getWindowToken(), 0);
                }
            }
        });
    }
    public void initGui(){
        main = findViewById(R.id.main);
        aNumb = findViewById(R.id.aNumb);
        bNumb = findViewById(R.id.bNumb);
        cNumb = findViewById(R.id.cNumb);
        alfaNumb = findViewById(R.id.alfaNumb);
        betaNumb = findViewById(R.id.betaNumb);
        gammaNumb = findViewById(R.id.gammaNumb);
    }


    public void click(View v){
        Eredmeny eredmeny = new Eredmeny(aNumb.getText().toString(), bNumb.getText().toString(), cNumb.getText().toString(), alfaNumb.getText().toString(),
                betaNumb.getText().toString(), gammaNumb.getText().toString());
        aNumb.setText(eredmeny.getA().toString());
        bNumb.setText(eredmeny.getB().toString());
        cNumb.setText(eredmeny.getC().toString());
        alfaNumb.setText(eredmeny.getAlfa().toString());
        betaNumb.setText(eredmeny.getBeta().toString());
        gammaNumb.setText(eredmeny.getGamma().toString());
    }

    public void clrAll(View v){
        aNumb.setText("");
        bNumb.setText("");
        cNumb.setText("");
        alfaNumb.setText("");
        betaNumb.setText("");
        gammaNumb.setText("");
    }

    public void clrA(View v){
        aNumb.setText("");
    }
    public void clrB(View v){
        bNumb.setText("");
    }
    public void clrC(View v){
        cNumb.setText("");
    }
    public void clrAlfa(View v){
        alfaNumb.setText("");
    }
    public void clrBeta(View v){
        betaNumb.setText("");
    }
    public void clrGamma(View v){
        gammaNumb.setText("");
    }

    public class Eredmeny{
        Double a;
        Double b;
        Double c;
        Double alfa;
        Double beta;
        Double gamma;
        Boolean hiba = false;
        public Eredmeny(String a, String b, String c, String alfa, String beta, String gamma){
            this.a= konvertal(a);
            this.b= konvertal(b);
            this.c= konvertal(c);
            this.alfa= konvertal(alfa);
            this.beta= konvertal(beta);
            this.gamma= konvertal(gamma);
            if (!hiba) {
                szamol();
            }
        }

        public Double konvertal(String szam){
            Double kimenet;
            if (szam.isEmpty()){
                szam = "0.0";
            }
            try {
                kimenet = Double.parseDouble(szam);
                return kimenet;
            } catch (Exception e) {
                Toast.makeText(MainActivity.this, "Szám formátum hiba", Toast.LENGTH_SHORT).show();
                hiba = true;
                return 0.0;
            }
        }

        public Double kerekit(Double szam, Integer jegy){
            szam = szam * Math.pow(10,jegy);
            szam = (double) Math.round(szam);
            szam = szam / Math.pow(10,jegy);
            return szam;
        }

        public void szamol(){
            gamma = Math.toDegrees(Math.acos((((a*a) + (b*b)) - (c*c)) / (2*a*b)));
            alfa = Math.toDegrees(Math.asin((Math.sin(Math.toRadians(gamma))) * a/c));
            beta = 180 - alfa- gamma;
        }

        public Double getA() {
            return kerekit(a,3);
        }

        public Double getB() {
            return kerekit(b,3);
        }

        public Double getC() {
            return kerekit(c,3);
        }

        public Double getAlfa() {
            return kerekit(alfa,3);
        }

        public Double getBeta() {
            return kerekit(beta,3);
        }

        public Double getGamma() {
            return kerekit(gamma,3);
        }
    }
}