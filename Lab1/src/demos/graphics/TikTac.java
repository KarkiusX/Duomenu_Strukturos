package demos.graphics;

import extendsFX.BaseApps;
import extendsFX.BaseGraphics;
import javafx.event.EventHandler;
import javafx.event.EventType;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Polygon;
import javafx.scene.shape.Polyline;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.awt.*;
import java.util.*;
import java.util.List;

public class TikTac extends BaseGraphics {

    enum Player_Type{
        X, O
    }
    enum Winner{
        Cross, Circle
    }
    final int TIK_X = 3;
    final int TIK_Y = 3;
    int Press_Count = 0;
    boolean Game_Over = false;
    Queue<Point> GAME_MOVES = new ArrayDeque<>();
    Map<Integer, Point> WIN_CONDITION = new HashMap<>();
    Map<Player_Type, List<Point>> Player_Clicks = new HashMap<>();
    final int RECK_WIDTH = 100;
    final int RECK_HEIGHT = 100;
    Player_Type Player = Player_Type.X;
    Winner winner;

    public void Create_Tic_Tac_Game()
    {
        Rectangle test = new Rectangle(RECK_WIDTH, RECK_HEIGHT);
        Reset_Everything();
        int Count = 0;
        int Y = 0;
        int X = 0;
        for(int i = 0; i < TIK_X; i++)
        {
            for(int j = 0; j < TIK_Y; j++)
            {
                Rectangle recki = new Rectangle(X, Y, RECK_WIDTH, RECK_HEIGHT);
                recki.setStroke(Color.BLACK);
                recki.setFill(Color.WHITE);
                nodes.add(recki);
                recki.setOnMouseClicked(e ->{
                    if(!Game_Over) {
                        if (Can_Move(new Point((int) recki.getX(), (int) recki.getY()))) {
                            if (Player == Player_Type.X) {
                                Polyline polyline = new Polyline(recki.getX(), recki.getY(), recki.getX() + RECK_WIDTH, recki.getY() + RECK_HEIGHT);
                                Polyline polyline1 = new Polyline(recki.getX(), recki.getY() + RECK_HEIGHT, recki.getX() + RECK_WIDTH, recki.getY());
                                nodes.add(polyline);
                                nodes.add(polyline1);

                            } else {
                                Circle circle = new Circle(recki.getX() + (RECK_WIDTH / 2), recki.getY() + (RECK_HEIGHT / 2), RECK_HEIGHT / 2);
                                circle.setFill(Color.WHITE);
                                circle.setStroke(Color.BLACK);
                                nodes.add(circle);
                            }
                        }
                        if (Press_Count >= 3) {
                            if (Check_Game_Conditions()) {

                                Text text = new Text(340, 80, "Win:" + winner);
                                text.setFont(Font.font(Font.getDefault().getFamily(), 30));
                                nodes.add(text);
                                Game_Over = true;
                            }
                        }
                        if (Player == Player_Type.O) {
                            Player = Player_Type.X;
                        } else {
                            Player = Player_Type.O;
                        }
                    }
                    e.consume();

                });
                WIN_CONDITION.put(Count, new Point((int)recki.getX(), (int)recki.getY()));
                Count++;
                X += RECK_WIDTH;
            }
            Y += RECK_HEIGHT;
            X = 0;
        }

    }
    public void Reset_Everything()
    {
        if(nodes.size() > 0) {
            nodes.remove(0, nodes.size());
        }
        Game_Over = false;
        Player = Player_Type.X;
        winner = null;
        Press_Count = 0;
        GAME_MOVES = new ArrayDeque<>();
        WIN_CONDITION = new HashMap<>();
        Player_Clicks = new HashMap<>();
    }
    public boolean Check_Game_Conditions()
    {
        if(Press_Count == 9)
        {
            return true;
        }
        if(Player == Player_Type.X && Check_Player_Condition(Player))
        {
            winner = Winner.Cross;
            return true;
        }
        else if(Player == Player_Type.O && Check_Player_Condition(Player))
        {
            winner = Winner.Circle;
            return true;
        }
        return false;
    }
    public boolean Can_Move(Point point)
    {
        if(!GAME_MOVES.contains(point))
        {
            Press_Count++;
            GAME_MOVES.add(point);
            if(Player_Clicks.get(Player) == null) {
                Player_Clicks.put(Player, new ArrayList<>());
            }
                Player_Clicks.get(Player).add(point);
            return true;
        }
        return false;
    }
    public boolean Check_Player_Condition(Player_Type player)
    {
        if(Player_Clicks.get(Player).contains(WIN_CONDITION.get(0)) && Player_Clicks.get(Player).contains(WIN_CONDITION.get(1)) && Player_Clicks.get(Player).contains(WIN_CONDITION.get(2)))
        {
            return true;
        }
        else if(Player_Clicks.get(Player).contains(WIN_CONDITION.get(3)) && Player_Clicks.get(Player).contains(WIN_CONDITION.get(4)) && Player_Clicks.get(Player).contains(WIN_CONDITION.get(5)))
        {
            return true;
        }
        else if(Player_Clicks.get(Player).contains(WIN_CONDITION.get(6)) && Player_Clicks.get(Player).contains(WIN_CONDITION.get(7)) && Player_Clicks.get(Player).contains(WIN_CONDITION.get(8))){
            return true;
        }
        else if(Player_Clicks.get(Player).contains(WIN_CONDITION.get(0)) && Player_Clicks.get(Player).contains(WIN_CONDITION.get(3)) && Player_Clicks.get(Player).contains(WIN_CONDITION.get(6)))
        {
            return true;
        }
        else if(Player_Clicks.get(Player).contains(WIN_CONDITION.get(1)) && Player_Clicks.get(Player).contains(WIN_CONDITION.get(4)) && Player_Clicks.get(Player).contains(WIN_CONDITION.get(7)))
        {
            return true;
        }
        else if(Player_Clicks.get(Player).contains(WIN_CONDITION.get(2)) && Player_Clicks.get(Player).contains(WIN_CONDITION.get(5)) && Player_Clicks.get(Player).contains(WIN_CONDITION.get(8)))
        {
            return true;
        }
        else if(Player_Clicks.get(Player).contains(WIN_CONDITION.get(0)) && Player_Clicks.get(Player).contains(WIN_CONDITION.get(4)) && Player_Clicks.get(Player).contains(WIN_CONDITION.get(8)))
        {
            return true;
        }
        else if(Player_Clicks.get(Player).contains(WIN_CONDITION.get(2)) && Player_Clicks.get(Player).contains(WIN_CONDITION.get(4)) && Player_Clicks.get(Player).contains(WIN_CONDITION.get(6)))
        {
            return true;
        }
        return false;
    }
    @Override
    public void createControls() {
        addButton("Create Game", e -> Create_Tic_Tac_Game());
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
