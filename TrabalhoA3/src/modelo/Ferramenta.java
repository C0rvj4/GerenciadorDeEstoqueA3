/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo;
import dao.ExceptionDAO;
import dao.FerramentaDAO;

/**
 *
 * @author felip
 */
public class Ferramenta {


   private String nome;
   private String marca;
   private double custoDeAquisicao;
   
//-----------------------------------------------------------------------------------------------------------------------------------//

    public Ferramenta(String nome, String marca, double custoDeAquisicao) {
        this.nome = nome;
        this.marca = marca;
        this.custoDeAquisicao = custoDeAquisicao;
    }
    
    
 
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
