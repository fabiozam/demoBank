package com.example.transactions.Response;

import java.util.Date;

public class AccountResponse {

    private int numeroCuenta;

    private Date fechaCreacion;

    private Date fechaUltimaModificacion;

    private int saldo;

    private ClientResponse cliente;

    private AccountTypeResponse tipoCuenta;

    public int getNumeroCuenta() {
        return numeroCuenta;
    }

    public void setNumeroCuenta(int numeroCuenta) {
        this.numeroCuenta = numeroCuenta;
    }

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

    public ClientResponse getCliente() {
        return cliente;
    }

    public void setCliente(ClientResponse cliente) {
        this.cliente = cliente;
    }

    public AccountTypeResponse getTipoCuenta() {
        return tipoCuenta;
    }

    public void setTipoCuenta(AccountTypeResponse tipoCuenta) {
        this.tipoCuenta = tipoCuenta;
    }
}
