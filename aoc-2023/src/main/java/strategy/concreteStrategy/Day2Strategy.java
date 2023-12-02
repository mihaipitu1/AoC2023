package strategy.concreteStrategy;

import strategy.ProblemSolvingStrategy;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Day2Strategy implements ProblemSolvingStrategy {
    @Override
    public List<String> solveProblem(List<String> inputData) {
        List<String> solution = new ArrayList<>();

        List<Integer> sumValues = new ArrayList<>();
        for (String str : inputData) {
            String[] gameData = str.split(": ");
            var strGameId = gameData[0].split(" ");
            Integer gameID = Integer.valueOf(strGameId[1]);

            String gameSet = gameData[1];
            boolean gameFlag = true;
            for (String game : gameSet.split("; ")) {
                Map<String, Integer> internalScoreMap = loadInternalMap();
                for (String gameInput : game.split(", ")) {
                    String[] rawCube = gameInput.split(" ");
                    internalScoreMap.put(rawCube[1], internalScoreMap.get(rawCube[1]) + Integer.parseInt(rawCube[0]));
                }
                if (!checkMap(internalScoreMap)) {
                    gameFlag = false;
                    break;
                }
            }
            if (gameFlag) {
                sumValues.add(gameID);
            }
        }
        Integer sum = sumValues.stream().reduce(0, Integer::sum);
        solution.add(sum.toString());

        List<Integer> powerValues = new ArrayList<>();
        for (String str : inputData) {
            String[] gameData = str.split(": ");

            String gameSet = gameData[1];
            Map<String, Integer> internalScoreMap = loadInternalMap();
            for (String game : gameSet.split("; ")) {

                for (String gameInput : game.split(", ")) {
                    String[] rawCube = gameInput.split(" ");
                    Integer cubeValue = Integer.valueOf(rawCube[0]);
                    if (internalScoreMap.get(rawCube[1]) < cubeValue) {
                        internalScoreMap.put(rawCube[1], cubeValue);
                    }
                }
            }

            var product = internalScoreMap.values().stream().reduce(1, (a, b) -> a * b);
            powerValues.add(product);
        }

        solution.add(powerValues.stream().reduce(0, Integer::sum).toString());
        return solution;
    }

    private Map<String, Integer> loadInternalMap() {
        Map<String, Integer> internalScoreMap = new HashMap<>();
        internalScoreMap.put("red", 0);
        internalScoreMap.put("blue", 0);
        internalScoreMap.put("green", 0);
        return internalScoreMap;
    }

    private Boolean checkMap(Map<String, Integer> internalMap) {
        if (internalMap.get("red") > 12) {
            return false;
        }
        if (internalMap.get("green") > 13) {
            return false;
        }
        if (internalMap.get("blue") > 14) {
            return false;
        }
        return true;
    }

}
