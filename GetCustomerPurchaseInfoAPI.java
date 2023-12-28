import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import org.json.simple.JSONObject;
import java.util.logging.Level;
import java.util.logging.Logger;

@WebServlet("/getCustomerPurchaseInfo")
public class GetCustomerPurchaseInfoServlet extends HttpServlet {

   
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        JSONObject obj=new JSONObject();
   Connection con = null;
        System.out.println("welcome to log");
        try {
            Class.forName("com.mysql.jdbc.Driver");
            System.out.println("Driver Loaded");

            con = java.sql.DriverManager.getConnection("jdbc:mysql://localhost:3306/your_database_name", "root", "root");
            System.out.println("Connected");

           String query = "SELECT c.CustomerID, p.PurchaseOrderID, p.ProductName, p.Quantity " +
                           "FROM customer c " +
                           "JOIN purchase p ON c.CustomerID = p.CustomerID";

            PreparedStatement statement = con.prepareStatement(query);
                 ResultSet resultSet = statement.executeQuery();

            
                while (resultSet.next()) {
                    
                    // Process results
                    out.println("{");
                 out.println("customerId" + ":"+ "'"+resultSet.getInt("CustomerID")+"';");
                 out.println("purchaseOrder:[");
                           out.println("purchaseOrderID : '" 
                            + resultSet.getInt("PurchaseOrderID") + "';"); 
                           
                           out.println("productName: '" 
                           + resultSet.getString("ProductName") + "';");
                           
                           out.println("quantity: '" 
                           + resultSet.getString("Quantity") + "';");

                }
                
            }
         catch (SQLException e) {
            out.println("Error: " + e.getMessage());
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(GetCustomerPurchaseInfoServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
