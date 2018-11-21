package sample;

import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

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
                mon_model.setNbJoueurs2(mon_model.getNbJoueurs());
                mon_model.setJoueurColors(new Color[mon_model.getNbJoueurs()]);
                System.out.println(mon_model.getNbJoueurs());
                mon_model.setDisplay(3);
                switchDisplay();
            }
        });

    }
    private void  action(){
        for (int i = 0; i < mon_model.getTabRectangle().length-1; i++) {
            final int x = i;
            mon_model.getTabRectangle()[i].setOnMouseClicked(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent event) {
                    mon_model.getNom().getChildren().remove(mon_model.getTabRectangle()[mon_model.getTabRectangle().length-1]);
                    for (int i = 0; i < mon_model.getTabRectangle().length-1; i++) {
                        mon_model.getCouleurs().getChildren().remove(mon_model.getTabRectangle()[i]);
                    }
                    mon_model.changeSelectCouleur(x);
                    mon_model.getNom().getChildren().add(mon_model.getTabRectangle()[mon_model.getTabRectangle().length-1]);
                    mon_model.getNom().setAlignment(Pos.CENTER);
                    for (int i = 0; i < mon_model.getTabRectangle().length-1; i++) {
                        mon_model.getCouleurs().getChildren().add(mon_model.getTabRectangle()[i]);
                    }
                    mon_model.getCouleurs().setAlignment(Pos.CENTER);
                    action();
                }
            });
        }
    }

    private void CouleurJoueursBis(int numJoueur){

        mon_model.getNom().getChildren().add(mon_model.getNoms());
        mon_model.getNom().getChildren().add(mon_model.getLabelnom());
        mon_model.getNom().getChildren().add(mon_model.getCouleurSelectionnée());
        mon_model.getNom().getChildren().add(mon_model.getTabRectangle()[mon_model.getTabRectangle().length-1]);
        mon_model.getNom().setAlignment(Pos.CENTER);

        for (int i = 0; i < mon_model.getTabRectangle().length-1; i++) {
            mon_model.getCouleurs().getChildren().add(mon_model.getTabRectangle()[i]);
        }
        mon_model.getCouleurs().setAlignment(Pos.CENTER);


        mon_model.getvBox().getChildren().add(mon_model.getNom());
        mon_model.getvBox().getChildren().add(mon_model.getCouleursDuJoueurs());

        mon_model.getCouleurs().setSpacing(10);

        mon_model.getvBox().getChildren().add(mon_model.getCouleurs());
        mon_model.getvBox().getChildren().add(mon_model.getB_suivant());
        mon_model.getvBox().setAlignment(Pos.CENTER);
        mon_model.getvBox().setSpacing(10);

        borderPane.setCenter(mon_model.getvBox());
        if (mon_model.isError()){
            mon_model.getvBox().getChildren().add(new Label("Vous devez donner un nom différent de null et des autres"));
        }
        action();

        mon_model.getB_suivant().setOnMousePressed(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                String[] tabTemp = new String[mon_model.getNbJoueurs()];
                if (mon_model.getNbJoueurs() == mon_model.getNbJoueurs2()){
                    mon_model.setTabNomJoueurs(tabTemp);
                }
                for (int i = 0; i < mon_model.getNbJoueurs()-1; i++) {
                    tabTemp = mon_model.getTabNomJoueurs();
                }
                tabTemp[mon_model.getNbJoueurs2()-1] = mon_model.getLabelnom().getText();
                mon_model.setTabNomJoueurs(tabTemp);
                mon_model.selectedColor(mon_model.getNbJoueurs2()-1);
                mon_model.verifDifferent();
                if ( mon_model.isError() == false) {
                    System.out.println("pas d'erreur");
                    mon_model.setNbJoueurs2(mon_model.getNbJoueurs2() - 1);
                    mon_model.initRectangle();
                    System.out.println(mon_model.getNbJoueurs2());
                    mon_model.getLabelnom().setText("");
                    if (mon_model.getNbJoueurs2() == 0) {
                        mon_model.setDisplay(0);
                        mon_model.getvBox().getChildren().clear();
                        mon_model.getNom().getChildren().clear();
                        mon_model.getCouleurs().getChildren().clear();
                        switchDisplay();
                    } else {
                        mon_model.setDisplay(3);
                        mon_model.getvBox().getChildren().clear();
                        mon_model.getNom().getChildren().clear();
                        mon_model.getCouleurs().getChildren().clear();
                        switchDisplay();
                    }
                }else{
                    System.out.println("Erreur");
                    mon_model.getvBox().getChildren().clear();
                    mon_model.getNom().getChildren().clear();
                    mon_model.getCouleurs().getChildren().clear();
                    mon_model.setDisplay(3);
                    switchDisplay();
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
