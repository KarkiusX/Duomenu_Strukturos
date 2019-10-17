/****************************************************************
 * Tai įvadinė klasė žengiant į įvykiais valdomą programavimą.
 * Event-driven programming: tai plati tema- pradedame nuo paprastų pavyzdžių
 * DAUG KLAUSIMŲ APIE JAVĄ GALIMA IŠSIAIŠKINTI, ATLIEKANT
 * SAVARANKIŠKUS BANDYMUS, TYRIMUS IR ANALIZĘ :)
 * 
 * Visos demonstracinės klasės turi main metodą ir vykdomos Run File (Shift+F6)
 * 
 * Pradžioje vykdykite kodą ir stebėkite atliekamus veiksmus
 * Užduotis: Peržiūrėkite ir išsiaiškinkite pateiktas klases,
 * pakeiskite programinį kodą, stebėkite gautus rezultatus.
 * Gynimo metu atlikite dėstytojo nurodytas užduotis naujų metodų pagalba.
 *
 * @author Eimutis Karčiauskas, KTU programų inžinerijos katedra 2019 08 05
 **************************************************************************/
package demos.console;

import extendsFX.BaseConsole;
import javafx.stage.Stage;

public class Demo0_Hello extends BaseConsole{
    int clicks = 0;
    // demo pavyzdys: skaičiuojame x^2, x^3 ir fact(x)
    // nustatykite nuo kurios x reikšmės faktorialas blogai skaičiuojamas
    public void calculate(){
        int x = readLastInt();
        int fact = 1;
        for(int k = 1; k <= x; k++)
            fact *= k;
        print(" x="+ x +" x^2="+ x*x +" x^3="+ x*x*x);
        printLn(" fact=" + fact);
    }
    public void CalculateSin()
    {
        int x = readLastInt();

        double sin = Math.sin(Math.toRadians(x));

        print("Sin:" + sin);
    }
    public void CalculateCos()
    {
        double x = Math.toRadians(readLastInt());

        double cos = Math.cos(x);

        print("Cos" + cos);
    }
    public void CalculateTan()
    {
        int x = readLastInt();

        if(x == 90)
        {
            String tan = null;
            printLn("Tan" + tan);
        }
        else {
            double tan = Math.tan(Math.toRadians(x));
            print("Tan" + tan);
        }

    }
// UŽDUOTIS: sudarykite naują metodą funkcijų sin, cos ir tan skaičiavimui, 
//           kai duotas kampas yra nurodomas laipsniais
    @Override
    public void createControls() {
        super.createControls();    // sukuriame bazinius mygtukus
        addButton("Hello1", e -> ta1.appendText("Hello World!!! " +nL));
        addButton("Hello2", e -> ta2.appendText("Hello World!!! " 
                                 + "I can count clicks: " + ++clicks +nL));
        addButton("calc",   e -> calculate());
        addButton("calc_sin", e->CalculateSin());
        addButton("calc_cos", e->CalculateCos());
        addButton("calc_tag", e->CalculateTan());
    }
    @Override
    public void start(Stage stage) throws Exception {
        stage.setTitle("Hello World (KTU IF)");
//         išbandykite skirtingus ekranų nustatytumus 
//        setTextAreas("green", "red", 200, 400);
//        setTextAreas("green", null, 300, 400);
//        setTextAreas(null, "red", 350, 400);
        super.start(stage);  
    }
    public static void main(String[] args) {
        launch();
    }
}