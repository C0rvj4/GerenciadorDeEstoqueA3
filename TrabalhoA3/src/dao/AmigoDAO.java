package dao;

import java.sql.SQLException;
import modelo.Amigo;
import java.sql.DriverManager;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.PreparedStatement;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author felipe
 */
/* A classe DAO é a responsável por alterar o banco de dados de fato, as classes dao são chamadas pela classe modelo.
São as últimas camadas do código antes da alteração do banco de dados.

//bloco finally verifica se a conexão com o banco de dados e com o pStatement ainda é existente, caso seja 
// ocorre a tentativa de encerrar a conexão, caso não seja possível é lançado um erro 
-----------------------------------------------------------------------------------------------------------------------------
<<<<<<< HEAD
Último modificação 05/06/2024 ~~ modificado por Felipe::
 */
public class AmigoDAO {

    //construtor vazio da classe
    public AmigoDAO() {

    }

//registra um novo amigo no banco de dados utilizando PreparedStatement onde é pré definido um comando no formato de string para fazer a modificação no banco de dados
    public void registrarAmigo(Amigo amigo) throws ExceptionDAO {

        Connection cnn = null;
        PreparedStatement pStatement = null;
        String sql = "INSERT INTO amigos (nome, contato) value (?,?)";

        try {
            cnn = new ConexaoMVC().getConnection();
            pStatement = cnn.prepareStatement(sql);
            pStatement.setString(1, amigo.getNome());
            pStatement.setString(1, amigo.getContato());
            pStatement.execute();

        } catch (SQLException erroRegistroDeAmigo) {
            throw new ExceptionDAO("Não foi possível registrar o nome amigo erro:" + erroRegistroDeAmigo);
        } finally {

            if (cnn != null) {
                try {
                    cnn.close();
                } catch (SQLException erro) {
                    throw new ExceptionDAO("Não foi possível encerrar a conexão com o banco de dados erro:" + erro);
                }
                if (pStatement != null) {
                    try {
                        pStatement.close();
                    } catch (SQLException erro) {
                        throw new ExceptionDAO("Não foi possível encerrar a conexão com o Prepare Statement");
                    }
                }
            }
        }

    }

// método que retorna um lista de objetos do tipo "Amigo", não requer nenhum parâmetro.
//utilizado para consultar todos os registros de Amigos no banco de dados retornando em uma lista com nome de "relatorio"
    public List<Amigo> getAmigosCadastrados() throws ExceptionDAO {

        Connection cnn = null;
        PreparedStatement pStatement = null;
        ResultSet select = null;
        String sql = "SELECT * FROM amigos";
        List<Amigo> relatorio = new ArrayList<>();

        try {
            cnn = new ConexaoMVC().getConnection();
            pStatement = cnn.prepareStatement(sql);
            select = pStatement.executeQuery(sql);

            while (select.next()) {

                Amigo amigo = new Amigo();
                amigo.setAmigoID(select.getInt("ID_amigo"));
                amigo.setNome(select.getString("nome"));
                amigo.setContato(select.getString("contato"));
                relatorio.add(amigo);

            }
            return relatorio;

        } catch (SQLException ErroRetiradaDeRelatorio) {
            throw new ExceptionDAO("não foi possível retirar o relatório de amigos registrados erro:" + ErroRetiradaDeRelatorio);

        } finally {

            if (cnn != null) {
                try {
                    cnn.close();
                } catch (SQLException erro) {
                    throw new ExceptionDAO("Não foi possível encerrar a conexão com o banco de dados erro:" + erro);
                }
                if (pStatement != null) {
                    try {
                        pStatement.close();
                    } catch (SQLException erro) {
                        throw new ExceptionDAO("Não foi possível encerrar a conexão com o Prepare Statement");
                    }
                }
            }
        }
    }

//Este método edita o nome de um amigo, pede como parâmetro o novoNome para substituir e o ID_amigo para localizar o cadastro
//Utiliza da linguagem SQL para localizar o registro utilizando WHERE = ? que recebe o valor do parâmetro ID_amigo
    public void editarNome(int ID_amigo, String novoNome) throws ExceptionDAO {

        Connection cnn = null;
        PreparedStatement pStatement = null;
        ResultSet result = null;
        String sql = "UPDATE amigos SET nome = ? WHERE ID_amigo = ?";

        try {

            cnn = new ConexaoMVC().getConnection();
            pStatement = cnn.prepareStatement(sql);
            pStatement.setString(1, novoNome);
            pStatement.setInt(2, ID_amigo);
            pStatement.execute();
        } catch (SQLException erroEditarCadastro) {
            throw new ExceptionDAO("Não foi possível editar o nome do cadastro selecionado erro:" + erroEditarCadastro);
        } finally {

            if (cnn != null) {
                try {
                    cnn.close();
                } catch (SQLException erro) {
                    throw new ExceptionDAO("Não foi possível encerrar a conexão com o banco de dados erro:" + erro);
                }
                if (pStatement != null) {
                    try {
                        pStatement.close();
                    } catch (SQLException erro) {
                        throw new ExceptionDAO("Não foi possível encerrar a conexão com o Prepare Statement");
                    }
                }
            }
        }
    }

    public void excluirAmigo(int ID_amigo) throws ExceptionDAO {

        Connection cnn = null;
        PreparedStatement pStatement = null;
        String sql = "DELETE FROM amigo WHERE ID = ? ";

        try {
            cnn = new ConexaoMVC().getConnection();
            pStatement = cnn.prepareStatement(sql);
            pStatement.setInt(1, ID_amigo);
            pStatement.execute();

        } catch (SQLException erroExcluirAmigo) {
            throw new ExceptionDAO("não foi possível excluir o registro do Amigo erro:" + erroExcluirAmigo);

        } finally {

            if (cnn != null) {
                try {
                    cnn.close();
                } catch (SQLException erro) {
                    throw new ExceptionDAO("Não foi possível encerrar a conexão com o banco de dados erro:" + erro);
                }
                if (pStatement != null) {
                    try {
                        pStatement.close();
                    } catch (SQLException erro) {
                        throw new ExceptionDAO("Não foi possível encerrar a conexão com o Prepare Statement");
                    }
                }
            }
        }

    }
}
