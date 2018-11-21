package sample;

public class Controller {
    Model mon_model = new Model();
    menu menu = new menu();

    private void switchDisplay(){
        switch (mon_model.getDisplay()){
            case 1:
                menu.MenuJeu();
                break;
            case 2:
                menu.MenuJeu();
                break;
            case 3:
                menu.CouleurJoueursBis(mon_model.getJoueurEnCoursDeCreation());
                break;
            default:
                menu.MenuPrincipal();
                break;
        }
        menu.borderPane.setMinSize(500,500);
    }
}
