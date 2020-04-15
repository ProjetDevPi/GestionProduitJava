package Gui;

import static Gui.AfficherRController.missionsel;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import entities.Notif;
import entities.rating;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
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
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.PieChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.DialogPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.effect.BoxBlur;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.paint.Paint;
import javafx.stage.Stage;
import javafx.util.Duration;
import javax.swing.JOptionPane;
import org.controlsfx.control.Notifications;
import services.CommandeProduitService;
import services.ProduitService;
import services.RatingService;
import tray.animations.AnimationType;
import tray.notification.TrayNotification;
import utils.MyConnection;




public class UIController implements Initializable {
    
    
    ////////////statwejdeneee//////////
      ObservableList<rating> listR = null;
       @FXML
    private TableView<rating> tabR;
    @FXML
    private BarChart<String,Double> barchart;
    @FXML
    private TableColumn<?, ?> nomC;

    @FXML
    private TableColumn<?, ?> ratC;
        @FXML
    private TableColumn<?, ?> userC;

    @FXML
    private TableColumn<?, ?> comC;
     @FXML
    private AnchorPane rootpane;
            static public rating missionsel;
       @FXML
    private JFXTextField filterfield;
    
    
/////////////stat/////
     private final ObservableList<PieChart.Data> details= FXCollections.observableArrayList();
       private final ObservableList<PieChart.Data> details2= FXCollections.observableArrayList();
private PieChart pieChart;

BorderPane root;
   
    private ResultSet rs=null,rs1=null;
    private PreparedStatement pst,pst1;
    @FXML
    PieChart piechart1;
     @FXML
    PieChart piechart2;
    ObservableList<PieChart.Data> piechartdata;
      ObservableList<PieChart.Data> piechartdata2;
       @FXML
    PieChart piechart3;
    ObservableList<PieChart.Data> piechartdata33;
ArrayList<Integer> np=new ArrayList<Integer>();
ArrayList<String> cat=new ArrayList<String>();
///////////////////statnbprodparcat////////////////////////////
ArrayList<Integer> np1=new ArrayList<Integer>();
ArrayList<String> cat1=new ArrayList<String>();

     private Connection cnx;
  
   
    
    
    
    /////////////////////
@FXML
private JFXButton btnproduits;
   @FXML
    private JFXButton com;
   

//////////////////////Revenu/////////////
public static double revenu;
static public int day;

    
    private double xOffset = 0;
    private double yOffset = 0;
       
    ///////////notif/////////
     @FXML
    private ImageView notif_empty;
    @FXML
    private ImageView notif_1;
 
    
        String path = "C:\\Users\\ASUS\\Desktop\\wejdene\\default-iphone.mp3";
      
      Media media = new Media(new File(path).toURI().toString());
      
      MediaPlayer mediaplayer = new MediaPlayer(media);

    
    @FXML
    void reset_revenu(MouseEvent event) throws SQLException
{
    
    ProduitService s1 = new ProduitService();
    LocalDateTime now = LocalDateTime.now();
   int day6=now.getDayOfMonth();
   
   if (day6-s1.jour()!=0)
   {
       s1.editrevenu();
   }
   
    
      JOptionPane.showMessageDialog(null, "Le revenu du jour "+now+" est de : " +s1.revenu()+" Dinars");
       
       /*/
    Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
         alert.setHeaderText(null);
         alert.setContentText("Le revenu du jour "+now+" est de : " +s1.revenu()+" Dinars");
         alert.showAndWait();
         DialogPane dialogPane = alert.getDialogPane();
    /*/
}
    
    /////////////////////////Notifffffffff////////////////////
    
     private void notif_empty()
    {
        notif_empty.setVisible(true);
        notif_1.setVisible(false);
    }
    
     private void notif_1()
    {
        notif_1.setVisible(true);
        notif_empty.setVisible(false);
    }
     
     
      @FXML
    void notification() {
       CommandeProduitService s3 = new CommandeProduitService();   
    
    List<Notif> notif = s3.afficherNotif();
    
     for(int i=0;i<notif.size();i++)
         
      {
         Notifications notificationBuilder;
           mediaplayer.play();
    notificationBuilder = Notifications.create()
            .title("Paiement")
            
            .text("Notification " +(i+1)+": Un nouveau paiement de la part de "+notif.get(i).getNom_user()+" d'une somme de : " +notif.get(i).getPrix())
           
          
             .hideAfter(Duration.seconds(10))
            .position(Pos.BOTTOM_RIGHT)
           
            ;
            
    notificationBuilder.darkStyle();
        notificationBuilder.show();
      
      
      }
    }
            
    @Override
    public void initialize(URL url, ResourceBundle rb) {
          RatingService cs =new RatingService();
        
        CommandeProduitService s3 = new CommandeProduitService();
        List<Notif> notif = s3.afficherNotif(); //affichage notif
        if (notif.size() >0)
        {
            notif_1();
        }
        else
        {
            notif_empty();
        }
        
         notif_1.setOnMouseClicked(e -> {
             
                  notification();
                  notif_empty();
                  s3.deletenotif();
                
                
     
     });
     notif_empty.setOnMouseClicked(e -> {
             
                 
                Notifications notificationBuilder;
    notificationBuilder = Notifications.create()
            .title("Pas de Notifications")
            
            .text("Vous n'avez pas de notifications pour le moment ! ")
           
          
             .hideAfter(Duration.seconds(6))
            .position(Pos.BOTTOM_RIGHT)
           
            ;
            
    notificationBuilder.darkStyle();
        notificationBuilder.show();
      
                
     
     });
        
        cnx = MyConnection.getInstance().getCnx();
        Piechart();
        Piechart2();
        
          try {
              Piechart33();
          } catch (SQLException ex) {
              Logger.getLogger(UIController.class.getName()).log(Level.SEVERE, null, ex);
          }
 
    
        piechart3.setData(piechartdata33);
        
        piechart1.setData(piechartdata);   
        piechart2.setData(piechartdata2); 
        
        
        ////////////////////statwejdeneeee+rechercherating/////////////
         nomC.setCellValueFactory(new PropertyValueFactory<>("nom"));
       ratC.setCellValueFactory(new PropertyValueFactory<>("rat"));
              userC.setCellValueFactory(new PropertyValueFactory<>("user"));
                     comC.setCellValueFactory(new PropertyValueFactory<>("commentaire"));
     
          
              try {
            listR = cs.getrating();
        } catch (SQLException ex) {
            Logger.getLogger(AfficherEleveController.class.getName()).log(Level.SEVERE, null, ex);
        }
        tabR.setItems(listR); 
        
          XYChart.Series<String, Double> series11 = new XYChart.Series<>();
         
        try {
            for (rating t1 : cs.getrating()) {
                
                XYChart.Data<String, Double> data11;
                
                
                
               
                  
                    data11 = new XYChart.Data<String, Double>(t1.getNom(), t1.getRat());
                    
                    series11.getData().add(data11);
                 
                
                
                
                
                
                
            }
        } catch (SQLException ex) {
            Logger.getLogger(AfficherRController.class.getName()).log(Level.SEVERE, null, ex);
        }
          barchart.getData().addAll(series11);
                XYChart.Series<String, Double> series = new XYChart.Series<>();
                
                
                 FilteredList<rating> filteredData = new FilteredList<>(listR, b -> true);
		
		// 2. Set the filter Predicate whenever the filter changes.
		filterfield.textProperty().addListener((observable, oldValue, newValue) -> {
			filteredData.setPredicate(rating -> {
				// If filter text is empty, display all persons.
								
				if (newValue == null || newValue.isEmpty()) {
					return true;
				}
				
				// Compare first name and last name of every person with filter text.
				String lowerCaseFilter = newValue.toLowerCase();
				
				if (rating.getNom().toLowerCase().indexOf(lowerCaseFilter) != -1 ) {
					return true; // Filter matches first name.
				} 
				
				     else  
				    	 return false; // Does not match.
			});
		});
		
		// 3. Wrap the FilteredList in a SortedList. 
		SortedList<rating> sortedData = new SortedList<>(filteredData);
		
		// 4. Bind the SortedList comparator to the TableView comparator.
		// 	  Otherwise, sorting the TableView would have no effect.
		sortedData.comparatorProperty().bind(tabR.comparatorProperty());
		
		// 5. Add sorted (and filtered) data to the table.
		tabR.setItems(sortedData);
        
        setCellValueFromTableToTextField();
        
        
    }
    
    ///////////////fonctionwejdenesetcellvalue////////////
    
    private void setCellValueFromTableToTextField() {
        tabR.setOnMouseClicked(e -> {
     
            try {
                missionsel = tabR.getSelectionModel().getSelectedItem();
                
                BoxBlur blur = new BoxBlur(3,3,3);
                Stage stage1 = (Stage) ((Node) e.getSource()).getScene().getWindow();
                
                
                
                
                
                Parent root;
                
                root = FXMLLoader.load(getClass().getResource("detailRating.fxml"));
                Scene scene = new Scene(root);
                Stage stage = new Stage();
                stage.setScene(scene);
                stage.setTitle("Rating");
                stage.show();
                
                
                
                
                rootpane.setEffect(blur);
                stage.setOnHiding( ev -> { rootpane.setEffect(null);} );   
            } catch (IOException ex) {
                Logger.getLogger(AfficherRController.class.getName()).log(Level.SEVERE, null, ex);
            }
         
          
        });
        
        
    }

///////////////////////redirectionsarra//////////////////////
    @FXML
    void PRODUIT(ActionEvent event) throws IOException{
       

       try {
               
      
               Parent AnchorPane = FXMLLoader.load(getClass().getResource("Produit.fxml"));
               Stage stage = new Stage();
               Scene scene = new Scene(AnchorPane);
               stage.setScene(scene);
               stage.show();
           } catch (IOException ex) {
              
           }
                   
    
                          ((Node) event.getSource()).getScene().getWindow().hide();
        
    }
    
    
        @FXML
    void COMMANDE(ActionEvent event) throws IOException {
        
           try {
               
      
               Parent AnchorPane = FXMLLoader.load(getClass().getResource("CommandeBack.fxml"));
               Stage stage = new Stage();
               Scene scene = new Scene(AnchorPane);
               stage.setScene(scene);
               stage.show();
           } catch (IOException ex) {
              
           }
                   
    
                          ((Node) event.getSource()).getScene().getWindow().hide();
        


    }
    
    
    
    

    

    
    @FXML
    void login(MouseEvent event) {
        
         try {
               
      
               Parent AnchorPane = FXMLLoader.load(getClass().getResource("FXMLLogin.fxml"));
               Stage stage = new Stage();
               Scene scene = new Scene(AnchorPane);
               stage.setScene(scene);
               stage.show();
           } catch (IOException ex) {
              
           }
                   
    
                          ((Node) event.getSource()).getScene().getWindow().hide();
        


    }
    
    
    
 


  @FXML
    void chatadmin(MouseEvent event) {
        try {
               
      
               Parent AnchorPane = FXMLLoader.load(getClass().getResource("FXMLChatAdmin.fxml"));
               Stage stage = new Stage();
               Scene scene = new Scene(AnchorPane);
               stage.setScene(scene);
               stage.show();
           } catch (IOException ex) {
              
           }
                   
    
                          ((Node) event.getSource()).getScene().getWindow().hide();
        
        

    }
    
    
    ////////////////////STATproduit le plus vendu/////////
     private void Piechart(){
        piechartdata=FXCollections.observableArrayList();
    try {
        
        pst=cnx.prepareStatement("select * from produit");
           
      
        rs=pst.executeQuery();
       
        while(rs.next() )
        {
              pst1=cnx.prepareStatement("SELECT COUNT(*) as countprodcat FROM commande1 WHERE produit='"+rs.getString("id")+"'");
        rs1=pst1.executeQuery();
           
        while(rs1.next())
        {
            int i=Integer.valueOf(rs1.getString("countprodcat"));
            piechartdata.add(new PieChart.Data(rs.getString(3),i));
            np.add(i);
            cat.add(rs.getString("nom"));
        }
        }
    } catch (SQLException ex) {
        Logger.getLogger(UIController.class.getName()).log(Level.SEVERE, null, ex);
    }
    }
     
     ///////////////////////////////////////////////////////////////////////////////////////////////
     
      private void Piechart2(){
        piechartdata2=FXCollections.observableArrayList();
    try {
        
        pst=cnx.prepareStatement("select * from categorie1");
           
      
        rs=pst.executeQuery();
       
        while(rs.next() )
        {
              pst1=cnx.prepareStatement("SELECT COUNT(*) as countprodcat FROM produit WHERE categorie='"+rs.getString("id")+"'");
        rs1=pst1.executeQuery();
           
        while(rs1.next())
        {
            int i=Integer.valueOf(rs1.getString("countprodcat"));
            piechartdata2.add(new PieChart.Data(rs.getString(2),i));
            np1.add(i);
            cat1.add(rs.getString("nom"));
        }
        }
    } catch (SQLException ex) {
        Logger.getLogger(UIController.class.getName()).log(Level.SEVERE, null, ex);
    }
      }
    ///////////////statMayssaaa///////
      private void Piechart33() throws SQLException{
        piechartdata33=FXCollections.observableArrayList();
   
       
        pst=cnx.prepareStatement("select * from evenement");
           
     
        rs=pst.executeQuery();
       
        while(rs.next() )
        {
              pst1=cnx.prepareStatement("SELECT count(*) as nbrpart FROM participant WHERE evenement_id='"+rs.getString("id")+"'");
        rs1=pst1.executeQuery();
           
        while(rs1.next())
        {
            int i=Integer.valueOf(rs1.getString("nbrpart"));
            piechartdata33.add(new PieChart.Data(rs.getString("nom"),i));
            np.add(i);
            cat.add(rs.getString("nom"));
        }
        }
    
      }
          /////////////////////boutonwejdene eleve///////////////////
   
       @FXML
    void toEleve(ActionEvent event) throws IOException {
  Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.close();

        Parent root2 = FXMLLoader.load(getClass().getResource("afficherEleve.fxml"));
        Scene scene2 = new Scene(root2);
        Stage stage2 = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage2.setScene(scene2);
        stage2.centerOnScreen();
        stage2.setTitle("Menu");
        
      stage2.show();
   
    }

     
     //////////////////////////////Redirection Mayssa events Back////////////
    
     @FXML
    void Events(ActionEvent event) throws IOException {
                try
                {
      
               Parent AnchorPane = FXMLLoader.load(getClass().getResource("FXMLEvenement.fxml"));
               Stage stage = new Stage();
               Scene scene = new Scene(AnchorPane);
               stage.setScene(scene);
               stage.show();
           } catch (IOException ex) {
              
           }
                   
    
                          ((Node) event.getSource()).getScene().getWindow().hide();
        
        

    }
    
        
///////////////////////redirection mahdi/////////////
      @FXML
    void documents(ActionEvent event) {
          try
                {
      
               Parent AnchorPane = FXMLLoader.load(getClass().getResource("CategorieDocument.fxml"));
               Stage stage = new Stage();
               Scene scene = new Scene(AnchorPane);
               stage.setScene(scene);
               stage.show();
           } catch (IOException ex) {
              
           }
                   
    
                          ((Node) event.getSource()).getScene().getWindow().hide();

    }
    
   

    }
     
     
     
     
     
     
     
     

             
    




    

