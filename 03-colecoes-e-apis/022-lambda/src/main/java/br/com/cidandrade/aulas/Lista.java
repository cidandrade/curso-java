package br.com.cidandrade.aulas;

import java.time.LocalDate;
import java.time.Month;
import java.util.ArrayList;
import java.util.List;
import lombok.Getter;

@Getter
public class Lista {

    List<Funcionario> funcionarios;

    public Lista() {
        funcionarios = new ArrayList<>();
        funcionarios.add(new Funcionario("Joyce Byers",
                Funcionario.Sexo.FEM,
                LocalDate.of(
                        1938, Month.MARCH, 5),
                "joyce@gmail.com"));
        funcionarios.add(new Funcionario("Jim Hopper",
                Funcionario.Sexo.MASC,
                LocalDate.of(
                        1933, Month.MAY, 17),
                "sheriff@hotmail.com"));
        funcionarios.add(new Funcionario("Jane Hooper",
                Funcionario.Sexo.FEM,
                LocalDate.of(
                        1965, Month.OCTOBER, 2),
                "eleven@gmail.com"));
        funcionarios.add(new Funcionario("Mike Wheeler",
                Funcionario.Sexo.MASC,
                LocalDate.of(
                        1964, Month.DECEMBER, 23),
                "mike@gmail.com"));
        funcionarios.add(new Funcionario("Dustin Henderson",
                Funcionario.Sexo.MASC,
                LocalDate.of(
                        1964, Month.FEBRUARY, 27),
                "dustin@gmail.com"));
        funcionarios.add(new Funcionario("Lucas Sainclair",
                Funcionario.Sexo.MASC,
                LocalDate.of(
                        1963, Month.NOVEMBER, 11),
                "lucas@gmail.com"));
        funcionarios.add(new Funcionario("Nancy Wheeler",
                Funcionario.Sexo.FEM,
                LocalDate.of(
                        1959, Month.JULY, 3),
                "nancy@gmail.com"));
        funcionarios.add(new Funcionario("Maxine \"Max\" Mayfield",
                Funcionario.Sexo.FEM,
                LocalDate.of(
                        1967, Month.MARCH, 9),
                "the_max@gmail.com"));
        funcionarios.add(new Funcionario("Erica Sainclair",
                Funcionario.Sexo.FEM,
                LocalDate.of(
                        1968, Month.MARCH, 23),
                "erica@gmail.com"));
        funcionarios.add(new Funcionario("Henry Creel",
                Funcionario.Sexo.OUTROS,
                LocalDate.of(
                        1960, Month.JANUARY, 5),
                "vecna@gmail.com"));
    }
}
