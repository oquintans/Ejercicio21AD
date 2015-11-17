/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package baserelacionald;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author oracle
 */
public class BaseRelacionalD {

    Connection conn;
    ResultSet rs;

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        BaseRelacionalD brD = new BaseRelacionalD();
        brD.connection();
        brD.listar();
    }

    public void connection() {
        try {
            String driver = "jdbc:oracle:thin:";
            String host = "localhost.localdomain"; // tambien puede ser una ip como "192.168.1.14"
            String porto = "1521";
            String sid = "orcl";
            String usuario = "hr";
            String password = "hr";
            String url = driver + usuario + "/" + password + "@" + host + ":" + porto + ":" + sid;

            conn = DriverManager.getConnection(url);
            System.out.println("Conexion Establecida.\n");

        } catch (SQLException ex) {
            Logger.getLogger(BaseRelacionalD.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void listar() {
        try {

            PreparedStatement pS = conn.prepareStatement("select produtos.* from produtos where prezo>?");
            pS.setInt(1, 5);
            rs = (ResultSet) pS.executeQuery();

            ResultSetMetaData rsmd = rs.getMetaData();

            for (int i = 1; i <= rsmd.getColumnCount(); i++) {
                System.out.print(rsmd.getColumnName(i) + ", ");
                System.out.print(rsmd.getColumnTypeName(i) + ", ");
                System.out.println(rsmd.getColumnDisplaySize(i));
            }
            rs.close();

        } catch (SQLException ex) {
            Logger.getLogger(BaseRelacionalD.class.getName()).log(Level.SEVERE, null, ex);

        }

    }

}
