import java.sql.SQLException;

import controller.BattleshipExtremeController;
import model.BattleshipExtremeModel;
import view.BattleshipExtremeView;

public class BattleshipExtremeMain {

	/**
	 * main del programma
	 * @param args
	 */
    public static void main(String[] args) {
    	   	    	
    	// Pattern MVC
    	BattleshipExtremeModel model = new BattleshipExtremeModel();
        BattleshipExtremeView view = new BattleshipExtremeView();
        BattleshipExtremeController controller = new BattleshipExtremeController(model, view);
        
    }

}
