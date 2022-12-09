package org.soulcodeacademy.helpr.domain;
import org.soulcodeacademy.helpr.domain.enums.Perfil;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;

@Entity
public class Funcionario extends Usuario {

    @Lob
    private byte[] foto;

    @ManyToOne
    @JoinColumn(name = "id_cargo")
    private Cargo cargo;

    public Funcionario() {
    }

    public Funcionario(Integer id, String nome, String email, String cpf, String senha, byte[] foto, Cargo cargo) {
        super(id, nome, email, cpf, senha, Perfil.FUNCIONARIO);
        this.foto = foto;
        this.cargo = cargo;
    }

    public byte[] getFoto() {
        return foto;
    }

    public void setFoto(byte[] foto) {
        this.foto = foto;
    }

    public Cargo getCargo() {
        return cargo;
    }

    public void setCargo(Cargo cargo) {
        this.cargo = cargo;
    }
}
