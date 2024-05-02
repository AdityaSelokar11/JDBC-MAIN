// You are using Java
[15:03] Harshala Vijaykumar Athani
JDBC
use ri_db;
create table laptop(id int,model varchar(20),price int,allocated boolean);
insert into laptop values(1,"i6",45000,true);
insert into laptop values(2,"i9",89000,false);
insert into laptop values(3,"i10",121000,true);
 
// You are using Java
import java.util.*;
import java.sql.*;
class main{
    static void insertData(Connection c, Scanner s){
        // int input = Integer.parseInt(s.nextLine());
        int id = Integer.parseInt(s.nextLine());
        String vname = s.nextLine();
        int price = Integer.parseInt(s.nextLine());
        boolean allocated = Boolean.parseBoolean(s.nextLine());
        try(PreparedStatement pst = c.prepareStatement("insert into laptop values(?,?,?,?)")){
            pst.setInt(1,id);
            pst.setString(2,vname);
            pst.setInt(3,price);
            pst.setBoolean(4,allocated);
            int x = pst.executeUpdate();
            if(x>0)
            System.out.println("Data inserted successfully..");
        }
         catch(SQLException e){
             e.printStackTrace();
         }
    }
    static void displayAll(Connection c){
        try(PreparedStatement pst = c.prepareStatement("select * from laptop")){
                ResultSet rs = pst.executeQuery();
                while(rs.next()){
                    System.out.println("Laptop Id: "+rs.getInt(1)+", Laptop Model: "+rs.getString(2)+", Laptop Price: "+rs.getInt(3)+", Allocated: "+rs.getBoolean(4));
                }
        }
        catch(SQLException e){
            e.printStackTrace();
        }
    }
    static void updateData(Connection c,Scanner s){
        int id = Integer.parseInt(s.nextLine());
        int price = Integer.parseInt(s.nextLine());
        boolean isAllocated = s.nextBoolean();
        try(PreparedStatement pst = c.prepareStatement("update laptop set price=?, allocated=? where id=?")){
            pst.setInt(1,price);
            pst.setBoolean(2,isAllocated);
            pst.setInt(3,id);
            int x = pst.executeUpdate();
            if(x>0)
            System.out.println("Data Updated Successfully..");
        }
        catch(SQLException e){
            e.printStackTrace();
        }
    }
    static void deleteData(Connection c,Scanner s){
        int id = Integer.parseInt(s.nextLine());
        try(PreparedStatement pst = c.prepareStatement("delete from laptop where id=?")){
            pst.setInt(1,id);
            int x = pst.executeUpdate();
            if(x>0)
            System.out.println("Row deleted Successfully..");
        }
        catch(SQLException e){
            e.printStackTrace();
        }
    }
    public static void main(String args[]){
        try(Connection c = DriverManager.getConnection("jdbc:mysql://localhost/ri_db","test","test123")){
            Scanner s = new Scanner(System.in);
            int choice = Integer.parseInt(s.nextLine());
            switch(choice){
                case 1:
                    insertData(c,s);
                    displayAll(c);
                    break;
                case 2:
                    displayAll(c);
                    break;
                case 3:
                    updateData(c,s);
                    displayAll(c);
                    break;
                case 4:
                    deleteData(c,s);
                    displayAll(c);
                    break;
            }
        }
        catch(SQLException e){
            e.printStackTrace();
        }
    }
}
