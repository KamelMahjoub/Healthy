package com.example.healthy.Classes;

public class Account {

    int _id ;
    String _email ;
    String _password ;

    public Account(int _id, String _email, String _password) {
        this._id = _id;
        this._email = _email;
        this._password = _password;
    }

    public Account() {
    }


    public int get_id() {
        return _id;
    }

    public void set_id(int _id) {
        this._id = _id;
    }

    public String get_email() {
        return _email;
    }

    public void set_email(String _email) {
        this._email = _email;
    }

    public String get_password() {
        return _password;
    }

    public void set_password(String _password) {
        this._password = _password;
    }
}
