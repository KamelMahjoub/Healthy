package com.example.healthy.Activities;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.healthy.Classes.Account;
import com.example.healthy.Database.DatabaseHandler;
import com.example.healthy.R;

public class RegisterActivity extends AppCompatActivity {

    DatabaseHandler db = new DatabaseHandler(this);




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);




        Button b1 = findViewById(R.id.BtnRegisterCreateAccount);
        Button b2 = findViewById(R.id.BtnLoginLogin);

        //Btn create account
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                EditText e1 = findViewById(R.id.InputRegisterEmail);
                EditText e2 = findViewById(R.id.InputRegisterPassword);
                EditText e3 = findViewById(R.id.InputRegisterVerifyPassword);

                String Email = e1.getText().toString();
                String Password = e2.getText().toString();
                String Password2 = e3.getText().toString();

                if(checkInputs())
                {
                    if(checkEmail())
                    {
                        if(checkPasswords())
                        {
                            if(db.checkEmailExists(Email))
                            {
                                AlertDialog alertDialog = new AlertDialog.Builder(RegisterActivity.this).create();
                                alertDialog.setTitle("Erreur");
                                alertDialog.setMessage("Adresse email existe déja ! Veuiller s'authentifier si vous avez un compte");
                                alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "Ok",
                                        new DialogInterface.OnClickListener() {
                                            public void onClick(DialogInterface dialog, int which) {
                                                dialog.dismiss();
                                            }
                                        });
                                alertDialog.show();
                            }
                            else
                            {
                                Account compte = new Account();
                                compte.set_email(Email);
                                compte.set_password(Password);
                                db.addAccount(compte);
                                Toast.makeText(getApplicationContext(),"Le compte a été créer",Toast.LENGTH_SHORT).show();


                        }

                        }
                    }
                }
            }
        });

        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(view.getContext(), LoginActivity.class);
                view.getContext().startActivity(intent);}
        });
    }



    public Boolean checkPasswords()
    {
        EditText e2 = findViewById(R.id.InputRegisterPassword);
        EditText e3 = findViewById(R.id.InputRegisterVerifyPassword);

        String Password = e2.getText().toString();
        String Password2 = e3.getText().toString();

        if(Password.equals(Password2))
        {
            if((Password.length()<6))
             {
            AlertDialog alertDialog = new AlertDialog.Builder(RegisterActivity.this).create();
            alertDialog.setTitle("Erreur");
            alertDialog.setMessage("Le mot de passe doit avoir au moins 6 caractéres !");
            alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "Ok",
                    new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.dismiss();
                        }
                    });
            alertDialog.show();
            return false;
            }
            else if (Password.length() > 20)
                {
                    AlertDialog alertDialog = new AlertDialog.Builder(RegisterActivity.this).create();
                    alertDialog.setTitle("Erreur");
                    alertDialog.setMessage("Le mot de passe doit avoir au maximum 20 caractéres !");
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
        else
        {
            AlertDialog alertDialog = new AlertDialog.Builder(RegisterActivity.this).create();
            alertDialog.setTitle("Erreur");
            alertDialog.setMessage("les mots de passe doivent correspondre");
            alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "Ok",
                    new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.dismiss();
                        }
                    });
            alertDialog.show();
            return false;
        }
    }

    public Boolean checkInputs()
    {
        EditText e1 = findViewById(R.id.InputRegisterEmail);
        EditText e2 = findViewById(R.id.InputRegisterPassword);
        EditText e3 = findViewById(R.id.InputRegisterVerifyPassword);

        String Email = e1.getText().toString();
        String Password = e2.getText().toString();
        String Password2 = e3.getText().toString();

        if (Email.isEmpty()||Password.isEmpty()||Password2.isEmpty())
        {
            AlertDialog alertDialog = new AlertDialog.Builder(RegisterActivity.this).create();
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

    public Boolean checkEmail()
    {
        EditText emailValidate = (EditText)findViewById(R.id.InputRegisterEmail);
        String email = emailValidate.getText().toString().trim();
        String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";

        if (email.matches(emailPattern))
        {
            return true;
        }
        else
        {
            AlertDialog alertDialog = new AlertDialog.Builder(RegisterActivity.this).create();
            alertDialog.setTitle("Erreur");
            alertDialog.setMessage("Adresse email invalide ! ");
            alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "Ok",
                    new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.dismiss();
                        }
                    });
            alertDialog.show();
            return false;
        }
    }





}
