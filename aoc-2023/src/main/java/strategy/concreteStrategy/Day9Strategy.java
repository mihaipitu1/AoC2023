package strategy.concreteStrategy;

import strategy.ProblemSolvingStrategy;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Day9Strategy implements ProblemSolvingStrategy {
    @Override
    public List<String> solveProblem(List<String> inputData) {
        List<String> solution = new ArrayList<>();
        Long part1Score = 0l;
        Long part2Score = 0l;

        List<List<Long>> parsedInputData = new ArrayList<>();
        for (String input : inputData) {
            List<Long> parsedNumbers = new ArrayList<>();
            String[] splitInput = input.split(" ");
            for (String number : splitInput) {
                parsedNumbers.add(Long.parseLong(number));
            }
            parsedInputData.add(parsedNumbers);
        }

        // Part 1
        List<Long> nextSensorValues = new ArrayList<>();
        for (List<Long> reading : parsedInputData) {
            List<List<Long>> pyramidList = new ArrayList<>();
            List<Long> startList = reading;
            while (!isListAllEquals(startList)) {
                List<Long> diffList = new ArrayList<>();
                for (int i = 0; i < startList.size() - 1; i++) {
                    diffList.add(startList.get(i + 1) - startList.get(i));
                }
                pyramidList.add(diffList);
                startList = diffList;
            }
            long nextSensorRead = pyramidList.stream()
                    .map(el -> el.getLast())
                    .reduce(0l, Long::sum);
            nextSensorValues.add(nextSensorRead + reading.getLast());
        }
        part1Score = nextSensorValues.stream().reduce(0l, Long::sum);

        // Part 2

        List<Long> firstSensorValues = new ArrayList<>();
        for (List<Long> reading : parsedInputData) {
            List<List<Long>> pyramidList = new ArrayList<>();
            List<Long> startList = reading;
            while (!isListAllEquals(startList)) {
                List<Long> diffList = new ArrayList<>();
                for (int i = 0; i < startList.size() - 1; i++) {
                    diffList.add(startList.get(i + 1) - startList.get(i));
                }
                pyramidList.add(diffList);
                startList = diffList;
            }

            List<Long> firstElements = pyramidList.stream()
                    .map(List::getFirst).toList();
            long nextSensorRead = getFirstElement(firstElements);

            firstSensorValues.add(reading.getFirst() - nextSensorRead);
        }

        part2Score = firstSensorValues.stream().reduce(0L, Long::sum);


        solution.add(part1Score.toString());
        solution.add(part2Score.toString());
        return solution;
    }

    private boolean isListAllEquals(List<Long> testList) {
        for (int i = 0; i < testList.size() - 1; i++) {
            if (!Objects.equals(testList.get(i), testList.get(i + 1)))
                return false;
        }
        return true;
    }

    private Long getFirstElement(List<Long> testList) {
        Long number = testList.getLast();
        for (int i = testList.size() - 2; i >= 0; i--) {
            number = (testList.get(i) - number);
        }
        return number;
    }
}
