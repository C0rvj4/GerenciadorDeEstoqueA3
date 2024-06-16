package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 *
 * @author felipe
 */
/* A classe ConexaoMVC tem como único objetivo cria a conexão com o Banco de Dados através do método getConnection().
Sempre que é necessário acessar o banco de dados exigindo uma conexão com o mesmo este método será utilizado 
-----------------------------------------------------------------------------------------------------------------------------
Último modificação 07/06/2024 ~~ modificado por Felipe;;
 */

public class ConexaoMVC {

//estabelece a conexão com o banco de dados
    public Connection getConnection() {

        Connection conn = null;

        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException exception) {
            exception.printStackTrace();
        }

        try {
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/mvc?useSSL=false", "root", "F!el1pe2003.@0");
        } catch (SQLException exception) {
            exception.printStackTrace();
        }

        return conn;

    }
    
    
// ao final dos métodos verifica se a conexão com o banco de dados e com o pStatement ainda é existente, caso seja 
//ocorre a tentativa de encerrar a conexão, caso não seja possível é lançado um erro 
    public void encerrarConexao(Connection cnn, PreparedStatement pStatement) throws ExceptionDAO {

        if (cnn != null) {
            try {
                cnn.close();
            } catch (SQLException erro) {
                throw new ExceptionDAO("Não foi possível encerrar a conexão com o banco de dados");
            }
        }
        if (pStatement != null) {
            try {
                pStatement.close();
            } catch (SQLException erro) {
                throw new ExceptionDAO("Não foi possível encerrar a conexão com o Prepare Statement" + erro);
            }
        }
    }

}
