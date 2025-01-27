package com.example.transactions.Response;

import com.example.transactions.Entity.TransactionType;

import java.util.Date;

public class TransactionResponse {

    private Long id;

    private AccountResponse cuentaOrigen;

    private AccountResponse cuentaDestino;

    private int monto;

    private TransactionTypeResponse tipoTransaccion;

    private Date fechaTransaccion;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public AccountResponse getCuentaOrigen() {
        return cuentaOrigen;
    }

    public void setCuentaOrigen(AccountResponse cuentaOrigen) {
        this.cuentaOrigen = cuentaOrigen;
    }

    public AccountResponse getCuentaDestino() {
        return cuentaDestino;
    }

    public void setCuentaDestino(AccountResponse cuentaDestino) {
        this.cuentaDestino = cuentaDestino;
    }

    public int getMonto() {
        return monto;
    }

    public void setMonto(int monto) {
        this.monto = monto;
    }

    public TransactionTypeResponse getTipoTransaccion() {
        return tipoTransaccion;
    }

    public void setTipoTransaccion(TransactionTypeResponse tipoTransaccion) {
        this.tipoTransaccion = tipoTransaccion;
    }

    public Date getFechaTransaccion() {
        return fechaTransaccion;
    }

    public void setFechaTransaccion(Date fechaTransaccion) {
        this.fechaTransaccion = fechaTransaccion;
    }
}
