import javax.xml.transform.Result;
import java.io.*;
import java.sql.*;
import java.util.Scanner;

public class imageEG {
    public static void main(String[] args) {

        String url = "jdbc:mysql://localhost:3306/mydatabase";
        String username = "root";
        String password = "ishangadi1235";
        String image_path = "C:\\Users\\ASUS\\Pictures\\Screenshots\\Screenshot (6).png";
        String sql = "INSERT INTO image_table(image_data) VALUES(?)";

        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            System.out.println("Drivers loaded successfully!!!");
        }catch(ClassNotFoundException e){
            System.out.println(e.getMessage());
        }
        try{
            Connection con = DriverManager.getConnection(url,username,password);
            System.out.println("Connection established successfully");

            FileInputStream fileInputStream = new FileInputStream(image_path);
            byte[] imageData = new byte[fileInputStream.available()];
            fileInputStream.read(imageData);

            PreparedStatement preparedStatement = con.prepareStatement(sql);
            preparedStatement.setBytes(1,imageData);
            int affectedRows = preparedStatement.executeUpdate();
            if(affectedRows>0){
                System.out.println("Image inserted successfully");
            }else{
                System.out.println("Image not inserted");
            }

        }catch(SQLException e){
            System.out.println("Connection failed");
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
