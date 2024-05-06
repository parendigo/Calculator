package calculator;

import calculator.model.AncientRomeModel;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class App {
    public static void main(String[] args) throws Exception {
        /* Инициализация калькулятора на Swing. */

        View view = new View();
        Controller controller = new Controller(view);
        view.setController(controller);
        view.init();

        /* Раскомментируйте блок ниже, чтобы получить консольную версию Римского калькулятора для задания. */

/*        AncientRomeModel ancientRomeModel = new AncientRomeModel();
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("Ancient calculator with Rome numbers.");
        System.out.println("=====================================");
        System.out.println("Usage: 1 <= number <= 10.");
        System.out.println("Available Rome numbers: [I,V,X]");
        System.out.println("Format: [number [*+/-] number]");
        System.out.println("=====================================");
        String text;
        while (!(text = reader.readLine()).equalsIgnoreCase("exit")){
            System.out.println(ancientRomeModel.computeToString(text));
        }*/
    }
}