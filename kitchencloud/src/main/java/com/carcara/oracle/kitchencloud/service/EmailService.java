package com.carcara.oracle.kitchencloud.service;


import com.carcara.oracle.kitchencloud.config.JSONToPDF;
import com.carcara.oracle.kitchencloud.model.EnvioEmail;
import com.carcara.oracle.kitchencloud.model.RankVendaProduto;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.MimeMessageHelper;

import java.io.IOException;
import java.util.List;

@Service
public class EmailService {

    @Autowired
    private RestTemplateEmailService restTemplateEmailService;

    @Autowired
    private RankVendaProdutoService rankVendaProdutoService;

    @Autowired
    private JSONToPDF jsonToPDF;

    private final JavaMailSender javaMailSender;


    public EmailService(final JavaMailSender javaMailSender) {
        this.javaMailSender = javaMailSender;
    }


    public void rankPratosDiarios(EnvioEmail envioEmail) throws IOException {
        InformacoesMes informacoesMes = new InformacoesMes();
        List<RankVendaProduto> rankVendaProdutos =
                rankVendaProdutoService.rankVendaProdutos(informacoesMes.getNumeroDoDia(),
                        informacoesMes.getPrimeiroDiaDoMes().toString(),informacoesMes.getUltimoDiaDoMes().toString());

        jsonToPDF.criarPdf(informacoesMes.getNumeroDoDia(),
                informacoesMes.getPrimeiroDiaDoMes().toString(),informacoesMes.getUltimoDiaDoMes().toString());
        // Inicialize um StringBuilder para construir a tabela HTML
        StringBuilder tableHtml = new StringBuilder();

        // Adicione a tag de abertura da tabela
        tableHtml.append("<table border=\"1\">");

        // Adicione o cabeçalho da tabela
        // Adicione o cabeçalho da tabela com estilos CSS
        tableHtml.append("<tr><th style=\"background-color:#f2f2f2; font-size:16px;\">Código do Prato</th><th style=\"background-color:#f2f2f2; font-size:16px;\">Nome do Prato</th><th style=\"background-color:#f2f2f2; font-size:16px;\">Dia da Semana</th><th style=\"background-color:#f2f2f2; font-size:16px;\">Quantidade Total</th><th style=\"background-color:#f2f2f2; font-size:16px;\">Valor Total do Produto (em R$)</th><th style=\"background-color:#f2f2f2; font-size:16px;\">Impacto (%)</th></tr>");

// Adicione estilo às células da tabela
        for (RankVendaProduto produto : rankVendaProdutos) {
            tableHtml.append("<tr>");
            tableHtml.append("<td style=\"border: 1px solid #ccc; padding: 8px;\">").append(produto.getCodPrato()).append("</td>");
            tableHtml.append("<td style=\"border: 1px solid #ccc; padding: 8px;\">").append(produto.getNomePrato()).append("</td>");
            tableHtml.append("<td style=\"border: 1px solid #ccc; padding: 8px;\">").append(produto.getDiaDaSemana()).append("</td>");
            tableHtml.append("<td style=\"border: 1px solid #ccc; padding: 8px;\">").append(produto.getQuantidadeTotal()).append("</td>");
            tableHtml.append("<td style=\"border: 1px solid #ccc; padding: 8px;\">").append(produto.getValorTotalProduto()).append("</td>");
            tableHtml.append("<td style=\"border: 1px solid #ccc; padding: 8px;\">").append(produto.getImpactoPorcentagem()).append("%</td>");
            tableHtml.append("</tr>");
        }


        // Adicione a tag de fechamento da tabela
        tableHtml.append("</table>");

        // A string `tableHtml` agora contém a tabela HTML
        String tabelaHTML = tableHtml.toString();

        envioEmail.setConteudo(tabelaHTML);

        restTemplateEmailService.enviarPost(envioEmail);
    }


    public static List<RankVendaProduto> conteudoToRankVenda (String rankVendaProduto){
        Gson gson = new Gson();
        List<RankVendaProduto> lista = gson.fromJson(rankVendaProduto, new TypeToken<List<RankVendaProduto>>() {}.getType());
        return lista;
    }

    public void enviar(String para, String titulo, String conteudo) throws MessagingException {
        if(conteudo.equals("abaixo")){
            conteudo = codigoAbaixo();
        }else if(conteudo.equals("acima")){
            conteudo = codigoAcima();
        }else {
            conteudo = codigoNaoBuild();
        }

        MimeMessage mimeMessage = javaMailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, "utf-8");
        helper.setText(conteudo, true);
        helper.setSubject(titulo);
        helper.setTo(para);

        var mensagem = new SimpleMailMessage();
        mensagem.setTo(para);

        mensagem.setSubject(titulo);
        mensagem.setText(conteudo);
        javaMailSender.send(mimeMessage);
    }

    public String codigoAbaixo (){
        return "<!DOCTYPE html>\n" +
                "<html lang=\"en\">\n" +
                "\n" +
                "<head>\n" +
                "    <meta charset=\"UTF-8\">\n" +
                "    <title>Notificação de Cobertura de Código</title>\n" +
                "</head>\n" +
                "\n" +
                "<body style=\"font-family: Arial, sans-serif; line-height: 1.6; background-color: #f4f4f4; margin: 0; padding: 0;\">\n" +
                "    <table align=\"center\" cellpadding=\"20\" style=\"max-width: 600px; background-color: #fff; border-radius: 8px; box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);\">\n" +
                "        <tr>\n" +
                "            <td>\n" +
                "                <h1 style=\"color: #333; margin-top: 0;\">Notificação de Cobertura de Código</h1>\n" +
                "                <p style=\"color: #555;\">Caro desenvolvedor,</p>\n" +
                "                <p style=\"color: #555;\">O código submetido não atingiu o nível de cobertura desejado. É fundamental que a cobertura de código seja ampliada para garantir a qualidade e confiabilidade do software.</p>\n" +
                "                <p style=\"color: #555;\">Por favor, revise o código e atualize os testes necessários para alcançar a cobertura exigida.</p>\n" +
                "                <a href=\"link_para_documentacao_ou_detalhes\" style=\"display: inline-block; font-size: 16px; padding: 10px 20px; text-decoration: none; background-color: #007bff; color: #fff; border-radius: 5px; margin-top: 20px;\">Mais Detalhes</a>\n" +
                "                <footer style=\"margin-top: 30px; text-align: center; color: #888;\">\n" +
                "                    <p>Este é um email automático, por favor não responda.</p>\n" +
                "                </footer>\n" +
                "            </td>\n" +
                "        </tr>\n" +
                "    </table>\n" +
                "</body>\n" +
                "\n" +
                "</html>\n";
    }
    public String codigoAcima (){
        return "<!DOCTYPE html>\n" +
                "<html lang=\"en\">\n" +
                "\n" +
                "<head>\n" +
                "    <meta charset=\"UTF-8\">\n" +
                "    <title>Notificação de Cobertura de Código</title>\n" +
                "</head>\n" +
                "\n" +
                "<body style=\"font-family: Arial, sans-serif; line-height: 1.6; background-color: #f4f4f4; margin: 0; padding: 0;\">\n" +
                "    <table align=\"center\" cellpadding=\"20\" style=\"max-width: 600px; background-color: #fff; border-radius: 8px; box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);\">\n" +
                "        <tr>\n" +
                "            <td>\n" +
                "                <h1 style=\"color: #333; margin-top: 0;\">Notificação de Cobertura de Código</h1>\n" +
                "                <p style=\"color: #555;\">Caro desenvolvedor,</p>\n" +
                "                <p style=\"color: #555;\">O código submetido atingiu o nível de cobertura desejado na atualização X. Parabéns pelo trabalho!</p>\n" +
                "                <p style=\"color: #555;\">A cobertura de código adequada é essencial para a qualidade e confiabilidade do software.</p>\n" +
                "                <a href=\"link_para_documentacao_ou_detalhes\" style=\"display: inline-block; font-size: 16px; padding: 10px 20px; text-decoration: none; background-color: #007bff; color: #fff; border-radius: 5px; margin-top: 20px;\">Mais Detalhes</a>\n" +
                "                <footer style=\"margin-top: 30px; text-align: center; color: #888;\">\n" +
                "                    <p>Este é um email automático, por favor não responda.</p>\n" +
                "                </footer>\n" +
                "            </td>\n" +
                "        </tr>\n" +
                "    </table>\n" +
                "</body>\n" +
                "\n" +
                "</html>\n";
    }

    public String codigoNaoBuild(){
        return "<!DOCTYPE html>\n" +
                "<html lang=\"en\">\n" +
                "\n" +
                "<head>\n" +
                "    <meta charset=\"UTF-8\">\n" +
                "    <title>Notificação de Build do Código</title>\n" +
                "</head>\n" +
                "\n" +
                "<body style=\"font-family: Arial, sans-serif; line-height: 1.6; background-color: #f4f4f4; margin: 0; padding: 0;\">\n" +
                "    <table align=\"center\" cellpadding=\"20\" style=\"max-width: 600px; background-color: #fff; border-radius: 8px; box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);\">\n" +
                "        <tr>\n" +
                "            <td>\n" +
                "                <h1 style=\"color: #333; margin-top: 0;\">Notificação de Build do Código</h1>\n" +
                "                <p style=\"color: #555;\">Caro desenvolvedor,</p>\n" +
                "                <p style=\"color: #555;\">O processo de build do código falhou na atualização X. É importante resolver essa questão para garantir a estabilidade e funcionalidade do software.</p>\n" +
                "                <p style=\"color: #555;\">Por favor, verifique e corrija os problemas encontrados no processo de build o mais rápido possível.</p>\n" +
                "                <a href=\"link_para_documentacao_ou_detalhes\" style=\"display: inline-block; font-size: 16px; padding: 10px 20px; text-decoration: none; background-color: #007bff; color: #fff; border-radius: 5px; margin-top: 20px;\">Mais Detalhes</a>\n" +
                "                <footer style=\"margin-top: 30px; text-align: center; color: #888;\">\n" +
                "                    <p>Este é um email automático, por favor não responda.</p>\n" +
                "                </footer>\n" +
                "            </td>\n" +
                "        </tr>\n" +
                "    </table>\n" +
                "</body>\n" +
                "\n" +
                "</html>\n";
    }

}
