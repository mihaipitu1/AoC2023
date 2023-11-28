import strategy.SolutionHandler;
import utils.FileHandler;

import java.util.List;
import java.util.Scanner;

public class MainApplication {

    public static void main(String[] args) {
        System.out.println("Enter your input day:");
        Scanner scanner = new Scanner(System.in);
        String inputValue = scanner.next();
        System.out.println("Today is day " + inputValue);

        List<String> inputData = FileHandler.getInputParameters(inputValue);

        var dayNo = Integer.valueOf(inputValue);

        List<String> outputResult = SolutionHandler.getSolution(dayNo, inputData);

        if(outputResult.size() == 2) {
            System.out.println("Part 1: " + outputResult.get(0));
            System.out.println("Part 2: " + outputResult.get(1));
        } else {
            System.out.println("ERROR: " + outputResult.get(0));
        }
    }
}
