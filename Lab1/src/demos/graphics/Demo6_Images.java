/****************************************************************
 * Šioje klasėje eksperimentuojama su kuriamų figūrų prioritetais
 * Sukuriami objektai Images klasės pagrindu
 * 
 * Pradžioje vykdykite kodą ir stebėkite atliekamus veiksmus
 * Užduotis atlikite sekdami nurodymus programinio kodo komentaruose
 * Studentams siūloma toliau vystyti Images pagrindų temą.
 * 
 * @author Eimutis Karčiauskas, KTU programų inžinerijos katedra 2019 08 05
 **************************************************************************/
package demos.graphics;

import extendsFX.BaseGraphics;
import javafx.animation.Animation;
import javafx.animation.AnimationTimer;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.paint.Color;
import javafx.scene.shape.*;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.awt.*;
import java.awt.event.MouseEvent;

public class Demo6_Images extends BaseGraphics {
    private EventHandler eh;
    void createRects(){
        double x = 0,  y = 0;
        double w = 60, h = 40;
        for(int i=0; i<12; i++){
            Rectangle rect = new Rectangle(x += w/2, y += h/2, w, h);
            rect.setFill(randomColor()); 
            nodes.add(rect);
        }
    }
    void createCircles(){
        double radius = 30;
        for(int i=0; i<12; i++){
            Circle circle = new Circle(canvasW/2, canvasH/2, radius);
            circle.setFill(randomColor());  
            nodes.add(0, circle);
            radius += 15;
        }
    }
    // vykdant tolimesnę funciją stebėkite ir paaiškinkite vaizdo kitimą
    void fromEndToBegin(){
        nodes.add(0, nodes.remove(nodes.size()-1));
    }
//=================================================    
    Image space = new Image( "images\\space.png" );
    Image sun   = new Image( "images\\sun.png" );
    Image earth = new Image( "images\\earth.png" );
    Image moon = new Image ("images\\moon.png");
    ImageView earthView = new ImageView(earth);
    ImageView moonView = new ImageView(moon);
    AnimationTimer timer = null;
    long aTimer = 0L;
    long startNanoTime = 0L;
    boolean FirsTime = false;
    Point pradinis_p = new Point();
    // kosmosą ir saulę nupiešime ant drobės, o žemę paleisime suktis
    void createSpace() {
        final double xc = canvasW / 2;
        final double yc = canvasH / 2;
        final double radius = canvasW / 2 - earth.getWidth() / 2 - 85;

        gc.drawImage(space, 0, 0, canvasW + 100, canvasH + 100);
        gc.drawImage(sun, xc - sun.getWidth() / 2, yc - sun.getHeight() / 2);
        nodes.add(moonView);
        nodes.add(earthView);
        earthView.setSmooth(false);
        moonView.setSmooth(false);
        earthView.setScaleX(1.5);
        earthView.setScaleY(1.5);
        moonView.setFitHeight(50);
        moonView.setFitWidth(50);
        earthView.setX(xc - earth.getWidth() / 2 + radius);
        earthView.setY(xc - earth.getWidth() / 2 + radius * 0);
        pradinis_p = new Point((int)earthView.getX(), (int)earthView.getY());
        moonView.setX(earthView.getX());
        moonView.setY(earthView.getY());
        final double e_radius = earthView.getY() - moonView.getY();
        timer = new AnimationTimer() {
            @Override
            public void handle(long now) {
                double t = 0;
                double tm = 0;
                if(FirsTime) {
                    t = (now - startNanoTime) / 1_000_000_000.0;
                }
                if((int)earthView.getX() == pradinis_p.x - 1)
                {
                    //System.out.println("Zeme apsisuka aplink saule per" + String.format("%.2f", t) + "sekundziu");
                }
                aTimer = now - startNanoTime;
                earthView.setX(xc - earth.getWidth() / 2 + radius * Math.cos(t + 0.1));
                earthView.setY(yc - earth.getWidth() / 2 + radius * Math.sin(t + 0.1));
                moonView.setX(earthView.getX() - 70 * Math.cos(t));
                moonView.setY(earthView.getY() + 70 * Math.sin(t));


            }
        };
    }

    // paaiškinkite kintamojo t skaičiavime naudojamą konstantą
    // paskaičiuokite per kiek laiko apskrieja žemė aplink saulę
    // sukurkite start - stopinį mygtuką, kuris aktyvuotų ir stabdytų žemę
    // papildykite erdvę kitais kosminiais ar fantastiniais kūnais
    // 
    //*****************************************
    final private EventHandler delete = e->{

    };
    @Override
    public void createControls(){
        Button buto = addButton("clear",    e -> {nodes.clear(); eh = delete;});
        addButton("Rects",    e -> createRects());
        addButton("Circles",  e -> createCircles());
        addButton("reverse1", e -> fromEndToBegin());
        addButton("clearFirst", e -> {if(nodes.size()>0)
                                        nodes.remove(0);});
        addButton("clearLast",  e -> {if(nodes.size()>0)
                                        nodes.remove(nodes.size()-1);});
        addButton("Sun_System", e -> createSpace());
        addButton("start", e ->{
            Button button = (Button)e.getSource();
            switch (button.getText())
            {
                case "start":
                {
                    if(!FirsTime) {
                        FirsTime = true;
                        startNanoTime = System.nanoTime();
                    }
                    timer.start();
                    button.setText("stop");
                    System.out.println("Start");
                    break;
                }
                case "stop":
                {
                    timer.stop();
                    button.setText("start");
                    System.out.println("Stop");
                    break;
                }
            }
        });
//        addNewHBox();
    }
    @Override
    public void start(Stage stage) throws Exception {
        setCanvas(Color.CYAN.brighter(), space.getWidth() + 100, space.getHeight() + 100);
        super.start(stage);
        stage.setTitle("Prioritetų ir Images Demo ");
        baseGrid();
    } 
    
    public static void main(String[] args) {
        launch(args);
    }    
} // *********************** Demo klasės pabaiga