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

/**
 *
 * @author felip
 */
public class EmprestimoDAO {

    public void cadastroEmprestimo(Emprestimo emprestimo) throws ExceptionDAO {

        String sql = "insert into emprestimos (data_inicial, data_final, ID_amigos, ID_ferramenta, situacao) value (?, ? ,? ,?, ?)";
        Connection cnn = null;
        PreparedStatement pStatement = null;

        try {
            cnn = new ConexaoMVC().getConnection();
            pStatement = cnn.prepareStatement(sql);
            pStatement.setDate(1, emprestimo.getDataEmprestimo());
            pStatement.setDate(2, emprestimo.getDataDevolucao());
            pStatement.setInt(3, emprestimo.getId_amigo());
            pStatement.setInt(4, emprestimo.getId_ferramenta());
            pStatement.setString(5, "Em andamento");
            pStatement.execute();
            JOptionPane.showMessageDialog(null, "O empréstimo foi registrado com sucesso ");

        } catch (SQLException erro) {
            throw new ExceptionDAO("Não foi possível registrar o empréstimo erro:" + erro);
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

    // Função para recuperar um empréstimo usando como parâmetro o ID do amigo que fez o empréstimo, utiliza a classe ResultSet para fazer o tratamento dos dados obtidos
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

        return emprestimosLista;
    }

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

    public void updateSituacaoDoEmprestimo() throws ExceptionDAO {

        Connection cnn = null;
        PreparedStatement pStatement = null;

    }
}
