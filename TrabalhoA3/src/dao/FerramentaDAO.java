package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import javax.swing.JOptionPane;
import modelo.Ferramenta;

/**
 *
 * @author felip
 */
public class FerramentaDAO {

    public void cadastroFerramenta(Ferramenta ferramenta) throws ExceptionDAO {

        String sql = "insert into ferramentas (nome, marca, valor) value (?, ?, ?)";
        PreparedStatement pStatement = null;
        Connection cnn = null;

        try {
            cnn = new ConexaoMVC().getConnection();
            pStatement = cnn.prepareStatement(sql);
            pStatement.setString(1, ferramenta.getNome());
            pStatement.setString(2, ferramenta.getMarca());
            pStatement.setDouble(3, ferramenta.getCustoDeAquisicao());
            pStatement.execute();
            JOptionPane.showMessageDialog(null, "A ferramenta foi registrada com sucesso ");
            
        } catch (SQLException erro) {
            throw new ExceptionDAO("Erro ao registra nova ferramenta: " + erro);
        } finally {

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
}

