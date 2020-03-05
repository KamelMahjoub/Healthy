package com.example.healthy.Classes;

import java.util.Date;

public class Profile {

    int _id ;
    String _nom ;
    String _prénom ;
    String _anniversaire ;
    int _age ;
    String _sexe;
    int _poids ;
    int _taille ;
    double _imc ;

    public Profile() {
    }

    public Profile(int _id, String _nom, String _prénom, String _anniversaire, int _age, String _sexe, int _poids, int _taille, double _imc) {
        this._id = _id;
        this._nom = _nom;
        this._prénom = _prénom;
        this._anniversaire = _anniversaire;
        this._age = _age;
        this._sexe = _sexe;
        this._poids = _poids;
        this._taille = _taille;
        this._imc = _imc;
    }

    public int get_id() {
        return _id;
    }

    public void set_id(int _id) {
        this._id = _id;
    }

    public String get_nom() {
        return _nom;
    }

    public void set_nom(String _nom) {
        this._nom = _nom;
    }

    public String get_prénom() {
        return _prénom;
    }

    public void set_prénom(String _prénom) {
        this._prénom = _prénom;
    }

    public String get_anniversaire() {
        return _anniversaire;
    }

    public void set_anniversaire(String _anniversaire) {
        this._anniversaire = _anniversaire;
    }

    public int get_age() {
        return _age;
    }

    public void set_age(int _age) {
        this._age = _age;
    }

    public String get_sexe() {
        return _sexe;
    }

    public void set_sexe(String _sexe) {
        this._sexe = _sexe;
    }

    public int get_poids() {
        return _poids;
    }

    public void set_poids(int _poids) {
        this._poids = _poids;
    }

    public int get_taille() {
        return _taille;
    }

    public void set_taille(int _taille) {
        this._taille = _taille;
    }

    public double get_imc() {
        return _imc;
    }

    public void set_imc(double _imc) {
        this._imc = _imc;
    }
}
