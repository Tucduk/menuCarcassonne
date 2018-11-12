package sample;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.paint.Color;

public class Model extends Parent {
    //Variables
    private int display = 0;
    private int nbJoueurs = 0;
    private String[] nomJoueur;
    private boolean[][] tabError;
    private boolean error = false;


    //elements menu principal
    private Button b_jouer = new Button("Jouer");

    private Button b_reglages = new Button("Reglages");
    private Button b_score = new Button("Scores");
    private Button b_quitter = new Button("Quitter");

    //elements menu jeu
    private Label l_NombreJoueurs = new Label("Nombre de joueur : ");

    private ObservableList<Integer> options =
            FXCollections.observableArrayList(2,3,4,5);
    final ComboBox comboBox = new ComboBox(options);
    private Button b_suivant = new Button("Suivant");
    //element choix couleurs
    private int joueurEnCoursDeCreation = 1;
    private String[] tabNomJoueurs = new String[nbJoueurs];
    private Color[] joueurColors = new Color[nbJoueurs];

    //fonctions
    public int verifDifferent(){ //verification pour savoir si toute les valeurs sont différentes
                                  // et sinon savoir lesquelles ne vont pas
        int nberreur = 0;
        tabError = new boolean[nbJoueurs][2];
        for (int i = 0; i < nbJoueurs; i++) {
            tabError[i][0] = false;
            tabError[i][1] = false;
        }
        for (int i = 0; i < nbJoueurs; i++) {
            for (int j = 1; j < nbJoueurs; j++) {
                if (tabNomJoueurs[i].equals("")){
                    tabError[i][0] = true;
                    nberreur++;
                }
                if (i!=j){
                    if (tabNomJoueurs[i].equals(tabNomJoueurs[j])){
                        tabError[i][0] = true;
                        tabError[j][0] = true;
                        nberreur+=2;
                    }
                    if (joueurColors[i].equals(joueurColors[j])){
                        tabError[i][1] = true;
                        tabError[j][1] = true;
                        nberreur+=2;
                    }
                }
            }
        }
        if (nberreur!=0){
            error=true;
        }
        else{
            error = false;
        }
        return nberreur;
    }


    //Getter/Setter


    public int getJoueurEnCoursDeCreation() {
        return joueurEnCoursDeCreation;
    }

    public void setJoueurEnCoursDeCreation(int joueurEnCoursDeCreation) {
        this.joueurEnCoursDeCreation = joueurEnCoursDeCreation;
    }

    public String[] getNomJoueur() {
        return nomJoueur;
    }

    public void setNomJoueur(String[] nomJoueur) {
        this.nomJoueur = nomJoueur;
    }

    public boolean isError() {
        return error;
    }

    public void setError(boolean error) {
        this.error = error;
    }

    public boolean[][] getTabError() {
        return tabError;
    }

    public Color[] getJoueurColors() {
        return joueurColors;
    }

    public void setJoueurColors(Color[] joueurColors) {
        this.joueurColors = joueurColors;
    }

    public String[] getTabNomJoueurs() {
        return tabNomJoueurs;
    }

    public void setTabNomJoueurs(String[] tabNomJoueurs) {
        this.tabNomJoueurs = tabNomJoueurs;
    }

    public Button getB_suivant() {
        return b_suivant;
    }

    public void setNbJoueurs(int nbJoueurs) {
        this.nbJoueurs = nbJoueurs;
    }

    public int getNbJoueurs() {
        return nbJoueurs;
    }

    public ComboBox getComboBox() {
        return comboBox;
    }

    public Label getL_NombreJoueurs() {
        return l_NombreJoueurs;
    }

    public int getDisplay() {
        return display;
    }

    public void setDisplay(int display) {
        this.display = display;
    }

    public Button getB_jouer() {
        return b_jouer;
    }

    public Button getB_reglages() {
        return b_reglages;
    }

    public Button getB_score() {
        return b_score;
    }

    public Button getB_quitter() {
        return b_quitter;
    }
}
