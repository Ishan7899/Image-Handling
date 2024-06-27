import javax.xml.transform.Result;
import java.io.*;
import java.sql.*;
import java.util.Scanner;

public class Image_rev {
    public static void main(String[] args) {

        String url = "jdbc:mysql://localhost:3306/mydatabase";
        String username = "root";
        String password = "ishangadi1235";
        String folder_path = "C:\\Users\\ASUS\\Downloads\\Test\\";
        String sql = "SELECT image_data from image_table where image_id = (?)";

        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            System.out.println("Drivers loaded successfully!!!");
        }catch(ClassNotFoundException e){
            System.out.println(e.getMessage());
        }
        try{
            Connection con = DriverManager.getConnection(url,username,password);
            System.out.println("Connection established successfully");

            PreparedStatement preparedStatement = con.prepareStatement(sql);
            preparedStatement.setInt(1,1);
            ResultSet resultSet = preparedStatement.executeQuery();
            if(resultSet.next()){
                byte[] image_data = resultSet.getBytes("image_data");
                String image_path = folder_path + "extractedImage";
                OutputStream outputStream = new FileOutputStream(image_path);
                outputStream.write(image_data);
                System.out.println("Image insertion successful");
            }else{
                System.out.println("Image not found");
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
