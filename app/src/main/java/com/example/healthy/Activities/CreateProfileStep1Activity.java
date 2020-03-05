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
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;

import com.example.healthy.R;

import java.util.Calendar;

public class CreateProfileStep1Activity extends AppCompatActivity {
    SharedPreferences sharedpreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_profile_step1);

        DatePicker dp = (DatePicker) findViewById(R.id.InputStep1Anniversaire);
        Button b1 = findViewById(R.id.BtnStep1Suivant);
        sharedpreferences = getSharedPreferences("MonProfil", Context.MODE_PRIVATE);


        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                EditText e1 = findViewById(R.id.InputStep1Nom);
                EditText e2 = findViewById(R.id.InputStep1Prenom);

                String nom = e1.getText().toString();
                String prenom = e2.getText().toString();
                String anniversaire = getDateofBirth();
                String age = currentDate();

                if(checkInputs())
                {
                    SharedPreferences.Editor editor = sharedpreferences.edit();
                    editor.putString("MonNom", nom);
                    editor.putString("MonPrénom", prenom);
                    editor.putString("MonAnniversaire",anniversaire);
                    editor.putInt("MonAge", Integer.parseInt(age));
                    editor.commit();

                    Intent intent = new Intent(view.getContext(), CreateProfileStep2Activity.class);
                    view.getContext().startActivity(intent);

                }
            }
        });
    }


    public String getDateofBirth(){
        DatePicker dp = (DatePicker) findViewById(R.id.InputStep1Anniversaire);
        StringBuilder builder=new StringBuilder();
        builder.append((dp.getMonth() + 1)+"/");//month is 0 based
        builder.append(dp.getDayOfMonth()+"/");
        builder.append(dp.getYear());
        return builder.toString();
    }


    public String currentDate(){
        DatePicker dp = (DatePicker) findViewById(R.id.InputStep1Anniversaire);

        StringBuilder todaydate=new StringBuilder();
        Calendar today=Calendar.getInstance();
        int age=today.get(Calendar.YEAR)-dp.getYear();
        if (today.get(Calendar.MONTH) < dp.getYear()) {
            age--;
        } else if (today.get(Calendar.MONTH) == dp.getYear()
                && today.get(Calendar.DAY_OF_MONTH) < dp.getYear()) {
            age--;
        }
        todaydate.append(String.valueOf(age));
        return todaydate.toString();
    }

    public Boolean checkInputs()
    {
        EditText e1 = findViewById(R.id.InputStep1Nom);
        EditText e2 = findViewById(R.id.InputStep1Prenom);

        String nom = e1.getText().toString();
        String prenom = e2.getText().toString();

        if (nom.isEmpty()||prenom.isEmpty())
        {
            AlertDialog alertDialog = new AlertDialog.Builder(CreateProfileStep1Activity.this).create();
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
        else
            return true;
    }





}
