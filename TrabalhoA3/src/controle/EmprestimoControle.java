package controle;

import dao.EmprestimoDAO;
import dao.ExceptionDAO;
import java.sql.Date;
import java.sql.SQLException;
import javax.swing.JOptionPane;
import modelo.Emprestimo;

/**
 *
 * @author felipe
 */
/*A classe Controle package "controle" serve como uma ponte que liga a visão (parte interativa do usuário e entrada de dados)
com as classes modelo, que fara o CRUD nos objetos.
Além de fazer o papel de intermediário as classes controle farão a validação dos dados antes de enviar as classes modelo 
-----------------------------------------------------------------------------------------------------------------------------
Último modificação 10/06/2024  ~~ modificado por Felipe ;;
 */



public class EmprestimoControle {

//Verifica se os parametros fornecidos não são nulos, para previnir a adição de dados incongruentes ao Banco de Dados.
//Caso obtenha sucesso na validação Cria um objeto emprestimo com os parâmetros fornecidos e chamada o método registrarEmprestimo() passando como parâmetro o objeto criado
//Caso não obtenha sucesso retorna um false (ainda não tem tratativa para o retorno falso)
    public boolean registrarEmprestimo(Date dataEmprestimo, Date dataDevolucao, int amigoID, int ferramentaID) throws ExceptionDAO, SQLException {

        if (dataEmprestimo != null && dataDevolucao != null && amigoID > 0 && ferramentaID > 0) {
            Emprestimo emprestimo = new Emprestimo(dataEmprestimo, dataDevolucao, amigoID, ferramentaID);
            emprestimo.registrarEmprestimo(emprestimo);
            JOptionPane.showMessageDialog(null, "O novo empréstimo foi registrado com sucesso!");
            return true;
            

        } else {
            JOptionPane.showMessageDialog(null, "Não foi possível realizar o regisrto do novo empréstio!");
            return false;
        }

    }

//Método que tem como objetivo uma consulta. Passa como parâmetro o ID do amigo que fez o empréstimo, para assim retornar os empréstimos ecistentes com esse ID
//Faz a validação dos ID passado antes de chamar o Método getEmprestimosPorAmigoID() localizado no package DAO
//Caso a validação não tenha sucesso retorna false
    public boolean pesquisaEmprestimoPorAmigoID(int amigoID) throws ExceptionDAO {

        if (amigoID > 0) {
            EmprestimoDAO dao = new EmprestimoDAO();
            dao.getEmprestimoPorAmigoID(amigoID);
            return true;
        }else{
            return false;
        }
        
    }

//Encerra um emprestimo significa alterar a coluna "situacao" do emprestimo no banco de dados, para "encerrado"
//Tem como parâmetro um ID do emprestimo, faz a validação se o ID é valido, caso seja cria um objeto do tipo empréstimo e chama a função EncerrarEmprestimo do package "modelo"
//caso a validação falhe, retorna false
    public boolean encerrarEmprestimo(int emprestimo_id) throws ExceptionDAO {

        if (emprestimo_id > 0) {
            Emprestimo emprestimo = new Emprestimo();
            emprestimo.EncerrarEmprestimo(emprestimo_id);
            JOptionPane.showMessageDialog(null, "O empréstimo foi encerrado com sucesso!");
            return true;

        } else {
            JOptionPane.showMessageDialog(null, "Não foi possível realizar o encerramento do empréstimo!");
            return false;
        }
    }

//Em fase de produção ~~
    public boolean atualizarSituacaoEmprestimo() {
        return true;
    }

//Não requer parâmetro portanto ele cria um objeto da classe dao e chama direto o método que para procurar todos os empréstimos com situação "Em andamento"
    public void getEmprestimosEmAndamento() throws ExceptionDAO {
        EmprestimoDAO dao = new EmprestimoDAO();
        dao.getAllEmprestimosAtivos();

    }
    
    
    public boolean verificarEmprestimoAmigo(int amigo_ID) throws ExceptionDAO{
        
        if(amigo_ID > 0){
            Emprestimo emprestimo = new Emprestimo();
            emprestimo.verificarEmprestimoAmigo(amigo_ID);
            return true;
            
        }else{
            JOptionPane.showMessageDialog(null,"Não foi possível identificar um amigo com o ID:" + amigo_ID);
            return false;
        }
    }
        
;
}
