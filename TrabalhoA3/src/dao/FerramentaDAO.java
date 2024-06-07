package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import javax.swing.JOptionPane;
import modelo.Ferramenta;

/**
 *
 * @author felipe
 */
/* A classe DAO é a responsável por alterar o banco de dados de fato, as classes dao são chamadas pela classe modelo.
São as últimas camadas do código antes da alteração do banco de dados.

Os blocos finally ao final dos métodos verifica se a conexão com o banco de dados e com o pStatement ainda é existente, caso seja 
ocorre a tentativa de encerrar a conexão, caso não seja possível é lançado um erro 
-----------------------------------------------------------------------------------------------------------------------------
Último modificação 06/06/2024 ~~ modificado por Felipe::
 */
public class FerramentaDAO {

//Faz o registro de um novo objeto do tipo ferramenta no banco de dados local
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
        } //bloco finally verifica se a conexão com o banco de dados e com o pStatement ainda é existente, caso seja 
        // ocorre a tentativa de encerrar a conexão, caso não seja possível é lançado um erro 
        finally {

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

//Edita a coluna "marca" do banco de dados de um objeto ferramenta, utilizando como identificação a chave primária ID_ferramenta
    public void editarMarca(int ID_ferramenta, String novaMarca) throws ExceptionDAO {

        PreparedStatement pStatement = null;
        Connection cnn = null;
        String sql = "UPDATE ferramentas SET marca = ? WHERE ID_ferramenta = ?";

        try {

            cnn = new ConexaoMVC().getConnection();
            pStatement = cnn.prepareStatement(sql);
            pStatement.setString(1, novaMarca);
            pStatement.setInt(2, ID_ferramenta);
            pStatement.execute();

        } catch (SQLException erroAlteraraMarca) {
            throw new ExceptionDAO("Não foi possível alterar a marca da ferramenta cadastrada, erro:" + erroAlteraraMarca);
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

//Exclui um registro de ferramenta do banco de dados utilizando de parâmetro o ID da ferramenta 
    public void excluirAmigo(int ID_ferramenta) throws ExceptionDAO {

        PreparedStatement pStatement = null;
        Connection cnn = null;
        String sql = "DELETE FROM ferramentas WHERE ID_ferramentas = ?";

        try {

            cnn = new ConexaoMVC().getConnection();
            pStatement = cnn.prepareStatement(sql);
            pStatement.setInt(1, ID_ferramenta);
            pStatement.execute();

        } catch (SQLException erroExcluirAmigo) {
            throw new ExceptionDAO("não foi possível excluir o registro selecionado, erro:" + erroExcluirAmigo);
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


//Retorna um relatório das ferramentas registradas no formato de Arraylist
//Não requer parâmetro
//Utiliza uma condicional While para criar objetos e inseri-los dentro do arrayList (relatorios) 
    public List<Ferramenta> getFerramentasRegistradas() throws ExceptionDAO {

        Connection cnn = null;
        PreparedStatement pStatement = null;
        String sql = "SELECT * from ferramentas";
        List<Ferramenta> relatorio = new ArrayList<Ferramenta>();
        ResultSet select = null;

        try{
            
            cnn = new ConexaoMVC().getConnection();
            pStatement = cnn.prepareStatement(sql);
            select = prepareStatement.executeQuery(sql);

            while(select.next()){
                Ferramenta ferramenta = new Ferramenta();
                ferramenta.setID(select.getInt("ID_ferramenta"));
                ferramenta.setNome(select.getString("nome"));
                ferramenta.setString(select.getString("marca"));
                ferramenta.setDouble(select.getCustoDeAquisicao("custo_aquisicao"));
                relatorio.add(ferramenta);
            }
            return relatorio;
        }catch(SQLException erroRetiradaDeRelatorio){
            throw new ExceptionDAO("Não foi possível retirar o relatório de ferramentas registradas, erro:" + erroRetiradaDeRelatorio);
        }finally {

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
