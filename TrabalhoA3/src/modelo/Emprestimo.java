package modelo;

import java.util.ArrayList;
import java.sql.Date;
import dao.EmprestimoDAO;
import dao.ExceptionDAO;
import java.sql.SQLException;


/**
 *
 * @author felip
 */
public class Emprestimo {

    private int ID;
    private Date dataEmprestimo;
    private Date dataDevolucao;
    private int id_ferramenta;
    private int id_amigo;
    
    
    public Emprestimo(Date dataEmprestimo, Date dataDevolucao, int amigoID, int ferramentaID){
        
        this.dataEmprestimo = dataEmprestimo;
        this.dataDevolucao = dataDevolucao;
        this.id_amigo = amigoID;
        this.id_ferramenta = ferramentaID;
       
    };
    
    public Emprestimo(){
        
    };
    
    // registra um novo emprestimo no banco de dados 
    public void registrarEmprestimo(Emprestimo emprestimo) throws ExceptionDAO{
        EmprestimoDAO dao = new EmprestimoDAO();
        dao.cadastroEmprestimo(emprestimo);
        
    }
    // encerra o empréstimo (altera a situação do empréstimo para encerrado)
    public void EncerrarEmprestimo(int emprestimo_id) throws ExceptionDAO{
        EmprestimoDAO dao = new EmprestimoDAO();
        dao.encerrarEmprestimo(emprestimo_id);
        
    }
    
    //faz o update da situação dos emprestimos
    public void updateSituacaoEmpestimos(){
        
    };
    
   
    

    // -------------------------------------Getter e setter --------------------------------------------------------------
    public void setID(int ID) {
        this.ID = ID;
    }

    public void setDataEmprestimo(Date dataEmprestimo) {
        this.dataEmprestimo = dataEmprestimo;
    }
    
    public void setDataDevolucao(Date dataDevolucao) {
        this.dataDevolucao = dataDevolucao;
    }

    public int getID() {
        return ID;
    }
    public Date getDataEmprestimo() {
        return dataEmprestimo;
    }

    public Date getDataDevolucao() {
        return dataDevolucao;
    }

    public void setDataDateInicia(Date dataEmprestimo) {
        this.dataEmprestimo = dataEmprestimo;
    }

    public int getId_ferramenta() {
        return id_ferramenta;
    }

    public void setId_ferramenta(int id_ferramenta) {
        this.id_ferramenta = id_ferramenta;
    }

    public int getId_amigo() {
        return id_amigo;
    }

    public void setId_amigo(int id_amigo) {
        this.id_amigo = id_amigo;
    }

    public void setDataFinal(Date dataDevolucao) {
        this.dataDevolucao = dataDevolucao;
    }
    // --------------------------------------------------------------------------------------------------------------
    
    
}
