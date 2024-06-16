package dao;

import com.mysql.fabric.xmlrpc.base.Data;
import java.sql.PreparedStatement;
import java.sql.Connection;
import java.sql.SQLException;
import javax.swing.JOptionPane;
import modelo.Emprestimo;
import java.sql.ResultSet;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import modelo.Amigo;

/**
 *
 * @author felipe
 */
/* A classe DAO é a responsável por alterar o banco de dados de fato, as classes dao são chamadas pela classe modelo.
São as últimas camadas do código antes da alteração do banco de dados.


-----------------------------------------------------------------------------------------------------------------------------
Último modificação 07/06/2024 ~~ modificado por Felipe
 */
public class EmprestimoDAO extends ConexaoMVC {

//Cadastra um empréstimo no banco de dados através dos dados fornecidos pelo package controle
    public void cadastroEmprestimo(Emprestimo emprestimo) throws ExceptionDAO {

        String sql = "insert into emprestimos (data_inicial, data_final, ID_amigos, amigo_nome, ID_ferramenta, ferramenta_nome, situacao) value (?, ? ,? ,?, ?, ?, ?)";
        Connection cnn = null;
        PreparedStatement pStatement = null;
        Amigo amigo = new Amigo();

        try {
            cnn = new ConexaoMVC().getConnection();
            pStatement = cnn.prepareStatement(sql);
            pStatement.setDate(1, emprestimo.getDataEmprestimo());
            pStatement.setDate(2, emprestimo.getDataDevolucao());
            pStatement.setInt(3, emprestimo.getId_amigo());
            pStatement.setString(4, getNomeAmigo(emprestimo.getId_amigo()));
            pStatement.setInt(5, emprestimo.getId_ferramenta());
            pStatement.setString(6, getNomeFerramenta(emprestimo.getId_ferramenta()));
            pStatement.setString(7, "Em andamento");
            pStatement.execute();
            JOptionPane.showMessageDialog(null, "O empréstimo foi registrado com sucesso ");

        } catch (SQLException erro) {
            throw new ExceptionDAO("Não foi possível registrar o empréstimo erro:" + erro);
        } finally {

            encerrarConexao(cnn, pStatement);

        }
    }
//altera a coluna situação do empréstimo para "encerrado" sinalizando que o empréstimo ja foi finalizado

    public void encerrarEmprestimo(int emprestimo_id) throws ExceptionDAO {

        Connection cnn = null;
        PreparedStatement pStatement = null;
        ResultSet update = null;
        String sql = "UPDATE emprestimos SET situacao = ? where ID = ?";

        try {

            cnn = new ConexaoMVC().getConnection();
            pStatement = cnn.prepareStatement(sql);
            update = pStatement.executeQuery(sql);

            pStatement.setString(1, "Encerrado");
            pStatement.setInt(2, emprestimo_id);

        } catch (SQLException erro) {
            throw new ExceptionDAO("Não foi possível realizar o fechamento do empréstimo erro:" + erro);
        } finally {

            encerrarConexao(cnn, pStatement);
        }
    }

//Em desenvolvimento ~~
    public void updateSituacaoDoEmprestimo() throws ExceptionDAO {

        Connection cnn = null;
        PreparedStatement pStatement = null;
    }

//----------------------------------------------------Relatórios----------------------------------------------------------------------//
    public List<Emprestimo> getAllEmprestimosVencidos() throws ExceptionDAO {

        Connection cnn = null;
        PreparedStatement pStatement = null;
        String sql = "SELECT * FROM emprestimos WHERE situacao = 'vencido' ";
        ResultSet select = null;
        List<Emprestimo> emprestimosLista = new ArrayList<>();

        try {
            cnn = new ConexaoMVC().getConnection();
            pStatement = cnn.prepareStatement(sql);
            select = pStatement.executeQuery(sql);

            while (select.next()) {
                Emprestimo emprestimo = new Emprestimo();
                emprestimo.setDataEmprestimo(select.getDate("data_inicial"));
                emprestimo.setID(select.getInt("ID"));
                emprestimo.setDataDevolucao(select.getDate("data_final"));
                emprestimo.setId_amigo(select.getInt("ID_amigo"));
                emprestimo.setId_ferramenta(select.getInt("ID_ferramenta"));
                emprestimo.setSituacao(select.getString("situacao"));
                emprestimosLista.add(emprestimo);

            }
            return emprestimosLista;

        } catch (SQLException getEmprestimosVencidos) {
            throw new ExceptionDAO("Não foi possível realizar a retirada do relatório de empréstimos vencidos erro: " + getEmprestimosVencidos);

        } finally {
            encerrarConexao(cnn, pStatement);
        }
    }

    // Função para consultar um empréstimo usando como parâmetro o ID do amigo que fez o empréstimo, utiliza a classe ResultSet para fazer o tratamento dos dados obtidos;
    public List<Emprestimo> getEmprestimoPorAmigoID(int amigoID) throws ExceptionDAO {

        Connection cnn = null;
        PreparedStatement pStatement = null;
        String sql = "SELECT * FROM emprestimos WHERE ID = ?";

        List<Emprestimo> emprestimosLista = new ArrayList<Emprestimo>();

        try {
            cnn = new ConexaoMVC().getConnection();
            pStatement = cnn.prepareStatement(sql);

            pStatement.setInt(1, amigoID);
            ResultSet select = pStatement.executeQuery();

            while (select.next()) {

                Emprestimo emprestimo = new Emprestimo();
                emprestimo.setID(select.getInt("ID"));
                emprestimo.setDataDateInicia(select.getDate("data_inicial"));
                emprestimo.setDataFinal(select.getDate("data_final"));
                emprestimo.setId_amigo(select.getInt("ID_amigo"));
                emprestimo.setId_ferramenta(select.getInt("ID_ferramenta"));
                emprestimosLista.add(emprestimo);
            }
        } catch (SQLException ErroProcuraDeEmprestimoPorAmigoID) {
            throw new ExceptionDAO("Não foi possível localizar o empréstimo solicitado erro:" + ErroProcuraDeEmprestimoPorAmigoID);

        } finally {
            encerrarConexao(cnn, pStatement);
        }

        return emprestimosLista;
    }

//Método sem parâmetros exigidos, retorna uma lista de todos os empréstimos (ativos, atrasados e encerrados)
//Utiliza a Classe ResultSet para tratar os dados recebidos do banco de dados 
    public List<Emprestimo> getAllEmprestimos() throws ExceptionDAO {

        Connection cnn = null;
        PreparedStatement pStatement = null;
        String sql = "SELECT * FROM emprestimos";
        ResultSet select;

        try {

            cnn = new ConexaoMVC().getConnection();
            pStatement = cnn.prepareStatement(sql);
            select = pStatement.executeQuery(sql);
            List<Emprestimo> emprestimosLista = new ArrayList<Emprestimo>();

            while (select.next()) {

                Emprestimo emprestimo = new Emprestimo();
                emprestimo.setID(select.getInt("ID"));
                emprestimo.setDataDateInicia(select.getDate("data_inicial"));
                emprestimo.setDataFinal(select.getDate("data_final"));
                emprestimo.setId_amigo(select.getInt("ID_amigo"));
                emprestimo.setId_ferramenta(select.getInt("ID_ferramenta"));
                emprestimosLista.add(emprestimo);
            }

            return emprestimosLista;

        } catch (SQLException erro) {
            throw new ExceptionDAO("Não foi possível retornar os Emprestimos armazenados erro:" + erro);

        } finally {
            encerrarConexao(cnn, pStatement);
        }
    }

//Retorna todos os empréstimos ativos verificando se a coluna "situacao" está preenchida com "Em andamento"
//É retornado em forma de lista de objetos do tipo "Emprestimo"
    public List<Emprestimo> getAllEmprestimosAtivos() throws ExceptionDAO {

        Connection cnn = null;
        PreparedStatement pStatement = null;
        String sql = "SELECT  * FROM emprestimos WHERE situacao = 'Em andamento'";
        ResultSet select = null;

        try {
            cnn = new ConexaoMVC().getConnection();
            pStatement = cnn.prepareStatement(sql);
            select = pStatement.executeQuery(sql);
            List<Emprestimo> emprestimoLista = new ArrayList<>();

            while (select.next()) {

                Emprestimo emprestimo = new Emprestimo();
                emprestimo.setID(select.getInt("ID"));
                emprestimo.setDataDateInicia(select.getDate("data_inicial"));
                emprestimo.setDataFinal(select.getDate("data_final"));
                emprestimo.setId_amigo(select.getInt("ID_amigo"));
                emprestimo.setId_ferramenta(select.getInt("ID_ferramenta"));
                emprestimoLista.add(emprestimo);
            }

            return emprestimoLista;
        } catch (SQLException ErroGetEmprestimosAtivos) {
            throw new ExceptionDAO("Não foi possível retirar o relatório de empréstimos ativos" + ErroGetEmprestimosAtivos);

        } finally {
            encerrarConexao(cnn, pStatement);
        }
    }

    //-------------------------------------------------Funções--------------------------------------------------------------------------------//
//Recupera o nome de um amigo pelo ID e retorna no formato de uma string para que seja utilizado no registro do empreréstimo
    public String getNomeAmigo(int ID_amigo) throws ExceptionDAO {

        Connection cnn = null;
        PreparedStatement pStatement = null;
        String sql = "SELECT  nome  FROM amigos WHERE ID_amigo = ?";
        ResultSet select = null;
        String nome = null;

        try {
            cnn = new ConexaoMVC().getConnection();
            pStatement = cnn.prepareStatement(sql);
            pStatement.setInt(1, ID_amigo);
            select = pStatement.executeQuery(sql);
            nome = select.getString("nome");
            return nome;

        } catch (SQLException erroGetNome) {
            throw new ExceptionDAO("não foi possível identificar o nome do amigo através deste ID");
        }
    }

//Recupera o nome de uma ferramenta pelo ID e retorna no formato de uma string para utilizar no registro de um novo emprestimo
    public String getNomeFerramenta(int ID_ferramenta) throws ExceptionDAO {

        Connection cnn = null;
        PreparedStatement pStatement = null;
        String sql = "SELECT  nome  FROM ferramentas WHERE ID_ferramenta = ?";
        ResultSet select = null;
        String nome = null;

        try {
            cnn = new ConexaoMVC().getConnection();
            pStatement = cnn.prepareStatement(sql);
            pStatement.setInt(1, ID_ferramenta);
            select = pStatement.executeQuery(sql);
            nome = select.getString("nome");
            return nome;
        } catch (SQLException erroGetNome) {
            throw new ExceptionDAO("não foi possível identificar o nome do amigo através deste ID");

        }

    }

    public boolean verificarEmprestimoAmigo(int amigo_id) throws ExceptionDAO {

        Connection cnn = null;
        PreparedStatement pStatement = null;
        ResultSet select = null;
        String sql = "SELECT COUNT(*) AS num_emprestimos FROM emprestimos WHERE ID_amigo = ? AND situacao = ?";

        try {

            cnn = new ConexaoMVC().getConnection();
            pStatement = cnn.prepareStatement(sql);
            pStatement.setInt(1, amigo_id);
            pStatement.setString(2, "Em andamento");

            try {
                select = pStatement.executeQuery(sql);
                if (select.next()) {
                    int numeroDeEmprestimos = select.getInt("num_emprestimos");
                    JOptionPane.showMessageDialog(null, "O amigo possuí" + numeroDeEmprestimos + "emprestimos ativos");
                    return true;

                } else {
                    return false;
                }
            } catch (SQLException erroVerificação) {
                throw new ExceptionDAO("Não foi possível realizar a contagem de empréstimos ativos para este amigo erro:" + erroVerificação);
            }

        } catch (SQLException erroAoConsultar) {
            throw new ExceptionDAO("Não foi possível realizar a consulta erro:" + erroAoConsultar);

        } finally {
            encerrarConexao(cnn, pStatement);
        }

    }
}
