/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Gui;

import animatefx.animation.FadeIn;
import animatefx.animation.RollIn;
import animatefx.animation.SlideInLeft;
import animatefx.animation.SlideInRight;
import animatefx.animation.Wobble;
import animatefx.animation.ZoomInLeft;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import entities.CategorieProduit;

import entities.Produit;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.animation.Interpolator;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DialogPane;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import javafx.scene.transform.Rotate;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.util.Duration;
import javax.swing.JOptionPane;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import services.CategorieProduitService;
import services.ProduitService;
import services.UserSevice;
import utils.ControleSaisie;

import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import utils.MyConnection;


/**
 * FXML Controller class
 *
 * @author ASUS
 */
public class ProduitController implements Initializable {

    static void close() {
         MainStage.close();
      
    }
       @FXML
    private AnchorPane rootpane;
          @FXML
    private JFXButton btnab;
          
          
    @FXML
    private ImageView chariot;

    @FXML
    private Text productlist;

    
    @FXML
    private TableColumn<Produit,String> image2;//photo dans table view
    
     @FXML
    private TableView<Produit> tableP;

    @FXML
    private TableColumn<Produit, String> categorieC;
    
     @FXML
    private TableColumn<Produit, Integer> idC; //visible

    @FXML
    private TableColumn<Produit, String> nomproduitC;

    @FXML
    private TableColumn<Produit, Double> prixC;

    @FXML
    private TableColumn<Produit, String> descriptionC;

    @FXML
    private TableColumn<Produit, String> imageC; //visible

    @FXML
    private TableColumn<Produit, Integer> quantiteC;
    /////////textfield and buttons/////////////////
    
    @FXML
    private JFXButton btnclick; //ajoutet

    @FXML
    private TextField nomm;

    @FXML
    private TextField prixx;

    @FXML
    private TextField descriptionn;

    @FXML
    private TextField filechoose;

    @FXML
    private TextField quantitee;

    @FXML
    private Button chooser;
    
    ObservableList<Produit> listP = null;
    //////////////////////////IMAGE/////////////////
        private Image image;
        
          FileChooser fc = new FileChooser();
    File selectedFile = new File("");
    
     /////////////export//////////
        
       private Connection cnx;
    private ResultSet rs=null;
    private PreparedStatement pst;
    
        private XSSFWorkbook wb;
    private XSSFSheet sheet;
     private XSSFRow header;
    
    ////////////////////////////Editer selection////////////////////////////
    
    public static int E_id_selection;
    public static int E_categorie_selection;
    public static String E_nom_selection;
    public static String E_description_selection;
    public static String E_image_selection;
    public static double E_prix_selection;
    public static int  E_quantite_selection;
    
    
      
     public static String EditTable = "";
     
 
    
    public static Stage MainStage;
    
    
//////////////
        @FXML
    private Button test;
    
    
    /////////////////////////////////////////////////
    
    ProduitService ps= new ProduitService();
    
    Produit P = new Produit();
    
     ////////////////////////////////////////////////Error Controle de saisie////////////////////////////////////
                @FXML
    private Label errornom;

    @FXML
    private Label errorprix;

    @FXML
    private Label errordescription;

    @FXML
    private Label errorquantite;

    @FXML
    private Label errorcategorie;
    
    

    
    ///////////////////////////////////////////CONTROLE de saisie///////////////////////////////////////////////////////////////
      ControleSaisie controle = new ControleSaisie();
    boolean Nomok = true;
    boolean Nomok1 = true;
    boolean Nomok2 = true;
    
    boolean quantiteok = true;
    boolean quantiteok1 = true;
    boolean quantiteok2 = true;

    boolean categoriee = true;
    boolean prixok= true;
    boolean prixok1 = true;
    boolean prixok2 = true;
    
    boolean descriptionok = true;
    
     boolean descriptionok1 = true;
    boolean descriptionok2 = true;
    
    
    
     @FXML
    private ComboBox<String> categorie; //combo box des noms des categories
                  
    ObservableList<String> listN = null;
 
      @FXML
    private JFXTextField filterfield;


    /////////////////////////////Ajout Porduit///////////////////////
    
     @FXML
 void addP(ActionEvent event) throws SQLException {
     
     
        controle.effacerControleSaisie(errornom);
            controle.effacerControleSaisie(errordescription);
            controle.effacerControleSaisie(errorprix);
        controle.effacerControleSaisie(errorquantite);
         //controle.effacerControleSaisie(errot);
     
    
     
     if(nomm.getText().isEmpty() || descriptionn.getText().isEmpty() || prixx.getText().isEmpty() || quantitee.getText().isEmpty() )
     
     {
         
         Alert alert = new Alert(Alert.AlertType.ERROR);
         alert.setHeaderText(null);
         alert.setContentText("veuiller remplir tous les champs");
         alert.showAndWait();
         return;
     }
     else
     {
   
String nom= nomm.getText();
Double prix= Double.valueOf(prixx.getText());
String description= descriptionn.getText();
String file=filechoose.getText();
int quantite= Integer.valueOf(quantitee.getText());




UserSevice s1=new UserSevice();
   
      


    
       String ss= s1.getlogin() ;                 //prendre l id du user connecté
       //int result = Integer.parseInt(ss);			
			
      
       
                       
        String r = categorie.getSelectionModel().getSelectedItem().toString();
        
         int idU=ps.findbynomcategorie(r); 
   
          

P.setCategorie(idU);


P.setNom(nom);
P.setPrix(prix);
P.setDescription(description);

P.setNom_image(file);
P.setQuantite(quantite);
        

 ProduitService ps = new ProduitService();
ps.ajouuterProduit(P);

ObservableList<Produit> data = FXCollections.observableArrayList();
        ProduitService srvRec = new ProduitService();
        data = FXCollections.observableArrayList(srvRec.afficherAll());
     
  
        tableP.setItems(data); 
        


  Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
         alert.setHeaderText(null);
         alert.setContentText("Votre produit à été ajouté");
         alert.showAndWait();
         DialogPane dialogPane = alert.getDialogPane();
 }       
        
 
    
 
 


     
 }
 
 
 ////////////////////////////Browse image/////////////////////
  //browseimage
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

        //images.setImage(imagee);   
    }
    
    /////////////////////////////Editer/////////////////////////////////
    
          @FXML  
    void edit(ActionEvent e) throws IOException {
        
 Stage stage1 = (Stage) ((Node) e.getSource()).getScene().getWindow();
 

             //int idU=ps.findbynomcategorie(r); 
        MainStage = stage1;
        EditTable = ((Button) e.getSource()).getText();
        if (EditTable.equals("Modify")) {
           
            
            E_id_selection = tableP.getSelectionModel().getSelectedItem().getId();
            E_categorie_selection = tableP.getSelectionModel().getSelectedItem().getCategorie();
            E_prix_selection = tableP.getSelectionModel().getSelectedItem().getPrix();
            E_nom_selection = tableP.getSelectionModel().getSelectedItem().getNom();
            E_description_selection = tableP.getSelectionModel().getSelectedItem().getDescription();
            E_image_selection = tableP.getSelectionModel().getSelectedItem().getNom_image();
            E_quantite_selection = tableP.getSelectionModel().getSelectedItem().getQuantite();
            
         

        } 
        Parent root = FXMLLoader.load(getClass().getResource("Edit.fxml"));
        Scene scene = new Scene(root);
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.setTitle("Edit");
        stage.show();
        
   
        
         ObservableList<Produit> data = FXCollections.observableArrayList();
        ProduitService srvRec = new ProduitService();
        data = FXCollections.observableArrayList(srvRec.afficherAll());
         
        tableP.setItems(data); 
     
      
    }
    
    
    //////////////////////////////Delete//////////////////////////////////////////////
    
   
          @FXML
    void delete(ActionEvent event) throws SQLException {
          if (!tableP.getSelectionModel().isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Delete Product ");
            alert.setHeaderText("Are you sure you want to delete this category"
                    + tableP.getSelectionModel().getSelectedItem().getId() + "?");
            Optional<ButtonType> result = alert.showAndWait();
        
        
          if (result.get() == ButtonType.OK) {
        
        
 ProduitService ms = new ProduitService();
        ObservableList<Produit> ll, ttmission=FXCollections.observableArrayList();;
        ttmission = tableP.getItems();
        // ta3tina les lignes selectionnés 
        ll = tableP.getSelectionModel().getSelectedItems();

        for (Produit m : ll) {
            //ttmission.remove(m);//refresh tableview
            ms.DeleteProduit((m.getId()));
                 
            
           
        }
        
        JOptionPane.showMessageDialog(null, "delete");
        ObservableList<Produit> data = FXCollections.observableArrayList();
        ProduitService srvRec = new ProduitService();
        data = FXCollections.observableArrayList(srvRec.afficherAll());
     
  
        tableP.setItems(data);

    }
          }
          else {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Erreur de selection");
            alert.setHeaderText("You are obliged to select a product ");

            Optional<ButtonType> result = alert.showAndWait();
        }
    }

    


    //passage au categorie
    @FXML
    void categorie(ActionEvent event) throws IOException {
          
           Parent root = FXMLLoader.load(getClass().getResource("Categorie.fxml"));
         Scene scene = btnab.getScene();
        root.translateYProperty().set(scene.getHeight());

        rootpane.getChildren().add(root);

        Timeline timeline = new Timeline();
        KeyValue kv = new KeyValue(root.translateYProperty(), 0, Interpolator.EASE_IN);
        KeyFrame kf = new KeyFrame(Duration.seconds(1), kv);
        timeline.getKeyFrames().add(kf);
        timeline.setOnFinished(t -> {
            rootpane.getChildren().remove(rootpane);
           
        });
        timeline.play();
        
        

    
  
          
    }
    
    

    
      
        
    
    
    
     
    
    
    
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
          cnx = MyConnection.getInstance().getCnx();
        ////////////////////////remplir combo avec nom categorie//////////////////
          try {
            listN = ps.getCategorie(); //combo avec nom categorie
        } catch (SQLException ex) {
            Logger.getLogger(ProduitController.class.getName()).log(Level.SEVERE, null, ex);
        }
        categorie.setItems(listN);
        
        ObservableList<Produit> data = FXCollections.observableArrayList();
        ProduitService srvRec = new ProduitService();
        data = FXCollections.observableArrayList(srvRec.afficherAll());
        idC.setCellValueFactory(new PropertyValueFactory<>("id"));
        categorieC.setCellValueFactory(new PropertyValueFactory<>("yassine"));
        nomproduitC.setCellValueFactory(new PropertyValueFactory<Produit, String>("nom"));
        prixC.setCellValueFactory(new PropertyValueFactory<Produit, Double>("prix"));
        descriptionC.setCellValueFactory(new PropertyValueFactory<Produit, String>("description"));
        imageC.setCellValueFactory(new PropertyValueFactory<>("nom_image")); //file dans l'entite
        quantiteC.setCellValueFactory(new PropertyValueFactory<Produit, Integer>("quantite"));
        image2.setCellValueFactory(new PropertyValueFactory<>("photo"));

        

        tableP.setItems(data);
       // Refresh.setVisible(true);
       
       
       ////////////////recherche//////////////////
       
         FilteredList<Produit> filteredData = new FilteredList<>(data, b -> true);
		
		// 2. Set the filter Predicate whenever the filter changes.
		filterfield.textProperty().addListener((observable, oldValue, newValue) -> {
			filteredData.setPredicate(Produit -> {
				// If filter text is empty, display all persons.
								
				if (newValue == null || newValue.isEmpty()) {
					return true;
				}
				
                                
				// Compare first name and last name of every person with filter text.
				String lowerCaseFilter = newValue.toLowerCase();
				
				if (Produit.getNom().toLowerCase().indexOf(lowerCaseFilter) != -1 ) {
					return true; // Filter matches first name.
				} else if (Produit.getDescription().toLowerCase().indexOf(lowerCaseFilter) != -1) {
					return true; // Filter matches last name.
				}
				
				     else  
				    	 return false; // Does not match.
			});
		});
		
		// 3. Wrap the FilteredList in a SortedList. 
		SortedList<Produit> sortedData = new SortedList<>(filteredData);
		
		// 4. Bind the SortedList comparator to the TableView comparator.
		// 	  Otherwise, sorting the TableView would have no effect.
		sortedData.comparatorProperty().bind(tableP.comparatorProperty());
		
		// 5. Add sorted (and filtered) data to the table.
		tableP.setItems(sortedData);
   
        
        // TODO
    }   
    
   
    @FXML
    void verifnom(KeyEvent event) {
          if (!controle.controleTextFieldVide(nomm, "Veuillez saisir un nom du produit", errornom)) {
           controle.controleTextFieldOnlyLetters(nomm, "nom non  numérique", errornom);
           
        
          }
          
          

    }

    @FXML
    void verifprix(KeyEvent event) {
        
        
      
          if (!controle.DoubleCheck(prixx.getText())) {
          //  controle.controleTextFieldNumerique1(idlebelle, "Libelle non numérique", errorLabel);
          controle.controleTextFieldVide(prixx,"veuillez saisir un prix", errorprix);
        }

    }

    @FXML
    void verifquantite(KeyEvent event) {
        
        if (!controle.controleTextFieldVide(quantitee, "Veuillez saisir la quantite", errorquantite)) {
          
        controle.controleTextFieldChiffres(quantitee, "la quantite doir etre une valeur", errorquantite);
        

    } 
        
  
        
    
    
}
    
  
       @FXML
    void verifdescription(KeyEvent event) {
        
        if (!controle.controleTextFieldVide(descriptionn, "Veuillez saisir la description", errordescription)) {
           controle.controleTextFieldOnlyLetters(descriptionn, "description non numérique", errordescription);
           
        
          }
          
        

    }
    
    
    @FXML
    void dashbord(MouseEvent event) {
        
        try {
               
      
               Parent AnchorPane = FXMLLoader.load(getClass().getResource("UI.fxml"));
               Stage stage = new Stage();
               Scene scene = new Scene(AnchorPane);
               stage.setScene(scene);
               stage.show();
           } catch (IOException ex) {
              
           }
                   
    
                          ((Node) event.getSource()).getScene().getWindow().hide();
        
        
        

    }
    
    //////////////////////////////exporter produ///////////////////
    
    @FXML
    void exporter(ActionEvent event)  {
//

    try {
         String query ="select * from produit";
        
            pst=cnx.prepareStatement(query);
        
        rs= pst.executeQuery();
        
        
         wb= new XSSFWorkbook();
        sheet= wb.createSheet("Product Details");
        header =sheet.createRow(0);
        header.createCell(0).setCellValue("id");
        header.createCell(1).setCellValue("categorie");
        header.createCell(2).setCellValue("nom");
        header.createCell(3).setCellValue("prix");
        header.createCell(4).setCellValue("quantite");
        
        int index=1;
         
        while(rs.next()){
          XSSFRow row=sheet.createRow(index);
            row.createCell(0).setCellValue(rs.getString(1));
            row.createCell(1).setCellValue((rs.getString(2)));
            row.createCell(2).setCellValue(rs.getString(3));
            row.createCell(3).setCellValue(rs.getString(4));
            row.createCell(4).setCellValue(rs.getString(7));
            index++;
        }
        
       FileOutputStream fileOut= new FileOutputStream("Product.xlsx");
        wb.write(fileOut);
        fileOut.close();
        
        Alert alert =new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Information Dialog");
        alert.setHeaderText(null);
        alert.setContentText("Product Details Exported in Excel Sheet.");
        alert.showAndWait();
        
        pst.close();
        rs.close();
        
    }
    catch (SQLException ex) {
            Logger.getLogger(ProduitController.class.getName()).log(Level.SEVERE, null, ex);
        }
    catch (IOException ex) {
        Logger.getLogger(ProduitController.class.getName()).log(Level.SEVERE, null, ex);
    }
    
    
    }
    
        @FXML
    void chariotanimation(MouseEvent event) {
          new SlideInRight(chariot).play();

    }
    
       @FXML
    void productlist(MouseEvent event) {
          new ZoomInLeft(productlist).play();

    }

    }
    



