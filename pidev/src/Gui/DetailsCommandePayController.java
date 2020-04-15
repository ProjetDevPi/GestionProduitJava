/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Gui;

import static Gui.PanierController.commande1;
import static Gui.PanierController.etat;
import static Gui.ProduitController.MainStage;
import com.itextpdf.text.Anchor;
import com.itextpdf.text.BadElementException;
import com.itextpdf.text.Chapter;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.List;
import com.itextpdf.text.ListItem;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.Section;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import com.jfoenix.controls.JFXButton;
import entities.CommandeProduit;
import entities.Produit;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.Date;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import javax.swing.JOptionPane;
import services.CommandeProduitService;
import services.ProduitService;

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
import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTextField;
import java.io.FileOutputStream;
import java.time.LocalDateTime;
import java.util.Date;
import javafx.scene.control.Alert;
import javafx.scene.control.DialogPane;


/**
 * FXML Controller class
 *
 * @author ASUS
 */
public class DetailsCommandePayController implements Initializable {

   
    
    
    
      @FXML
    private JFXTextField txt2;

    @FXML
    private JFXTextField txt3;

    @FXML
    private JFXTextArea txt4;

     
      @FXML
    private  Label id_commande;

    @FXML
    private  Label nomprod_commande;

    @FXML
    private Label prixtotal_commande;

    @FXML
    private  Label etat_commande;

    @FXML
    private  Label quantite_commande;

    @FXML
    private  ImageView photoprod_commande;

      @FXML
    private  Label user_commande;
    
    
    private Image image;
    
    @FXML
    private JFXButton payer;
    
    @FXML
    private Label paye;
    
    
    static public CommandeProduit c = new CommandeProduit();
    
       public static String etat;//variable static
    
    /////////////////////////////////////////////////
      private static String FILE = "C:\\Users\\ASUS\\Desktop\\pidev\\Bon de Commande.pdf";
      
      
      
      ////////////////Revenue////////////////////////////////
          public static  LocalDateTime last_change=LocalDateTime.now(); 
    public static int jour;
     public static double price;
      
     
       //////   ///////PDF///////////////////////////////////////////////////////  
    private static Font catFont = new Font(Font.FontFamily.TIMES_ROMAN, 18,
            Font.BOLD, BaseColor.BLUE);
    private static Font redFont = new Font(Font.FontFamily.TIMES_ROMAN, 12,
            Font.BOLD, BaseColor.RED);
    
     private static Font boldFont = new Font(Font.FontFamily.TIMES_ROMAN, 14,
            Font.BOLD, BaseColor.RED);
     
     private static Font boldFont1 = new Font(Font.FontFamily.TIMES_ROMAN, 16,
            Font.BOLD, BaseColor.ORANGE);
    
     private static Font bleueFont = new Font(Font.FontFamily.TIMES_ROMAN, 14,
            Font.NORMAL, BaseColor.BLUE);
     
      private static Font magentaFont = new Font(Font.FontFamily.TIMES_ROMAN, 14,
            Font.NORMAL, BaseColor.MAGENTA);
     
      private static Font orangeFont = new Font(Font.FontFamily.TIMES_ROMAN, 14,
            Font.NORMAL, BaseColor.ORANGE);
      
       private static Font pinkFont = new Font(Font.FontFamily.TIMES_ROMAN, 14,
            Font.NORMAL, BaseColor.PINK);
       
         private static Font cyanFont = new Font(Font.FontFamily.TIMES_ROMAN, 14,
            Font.NORMAL, BaseColor.CYAN);
         
          private static Font greenFont = new Font(Font.FontFamily.TIMES_ROMAN, 14,
            Font.NORMAL, BaseColor.GREEN);
    private static Font subFont = new Font(Font.FontFamily.TIMES_ROMAN, 16,
            Font.BOLD);
    private static Font smallBold = new Font(Font.FontFamily.TIMES_ROMAN, 12,
            Font.BOLD);
    

   
   ////////////////details cmd//////////////////
     
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
           int idd = PanierController.idcommande1;
        
          
        //String yassine = FXMLDocumentController.missionsel.getYassine();
        //Commande c = new Commande();
         CommandeProduitService cs = new CommandeProduitService();
         
         Produit p = new Produit();
         ProduitService ps = new ProduitService();
         
         
        
        this.id_commande.setText(String.valueOf(idd));
        
          try {
              c =cs.Get_Commande_by_Id(idd);
          } catch (SQLException ex) {
              Logger.getLogger(DetailsCommandePayController.class.getName()).log(Level.SEVERE, null, ex);
          }
        id_commande.setText(""+c.getId());
          try {
              nomprod_commande.setText(""+ps.getnomprod(c.getProduit())); //attentionnnn
              
             
              
          } catch (SQLException ex) {
              Logger.getLogger(DetailsCommandePayController.class.getName()).log(Level.SEVERE, null, ex);
          }
        prixtotal_commande.setText(""+c.getPrixtotal());
        
         quantite_commande.setText(""+c.getQuantite());
         
          etat_commande.setText(""+c.getEtat());
          
          paye.setText(""+c.getPay());
         
         
       
        String yass1 = null;
          try {
              yass1= cs.getloginusername();
              //yass = cs.findbynom(c.getUser());
          } catch (SQLException ex) {
              Logger.getLogger(DetailsCommandePayController.class.getName()).log(Level.SEVERE, null, ex);
          }
        user_commande.setText("nomuser : "+yass1);
        
        
        String a = null;
          try {
              a = ps.getnomimage(c.getProduit());
          } catch (SQLException ex) {
              Logger.getLogger(DetailsCommandePayController.class.getName()).log(Level.SEVERE, null, ex);
          }
               Image image = new Image("file:"+a+"",70, 70, true, true);
                ImageView photo= new ImageView(image);
                c.setPhoto2(photo);
                    photoprod_commande.setImage(image);
                   

                System.out.println(a);


        
        
        
        
         // image = new Image("file:"+c.getPhoto2()+"", photoprod_commande.getFitWidth(), photoprod_commande.getFitHeight(), true, true);
                //photoprod_commande.setImage(image);
               
                
                
         
     
     
    }   
    
    ////modif pay
     @FXML
    void payer(ActionEvent event) throws SQLException, IOException {
        
         last_change=LocalDateTime.now();
        
         jour=last_change.getDayOfMonth();
        
       CommandeProduitService s3 = new CommandeProduitService();
       ProduitService s2 = new ProduitService();
             if (PanierController.etat.equals("Non Validée"))
                 {
                      JOptionPane.showMessageDialog(null, "Desolé la Commande n'est pas encore validée");
                 }
         else
             {
            try {
                s3.editpaye(PanierController.idcommande1);
                price=s3.findprixbyid(PanierController.idcommande1);
                
               s2.ajouter_revenu();
              
           s3.ajouter_notif(); //apres paiement  il y'aura une notification a l'admin du paiement effectué
             
        
            } catch (SQLException ex) {
                Logger.getLogger(DetailsCommandePayController.class.getName()).log(Level.SEVERE, null, ex);
            }
         JOptionPane.showMessageDialog(null, "Commande Payée");
         //FXMLProduitCategorieController.revenu+=s3.findprixbyid(FXMLPanierController.idcommande1);
             }
          
         Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.close();

        Parent root2 = FXMLLoader.load(getClass().getResource("Panier.fxml"));
        Scene scene2 = new Scene(root2);
        Stage stage2 = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage2.setScene(scene2);
        stage2.centerOnScreen();
       
        
      stage2.show();
     //PanierController.close();
         
         
        
                          
             
        
      
    
    }
  
    
    
   
    //////////////////////////PDF//////////////////////////////
    
      @FXML
    void pdf(ActionEvent event) {
        
 try {
            Document document = new Document();
            PdfWriter.getInstance(document, new FileOutputStream(FILE));
            document.open();
            addMetaData(document);
            addTitlePage(document);
           
            document.close();
              Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
         alert.setHeaderText("pdf");
         alert.setContentText("Votre bon de commande est pret à imprimer ");
         alert.showAndWait();
         DialogPane dialogPane = alert.getDialogPane();
            
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    
     private static void addMetaData(Document document) {
        document.addTitle("My first PDF");
        document.addSubject("Using iText");
        document.addKeywords("Java, PDF, iText");
        document.addAuthor("Lars Vogel");
        document.addCreator("Lars Vogel");
        
    }

    private static void addTitlePage(Document document)
            throws DocumentException, BadElementException, IOException {
        
        
      
        com.itextpdf.text.Image image = com.itextpdf.text.Image.getInstance("C:\\Users\\ASUS\\Desktop\\pidev\\src\\images\\log.png");
         image.scaleToFit(100, 100);
         //image.setAbsolutePosition(0, 800);
         
        
         
            document.add(image);
        Paragraph preface = new Paragraph();
        // We add one empty line
        addEmptyLine(preface, 1);
        // Lets write a big header
        preface.add(new Paragraph("                                      La Commande de " +c.getYassine(), catFont));

        addEmptyLine(preface, 1);
        
         document.add(new Paragraph("                                                                                                                 "));
         document.add(new Paragraph("                                                                                                                 "));
        // Will create: Report generated by: _name, _date
        preface.add(new Paragraph(
                "Report generated by: " + System.getProperty("user.name") + ", " + new Date(), //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
                smallBold));
        addEmptyLine(preface, 1);
        preface.add(new Paragraph(
                   "                                           Contenu de la Commande    "+c.getId(),
                boldFont1));
        

        addEmptyLine(preface, 1);
        
       
           preface.add(new Paragraph
        ("             *Nom du Produit Commandé:        "   +c.getNom_prod() , pinkFont));
        
         addEmptyLine(preface, 1);
         
           preface.add(new Paragraph
        ("             *Quantite de la Commande:        "  +c.getQuantite() ,magentaFont));
          
           addEmptyLine(preface,1);
           
          
           
            preface.add(new Paragraph
        ("            *La Commande:                    "  +c.getPay() ,cyanFont));
            
              addEmptyLine(preface,1);
           
            preface.add(new Paragraph
        ("            *Etat de la Commande:            "  +   c.getEtat() ,greenFont));

         
                addEmptyLine(preface,1);
           
            preface.add(new Paragraph
        ("            *Prix Totale de la commande:                   "  +c.getPrixtotal() ,boldFont));
        document.add(preface);
        
        // Start a new page
        
        document.newPage();
    }

   


    private static void addEmptyLine(Paragraph paragraph, int number) {
        for (int i = 0; i < number; i++) {
            paragraph.add(new Paragraph(" "));
        }
    }
    
    
}
