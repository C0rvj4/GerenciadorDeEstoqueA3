package dao;

/**
 *
 * @author felipe
 */
/* A classe ExceptionDAO foi projetada para lidar com exceções de acesso do banco de dados.
-----------------------------------------------------------------------------------------------------------------------------
Último modificação 05/06/2024 ~~ modificado por Felipe::
*/

public class ExceptionDAO extends Exception {
    
    public ExceptionDAO(String mensagem){
            super(mensagem);
    } 
    
}
