
package dao;
import java.sql.SQLException;
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
}