package com.example.account.Request;

import java.util.Date;

public class AddAccountRequest {

    private Date fechaCreacion;

    private Date fechaUltimaModificacion;

    private int saldo;

    private int cliente;

    private Long tipoCuenta;

    public Date getFechaCreacion() {
        return fechaCreacion;
    }

    public void setFechaCreacion(Date fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }

    public Date getFechaUltimaModificacion() {
        return fechaUltimaModificacion;
    }

    public void setFechaUltimaModificacion(Date fechaUltimaModificacion) {
        this.fechaUltimaModificacion = fechaUltimaModificacion;
    }

    public int getSaldo() {
        return saldo;
    }

    public void setSaldo(int saldo) {
        this.saldo = saldo;
    }

    public int getCliente() {
        return cliente;
    }

    public void setCliente(int cliente) {
        this.cliente = cliente;
    }

    public Long getTipoCuenta() {
        return tipoCuenta;
    }

    public void setTipoCuenta(Long tipoCuenta) {
        this.tipoCuenta = tipoCuenta;
    }
}
