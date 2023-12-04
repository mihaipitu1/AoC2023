package strategy.concreteStrategy;

import strategy.ProblemSolvingStrategy;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Day3Strategy implements ProblemSolvingStrategy {

    private char[][] inputMap;
    @Override
    public List<String> solveProblem(List<String> inputData) {
        List<String> solution = new ArrayList<>();
        loadMap(inputData);

        Pattern digitPattern = Pattern.compile("\\d+");
        Integer part1Value = 0;
        for (String str : inputData) {
            Matcher matchedValues = digitPattern.matcher(str);
            while (matchedValues.find()) {
                int lineIndex = inputData.indexOf(str);
                int colStart = matchedValues.start();
                int colEnd = matchedValues.end();
                Integer foundNumber = Integer.parseInt(matchedValues.group());
                if (checkForSymbol(lineIndex, colStart, colEnd)) {
                    part1Value += foundNumber;
                }
            }
        }
        solution.add(part1Value.toString());

        Integer part2Value = 0;

        Map<Point, List<Integer>> gearMap = new HashMap<>();
        for (String str : inputData) {
            Matcher matchedValues = digitPattern.matcher(str);
            while (matchedValues.find()) {
                int lineIndex = inputData.indexOf(str);
                int colStart = matchedValues.start();
                int colEnd = matchedValues.end();
                Integer foundNumber = Integer.parseInt(matchedValues.group());
                Point symbolPosition = getSymbolPoint(lineIndex, colStart, colEnd);
                if (symbolPosition.x != -1 && symbolPosition.y != -1) {
                    if (inputMap[symbolPosition.x][symbolPosition.y] == '*') {
                        boolean added = false;
                        for (Point g : gearMap.keySet()) {
                            if (g.x == symbolPosition.x && g.y == symbolPosition.y) {
                                added = true;
                                List<Integer> addedList = gearMap.get(g);
                                addedList.add(foundNumber);
                                gearMap.put(g, addedList);
                            }
                        }
                        if (!added) {
                            gearMap.put(symbolPosition, new ArrayList<>());
                            gearMap.get(symbolPosition).add(foundNumber);
                        }
                    }
                }
            }
        }

        part2Value = gearMap.values().stream()
                .filter(list -> list.size() == 2)
                .map(list -> list.get(0) * list.get(1))
                .reduce(0, Integer::sum);

        solution.add(part2Value.toString());

        return solution;
    }

    private void loadMap(List<String> strings) {
        inputMap = new char[strings.size()][strings.get(0).length()];

        for (String str : strings) {
            inputMap[strings.indexOf(str)] = str.toCharArray();
        }
    }

    private boolean isSymbol(char ch) {
        if (ch == '.') return false;
        return !Character.isDigit(ch);
    }

    private boolean checkForSymbol(int lineNo, int colStart, int colEnd) {

        if (lineNo - 1 >= 0) {
            for (int i = colStart - 1; i <= colEnd; i++) {
                if (i >= 0 && i <= 139) {
                    if (isSymbol(inputMap[lineNo - 1][i])) {
                        return true;
                    }
                }
            }
        }
        if (lineNo + 1 < 140) {
            for (int i = colStart - 1; i <= colEnd; i++) {
                if (i >= 0 && i <= 139) {
                    if (isSymbol(inputMap[lineNo + 1][i])) {
                        return true;
                    }
                }
            }
        }
        if (colStart - 1 >= 0) {
            if (isSymbol(inputMap[lineNo][colStart - 1])) {
                return true;
            }
        }
        if (colEnd + 1 < 140) {
            if (isSymbol(inputMap[lineNo][colEnd])) {
                return true;
            }
        }
        return false;
    }

    private Point getSymbolPoint(int lineNo, int colStart, int colEnd) {

        if (lineNo - 1 >= 0) {
            for (int i = colStart - 1; i <= colEnd; i++) {
                if (i >= 0 && i <= 139) {
                    if (isSymbol(inputMap[lineNo - 1][i])) {
                        return new Point(lineNo - 1, i);
                    }
                }
            }
        }

        if (lineNo + 1 < 140) {
            for (int i = colStart - 1; i <= colEnd; i++) {
                if (i >= 0 && i <= 139) {
                    if (isSymbol(inputMap[lineNo + 1][i])) {
                        return new Point(lineNo + 1, i);
                    }
                }
            }
        }

        if (colStart - 1 >= 0) {
            if (isSymbol(inputMap[lineNo][colStart - 1])) {
                return new Point(lineNo, colStart - 1);
            }
        }

        if (colEnd + 1 < 140) {
            if (isSymbol(inputMap[lineNo][colEnd])) {
                return new Point(lineNo, colEnd);
            }
        }

        return new Point(-1, -1);
    }


    private class Point {
        private int x;
        private int y;

        Point(int x, int y) {
            this.x = x;
            this.y = y;
        }

        int getX() {
            return this.x;
        }

        int getY() {
            return this.y;
        }
    }
}
