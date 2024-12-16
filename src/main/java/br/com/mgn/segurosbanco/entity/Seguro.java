package br.com.mgn.segurosbanco.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

@Entity
public class Seguro {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID uuid;
    private String cpfCliente;
    private TipoSeguro tipoSeguro; // BRONZE, PRATA, OURO
    private BigDecimal valorContratado;
    private LocalDate dataContratacao;

    public Seguro() {}

    public Seguro(UUID uuid, String cpfCliente, TipoSeguro tipoSeguro, BigDecimal valorContratado, LocalDate dataContratacao) {
        this.uuid = uuid;
        this.cpfCliente = cpfCliente;
        this.tipoSeguro = tipoSeguro;
        this.valorContratado = valorContratado;
        this.dataContratacao = dataContratacao;
    }

    public UUID getUuid() {
        return uuid;
    }

    public void setUuid(UUID uuid) {
        this.uuid = uuid;
    }

    public String getCpfCliente() {
        return cpfCliente;
    }

    public void setCpfCliente(String cpfCliente) {
        this.cpfCliente = cpfCliente;
    }

    public TipoSeguro getTipoSeguro() {
        return tipoSeguro;
    }

    public void setTipoSeguro(TipoSeguro tipoSeguro) {
        this.tipoSeguro = tipoSeguro;
    }

    public BigDecimal getValorContratado() {
        return valorContratado;
    }

    public void setValorContratado(BigDecimal valorContratado) {
        this.valorContratado = valorContratado;
    }

    public LocalDate getDataContratacao() {
        return dataContratacao;
    }

    public void setDataContratacao(LocalDate dataContratacao) {
        this.dataContratacao = dataContratacao;
    }
}

