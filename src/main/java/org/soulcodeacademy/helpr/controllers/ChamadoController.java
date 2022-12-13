package org.soulcodeacademy.helpr.controllers;

import com.lowagie.text.DocumentException;
import org.soulcodeacademy.helpr.domain.Chamado;
import org.soulcodeacademy.helpr.domain.ChamadosPDFExporter;
import org.soulcodeacademy.helpr.domain.dto.ChamadoDTO;
import org.soulcodeacademy.helpr.domain.enums.StatusChamado;
import org.soulcodeacademy.helpr.services.ChamadoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;

@RestController
public class ChamadoController {
    @Autowired
    private ChamadoService chamadoService;

    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_FUNCIONARIO')")
    @GetMapping("/chamados")
    public List<Chamado> listarChamados() {
        return this.chamadoService.listarChamados();
    }

    @GetMapping("/chamados/{idChamado}")
    public Chamado getChamado(@PathVariable Integer idChamado) {
        return this.chamadoService.getChamado(idChamado);
    }

    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_FUNCIONARIO', ROLE_CLIENTE)")
    @PostMapping("/chamados")
    public Chamado salvar(@Valid @RequestBody ChamadoDTO dto) {
        return this.chamadoService.salvar(dto);
    }

    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_FUNCIONARIO')")
    @PutMapping("/chamados/{idChamado}")
    public Chamado atualizar(@PathVariable Integer idChamado, @Valid @RequestBody ChamadoDTO dto) {
        return this.chamadoService.atualizar(idChamado, dto);
    }


    @GetMapping("/chamados/clientes/{idCliente}")
    public List<Chamado> listarPorCliente(@PathVariable Integer idCliente) {
        return this.chamadoService.listarPorCliente(idCliente);
    }


    @GetMapping("/chamados/funcionarios/{idFuncionario}")
    public List<Chamado> listarPorFuncionario(@PathVariable Integer idFuncionario) {
        return this.chamadoService.listarPorFuncionario(idFuncionario);
    }



    @GetMapping("/soma")
    public Integer soma(@RequestParam Integer numero1, @RequestParam Integer numero2) {
        return numero1 + numero2;
    }


    @GetMapping("/chamados/status")
    public List<Chamado> listarPorStatus(@RequestParam StatusChamado batata) {
        return this.chamadoService.listarPorStatus(batata);
    }


    @GetMapping("/chamados/intervalo")
    public List<Chamado> listarPorIntervaloDatas(
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate inicio,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate fim
    ) {
        return this.chamadoService.listarPorIntervaloDatas(inicio, fim);
    }

    @GetMapping("/chamados/export/pdf")
    public void exportToPDF(HttpServletResponse response) throws DocumentException, IOException {
        response.setContentType("application/pdf");
        DateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd_HH:mm:ss");
        String currentDateTime = dateFormatter.format(new Date());

        String headerKey = "Content-Disposition";
        String headerValue = "attachment; filename=users_" + currentDateTime + ".pdf";
        response.setHeader(headerKey, headerValue);

        List<Chamado> listaChamados = this.chamadoService.listarChamados();

        ChamadosPDFExporter exporter = new ChamadosPDFExporter(listaChamados);
        exporter.export(response);

    }


}
