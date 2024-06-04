
package dao;
import java.sql.SQLException;

import modelo.Amigo;

import java.sql.DriverManager;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.PreparedStatement;

public class AmigoDAO{

    //construtor vazio da classe
    public AmigoDAO(){

    }

    //registra um novo amigo no banco de dados
    public void registrarAmigo(Amigo amigo) throws ExceptionDAO {


        Connection cnn = null;
        PreparedStatement pStatement = null;
        String sql = "INSERT INTO amigos (nome, contato) value (?,?)";

        try{
            cnn = new ConexaoMVC().getConnection();
            pStatement = cnn.prepareStatement(sql);
            pStatement.setString(1, amigo.getNome());
            pStatement.setString(1, amigo.getContato());
            pStatement.execute();

        }catch(SQLException erroRegistroDeAmigo){
            throw new ExceptionDAO("Não foi possível registrar o nome amigo erro:" + erroRegistroDeAmigo);
        }finally {

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

    public List<Amigio> getAmigosCadastrados(){
         
        Connection cnn = null;
        PreparedStatement pStatement = null;
        ResultSet select = null;
        String sql = "SELECT * FROM amigos";
        LIst<Amigo> relatorio = new ArrayList<>();

        try{
            cnn = new ConexaoMVC().getConnection();
            pStatement = cmm.prepareStatement(sql);
            select = pStatement.executeQuery(sql);

            while(select.next()){

                Amigo amigo = new Amigo();
                amigo.setAmigoID(select.getInt("ID_amigo"));
                amigo.setNome(select.getString("nome"));
                amigo.setContato(select.getString("contato"));
                relatorio.add(amigo);

            }
            return relatorio;

        }catch(SQLException ErroRetiradaDeRelatorio){
            throw new ExceptionDAO("não foi possível retirar o relatório de amigos registrados erro:" + ErroRetiradaDeRelatorio);
        }finally {

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