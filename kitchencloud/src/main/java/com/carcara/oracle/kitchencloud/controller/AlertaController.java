package com.carcara.oracle.kitchencloud.controller;

import com.carcara.oracle.kitchencloud.model.ConfiguracaoAlerta;
import com.carcara.oracle.kitchencloud.model.dto.CadastroConfiguracaoAlertaDTO;
import com.carcara.oracle.kitchencloud.service.ConfiguracaoAlertaService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/alerta")
@CrossOrigin
@Tag(name = "Alertas")
public class AlertaController {

    @Autowired
    private ConfiguracaoAlertaService configuracaoAlertaService;

    @PostMapping
    public ConfiguracaoAlerta criarAlerta (@RequestBody CadastroConfiguracaoAlertaDTO configuracaoAlerta){
        return configuracaoAlertaService.criarAlerta(configuracaoAlerta);
    }

    @PostMapping("rotina-data")
    public List<ConfiguracaoAlerta> teste(){
        return configuracaoAlertaService.alertaData();
    }
}
