package controle;

import dao.ExceptionDAO;
import modelo.Ferramenta;

/**
 *
 * @author felip
 */
/*A classe Controle package "controle" serve como uma ponte que liga a visão (parte interativa do usuário e entrada de dados)
com as classes modelo, que fara o CRUD nos objetos.
Além de fazer o papel de intermediário as classes controle farão a validação dos dados antes de enviar as classes modelo 
-----------------------------------------------------------------------------------------------------------------------------
Último modificação 05/06/2024  */

public class FerramentaControle {

    public boolean cadastroFerramenta(String nome, String marca, double custoAquisicao) throws ExceptionDAO {

        if (nome != null && nome.length() > 0 && marca != null && marca.length() > 0 && custoAquisicao > 0) {

            Ferramenta ferramenta = new Ferramenta(nome, marca, custoAquisicao);
            ferramenta.cadastroFerramenta(ferramenta);
            return true;} 
            
         else {return false;}
    }
}