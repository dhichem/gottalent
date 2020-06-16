/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.gui;

import com.codename1.components.SpanButton;
import com.codename1.components.SpanLabel;
import com.codename1.io.FileSystemStorage;
import com.codename1.io.Log;
import com.codename1.io.Storage;
import com.codename1.io.Util;
import com.codename1.ui.Button;
import com.codename1.ui.ButtonGroup;
import com.codename1.ui.Command;
import com.codename1.ui.ComponentGroup;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.Display;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.RadioButton;
import com.codename1.ui.TextArea;
import com.codename1.ui.TextComponent;
import com.codename1.ui.TextField;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.list.MultiList;
import com.codename1.ui.plaf.Border;
import com.codename1.ui.plaf.Style;
import com.codename1.ui.plaf.UIManager;
import com.codename1.ui.util.Resources;
import com.codename1.ui.util.UITimer;
import com.mycompany.myapp.entities.Publication;
import static com.mycompany.myapp.gui.HomeForm.listLogin;
import static com.mycompany.myapp.gui.ListTasksForm.getImageFromTheme;
import com.mycompany.myapp.services.ServiceTask;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Hashtable;

/**
 *
 * @author bhk
 */
public class AddTaskForm extends Form{
    
    
    private Resources theme;
    
    public static String fileName, imageThumb;
    SpanLabel lfile, desc1, desc2, lthumb;
    

    Container cfile=new Container(BoxLayout.x());
    Container cthumb=new Container(BoxLayout.x());
    Container logo=new Container(BoxLayout.x());

    public AddTaskForm(Form previous) {
        String categorie;
        
        setTitle("Ajouter une publication");
        setLayout(BoxLayout.y());
        theme = UIManager.initFirstTheme("/theme");
        
         Image logoi = getImageFromTheme("tgt.png").scaledWidth(Math.round(Display.getInstance().getDisplayWidth() / 3));
        
        SpanButton getFile = new SpanButton();
        Image resizedImagefile = getImageFromTheme("file.png").scaledWidth(Math.round(Display.getInstance().getDisplayWidth() / 14));
        
        SpanButton getThumb = new SpanButton();
        Image resizedImagethumb = getImageFromTheme("thumb.png").scaledWidth(Math.round(Display.getInstance().getDisplayWidth() / 14));
        
        getFile.setIcon(resizedImagefile);
        getThumb.setIcon(resizedImagethumb);
        
        ButtonGroup bg = new ButtonGroup();
        RadioButton rb1 = RadioButton.createToggle("Musique", bg);
        RadioButton rb2 = RadioButton.createToggle("Chanson", bg);
        RadioButton rb3 = RadioButton.createToggle("Divertissement", bg);
        RadioButton rb4 = RadioButton.createToggle("Technologie", bg);
        rb1.setSelected(true);
        
        
        TextComponent tftitre = new TextComponent().labelAndHint("Titre");
        FontImage.setMaterialIcon(tftitre.getField().getHintLabel(), FontImage.MATERIAL_TITLE);
      
        desc1=new SpanLabel("Choisir un fichier");
        lfile=new SpanLabel("                                                                ");
        
        desc2=new SpanLabel("Choisir une vignette");
        lthumb=new SpanLabel("                                                              ");

        Button btnValider = new Button("Publier");
        
        
        
        
        Image commentImg = getImageFromTheme("load.png").scaledWidth(Math.round(Display.getInstance().getDisplayWidth() / 6));
// Replace the "loadingIcon" with the animated gif that you want
Label loadingIcon = new Label(FontImage.createMaterial(FontImage.MATERIAL_VERIFIED_USER, "Label", 5));
loadingIcon.getAllStyles().setBgTransparency(0);

Dialog loadingAnimation = new Dialog();
loadingAnimation.setLayout(new BorderLayout(BorderLayout.CENTER_BEHAVIOR_CENTER_ABSOLUTE));
Style dlgStyle = loadingAnimation.getDialogStyle();
dlgStyle.setBorder(Border.createEmpty());
dlgStyle.setBgTransparency(0);
loadingAnimation.add(BorderLayout.CENTER, commentImg);
        
        
        
        getFile.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
           
                Form hi = new Form("Storage", new BoxLayout(BoxLayout.Y_AXIS));
                for(String file : Storage.getInstance().listEntries()) {
                     createFileEntry(hi, file);
                 }
                
                hi.getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, e-> showBack());
                
                hi.getToolbar().addCommandToRightBar("+", null, (e) -> {
        TextField tf = new TextField("", "File Name", 20, TextField.ANY);
        TextArea body = new TextArea(5, 20);
        body.setHint("File Body");
        Command ok = new Command("OK");
        Command cancel = new Command("Cancel");
        Command result = Dialog.show("File Name", BorderLayout.north(tf).add(BorderLayout.CENTER, body), ok, cancel);
        if(ok == result) {
            try(OutputStream os = Storage.getInstance().createOutputStream(tf.getText())) {
                os.write(body.getText().getBytes("UTF-8"));
                createFileEntry(hi, tf.getText());
                hi.getContentPane().animateLayout(250);
            } catch(IOException err) {
                Log.e(err);
            }
        }
    });
                
                hi.show();
                
            }
        });
        
        
        
        getThumb.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
           
                Form hi = new Form("Storage", new BoxLayout(BoxLayout.Y_AXIS));
                for(String file : Storage.getInstance().listEntries()) {
                     createFileEntry2(hi, file);
                 }
                
                hi.getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, e-> showBack());
                
                hi.getToolbar().addCommandToRightBar("+", null, (e) -> {
        TextField tf = new TextField("", "File Name", 20, TextField.ANY);
        TextArea body = new TextArea(5, 20);
        body.setHint("File Body");
        Command ok = new Command("OK");
        Command cancel = new Command("Cancel");
        Command result = Dialog.show("File Name", BorderLayout.north(tf).add(BorderLayout.CENTER, body), ok, cancel);
        if(ok == result) {
            try(OutputStream os = Storage.getInstance().createOutputStream(tf.getText())) {
                os.write(body.getText().getBytes("UTF-8"));
                createFileEntry(hi, tf.getText());
                hi.getContentPane().animateLayout(250);
            } catch(IOException err) {
                Log.e(err);
            }
        }
    });
                
                hi.show();
                
            }
        });
        
        
        if(rb1.isSelected())
            categorie="Musique";
        
        else if(rb2.isSelected())
            categorie="Chanson";
        
        else if(rb3.isSelected())
            categorie="Divertissement";
        else
            categorie="Technologie";
        
        
        btnValider.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                if ((tftitre.getText().length()==0))
                    Dialog.show("Alert", "Please fill all the fields", new Command("OK"));
                else
                {
                    try {
                        Publication p = new Publication(listLogin.get(0).getUsername(), tftitre.getText(), 
                                lfile.getText(), categorie, listLogin.get(0).getImage(), lthumb.getText());
                        if( ServiceTask.getInstance().addPublication(p)){
                            loadingAnimation.showModeless();
                            new UITimer(() -> {
                            loadingAnimation.dispose();
                            }).schedule(10000, false, loadingAnimation);
                            Dialog.show("Success","Publication enregistré",new Command("OK"));
                        }
                        else
                            Dialog.show("ERROR", "Server error", new Command("OK"));
                    } catch (NumberFormatException e) {
                        Dialog.show("ERROR", "Status must be a number", new Command("OK"));
                    }
                    
                }
                
                
            }
        });
        
        logo.add("                                          ").add(logoi);
        add(logo);
        add(tftitre);
        add("");
        cfile.add(desc1).add(lfile).add(getFile);
        add(cfile);
        add("");
        add(ComponentGroup.enclose( rb1, rb2, rb3, rb4));
        //add(ccategorie);
        cthumb.add(desc2).add(lthumb).add(getThumb);
        add("");
        add(cthumb);
        add("");
        addAll( btnValider);
        getToolbar().setUIID("SideCommand");
        getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, e-> previous.showBack());
        
        
        
        
        
                
    }
    
    
    
    private void createFileEntry(Form hi, String file) {
   Label fileField = new Label(file);
   Button delete = new Button();
   Button view = new Button();
   FontImage.setMaterialIcon(delete, FontImage.MATERIAL_DELETE);
   FontImage.setMaterialIcon(view, FontImage.MATERIAL_OPEN_WITH);
   Container content = BorderLayout.center(fileField);
   int size = Storage.getInstance().entrySize(file);
   content.add(BorderLayout.EAST, BoxLayout.encloseX(new Label(size + "bytes"), delete, view));            
   delete.addActionListener((e) -> {
       Storage.getInstance().deleteStorageFile(file);
       content.setY(hi.getWidth());
       hi.getContentPane().animateUnlayoutAndWait(150, 255);
       hi.removeComponent(content);
       hi.getContentPane().animateLayout(150);
   });         
   view.addActionListener((e) -> {
       try(InputStream is = Storage.getInstance().createInputStream(file)) {
           String s = Util.readToString(is, "UTF-8");
           //Dialog.show(file, s, "OK", null);
           
           showBack();
           lfile.setText(file);
       } catch(IOException err) {
           Log.e(err);
       }
   });
   hi.add(content);
} 
    
    
    
    private void createFileEntry2(Form hi, String file) {
   Label fileField = new Label(file);
   Button delete = new Button();
   Button view = new Button();
   FontImage.setMaterialIcon(delete, FontImage.MATERIAL_DELETE);
   FontImage.setMaterialIcon(view, FontImage.MATERIAL_OPEN_WITH);
   Container content = BorderLayout.center(fileField);
   int size = Storage.getInstance().entrySize(file);
   content.add(BorderLayout.EAST, BoxLayout.encloseX(new Label(size + "bytes"), delete, view));            
   delete.addActionListener((e) -> {
       Storage.getInstance().deleteStorageFile(file);
       content.setY(hi.getWidth());
       hi.getContentPane().animateUnlayoutAndWait(150, 255);
       hi.removeComponent(content);
       hi.getContentPane().animateLayout(150);
   });         
   view.addActionListener((e) -> {
       try(InputStream is = Storage.getInstance().createInputStream(file)) {
           String s = Util.readToString(is, "UTF-8");
           //Dialog.show(file, s, "OK", null);
           
           showBack();
           lthumb.setText(file);
       } catch(IOException err) {
           Log.e(err);
       }
   });
   hi.add(content);
} 
    
    
    
    
}
