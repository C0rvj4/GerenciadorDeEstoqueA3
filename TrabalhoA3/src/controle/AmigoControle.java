
package controle;
import modelo.Amigo;
import modelo.amigo;

/**
 *
 * @author felipe
 */
/*A classe Controle package "controle" serve como uma ponte que liga a visão (parte interativa do usuário e entrada de dados)
com as classes modelo, que fara o CRUD nos objetos.
Além de fazer o papel de intermediário as classes controle farão a validação dos dados antes de enviar as classes modelo 
-----------------------------------------------------------------------------------------------------------------------------
Última modificação 06/06/2024  ~~ modificado por Felipe;;

*/

public class AmigoControle{

//Faz a validação dos dados, verifica se os dados não são nulos ou muito curtos, para garantir que apenas dados congruentes sejam adicionados ao BD
//caso o paço anterior não seja bem sucedido ele retorna um valor false (Sem tratamento ainda)
    public boolean registrarAmigo(String nome, String contato){

        if(nome != null && nome.length() >= 3 && contato != null && contato.length() >= 8){
            Amigo amigo = new Amigo(contato, nome);
            amigo.registrarAmigo(amigo);
            return true;
        }else return false;
    }

//Chama a função getAmigosCadastrados() da classe Amigo (package modelo)
    public List<Amigo> getAmigosCadastrados(){
        Amigo amigo = new Amigo();
        amigo.getAmigosCadastrados();
    }

//Faz a validação do ID_amigo, caso seja ele cria um objeto do tipo amigo
//Chama o método excluirAmigo() que tem como parâmetro o ID fornecido
//Caso a validação não seja bem sucedida ela retorna um valor false (sem tratamento ainda)
    public boolean excluirAmigo(int ID_amigo){

        if(ID_amigo != null && ID_amigo > 0){
            Amigo amigo = new Amigo();
            amigo.excluirAmigo(ID_amigo);
            return true

        }else return false 
    }

    public boolean editarNome(int ID_amigo, String novoNome){
        
        if(ID_amigo > 0 && novoNome != null && novoNome.length() > 3){
            Amigo amigo = new amigo();
            amigo.setNome(novoNome);
            amigo.setAmigoID(ID_amigo);
            amigo.editarNome(amigo);
            return true; 

        }return false;
    }

}