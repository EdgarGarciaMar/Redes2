/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package info;

import java.io.Serializable;

/**
 *
 * @author edgargarcia
 */
public class mensajeDeUsuario implements Serializable {

    private String mensaje;
    private String usuarioOrigen;
    private String usuarioDestino;
    private int tipo;
    private int img;

    public mensajeDeUsuario (String mensaje, String usuarioOrigen, String usuarioDestino,int tipo, int img) {
        this.mensaje = mensaje;
        this.usuarioOrigen = usuarioOrigen;
        this.usuarioDestino = usuarioDestino;
        this.tipo=tipo;
        this.img=img;

    }

    public int getImg() {
        return img;
    }

    public void setImg(int img) {
        this.img = img;
    }

    public int getTipo() {
        return tipo;
    }

    public void setTipo(int tipo) {
        this.tipo = tipo;
    }

    public String getMensaje() {
        return mensaje;
    }

    public String getUsuarioOrigen() {
        return usuarioOrigen;
    }

    public String getUsuarioDestino() {
        return usuarioDestino;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }

    public void setUsuarioOrigen(String usuarioOrigen) {
        this.usuarioOrigen = usuarioOrigen;
    }

    public void setUsuarioDestino(String usuarioDestino) {
        this.usuarioDestino = usuarioDestino;
    }

}
