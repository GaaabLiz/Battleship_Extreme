import java.sql.SQLException;

import controller.BattleshipExtremeController;
import controller.DatabaseController;
import controller.WindowMainListener;
import model.BattleshipExtremeModel;
import view.BattleshipExtremeView;

public class BattleshipExtremeMain {

    public static void main(String[] args) {
    	
    	    	
    	// Pattern MVC
    	BattleshipExtremeModel model = new BattleshipExtremeModel();
        BattleshipExtremeView view = new BattleshipExtremeView();
        view.addWindowListener(new WindowMainListener(model));
        BattleshipExtremeController controller = new BattleshipExtremeController(model, view);
        
        // Settaggio Window Listener
        
    }

}
