package demos.graphics;

import extendsFX.BaseGraphics;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.awt.*;

public class TikTac_Base extends BaseGraphics {
    private EventHandler hl;
    enum Player_Type{
        X, O
    }
    Player_Type Type = Player_Type.X;
    final int SquareWidth = 100;
    final int SquareHeight = 100;
    final int TIK_X = 3;
    final int TIK_Y = 3;
    final Point Starting_Point = new Point(0, 0);
    final javafx.scene.control.Button[] Game_Buttons ={
            new javafx.scene.control.Button(),
            new javafx.scene.control.Button(),
    };
    private void CreateGameCanvas()
    {
        gc.setStroke(Color.RED);
        int x = 0;
        int y= 0;
        for(int i =0; i < TIK_X; i++) {

            for (int b = 0; b < TIK_Y; b++) {
                gc.strokeLine(Starting_Point.x + x, Starting_Point.y + y, Starting_Point.x + SquareWidth + x, Starting_Point.y + y);
                gc.strokeLine(Starting_Point.x + x, Starting_Point.y + y, Starting_Point.x + x, Starting_Point.y + SquareHeight + y);
                gc.strokeLine(Starting_Point.x + x, Starting_Point.y + SquareHeight + y, Starting_Point.x + SquareWidth + x, Starting_Point.y + SquareHeight + y);
                gc.strokeLine(Starting_Point.x + SquareWidth + x, Starting_Point.y + SquareHeight + y, Starting_Point.x + SquareWidth + x, Starting_Point.y + y);
                x += SquareWidth;
            }
            x = 0;
            y += SquareHeight;
        }
    }
    private void PlayPart(ObservableList<Node> nodes, int start, int end)
    {
        if(Type == Player_Type.X)
        {
           System.out.println(nodes.get(1).getLayoutX());
        }
        else if(Type == Player_Type.O)
        {
            System.out.println(nodes.get(1).getLayoutX());
        }
    }
    final private javafx.event.EventHandler OnPress = e->{
        Game_Buttons[0].setDisable(true);
    };
    @Override
    public void createControls() {
        addButton("Clear", e -> {clearCanvas(); Game_Buttons[0].setDisable(false);});
        Game_Buttons[0] = addButton("CreateSquare", e -> {CreateGameCanvas(); hl = OnPress;});
        Game_Buttons[1] = addButton("Click On[1,1]", e-> PlayPart(nodes, 0, 3));
    }
    @Override
    public void start(Stage stage) throws Exception {
        setCanvas(Color.WHITE, 600, 600);
        super.start(stage);
        stage.setTitle("TicTac The Game");
    }

    public static void main(String[] args) {
        launch(args);
    }
}
