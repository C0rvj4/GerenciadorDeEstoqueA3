/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo;
import dao.ExceptionDAO;
import dao.FerramentaDAO;

/**
 *
 * @author felipe
 */
/*A classes Modelo, tem como sua principal função administrar a criação de novos objetos
As classes do pacote modelo são chamadas pelas classes do ppackage "Controle" para facilitar o encapsulamento dos getters e setters
-------------------------------------------------------------------------------------------------------------------------------------------------------
Última modificação efetuada em 05/06/2024 ~~ modificado por Felipe
*/  

public class Ferramenta {


   private String nome;
   private String marca;
   private double custoDeAquisicao;
   
//-----------------------------------------------------------------------------------------------------------------------------------//


//construtor da classe Ferramenta
    public Ferramenta(String nome, String marca, double custoDeAquisicao) {
        this.nome = nome;
        this.marca = marca;
        this.custoDeAquisicao = custoDeAquisicao;
    }
    
    
//método de registro da classe ferramenta, tem como parâmetro um objeto da classe ferramenta
//cria um objeto da classe dao e chama o método cadastroFerramea() passando como parâmetro o mesmo objeto passado como parâmetro anteriormente;

   public void cadastroFerramenta(Ferramenta ferramenta) throws ExceptionDAO{
       FerramentaDAO dao = new FerramentaDAO();
       dao.cadastroFerramenta(ferramenta);
   };
   
    public String getNome() {
        return nome;
    }

    public String getMarca() {
        return marca;
    }

    public double getCustoDeAquisicao() {
        return custoDeAquisicao;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public void setCustoDeAquisicao(double custoDeAquisicao) {
        this.custoDeAquisicao = custoDeAquisicao;
    }
    
   
}
