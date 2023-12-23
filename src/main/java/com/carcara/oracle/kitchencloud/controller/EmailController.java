package com.carcara.oracle.kitchencloud.controller;


import com.carcara.oracle.kitchencloud.model.EnvioEmail;
import com.carcara.oracle.kitchencloud.service.EmailService;
import com.carcara.oracle.kitchencloud.service.RestTemplateEmailService;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.mail.MessagingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@RestController
@RequestMapping("email")
@CrossOrigin
@Tag(name = "EMAIL")
public class EmailController {

    @Autowired
    private EmailService emailService;

    @PostMapping("rank-pratos-diarios")
    public void rankPratosDiarios (@RequestBody EnvioEmail envioEmail) throws IOException {
        emailService.rankPratosDiarios(envioEmail);
    }

    @GetMapping
    public void recuperacaoDeSenha(@RequestParam String email, @RequestParam String titulo, @RequestParam String descricao) throws MessagingException {
        emailService.enviar(email, titulo, descricao);
    }
}
