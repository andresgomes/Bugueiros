package br.com.coopbuggy.mcoopbuggy.javaclass;

import android.graphics.Bitmap;

import java.io.Serializable;

/**
 * Created by andre on 03/11/2017.
 */

public class Bugueiro implements Serializable{

    private int id;
    private String first_name;
    private String last_name;
    private String username;
    private String email;
    private String telefone;
    private int foto;
    private int permission;
    private String placaVeiculo;
    private String token;
    private int anoVeiculo;
    private Bitmap imagem;
    private String imagemSerializada = null;

    public Bugueiro (String first_name, String placa, int imagem){
        this.first_name = first_name;
        this.foto = imagem;
        this.placaVeiculo = placa;
    }

    public Bugueiro(){

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFirstName() {
        return first_name;
    }

    public void setFirstName(String nome) {
        this.first_name = nome;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getFoto() {
        return foto;
    }

    public void setFoto(int foto) {
        this.foto = foto;
    }

    public String getPlacaVeiculo() {
        return placaVeiculo;
    }

    public void setPlacaVeiculo(String placaVeiculo) {
        this.placaVeiculo = placaVeiculo;
    }

    public int getAnoVeiculo() {
        return anoVeiculo;
    }

    public void setAnoVeiculo(int anoVeiculo) {
        this.anoVeiculo = anoVeiculo;
    }

    public Bitmap getImagem() {
        return imagem;
    }

    public void setImagem(Bitmap imagem) {
        this.imagem = imagem;
    }

    public String getImagemSerializada() {
        return imagemSerializada;
    }

    public void setImagemSerializada(String imagemSerializada) {
        this.imagemSerializada = imagemSerializada;
    }

    public String getLast_name() {
        return last_name;
    }

    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public int getPermission() {
        return permission;
    }

    public void setPermission(int permission) {
        this.permission = permission;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    @Override
    public String toString() {
        return first_name;
    }
}
