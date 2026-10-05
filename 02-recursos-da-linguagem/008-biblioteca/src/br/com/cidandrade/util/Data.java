package br.com.cidandrade.util;

import java.time.LocalDate;
import java.time.Month;
import java.time.Period;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Date;

/**
 *
 * @author cidandrade
 */
public class Data {

    private static final String PADRAO_DE_DATA = "dd/MM/yyyy";
    private static final String PADRAO_DE_DATA_SQL = "yyyy-MM-dd";

    public static LocalDate dateToLocalDate(Date data) {
        return data.toInstant().atZone(
                ZoneId.systemDefault()).toLocalDate();
    }

    public static LocalDate dateToLocalDate(java.sql.Date data) {
        return data.toLocalDate();
    }

    public static String dataFormatada() {
        return getAgora().format(
                DateTimeFormatter.ofPattern(PADRAO_DE_DATA));
    }

    public static String dataFormatada(int dia, int mes, int ano) {
        return getLocalDate(dia, mes, ano).format(
                DateTimeFormatter.ofPattern(PADRAO_DE_DATA));
    }

    public static String dataFormatada(LocalDate data) {
        return data.format(
                DateTimeFormatter.ofPattern(PADRAO_DE_DATA));
    }

    public static String dataFormatadaSQL(Date data) {
        return dateToLocalDate(data).format(
                DateTimeFormatter.ofPattern(PADRAO_DE_DATA_SQL));
    }

    public static String dataFormatadaSQL(java.sql.Date data) {
        return dateToLocalDate(data).format(
                DateTimeFormatter.ofPattern(PADRAO_DE_DATA_SQL));
    }

    public static LocalDate getAgora() {
        return LocalDate.now();
    }

    public static int getIdade(int dia, int mes, int ano) {
        return Period.between(
                getLocalDate(dia, mes, ano),
                getAgora()).getYears();
    }

    public static int getIdade(LocalDate nascimento) {
        return Period.between(
                nascimento,
                getAgora()).getYears();
    }

    public static LocalDate getLocalDate(int dia, int mes, int ano) {
        LocalDate localDate = null;
        if (mes >= 1 && mes <= 12) {
            Month Mmes = null;
            switch (mes) {
                case 1:
                    Mmes = Month.JANUARY;
                    break;
                case 2:
                    Mmes = Month.FEBRUARY;
                    break;
                case 3:
                    Mmes = Month.MARCH;
                    break;
                case 4:
                    Mmes = Month.APRIL;
                    break;
                case 5:
                    Mmes = Month.MAY;
                    break;
                case 6:
                    Mmes = Month.JUNE;
                    break;
                case 7:
                    Mmes = Month.JULY;
                    break;
                case 8:
                    Mmes = Month.AUGUST;
                    break;
                case 9:
                    Mmes = Month.SEPTEMBER;
                    break;
                case 10:
                    Mmes = Month.OCTOBER;
                    break;
                case 11:
                    Mmes = Month.NOVEMBER;
                    break;
                case 12:
                    Mmes = Month.DECEMBER;
                    break;
            }
            localDate = LocalDate.of(ano, Mmes, dia);
        }
        return localDate;
    }
}
