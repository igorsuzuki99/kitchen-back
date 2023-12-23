package com.carcara.oracle.kitchencloud.config;

import com.carcara.oracle.kitchencloud.model.ViewVendas;
import com.carcara.oracle.kitchencloud.repository.ViewVendasRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.json.JsonMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.swing.text.View;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class JSONToPDF {

    @Autowired
    private ViewVendasRepository viewVendasRepository;

    public static JSONArray convertViewVendasListToJsonArray(List<ViewVendas> vendas) {
        JSONArray jsonArray = new JSONArray();
        for (ViewVendas venda : vendas) {
            JSONObject a = new JSONObject(venda);
            jsonArray.put(new JSONObject(venda));
        }
        return jsonArray;
    }
    public void criarPdf (Integer diaSemana, String data1, String data2){
        List<ViewVendas> vendas = viewVendasRepository.vendasPorDataEdia(diaSemana, data1, data2);


        try {
            // Parse o JSON para uma lista de objetos
            JSONArray jsonArray = new JSONArray(convertViewVendasListToJsonArray(vendas));

            // Crie um novo documento PDF
            Document document = new Document(PageSize.A4, 50, 50, 50, 50);
            PdfWriter.getInstance(document, new FileOutputStream("output.pdf"));
            document.open();

            // Adicione um título
            Font titleFont = FontFactory.getFont(FontFactory.HELVETICA_BOLD, 18, BaseColor.BLACK);
            Paragraph title = new Paragraph("Relatório de Vendas", titleFont);
            title.setAlignment(Element.ALIGN_CENTER);
            document.add(title);

            // Adicione uma descrição
            Font descriptionFont = FontFactory.getFont(FontFactory.HELVETICA, 12, BaseColor.DARK_GRAY);
            Paragraph description = new Paragraph("Este relatório contém informações sobre as vendas de pratos.", descriptionFont);
            description.setAlignment(Element.ALIGN_CENTER);
            document.add(description);

            // Adicione uma tabela para os dados
            PdfPTable table = new PdfPTable(7); // 7 colunas para os campos no JSON
            table.setWidthPercentage(100);

            // Defina o cabeçalho da tabela
            Font headerFont = FontFactory.getFont(FontFactory.HELVETICA_BOLD, 12, BaseColor.WHITE);
            PdfPCell headerCell;
            headerCell = new PdfPCell(new Phrase("Cod Item Venda", headerFont));
            headerCell.setBackgroundColor(BaseColor.GRAY);
            table.addCell(headerCell);
            headerCell = new PdfPCell(new Phrase("Cod Venda", headerFont));
            headerCell.setBackgroundColor(BaseColor.GRAY);
            table.addCell(headerCell);
            headerCell = new PdfPCell(new Phrase("Cod Prato", headerFont));
            headerCell.setBackgroundColor(BaseColor.GRAY);
            table.addCell(headerCell);
            headerCell = new PdfPCell(new Phrase("Quantidade", headerFont));
            headerCell.setBackgroundColor(BaseColor.GRAY);
            table.addCell(headerCell);
            headerCell = new PdfPCell(new Phrase("Valor Item", headerFont));
            headerCell.setBackgroundColor(BaseColor.GRAY);
            table.addCell(headerCell);
            headerCell = new PdfPCell(new Phrase("Data Venda", headerFont));
            headerCell.setBackgroundColor(BaseColor.GRAY);
            table.addCell(headerCell);
            headerCell = new PdfPCell(new Phrase("Nome Prato", headerFont));
            headerCell.setBackgroundColor(BaseColor.GRAY);
            table.addCell(headerCell);

            // Preencha a tabela com os dados do JSON
            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject item = jsonArray.getJSONObject(i);
                table.addCell(item.getInt("codItemVenda") + "");
                table.addCell(item.getInt("codVenda") + "");
                table.addCell(item.getInt("codPrato") + "");
                table.addCell(item.getInt("quantidade") + "");
                table.addCell(item.getDouble("valorItem") + "");
                table.addCell(formatDate(item.getString("dataVenda")));
                table.addCell(item.getString("nomePrato"));
            }

            // Adicione a tabela ao documento PDF
            document.add(table);

            // Calcule e insira algumas estatísticas (por exemplo, total e média)
            double totalValorTotal = 0;
            int totalQuantidade = 0;

            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject item = jsonArray.getJSONObject(i);
                totalValorTotal += item.getDouble("valorTotal");
                totalQuantidade += item.getInt("quantidade");
            }

            Font statFont = FontFactory.getFont(FontFactory.HELVETICA_BOLD, 14, BaseColor.BLACK);
            Paragraph stats = new Paragraph("Estatísticas:", statFont);
            stats.add(new Chunk("\nTotal Valor Total: " + totalValorTotal));
            stats.add(new Chunk("\nTotal Quantidade: " + totalQuantidade));
            document.add(stats);

            // Feche o documento
            document.close();

            System.out.println("PDF gerado com sucesso.");

        } catch (IOException | DocumentException e) {
            e.printStackTrace();
        }
    }

    private static String formatDate(String dateString) {
        try {
            SimpleDateFormat inputFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm");
            SimpleDateFormat outputFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm");
            Date date = inputFormat.parse(dateString);
            return outputFormat.format(date);
        } catch (ParseException e) {
            e.printStackTrace();
            return ""; // Trate o erro de parsing adequadamente, se necessário
        }
    }

}
