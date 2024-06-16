package controle;

import dao.ExceptionDAO;
import javax.swing.JOptionPane;
import modelo.Ferramenta;

/**
 *
 * @author felipe
 */
/*A classe Controle package "controle" serve como uma ponte que liga a visão (parte interativa do usuário e entrada de dados)
com as classes modelo, que fara o CRUD nos objetos.
Além de fazer o papel de intermediário as classes controle farão a validação dos dados antes de enviar as classes modelo 
-----------------------------------------------------------------------------------------------------------------------------
Último modificação 10/06/2024 ~~ modificado por Felipe;;
 */
public class FerramentaControle {

//Faz a validação dos dados para registrar uma nova ferramenta no banco de dados, após a validação cria um objeto ferramenta com os parâmetros passados
//Caso a validação seja efetuada com sucesso retorna true, caso não seja retorna false (Sem tratamento de erro na validação ainda)
    public boolean cadastroFerramenta(String nome, String marca, double custoAquisicao) throws ExceptionDAO {

        if (nome != null && nome.length() > 0 && marca != null && marca.length() > 3 && custoAquisicao > 0) {

            Ferramenta ferramenta = new Ferramenta(nome, marca, custoAquisicao);
            ferramenta.cadastroFerramenta(ferramenta);
            JOptionPane.showMessageDialog(null, "A nova ferramenta foi registrada com sucesso!");
            return true;
        } else {
            JOptionPane.showMessageDialog(null, "Não foi possível realizar o registro da nova ferramenta!");
            return false;
        }
    }

//Faz a validação do ID da ferramenta e no novo nome fornecido e atribui os dados em um novo objeto "Ferramenta"
//caso a validação seja efetuada com sucesso retorna true, caso não seja retorna false
    public boolean editarMarca(int ID_ferramenta, String novaMarca) throws ExceptionDAO {

        if (ID_ferramenta > 0 && novaMarca != null && novaMarca.length() > 3) {
            Ferramenta ferramenta = new Ferramenta();
            ferramenta.setNome(novaMarca);
            ferramenta.setID(ID_ferramenta);
            ferramenta.editarMarca(ferramenta);
            JOptionPane.showMessageDialog(null, "A marca da ferramenta foi editada para:" + novaMarca);
            return true;

        } else {
            JOptionPane.showMessageDialog(null, "Não foi possível editar a marca da ferramenta para" + novaMarca);
            return false;
        }
    }

    public boolean editarNome(int ID_ferramenta, String novoNome) throws ExceptionDAO {
        if (ID_ferramenta > 0 && novoNome != null && novoNome.length() > 3) {
            Ferramenta ferramenta = new Ferramenta();
            ferramenta.setID(ID_ferramenta);
            ferramenta.setNome(novoNome);
            ferramenta.editarNome(ferramenta);
            JOptionPane.showMessageDialog(null, "O nome da ferramenta foi editado para:" + novoNome);
            return true;

        } else {
            JOptionPane.showMessageDialog(null, "Não foi possível editar o nome da ferramenta para:" + novoNome);
        return false;
            
        }
    }

//Faz a validação para que o ID passado seja constatado como válido
    public boolean excluirFerramenta(int ID_ferramenta) throws ExceptionDAO {

        if (ID_ferramenta > 0) {
            Ferramenta ferramenta = new Ferramenta();
            ferramenta.excluirFerramenta(ID_ferramenta);
            JOptionPane.showMessageDialog(null, "A ferramenta foi excluída com sucesso!");
            return true;
        } else {
            JOptionPane.showMessageDialog(null, "Não foi possível excluir a ferramenta!");
            return false;
        }
    }

//Este método não requer validação de parâmetros pois não exige parâmetros
//Esta função foi criada por necessidade de uma padronização na chamada dos métodos 
//Controle > modelo > DAO
    public void getFerramentasRegistradas() throws ExceptionDAO {
        Ferramenta ferramenta = new Ferramenta();
        ferramenta.getFerramentasRegistradas();
    }

}
