
package controle;

import dao.EmprestimoDAO;
import dao.ExceptionDAO;
import java.sql.Date;
import java.sql.SQLException;
import modelo.Emprestimo;

/**
 *
 * @author felip
 */
/*A classe Controle package "controle" serve como uma ponte que liga a visão (parte interativa do usuário e entrada de dados)
com as classes modelo, que fara o CRUD nos objetos.
Além de fazer o papel de intermediário as classes controle farão a validação dos dados antes de enviar as classes modelo 
-----------------------------------------------------------------------------------------------------------------------------
Último modificação 05/06/2024  */

public class EmprestimoControle {

    public boolean registrarEmprestimo(Date dataEmprestimo, Date dataDevolucao, int amigoID, int ferramentaID) throws ExceptionDAO, SQLException {

        if (dataEmprestimo != null && dataDevolucao != null && amigoID > 0 && ferramentaID > 0) {
            Emprestimo emprestimo = new Emprestimo(dataEmprestimo, dataDevolucao, amigoID, ferramentaID);
            emprestimo.registrarEmprestimo(emprestimo);
            return true;

        } else {
            return false;
        }

    }

    public boolean pesquisaEmprestimoPorAmigoID(int amigoID) throws ExceptionDAO {

        if (amigoID > 0) {
            EmprestimoDAO dao = new EmprestimoDAO();
            dao.getAllEmprestimos();
        }
        return true;
    }

    public boolean encerrarEmprestimo(int emprestimo_id) throws ExceptionDAO {

        if (emprestimo_id > 0) {
            Emprestimo emprestimo = new Emprestimo();
            emprestimo.EncerrarEmprestimo(emprestimo_id);
            return true;

        } else {
            return false;
        }
    }

    public boolean atualizarSituacaoEmprestimo() {
        return true;
    }

    public void getEmprestimosEmAndamento() throws ExceptionDAO {
        EmprestimoDAO dao = new EmprestimoDAO();
        dao.getAllEmprestimosAtivos();

    }
;
}