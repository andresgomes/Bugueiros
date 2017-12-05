package br.com.coopbuggy.mcoopbuggy.javaclass;

import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.media.Image;

import java.io.Serializable;

/**
 * Created by andre on 03/11/2017.
 */

public class Bugueiro implements Serializable{

    private int id;
    private String nome;
    private String telefone;
    private String email;
    private int foto;
    private String placaVeiculo;
    private int anoVeiculo;
    private Bitmap imagem;
    private String imagemSerializada = null;

    public Bugueiro (String nome, String placa, int imagem){
        this.nome = nome;
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

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
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

    @Override
    public String toString() {
        return nome;
    }
}
