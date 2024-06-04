package controle;

import dao.ExceptionDAO;
import modelo.Ferramenta;

/**
 *
 * @author felip
 */
public class FerramentaControle {

    public boolean cadastroFerramenta(String nome, String marca, double custoAquisicao) throws ExceptionDAO {

        if (nome != null && nome.length() > 0 && marca != null && marca.length() > 0 && custoAquisicao > 0) {

            Ferramenta ferramenta = new Ferramenta(nome, marca, custoAquisicao);
            ferramenta.cadastroFerramenta(ferramenta);
            return true;} 
            
         else {return false;}
    }
}