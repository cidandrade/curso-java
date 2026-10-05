package br.com.cidandrade.aulas.classes;

import br.com.cidandrade.aulas.enums.ArteNoCafe;
import br.com.cidandrade.aulas.enums.SaborDeCafe;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class Cafe {

    private SaborDeCafe sabor;
    private ArteNoCafe arte;

}
