/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.gui;

import com.codename1.components.ScaleImageLabel;
import com.codename1.components.SpanButton;
import com.codename1.components.SpanLabel;
import com.codename1.ui.Button;
import static com.codename1.ui.CN.CENTER;
import static com.codename1.ui.CN.CENTER_BEHAVIOR_CENTER_ABSOLUTE;
import static com.codename1.ui.CN.callSerially;
import com.codename1.ui.Command;
import com.codename1.ui.Component;
import com.codename1.ui.Container;
import com.codename1.ui.Display;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.TextArea;
import com.codename1.ui.TextComponent;
import com.codename1.ui.TextComponentPassword;
import com.codename1.ui.TextField;
import com.codename1.ui.animations.CommonTransitions;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.LayeredLayout;
import com.codename1.ui.plaf.Style;
import com.codename1.ui.plaf.UIManager;
import com.codename1.ui.util.Resources;
import com.mycompany.myapp.entities.Login;
import com.mycompany.myapp.entities.Publication;
import static com.mycompany.myapp.gui.ListTasksForm.getImageFromTheme;
import com.mycompany.myapp.services.ServiceTask;
import java.util.ArrayList;

/**
 *
 * @author bhk
 */
public class HomeForm extends Form{
Form current;
Label ltitre,lvideo;
Container c4=new Container(BoxLayout.y());
Container cL=new Container(BoxLayout.x());

private Resources res;

public static ArrayList<Login> listLogin;



    public HomeForm() {
        current=this;
        setTitle("Home");
        setLayout(BoxLayout.y());
        SpanButton loginB = new SpanButton();
       
        res = UIManager.initFirstTheme("/theme");
                   
        Image badge = getImageFromTheme("tunisian.png").scaledWidth(Math.round(Display.getInstance().getDisplayWidth() / 1));
        Image login = getImageFromTheme("login.png").scaledWidth(Math.round(Display.getInstance().getDisplayWidth() / 2));

        
        TextComponent username = new TextComponent().labelAndHint("login");
        FontImage.setMaterialIcon(username.getField().getHintLabel(), FontImage.MATERIAL_LOGOUT);
        TextComponent mdp = new TextComponentPassword().labelAndHint("mdp");
        FontImage.setMaterialIcon(mdp.getField().getHintLabel(), FontImage.MATERIAL_ENHANCED_ENCRYPTION);
        
        loginB.setIcon(login);
       
       
        add(" ");
        add(badge);
        add(" ");
     
        addAll(username, mdp);
        add(" ");
        cL.add("                          ").add(loginB);
        add(cL);
        
        
        loginB.addActionListener(new ActionListener() {
               @Override
               public void actionPerformed(ActionEvent evt) {
                   
        
        listLogin=new ArrayList<>();
                   
        listLogin=ServiceTask.getInstance().getLogin(username.getText(), mdp.getText());
        
            
        
        new ListTasksForm(current).show();
        
        }

               
               
        });
  

        getToolbar().setUIID("SideCommand");
        
        
    }
    
    
    
    
    
    private void showSplashAnimation() {
    
        
        Image resizedImagetitle2 = getImageFromTheme("tgt.png").scaledWidth(Math.round(Display.getInstance().getDisplayWidth() / 4));

        
        Form splash = new Form(new LayeredLayout());
        
        
        Image mask = res.getImage("card-full.png");
        splash.add(mask);
        
        
        
        splash.show();
      
    }
    
    
    
    
    
    
    
    
    
    
}
