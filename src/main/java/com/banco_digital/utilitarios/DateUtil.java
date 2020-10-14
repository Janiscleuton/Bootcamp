package com.banco_digital.utilitarios;

import org.springframework.stereotype.Component;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

@Component
public class DateUtil {

    // Metodo calcula a idade baseado na data de nascimento
    public static int calculaIdade(Date dataNasc) {
        Calendar dateOfBirth = new GregorianCalendar();
        dateOfBirth.setTime(dataNasc);

        // Cria um objeto calendar com a data atual
        Calendar dataAtual = Calendar.getInstance();

        // Obtém a idade baseado no ano
        int idade = dataAtual.get(Calendar.YEAR) - dateOfBirth.get(Calendar.YEAR);
        dateOfBirth.add(Calendar.YEAR, idade);

        //se a data de hoje é antes da data de Nascimento, então diminui 1(um)
        if (dataAtual.before(dateOfBirth)) {
            idade--;
        }
        return idade;
    }
}
