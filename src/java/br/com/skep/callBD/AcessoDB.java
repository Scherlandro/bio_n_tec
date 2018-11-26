package br.com.skep.callBD;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class AcessoDB {

    private static Connection conn;
    private static final String DRIVER  = "com.mysql.jdbc.Driver";
    //private static final String DRIVER = "org.postgresql.Driver";
    private static final String URL = "jdbc:mysql://localhost:3306/Autofretebd";
   // private static final String URL = "jdbc:postgresql://localhost:5432/Autofretebd";
    private static final String USER = "root";//Usuario de administraçao DB no Mysql
   // private static final String USER = "postgres";//Usuario de administraçao DB no Postgresql
    private static final String SENHA = "84505050";

    public static Connection getConexao() throws ErroSistema {

        if (conn == null) {
            try {
                Class.forName(DRIVER);
                conn = DriverManager.getConnection(URL, USER, SENHA);
            } catch (SQLException ex) {
                throw new ErroSistema("Não foi possível conectar ao BD", ex);
            } catch (ClassNotFoundException ex) {
                throw new ErroSistema("Driver não encontrado", ex);
            }
        }
        return conn;
    }

    
    public static void desconectar() throws ErroSistema {
        if (conn == null) {
            try {
                conn.close();
                conn = null;
            } catch (SQLException ex) {
                throw new ErroSistema("Erro ao desconectar o BD", ex);
            }
        }
    }

}
