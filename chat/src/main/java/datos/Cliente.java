/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package datos;

import java.io.Serializable;

/**
 *
 * @author edgargarcia
 */
public class Cliente implements Serializable {

    private String mensaje;
    private String usuarioOrigen;
    private String usuarioDestino;

    public Cliente(String mensaje, String usuarioOrigen, String usuarioDestino) {
        this.mensaje = mensaje;
        this.usuarioOrigen = usuarioOrigen;
        this.usuarioDestino = usuarioDestino;

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
