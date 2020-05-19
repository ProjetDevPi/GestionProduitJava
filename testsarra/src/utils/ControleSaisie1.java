/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utils;

import java.sql.Date;
import java.util.Calendar;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

/**
 *
 * @author ASUS
 */
public class ControleSaisie1 {

    public ControleSaisie1() {
    }
    public boolean controleTextFieldVide(TextField textField, String msg, Label errorLabel) {
        String chaine = textField.getText().trim();
        if (chaine.length() == 0) {
            errorLabel.setText(msg);
            textField.clear();
            return true;
        }
        effacerControleSaisie(errorLabel);
        return false;
    }
    
       public void effacerControleSaisie(Label textField) {
        textField.setText("");
    }
          public boolean controleTextFieldNonNumerique(TextField textField, String msg, Label errorLabel) {
              if (!textField.getText().matches(".*[a-zA-Z].*")) {
                  errorLabel.setText(msg);

                  return true;
              }
              return false;
    }
           public boolean controleTextFieldChiffres(TextField textField, String msg, Label errorLabel) {
               String chaine = textField.getText();
               char[] tab = chaine.toCharArray();

               boolean estUnNombre = true;
               for (int i = 0; i < tab.length; i++) {
                   if (!Character.isDigit(tab[i])) {
                       estUnNombre = false;
                   }
               }
               if (!estUnNombre) {
                   errorLabel.setText(msg);
                   return true;
               }
               effacerControleSaisie(errorLabel);
               return false;
    }
               public boolean controleTextFieldNumerique(TextField textField, String msg, Label errorLabel) {
                   if (!textField.getText().matches(".*[a-zA-Z].*")) {
                       errorLabel.setText(msg);

                       return true;
                   }
                   effacerControleSaisie(errorLabel);

                   return false;
    }
           
              public boolean controleNumTelLongueur(TextField textField, String msg, Label errorLabel) {
                  if (textField.getText().length() != 8) {
                      errorLabel.setText(msg);
                      return true;
                  } else if (textField.getText().substring(0, 1) != "31" && textField.getText().charAt(0) != '2' && textField.getText().charAt(0) != '5' && textField.getText().charAt(0) != '9' && textField.getText().charAt(0) != '7') {
                      errorLabel.setText("N° Tel. incorrecte                 ");
                      return true;
                  }
                  effacerControleSaisie(errorLabel);

                  return false;
    }
               public boolean controleMailFormat(TextField textField, String msg, Label errorLabel) {
                   String chaine = textField.getText();
                   if (chaine.length() != 0) {
                       if (chaine.charAt(chaine.length() - 1) == '.') {
                           errorLabel.setText(msg);
                           return true;
                       } else {

                           int firstIndexA = chaine.indexOf("@");
                           int lastIndexA = chaine.lastIndexOf("@");
                           int lastIndexPt = chaine.lastIndexOf(".");
                           if (firstIndexA < 3 || firstIndexA != lastIndexA || firstIndexA > lastIndexPt || lastIndexPt - firstIndexA < 4 || chaine.substring(lastIndexPt + 1, chaine.length() - 1).length() > 3 || chaine.substring(lastIndexPt + 1, chaine.length()).length() < 2) {
                               errorLabel.setText(msg);
                               return true;
                           }
                       }
                   }
                   effacerControleSaisie(errorLabel);
                   return false;
    }
               public boolean controleComboBox(ComboBox<String> combo, String msg, Label errorLabel) {
                   if (combo.getValue() == null) {
                       errorLabel.setText(msg);
                       return true;
                   }
                   effacerControleSaisie(errorLabel);
                   return false;
    }
               
              /* public boolean controleDate(DatePicker datep,String msg, Label errorlabel)
               {
                   //Date debut = new Date(datep.getValue().getYear() - 1900, datep.getValue().getMonthValue() - 1, datep.getValue().getDayOfMonth());
                   System.out.println(datep.getValue());
                   Date today = new Date(Calendar.getInstance().getTime().getYear()+1900, Calendar.getInstance().getTime().getMonth(), Calendar.getInstance().getTime().getDay());
                  System.out.println(Calendar.getInstance().getTime().getYear()+1900);
                   if (debut.compareTo(today) == -1) {
                       errorlabel.setText(msg);
                       return true;
                   }
            
                  // effacerControleSaisie(errorlabel);
                   return false;
               }*/
               public boolean validateDatePicker(DatePicker d)
    {
          if(d.getEditor().getText().isEmpty())
         {
            Alert alert =new Alert(Alert.AlertType.ERROR);
            alert.setTitle("validate Date");
            alert.setHeaderText(null);
            alert.setContentText("Please chosse a birth date");
            alert.showAndWait();
            return false;
         }
           return true;
        }
    
}
