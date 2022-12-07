package org.soulcodeacademy.helpr.domain;

import com.lowagie.text.*;
import com.lowagie.text.Font;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;

import javax.servlet.http.HttpServletResponse;
import java.awt.*;
import java.io.IOException;
import java.util.List;

public class ChamadosPDFExporter {
    private List<Chamado> listaChamados;

    public ChamadosPDFExporter(List<Chamado> listaChamados) {
        this.listaChamados = listaChamados;
    }

    private void writeTableHeader(PdfPTable table) {
        PdfPCell cell = new PdfPCell();
        cell.setBackgroundColor(Color.GRAY);
        cell.setPadding(4);

        com.lowagie.text.Font font = FontFactory.getFont(FontFactory.HELVETICA);
        font.setColor(Color.WHITE);

        cell.setPhrase(new Phrase("ID Chamado", font));
        table.addCell(cell);

        cell.setPhrase(new Phrase("Título", font));
        table.addCell(cell);

        cell.setPhrase(new Phrase("Descrição", font));
        table.addCell(cell);

        cell.setPhrase(new Phrase("Cliente", font));
        table.addCell(cell);

        cell.setPhrase(new Phrase("Funcionário", font));
        table.addCell(cell);

        cell.setPhrase(new Phrase("Data abertura", font));
        table.addCell(cell);

        cell.setPhrase(new Phrase("Data fechamento", font));
        table.addCell(cell);

        cell.setPhrase(new Phrase("Status", font));
        table.addCell(cell);
    }

    private void writeTableData(PdfPTable table) {
        for (Chamado chamado : listaChamados) {
            table.addCell(String.valueOf(chamado.getIdChamado()));
            table.addCell(chamado.getTitulo());
            table.addCell(chamado.getDescricao());
            table.addCell(chamado.getCliente().nome);
            if(chamado.getFuncionario() == null){
                table.addCell("Não determinado");
            } else {
                table.addCell(chamado.getFuncionario().nome);
            };
            if(chamado.getDataAbertura() == null){
                table.addCell("Não determinado");
            } else {
                table.addCell(chamado.getDataAbertura().toString());
            };
            if(chamado.getDataFechamento() == null){
                table.addCell("Não determinado");
            } else {
                table.addCell(chamado.getDataFechamento().toString());
            };
            table.addCell(String.valueOf(chamado.getStatus()));
        }
    }

    public void export(HttpServletResponse response) throws DocumentException, IOException {
        Document document = new Document(PageSize.A4);
        PdfWriter.getInstance(document, response.getOutputStream());

        document.open();
        Font font = FontFactory.getFont(FontFactory.HELVETICA_BOLD);
        font.setSize(12);
        font.setColor(Color.BLUE);

        Paragraph p = new Paragraph("Lista de chamados", font);
        p.setAlignment(Paragraph.ALIGN_CENTER);

        document.add(p);

        PdfPTable table = new PdfPTable(8);
        table.setWidthPercentage(110f);
        table.setWidths(new float[] {3.0f, 3.0f, 3.0f, 3.0f, 3.3f, 3.0f, 3.3f, 3.0f});
        table.setSpacingBefore(1);

        writeTableHeader(table);
        writeTableData(table);

        document.add(table);

        document.close();

    }
}