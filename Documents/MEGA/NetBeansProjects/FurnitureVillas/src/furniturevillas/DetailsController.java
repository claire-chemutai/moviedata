/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package furniturevillas;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.stage.Modality;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author study
 */
public class DetailsController implements Initializable {
    ObservableList<ChairsClass> theChairList= FXCollections.observableArrayList();
    ObservableList<TableClass> theTableList= FXCollections.observableArrayList();
    ObservableList<CanopiesClass> theCanopyList= FXCollections.observableArrayList();
    ObservableList<RecentRecordsClass> theRecentList= FXCollections.observableArrayList();
    
    private Label lblDetail;
    private Stage detailStage = null;
    ObservableList<Person> data;
    //=FXCollections.observableArrayList();
    
    
    private final EditDetailsController editCustomerController = null;
    private AddingDetailsController addCustomerController=null;

    @FXML
    private TableView<ChairsClass> chairsTable;
    @FXML
    private TableView<TableClass> tablesTable;
    @FXML
    private TableView<CanopiesClass> canopiesTable;
    @FXML
    private TableView<RecentRecordsClass> recentRecordsTable;
     @FXML
    private TableView<Person> customerDetailsTable;
    @FXML
    private TextField searchDetailField;
    @FXML
    private Button addDetailBtn;
    @FXML
    private Button editDetailBtn;
    
    @FXML
    private TableColumn<ChairsClass, String> customerNameChairsCol;
    @FXML
    private TableColumn<ChairsClass, Integer> phoneNoChairsCol;
    @FXML
    private TableColumn<ChairsClass, String> addressChairsCol;
    @FXML
    private TableColumn<ChairsClass, Integer> quantityChairsCol;
    @FXML
    private TableColumn<ChairsClass, String> dateChairsCol;
    @FXML
    private TableColumn<TableClass, String> customerNameTablesCol;
    @FXML
    private TableColumn<TableClass, Integer> phoneNoTablesCol;
    @FXML
    private TableColumn<TableClass, String> addressTableCol;
    @FXML
    private TableColumn<TableClass, Integer> quantityTableCol;
    @FXML
    private TableColumn<TableClass, String> dateTableCol;
    @FXML
    private TableColumn<CanopiesClass, String> customerNameCanopiesCol;
    @FXML
    private TableColumn<CanopiesClass, Integer> phoneNoCanopiesCol;
    @FXML
    private TableColumn<CanopiesClass, String> addressCanopiesCol;
    @FXML
    private TableColumn<CanopiesClass, Integer> quantityCanopiesCol;
    @FXML
    private TableColumn<CanopiesClass, String> dateCanopiesCol;
    @FXML
    private TableColumn<RecentRecordsClass, String> nameRecentCol;
    @FXML
    private TableColumn<RecentRecordsClass, String> itemRecentCol;
    @FXML
    private TableColumn<RecentRecordsClass, Integer> quantityRecentCol;
    @FXML
    private TableColumn<RecentRecordsClass, String> dateRecentCol;
    @FXML
    private TableColumn<Person, String> customerDetailsCol;
    @FXML
    private TableColumn<Person, Integer> phoneDetailsCol;
    @FXML
    private TableColumn<Person, String> addressDetailCol;
    @FXML
    private TableColumn<Person, String> itemDetailCol;
    @FXML
    private TableColumn<Person, Integer> quantityDetailCol;
    @FXML
    private TableColumn<Person, Float> costDetailCol;
    @FXML
    private TableColumn<Person, String> dateDetailCol;
    @FXML
    private TableColumn<ChairsClass, Float> costChairsCol;
    @FXML
    private TableColumn<TableClass, Float> costTableCol;
    @FXML
    private TableColumn<CanopiesClass, Float> costCanopiesCol;

    
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        data = FXCollections.observableArrayList();
        System.out.println("Getting the customer details into table and database");
        /*
        java.sql.Connection conn = null;
        
        
        System.out.println("This program demos DB connectivity");
        try {
            Class.forName("com.mysql.jdbc.Driver").newInstance();
            conn = java.sql.DriverManager.getConnection(
                    "jdbc:mysql://localhost/furniture?user=root&password=root");
        } catch (Exception e) {
            System.out.println(e);
            System.out.println(" ON CUSTOMER DETAILS");
            //System.exit(0);
        }*/
        
                try{
            Class.forName("com.mysql.jdbc.Driver").newInstance();
            
          } catch (ClassNotFoundException | IllegalAccessException |
                InstantiationException  e) {
            System.out.println("Where is your MySQL JDBC Driver?");
            
            return;
        }
        
        System.out.println("MySQL JDBC Driver Registered!");
        
         Connection connection = null;
        try {
            connection = (Connection) DriverManager
                    .getConnection("jdbc:mysql://localhost/furniture?user=root&password=root");
        } catch (SQLException e) {
            System.out.println("Connection Failed! Check output console");
            e.printStackTrace();
            return;
        }
        if (connection != null) {
            System.out.println("You made it, take control your database now!");
        } else {
            System.out.println("Failed to make connection!");
        }
        
        
        
        
        
        
        System.out.println("Connection established");
        try {
            java.sql.Statement s = connection.createStatement();
            try (java.sql.ResultSet pr = s.executeQuery("SELECT * FROM person"))
                   
            
            {
                System.out.println(" SECOND CUSTOMER DETAILS STILL");
                while (pr.next()) {
                    data.add(new Person(pr.getString("customer"),
                            pr.getInt("phone_No"),pr.getString("address"),
                            pr.getString("item"),pr.getInt("quantities"),
                            pr.getFloat("cost"),pr.getString("dates")));
                    //pr.getString("dates")));
                    
                    customerDetailsCol.setCellValueFactory(cellData -> cellData.getValue().customerProperty());
                    phoneDetailsCol.setCellValueFactory(cellData -> cellData.getValue().phone_numberProperty().asObject());
                    addressDetailCol.setCellValueFactory(cellData -> cellData.getValue().addressProperty());
                    itemDetailCol.setCellValueFactory(cellData -> cellData.getValue().itemProperty());
                    quantityDetailCol.setCellValueFactory(cellData -> cellData.getValue().quantityProperty().asObject());
                    costDetailCol.setCellValueFactory(cellData -> cellData.getValue().costProperty().asObject());
                    dateDetailCol.setCellValueFactory(cellData -> cellData.getValue().dateProperty());
                    customerDetailsTable.setItems(data);
                    
                    System.out.println("ADDED PERSON");
                }
            //}
            
            //connection.close();
        } catch (SQLException ex) {
            System.out.println(ex.toString());
        }
                
                
                 //try {
            //java.sql.Statement s = connection.createStatement();
            try (java.sql.ResultSet r = s.executeQuery("SELECT * FROM tables"))
                   
            
            {
                
                while (r.next()) {
                    theTableList.add(new TableClass(r.getString("customer"),
                    r.getInt("phone_No"),r.getString("address"),
                    r.getInt("quantities"), r.getString("dates"),
                    r.getFloat("cost")));
                //,r.getString("dates")));
                    customerNameTablesCol.setCellValueFactory(cellData -> cellData.getValue().customerNameTablesColProperty());
                    phoneNoTablesCol.setCellValueFactory(cellData -> cellData.getValue().phoneNoTablesColProperty().asObject());
                    addressTableCol.setCellValueFactory(cellData -> cellData.getValue().addressTableColProperty());
                    quantityTableCol.setCellValueFactory(cellData -> cellData.getValue().quantityTableColProperty().asObject());
                    dateTableCol.setCellValueFactory(cellData -> cellData.getValue().dateTableColProperty());
                    costTableCol.setCellValueFactory(cellData -> cellData.getValue().costTableColProperty().asObject());
                    tablesTable.setItems(theTableList);
                    System.out.println("ADDED TABLE");
                }
            //}
                
               // connection.close();
        } catch (SQLException ex) {
            System.out.println(ex.toString());
        }
                
                
                
                
               // try {
            //java.sql.Statement s = connection.createStatement();
            try (java.sql.ResultSet m = s.executeQuery("SELECT * FROM chairs"))
                   
            
            {
                while (m.next()) {
                    theChairList.add(new ChairsClass(m.getString("customer"),
                    m.getInt("phone_No"),m.getString("address"),
                    m.getInt("quantities"), m.getString("dates"),
                            m.getFloat("cost")));
                //,r.getString("dates")));
                    customerNameChairsCol.setCellValueFactory(cellData -> cellData.getValue().customerNameChairsColProperty());
                    phoneNoChairsCol.setCellValueFactory(cellData -> cellData.getValue().phoneNoChairsColProperty().asObject());
                    addressChairsCol.setCellValueFactory(cellData -> cellData.getValue().addressChairsColProperty());
                    quantityChairsCol.setCellValueFactory(cellData -> cellData.getValue().quantityChairsColProperty().asObject());
                    dateChairsCol.setCellValueFactory(cellData -> cellData.getValue().dateChairsColProperty());
                    costChairsCol.setCellValueFactory(cellData -> cellData.getValue().costChairsColProperty().asObject());
                    chairsTable.setItems(theChairList);
                    System.out.println("ADDED CHAIR");
                }
            //}
                
                //connection.close();
        } catch (SQLException ex) {
            System.out.println(ex.toString());
        }
                
                
                
                 //try {
            //java.sql.Statement s = connection.createStatement();
            try (java.sql.ResultSet n = s.executeQuery("SELECT * FROM canopies"))
                   
            
            {
                while (n.next()) {
                    theCanopyList.add(new CanopiesClass(n.getString("customer"),
                    n.getInt("phone_No"),n.getString("address"),
                    n.getInt("quantities"), n.getString("dates"),
                            n.getFloat("cost")));
                //,r.getString("dates")));
                    customerNameCanopiesCol.setCellValueFactory(cellData -> cellData.getValue().customerNameCanopiesColProperty());
                    phoneNoCanopiesCol.setCellValueFactory(cellData -> cellData.getValue().phoneNoCanopiesColProperty().asObject());
                    addressCanopiesCol.setCellValueFactory(cellData -> cellData.getValue().addressCanopiesColProperty());
                    quantityCanopiesCol.setCellValueFactory(cellData -> cellData.getValue().quantityCanopiesColProperty().asObject());
                    dateCanopiesCol.setCellValueFactory(cellData -> cellData.getValue().dateCanopiesColProperty());
                    costCanopiesCol.setCellValueFactory(cellData -> cellData.getValue().costCanopiesColProperty().asObject());
                    canopiesTable.setItems(theCanopyList);
                
                    System.out.println("ADDED CANOPY");
                }
            }
             
            connection.close();
        } catch (SQLException ex) {
            System.out.println(ex.toString());
        }
        
        // 1. Wrap the ObservableList in a FilteredList (initially display all data).
        FilteredList<Person> filteredData = new FilteredList<>(data, p -> true);

        // 2. Set the filter Predicate whenever the filter changes.
        searchDetailField.textProperty().addListener((observable, oldValue, newValue) -> {
            filteredData.setPredicate(record -> {
                // If filter text is empty, display all persons.
                if (newValue == null || newValue.isEmpty()) {
                    return true;
                }

                // Compare first name and last name of every person with filter text.
                String lowerCaseFilter = newValue.toLowerCase();
                // Does not match.

                return record.getCustomer().toLowerCase().contains(lowerCaseFilter) ; 
            });
        });
        // 3. Wrap the FilteredList in a SortedList. 
        SortedList<Person> sortedData = new SortedList<>(filteredData);

        // 4. Bind the SortedList comparator to the TableView comparator.
        sortedData.comparatorProperty().bind(customerDetailsTable.comparatorProperty());

        // 5. Add sorted (and filtered) data to the table.
        customerDetailsTable.setItems(sortedData);
        
        
       
        
        
    } 
    
    
    /**
     * show the details of an employee
     * @param selectedCustomer is an employee
     * @param isNew is a boolean
     */
    
    private void showDetailStage(Person selectedCustomer, boolean isNew) throws IOException, NullPointerException
    , RuntimeException{
        System.out.println("On detail stage");
        Parent root = FXMLLoader.load(getClass().getResource("addingDetails.fxml"));
        Scene scene = new Scene(root);
        Stage stage = (Stage) addDetailBtn.getScene().getWindow();
        stage.setScene(scene);
//        addCustomerController.showCustomerDetail(selectedCustomer, isNew);
        //detailStage.showAndWait();
        /*
        if (isNew) {
            System.out.println("adding new Employee");
            userRecordsTable.getItems().add(selectedEmployee);
        }
       */
        System.out.println("finished detail stage");
    
    }

    @FXML
    private void addDetailsAction(ActionEvent event) throws IOException {
        Person selectedCustomer = null;
        boolean isNew = true;
        showDetailStage(selectedCustomer, isNew);
        //data.clear();
        
        try {
            Parent root = FXMLLoader.load(getClass().getResource("details.fxml"));
            Scene scene = new Scene(root);
            Stage stage = (Stage) addDetailBtn.getScene().getWindow();
            stage.setScene(scene);
        }
        catch(IOException e) {
            System.err.println(e.toString());
        }
    }

    @FXML
    private void editDetailsAction(ActionEvent event) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("editDetails.fxml"));
            Scene scene = new Scene(root);
            Stage stage = (Stage) addDetailBtn.getScene().getWindow();
            stage.setScene(scene);
        }
        catch(IOException e) {
            System.err.println(e.toString());
        }
    }
    
}
