/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.gui;

import com.codename1.components.FloatingActionButton;
import com.codename1.components.ImageViewer;
import com.codename1.components.OnOffSwitch;
import com.codename1.components.SpanButton;
import com.codename1.components.SpanLabel;
import com.codename1.components.ToastBar;
import com.codename1.io.Log;
import com.codename1.l10n.SimpleDateFormat;
import com.codename1.ui.BrowserComponent;
import com.codename1.ui.Button;
import com.codename1.ui.CN;
import static com.codename1.ui.CN.CENTER;
import com.codename1.ui.Command;
import com.codename1.ui.Component;
import static com.codename1.ui.Component.TOP;
import static com.codename1.ui.ComponentSelector.$;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.Display;
import com.codename1.ui.Font;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Graphics;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.TextArea;
import com.codename1.ui.TextComponent;
import com.codename1.ui.TextField;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.layouts.TextModeLayout;
import com.codename1.ui.plaf.RoundBorder;
import com.codename1.ui.plaf.Style;
import com.codename1.ui.plaf.UIManager;
import com.codename1.ui.util.Resources;
import com.mycompany.myapp.services.ServiceTask;
import java.util.ArrayList;
import com.mycompany.myapp.entities.Publication;
import com.mycompany.myapp.entities.Commentaire;
import com.mycompany.myapp.entities.Task;
import static com.mycompany.myapp.gui.HomeForm.listLogin;
import java.io.IOException;
import java.util.Date;

/**
 *
 * @author bhk
 */
public class ListTasksForm extends Form{
    
    SpanLabel ltitre, lvideo, lpseudo, ldatePublication, lcategorie, lnbreVue, limage, lthumbnail, limageComm, 
            lpseudoComm, ldateCommentaire, lidPublication, lidC;
    Form current;
    private Resources theme;
    ImageViewer categor = new ImageViewer();
    final String ligne="ligne4.png";
    
    Style s = UIManager.getInstance().getComponentStyle("Title");
    
    int trigger=0;
    Date date = new Date(); // This object contains the current date value
    
    Image resizedImageSupp = getImageFromTheme("supp.png").scaledWidth(Math.round(Display.getInstance().getDisplayWidth() / 19));
    Image resizedImageModif = getImageFromTheme("modif.png").scaledWidth(Math.round(Display.getInstance().getDisplayWidth() / 19));
    Image resizedImagetitle = getImageFromTheme("tgt.png").scaledWidth(Math.round(Display.getInstance().getDisplayWidth() / 3));

    public static ArrayList<Publication> listSearch;
    
    public ListTasksForm(Form previous) {
        
        setLayout(BoxLayout.y());
        current=this;
        theme = UIManager.initFirstTheme("/theme");
        
        categor = new ImageViewer(theme.getImage("categ1.png"));
        
        ArrayList<Publication> listPublication;
        
                    
        listPublication=new ArrayList<>();
        listSearch=new ArrayList<>();
        System.out.println(HomeForm.listLogin.get(0).getImage());
     
        
        SpanLabel sp = new SpanLabel();
        listPublication=ServiceTask.getInstance().getAllTasks();
     
        
        for(Publication t:listPublication){
        
            Container c4=new Container(BoxLayout.y());
            Container c5=new Container(new BorderLayout());
            Container c6=new Container(new BorderLayout());
            Container categ=new Container(BoxLayout.x());

            lvideo=new SpanLabel(t.getVideo());
            ltitre=new SpanLabel(t.getTitre());
            limage=new SpanLabel(t.getImage());
            lpseudo=new SpanLabel(t.getPseudo());
            lnbreVue=new SpanLabel(Integer.toString(t.getNbreVue()).concat(" Views"));
            ldatePublication=new SpanLabel("Publiée le ".concat(t.getDatePublication()));
            lcategorie=new SpanLabel(t.getCategorie());
            lthumbnail=new SpanLabel(t.getThumbnail());
            
            
             ltitre.getTextAllStyles().setFont(Font.createSystemFont(Font.FACE_SYSTEM, Font.STYLE_BOLD, Font.SIZE_LARGE));
            
            
            
            
            
            FontImage icon = FontImage.createMaterial(FontImage.MATERIAL_VIDEO_LIBRARY, s);
            
            Image resizedImage = getImageFromTheme(t.getThumbnail()).scaledWidth(Math.round(Display.getInstance().getDisplayWidth() / 1)); //change value as necessary
            Image resizedImage2 = getImageFromTheme(t.getImage()).scaledWidth(Math.round(Display.getInstance().getDisplayWidth() / 8));
            Image resizedImageCat = getImageFromTheme("tag1.png").scaledWidth(Math.round(Display.getInstance().getDisplayWidth() / 13));
            
            
            c4.add(resizedImage);
            
            c4.add(ltitre);
            
            
            
            c5.add(BorderLayout.WEST, roundImage(resizedImage2));
            c5.add(BorderLayout.CENTER, lpseudo);
            c4.add(c5);
            
            c6.add(BorderLayout.WEST, lnbreVue);
            c6.add(BorderLayout.CENTER, ldatePublication);
            c4.add(c6);
            categ.add(resizedImageCat).add(lcategorie);
            c4.add(categ);
            
            Button btnOk2 = new Button();
            add(btnOk2);
            
            
           
            
            
             /**************************     consulter  publication      ******************************/
            
            
             
        btnOk2.addActionListener((v3) -> {
            
            
            ServiceTask.getInstance().addVue(t.getIdPublication());
            
               Form f2=new Form( BoxLayout.y());
               
               Container c7=new Container(BoxLayout.x());
               Container c8=new Container(BoxLayout.x());
               Container tag=new Container(BoxLayout.x());
               
               BrowserComponent browser = new BrowserComponent();
               browser.setUIID("GUI 1");
               browser.setURL("http://localhost/tunisiangottalent/web/app_dev.php/Publication/mobileVideoId/"+t.getIdPublication());
               
               Container vid = new Container(BoxLayout.y());
               vid.add(browser);
               
               
               f2.add(vid);
               
               ltitre=new SpanLabel(t.getTitre());
               lnbreVue=new SpanLabel(Integer.toString(t.getNbreVue()).concat(" Views"));
               ldatePublication=new SpanLabel("Publiée le ".concat(t.getDatePublication()));
               lcategorie=new SpanLabel(t.getCategorie());
               lpseudo=new SpanLabel(t.getPseudo());
               
               ltitre.getTextAllStyles().setFont(Font.createSystemFont(Font.FACE_SYSTEM, Font.STYLE_BOLD, Font.SIZE_LARGE));
               
               f2.add(ltitre);
               c7.add(lnbreVue).add(ldatePublication);
               f2.add(c7);
               
               Image resizedImageTag = getImageFromTheme("tag2.png").scaledWidth(Math.round(Display.getInstance().getDisplayWidth() / 13));
               
               tag.add(resizedImageTag).add(lcategorie);
               f2.add(tag);
               
               Image imgLineC = getImageFromTheme(ligne).scaledWidth(Math.round(Display.getInstance().getDisplayWidth() / 1));
               f2.add(imgLineC);
               
               Image resizedImage3 = getImageFromTheme(t.getImage()).scaledWidth(Math.round(Display.getInstance().getDisplayWidth() / 8));
               c8.add(roundImage(resizedImage3));
               c8.add(lpseudo);
               f2.add(c8);
               
               
              Image imgLineC2 = getImageFromTheme(ligne).scaledWidth(Math.round(Display.getInstance().getDisplayWidth() / 1));
               f2.add(imgLineC2);
               
               
               ArrayList<Commentaire> listCommentaire;
               listCommentaire=new ArrayList<>();
               listCommentaire=ServiceTask.getInstance().getComm(t.getIdPublication());
               
                SpanLabel commentaires=new SpanLabel("Commentaires : " + listCommentaire.size()); 
                f2.add(commentaires);
                
                Container text=new Container(BoxLayout.x());
                Image resizedImageComment = getImageFromTheme(listLogin.get(0).getImage()).scaledWidth(Math.round(Display.getInstance().getDisplayWidth() / 13));
                Image resizedImageArrow = getImageFromTheme("send.png").scaledWidth(Math.round(Display.getInstance().getDisplayWidth() / 14));
                
                TextField commentaireAjout = new TextField("", "Ajouter un commentaire...", 25, TextArea.ANY);
                
                Container arrow=new Container(BoxLayout.xRight());
                
                SpanButton addComment = new SpanButton();
                addComment.setIcon(resizedImageArrow);
                arrow.add(addComment);
                
                text.add(roundImage(resizedImageComment)).add(commentaireAjout);
                f2.add(text).add(arrow);
                
                
                
                
                
                /**************************     ajouter  commentaires      ******************************/
                
                
                
                
                addComment.addActionListener(new ActionListener() {
                    SpanLabel lcontenu;
            @Override
            public void actionPerformed(ActionEvent evt) {
                if ((commentaireAjout.getText().length()==0))
                    Dialog.show("Alert", "Champ vide!", new Command("OK"));
                else
                {
                    try {
                        Commentaire c = new Commentaire(listLogin.get(0).getUsername(),
                                commentaireAjout.getText(), listLogin.get(0).getImage(), t.getIdPublication());
                        System.out.println(c);
                        if( ServiceTask.getInstance().addCommentaire(c)){
                            
                         
                            Container c9=new Container(new FlowLayout(TOP));
            Container c10=new Container(BoxLayout.x());
            Container c10c1=new Container(BoxLayout.y());
            Container c10c2=new Container(BoxLayout.x());
                            
                             SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm");
                   
                   Image currentCommentImg = getImageFromTheme(listLogin.get(0).getImage()).scaledWidth(Math.round(Display.getInstance().getDisplayWidth() / 8));
         
            lpseudoComm=new SpanLabel(listLogin.get(0).getUsername());
            lcontenu=new SpanLabel(commentaireAjout.getText());
            ldateCommentaire=new SpanLabel("●  le  ".concat(formatter.format(date)));
            
            c9.add(currentCommentImg);
            c10c2.add(lpseudoComm).add(ldateCommentaire);
            c10c1.add(c10c2).add(lcontenu);
            c9.add(c10c1);
            
      
            
            f2.add(c9);
Image imgLineC3 = getImageFromTheme(ligne).scaledWidth(Math.round(Display.getInstance().getDisplayWidth() / 1));
            
               f2.add(imgLineC3);
               
            ToastBar.showMessage("Commentaire ajouter", FontImage.MATERIAL_CHECK_CIRCLE);
               f2.show();
                            
                        }
                        else
                            Dialog.show("ERROR", "Server error", new Command("OK"));
                    } catch (NumberFormatException e) {
                        Dialog.show("ERROR", "Status must be a number", new Command("OK"));
                    }
                    
                }
                
                
            }
        });
              
              
    
             
                
                
                /**************************     consulter  commentaires      ******************************/
                
                
               
               for(Commentaire c:listCommentaire){
        SpanLabel lcontenu;
            Container c9=new Container(new FlowLayout(TOP));
            Container c10=new Container(BoxLayout.x());
            Container c10c1=new Container(BoxLayout.y());
            Container c10c2=new Container(BoxLayout.x());
            Container c10c3=new Container(BoxLayout.yLast());
            
            Image commentImg = getImageFromTheme(c.getImage()).scaledWidth(Math.round(Display.getInstance().getDisplayWidth() / 8));

            //lidC=new SpanLabel(c.getIdPublication());
            lpseudoComm=new SpanLabel(c.getPseudo());
            lcontenu=new SpanLabel(c.getContenu());
            ldateCommentaire=new SpanLabel("●  le  ".concat(c.getDateCommentaire()));
            
            
            lcontenu.setUIID(Integer.toString(c.getIdCommentaire()));
          
            
            c9.add(roundImage(commentImg));
            c10c2.add(lpseudoComm).add(ldateCommentaire).add("              ");
            
            
            /**************************     supprimer / modifier  commentaires      ******************************/
            
            if(c.getPseudo().compareTo(listLogin.get(0).getUsername())==0){
                SpanButton suppComment = new SpanButton();
                SpanButton modifComment = new SpanButton();
                
                suppComment.setIcon(resizedImageSupp);
                modifComment.setIcon(resizedImageModif);
                
                c10c3.addAll(suppComment,modifComment);
                
                suppComment.addActionListener((e) -> {
 
                    if (Dialog.show("Supprimer", "Confirmer la suppression?", "OK", "Annuler")) {
                        if( ServiceTask.getInstance().deleteCommentaire(c.getIdCommentaire())){
                            ToastBar.showMessage("Commentaire supprimer", FontImage.MATERIAL_CHECK_CIRCLE);
                            c9.removeAll();
                            
                            
                        }
                    }
                });
                
                modifComment.addActionListener((e) -> {
 
                    System.out.println("uiid  "+lcontenu.getUIID());
                    
                  TextArea body = new TextArea(5, 20);
                  body.setText(c.getContenu());
                  int x=c.getIdCommentaire();
                  Command ok = new Command("Valider");
                  Command cancel = new Command("Annuler");
                  Command result = Dialog.show("Modifier Commentaire", BorderLayout.north(body), ok, cancel);
                    System.out.println(c.getIdCommentaire());
                    System.out.println("x  : "+c);
                  if(ok == result) {
                      if ((body.getText().length()==0))
                        Dialog.show("Alert", "Champs vide!", new Command("OK"));
                      else
                          if( ServiceTask.getInstance().modifyCommentaire(c.getIdCommentaire(), body.getText())){
                              ToastBar.showMessage("Commentaire modifier", FontImage.MATERIAL_CHECK_CIRCLE);
                             // findCurrentlyEditingComponent(lcontenu.setText(body.getText()));
                              lcontenu.setText(body.getText());
                             
                          }
                        
                   }
        
                });
                
            }
            
            /********************************************************/
            
            c10c1.add(c10c2).add(lcontenu);
            c10c1.setUIID(Integer.toString(c.getIdCommentaire()));
            c9.add(c10c1);
            
            c9.add( c10c3);
            
            f2.add(c9);
            

            Image imgLineC3 = getImageFromTheme(ligne).scaledWidth(Math.round(Display.getInstance().getDisplayWidth() / 1));
               f2.add(imgLineC3);
               
               
               
            
               }
               
               
               
               
               f2.getToolbar().setUIID("SideCommand");
         Image badge = getImageFromTheme("tunisian.png").scaledWidth(Math.round(Display.getInstance().getDisplayWidth() / 2));
         Container topBar = BorderLayout.centerAbsolute(new Label(badge));
         f2.getToolbar().addComponentToSideMenu(topBar);
        FontImage icon2 = FontImage.createMaterial(FontImage.MATERIAL_CONTACTS, s);
        FontImage iconP = FontImage.createMaterial(FontImage.MATERIAL_VIDEO_LABEL, s);
        FontImage iconHome = FontImage.createMaterial(FontImage.MATERIAL_HOME, s);
        FontImage iconChaine = FontImage.createMaterial(FontImage.MATERIAL_TV, s);
        f2.getToolbar().addCommandToRightBar(listLogin.get(0).getUsername(), icon2, (e) -> Log.p("Clicked"));
       
        
        
        f2.getToolbar().addCommandToSideMenu("Acceuil", iconHome, (e) -> Log.p("Clicked"));
        f2.getToolbar().addCommandToSideMenu("Chaine", iconChaine, (e) -> new ChaineForm(current).show());
        f2.getToolbar().addCommandToSideMenu("Publier Video", iconP, (e) -> new AddTaskForm(current).show());
        f2.getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, e-> showBack());

               
                f2.show();
            
            
            } );
            
            add(c4);
        
       
            
                 c4.setLeadComponent(btnOk2);
                
            
            
            
            
        }
        



        getToolbar().setUIID("SideCommand");
         Image badge = getImageFromTheme("tunisian.png").scaledWidth(Math.round(Display.getInstance().getDisplayWidth() / 2));
         Container topBar = BorderLayout.centerAbsolute(new Label(badge));
         getToolbar().addComponentToSideMenu(topBar);
        FontImage icon = FontImage.createMaterial(FontImage.MATERIAL_CONTACTS, s);
        FontImage iconP = FontImage.createMaterial(FontImage.MATERIAL_VIDEO_LABEL, s);
        FontImage iconHome = FontImage.createMaterial(FontImage.MATERIAL_HOME, s);
        FontImage iconChaine = FontImage.createMaterial(FontImage.MATERIAL_TV, s);
        FontImage iconS = FontImage.createMaterial(FontImage.MATERIAL_SEARCH, s);
        getToolbar().addCommandToRightBar(listLogin.get(0).getUsername(), icon, (e) -> Log.p("Clicked"));
        
        
        
        getToolbar().addCommandToRightBar(null, iconS, (e) -> {
 
                    
                  TextField body = new TextField();

  
                  Command ok = new Command("Valider");
                  Command cancel = new Command("Annuler");
                  Command result = Dialog.show("Recherche", BorderLayout.north(body), ok, cancel);
   
                  if(ok == result) {
                      if ((body.getText().length()==0))
                        Dialog.show("Alert", "Champs vide!", new Command("OK"));
                      else
                      {
                          listSearch=ServiceTask.getInstance().getAllSearch(body.getText());
                          new SearchForm(current).show();
                      }
     
                             
                          
                        
                   }
        
                });
        
        
        
        getToolbar().addCommandToSideMenu("Acceuil", iconHome, (e) -> Log.p("Clicked"));
        getToolbar().addCommandToSideMenu("Chaine", iconChaine, (e) -> new ChaineForm(current).show());
        getToolbar().addCommandToSideMenu("Publier Video", iconP, (e) -> new AddTaskForm(current).show());
    }
    
    
    public static Image getImageFromTheme(String name) {
    try {
        Resources resFile = Resources.openLayered("/theme");
        Image image = resFile.getImage(name);
        return image;
    } catch (IOException ioe) {
        //Log.p("Image " + name + " not found: " + ioe);
    }
    return null;
}
    
    
    public static Label roundImage(Image img) {
     Label labelC1 = new Label(img);
       
        int wC = img.getWidth();
        int hC = img.getHeight();
        
        Image maskImageC = Image.createImage(wC, hC);
        Graphics gC = maskImageC.getGraphics();
        gC.setAntiAliased(true);
        gC.setColor(0x000000);
        gC.fillRect(0, 0, wC, hC);
        gC.setColor(0xffffff);
        gC.fillArc(0, 0, wC, hC, 0, 360);
        Label labelC2 = new Label(maskImageC);
        
        
        Object maskC = maskImageC.createMask();
        
        Image maskedImageC = img.applyMask(maskC);
        Label labelC3 = new Label(maskedImageC);
    
        return labelC3;
    }
}
