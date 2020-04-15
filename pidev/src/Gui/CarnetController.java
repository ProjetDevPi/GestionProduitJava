/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Gui;

import static Gui.AfficherEleveController.E_id_selection;
import static Gui.AfficherEleveController.MainStage;
import static Gui.AfficherEleveController.missionsel;
import animatefx.animation.BounceIn;
import animatefx.animation.FadeIn;
import animatefx.animation.Tada;
import entities.carnet;
import entities.eleve;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.Date;

import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import javafx.util.Duration;
import services.CarnetService;
import services.UserSevice;
import org.controlsfx.control.Notifications;


import com.itextpdf.text.Anchor;
import com.itextpdf.text.BadElementException;
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Chapter;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.List;
import com.itextpdf.text.ListItem;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.Section;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import com.jfoenix.controls.JFXButton;
import entities.absence;
import java.io.File;
import java.util.ArrayList;

import javafx.animation.TranslateTransition;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.DialogPane;
import javafx.scene.effect.BoxBlur;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;


/**
 * FXML Controller class
 *
 * @author wejdene
 */
public class CarnetController implements Initializable {
CarnetService ca=new CarnetService();
 ObservableList<carnet> listE = null;
  UserSevice s1=new UserSevice();
  @FXML
    private TableView<carnet> tabp;

    @FXML
    private TableColumn<?, ?> id;

    @FXML
    private TableColumn<?, ?> note;

    @FXML
    private TableColumn<?, ?> sem;

    @FXML
    private TableColumn<?, ?> student;
    
      static public carnet missionsel;
        @FXML
    private AnchorPane rootpane;   
      
    @FXML
   private Label eleve;

    @FXML
    private Label notee;

    @FXML
    private Label appreciation;

    @FXML
    private Label semestre;
        @FXML
    private Label date;
            @FXML
    private ImageView flag;
        static    public  carnet carn=new carnet();
public static int ide;
    @FXML
    private ImageView min;
       private Image img;
       
    @FXML
    private Pane panel;
       @FXML
    private Pane det;

        private static String FILE = "C:\\Users\\ASUS\\Desktop\\pidev\\sarra.pdf";
    private static Font catFont = new Font(Font.FontFamily.TIMES_ROMAN, 22,
            Font.BOLD ,BaseColor.ORANGE);
        private static Font catFont1 = new Font(Font.FontFamily.TIMES_ROMAN, 18,
            Font.BOLD ,BaseColor.GREEN);
    private static Font redFont = new Font(Font.FontFamily.TIMES_ROMAN, 12,
            Font.NORMAL, BaseColor.RED);
    private static Font subFont = new Font(Font.FontFamily.TIMES_ROMAN, 16,
            Font.BOLD);
    private static Font smallBold = new Font(Font.FontFamily.TIMES_ROMAN, 11,
            Font.BOLD);
       private static Font smallBold2 = new Font(Font.FontFamily.TIMES_ROMAN, 11,
            Font.BOLD,BaseColor.BLUE);
           private static Font smallBold3 = new Font(Font.FontFamily.TIMES_ROMAN, 11,
            Font.BOLD,BaseColor.MAGENTA);
               private static Font smallBold4 = new Font(Font.FontFamily.TIMES_ROMAN, 11,
            Font.BOLD,BaseColor.RED);
                   private static Font smallBold5 = new Font(Font.FontFamily.TIMES_ROMAN, 11,
            Font.BOLD,BaseColor.PINK);
     String path = "C:\\Users\\ASUS\\Desktop\\wejdene\\default-iphone.mp3";
      
      Media media = new Media(new File(path).toURI().toString());
      
      MediaPlayer mediaplayer = new MediaPlayer(media);
       @FXML
    private void show_details_Personne(ActionEvent event) throws IOException, SQLException {

        missionsel = tabp.getSelectionModel().getSelectedItem();
    int idd = missionsel.getId();
 
        this.id.setText(String.valueOf(idd));
        carnet car=new carnet();
       car=ca.Get_carnet_by_Id(idd);
     
        notee.setText(" "+car.getNote());
        appreciation.setText(" "+car.getAppreciation());
       semestre.setText(""+car.getSemestre());
          date.setText(""+car.getDate());
         
     String yass=  ca.findbynom(car.getEleve_id());

        eleve.setText(""+yass);
    
   
     

       
    }
      
     @FXML
    void rating(ActionEvent e) throws IOException {
        BoxBlur blur = new BoxBlur(3,3,3);
 Stage stage1 = (Stage) ((Node) e.getSource()).getScene().getWindow();
        
    
         

        
        Parent root = FXMLLoader.load(getClass().getResource("rating.fxml"));
        Scene scene = new Scene(root);
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.setTitle("Rating");
       stage.show();
       
        rootpane.setEffect(blur);
      stage.setOnHiding( ev -> { rootpane.setEffect(null);} );   
    }
   
    @FXML
    void bounce(MouseEvent event) {
  new Tada(panel).play();

    }
      @FXML
    void bo(MouseEvent event) {
  new FadeIn(det).play();

    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
    
    new Tada(panel).play();
        String ss = null ;
    try {
       ss= s1.getlogin();
      
    } catch (SQLException ex) {
        Logger.getLogger(CarnetController.class.getName()).log(Level.SEVERE, null, ex);
    }
           int result = Integer.parseInt(ss);
   
    try {
        listE=   ca.getcarnets(result);
    
    } catch (SQLException ex) {
        Logger.getLogger(CarnetController.class.getName()).log(Level.SEVERE, null, ex);
    }
    tabp.setItems(listE);
    
     id.setCellValueFactory(new PropertyValueFactory<>("id"));
       note.setCellValueFactory(new PropertyValueFactory<>("note"));
        sem.setCellValueFactory(new PropertyValueFactory<>("semestre"));
         
            
            student.setCellValueFactory(new PropertyValueFactory<>("yassine")); 
    }
 
         
      
      
      @FXML
    void notification(ActionEvent event) throws SQLException {
          String ss = null ;
    try {
       ss= s1.getlogin();
      
    } catch (SQLException ex) {
        Logger.getLogger(CarnetController.class.getName()).log(Level.SEVERE, null, ex);
    }
              int result = Integer.parseInt(ss);
          java.util.List<eleve> el = new ArrayList<>();
           
        el   = ca.findelevebylogin(result);
       

          
for (int i=0;i<el.size();i++)
{
      
 
      String nom=el.get(i).getNom();
      int idd=el.get(i).getId();
         java.util.List<absence> abs= new ArrayList<>();
       abs = ca.findabsencebyeleve(el.get(i).getId());
       
int k=0;
       for (int j=0;j<abs.size();j++)
       {
  
           Image img=new Image("/Gui/notification.png");
      ImageView v=new ImageView(img);
      v.setFitHeight(100);
      v.setFitWidth(100);
       mediaplayer.play();
        Notifications notificationBuilder;
    notificationBuilder = Notifications.create()
            .title("Alert d'Absence")
            .text("notification "+k+"  votre enfant  "+nom+" a ete absent le "+abs.get(j).getDate())
           
            .graphic(v)
             .hideAfter(Duration.seconds(8))
            .position(Pos.BOTTOM_RIGHT)
            .onAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent event){
               ide=idd;
                   FXMLLoader loader = new FXMLLoader(getClass().getResource(""));
         Parent root;
                    try {
                        
                         BoxBlur blur = new BoxBlur(3,3,3);
                        root = FXMLLoader.load(getClass().getResource("validerAbsence.fxml"));
                             Scene scene = new Scene(root);
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.setTitle("details");
       stage.show();
             rootpane.setEffect(blur);
      stage.setOnHiding( ev -> { rootpane.setEffect(null);} );
     
                    } catch (IOException ex) {
                        Logger.getLogger(CarnetController.class.getName()).log(Level.SEVERE, null, ex);
                    }
   
                }
            }  );
  
    notificationBuilder.darkStyle();
        notificationBuilder.show();
            
     k++;
      }

       
      
      
 if (el.get(i).getNbre_absence()==0){
           Image img=new Image("/Gui/small_tick.png");
      ImageView v=new ImageView(img);
      v.setFitHeight(100);
      v.setFitWidth(100);
        Notifications notificationBuilder;
    notificationBuilder = Notifications.create()
            .title("Alert d'Absence")
            .text("Vote  Enfant  "+nom+"  n' Est pas Absent ")
           
            .graphic(v)
             .hideAfter(Duration.seconds(8))
            .position(Pos.BOTTOM_RIGHT);
        notificationBuilder.darkStyle();
        notificationBuilder.show();
      }
 }
       

    
    }
                               @FXML
    void pdf(ActionEvent event) {
  
  int idd = missionsel.getId();
                 try {
        carn=ca.Get_carnet_by_Id(idd);
    } catch (SQLException ex) {
        Logger.getLogger(CarnetController.class.getName()).log(Level.SEVERE, null, ex);
    }
  
 try {
            Document document = new Document();
            PdfWriter.getInstance(document, new FileOutputStream(FILE));
            document.open();
       
            addTitlePage(document);
          
            document.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
  Alert alert = new Alert(Alert.AlertType.CONFIRMATION);


   alert.setHeaderText(null);
         alert.setContentText("Votre PDF a été Crée Avec succée  ");
         alert.showAndWait();
         DialogPane dialogPane = alert.getDialogPane();
    }
    
    


    private static void addTitlePage(Document document)
            throws DocumentException, SQLException, BadElementException, IOException {
        Paragraph preface = new Paragraph();
        // We add one empty line
        addEmptyLine(preface, 1);
        // Lets write a big header
        CarnetService cs=new CarnetService();
             String yass=  cs.findbynom(carn.getEleve_id());
 com.itextpdf.text.Image image = com.itextpdf.text.Image.getInstance("C:\\Users\\ASUS\\Desktop\\pidev\\flag.png");


image.setAbsolutePosition(0,750);
     image.scaleToFit(100,80 );

  
            document.add(image);
          

     
        preface.add(new Paragraph("                            Carnet De   "+yass, catFont));
        preface.add(new Paragraph("                           ***Numéro de Carnet   "+carn.getId()+"***", catFont1));
        addEmptyLine(preface, 1);
        // Will create: Report generated by: _name, _date
        preface.add(new Paragraph(
                "                                        Report generated by: " + System.getProperty("user.name") + ", " + new Date(), //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
                smallBold));
        addEmptyLine(preface, 3);
        preface.add(new Paragraph(
                "*Semestre:     "+carn.getSemestre(),
                smallBold2));

        addEmptyLine(preface, 2);
        
              preface.add(new Paragraph(
                "*Note génèrale :    "+carn.getNote(),
                smallBold3));
                 addEmptyLine(preface, 2);
                     preface.add(new Paragraph(
                "*Apprreciation des Enseignant  :   "+carn.getAppreciation(),
                smallBold4));
                      addEmptyLine(preface, 2);
                      
                              preface.add(new Paragraph(
                "*Date Du Carnet  :"+carn.getDate(),
                smallBold5));
        
addEmptyLine(preface, 15);
        preface.add(new Paragraph(
                "This document is a preliminary version and not subject to your license agreement or any other agreement with vogella.com ;-).",
                redFont));

        document.add(preface);
        // Start a new page
        document.newPage();
    }

    private static void addContent(Document document) throws DocumentException {
        Anchor anchor = new Anchor("", catFont);
        
        anchor.setName("First Chapter");

        // Second parameter is the number of the chapter
        Chapter catPart = new Chapter(new Paragraph(anchor), 1);

        Paragraph subPara = new Paragraph("Subcategory 1", subFont);
        Section subCatPart = catPart.addSection(subPara);
        subCatPart.add(new Paragraph("Hello"));

        subPara = new Paragraph("Subcategory 2", subFont);
        subCatPart = catPart.addSection(subPara);
        subCatPart.add(new Paragraph("Paragraph 1"));
        subCatPart.add(new Paragraph("Paragraph 2"));
        subCatPart.add(new Paragraph("Paragraph 3"));

        // add a list
        createList(subCatPart);
        Paragraph paragraph = new Paragraph();
        addEmptyLine(paragraph, 5);
        subCatPart.add(paragraph);

        // add a table
        createTable(subCatPart);

        // now add all this to the document
        document.add(catPart);

        // Next section
        anchor = new Anchor("Second Chapter", catFont);
        anchor.setName("Second Chapter");

        // Second parameter is the number of the chapter
        catPart = new Chapter(new Paragraph(anchor), 1);

        subPara = new Paragraph("Subcategory", subFont);
        subCatPart = catPart.addSection(subPara);
        subCatPart.add(new Paragraph("This is a very important message"));

        // now add all this to the document
        document.add(catPart);

    }
 private static void createTable(Section subCatPart)
            throws BadElementException {
        PdfPTable table = new PdfPTable(3);

        // t.setBorderColor(BaseColor.GRAY);
        // t.setPadding(4);
        // t.setSpacing(4);
        // t.setBorderWidth(1);

        PdfPCell c1 = new PdfPCell(new Phrase("Table Header 1"));
        c1.setHorizontalAlignment(Element.ALIGN_CENTER);
        table.addCell(c1);

        c1 = new PdfPCell(new Phrase("Table Header 2"));
        c1.setHorizontalAlignment(Element.ALIGN_CENTER);
        table.addCell(c1);

        c1 = new PdfPCell(new Phrase("Table Header 3"));
        c1.setHorizontalAlignment(Element.ALIGN_CENTER);
        table.addCell(c1);
        table.setHeaderRows(1);

        table.addCell("1.0");
        table.addCell("1.1");
        table.addCell("1.2");
        table.addCell("2.1");
        table.addCell("2.2");
        table.addCell("2.3");

        subCatPart.add(table);

    }

    private static void createList(Section subCatPart) {
        List list = new List(true, false, 10);
        list.add(new ListItem("First point"));
        list.add(new ListItem("Second point"));
        list.add(new ListItem("Third point"));
        subCatPart.add(list);
    }

    private static void addEmptyLine(Paragraph paragraph, int number) {
        for (int i = 0; i < number; i++) {
            paragraph.add(new Paragraph(" "));
        }
    }
   
    ///////////////redirection home///////////
     @FXML
    void homeretour(ActionEvent event) throws IOException {
        
       try {
               
      
               Parent AnchorPane = FXMLLoader.load(getClass().getResource("Home.fxml"));
               Stage stage = new Stage();
               Scene scene = new Scene(AnchorPane);
               stage.setScene(scene);
               stage.show();
                stage.setTitle("Produits");
           } catch (IOException ex) {
              
           }
                   
    
                          ((Node) event.getSource()).getScene().getWindow().hide();
        


    }
    
    @FXML
    void products(ActionEvent event) throws IOException {
        try {
               
      
               Parent AnchorPane = FXMLLoader.load(getClass().getResource("ProduitFront.fxml"));
               Stage stage = new Stage();
               Scene scene = new Scene(AnchorPane);
               stage.setScene(scene);
               stage.show();
                stage.setTitle("Produits");
           } catch (IOException ex) {
              
           }
                   
    
                          ((Node) event.getSource()).getScene().getWindow().hide();
        


    }
    
    @FXML
    void inscriptionretour(ActionEvent event) throws IOException {
       try {
               
      
               Parent AnchorPane = FXMLLoader.load(getClass().getResource("inscription.fxml"));
               Stage stage = new Stage();
               Scene scene = new Scene(AnchorPane);
               stage.setScene(scene);
               stage.show();
                stage.setTitle("inscription");
           } catch (IOException ex) {
              
           }
                   
    
                          ((Node) event.getSource()).getScene().getWindow().hide();
        


    }
     @FXML
    void library(ActionEvent event) {
        
          try {
               
      
               Parent AnchorPane = FXMLLoader.load(getClass().getResource("Emprunt.fxml"));
               Stage stage = new Stage();
               Scene scene = new Scene(AnchorPane);
               stage.setScene(scene);
               stage.show();
           } catch (IOException ex) {
              
           }
                   
    
                          ((Node) event.getSource()).getScene().getWindow().hide();

    }
    
    @FXML
    void events(ActionEvent event) {
         try {
               
      
               Parent AnchorPane = FXMLLoader.load(getClass().getResource("FXMLevenementfront.fxml"));
               Stage stage = new Stage();
               Scene scene = new Scene(AnchorPane);
               stage.setScene(scene);
               stage.show();
           } catch (IOException ex) {
              
           }
                   
    
                          ((Node) event.getSource()).getScene().getWindow().hide();

    }

}
