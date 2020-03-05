package com.example.healthy.Activities;

import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.healthy.Classes.Profile;
import com.example.healthy.Database.DatabaseHandler;
import com.example.healthy.R;

public class EtatActivity extends AppCompatActivity {
    DatabaseHandler db = new DatabaseHandler(this);


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_etat);
        Profile p = db.getProfile(1);
        etat(p.get_imc());
    }


    public void etat(double imc)
    {
        TextView t1 = findViewById(R.id.TextEtatIMC) ;
        TextView t2 = findViewById(R.id.TextEtatEtat) ;
        TextView t3 = findViewById(R.id.TextEtatRecommandation) ;
        TextView t4 = findViewById(R.id.TextEtatTextRecommandation) ;
        t1.setText(t1.getText().toString()+" "+String.format("%.2f",imc));
        if(imc < 18.5)
        {
            t2.setText(t2.getText().toString()+" Votre IMC est trop faible : vous étes en situation de maigreur");
            t3.setText(t3.getText().toString()+" Prendre du poids");
            t4.setText(t4.getText().toString()+" Si votre IMC est inférieur à 18,5, vous êtes maigre, au sens médical du terme. Aussi peut-il apparaître nécessaire pour vous de grossir : mais rien ne remplace une consultation chez le médecin, seul lui pourra vous donner la marche à suivre.");
        }
        else if((imc >= 18.5) && (imc <= 25))
        {
            t2.setText(t2.getText().toString()+" Votre IMC est normal");
            t3.setText(t3.getText().toString()+" Continuez à manger équilibré");
            t4.setText(t4.getText().toString()+" Si votre IMC se situe entre 18,5 et 25, vous êtes de corpulence normale, c’est-à-dire que vous n’êtes ni en surpoids, ni maigre. Continuez à manger équilibré et à faire de l’exercice régulièrement : ce mode de vie sain est garant d’une bonne santé, sans oublier" + " la notion de plaisir bien sûr !");
        }
        else
        {
            t2.setText(t2.getText().toString()+" Votre IMC est trop élevé : vous étes en surpoids");
            t3.setText(t3.getText().toString()+" Perdre du poids");
            t4.setText(t4.getText().toString()+"Si votre IMC est supérieur à 25, vous êtes en situation de surpoids. peut-il apparaître nécessaire pour vous de maigrir : mais rien ne remplace une consultation chez le médecin, seul lui pourra vous donner la marche à suivre. ");
        }



    }









}
