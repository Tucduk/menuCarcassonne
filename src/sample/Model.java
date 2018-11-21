package sample;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.ComboBoxListCell;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

import java.awt.*;

public class Model extends Parent {
    //Variables
    private int display = 0;
    private int nbJoueurs = 0;
    private String[] nomJoueur;
    private boolean[] tabError;
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
    private int nbJoueurs2 = 0;
    private VBox vBox = new VBox();
    private HBox nom = new HBox();
    private HBox couleurs = new HBox();
    private Label noms = new Label("Nom du joueur: ");
    private javafx.scene.control.TextField labelnom = new TextField();
    private ComboBoxListCell tableCouleurs = new ComboBoxListCell(8);

    private Label couleursDuJoueurs = new Label("Couleurs du joueur " + joueurEnCoursDeCreation + ":" );
    private Label couleurSelectionnée = new Label("   Couleur sélectionner :  ");

    private Rectangle vert = new Rectangle(25,25,Color.GREEN);
    private Rectangle jaune = new Rectangle(25,25,Color.GOLD);
    private Rectangle rouge = new Rectangle(25,25,Color.RED);
    private Rectangle bleu = new Rectangle(25,25,Color.BLUE);
    private Rectangle noir = new Rectangle(25,25,Color.BLACK);
    private Rectangle rose = new Rectangle(25,25,Color.PINK);
    private Rectangle violet = new Rectangle(25,25,Color.BLUEVIOLET);
    private Rectangle chartreuse = new Rectangle(25,25,Color.CHARTREUSE);
    private Rectangle gris = new Rectangle(25,25,Color.GREY);
    private Rectangle[] tabRectangle = {vert, jaune, rouge, bleu, noir, rose, violet, chartreuse, gris};
    private Rectangle[] tabRectangleTemp;
    private Color[] joueurColors;
    public void initRectangle(){
        tabRectangleTemp = new Rectangle[tabRectangle.length-1];
            for (int i = 0; i < tabRectangle.length-1; i++) {
                tabRectangleTemp[i]=tabRectangle[i];
            }
            tabRectangle = tabRectangleTemp;
    }
    public void selectedColor(int i){
        if (tabRectangle[tabRectangle.length-1] == vert){
            joueurColors[i] = Color.GREEN;
        }
        if (tabRectangle[tabRectangle.length-1] == jaune){
            joueurColors[i] = Color.GOLD;
        }
        if (tabRectangle[tabRectangle.length-1] == rouge){
            joueurColors[i] = Color.RED;
        }
        if (tabRectangle[tabRectangle.length-1] == bleu){
            joueurColors[i] = Color.BLUE;
        }
        if (tabRectangle[tabRectangle.length-1] == noir){
            joueurColors[i] = Color.BLACK;
        }
        if (tabRectangle[tabRectangle.length-1] == rose){
            joueurColors[i] = Color.PINK;
        }
        if (tabRectangle[tabRectangle.length-1] == violet){
            joueurColors[i] = Color.BLUEVIOLET;
        }
        if (tabRectangle[tabRectangle.length-1] == chartreuse){
            joueurColors[i] = Color.CHARTREUSE;
        }
        if (tabRectangle[tabRectangle.length-1] == gris){
            joueurColors[i] = Color.GREY;
        }
    }
    private String[] tabNomJoueurs = null;
    //fonctions

    public int verifDifferent(){ //verification pour savoir si toute les valeurs sont différentes
                                  // et sinon savoir lesquelles ne vont pas
        int nberreur = 0;
        if (tabNomJoueurs[nbJoueurs2-1]==null || tabNomJoueurs[nbJoueurs2-1].equals("")){
            nberreur++;
        }else {
            for (int i = 0; i < nbJoueurs - 1; i++) {
                for (int j = 0; j < nbJoueurs; j++) {
                    if (tabNomJoueurs[i] != null && tabNomJoueurs[j] != null && tabNomJoueurs[i].equals(tabNomJoueurs[j]) && i != j) {
                        nberreur++;
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

    public void changeSelectCouleur(int i){
        Rectangle e = getTabRectangle()[i];
        tabRectangle[i] = tabRectangle[tabRectangle.length-1];
        tabRectangle[tabRectangle.length-1] = e;
    }


    //Getter/Setter


    public Label getNoms() {
        return noms;
    }

    public void setNoms(Label noms) {
        this.noms = noms;
    }

    public TextField getLabelnom() {
        return labelnom;
    }

    public void setLabelnom(TextField labelnom) {
        this.labelnom = labelnom;
    }

    public ComboBoxListCell getTableCouleurs() {
        return tableCouleurs;
    }

    public void setTableCouleurs(ComboBoxListCell tableCouleurs) {
        this.tableCouleurs = tableCouleurs;
    }

    public Label getCouleursDuJoueurs() {
        return couleursDuJoueurs;
    }

    public void setCouleursDuJoueurs(Label couleursDuJoueurs) {
        this.couleursDuJoueurs = couleursDuJoueurs;
    }

    public Label getCouleurSelectionnée() {
        return couleurSelectionnée;
    }

    public void setCouleurSelectionnée(Label couleurSelectionnée) {
        this.couleurSelectionnée = couleurSelectionnée;
    }

    public void setTabError(boolean[] tabError) {
        this.tabError = tabError;
    }

    public VBox getvBox() {
        return vBox;
    }

    public void setvBox(VBox vBox) {
        this.vBox = vBox;
    }

    public HBox getNom() {
        return nom;
    }

    public void setNom(HBox nom) {
        this.nom = nom;
    }

    public HBox getCouleurs() {
        return couleurs;
    }

    public void setCouleurs(HBox couleurs) {
        this.couleurs = couleurs;
    }

    public Rectangle[] getTabRectangle() {
        return tabRectangle;
    }

    public void setTabRectangle(Rectangle[] tabRectangle) {
        this.tabRectangle = tabRectangle;
    }

    public int getNbJoueurs2() {
        return nbJoueurs2;
    }

    public void setNbJoueurs2(int nbJoueurs2) {
        this.nbJoueurs2 = nbJoueurs2;
    }

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

    public boolean[] getTabError() {
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
