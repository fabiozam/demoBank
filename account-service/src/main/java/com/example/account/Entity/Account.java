package com.example.account.Entity;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name="cuenta")
public class Account {

    @Id
    @Column(name="numero_cuenta")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long numeroCuenta;

    @Column(name="fecha_creacion")
    private Date fechaCreacion;

    @Column(name="fecha_ultima_modificacion")
    private Date fechaUltimaModificacion;

    @Column(name="saldo")
    private int saldo;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "cliente", referencedColumnName = "cedula")
    private Client cliente;

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "tipo_cuenta", referencedColumnName = "id_tipo_cuenta")
    private AccountType accountType;

    public Long getNumeroCuenta() {
        return numeroCuenta;
    }

    public void setNumeroCuenta(Long numeroCuenta) {
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

    public Client getCliente() {
        return cliente;
    }

    public void setCliente(Client cliente) {
        this.cliente = cliente;
    }

    public AccountType getTipoCuenta() {
        return accountType;
    }

    public void setTipoCuenta(AccountType accountType) {
        this.accountType = accountType;
    }
}
