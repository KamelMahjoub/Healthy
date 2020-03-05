package com.example.healthy.Activities;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.example.healthy.Classes.Profile;
import com.example.healthy.Database.DatabaseHandler;
import com.example.healthy.R;

public class CreateProfileStep2Activity extends AppCompatActivity {
    SharedPreferences sharedpreferences;
    DatabaseHandler db = new DatabaseHandler(this);



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_profile_step2);

        Button b1 = findViewById(R.id.BtnStep2CréerProfil);






        sharedpreferences = getSharedPreferences("MonProfil", Context.MODE_PRIVATE);
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                RadioGroup rg = (RadioGroup) findViewById(R.id.RadioGroupStep2);
                EditText e1 = findViewById(R.id.InputStep2Poids);
                EditText e2 = findViewById(R.id.InputStep2Taille);
                String poids = e1.getText().toString();
                String taille = e2.getText().toString();
            if(checkInputs())
            {
                if(checkPoids())
                {
                    if(checkTaille())
                    {
                        String nom = sharedpreferences.getString("MonNom", "defaultValue");
                        String prenom = sharedpreferences.getString("MonPrénom", "defaultValue");
                        String anniversaire = sharedpreferences.getString("MonAnniversaire", "defaultValue");
                        int age = sharedpreferences.getInt("MonAge", 0);
                        Profile p = new Profile();
                        p.set_nom(nom);
                        p.set_prénom(prenom);
                        p.set_anniversaire(anniversaire);
                        p.set_age(age);
                        p.set_poids(Integer.parseInt(poids));
                        p.set_taille(Integer.parseInt(taille));
                        double x =  calculeIMC(Integer.parseInt(poids),Integer.parseInt(taille));
                        p.set_imc(x);
                        int id = rg.getCheckedRadioButtonId();
                        RadioButton rb = (RadioButton) findViewById(id);
                        String gender=rb.getText().toString();
                        p.set_sexe(gender);
                        db.addProfile(p);
                        Toast.makeText(getApplicationContext(),"Le profil a été créer avec succées",Toast.LENGTH_SHORT).show();

                        Intent intent = new Intent(view.getContext(), EtatActivity.class);
                        view.getContext().startActivity(intent);
                    }
                }
            }

            }
        });





    }

    public Boolean checkInputs()
    {

        RadioGroup rg = (RadioGroup) findViewById(R.id.RadioGroupStep2);
        EditText e1 = findViewById(R.id.InputStep2Poids);
        EditText e2 = findViewById(R.id.InputStep2Taille);

        String poids = e1.getText().toString();
        String taille = e2.getText().toString();

        if (poids.isEmpty()||taille.isEmpty())
        {
            AlertDialog alertDialog = new AlertDialog.Builder(CreateProfileStep2Activity.this).create();
            alertDialog.setTitle("Erreur");
            alertDialog.setMessage("Veuillez remplir tous les champs s'il vous plait ! ");
            alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "Ok",
                    new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.dismiss();
                        }
                    });
            alertDialog.show();
            return false;
        }
        else if (rg.getCheckedRadioButtonId() == -1)
        {
            AlertDialog alertDialog = new AlertDialog.Builder(CreateProfileStep2Activity.this).create();
            alertDialog.setTitle("Erreur");
            alertDialog.setMessage("Veuillez sélectionner le sexe s'il vous plait ! ");
            alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "Ok",
                    new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.dismiss();
                        }
                    });
            alertDialog.show();
            return false;
        }
        else
            return true;
    }


    public Boolean checkPoids()
    {
        EditText e1 = findViewById(R.id.InputStep2Poids);
        String poids = e1.getText().toString();
        int p = Integer.parseInt(poids);

        if(p <= 0 )
        {
            AlertDialog alertDialog = new AlertDialog.Builder(CreateProfileStep2Activity.this).create();
            alertDialog.setTitle("Erreur");
            alertDialog.setMessage("Le poids doit étre supérieur a zéro ! ");
            alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "Ok",
                    new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.dismiss();
                        }
                    });
            alertDialog.show();
            return false;
        }
        else
            if (p > 500)
            {
                AlertDialog alertDialog = new AlertDialog.Builder(CreateProfileStep2Activity.this).create();
                alertDialog.setTitle("Erreur");
                alertDialog.setMessage("valeur du poids invalide ! ");
                alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "Ok",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.dismiss();
                            }
                        });
                alertDialog.show();
                return false;
            }
            else
            {
                return true;
            }
    }


    public Boolean checkTaille()
    {
        EditText e1 = findViewById(R.id.InputStep2Taille);
        String taille = e1.getText().toString();
        int t = Integer.parseInt(taille);

        if(t <= 0 )
        {
            AlertDialog alertDialog = new AlertDialog.Builder(CreateProfileStep2Activity.this).create();
            alertDialog.setTitle("Erreur");
            alertDialog.setMessage("La taille doit étre supérieur a zéro ! ");
            alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "Ok",
                    new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.dismiss();
                        }
                    });
            alertDialog.show();
            return false;
        }
        else
        if (t > 300)
        {
            AlertDialog alertDialog = new AlertDialog.Builder(CreateProfileStep2Activity.this).create();
            alertDialog.setTitle("Erreur");
            alertDialog.setMessage("la valeur de la taille est invalide ! ");
            alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "Ok",
                    new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.dismiss();
                        }
                    });
            alertDialog.show();
            return false;
        }
        else
        {
            return true;
        }


    }

    public double calculeIMC(int p , int t)
    {
        double res ;
        double x = Math.pow((double)t/100,2.0);
        return res = (double)p/x ;
    }













}
