package com.carcara.oracle.kitchencloud.service;

import com.carcara.oracle.kitchencloud.model.ConfiguracaoAlerta;
import com.carcara.oracle.kitchencloud.model.EnvioEmail;
import com.carcara.oracle.kitchencloud.model.Estoque;
import com.carcara.oracle.kitchencloud.model.Ingrediente;
import com.carcara.oracle.kitchencloud.model.dto.CadastroConfiguracaoAlertaDTO;
import com.carcara.oracle.kitchencloud.model.dto.ExibicaoEstoqueDTO;
import com.carcara.oracle.kitchencloud.model.enums.CondicaoDisparoAlerta;
import com.carcara.oracle.kitchencloud.model.enums.Entidade;
import com.carcara.oracle.kitchencloud.repository.ConfiguracaoAlertaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class ConfiguracaoAlertaService {

    @Autowired
    private ConfiguracaoAlertaRepository configuracaoAlertaRepository;

    @Autowired
    private RestTemplateEmailService restTemplateEmailService;

    @Autowired
    @Lazy
    private EstoqueService estoqueService;

    public ConfiguracaoAlerta criarAlerta (CadastroConfiguracaoAlertaDTO cadastroConfiguracaoAlertaDTO){
        ConfiguracaoAlerta configuracaoAlerta = new ConfiguracaoAlerta(cadastroConfiguracaoAlertaDTO);

        Integer firstId = configuracaoAlertaRepository.findFirstByOrderByIdDesc();

        configuracaoAlerta.setId(firstId != null ? firstId+1 : 1);
        return configuracaoAlertaRepository.save(configuracaoAlerta);
    }

    public List<ConfiguracaoAlerta> procurarAlertaEstoque (){
        return configuracaoAlertaRepository.findByEntidade(Entidade.ESTOQUE);
    }

    public void alertaEstoque(Estoque estoque, ConfiguracaoAlerta configuracaoAlerta){
        EnvioEmail envioEmail = new EnvioEmail();
        switch (configuracaoAlerta.getCondicaoDisparo()){
            case QUANTIDADE_EM_ESTOQUE -> {
                Ingrediente ingrediente = estoque.getItemCompra().getIngrediente();
                Float condicao = ingrediente.getEstoqueMinimo()+
                        (Float.valueOf(ingrediente.getEstoqueMinimo()/100*Float.valueOf(configuracaoAlerta.getValorParametro())));
                if(ingrediente.getQuantidadeTotal() <= condicao){
                    envioEmail = EnvioEmail.builder()
                            .para(configuracaoAlerta.getDestinatarios())
                                    .assunto("Alerta estoque")
                                            .conteudo("<h1>Aviso de Estoque Mínimo</h1>\n" +
                                                    "\n" +
                                                    "    <p>Olá,</p>\n" +
                                                    "\n" +
                                                    "    <p>Este é um aviso para informar que o estoque do item <strong>"+ingrediente.getNomeIngrediente()+"</strong> está abaixo do nível mínimo. A quantidade disponível atualmente é insuficiente para atender à demanda esperada.</p>\n" +
                                                    "\n" +
                                                    "    <p>Por favor, tome as medidas necessárias para reabastecer o estoque deste item o mais rápido possível.</p>\n" +
                                                    "\n" +
                                                    "    <p>Lembre-se de que a disponibilidade dos produtos em estoque é crucial para garantir um bom atendimento aos nossos clientes e evitar a interrupção das operações.</p>\n" +
                                                    "\n" +
                                                    "    <p>Se você tiver alguma dúvida ou precisar de assistência adicional, não hesite em entrar em contato conosco.</p>\n" +
                                                    "\n" +
                                                    "    <p>Obrigado pela atenção e ação imediata.</p>\n" +
                                                    "\n" +
                                                    "    <p>Atenciosamente,<br>")
                                                    .build();
                    restTemplateEmailService.enviarEmailSimples(envioEmail);
                }
            }
        }
    }

    public List<ConfiguracaoAlerta> alertaData() {
        EnvioEmail envioEmail = new EnvioEmail();
        List<ConfiguracaoAlerta> alertas = configuracaoAlertaRepository
                .findByEntidadeAndCondicaoDisparo(Entidade.ESTOQUE, CondicaoDisparoAlerta.VALIDADE);

        //for(ConfiguracaoAlerta alerta : alertas){
        ConfiguracaoAlerta alerta = alertas.get(0);
            switch (alerta.getCondicaoDisparo()){
                case VALIDADE -> {
                    List<Estoque> estoques = estoqueService.procurarEstoquePorDataValidade(LocalDate.now().plusDays(Long.valueOf(alerta.getValorParametro())));
                    envioEmail = EnvioEmail.builder().para(alerta.getDestinatarios())
                                    .assunto("Validade")
                                            .conteudo(tabelaItensEstoque(estoques)).build();
                    restTemplateEmailService.enviarEmailSimples(envioEmail);
                }
            }
        //}
        return configuracaoAlertaRepository.findByEntidadeAndCondicaoDisparo(Entidade.ESTOQUE, CondicaoDisparoAlerta.VALIDADE);
    }

    public String tabelaItensEstoque(List<Estoque> estoque){
        String itens  = "";

        for(Estoque item : estoque){
            itens = itens +
                    "<tr>" +
                    "<td>"+item.getItemCompra().getIngrediente().getNomeIngrediente()+"</td>"+
                    "<td>"+item.getCodEstoque()+"</td>"+
                    "<td>"+item.getQuantidadeProduto() + " "+item.getItemCompra().getIngrediente().getUnidadeMedida()+"</td>"+
                    "<td>"+item.getDataValidade()+"</td>"+
                    "</tr>";
        }
        String tabela = " <table border=\"1\">\n" +
                "        <tr>\n" +
                "            <th>Ingrediente</th>\n" +
                "            <th>Código Estoque</th>\n" +
                "            <th>Quantidade</th>\n" +
                "            <th>Data de Vencimento</th>\n" +
                "        </tr>\n" +
                itens +
                "        <!-- Adicione mais linhas conforme necessário -->\n" +
                "    </table>";
        return tabela;
    }
}
