/****************************************************************
 * Šioje klasėje pateikamas įvadas į JavaFX grafiką
 * 
 * Pradžioje vykdykite kodą ir stebėkite atliekamus veiksmus
 * Užduotis atlikite sekdami nurodymus programinio kodo komentaruose
 * Gynimo metu atlikite dėstytojo nurodytas užduotis naujų metodų pagalba.
 *
 * @author Eimutis Karčiauskas, KTU programų inžinerijos katedra 2019 08 05
 **************************************************************************/
package demos.graphics;

import extendsFX.BaseGraphics;
import javafx.scene.paint.Color;
import javafx.scene.shape.ArcType;
import javafx.scene.shape.FillRule;
import javafx.scene.text.Font;
import javafx.stage.Stage;

import java.awt.*;
import java.util.HashMap;

public class Demo0_Basic extends BaseGraphics {

    // pradžioje brėšime horizontalias ir vertikalias linijas per centrą
    private void drawHVtoCenter() {  
        gc.setLineWidth(3);       // brėžimo linijos plotis
        gc.setStroke(Color.RED);  // ir tos linijos spalva
        gc.strokeLine(0, canvasH / 2, canvasW, canvasH / 2);
        gc.strokeLine(canvasW / 2, 0, canvasW / 2, canvasH);
    }
    // po to brėšime įstrižaines per centrą
    private void drawXtoCenter() {
        gc.setLineWidth(4);         // brėžimo linijos plotis
        gc.setStroke(Color.GREEN);  // ir tos linijos spalva
        gc.strokeLine(0, 0, canvasW, canvasH);
        gc.strokeLine(0, canvasH, canvasW, 0);
    }  
// UŽDUOTIS_1: plonomis linijomis su žingsniu step=50 nubrėžkite tinklelį
    private void drawGrid1() {
        double step = 50;
        gc.setLineWidth(0.1);         // linijos plotis galimai mažesnis
        for(double u = step; u < Math.max(canvasW, canvasH); u += step) {
            gc.setStroke(u%100==0? Color.GREY: Color.GREY.brighter());
            gc.strokeLine(0, u, canvasW, u);   // horizontalios linijos
            gc.strokeLine(u, 0, u, canvasH);   // vertikalios linijos
        }

    }
// https://examples.javacodegeeks.com/desktop-java/javafx/javafx-canvas-example/    
    private void drawExamples1() {
        double lw = 3.0;
        gc.setLineWidth(lw);        // brėžimo linijos plotis
        gc.setStroke(Color.BLUE);   // ir tos linijos spalva
        gc.setFill(Color.RED);      // dažymo spalva figūroms
        int x=10, y=10, w=80, h=50, 
            d=20, ax=10, ay=20; // d-tarpas tarp elementų, ax,ay-apvalinimai
        gc.strokeRoundRect(x, y, w, h, ax, ay);
        x+=w+d; // sekantis į dešinę
        gc.fillRoundRect(  x, y, w, h, ax, ay);
        gc.setLineWidth(0.5);
        gc.strokeText("Wolf and Bear", x, y+h);
        //-------------------
        gc.setLineWidth(2*lw);    // dvigubai pastoriname liniją      
        gc.setFill(Color.YELLOW);
        x = 10;    // grįžtame horizontaliai
        y += h+d;  // ir pereiname žemyn
        gc.strokeOval(x, y, w, h);
        x += w+d; // sekantis į dešinę
        gc.fillOval( x, y, h, w);
        x = 10;     // grįžtame horizontaliai
        y += h+2*d; // ir pereiname žemyn ir brėžiame lankus
        gc.strokeArc  (x, y, w, w, 30,  90, ArcType.ROUND);
        gc.fillArc(x+w+d, y, w, w, 45, 180, ArcType.OPEN);
    }  
    private void drawUnicode(){
        // išbandykite ir kitus simbolius
        // https://en.wikipedia.org/wiki/List_of_Unicode  skyrius 31
        StringBuilder sb = new StringBuilder();
        for(char ch = '\u2654'; ch <= '\u265F'; ch++)
            sb.append(ch);
        gc.setFont(Font.font("Lucida Console", 36));
        gc.setLineWidth(1);
        gc.setStroke(Color.BLACK);
        gc.strokeText(sb.toString(), 50, 350);
    }
// UŽDUOTIS_2: nubrėžkite polilinijas ir poligonus   
// https://www.tutorialspoint.com/javafx/2dshapes_polygon    
    private void drawExamples2() {
        final double POINTS_X[] =
        {
                50,
                70,
                70,
                50,
        };
        final double POINTS_Y[] = {
                50,
                50,
                90,
                90,
        };
        gc.setLineWidth(1);
        gc.setStroke(Color.BLACK);
        gc.strokePolygon(POINTS_X, POINTS_Y, POINTS_X.length);


    }
// UŽDUOTIS_3: nubrėžkite taisyklingus 3, 4, 5, ..., 9-kampius  
    private void drawExamples3() {
        final int MAX_POINTS = 5;
        final int LINE_HEIGHT = 30;
        int index = 0;
        Point starting_point_x = new Point(200, 150);
        HashMap<Integer, Point> figure2 = new HashMap<>();
        int kampas = 360 / MAX_POINTS;
        figure2.put(0, starting_point_x);
        int xxx, xxxx;
        int x = kampas;
        int point_x = 0;
        int point_y = 0;

        while(index < MAX_POINTS)
        {
            point_x = figure2.get(index).x;
            point_y = figure2.get(index).y;
            xxx = (int)Math.round(point_x + LINE_HEIGHT * Math.cos(Math.toRadians(x)));
            xxxx = (int)Math.round(point_y + LINE_HEIGHT * Math.sin(Math.toRadians(x)));
            gc.strokeLine(point_x, point_y, xxx, xxxx);
            point_x = xxx;
            point_y = xxxx;
            x += kampas;
            index++;
            figure2.put(index, new Point(point_x, point_y));

        }
        // Nurodymas: parašykite funkciją, kuri paskaičiuoja skaičių masyvus
        // kuriuose surašomos taisyklingo daugiakampio koordinatės
    }
// UŽDUOTIS_4: nubrėžkite žiedus https://en.wikipedia.org/wiki/Olympic_symbols
    private void drawOlympicRings() {
        int START_POSITION_X = 195;
        int START_POSITION_Y = 150;

        gc.setStroke(Color.BLUE);
        gc.setLineWidth(4);
        gc.strokeOval(START_POSITION_X, START_POSITION_Y, 30, 30);

        START_POSITION_X += 17;
        START_POSITION_Y += 16;

        gc.setStroke(Color.ORANGE);

        gc.strokeArc(START_POSITION_X, START_POSITION_Y, 30, 30, 107, 254, ArcType.OPEN);


        START_POSITION_X += 19;
        START_POSITION_Y -= 15;

        gc.setStroke(Color.BLACK);
       // gc.strokeArc(START_POSITION_X, START_POSITION_Y, 30, 30, 90, 360, ArcType.OPEN);
        gc.strokeOval(START_POSITION_X, START_POSITION_Y, 30, 30);
        gc.setStroke(Color.ORANGE);
        gc.strokeArc(START_POSITION_X - 19, START_POSITION_Y + 15, 30, 30, 15, 65, ArcType.OPEN);

        START_POSITION_X += 17;
        START_POSITION_Y += 16;

        gc.setStroke(Color.GREEN);
        gc.strokeArc(START_POSITION_X, START_POSITION_Y, 30, 30, 107, 254, ArcType.OPEN);

        START_POSITION_X += 19;
        START_POSITION_Y -= 15;
        gc.setStroke(Color.RED);
        gc.strokeOval(START_POSITION_X, START_POSITION_Y, 30, 30);
        gc.setStroke(Color.GREEN);
        gc.strokeArc(START_POSITION_X - 19, START_POSITION_Y + 15, 30, 30, 15, 67, ArcType.OPEN);


    }
// UŽDUOTIS_5: pasirinktinai nubrėžkite savo tematiką:
// kelių valstybių sudėtingesnes vėliavas http://flagpedia.net/index
// pvz: Pietų Afrikos, Makedonijos, Norvegijos, Graikijos, Britanijos, ...
// arba futbolo, krepšinio ar ledo ritulio aikštes su žaidėjų pozicijomis
    private void drawFreeThema() {
        // Guayna Flag
        final int[] FLAG_BASE = {
               // 130,
                //170,
                //110,
                //49
                130,
                180,
                110,
                49
        };
        final Point TRIANGLE_AV_S = new Point(FLAG_BASE[0], FLAG_BASE[1] + 5);
        final Point TRIANGLE_AV_E = new Point(FLAG_BASE[0], FLAG_BASE[1] + 43);
        final Point TRIANAGLE_AV_HEIGHT = new Point(TRIANGLE_AV_S.x + 40, TRIANGLE_AV_S.y + (TRIANGLE_AV_E.y - TRIANGLE_AV_S.y) / 2);
        final Point TRIANGLE_SM_S = new Point(TRIANGLE_AV_S.x , TRIANGLE_AV_S.y - 5);
        final Point TRIANGLE_SM_E = new Point(TRIANGLE_AV_E.x, TRIANGLE_AV_E.y + 5);
        final Point TRIANGLE_SM_HEIGHT = new Point(TRIANAGLE_AV_HEIGHT.x + 8, TRIANAGLE_AV_HEIGHT.y);
        final Point TRIANGLE_BIG_S = new Point(TRIANGLE_SM_S.x + 2, TRIANGLE_SM_S.y + 1);
        final Point TRIANGLE_BIG_E = new Point(TRIANGLE_SM_E.x + 2, TRIANGLE_SM_E.y - 1);
        final Point TRIANGLE_BIG_HEIGHT = new Point(TRIANAGLE_AV_HEIGHT.x + 60, TRIANAGLE_AV_HEIGHT.y);
        final Point TRIANGLE_SM1_S = new Point(TRIANGLE_SM_S.x, TRIANGLE_SM_S.y - 1);
        final Point TRIANGLE_SM1_E = new Point(TRIANGLE_SM_E.x, TRIANGLE_SM_E.y + 1);
        final Point TRIANGLE_SM1_HEIGHT = new Point(TRIANGLE_BIG_HEIGHT.x + 8, TRIANGLE_BIG_HEIGHT.y);
        final Point TRIANGLE_H_S = new Point(FLAG_BASE[0] + FLAG_BASE[2], FLAG_BASE[1]);
        final Point TRIANGLE_H_E = new Point(TRIANGLE_H_S.x, TRIANGLE_H_S.y + FLAG_BASE[3]);
        final Point TRIANGLE_H_HEIGHT = new Point(FLAG_BASE[0], FLAG_BASE[1]);
        final double[] TRIANGLE_AV_X = {
                TRIANGLE_AV_S.x,
                TRIANGLE_AV_E.x,
                TRIANAGLE_AV_HEIGHT.x
        };
        final double[] TRIANGLE_AV_Y = {
                TRIANGLE_AV_S.y,
                TRIANGLE_AV_E.y,
                TRIANAGLE_AV_HEIGHT.y
        };
        final double[] TRIANGLE_SM_X = {
                TRIANGLE_AV_S.x,
                TRIANGLE_SM_S.x,
                TRIANGLE_SM_HEIGHT.x,
                TRIANGLE_SM_E.x,
                TRIANGLE_AV_E.x,
                TRIANAGLE_AV_HEIGHT.x
        };
        final double[] TRIANGLE_SM_Y = {
                TRIANGLE_AV_S.y,
                TRIANGLE_SM_S.y,
                TRIANGLE_SM_HEIGHT.y,
                TRIANGLE_SM_E.y,
                TRIANGLE_AV_E.y,
                TRIANAGLE_AV_HEIGHT.y
        };
        final double[] TRIANGLE_BIG_X = {
                TRIANGLE_BIG_S.x,
                TRIANGLE_BIG_HEIGHT.x,
                TRIANGLE_BIG_E.x,
                TRIANGLE_SM_HEIGHT.x
        };
        final double[] TRIANGLE_BIG_Y = {
                TRIANGLE_BIG_S.y,
                TRIANGLE_BIG_HEIGHT.y,
                TRIANGLE_BIG_E.y,
                TRIANGLE_SM_HEIGHT.y + 1
        };
        final double[] TRIANGLE_SM1_X = {
                TRIANGLE_BIG_S.x,
                TRIANGLE_SM1_S.x,
                TRIANGLE_SM1_HEIGHT.x,
                TRIANGLE_SM1_E.x,
                TRIANGLE_BIG_E.x,
                TRIANGLE_BIG_HEIGHT.x
        };
        final double[] TRIANGLE_SM1_Y = {
                TRIANGLE_BIG_S.y,
                TRIANGLE_SM1_S.y,
                TRIANGLE_SM1_HEIGHT.y,
                TRIANGLE_SM1_E.y,
                TRIANGLE_BIG_E.y,
                TRIANGLE_BIG_HEIGHT.y
        };
        final double[] TRIANGLE_H_X = {
                TRIANGLE_H_S.x,
                TRIANGLE_H_HEIGHT.x + 3,
                TRIANGLE_H_S.x ,
                FLAG_BASE[0],
                TRIANGLE_H_E.x
        };
        final double[] TRIANGLE_H_Y = {
                TRIANGLE_H_S.y,
                TRIANGLE_H_HEIGHT.y,
                (TRIANGLE_H_S.y  + (TRIANGLE_H_E.y - TRIANGLE_H_S.y) / 2),
                FLAG_BASE[1] + FLAG_BASE[3],
                TRIANGLE_H_E.y,
        };
        gc.strokeRect(FLAG_BASE[0], FLAG_BASE[1], FLAG_BASE[2], FLAG_BASE[3]);
        gc.setStroke(Color.RED);
        gc.setFill(Color.RED);
        gc.setFillRule(FillRule.EVEN_ODD);
        gc.strokePolygon(TRIANGLE_AV_X, TRIANGLE_AV_Y, 3);
        gc.fillPolygon(TRIANGLE_AV_X, TRIANGLE_AV_Y, 3);
        gc.strokePolygon(TRIANGLE_SM_X, TRIANGLE_SM_Y, 6);
        gc.setStroke(Color.BLACK);
        gc.setFill(Color.BLACK);
        gc.setFillRule(FillRule.EVEN_ODD);
        gc.fillPolygon(TRIANGLE_SM_X, TRIANGLE_SM_Y, 6);
        gc.setStroke(Color.YELLOW);
        gc.setFill(Color.YELLOW);
        gc.strokePolygon(TRIANGLE_BIG_X , TRIANGLE_BIG_Y, 4);
        gc.fillPolygon(TRIANGLE_BIG_X , TRIANGLE_BIG_Y, 4);
        gc.setStroke(Color.WHITE);
        gc.setFill(Color.WHITE);
        gc.strokePolygon(TRIANGLE_SM1_X, TRIANGLE_SM1_Y, 6);
        gc.fillPolygon(TRIANGLE_SM1_X, TRIANGLE_SM1_Y, 6);
        gc.setStroke(Color.GREEN);
        gc.setFill(Color.GREEN);
        gc.strokePolygon(TRIANGLE_H_X, TRIANGLE_H_Y, 5);
        gc.fillPolygon(TRIANGLE_H_X, TRIANGLE_H_Y, 5);


    }    
// kontrolinės užduotys gynimo metu:
// įvairios vėliavos, tiesiog tokios sudėtinės figūros kaip namukas,
// medis, eglė, sniego senio siluetas :-) ir t.t.    
    @Override
    public void createControls(){
        addButton("clear", e -> clearCanvas()); 
        addButton("grid",  e -> baseGrid());
        addButton("HVC",   e -> drawHVtoCenter());
        addButton("XC",    e -> drawXtoCenter());
        addButton("pvz1",  e -> drawExamples1());
        addButton("UniCode",  e -> drawUnicode());
        addButton("CustomGrid", e->drawGrid1());
        addButton("Draw Polygon", e->drawExamples2());
        addButton("Draw n-figure", e->drawExamples3());
        addButton("Olympic rings", e-> drawOlympicRings());
        addButton("My Theme", e-> drawFreeThema());
        addNewHBox();
    }
    @Override
    public void start(Stage stage) throws Exception {
        stage.setTitle("Braižymai Canvas lauke (KTU IF)");        
        setCanvas(Color.CYAN.brighter(), 600, 400);
        super.start(stage);
    }       
    public static void main(String[] args) {
        launch(args);
    }    
}
