
package controle;
import modelo.Amigo;
import modelo.amigo;


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