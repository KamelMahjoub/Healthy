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

public class LoginActivity extends AppCompatActivity {
    DatabaseHandler db = new DatabaseHandler(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        Button b1 = findViewById(R.id.BtnLoginLogin);
        Button b2 = findViewById(R.id.BtnLoginRegister);

        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                EditText e1 = findViewById(R.id.InputLoginEmail);
                EditText e2 = findViewById(R.id.InputLoginPassword);
                String Email = e1.getText().toString();
                String Password = e2.getText().toString();

                if(checkInputs())
                {
                    if(checkEmail())
                    {
                        if(checkPassword())
                        {
                            if(db.checkEmailExists(Email))
                            {
                                if(db.checkUserAccount(Email,Password))
                                {
                                    Account compte = db.getAccount(Email);
                                    Toast.makeText(getApplicationContext(),compte.get_id()+" "+compte.get_email()+" "+compte.get_password(),Toast.LENGTH_SHORT).show();
                                    Intent intent = new Intent(view.getContext(), CreateProfileStep1Activity.class);
                                    view.getContext().startActivity(intent);
                                }
                                else
                                {
                                    AlertDialog alertDialog = new AlertDialog.Builder(LoginActivity.this).create();
                                    alertDialog.setTitle("Erreur");
                                    alertDialog.setMessage("Vérifier le mot de passe s'il vous plait !");
                                    alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "Ok",
                                            new DialogInterface.OnClickListener() {
                                                public void onClick(DialogInterface dialog, int which) {
                                                    dialog.dismiss();
                                                }
                                            });
                                    alertDialog.show();
                                }

                            }
                            else
                            {
                                AlertDialog alertDialog = new AlertDialog.Builder(LoginActivity.this).create();
                                alertDialog.setTitle("Erreur");
                                alertDialog.setMessage("On n'a pas trouvé un compte associé a cetter adresse email !");
                                alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "Ok",
                                        new DialogInterface.OnClickListener() {
                                            public void onClick(DialogInterface dialog, int which) {
                                                dialog.dismiss();
                                            }
                                        });
                                alertDialog.show();
                            }

                        }
                    }
                }
            }
        });

        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(view.getContext(), RegisterActivity.class);
                view.getContext().startActivity(intent);}
        });

    }


    public Boolean checkPassword()
    {
        EditText e2 = findViewById(R.id.InputLoginPassword);

        String Password = e2.getText().toString();
            if((Password.length()<6))
            {
                AlertDialog alertDialog = new AlertDialog.Builder(LoginActivity.this).create();
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
                AlertDialog alertDialog = new AlertDialog.Builder(LoginActivity.this).create();
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

    public Boolean checkInputs()
    {
        EditText e1 = findViewById(R.id.InputLoginEmail);
        EditText e2 = findViewById(R.id.InputLoginPassword);

        String Email = e1.getText().toString();
        String Password = e2.getText().toString();

        if (Email.isEmpty()||Password.isEmpty())
        {
            AlertDialog alertDialog = new AlertDialog.Builder(LoginActivity.this).create();
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
        EditText emailValidate = (EditText)findViewById(R.id.InputLoginEmail);
        String email = emailValidate.getText().toString().trim();
        String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";

        if (email.matches(emailPattern))
        {
            return true;
        }
        else
        {
            AlertDialog alertDialog = new AlertDialog.Builder(LoginActivity.this).create();
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




