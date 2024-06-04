package modelo;
import dao.AmigoDAO;


public class Amigo{

    private int ID_amigo;
    private String nome;
    private String contato;

//----------------------------------------------------construtores----------------------------------------------------------------//

    //construtor vazio da classe
    public Amigo(){

    }

    //construtor com parâmetros 
    public Amigo(String nome, String contato){
        this.contato = contato;
        this.nome = nome;
    }


//-----------------------------------------------Getter e Stter ------------------------------------------------------//

    public void setNome(String nome){
        this.nome = nome;
    }

    public String getNome(){
        return nome;
    }

    public void setContato(String contato){
        this.contato = contato;
    }

    public String getContato(){
        return contato;
    }

    public void setAmigoID(int ID_amigo){
        this.ID_amigo = ID_amigo;
    }

    public int getAmigoID(){
        return ID_amigo;
    }

//---------------------------------------------------Funções---------------------------------------------------------//

    //chama a classe DAO para realizar o registro do novo amigo
    public void registrarAmigo(Amigo amigo){
        AmigoDAO dao = new AmigoDAO();
        dao.registrarAmigo(amigo);
    }

    public List<Amigo> getAmigosCadastrados(){
        AmigoDAO dao = new AmigoDAO();
        dao.getAmigosCadastrados();
    }

    
}