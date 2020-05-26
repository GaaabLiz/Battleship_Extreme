import controller.BattleshipExtremeController;
import model.BattleshipExtremeModel;
import view.BattleshipExtremeView;


/**
 * Classe che contiene il main del programma.
 * @author Gabriele
 */
public class BattleshipExtremeMain {
    @SuppressWarnings("unused")
	public static void main(String[] args) {

    	BattleshipExtremeModel model = new BattleshipExtremeModel();
        BattleshipExtremeView view = new BattleshipExtremeView();
        BattleshipExtremeController controller = new BattleshipExtremeController(model, view);
    
    }

}
