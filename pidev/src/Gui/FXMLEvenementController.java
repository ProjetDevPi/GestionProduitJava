/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Gui;


import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXTextField;
import entities.Evenement;
import entities.Sponsor;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javax.swing.JOptionPane;
import services.EvenementService;
import services.SponsorService;
import services.UserSevice;
import utils.MyConnection;

/**
 * FXML Controller class
 *
 * @author HP
 */
public class FXMLEvenementController implements Initializable {
 
//recherche
     ObservableList<Evenement> listE = null;
    @FXML
    private TextField filterfield;
    @FXML
    private AnchorPane parent;
    @FXML
    private JFXTextField eventname;

    @FXML
    private JFXTextField type;

    @FXML
    private JFXDatePicker date;

    @FXML
    private JFXTextField cap;

    
       @FXML
    private JFXTextField filechoose;

    @FXML
    private JFXButton chooser;

  @FXML
    private ImageView imageView;

    
    @FXML
    private JFXButton btninsert;

    @FXML
    private JFXButton btninsert1;

    @FXML
    private JFXButton btninsert11;

    
  @FXML
    private Button btnaffecter;

    /*/@FXML
    private Button chooser;/*/
     FileChooser fc = new FileChooser();
    File selectedFile = new File("");

    
    @FXML
    private TableView<Evenement> tableviewE;

    @FXML
    private TableColumn<Evenement, String> nom;
    @FXML
    private TableColumn<Evenement, String> afftype;

    @FXML
    private TableColumn<Evenement, Date> affdate;
     @FXML
    private TableColumn<?,?> imageC;
     @FXML
    private TableColumn<Evenement,Integer> capacity; 
 public static String EditTable = "";
     public static int E_id_selection;
    public static String E_nom_selection;
    public static String E_prenom_selection;
    public static int E_cap_selection;
    public static Stage MainStage;

     ObservableList<Evenement> data = FXCollections.observableArrayList();
     private Connection cnx;
    private Statement st;
    private PreparedStatement pre;
    private Image image;
    ///////////////////////Check
     @FXML
    private Label checkname;
     
    @FXML
    private Label checktype;
     @FXML
    private Label checkdate;
      @FXML
    private Label checkcapacitys;
    //detail
        @FXML
    private JFXTextField namedetail;

    @FXML
    private JFXTextField typedetail;

    @FXML
    private JFXTextField datedetail;

    @FXML
    private JFXTextField capacitydetail;
////////////////////////////MENU///////////////////////
      @FXML
    private JFXButton btnevent;

    @FXML
    private JFXButton btnsponsor;

    @FXML
    private JFXButton btncontrat;

    @FXML
    private JFXButton btnpart;

    
 @FXML
    void event(ActionEvent event) throws IOException {
          Parent root = FXMLLoader.load(getClass().getResource("FXMLEvenement.fxml"));//appler la page edit
        Scene scene = new Scene(root);
        Stage stage = new Stage();
        stage.setScene(scene);
       
         stage.show();
          ((Node) event.getSource()).getScene().getWindow().hide();

    }

    @FXML
    void participant(ActionEvent event) throws IOException {
         Parent root = FXMLLoader.load(getClass().getResource("FXMLParticipationback.fxml"));//appler la page edit
        Scene scene = new Scene(root);
        Stage stage = new Stage();
        stage.setScene(scene);
       
         stage.show();
          ((Node) event.getSource()).getScene().getWindow().hide();

    }

    @FXML
    void sponsor(ActionEvent event) throws IOException {
         Parent root = FXMLLoader.load(getClass().getResource("FXMLSponsor.fxml"));//appler la page edit
        Scene scene = new Scene(root);
        Stage stage = new Stage();
        stage.setScene(scene);
       
         stage.show();
          ((Node) event.getSource()).getScene().getWindow().hide();

    }
       @FXML
    void contrat(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("FXMLContrat.fxml"));//appler la page edit
        Scene scene = new Scene(root);
        Stage stage = new Stage();
        stage.setScene(scene);
       
         stage.show();
          ((Node) event.getSource()).getScene().getWindow().hide();

    }
    
  ////////////////endMenu////////////
    @FXML
    void add(ActionEvent event) throws SQLException {
        String nomevent= eventname.getText();
        
        String typeevent= type.getText();
        LocalDate d=(date.getValue());
        Date da=Date.valueOf(date.getValue());
        int nb=Integer.valueOf(cap.getText());
        String file=filechoose.getText();
        //LocalDate date = date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        LocalDate dateDuJour = LocalDate.now();
        System.out.println(da);
        /*/DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");  
         String strDate = dateFormat.format(da); 
          String datedujour= LocalDate.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));;/*/
EvenementService ps = new EvenementService();
Date datebase=ps.findbynom(da);

 if(eventname.getText().trim().isEmpty() && type.getText().trim().isEmpty()&& cap.getText().trim().isEmpty()) 
           {
               
                       checkname.setText("Name is required!");
                       checktype.setText("Type is required!");
                       checkcapacitys.setText("Capacity is required");
           
           }
 else if(type.getText().trim().isEmpty())
            { 
                      checktype.setText("Type is required!"); 
            }
 else if(eventname.getText().trim().isEmpty())
            { 
                      checkname.setText("Type is required!"); 
            }
 else if(cap.getText().trim().isEmpty())
            { 
                      checkcapacitys.setText("Capacity is required!");
            }
 

 else 
 {     
         if ((d.isBefore(LocalDate.now())) && (datebase==null))
         {
         
          Evenement P = new Evenement();
    
          P.setNom(nomevent);
          P.setType(typeevent);
          P.setDateevent(da);
          P.setNbrpart(nb);
          P.setNom_image(file);
          //EvenementService ps = new EvenementService();
          ps.addEvenement(P);
          Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Evenement ajouté ");
            alert.setHeaderText("Votre evenement a été bien ajouté");
               Optional<ButtonType> resul = alert.showAndWait();
         
      
      
  }
            else
     
    {
        
       
        Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Date invalide ");
            alert.setHeaderText("La date passée est invalide veuillez entrer une nouvelle date");
               Optional<ButtonType> resul = alert.showAndWait();
        
        
     }
 
    
 }
 


ObservableList<Evenement> data = FXCollections.observableArrayList();
        EvenementService srvRec = new EvenementService();
        data = FXCollections.observableArrayList(srvRec.afficherAll());
     
     
        tableviewE.setItems(data);


    }
     
    ///check
   @FXML
    void eventname(KeyEvent event) {
        checkname.setText("");

    }
    
    @FXML
    void checkcapacity(KeyEvent event) {
checkcapacitys.setText("");
    }

    @FXML
    void checkdate(KeyEvent event) {
checkdate.setText("");
    }

    @FXML
    void checktype(KeyEvent event) {
        checktype.setText("");

    }
     
@FXML
    void pdf(ActionEvent event) throws SQLException, FileNotFoundException, DocumentException {
         cnx = MyConnection.getInstance().getCnx();
        st=cnx.createStatement();
          ResultSet query_set = st.executeQuery("SELECT *From evenement");
                       /* Step-2: Initialize PDF documents - logical objects */
                       Document my_pdf_report = new Document();
                       PdfWriter.getInstance(my_pdf_report, new FileOutputStream("Evenement.pdf"));
                       my_pdf_report.open();            
                       //we have four columns in our table
                       PdfPTable my_report_table = new PdfPTable(3);
                        my_report_table.addCell("Event Name");
                        my_report_table.addCell("Type");
                        my_report_table.addCell("Capacity");
                       //create a cell object
                       PdfPCell table_cell;

                       while (query_set.next()) {  
                           
                                       String nom = query_set.getString("nom");
                                       table_cell=new PdfPCell(new Phrase(nom));
                                       my_report_table.addCell(table_cell);
                                       String type=query_set.getString("type");
                                       table_cell=new PdfPCell(new Phrase(type));
                                       my_report_table.addCell(table_cell);
                                       String capacity=query_set.getString("nbrpart");
                                       table_cell=new PdfPCell(new Phrase(capacity));
                                       my_report_table.addCell(table_cell);
                                      
                                       }
                       /* Attach report table to PDF */
                       my_pdf_report.add(my_report_table);                       
                       my_pdf_report.close();

                                     



       } 

    
    @FXML
    void edit(ActionEvent event) throws IOException {
        Stage stage1 = (Stage) ((Node) event.getSource()).getScene().getWindow();
        MainStage = stage1;
        EditTable = ((Button) event.getSource()).getText();
        if (EditTable.equals("Edit")) {
            E_id_selection = tableviewE.getSelectionModel().getSelectedItem().getId();
            E_nom_selection = tableviewE.getSelectionModel().getSelectedItem().getNom();
            E_prenom_selection = tableviewE.getSelectionModel().getSelectedItem().getType();
            E_cap_selection = tableviewE.getSelectionModel().getSelectedItem().getNbrpart();
         

        } 
        
        Parent root = FXMLLoader.load(getClass().getResource("Editevenement.fxml"));
        Scene scene = new Scene(root);
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.setTitle("Edit");
        
         ObservableList<Evenement> data = FXCollections.observableArrayList();
        EvenementService srvRec = new EvenementService();
        data = FXCollections.observableArrayList(srvRec.afficherAll());
     
        tableviewE.setItems(data);
        stage.show();


    }
    

    @FXML
    void image(ActionEvent event) {
         fc.setTitle("Open Resource File");
        fc.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("Text files","*.pdf", "*.tkt", "*.docx","*.png","*.jpg"));
        fc.setInitialDirectory(new File("C:"));
        selectedFile = fc.showOpenDialog(null);

        //UploadFile.upload(selectedFile,"", "");
        File file = new File("" + selectedFile.getName());
        filechoose.setText(selectedFile.getName());

    }

   
          private void setCellValueFromTableToTextField(){
        tableviewE.setOnMouseClicked(e -> {
            Evenement pl = tableviewE.getItems().get(tableviewE.getSelectionModel().getSelectedIndex());
           
      
           eventname.setText(pl.getNom());
           
   
      
            try {
                
                showProductImage(pl.getNom());
                
            } catch (SQLException ex) {
                Logger.getLogger(FXMLEvenementController.class.getName()).log(Level.SEVERE, null, ex);
            }
          
        });
        
        
    } 
   
    @FXML
    void affecter(ActionEvent event) throws IOException {
        System.out.println("vghdv");
        Stage stage1 = (Stage) ((Node) event.getSource()).getScene().getWindow();
        MainStage = stage1;
        EditTable = ((Button) event.getSource()).getText();
        if (EditTable.equals("Assign  sponsor")) {
            E_id_selection = tableviewE.getSelectionModel().getSelectedItem().getId();
            E_nom_selection = tableviewE.getSelectionModel().getSelectedItem().getNom();
            System.out.println(E_nom_selection );
            E_prenom_selection = tableviewE.getSelectionModel().getSelectedItem().getType();
         

        } 
        
        Parent root = FXMLLoader.load(getClass().getResource("FXMLContrat.fxml"));
        Scene scene = new Scene(root);
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.setTitle("Contrat");
        
        
        stage.show();
         ((Node) event.getSource()).getScene().getWindow().hide();
        

    }
  private void showProductImage(String nomm) throws SQLException{
        cnx = MyConnection.getInstance().getCnx();
        st=cnx.createStatement();
        try {
           String req="Select * from evenement";
            
            
       ResultSet     rs ;
       rs=st.executeQuery(req);
     
    
            while(rs.next()) {
                
                if (rs.getString("nom").equals(nomm))
                {                 
                
                    System.out.println(rs.getString("nom"));
                String a=rs.getString("nom_image");
                
                
                
                image = new Image("file:"+a+"", imageView.getFitWidth(), imageView.getFitHeight(), true, true);
                    System.out.println(image);
                imageView.setImage(image);
                imageView.setPreserveRatio(true);
            
            }
            }
        } catch (SQLException ex) {
            Logger.getLogger(FXMLEvenementController.class.getName()).log(Level.SEVERE, null, ex);
        
        
    }
        }
     private void detaill(){
        tableviewE.setOnMouseClicked((MouseEvent e) -> {
            Evenement p = tableviewE.getItems().get(tableviewE.getSelectionModel().getSelectedIndex());
           namedetail.setText(p.getNom());
           typedetail.setText(p.getType());
           datedetail.setText(String.valueOf(p.getDateevent()));
           capacitydetail.setText(String.valueOf(p.getNbrpart()));
           try {
                showProductImage(p.getNom());
            } catch (SQLException ex) {
                Logger.getLogger(FXMLEvenementController.class.getName()).log(Level.SEVERE, null, ex);
            }
                
  
          
        });
        
        
    } 
     @FXML
    void deletes(Event event) {
        if (!tableviewE.getSelectionModel().isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Delete Event ");
            alert.setHeaderText("Are you sure you want to delete this event"
                    + tableviewE.getSelectionModel().getSelectedItem().getId() + "?");
            Optional<ButtonType> result = alert.showAndWait();
        
        
          if (result.get() == ButtonType.OK) {
        
        
 EvenementService ms = new  EvenementService();
        ObservableList< Evenement> ll, ttmission;
        ttmission = tableviewE.getItems();
        // ta3tina les lignes selectionnés 
        ll = tableviewE.getSelectionModel().getSelectedItems();

        for ( Evenement m : ll) {
            ttmission.remove(m);//refresh tableview
            ms.DeleteSponsor(m.getId());
        }
        JOptionPane.showMessageDialog(null, "supprimer");

    }
          }
          else {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Erreur de selection");
            alert.setHeaderText("Vous etes obligé de selectioner un evenement  ");

            Optional<ButtonType> result = alert.showAndWait();
        }

    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO setCellValueFromTableToTextField();
       setCellValueFromTableToTextField();
         detaill();
         
          ObservableList<Evenement> data = FXCollections.observableArrayList();
        EvenementService srvRec = new EvenementService();
        data = FXCollections.observableArrayList(srvRec.afficherAll());
     
        nom.setCellValueFactory(new PropertyValueFactory<Evenement, String>("nom"));
        afftype.setCellValueFactory(new PropertyValueFactory<Evenement, String>("type"));
        affdate.setCellValueFactory(new PropertyValueFactory<Evenement, Date>("dateevent"));
            capacity.setCellValueFactory(new PropertyValueFactory<Evenement, Integer>("nbrpart"));
           
imageC.setCellValueFactory(new PropertyValueFactory<>("nom_image")); //file dans l'entite
        tableviewE.setItems(data);
        ///rechercheeeeee**********************
         // Wrap the ObservableList in a FilteredList (initially display all data).
        FilteredList<Evenement> filteredData = new FilteredList<>(data, b -> true);
		
		// 2. Set the filter Predicate whenever the filter changes.
		filterfield.textProperty().addListener((observable, oldValue, newValue) -> {
			filteredData.setPredicate(Evenement -> {
				// If filter text is empty, display all persons.
								
				if (newValue == null || newValue.isEmpty()) {
					return true;
				}
				
				// Compare first name and last name of every person with filter text.
				String lowerCaseFilter = newValue.toLowerCase();
				
				if (Evenement.getNom().toLowerCase().indexOf(lowerCaseFilter) != -1 ) {
					return true; // Filter matches first name.
				} else if (Evenement.getType().toLowerCase().indexOf(lowerCaseFilter) != -1) {
					return true; // Filter matches last name.
				}
				
				     else  
                                    
				    	 return false; // Does not match.
			});
		});
		
		// 3. Wrap the FilteredList in a SortedList. 
		SortedList<Evenement> sortedData = new SortedList<>(filteredData);
		
		// 4. Bind the SortedList comparator to the TableView comparator.
		// 	  Otherwise, sorting the TableView would have no effect.
		sortedData.comparatorProperty().bind(tableviewE.comparatorProperty());
		
		// 5. Add sorted (and filtered) data to the table.
		tableviewE.setItems(sortedData);
    }    
    
    
    //////////////////redirection dahsboard////////////
     @FXML
    void backdashboard(ActionEvent event) {
        try {
               
      
               Parent AnchorPane = FXMLLoader.load(getClass().getResource("UI.fxml"));
               Stage stage = new Stage();
               Scene scene = new Scene(AnchorPane);
               stage.setScene(scene);
               stage.show();
                stage.setTitle("Dashboard");
           } catch (IOException ex) {
              
           }
                   
    
                          ((Node) event.getSource()).getScene().getWindow().hide();
        

    }

    
}
