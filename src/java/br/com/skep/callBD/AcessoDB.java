package br.com.skep.callBD;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class AcessoDB {
    
    private static Connection conn;
    //private static final String DRIVER = "org.postgresql.Driver";
    // private static final String USER = "postgres";//Usuario de administraçao DB no Postgresql
    // private static final String URL = "jdbc:postgresql://localhost:5432/Bionaturatec";    
    
    private static final String DRIVER = "com.mysql.jdbc.Driver";
    private static final String URL = "jdbc:mysql://localhost:3306/Bionaturatec";
    // private static final String URL = "jdbc:mysql://node50429-bionaturatec.jelastic.saveincloud.net/bionaturatec";
    private static final String USER = "root";
    private static final String SENHA = "84505050";
    // private static final String SENHA = "TRTvgt68596";

    public static Connection getConexao() throws TratarErros {

        if (conn == null) {
            try {
                Class.forName(DRIVER);
                DriverManager.registerDriver(new com.mysql.jdbc.Driver());
                conn = DriverManager.getConnection(URL, USER, SENHA);
            } catch (SQLException ex) {
                throw new TratarErros("Não foi possível conectar ao BD", ex);
            } catch (ClassNotFoundException ex) {
                throw new TratarErros("Driver não encontrado", ex);
            }
        }
        return conn;
    }

    public static void desconectar() throws TratarErros {
        if (conn == null) {
            try {
                conn.close();
                conn = null;
            } catch (SQLException ex) {
                throw new TratarErros("Erro ao desconectar o BD", ex);
            }
        }
    }

}
