/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
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