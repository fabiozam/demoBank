package com.example.transactions.Entity;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "transacciones")
public class Transaction {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "cuenta_origen")
    private int cuentaOrigen;

    @Column(name = "cuenta_destino")
    private int cuentaDestino;

    @Column(name = "monto")
    private int monto;

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "tipo_transaccion", referencedColumnName = "id")
    private TransactionType tipoTransaccion;

    @Column(name = "fecha")
    private Date fechaTransaccion;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getCuentaOrigen() {
        return cuentaOrigen;
    }

    public void setCuentaOrigen(int cuentaOrigen) {
        this.cuentaOrigen = cuentaOrigen;
    }

    public int getCuentaDestino() {
        return cuentaDestino;
    }

    public void setCuentaDestino(int cuentaDestino) {
        this.cuentaDestino = cuentaDestino;
    }

    public int getMonto() {
        return monto;
    }

    public void setMonto(int monto) {
        this.monto = monto;
    }

    public TransactionType getTipoTransaccion() {
        return tipoTransaccion;
    }

    public void setTipoTransaccion(TransactionType tipoTransaccion) {
        this.tipoTransaccion = tipoTransaccion;
    }

    public Date getFechaTransaccion() {
        return fechaTransaccion;
    }

    public void setFechaTransaccion(Date fechaTransaccion) {
        this.fechaTransaccion = fechaTransaccion;
    }
}
