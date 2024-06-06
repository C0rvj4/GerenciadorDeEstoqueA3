package controle;

import dao.ExceptionDAO;
import java.util.List;
import modelo.Amigo;
import modelo.Amigo;

/**
 *
 * @author felipe
 */
/*A classe Controle package "controle" serve como uma ponte
que liga a visão (parte interativa do usuário e entrada de dados)
com as classes modelo, que fara o CRUD nos objetos.
Além de fazer o papel de intermediário
as classes controle farão a validação dos dados antes de enviar as classes modelo 
-----------------------------------------------------------------------------------------------------------------------------
Último modificação 05/06/2024  ~~ modificado por Felipe 
 */
public class AmigoControle {

//Faz a validação dos dados, verifica se os dados não são nulos ou muito curtos,
//para garantir que apenas dados congruentes sejam adicionados ao BD
//caso o paço anterior não seja bem sucedido ele retorna um valor false (Sem tratamento ainda)
    public boolean registrarAmigo(String nome, String contato) throws ExceptionDAO {

        if (nome != null && nome.length() >= 3 && contato != null && contato.length() >= 8) {
            Amigo amigo = new Amigo();
            amigo.setNome(nome);
            amigo.setContato(contato);
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

//Faz a validação do ID e do novo nome fornecidos como parâmetro, caso sejam coerentes 
//Cria um objeto do tipo amigo e atribui os valores fornecidos 
//Chama o método de edição no package modelo para passar os novos parâmetros
    public boolean editarNome(int ID_amigo, String novoNome) throws ExceptionDAO {

        if (ID_amigo > 0 && novoNome != null && novoNome.length() > 3) {
            Amigo amigo = new Amigo();
            amigo.setNome(novoNome);
            amigo.setID(ID_amigo);
            amigo.editarNome(amigo);
            return true;

        }
        return false;
    }

//Faz a validação dos parâmetros fornecidos pela camada view para evitar erros na atribuição dos dados e na pesquisa
//Cria um objeto do tipo amigo para chamar a função de edição e passa os parâmetros fornecidos anteriormente
    public boolean editarContato(int ID_amigo, String contato) {

        if (ID_amigo > 0 && contato != null && contato.length() >= 8) {
            Amigo amigo = new Amigo();
            amigo.setID(ID_amigo);
            amigo.setContato(contato);
            amigo.editarContato(amigo);
            return true;
        }else return false;
    }



}
