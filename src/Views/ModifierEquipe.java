/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Views;

/**
 *
 * @author aroua
 */
    import com.codename1.components.Switch;
import com.codename1.io.FileSystemStorage;
import com.codename1.ui.Button;
import com.codename1.ui.Command;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.TextField;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.util.ImageIO;
import com.codename1.ui.util.Resources;
import Entities.Equipe;
import Controller.EquipeController;
import java.io.IOException;
import java.io.OutputStream;

/**
 *
 * @author bhk
 */
public class ModifierEquipe extends Form{
    Form current;

    public ModifierEquipe(Form previous, Resources res, Equipe m) {
        int id = m.getId();
        setTitle("Modifier equipe");
        setLayout(BoxLayout.y());
        
        TextField tfnom = new TextField(m.getNom(),"nom");
        TextField tfclassement= new TextField(m.getClassement()+"", "nb points");
        Container cnt=new Container(BoxLayout.x());
        Label lbCategorie=new Label();
//        Label lb=new Label("Vegan :");
//        Switch sw=new Switch();
//        if("Vegan".equals(m.getCategorie())){
//            sw.setOn();
//        }
//        cnt.addAll(lb,sw);
        Button btnValider = new Button("Modifier equipe");
//        Button btCapture = new Button("Choisir une image");
//        Label lbImage = new Label();
//        btCapture.addActionListener(l->{
//            if (FileChooser.isAvailable()) {
//                FileChooser.setOpenFilesInPlace(true);
//                FileChooser.showOpenDialog(".jpg, .jpeg, png/plain", e2-> {
//                String file = (String)e2.getSource();
//                if (file == null) {
//                    add("No file was selected");
//                    revalidate();
//                } else {
//                   int index = file.lastIndexOf("/");
//                   image = file.substring(index + 1);
//                   lbImage.setText(image);
//                   //add("Selected file "+file);
//                   Image previewImg;
//                   try{
//                      previewImg = Image.createImage(file).scaledHeight(500);
//                      add(previewImg);
//                      String imageFile = FileSystemStorage.getInstance().getAppHomePath() + "photo.png";
//                      try(OutputStream os = FileSystemStorage.getInstance().openOutputStream(imageFile)){
//                          System.out.println(imageFile);
//                          ImageIO.getImageIO().save(previewImg, os, ImageIO.FORMAT_PNG, 1);
//                      } catch(IOException err){
//                          
//                      }
//                   } catch (IOException ex) {
//                        
//                    }
//                }
//                //System.out.println(image);
//                revalidate();
//    });
//}});
        
        
        btnValider.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                if ((tfnom.getText().length()==0)||(tfclassement.getText().length()==0))
                    Dialog.show("Alert", "Veuillez remplir tous les champs", new Command("OK"));
                else
                {
                    try {
//                        if(sw.isValue())
//                            lbCategorie.setText("Vegan");
//                        else{
//                            lbCategorie.setText("Normal");
//                        }
                        Equipe m = new Equipe(id, tfnom.getText(), (int) Float.parseFloat(tfclassement.getText()));
                        if( EquipeController.getInstance().updateEquipe(m))
                        {
                           Dialog.show("Success","Menu modifié avec succès!",new Command("OK"));
                           new ListEquipe(previous, res).show();
                        }else
                            Dialog.show("ERROR", "Server error", new Command("OK"));
                    } catch (NumberFormatException e) {
                        Dialog.show("ERROR", "Prix doit être un nombre", new Command("OK"));
                    }
                    
                }
                
                
            }
        });
        
        addAll(tfnom,tfclassement,btnValider);
        addAll(cnt);
        getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, e-> previous.showBack());
                
    }
}
