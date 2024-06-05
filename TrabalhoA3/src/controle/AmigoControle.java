package controle;

import dao.ExceptionDAO;
import java.util.List;
import modelo.Amigo;
import modelo.Amigo;

/**
 *
 * @author felipe
 */
/*A classe Controle package "controle" serve como uma ponte que liga a visão (parte interativa do usuário e entrada de dados)
com as classes modelo, que fara o CRUD nos objetos.
Além de fazer o papel de intermediário as classes controle farão a validação dos dados antes de enviar as classes modelo 
-----------------------------------------------------------------------------------------------------------------------------
Último modificação 05/06/2024  ~~ modificado por Felipe 
 */
public class AmigoControle {

//Faz a validação dos dados, verifica se os dados não são nulos ou muito curtos, para garantir que apenas dados congruentes sejam adicionados ao BD
//caso o paço anterior não seja bem sucedido ele retorna um valor false (Sem tratamento ainda)
    public boolean registrarAmigo(String nome, String contato) throws ExceptionDAO {

        if (nome != null && nome.length() >= 3 && contato != null && contato.length() >= 8) {
            Amigo amigo = new Amigo(contato, nome);
            amigo.registrarAmigo(amigo);
            return true;
        } else {
            return false;
        }
    }

//Chama a função getAmigosCadastrados() da classe Amigo (package modelo)
    public void getAmigosCadastrados() throws ExceptionDAO {
        Amigo amigo = new Amigo();
        amigo.getAmigosCadastrados();
    }

//Faz a validação do ID_amigo, caso seja ele cria um objeto do tipo amigo
//Chama o método excluirAmigo() que tem como parâmetro o ID fornecido
//Caso a validação não seja bem sucedida ela retorna um valor false (sem tratamento ainda)
    public boolean excluirAmigo(int ID_amigo) throws ExceptionDAO {

        if (ID_amigo > 0) {
            Amigo amigo = new Amigo();
            amigo.excluirAmigo(ID_amigo);
            return true;

        } else {
            return false;
        }
    }

}
