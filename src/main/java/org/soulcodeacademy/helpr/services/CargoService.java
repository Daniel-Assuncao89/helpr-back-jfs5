package org.soulcodeacademy.helpr.services;
import org.soulcodeacademy.helpr.domain.Cargo;
import org.soulcodeacademy.helpr.domain.dto.CargoDTO;
import org.soulcodeacademy.helpr.repositories.CargoRepository;
import org.soulcodeacademy.helpr.services.errors.RecursoNaoEncontradoError;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class CargoService {
    @Autowired
    private CargoRepository cargoRepository;


    public List<Cargo> listar() {

        return this.cargoRepository.findAll();
    }


    public Cargo getCargo(Integer idCargo) {
        return this.cargoRepository.findById(idCargo)
                .orElseThrow(() -> new RecursoNaoEncontradoError("Cargo não encontrado."));
    }

    public Cargo salvar(CargoDTO dto) {

        Cargo cargo = new Cargo(null, dto.getNome(), dto.getDescricao(), dto.getSalario());
        Cargo cargoSalvo = this.cargoRepository.save(cargo);
        return cargoSalvo;
    }

    public Cargo atualizar(Integer idCargo, CargoDTO dto) {

        Cargo cargoAtual = this.getCargo(idCargo);

        cargoAtual.setNome(dto.getNome());
        cargoAtual.setDescricao(dto.getDescricao());
        cargoAtual.setSalario(dto.getSalario());

        Cargo atualizado = this.cargoRepository.save(cargoAtual);
        return atualizado;
    }

    public void deletar(Integer idCargo) {
        Cargo cargo = this.getCargo(idCargo);

        this.cargoRepository.delete(cargo);
    }
}
