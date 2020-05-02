import controller.BattleshipExtremeController;
import model.BattleshipExtremeModel;
import view.BattleshipExtremeView;

public class BattleshipExtremeMain {

    public static void main(String[] args) {
    	BattleshipExtremeModel model = new BattleshipExtremeModel();
        BattleshipExtremeView view = new BattleshipExtremeView();
        BattleshipExtremeController controller = new BattleshipExtremeController(model, view);
    }

}
