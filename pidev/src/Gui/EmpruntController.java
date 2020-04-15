/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Gui;

//import Dialog.AlertDialog;
import static Gui.CategorieDocumentController.saveToFileImageNormal;
import com.nexmo.client.NexmoClient;
import com.nexmo.client.sms.MessageStatus;
import com.nexmo.client.sms.SmsSubmissionResponse;
import com.nexmo.client.sms.messages.TextMessage;
import entities.Categorie;
import entities.Documents;
import entities.Emprunt;
import entities.FosUser;
import java.awt.AWTException;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Optional;
import java.util.Properties;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.FileChooser;
import javax.imageio.ImageIO;
import org.apache.commons.lang3.RandomStringUtils;
import services.categorieService;
import services.UserSevice;
import utils.MyConnection;
import javafx.scene.control.DatePicker;
import javafx.stage.Stage;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.swing.JOptionPane;
import services.documentService;
import services.empruntService;
 
/**
 * FXML Controller class
 *
 * @author Mehdi
 */
public class EmpruntController implements Initializable {

    /**
     * Initializes the controller class.
     */
    private ObservableList<Documents> datadoc;
    private ObservableList<Emprunt> dataemp;

private Connection con;
    private ResultSet rs=null;
    private PreparedStatement pst;
       @FXML
    private Button buttemp;
    @FXML
    private TableView<Documents> tab_doc;
    @FXML
    private TableColumn<?, ?> Iddoc;
    @FXML
    private TableColumn<?, ?> empdoc;
    @FXML
    private TableColumn<?, ?> nomdoc;
    @FXML
    private TableColumn<?, ?> catdoc;
    @FXML
    private TableColumn<?, ?> etatdoc;
      @FXML
    private ImageView imgview;
    @FXML
    private DatePicker date_emp;
    @FXML
    private DatePicker date_ret;
    @FXML
    private TableView<Emprunt> tab_empuser;
    @FXML
    private TableColumn<?, ?> nomdocemp;
    @FXML
    private TableColumn<?, ?> dateempaff;
        @FXML
    private TableColumn<?, ?> dateretaff;
      @FXML
    private DatePicker newretdate;
    
      @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        con = MyConnection.getInstance().getCnx();
               datadoc= FXCollections.observableArrayList();
               dataemp= FXCollections.observableArrayList();

        afficherdoc();
       loadDatadoc();
     
        setCellValueFromTableToTextFielddoc();
        try {
            loadDataempuser();
        } catch (SQLException ex) {
            Logger.getLogger(EmpruntController.class.getName()).log(Level.SEVERE, null, ex);
        }
        afficherempuser();
        
    }    
        private void adaptemp()
    {
    
    try {
        pst=con.prepareStatement("select emprunte from document where id");
    } catch (SQLException ex) {
        Logger.getLogger(CategorieController.class.getName()).log(Level.SEVERE, null, ex);
    }
    }
         
    
     private void loadDatadoc() {
   
       datadoc.clear();
  
    try {
        pst =con.prepareStatement("Select * from document");
       

    rs=pst.executeQuery();
    categorieService cat=new categorieService();
     while (rs.next()) {                
            Documents d=new Documents(rs.getInt("id"),rs.getString("nomdocument"),cat.getNamecatbyId(rs.getInt("categorie")), rs.getString("etatdocument"),rs.getInt("emprunte"));
             datadoc.add(d); 
           
     }
        } catch (SQLException ex) {
        Logger.getLogger(CategorieController.class.getName()).log(Level.SEVERE, null, ex);
    }

        tab_doc.setItems(datadoc);
    }
   
     private void afficherdoc(){

             nomdoc.setCellValueFactory(new PropertyValueFactory <>("nomdocument"));
             catdoc.setCellValueFactory(new PropertyValueFactory <>("nomcategorie"));
             etatdoc.setCellValueFactory(new PropertyValueFactory <>("etatdocument")); 
             
        }
     private void setCellValueFromTableToTextFielddoc(){
    tab_doc.setOnMouseClicked(new EventHandler<MouseEvent>(){
        @Override
        public void handle(MouseEvent event) {
Documents doc=tab_doc.getItems().get(tab_doc.getSelectionModel().getSelectedIndex());


             String photo = null;
                  TableColumn.CellEditEvent edittedcell = null;
            Documents d=gettempdoc1(edittedcell);
            
            try {
                photo = getImagebyId(d.getId());
            } catch (SQLException ex) {
                Logger.getLogger(EmpruntController.class.getName()).log(Level.SEVERE, null, ex);
            }

            Image imageURL= new Image("file:///C:/Users/ASUS/Desktop/pidev/imagedocmehdi/" + photo);
            imgview.setImage(imageURL);
             
            

        }
        });
        }
     
          @FXML
    private void addImage(ActionEvent event) throws IOException{
        FileChooser fc = new FileChooser();

        FileChooser.ExtensionFilter extFilterJPG = new FileChooser.ExtensionFilter("JPG files (*.jpg)", "*.JPG");
        FileChooser.ExtensionFilter extFilterPNG = new FileChooser.ExtensionFilter("PNG files (*.png)", "*.PNG");
        fc.getExtensionFilters().addAll(extFilterJPG, extFilterPNG);
        File selectedFile = fc.showOpenDialog(null);
        try {
            BufferedImage bufferedImage = ImageIO.read(selectedFile);
              Image image = SwingFXUtils.toFXImage(bufferedImage, null);
             imgview.setImage(image);
        } catch (IOException ex) {
        }
    }
    
    
     public static String saveToFileImageNormal(Image image)throws SQLException  {

        String ext = "jpg";
        File dir = new File("D:\\LOGICIELS\\wamp64\\www\\pidev\\src\\images");
        String name;
        name = String.format("%s.%s", RandomStringUtils.randomAlphanumeric(8), ext);
        File outputFile = new File(dir, name);
        BufferedImage bImage = SwingFXUtils.fromFXImage(image, null);
        try {
            ImageIO.write(bImage, "png", outputFile);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return name;
    }
    
                      @FXML
    public Documents gettempdoc1(TableColumn.CellEditEvent edittedCell) {
        Documents test = tab_doc.getSelectionModel().getSelectedItem();
        return test;
    }  
        public String getImagebyId(int ide) throws SQLException
    {
        String i="";
          Statement ste;
        String  id=null;
           String query="SELECT nom_image as nom_image FROM document WHERE id LIKE '%"+ide+"%'";
           ste=con.createStatement();
        ResultSet rst = ste.executeQuery(query); 
         while(rst.next())
        {
             i=rst.getString("nom_image");

        }


        return i;
    }
        
@FXML 
   public void ajouteremp(ActionEvent event) throws SQLException, AWTException, MalformedURLException {
    dataemp.clear();
java.util.Date today = new java.util.Date();
java.sql.Date sqlDate = new java.sql.Date(today.getTime());
int i;
           
    Date dateemprunt = Date.valueOf(date_emp.getValue());
    Date dateretour = Date.valueOf(date_ret.getValue());
       empruntService emp = new empruntService();
           Documents doc=tab_doc.getItems().get(tab_doc.getSelectionModel().getSelectedIndex());
           int iz= doc.getId();
           UserSevice use= new UserSevice();
           String id_user=use.getlogin();
           int user=Integer.parseInt(id_user);
           documentService dd=new documentService();
           String namedd =dd.getNamedocbyId(iz);
            categorieService cat=new categorieService();
            
           Emprunt e =new Emprunt(iz,user, dateemprunt, dateretour) ;
           if ( sqlDate.compareTo(dateemprunt)>0 )
            {
        JOptionPane.showMessageDialog(null, "Erreur ! La date d'emprunt doit être superieure à la date actuelle");

             }
           else if ( dateretour.compareTo(dateemprunt)<0 )
            {
        JOptionPane.showMessageDialog(null, "Erreur ! La date de retour doit être superieure à la date d'emprunt ");

            }
           
           else if (doc.getEmprunte()==1 )
           {
                 JOptionPane.showMessageDialog(null, "Erreur ! Le document est emprunté");
           }
           else {
               i=emp.emprunter(e);
           if(i==1)
              {
            
          
          Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Emprunt ajouté ");
            alert.setHeaderText("Votre Emprunt du document a été bien ajouté");
               Optional<ButtonType> resul = alert.showAndWait();
               /*/
           NexmoClient client = NexmoClient.builder().apiKey("1a39295c").apiSecret("7Iul24U8rmw7ZN9i").build();
  TextMessage message = new TextMessage("kido",
                   "+21692048760",
                    "your document"+ namedd +"has been loaned on "+dateemprunt+"you have to return it on "+dateretour
            );

SmsSubmissionResponse response = client.getSmsClient().submitMessage(message);
  if (response.getMessages().get(0).getStatus() == MessageStatus.OK) {
    System.out.println("Message sent successfully.");
} else {
    System.out.println("Message failed with error: " + response.getMessages().get(0).getErrorText());
}/*/
        }
     
              
        }
              afficherdoc();
            loadDatadoc();
 loadDataempuser();
        } 
  
@FXML
    void showhome(ActionEvent event) throws IOException{
        try {
               
               
     
               Parent AnchorPane = FXMLLoader.load(getClass().getResource("Home.fxml"));
               Stage stage = new Stage();
               Scene scene = new Scene(AnchorPane);
               stage.setScene(scene);
               stage.show();
           } catch (IOException ex) {
                          System.out.println(ex.getMessage());

           }
                   
    
                          ((Node) event.getSource()).getScene().getWindow().hide();
        

    }
   
     private void afficherempuser(){

            nomdocemp.setCellValueFactory(new PropertyValueFactory <>("nomdocument"));
             dateempaff.setCellValueFactory(new PropertyValueFactory <>("dateemprunt")); 
             dateretaff.setCellValueFactory(new PropertyValueFactory <>("dateretour")); 
             
        }
        
        private void loadDataempuser() throws SQLException {
            //dataemp.clear();
           UserSevice use= new UserSevice();
                    
            String curuseer=use.getlogin();
            int user=Integer.parseInt(curuseer);
        pst =con.prepareStatement( "select * from emprunt WHERE `id_user`= '"+user+"'");
    documentService doc=new documentService();

    rs=pst.executeQuery();
     while (rs.next()) {                

         Emprunt e=new Emprunt(rs.getInt("id"),doc.getNamedocbyId(rs.getInt("documentid")),rs.getDate("dateemprunt"),rs.getDate("dateretour"));
            
             dataemp.add(e); 
     }
     

       tab_empuser.setItems(dataemp);
       afficherempuser();
        }
    public void sendMail() throws SQLException {
        //Setting up configurations for the email connection to the Google SMTP server using TLS
        Properties props = new Properties();
        props.put("mail.smtp.host", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "587");
        props.put("mail.smtp.auth", "true");
        //Establishing a session with required user details
        Session session = Session.getInstance(props, new javax.mail.Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication("mehditekayatest@gmail.com", "Mehdi123");
            }
        });
        try {
            Date dateExt = Date.valueOf(newretdate.getValue());

            empruntService emp= new empruntService();
            String rec=emp.getmail();
            //Creating a Message object to set the email content
            MimeMessage msg = new MimeMessage(session);
            //Storing the comma seperated values to email addresses
            String to = rec;
            /*Parsing the String with defualt delimiter as a comma by marking the boolean as true and storing the email
            addresses in an array of InternetAddress objects*/
            InternetAddress[] address = InternetAddress.parse(to, true);
            //Setting the recepients from the address variable
            msg.setRecipients(Message.RecipientType.TO, address);
            msg.setSubject("Votre demande d'extension");
            msg.setText("Votre extension a été accordée avec succés la nouvelle date de retour est "+dateExt);
            msg.setHeader("XPriority", "1");
            Transport.send(msg);
            System.out.println("Mail has been sent successfully");
        } catch (MessagingException mex) {
            System.out.println("Unable to send an email" + mex);
        }
    }
    @FXML
    public void extensionemp(ActionEvent event) throws SQLException, AWTException, MalformedURLException {
          Emprunt test = tab_empuser.getSelectionModel().getSelectedItem();
            Date dateretour=test.getDateretour();
            TableColumn.CellEditEvent edittedcell = null;
            int x=gettempemp(edittedcell);
            Date dateExt = Date.valueOf(newretdate.getValue());
            int i;
            empruntService emp = new empruntService();

           if(dateExt.compareTo(dateretour)<0)
           {       
               JOptionPane.showMessageDialog(null, "Erreur ! La date d'extension doit être superieure à la date de retour ");

           } 
           else
           {
            i=emp.extemp(x,dateExt);
              if(i==1)
        {
      dataemp.clear();
     
   
      //mail 
     sendMail();
             JOptionPane.showMessageDialog(null, "Extension accordée ");

            afficherempuser();
            loadDataempuser();
            
        }
          
        }     
          
           
        
    }
    @FXML
    public int gettempemp(TableColumn.CellEditEvent edittedCell) {
        Emprunt test = tab_empuser.getSelectionModel().getSelectedItem();
        int x= test.getId();
        return x;
}
//////////////////////////////////////////////redirectionnnnnnnnnn Mehdi/////////
    
    @FXML
    void carnet(ActionEvent event) {
         try {
               
               
     
               Parent AnchorPane = FXMLLoader.load(getClass().getResource("carnet.fxml"));
               Stage stage = new Stage();
               Scene scene = new Scene(AnchorPane);
               stage.setScene(scene);
               stage.show();
           } catch (IOException ex) {
                          System.out.println(ex.getMessage());

           }
                   
    
                          ((Node) event.getSource()).getScene().getWindow().hide();
        

    }

    @FXML
    void events(ActionEvent event) {
         try {
               
               
     
               Parent AnchorPane = FXMLLoader.load(getClass().getResource("FXMLeventfront.fxml"));
               Stage stage = new Stage();
               Scene scene = new Scene(AnchorPane);
               stage.setScene(scene);
               stage.show();
           } catch (IOException ex) {
                          System.out.println(ex.getMessage());

           }
                   
    
                          ((Node) event.getSource()).getScene().getWindow().hide();
        

    }

  
    @FXML
    void inscriptionretour(ActionEvent event) {
         try {
               
               
     
               Parent AnchorPane = FXMLLoader.load(getClass().getResource("inscription.fxml"));
               Stage stage = new Stage();
               Scene scene = new Scene(AnchorPane);
               stage.setScene(scene);
               stage.show();
           } catch (IOException ex) {
                          System.out.println(ex.getMessage());

           }
                   
    
                          ((Node) event.getSource()).getScene().getWindow().hide();
        

    }

    @FXML
    void products(ActionEvent event) {
         try {
               
               
     
               Parent AnchorPane = FXMLLoader.load(getClass().getResource("ProduitFront.fxml"));
               Stage stage = new Stage();
               Scene scene = new Scene(AnchorPane);
               stage.setScene(scene);
               stage.show();
           } catch (IOException ex) {
                          System.out.println(ex.getMessage());

           }
                   
    
                          ((Node) event.getSource()).getScene().getWindow().hide();
        

    }
}
