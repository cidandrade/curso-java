package br.com.cidandrade.util;

import java.math.BigDecimal;
import java.nio.charset.StandardCharsets;

public class Conv {

    public static String byteArrayToString(byte[] b) {
        return new String(b, StandardCharsets.UTF_8);
    }

    public static BigDecimal objectToBigDecimal(Object o) {
        return (BigDecimal) o;
    }

    public static Byte objectToValueByte(Object o) {
        return Byte.valueOf(o.toString());
    }

    public static Float objectToFloat(Object o) {
        BigDecimal bd = objectToBigDecimal(o);
        return bd.floatValue();
    }

    public static Integer objectToInteger(Object o) {
        return (Integer) o;
    }

    public static Object[] objectToObjectArray(Object o) {
        return (Object[]) o;
    }

    public static String objectToString(Object o) {
        return objectToString(o, false);
    }

    public static String objectToString(Object o, boolean previne) {
        String retorno = (String) o;
        if (previne) {
            retorno = previneStringNulo(retorno);
        }
        return retorno;
    }

    public static String objectToValueString(Object o) {
        return String.valueOf(o);
    }

    public static String previneStringNulo(String s) {
        return (s == null ? "" : s.trim());
    }

    public static byte[] stringToByteArray(String s) {
        return s.getBytes();
    }

}
