package modelo;

import dao.AmigoDAO;
import dao.ExceptionDAO;

/**
 *
 * @author felipe
 */
/*A classes Modelo, tem como sua principal função administrar a criação de novos objetos
As classes do pacote modelo são chamadas pelas classes do ppackage "Controle" para facilitar o encapsulamento dos getters e setters
-------------------------------------------------------------------------------------------------------------------------------------------------------
Última modificação efetuada em 06/06/2024 ~~ modificado por Felipe;;
 */
public class Amigo{


    private int ID_amigo;
    private String nome;
    private String contato;

//----------------------------------------------------construtores----------------------------------------------------------------//
    //construtor vazio da classe
    public Amigo() {

    }

    //construtor com parâmetros 
    public Amigo(String nome, String contato) {
        this.contato = contato;
        this.nome = nome;
    }

//-------------------------------------------------Métodos------------------------------------------------------------------------//

//chama a classe DAO para realizar o registro do novo amigo
//Tem como parâmetro um objeto da classe amigo o mesmo será passado como parâmetro no método registrarAmigo()
    public void registrarAmigo(Amigo amigo) throws ExceptionDAO {
        AmigoDAO dao = new AmigoDAO();
        dao.registrarAmigo(amigo);
    }

//Não requer parâmetro para ser utiliza, não retorna valor
//Faz a chamada do método getAmigosCadastrados() localizado na classe "dao" que retornará uma lista dos amigos registrados no sistema 
    public void getAmigosCadastrados() throws ExceptionDAO {
        AmigoDAO dao = new AmigoDAO();
        dao.getAmigosCadastrados();
    }

//Faz a exclusão de um Registro de amigo, tem como parâmletro o ID desse amigo para que seja selecionado o objeto no banco de dados
//Cria um objeto do tipo AmigoDAO e chama a função desse objeto passando como parâmetro o ID_amigo fornecido anteriormente 
    public void excluirAmigo(int ID_amigo) throws ExceptionDAO {
        AmigoDAO dao = new AmigoDAO();
        dao.excluirAmigo(ID_amigo);
    }
    
//Faz a edição do nome de um objeto do tipo Amigo, pedindo como parâmetro de edição o novo nome desejado e o ID para identificação do objeto
//Cria um objeto do tipo AmigoDAO e chama a função editarNome() e passa como parametro os ID e o novo Nome fornecidos anteriormente
    public void editarNome(Amigo amigo) throws ExceptionDAO {
        AmigoDAO dao = new AmigoDAO();
        dao.editarNome(amigo.getID(), amigo.getNome());
    }

//Cria um objeto do tipo AmigoDAO e chama a função editarContato passando como parâmetro os dados do amigo
//Utilizando dos Getters para extrair a informação do objeto fornecido como parâmetro 
    public void editarContato(Amigo amigo) throws ExceptionDAO {
        AmigoDAO dao = new AmigoDAO();
        dao.editarContato(amigo.getID(), amigo.getContato());
        
    }




//-----------------------------------------------Getter e Stter ------------------------------------------------------//
  

    public void setNome(String nome){
        this.nome = nome;
    }

    public String getNome() {
        return nome;
    }

    public void setContato(String contato) {
        this.contato = contato;
    }

    public String getContato() {
        return contato;
    }

    public void setID(int ID_amigo) {
        this.ID_amigo = ID_amigo;
    }

    public int getID() {
        return ID_amigo;
    }

}
