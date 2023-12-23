package com.carcara.oracle.kitchencloud.service;

import com.carcara.oracle.kitchencloud.model.EnvioEmail;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.net.URI;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@Service
public class RestTemplateEmailService {

    private final String url = "https://hook.us1.make.com/r63hqv4g54v923bcq44qm16vbbbtdh49";

    private final RestTemplate restTemplate;

    @Autowired
    public RestTemplateEmailService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public ResponseEntity<String> enviarPost(EnvioEmail envioEmail) throws IOException {
        // Crie o cabeçalho da solicitação
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.MULTIPART_FORM_DATA);

        // Carregue o arquivo PDF
        Path pdfPath = Paths.get("D:/Fatec Projects/Cloud-Kitchen_Back/kitchencloud/output.pdf");
        byte[] pdfBytes = Files.readAllBytes(pdfPath);
        ByteArrayResource pdfResource = new ByteArrayResource(pdfBytes) {
            @Override
            public String getFilename() {
                return "output.pdf"; // Nome do arquivo que será usado no servidor
            }
        };

        // Crie o corpo da solicitação com o arquivo PDF como anexo
        MultiValueMap<String, Object> body = new LinkedMultiValueMap<>();
        body.add("pdfAnexo", pdfResource);

        // Adicione outras partes do formulário, como o objeto EnvioEmail
        body.add("para", envioEmail.getPara());
        body.add("assunto", envioEmail.getAssunto());
        body.add("conteudo", envioEmail.getConteudo());

        // Crie a solicitação HTTP com o corpo multipart
        HttpEntity<MultiValueMap<String, Object>> requestEntity = new HttpEntity<>(body, headers);

        // Faça a solicitação POST
        ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.POST, requestEntity, String.class);

        return response;
    }

    public void enviarEmailSimples(EnvioEmail envioEmail) {
        // Crie um objeto RestTemplate
        RestTemplate restTemplate = new RestTemplate();

        // URL do endpoint onde você deseja fazer a solicitação POST
        String postUrl = "https://hook.us1.make.com/s5rrbn3trol1t5823eipjmbwfvcn5im6";

        // Crie os cabeçalhos da solicitação, incluindo o tipo de mídia
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        // Crie uma entidade HttpEntity com o objeto e cabeçalhos
        HttpEntity<EnvioEmail> request = new HttpEntity<>(envioEmail, headers);

        // Faça a solicitação POST usando o RestTemplate
        URI location = restTemplate.postForLocation(postUrl, request);

        // Manipule a resposta conforme necessário
        System.out.println("A solicitação foi bem-sucedida e a localização da resposta é: " + location);
    }


    public static void main(String[] args) {
//        EnvioEmail envioEmail = new EnvioEmail();
//        envioEmail.setPara("pedrolucas.emp@gmail.com");
//        envioEmail.setAssunto("teste");
//        envioEmail.setConteudo("teste");
//        enviarEmailSimples(envioEmail);
    }
}
