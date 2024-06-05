
package controle;
import modelo.Amigo;
import modelo.amigo;

/**
 *
 * @author felip
 */
/*A classe Controle package "controle" serve como uma ponte que liga a visão (parte interativa do usuário e entrada de dados)
com as classes modelo, que fara o CRUD nos objetos.
Além de fazer o papel de intermediário as classes controle farão a validação dos dados antes de enviar as classes modelo 
-----------------------------------------------------------------------------------------------------------------------------
Último modificação 05/06/2024  */

public class AmigoControle{

//Faz a validação dos dados, verifica se os dados não são nulos ou muito curtos, para garantir que apenas dados congruentes sejam adicionados ao BD
    public boolean registrarAmigo(String nome, String contato){

        if(nome != null && nome.length() >= 2 && contato != null && contato.length() >= 2){
            Amigo amigo = new Amigo(contato, nome);
            amigo.registrarAmigo(amigo);
            return true;
        }else return false;
    }

//Chama a função
    public List<Amigo> getAmigosCadastrados(){
        Amigo amigo = new Amigo();
        amigo.getAmigosCadastrados();
    }

}