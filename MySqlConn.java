import org.postgresql.util.PSQLException;

import java.sql.*;
import java.util.ArrayList;

class MysqlConn {

    public static String getOutletNames(String submiterCode ){
        //String a[]=new String[2];
        String aa="";
        String bb="";
        try {
            Class.forName("org.postgresql.Driver");
            Connection con = DriverManager.getConnection(
                    "jdbc:postgresql://localhost:5432/postgres", "postgres", "pavan");
            Statement stmt = con.createStatement();
            String  query="select * from outlet_Id where id='"+submiterCode+"'";
            System.out.println("query:"+query);
            ResultSet rs = stmt.executeQuery(query);

            while (rs.next())
   //             System.out.println("   OutLet Name: "+rs.getString(2)+"     Outlet Code: "+rs.getString(1));
            aa=rs.getString(1);
            //bb=rs.getString(2);
            con.close();
        } catch (Exception e) {
            System.out.println(e);
        }

        if(aa.equals(submiterCode)){
        System.out.println("This Submiter code is valid : "+aa);
        }else{
            System.out.println(submiterCode+"  : This Submiter code is not valid : "+aa);
        }
        return aa;
    }

    public static void main(String args[]) {
        getOutletNames("abc111");
    }
}