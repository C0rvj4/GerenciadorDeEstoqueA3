package dao;

import com.mysql.jdbc.ResultSetImpl;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
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
Último modificação 07/06/2024 ~~ modificado por Felipe;;
 */
public class FerramentaDAO extends ConexaoMVC {

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
            encerrarConexao(cnn, pStatement);
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
            encerrarConexao(cnn, pStatement);
        }
    }

//Edita o nome de uma ferramenta no banco de dados tendo como parâmetro de busca o ID da ferramenta
    public void editarNome(int ID_ferramenta, String novoNome) throws ExceptionDAO {

        PreparedStatement pStatement = null;
        Connection cnn = null;
        String sql = "UPDATE ferramentas SET nome = ? WHERE ID_ferramenta = ?";

        try {

            cnn = new ConexaoMVC().getConnection();
            pStatement = cnn.prepareStatement(sql);
            pStatement.setString(1, novoNome);
            pStatement.setInt(2, ID_ferramenta);
            pStatement.execute();
        } catch (SQLException erroEditarNome) {
            throw new ExceptionDAO("Não foi possível editar o nome da ferramenta: erro" + erroEditarNome);
        } finally {
            encerrarConexao(cnn, pStatement);
        }
    }

//Exclui um registro de ferramenta do banco de dados utilizando de parâmetro o ID da ferramenta
    public void excluirFerramenta(int ID_ferramenta) throws ExceptionDAO {

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
            encerrarConexao(cnn, pStatement);
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

        try {

            cnn = new ConexaoMVC().getConnection();
            pStatement = cnn.prepareStatement(sql);
            select = pStatement.executeQuery();

            while (select.next()) {
                Ferramenta ferramenta = new Ferramenta();
                ferramenta.setID(select.getInt("ID_ferramenta"));
                ferramenta.setNome(select.getString("nome"));
                ferramenta.setMarca(select.getString("marca"));
                ferramenta.setCustoDeAquisicao(select.getDouble("valor"));
                relatorio.add(ferramenta);
            }
            return relatorio;
        } catch (SQLException erroRetiradaDeRelatorio) {
            throw new ExceptionDAO("Não foi possível retirar o relatório de ferramentas registradas, erro:" + erroRetiradaDeRelatorio);
        } finally {
            encerrarConexao(cnn, pStatement);
        }
    }
    

//Faz o calculo de todos os valores das ferramentas e retorna em formato double, caso não consiga efetuar
//retorna um valor zerado em formato double
    public double getTotalGasto() throws ExceptionDAO{
        
        Connection cnn = null;
        PreparedStatement pStatement = null;
        ResultSet soma = null;
        String sql = "SELECT SUM(valor) AS total_gasto FROM ferramentas";
        
        try{
            cnn = new ConexaoMVC().getConnection();
            pStatement = cnn.prepareStatement(sql);
            soma = pStatement.executeQuery(sql);
            
            if(soma.next()){
                double resultado = soma.getDouble("total_gasto");
                return resultado;
            }else{
                return 00.00;
            }
        }catch(SQLException erroCalcula){
            throw new ExceptionDAO("Erro ao calcular o valor total gasto:" + erroCalcula);
        }finally{
            encerrarConexao(cnn, pStatement);
        }
    }

}
