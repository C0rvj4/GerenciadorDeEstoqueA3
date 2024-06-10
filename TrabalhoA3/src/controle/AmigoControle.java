package controle;

import dao.ExceptionDAO;
import java.util.List;
import javax.swing.JOptionPane;
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

Os blocos finally ao final dos métodos verifica se a conexão com o banco de dados e com o pStatement ainda é existente, caso seja 
ocorre a tentativa de encerrar a conexão, caso não seja possível é lançado um erro 
-----------------------------------------------------------------------------------------------------------------------------
Último modificação 10/06/2024  ~~ modificado por Felipe 
 */
public class AmigoControle {

//Faz a validação dos dados, verifica se os dados não são nulos ou muito curtos,
//para garantir que apenas dados congruentes sejam adicionados ao BD
//caso o paço anterior não seja bem sucedido ele retorna um valor false (Sem tratamento ainda)
    public boolean registrarAmigo(String nome, String contato) throws ExceptionDAO {

        boolean condicao = false;
        if (nome != null && nome.length() >= 3 && contato != null && contato.length() >= 8) {
            Amigo amigo = new Amigo();
            amigo.setNome(nome);
            amigo.setContato(contato);
            amigo.registrarAmigo(amigo);
            JOptionPane.showMessageDialog(null, "O amigo foi registrado com sucesso!");
            return true;
        } else {
            JOptionPane.showMessageDialog(null, "Ocorreu um erro ao registrar o amigo, tente novamente!");
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
            JOptionPane.showMessageDialog(null, "O amigo foi excluido com sucesso!");
            return true;

        } else {
            JOptionPane.showMessageDialog(null,"Não foi possível excluir o amigo!");
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
            JOptionPane.showMessageDialog(null, "O nome do amigo foi alterado para:" + novoNome);
            return true;

        }else{
        JOptionPane.showMessageDialog(null, "ão foi possível alterrar o nome para:" + novoNome);
        return false;
        }
        
    }

//Faz a validação dos parâmetros fornecidos pela camada view para evitar erros na atribuição dos dados e na pesquisa
//Cria um objeto do tipo amigo para chamar a função de edição e passa os parâmetros fornecidos anteriormente
    public boolean editarContato(int ID_amigo, String novoContato) throws ExceptionDAO {

        if (ID_amigo > 0 && contato != null && novoContato.length() >= 8) {
            Amigo amigo = new Amigo();
            amigo.setID(ID_amigo);
            amigo.setContato(novoContato);
            amigo.editarContato(amigo);
            JOptionPane.showMessageDialog(null, "O contato do amigo foi editado para:" + novoContato);
            return true;
        } else {
            JOptionPane.showMessageDialog(null, "O contato do amigo foi editado para:" + novoContato);
            return false;
        }
    }

}
