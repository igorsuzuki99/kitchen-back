package com.carcara.oracle.kitchencloud.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.Arrays;

@RestControllerAdvice
public class TratamentoErros {

    @ExceptionHandler(SolicitacaoNaoEncontrada.class)
    public ResponseEntity<RespostaErro> tratarErro404NoSuchElementException(SolicitacaoNaoEncontrada ex) {
        RespostaErro respostaErro = new RespostaErro(ex.getMsg(), HttpStatus.NOT_FOUND.value());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(respostaErro);
    }

    @ExceptionHandler(ArithmeticException.class)
    public ResponseEntity<RespostaErro> tratarIndivisaoPorZero(ArithmeticException ex){
        RespostaErro respostaErro = new RespostaErro("Numero indivisivel por zero", HttpStatus.BAD_REQUEST.value());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(respostaErro);
    }


}
