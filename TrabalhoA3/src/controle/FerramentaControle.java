package controle;

import dao.ExceptionDAO;
import modelo.Ferramenta;

/**
 *
 * @author felipe
 */
/*A classe Controle package "controle" serve como uma ponte que liga a visão (parte interativa do usuário e entrada de dados)
com as classes modelo, que fara o CRUD nos objetos.
Além de fazer o papel de intermediário as classes controle farão a validação dos dados antes de enviar as classes modelo 
-----------------------------------------------------------------------------------------------------------------------------
Último modificação 05/06/2024 ~~ modificado por Felipe
 */
public class FerramentaControle {

//Faz a validação dos dados para registrar uma nova ferramenta no banco de dados, após a validação cria um objeto ferramenta com os parâmetros passados
//Caso a validação seja efetuada com sucesso retorna true, caso não seja retorna false (Sem tratamento de erro na validação ainda)
    public boolean cadastroFerramenta(String nome, String marca, double custoAquisicao) throws ExceptionDAO {

        if (nome != null && nome.length() > 0 && marca != null && marca.length() > 3 && custoAquisicao > 0) {

            Ferramenta ferramenta = new Ferramenta(nome, marca, custoAquisicao);
            ferramenta.cadastroFerramenta(ferramenta);
            return true;
        } else {
            return false;
        }
    }
}
