/**
 * Classe para manipulação do banco de dados
 */
package br.com.cidandrade.aulas.modelo;

import br.com.cidandrade.util.Mensagem;
import java.sql.ResultSetMetaData;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Classe de conexão com banco de dados Singleton
 *
 * No computador onde a aplicação deve rodar foi instalado o SGBDR MySQL. Na aba
 * Services do NetBeans o MySQL foi registrado e criada uma conexão. O arquivo
 * JDBC para MySQL foi adicionado nas propriedades do projeto. Foi criado o
 * database livraria.
 *
 * @author profandrade@gmail.com
 */
public class BD {

    private static BD banco;
    private Connection con;
    private boolean fechaConexaoAoFinal = true;
    private final String SENHA = "123456";
    private final String URL = "jdbc:mysql://localhost/livraria";
    private final String USUARIO = "cidandrade";

    /**
     * Realiza consulta SQL para retorno de um valor inteiro
     *
     * @param sql - String com SQL para consulta
     * @return int - Valor procurado
     */
    public int getValorConsulta(String sql) {
        int retorno = 0;
        conectar();
        try {
            ResultSet rs = con.createStatement().executeQuery(sql);
            rs.next();
            retorno = rs.getInt(1);
            desconectar();
        } catch (SQLException ex) {
            Mensagem.msgErro("Não foi possível executar "
                    + sql + "\n", ex);
        }
        return retorno;
    }

    /**
     * Retorna resultado de uma consulta em um List
     *
     * @param sql - String com instrução de consulta SQL
     * @return List
     */
    public List getSQLDados(String sql) {
        ResultSet rs;
        List retorno = null;
        conectar();
        try {
            rs = con.createStatement().executeQuery(sql);
            retorno = resultsetParaList(rs);
            desconectar();
        } catch (SQLException ex) {
            Mensagem.msgErro("Não foi possível executar consulta\n",
                    ex);
            System.exit(1);
        }
        return retorno;
    }

    /**
     * Converte ResultSet em List
     *
     * @param rs - ResultSet
     * @return List
     */
    private List resultsetParaList(ResultSet rs) {
        List retorno = new ArrayList();
        try {
            ResultSetMetaData rsmd = rs.getMetaData();
            int colunas = rsmd.getColumnCount();
            while (rs.next()) {
                Object[] linha = new Object[colunas];
                for (int i = 0; i < colunas; i++) {
                    linha[i] = rs.getObject(i + 1);
                }
                retorno.add(linha);
            }
        } catch (SQLException ex) {
            Mensagem.msgErro("Problema em acesso a dados\n", ex);
        }
        return retorno;
    }

    /**
     * Executa instrução SQL Desconecta ao final ou não dependendo de
     * fechaConexaoAoFinal
     *
     * @param sql - String - Instrução a ser executada
     */
    public void executa(String sql) {
        try {
            if (con == null || con.isClosed()) {
                conectar();
            }
            con.createStatement().executeUpdate(sql);
            if (fechaConexaoAoFinal) {
                desconectar();
            }
        } catch (SQLException e) {
            Mensagem.msgErro("Não foi possível executar\n" + sql, e);
            System.exit(1);
        }
    }

    /**
     * Conecta o banco de dados
     */
    private void conectar() {
        try {
            con = DriverManager.getConnection(
                    URL, USUARIO, SENHA);
        } catch (SQLException e) {
            Mensagem.msgErro("Não foi possível conectar", e);
            System.exit(1);
        }
    }

    /**
     * Desconecta o banco de dados
     */
    public void desconectar() {
        try {
            if (con != null && !con.isClosed()) {
                con.close();
            }
        } catch (SQLException ex) {
        }
    }

    /**
     * Inicialização do banco de dados para testes
     */
    public void inicializa() {

        fechaConexaoAoFinal = false;
        executa("Drop table if exists precos");
        executa("Drop table if exists livros");
        executa("Drop table if exists categorias");
        executa("Drop table if exists livrarias");
        executa("Create table livrarias "
                + "(id int not null auto_increment primary key, "
                + "nome varchar(10) not null, "
                + "unique key (nome))");
        executa("Insert into livrarias (nome) values ('Amazonia')");
        executa("Insert into livrarias (nome) values ('Jangada')");
        executa("Insert into livrarias (nome) values ('Africanas')");
        executa("Create table categorias "
                + "(id int not null auto_increment primary key, "
                + "nome varchar(15) not null, "
                + "unique key (nome))");
        executa("Insert into categorias (nome) values ('Ficção')");
        executa("Insert into categorias (nome) values ('Romance')");
        executa("Insert into categorias (nome) values ('Esotéricos')");
        executa("Create table livros "
                + "(id int not null auto_increment primary key, "
                + "idCat int not null, "
                + "nome varchar(30) not null, "
                + "autor varchar(25) not null, "
                + "foreign key (idCat) references categorias(id), "
                + "unique key (nome, autor))");
        executa("Insert into livros (nome, autor, idCat) "
                + "values ('Lolita', 'Vladimir Nabokov', 2)");
        executa("Insert into livros (nome, autor, idCat) "
                + "values ('Eu, Robô', 'Issac Azimov', 1)");
        executa("Insert into livros (nome, autor, idCat) "
                + "values ('O Caminho do Tarot', 'Alejandro Jodorowsky', 3)");
        executa("Create table precos "
                + "(idLivro int not null, "
                + "idLivraria int not null, "
                + "data date not null, "
                + "preco decimal(6,2) not null, "
                + "foreign key (idLivro) references livros(id), "
                + "foreign key (idLivraria) references livrarias(id))");
        executa("Insert into precos (idLivro, idLivraria, data, preco) "
                + "values (1, 1, '2021-1-10', 45.6)");
        executa("Insert into precos (idLivro, idLivraria, data, preco) "
                + "values (1, 2, '2021-1-11', 44.7)");
        executa("Insert into precos (idLivro, idLivraria, data, preco) "
                + "values (1, 3, '2021-1-11', 48.12)");
        executa("Insert into precos (idLivro, idLivraria, data, preco) "
                + "values (2, 1, '2022-3-26', 32.5)");
        executa("Insert into precos (idLivro, idLivraria, data, preco) "
                + "values (2, 2, '2022-3-26', 33.9)");
        executa("Insert into precos (idLivro, idLivraria, data, preco) "
                + "values (2, 3, '2022-3-26', 35.0)");
        executa("Insert into precos (idLivro, idLivraria, data, preco) "
                + "values (3, 1, '2022-10-10', 92.5)");
        executa("Insert into precos (idLivro, idLivraria, data, preco) "
                + "values (3, 2, '2022-10-10', 92)");
        executa("Insert into precos (idLivro, idLivraria, data, preco) "
                + "values (3, 3, '2022-10-10', 87.48)");
        fechaConexaoAoFinal = true;
        desconectar();
    }

    /**
     * Retorna objeto Singleton desta classe
     *
     * @return BD
     */
    public static BD obterBanco() {
        if (banco == null) {
            banco = new BD();
        }
        return banco;
    }

    /**
     * Construtor privado - Singleton
     */
    private BD() {
    }
}
