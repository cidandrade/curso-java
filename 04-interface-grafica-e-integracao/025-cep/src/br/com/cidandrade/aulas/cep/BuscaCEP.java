package br.com.cidandrade.aulas.cep;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.HashMap;
import java.util.Map;

public class BuscaCEP {

    public static Map<String, String> buscaCEP(String cep) {
        Map<String, String> retorno;
        retorno = new HashMap<>();
        String json;
        try {
            URL url = new URL("http://viacep.com.br/ws/" + cep + "/json");
            URLConnection uc = url.openConnection();
            InputStream is = uc.getInputStream();
            BufferedReader br = new BufferedReader(
                    new InputStreamReader(is));
            StringBuilder jsonSb = new StringBuilder();
            br.lines().forEach(l -> jsonSb.append(l.trim()));
            json = jsonSb.toString();
            json = json.replaceAll("[{},:]", "");
            json = json.replaceAll("\"", "\n");
            System.out.println(json);
            String[] array = json.split("\n");
            try {
                retorno.put("Logradouro", array[7]);
                retorno.put("Bairro", array[15]);
                retorno.put("Cidade", array[19]);
                retorno.put("UF", array[23]);
            } catch (Exception e) {
                retorno.put("Logradouro", "Não encontrado");
                retorno.put("Bairro", "");
                retorno.put("Cidade", "");
                retorno.put("UF", "");
            }
        } catch (IOException ex) {
            System.out.println(ex.getLocalizedMessage());
        }
        return retorno;
    }
}
