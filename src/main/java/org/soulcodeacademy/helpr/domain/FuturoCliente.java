package org.soulcodeacademy.helpr.domain;
import javax.persistence.*;

@Entity
public class FuturoCliente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idFuturoCliente;

    @Column(nullable = false, length = 150)
    public String nome;

    @Column(length = 25)
    public String telefone;

    @Column(nullable = false, unique = true, length = 120)
    public String email;

    @Column(nullable = false, unique = true, length = 11)
    public String cpf;

    public FuturoCliente (){ }

    public FuturoCliente(Integer idFuturoCliente, String nome, String telefone, String email, String cpf) {
        this.idFuturoCliente = idFuturoCliente;
        this.nome = nome;
        this.telefone = telefone;
        this.email = email;
        this.cpf = cpf;
    }

    public Integer getIdFuturoCliente() {
        return idFuturoCliente;
    }

    public void setIdFuturoCliente(Integer idFuturoCliente) {
        this.idFuturoCliente = idFuturoCliente;
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

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }
}
