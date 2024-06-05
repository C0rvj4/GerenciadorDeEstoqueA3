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
Última modificação efetuada em 05/06/2024 ~~ modificado por Felipe;;
 */
public class Amigo {

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
//Re

    public void excluirAmigo(int ID_amigo) throws ExceptionDAO {
        AmigoDAO dao = new AmigoDAO();
        dao.excluirAmigo(ID_amigo);
    }

//-----------------------------------------------Getter e Stter ------------------------------------------------------//
    public void setNome(String nome) {
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

    public void setAmigoID(int ID_amigo) {
        this.ID_amigo = ID_amigo;
    }

    public int getAmigoID() {
        return ID_amigo;
    }

}
