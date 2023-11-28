package strategy;

import strategy.concreteStrategy.*;

import java.util.ArrayList;
import java.util.List;

public class SolutionHandler {

    public static List<String> getSolution(int dayNo, List<String> inputData) {
        List<String> solution = new ArrayList<>();

        switch(dayNo) {
            case 1: solution.addAll(new DayProblem(new Day1Strategy()).getSolution(inputData));
            case 2: solution.addAll(new DayProblem(new Day2Strategy()).getSolution(inputData));
            case 3: solution.addAll(new DayProblem(new Day3Strategy()).getSolution(inputData));
            case 4: solution.addAll(new DayProblem(new Day4Strategy()).getSolution(inputData));
            case 5: solution.addAll(new DayProblem(new Day5Strategy()).getSolution(inputData));
            case 6: solution.addAll(new DayProblem(new Day6Strategy()).getSolution(inputData));
            case 7: solution.addAll(new DayProblem(new Day7Strategy()).getSolution(inputData));
            case 8: solution.addAll(new DayProblem(new Day8Strategy()).getSolution(inputData));
            case 9: solution.addAll(new DayProblem(new Day9Strategy()).getSolution(inputData));
            case 10: solution.addAll(new DayProblem(new Day10Strategy()).getSolution(inputData));
            case 11: solution.addAll(new DayProblem(new Day11Strategy()).getSolution(inputData));
            case 12: solution.addAll(new DayProblem(new Day12Strategy()).getSolution(inputData));
            case 13: solution.addAll(new DayProblem(new Day13Strategy()).getSolution(inputData));
            case 14: solution.addAll(new DayProblem(new Day14Strategy()).getSolution(inputData));
            case 15: solution.addAll(new DayProblem(new Day15Strategy()).getSolution(inputData));
            case 16: solution.addAll(new DayProblem(new Day16Strategy()).getSolution(inputData));
            case 17: solution.addAll(new DayProblem(new Day17Strategy()).getSolution(inputData));
            case 18: solution.addAll(new DayProblem(new Day18Strategy()).getSolution(inputData));
            case 19: solution.addAll(new DayProblem(new Day19Strategy()).getSolution(inputData));
            case 20: solution.addAll(new DayProblem(new Day20Strategy()).getSolution(inputData));
            case 21: solution.addAll(new DayProblem(new Day21Strategy()).getSolution(inputData));
            case 22: solution.addAll(new DayProblem(new Day22Strategy()).getSolution(inputData));
            case 23: solution.addAll(new DayProblem(new Day23Strategy()).getSolution(inputData));
            case 24: solution.addAll(new DayProblem(new Day24Strategy()).getSolution(inputData));
            case 25: solution.addAll(new DayProblem(new Day25Strategy()).getSolution(inputData));
            default: solution.add("Invalid input.");
        }

        return solution;
    }
}
