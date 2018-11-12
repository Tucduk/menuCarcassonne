package sample;

import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

import java.util.List;


public class menu extends Application {

    public static void main(String[] args) {
        Application.launch(menu.class,args);
    }

    BorderPane borderPane = new BorderPane();
    Group root = new Group();
    Scene scene = new Scene(root, 500, 500);
    Model mon_model = new Model();
    @Override
    public void start(Stage primaryStage) throws Exception{
        primaryStage.setTitle("Carcass'ON");
        switchDisplay();
        root.getChildren().add(borderPane);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private void switchDisplay(){
        switch (mon_model.getDisplay()){
            case 1:
                MenuJeu();
                break;
            case 2:
                MenuJeu();
                break;
            case 3:
                /*CouleurJoueurs();*/
                CouleurJoueursBis(mon_model.getJoueurEnCoursDeCreation());
                break;
            default:
                MenuPrincipal();
                break;
        }
        borderPane.setMinSize(500,500);
    }

    private void MenuJeu() {

        VBox vBox = new VBox();
        HBox hBox = new HBox();
        hBox.getChildren().add(mon_model.getL_NombreJoueurs());
        mon_model.getComboBox().setValue(2);
        hBox.getChildren().add(mon_model.getComboBox());
        vBox.getChildren().add(hBox);
        vBox.getChildren().add(mon_model.getB_suivant());
        hBox.setAlignment(Pos.CENTER);
        vBox.setAlignment(Pos.CENTER);



        borderPane.setCenter(vBox);

        mon_model.getB_suivant().setOnMousePressed(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                mon_model.setNbJoueurs((int)mon_model.getComboBox().getValue());
                System.out.println(mon_model.getNbJoueurs());
                mon_model.setDisplay(3);
                switchDisplay();

            }
        });

    }

    private void CouleurJoueursBis(int numJoueur){
        VBox vBox = new VBox();
        HBox nom = new HBox();
        HBox couleurs = new HBox();

        Label noms = new Label("Nom du joueur: ");
        TextField labelnom = new TextField();

        Rectangle vert = new Rectangle(25,25,Color.GREEN);
        Rectangle jaune = new Rectangle(25,25,Color.YELLOW);
        Rectangle rouge = new Rectangle(25,25,Color.RED);
        Rectangle bleu = new Rectangle(25,25,Color.BLUE);
        Rectangle noir = new Rectangle(25,25,Color.BLACK);
        Rectangle gris = new Rectangle(25,25,Color.GREY);
        Rectangle violet = new Rectangle(25,25,Color.VIOLET);
        Rectangle chartreuse = new Rectangle(25,25,Color.CHARTREUSE);
        Label couleursDuJoueurs = new Label("Couleurs du joueur " + mon_model.getJoueurEnCoursDeCreation() + ":" );

        nom.getChildren().add(noms);
        nom.getChildren().add(labelnom);
        nom.setAlignment(Pos.CENTER);

        couleurs.getChildren().add(vert);
        couleurs.getChildren().add(jaune);
        couleurs.getChildren().add(rouge);
        couleurs.getChildren().add(bleu);
        couleurs.getChildren().add(noir);
        couleurs.getChildren().add(gris);
        couleurs.getChildren().add(violet);
        couleurs.getChildren().add(chartreuse);
        couleurs.setAlignment(Pos.CENTER);

        vBox.getChildren().add(nom);
        vBox.getChildren().add(couleursDuJoueurs);

        couleurs.setSpacing(10);
        vBox.getChildren().add(couleurs);
        vBox.getChildren().add(mon_model.getB_suivant());
        vBox.setAlignment(Pos.CENTER);
        vBox.setSpacing(10);

        borderPane.setCenter(vBox);
        vert.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                vert.setOpacity(20);

            }
        });





    }

    private void CouleurJoueurs(){

        VBox vBox = new VBox();
        HBox[] hBoxes = new HBox[mon_model.getNbJoueurs()*2+1];

        ColorPicker[] colorPickers = new ColorPicker[mon_model.getNbJoueurs()];
        TextField[] textFields = new TextField[mon_model.getNbJoueurs()];
        String[] tabStringTemp = new String[mon_model.getNbJoueurs()];
        Color[] tabColorTemp = new Color[mon_model.getNbJoueurs()];
        mon_model.setJoueurColors(tabColorTemp);
        mon_model.setTabNomJoueurs(tabStringTemp);

                for (int i = 1; i < mon_model.getNbJoueurs()+1; i++) {
                    hBoxes[i] = new HBox();
                    hBoxes[i+i] = new HBox();
                    textFields[i-1] = new TextField();
                    colorPickers[i-1] = new ColorPicker();
                    String s = new String("Nom du joueur " + i + " ");
                    hBoxes[i].getChildren().add(new Label(s));
                    if (mon_model.isError())textFields[i-1].setText(mon_model.getTabNomJoueurs()[i-1]);
                    hBoxes[i].getChildren().add(textFields[i-1]);
                    s = ("Couleur du joueur " + i + " ");
                    hBoxes[i+i].getChildren().add(new Label(s));
                    if (mon_model.isError())colorPickers[i-1].setValue(mon_model.getJoueurColors()[i-1]);
                        hBoxes[i+i].getChildren().add(colorPickers[i-1]);
                    vBox.getChildren().add(hBoxes[i]);
                    if (mon_model.isError())if (mon_model.getTabError()[i-1][0])vBox.getChildren().add(new Label("Vous devez donner un nom différent de null et des autres"));
                    vBox.getChildren().add(hBoxes[i+i]);
                    if (mon_model.isError())if (mon_model.getTabError()[i-1][1])vBox.getChildren().add(new Label("Vous devez fournir une couleur différentes des autres"));
                    hBoxes[i].setAlignment(Pos.CENTER);
                    hBoxes[i+i].setAlignment(Pos.CENTER);
                }
        vBox.getChildren().add(mon_model.getB_suivant());
        vBox.setSpacing(10);
        vBox.setAlignment(Pos.CENTER);

        borderPane.setCenter(vBox);
        mon_model.getB_suivant().setOnMousePressed(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                for (int i = 0; i < mon_model.getNbJoueurs(); i++) {
                    mon_model.getTabNomJoueurs()[i] = textFields[i].getText();
                    mon_model.getJoueurColors()[i] = colorPickers[i].getValue();
                }
                if (mon_model.verifDifferent() != 0){
                    mon_model.setDisplay(3);
                    switchDisplay();
                }else{
                    mon_model.setDisplay(0);
                    switchDisplay();
                    for (int i = 0; i < mon_model.getNbJoueurs(); i++) {
                        System.out.println(mon_model.getTabNomJoueurs()[i]);
                        System.out.println(mon_model.getJoueurColors()[i]);
                    }
                }
            }
        });
    }



    private void MenuPrincipal() {

        VBox vBox = new VBox();

        vBox.getChildren().add(mon_model.getB_jouer());
        vBox.getChildren().add(mon_model.getB_score());
        vBox.getChildren().add(mon_model.getB_reglages());
        vBox.getChildren().add(mon_model.getB_quitter());
        vBox.setAlignment(Pos.CENTER);
        vBox.setSpacing(10);

        borderPane.setCenter(vBox);

        mon_model.getB_jouer().setOnMousePressed(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                mon_model.setDisplay(1);
                switchDisplay();
            }
        });
        mon_model.getB_score().setOnMousePressed(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                mon_model.setDisplay(2);
            }
        });
        mon_model.getB_reglages().setOnMousePressed(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                mon_model.setDisplay(3);
            }
        });
        mon_model.getB_quitter().setOnMousePressed(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                System.exit(0);
            }
        });
    }












}
