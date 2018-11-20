package br.com.skep.callBD;


import java.sql.*;
import java.sql.Connection;
//import org.gjt.mm.mysql.*;
import java.sql.DriverManager;
import java.sql.SQLException;
import javax.swing.JOptionPane;

public class AcessoMySql {
    public static String status = "Não conectou...";
    private com.mysql.jdbc.Statement stmt;
  
    public AcessoMySql() {
        conectar();
    }

    public static java.sql.Connection conectar() {
        Connection conn = null;
        try {
            String driver = "com.mysql.jdbc.Driver";
            Class.forName(driver);
			String url = "jdbc:mysql://localhost:3306/Autofretebd";
            String user = "root";
          //String url = "jdbc:mysql://192.168.1.3/AssistanceTec";
          //String user = "servidor2";
            String senha = "84505050";
            conn = DriverManager.getConnection(url, user, senha);
            //Testa sua conexão// 
            if (conn != null) {
                status = ("STATUS--->Conectado com sucesso!");
            } else {
                status = ("STATUS--->Não foi possivel realizar conexão");
            }
            return conn;
        } catch (ClassNotFoundException Driver) {
            JOptionPane.showMessageDialog(null, "Driver não localizado!" + Driver);
            return null;
        } catch (SQLException Fonte) {
            JOptionPane.showMessageDialog(null, "Não foi possivel conectar ao banco!" + Fonte);
            return null;
        }
    }

    public static boolean desconectar() {
        // boolean result = true;
        try {
            AcessoMySql.conectar().close();
          //  conn.close();
            return true;
            // JOptionPane.showMessageDialog(null,"Conexão do DB fechada");
        } catch (SQLException erroSQL) {
            JOptionPane.showMessageDialog(null, "Não foi possivel " + erroSQL
                    + "Desconectar ao banco!" + erroSQL.getMessage());
            return false;
        }
    }
        public static java.sql.Connection restartConection(){
            desconectar();
            return AcessoMySql.conectar();
        }
        
        /*
            public void Comandos(String cad) {
        try {
            stmt = (com.mysql.jdbc.Statement) conectar().createStatement();
            stmt.execute(cad);
            stmt.close();
            desconectar();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro de conexão");
        }
    }
*/
}
