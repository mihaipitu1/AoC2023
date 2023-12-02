package strategy;

import strategy.concreteStrategy.*;

import java.util.ArrayList;
import java.util.List;

public class SolutionHandler {

    public static List<String> getSolution(int dayNo, List<String> inputData) {
        List<String> solution = new ArrayList<>();

        switch(dayNo) {
            case 1:
                solution.addAll(new DayProblem(new Day1Strategy()).getSolution(inputData));
                break;
            case 2:
                solution.addAll(new DayProblem(new Day2Strategy()).getSolution(inputData));
                break;
            case 3:
                solution.addAll(new DayProblem(new Day3Strategy()).getSolution(inputData));
                break;
            case 4:
                solution.addAll(new DayProblem(new Day4Strategy()).getSolution(inputData));
                break;
            case 5:
                solution.addAll(new DayProblem(new Day5Strategy()).getSolution(inputData));
                break;
            case 6:
                solution.addAll(new DayProblem(new Day6Strategy()).getSolution(inputData));
                break;
            case 7:
                solution.addAll(new DayProblem(new Day7Strategy()).getSolution(inputData));
                break;
            case 8:
                solution.addAll(new DayProblem(new Day8Strategy()).getSolution(inputData));
                break;
            case 9:
                solution.addAll(new DayProblem(new Day9Strategy()).getSolution(inputData));
                break;
            case 10:
                solution.addAll(new DayProblem(new Day10Strategy()).getSolution(inputData));
                break;
            case 11:
                solution.addAll(new DayProblem(new Day11Strategy()).getSolution(inputData));
                break;
            case 12:
                solution.addAll(new DayProblem(new Day12Strategy()).getSolution(inputData));
                break;
            case 13:
                solution.addAll(new DayProblem(new Day13Strategy()).getSolution(inputData));
                break;
            case 14:
                solution.addAll(new DayProblem(new Day14Strategy()).getSolution(inputData));
                break;
            case 15:
                solution.addAll(new DayProblem(new Day15Strategy()).getSolution(inputData));
                break;
            case 16:
                solution.addAll(new DayProblem(new Day16Strategy()).getSolution(inputData));
                break;
            case 17:
                solution.addAll(new DayProblem(new Day17Strategy()).getSolution(inputData));
                break;
            case 18:
                solution.addAll(new DayProblem(new Day18Strategy()).getSolution(inputData));
                break;
            case 19:
                solution.addAll(new DayProblem(new Day19Strategy()).getSolution(inputData));
                break;
            case 20:
                solution.addAll(new DayProblem(new Day20Strategy()).getSolution(inputData));
                break;
            case 21:
                solution.addAll(new DayProblem(new Day21Strategy()).getSolution(inputData));
                break;
            case 22:
                solution.addAll(new DayProblem(new Day22Strategy()).getSolution(inputData));
                break;
            case 23:
                solution.addAll(new DayProblem(new Day23Strategy()).getSolution(inputData));
                break;
            case 24:
                solution.addAll(new DayProblem(new Day24Strategy()).getSolution(inputData));
                break;
            case 25:
                solution.addAll(new DayProblem(new Day25Strategy()).getSolution(inputData));
                break;
            default: solution.add("Invalid input.");
        }

        return solution;
    }
}
