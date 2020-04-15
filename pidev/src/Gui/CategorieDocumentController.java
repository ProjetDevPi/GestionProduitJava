/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Gui;

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

import entities.Categorie;
import entities.Documents;
import entities.Emprunt;
import java.awt.AWTException;
import java.awt.TrayIcon;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
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
import javafx.scene.control.ComboBox;
import javafx.scene.control.DialogPane;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javax.imageio.ImageIO;
import javax.swing.JOptionPane;
import org.apache.commons.lang3.RandomStringUtils;
import services.UserSevice;
import services.categorieService;
import services.documentService;
import services.empruntService;
import utils.MyConnection;

/**
 * FXML Controller class
 *
 * @author Mehdi Tekaya
 */
public class CategorieDocumentController implements Initializable {
private ObservableList<Categorie> data;
private ObservableList<Documents> datadoc;
private ObservableList<Emprunt> dataemp;

private Connection con;
    private ResultSet rs=null;
    private PreparedStatement pst;
    @FXML
    private TextField tfnom;

    @FXML
    private Button buttajoutcat;
    @FXML
    private Button buttsupprimercat;
    @FXML

    private Button buttmodifiercat;
    @FXML
    private TableView<Categorie> tab_cat;

    @FXML
    private TableView<Emprunt> tab_emp;
    @FXML
    private TableColumn<Categorie, ?> nomc;
    
    @FXML
    private Label error_namecat; 
    @FXML
    private TextField tfnomdoc;
    @FXML
    private Button buttajoutdoc;
    @FXML
    private TableView<Documents> tab_doc;

    @FXML
    private TableColumn<?, ?> empdoc;
    @FXML
    private TableColumn<?, ?> nomdoc;
    @FXML
    private TableColumn<?, ?> catdoc;
    @FXML
    private TableColumn<?, ?> etatdoc;

    @FXML
    private TableColumn<?, ?> iddocemp;
    @FXML
    private TableColumn<?, ?> iduseremp;
    @FXML
    private TableColumn<?, ?> dateemp;
    @FXML
    private TableColumn<?, ?> dateret;
    @FXML
    private Button buttsupprimerdoc;
    @FXML
    private Button buttmodifierdoc;
    @FXML
    private ComboBox<String> combocat;
    @FXML
    private ComboBox<String> state_combo;
    @FXML
    private TextField docsrc;
    @FXML
    private TextField cat_search;
    @FXML
    private Button buttimpimg;
    @FXML
    private Button pdfbutt;
    @FXML
    private ImageView imgview;
    private static String FILE = "C:\\Users\\ASUS\\DESKTOP\\pidev\\Liste DES EMPRUNTS.pdf";


private static Font catFont = new Font(Font.FontFamily.TIMES_ROMAN, 18,
            Font.BOLD, BaseColor.BLACK);
    private static Font redFont = new Font(Font.FontFamily.TIMES_ROMAN, 12,
            Font.BOLD, BaseColor.RED);
    
     private static Font boldFont = new Font(Font.FontFamily.TIMES_ROMAN, 14,
            Font.BOLD, BaseColor.BLACK);
     
     private static Font boldFont1 = new Font(Font.FontFamily.TIMES_ROMAN, 16,
            Font.BOLD, BaseColor.BLACK);
    
     private static Font bleueFont = new Font(Font.FontFamily.TIMES_ROMAN, 14,
            Font.NORMAL, BaseColor.BLACK);
     
      private static Font magentaFont = new Font(Font.FontFamily.TIMES_ROMAN, 14,
            Font.NORMAL, BaseColor.BLACK);
     
      private static Font orangeFont = new Font(Font.FontFamily.TIMES_ROMAN, 14,
            Font.NORMAL, BaseColor.ORANGE);
      
       private static Font pinkFont = new Font(Font.FontFamily.TIMES_ROMAN, 14,
            Font.NORMAL, BaseColor.PINK);
       
         private static Font cyanFont = new Font(Font.FontFamily.TIMES_ROMAN, 14,
            Font.NORMAL, BaseColor.BLACK);
         
          private static Font greenFont = new Font(Font.FontFamily.TIMES_ROMAN, 14,
            Font.NORMAL, BaseColor.GREEN);
    private static Font subFont = new Font(Font.FontFamily.TIMES_ROMAN, 16,
            Font.BOLD);
    private static Font smallBold = new Font(Font.FontFamily.TIMES_ROMAN, 12,
            Font.BOLD);
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
            con = MyConnection.getInstance().getCnx();
       data= FXCollections.observableArrayList();
       datadoc= FXCollections.observableArrayList();
       dataemp= FXCollections.observableArrayList();
        afficheremp();
        loadDataemp();
        affichercat();
        loadDatacat();
        afficherdoc();
        loadDatadoc();
        setCellValueFromTableToTextField();
        initcatcombo();
        setCellValueFromTableToTextFielddoc();
        searchDoc();
        searchCategorie();
    remplirComboBox();

    }

    private void setCellValueFromTableToTextField(){
    tab_cat.setOnMouseClicked(new EventHandler<MouseEvent>(){
        @Override
        public void handle(MouseEvent event) {
Categorie cat=tab_cat.getItems().get(tab_cat.getSelectionModel().getSelectedIndex());
tfnom.setText(cat.getNomcategorie());

        }
});

}
    @FXML
   
    private void clearTextField() {
    tfnom.clear();

    }
     
     
     //**************************CATEGORIE*********************************************
     public void ajoutercat(ActionEvent event) throws SQLException {
     
            String nomcategorie = tfnom.getText();
            int i;
            categorieService cat = new categorieService();
            Categorie c = new Categorie(nomcategorie);
            
            i=cat.ajoutercategory(c);
              if(i==1)
        {      
            JOptionPane.showMessageDialog(null, "catégorie ajoutée ");

            affichercat();
            loadDatacat();
            clearTextField();
            
        }
          
       //}     
          
           
        
    }
     
     private void affichercat(){

             nomc.setCellValueFactory(new PropertyValueFactory <>("nomcategorie"));
    }
     
    public void deletecat(ActionEvent event) throws SQLException, AWTException, MalformedURLException {
        
        
            TableColumn.CellEditEvent edittedcell = null;
          int x=gettemp(edittedcell);
         
            int i;
            categorieService cat = new categorieService();
           
           
            
            i=cat.deletecategory(x);
              if(i==1)
        {                    
            JOptionPane.showMessageDialog(null, "Catégorie supprimée");

            affichercat();
            loadDatacat();
            afficherdoc();
            loadDatadoc();
            clearTextField();
            
        }

}   

    private void loadDatacat() {
   
       data.clear();
  
    try {
        pst =con.prepareStatement("Select * from categorie");
    rs=pst.executeQuery();
     while (rs.next()) {                
            Categorie c=new Categorie(rs.getInt("id"),rs.getString("nomcategorie"));
             data.add(c); 
           
     }
        } catch (SQLException ex) {
        Logger.getLogger(CategorieDocumentController.class.getName()).log(Level.SEVERE, null, ex);
    }

        tab_cat.setItems(data);
    }
    
       @FXML
    public int gettemp(TableColumn.CellEditEvent edittedCell) {
        Categorie test = tab_cat.getSelectionModel().getSelectedItem();
        int x= test.getId();
        return x;
    }  
    
         @FXML
    public void updatecat(ActionEvent event) throws SQLException, AWTException, MalformedURLException {
             
          
            TableColumn.CellEditEvent edittedcell = null;
            int x=gettemp(edittedcell);
            String NomC = tfnom.getText();
            int i;
            categorieService cat = new categorieService();
           
            Categorie c = new Categorie(x,NomC);
            
            i=cat.updatecategory(c);
              if(i==1)
        {
             JOptionPane.showMessageDialog(null, "Catégorie modifié ");

            affichercat();
            loadDatacat();
            clearTextField();
        
          
        }     
          
           
        
    }
    
         private void initcatcombo() {
    ObservableList datacat=FXCollections.observableArrayList();
   combocat.getSelectionModel().clearSelection();
   try {
           pst =con.prepareStatement("SELECT * from categorie");

    rs=pst.executeQuery();
     while (rs.next()) {                
             datacat.add(rs.getString(2));
     }       }
       catch (SQLException ex) {
           Logger.getLogger(categorieService.class.getName()).log(Level.SEVERE, null, ex);
       }
combocat.setItems(datacat);

}     

    
//****************************************DOCUMENT*********************************************
    
         private void adaptemp()
    {
    
    try {
        pst=con.prepareStatement("select emprunte from document where id");
    } catch (SQLException ex) {
        Logger.getLogger(CategorieDocumentController.class.getName()).log(Level.SEVERE, null, ex);
    }
    }
         
    
     private void loadDatadoc() {
     initcatcombo();

       datadoc.clear();
  loadDatacat();
    try {
        pst =con.prepareStatement("Select * from document");
      
    rs=pst.executeQuery();
    categorieService cat=new categorieService();
     while (rs.next()) { 
         if(rs.getInt("emprunte")==1)
         {Documents d=new Documents(rs.getInt("id"),rs.getString("nomdocument"),cat.getNamecatbyId(rs.getInt("categorie")), rs.getString("etatdocument"),rs.getInt("emprunte"),"emprunte");
             datadoc.add(d);
            }
         else
         {
             Documents d=new Documents(rs.getInt("id"),rs.getString("nomdocument"),cat.getNamecatbyId(rs.getInt("categorie")), rs.getString("etatdocument"),rs.getInt("emprunte"),"non emprunté");
             datadoc.add(d); 
         }
            
           
     }
        } catch (SQLException ex) {
        Logger.getLogger(CategorieDocumentController.class.getName()).log(Level.SEVERE, null, ex);
    }

        tab_doc.setItems(datadoc);
    }
   
    private void remplirComboBox() {
        String list[] = {"Good", "Medium" , "Bad"};

        for (int i = 0; i < list.length; i++) {
            state_combo.getItems().add(list[i]);
        }
    }
 
    @FXML
   public void ajouterdoc(ActionEvent event) throws SQLException, AWTException, MalformedURLException {
      
          
            int i;
           
            
   Image image1=null;
             image1= imgview.getImage();
            String photo = null;
             photo = saveToFileImageNormal(image1);
            //String idp = tf_idprod.getText();
            String  Name= tfnomdoc.getText();
            //String etat= tf_etat.getText();
               String etat= state_combo.getValue();
            String c = combocat.getSelectionModel().getSelectedItem();
            
                  documentService doc = new documentService();
            categorieService cat=new categorieService();
           Documents d =new Documents(cat.getIdatbyName(c), Name, etat,photo) ;
            
            i=doc.ajouterdocuemnt(d);
              if(i==1)
        {
                          
            JOptionPane.showMessageDialog(null, "Document Ajouté ");
          afficherdoc();
            loadDatadoc();
            clearTextFielddoc();
        }
          
        }    
          
        
    private void afficherdoc(){

             nomdoc.setCellValueFactory(new PropertyValueFactory <>("nomdocument"));
             catdoc.setCellValueFactory(new PropertyValueFactory <>("nomcategorie"));
             etatdoc.setCellValueFactory(new PropertyValueFactory <>("etatdocument")); 
             empdoc.setCellValueFactory(new PropertyValueFactory <>("emptxt")); 
             
             
        }
    
public void deletedoc(ActionEvent event) throws SQLException, AWTException, MalformedURLException {
        
        
            TableColumn.CellEditEvent edittedcell = null;
            int x=gettempdoc(edittedcell);
         
            int i;
            documentService doc = new documentService();
           
           
            
            i=doc.deletedocument(x);
              if(i==1)
        {
                                    
            JOptionPane.showMessageDialog(null, "Document Supprimé ");
            afficherdoc();
            loadDatadoc();
            afficherdoc();
            loadDatadoc();
            clearTextField();
            
        }

} 

public void searchDoc(){  
    docsrc.setOnKeyReleased(e->{ 
    if(docsrc.getText().equals("")){
        loadDatadoc();

    }
    else{

        datadoc.clear();
          String sql = "Select * from document where nomdocument LIKE '"+docsrc.getText()+"%'";
                
    try {
      
        pst=con.prepareStatement(sql);
        rs=pst.executeQuery();

        while(rs.next())
        {            
            int id=rs.getInt("id");
            String nomdocument=rs.getString("nomdocument");
            String etatdocument= rs.getString("etatdocument");
            int emprunte=rs.getInt("emprunte");
         categorieService cat =new categorieService();
         String nomcategorie=cat.getNamecatbyId(rs.getInt("categorie"));
       datadoc.add(new Documents(id,nomdocument,nomcategorie,etatdocument,emprunte));
                  tab_doc.setItems(datadoc);

                    }
    } catch (SQLException ex) {
        Logger.getLogger(CategorieDocumentController.class.getName()).log(Level.SEVERE, null, ex);
    }
    
    }
});
    
}

    
@FXML
    public int gettempdoc(TableColumn.CellEditEvent edittedCell) {
        Documents test = tab_doc.getSelectionModel().getSelectedItem();
        int x= test.getId();
        return x;
    }  
    @FXML
    public Documents gettempdoc1(TableColumn.CellEditEvent edittedCell) {
        Documents test = tab_doc.getSelectionModel().getSelectedItem();
        return test;
    }  
 //************************** --------- ********************************
    
//*************************** IMAGE DOC ********************************
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
            System.out.println(ex.getMessage());
        }
    }
    
    
     public static String saveToFileImageNormal(Image image)throws SQLException  {

        String ext = "jpg";
        File dir = new File("C:\\Users\\ASUS\\Desktop\\pidev\\imagedocmehdi");
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
     public void updatedoc(ActionEvent event) throws SQLException, AWTException, MalformedURLException {
        
   
        int i;
            
              Image image1=null;
             image1= imgview.getImage();
            String photo = null;
             photo = saveToFileImageNormal(image1);
             TableColumn.CellEditEvent edittedcell = null;
            int x=gettempdoc(edittedcell);
            String Nomdoc = tfnomdoc.getText();
            String c=combocat.getSelectionModel().getSelectedItem();
            String ett=state_combo.getSelectionModel().getSelectedItem();
   
   
            documentService doc=new documentService();
            
         categorieService cat=new categorieService();
         
           Documents d =new Documents(x,cat.getIdatbyName(c), Nomdoc, ett,photo) ;
            i=doc.updatedocument(d);
              if(i==1)
        {
            JOptionPane.showMessageDialog(null, "Document modifié ");

            afficherdoc();
            loadDatadoc();
            clearTextFielddoc();
            
        }  
        
    }
     

   

    public void searchCategorie(){
    cat_search.setOnKeyReleased(e->{
    if(cat_search.getText().equals("")){
        loadDatacat();
    }
    else{
        data.clear();
          String sql = "Select * from categorie where nomcategorie LIKE '"+cat_search.getText()+"%'";
                
    try {
      
        pst=con.prepareStatement(sql);
        rs=pst.executeQuery();

        while(rs.next())
        {            
            int id=rs.getInt("id");
            String nomcategorie=rs.getString("nomcategorie");
         categorieService cat =new categorieService();
         

       data.add(new Categorie(id,nomcategorie));

        }
        tab_cat.setItems(data);
    } catch (SQLException ex) {
        Logger.getLogger(CategorieDocumentController.class.getName()).log(Level.SEVERE, null, ex);
    }
    
    }
});
}
    private void clearTextFielddoc(){
    tfnomdoc.clear();
       state_combo.getSelectionModel().select(0);
   combocat.getSelectionModel().select(0);
    
}
    
    private void setCellValueFromTableToTextFielddoc(){
    tab_doc.setOnMouseClicked(new EventHandler<MouseEvent>(){
        @Override
        public void handle(MouseEvent event) {
Documents doc=tab_doc.getItems().get(tab_doc.getSelectionModel().getSelectedIndex());

//tf_rate.setText(idprod);



tfnomdoc.setText(doc.getNomdocument());
//combobox
//state_combo.setSelectionModel(doc.getEtatdocument());
//tf_etat.setText(doc.getEtatdocument());
             String photo;
                  TableColumn.CellEditEvent edittedcell = null;
            Documents d=gettempdoc1(edittedcell);
            
            try {
                photo = getImagebyId(d.getId());



            Image imageURL= new Image("file:///C:/Users/ASUS/Desktop/pidev/imagedocmehdi/" + photo);



        imgview.setImage(imageURL);
             } catch (SQLException ex) {
                Logger.getLogger(CategorieDocumentController.class.getName()).log(Level.SEVERE, null, ex);
            }
             
            

        }
        });
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

    

 //***************************EMPRUNTS**************************
@FXML
    
void pdf(ActionEvent event) {
  
    UserSevice us = new UserSevice();
        
 try {
           Document document = new Document();
            PdfWriter.getInstance(document, new FileOutputStream(FILE));
            document.open();
            addMetaData(document);
            addTitlePage(document);
           
            document.close();
              Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
         alert.setHeaderText("pdf");
         alert.setContentText("La liste Des emprunts est préte à imprimer ");
         alert.showAndWait();
         DialogPane dialogPane = alert.getDialogPane();
            
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    private static void addTitlePage(Document document) throws DocumentException, BadElementException, IOException {
        
           Emprunt emp = new Emprunt();
     
    

        Paragraph preface = new Paragraph();
        // We add one empty line
          preface.add(new Paragraph
        ("             *id Utilisateur:        "  +emp.getId_user() ,magentaFont));
        addEmptyLine(preface, 1);
        // Lets write a big header
        preface.add(new Paragraph("                                     Nom Document " +emp.getNomdocument(), catFont));

        addEmptyLine(preface, 1);
        
         document.add(new Paragraph("                                                                                                                 "));
         document.add(new Paragraph("                                                                                                                 "));
        // Will create: Report generated by: _name, _date
      
        addEmptyLine(preface, 1);
        preface.add(new Paragraph(
                   "                                           Date Emprunt    "+emp.getDateemprunt(),
                boldFont1));
        

        addEmptyLine(preface, 1);
        
       
           preface.add(new Paragraph
        ("             *Date retour Emprunt:        "   +emp.getDateretour() , pinkFont));
        
         
         
          
           
           
    
           
        document.add(preface);
        
        // Start a new page
        
        document.newPage();
    }

   


    private static void addEmptyLine(Paragraph paragraph, int number) {
        for (int i = 0; i < number; i++) {
            paragraph.add(new Paragraph(" "));
        }
    }
    
    
     private static void addMetaData(Document document) {
        document.addTitle("My first PDF");
        document.addSubject("Using iText");
        document.addKeywords("Java, PDF, iText");
        document.addAuthor("Lars Vogel");
        document.addCreator("Lars Vogel");
        
    }
        
  
    private void afficheremp(){

         iddocemp.setCellValueFactory(new PropertyValueFactory <>("nomdocument"));
             dateemp.setCellValueFactory(new PropertyValueFactory <>("dateemprunt")); 
             dateret.setCellValueFactory(new PropertyValueFactory <>("dateretour")); 
             
        }
        
        private void loadDataemp() {
   
       dataemp.clear();
  
    try {
        pst =con.prepareStatement("Select * from emprunt");
    documentService doc=new documentService();

    rs=pst.executeQuery();

     while (rs.next()) {                
            Emprunt e=new Emprunt(rs.getInt("id"),rs.getInt("documentid"),doc.getNamedocbyId(rs.getInt("documentid")),rs.getInt("id_user"),rs.getDate("dateemprunt"),rs.getDate("dateretour"));
          
             dataemp.add(e); 
     }
        } catch (SQLException ex) {
        Logger.getLogger(CategorieDocumentController.class.getName()).log(Level.SEVERE, null, ex);
    }

       tab_emp.setItems(dataemp);
    }
        
    public void deleteemp(ActionEvent event) throws SQLException, AWTException, MalformedURLException {
            //TableColumn.CellEditEvent edittedcell = null;
          
            Emprunt test = tab_emp.getSelectionModel().getSelectedItem();
            int x=(test.getId());
            int i;
            empruntService emp = new empruntService();

            i=emp.deleteemprunt(x);
              if(i==1)
        {
             JOptionPane.showMessageDialog(null, "emprunt supprimé");

            afficheremp();
            loadDataemp();
      
            clearTextField();
            
        }

} 
    @FXML
    public int gettempemp(TableColumn.CellEditEvent edittedCell) {
        Emprunt test = tab_emp.getSelectionModel().getSelectedItem();
        int x= test.getId();
        return x;
}

  @FXML
    void backdash(MouseEvent event) {
         try
                {
      
               Parent AnchorPane = FXMLLoader.load(getClass().getResource("UI.fxml"));
               Stage stage = new Stage();
               Scene scene = new Scene(AnchorPane);
               stage.setScene(scene);
               stage.show();
           } catch (IOException ex) {
              
           }
                   
    
                          ((Node) event.getSource()).getScene().getWindow().hide();

    }   
}
