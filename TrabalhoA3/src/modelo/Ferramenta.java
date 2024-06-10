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
Última modificação efetuada em 09/06/2024 ~~ modificado por Felipe;;
 */
public class Ferramenta {

    private int ID_ferramenta;
    private String nome;
    private String marca;
    private double custoDeAquisicao;

//-----------------------------------------------------Construtores-----------------------------------------------------------------//
//construtor da classe Ferramenta
    public Ferramenta(String nome, String marca, double custoDeAquisicao) {
        this.nome = nome;
        this.marca = marca;
        this.custoDeAquisicao = custoDeAquisicao;
    }

//Construtor vazio da classe ferramenta
    public Ferramenta() {

    }

    
    
//-----------------------------------------------------------Métodos----------------------------------------------------------------//
//método de registro da classe ferramenta, tem como parâmetro um objeto da classe ferramenta
//cria um objeto da classe dao e chama o método cadastroFerramea() passando como parâmetro o mesmo objeto passado como parâmetro anteriormente;
    public void cadastroFerramenta(Ferramenta ferramenta) throws ExceptionDAO {
        FerramentaDAO dao = new FerramentaDAO();
        dao.cadastroFerramenta(ferramenta);
    }

//Cria um objeto dao e chama o método editarMarca para editar a coluna marca no banco de dados, passando como 
//Parâmetros os dados do objeto criado pela classe FerramentaControle
    public void editarMarca(Ferramenta ferramenta) throws ExceptionDAO {
        FerramentaDAO dao = new FerramentaDAO();
        dao.editarMarca(ferramenta.getID(), ferramenta.getMarca());

    }
    
    public void editarNome(Ferramenta ferramenta) throws ExceptionDAO {
        FerramentaDAO dao = new FerramentaDAO();
        dao.editarNome(ferramenta.getID(), ferramenta.getNome());
    }

//Cria um objeto dao e chama o método excluirAmigo passando como parâmetro o ID para localiz no banco de dados
    public void excluirFerramenta(int ID_ferramenta) throws ExceptionDAO{
        FerramentaDAO dao = new FerramentaDAO();
        dao.excluirFerramenta(ID_ferramenta);

    }

//Método para retirar um relatório, não exige parâmetros, chama a função getFerramentasRegistradas da classe ferramentaDAO
    public void getFerramentasRegistradas() throws ExceptionDAO {
        FerramentaDAO dao = new FerramentaDAO();
        dao.getFerramentasRegistradas();

    }

    

    //-------------------------------------Getters e Seters--------------------------------------------------------------------------//
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

    public int getID() {
        return ID_ferramenta;
    }

    public void setID(int ID_ferramenta) {
        this.ID_ferramenta = ID_ferramenta;
    }

}
