package com.carcara.oracle.kitchencloud.controller;

import com.carcara.oracle.kitchencloud.service.RestTemplateEmailService;
import com.carcara.oracle.kitchencloud.model.EnvioEmail;
import com.carcara.oracle.kitchencloud.model.RankVendaProduto;
import com.carcara.oracle.kitchencloud.service.RankVendaProdutoService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/rank")
@CrossOrigin
@Tag(name = "RANK VENDA")
public class RankVendaProdutoController {

    @Autowired
    private RankVendaProdutoService rankVendaProdutoService;

    @Autowired
    private RestTemplateEmailService restTemplateEmailService;

    @GetMapping("aws")
    public List<RankVendaProduto> rankVendaProdutos(@RequestParam String data1, @RequestParam String data2, @RequestParam Integer diaSemana) {
        return rankVendaProdutoService.rankVendaProdutos(diaSemana,data1, data2);
    }

    @PostMapping("/teste")
    public void envioEmail (@RequestBody EnvioEmail envioEmail) throws IOException {
        restTemplateEmailService.enviarPost(envioEmail);
    }
}
