package strategy.concreteStrategy;

import strategy.ProblemSolvingStrategy;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Day6Strategy implements ProblemSolvingStrategy {
    @Override
    public List<String> solveProblem(List<String> inputData) {
        List<String> solution = new ArrayList<>();
        Integer part1Score = 0;
        Long part2Score = 0l;

        List<Integer> times = Arrays.stream(inputData.get(0).split(":")[1].split(" "))
                .filter(str -> !str.isEmpty()).map(str -> Integer.parseInt(str))
                .toList();
        List<Integer> distances = Arrays.stream(inputData.get(1).split(":")[1].split(" "))
                .filter(str -> !str.isEmpty()).map(str -> Integer.parseInt(str))
                .toList();
        // Part 1

        List<Integer> winningWays = new ArrayList<>();
        for (int i = 0; i < times.size(); i++) {
            int count = 0;
            for (int k = 1; k < times.get(i) - 1; k++) {
                if (k * (times.get(i) - k) > distances.get(i)) {
                    count++;
                }
            }
            winningWays.add(count);
        }
        part1Score = winningWays.stream().reduce(1, (a, b) -> a * b);

        // Part 2

        Long concatenatedTime = Long.parseLong(times.stream()
                .map(no -> no.toString())
                .collect(Collectors.joining()));
        Long concatenatedDistance = Long.parseLong(distances.stream()
                .map(no -> no.toString())
                .collect(Collectors.joining()));

        Long secondCount = 0l;
        for (long i = 1l; i < concatenatedTime - 1; i++) {
            if (i * (concatenatedTime - i) > concatenatedDistance)
                secondCount++;
        }
        part2Score = secondCount;

        solution.add(part1Score.toString());
        solution.add(part2Score.toString());

        return solution;
    }
}
