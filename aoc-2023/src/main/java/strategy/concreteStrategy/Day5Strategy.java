package strategy.concreteStrategy;

import strategy.ProblemSolvingStrategy;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Day5Strategy implements ProblemSolvingStrategy {

    @Override
    public List<String> solveProblem(List<String> inputData) {
        List<String> solution = new ArrayList<>();

        List<Long> seedsList = Arrays.stream(inputData.get(0).split(":")[1].split(" ")).toList()
                .stream().filter(str -> str.length() > 0).map(string -> Long.parseLong(string)).toList();


        List<List<PointMap>> mapList = new ArrayList<>();
        int i = 3;
        List<PointMap> mapElement = new ArrayList<>();
        while (i < inputData.size()) {
            if (inputData.get(i).length() == 0) {
                i += 1;
            } else {
                if (Character.isDigit(inputData.get(i).toCharArray()[0])) {
                    String[] coordinates = inputData.get(i).split(" ");
                    mapElement.add(new PointMap(Long.parseLong(coordinates[0]), Long.parseLong(coordinates[1]), Long.parseLong(coordinates[2])));
                } else {
                    mapList.add(mapElement);
                    mapElement = new ArrayList<>();
                }
                i += 1;
                if (i == inputData.size()) {
                    mapList.add(mapElement);
                }
            }
        }

        // Part1
        Long part1Score = 0l;

        List<Long> locationList = getLocation(seedsList, mapList);

        part1Score = locationList.stream().min(Long::compareTo).get();


        // Part 2
        Long part2Score = 0l;
        boolean flag = false;
        while (!flag) {
            Long current = part2Score;
            for (i = mapList.size() - 1; i >= 0; i--) {
                List<PointMap> elementMap = mapList.get(i);
                boolean found = false;
                int index = -1;
                Long difference = -1l;
                for (int k = 0; k < elementMap.size() && !found; k++) {
                    difference = current - elementMap.get(k).destination;
                    if (difference >= 0 && difference < elementMap.get(k).range) {
                        found = true;
                        index = k;
                    }
                }
                if (found) {
                    current = elementMap.get(index).source + difference;
                }
            }
            for (i = 0; i < seedsList.size() && !flag; i += 2) {
                Long diff = current - seedsList.get(i);
                if (diff >= 0 && diff < seedsList.get(i + 1)) {
                    flag = true;
                }
            }
            if (!flag) {
                part2Score += 1;
            }
        }
        part2Score = locationList.stream().min(Long::compareTo).get();

        solution.add(part1Score.toString());
        solution.add(part2Score.toString());
        return solution;
    }

    private class PointMap {
        Long destination;
        Long source;
        Long range;

        PointMap(Long destination, Long source, Long range) {
            this.destination = destination;
            this.source = source;
            this.range = range;
        }

        private Long getDestination() {
            return this.destination;
        }
    }

    private List<Long> getLocation(List<Long> seedsList, List<List<PointMap>> mapList) {
        List<Long> locationList = new ArrayList<>();
        for (Long seed : seedsList) {
            Long current = seed;
            for (List<PointMap> map : mapList) {
                for (PointMap point : map) {
                    Long diff = current - point.source;
                    if (diff < point.range && diff >= 0) {
                        current = point.destination + diff;
                        break;
                    }
                }
            }
            locationList.add(current);
        }
        return locationList;
    }

    private List<Long> getIntersectionRanges(List<Long> seedsList) {
        List<Long> intersection = new ArrayList<>();
        intersection.set(0, seedsList.get(0));
        intersection.set(1, seedsList.get(0) + seedsList.get(1) - 1);
        for (int k = 2; k < seedsList.size(); k += 2) {

        }
        return intersection;
    }
}
