package strategy.concreteStrategy;

import strategy.ProblemSolvingStrategy;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

public class Day4Strategy implements ProblemSolvingStrategy {
    @Override
    public List<String> solveProblem(List<String> inputData) {
        List<String> solution = new ArrayList<>();

        Integer part1Score = 0;
        for (String scratchCard : inputData) {
            String[] lineData = scratchCard.split(":");
            String[] cardData = lineData[1].split("\\|");

            var winningNumbers = Arrays.stream(cardData[0].split(" "))
                    .map(this::parseNumber)
                    .toList();
            var playNumbers = Arrays.stream(cardData[1].split(" "))
                    .map(this::parseNumber)
                    .toList();

            AtomicInteger matched = new AtomicInteger();
            winningNumbers.forEach(number -> {
                if (playNumbers.contains(number) && number != 0) {
                    matched.addAndGet(1);
                }
            });

            if (matched.get() > 0) part1Score += (int) Math.pow(2, matched.get() - 1);
        }

        solution.add(part1Score.toString());

        // Part 2

        Integer part2Score = 0;
        var cardList = getInitialCardNumbers();
        for (int i = 0; i < inputData.size(); i++) {
            String[] lineData = inputData.get(i).split(":");
            String[] cardData = lineData[1].split("\\|");

            var winningNumbers = Arrays.stream(cardData[0].split(" "))
                    .map(this::parseNumber)
                    .toList();
            var playNumbers = Arrays.stream(cardData[1].split(" "))
                    .map(this::parseNumber)
                    .toList();

            AtomicInteger matched = new AtomicInteger();
            winningNumbers.forEach(number -> {
                if (playNumbers.contains(number) && number != 0) {
                    matched.addAndGet(1);
                }
            });

            if (matched.get() > 0) {
                for (int j = 1; j <= matched.get(); j++) {
                    cardList[i + j] += cardList[i];
                }
            }
        }

        part2Score = Arrays.stream(cardList).reduce(0, Integer::sum);
        solution.add(part2Score.toString());
        return solution;
    }

    private Integer parseNumber(String number) {
        return number == "" ? 0 : Integer.parseInt(number);
    }

    private Integer[] getInitialCardNumbers() {
        Integer[] cardList = new Integer[199];
        for (int i = 0; i < 199; i++) {
            cardList[i] = 1;
        }
        return cardList;
    }
}
