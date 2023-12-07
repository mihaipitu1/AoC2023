package strategy.concreteStrategy;

import strategy.ProblemSolvingStrategy;

import java.util.*;

public class Day7Strategy implements ProblemSolvingStrategy {
    @Override
    public List<String> solveProblem(List<String> inputData) {

        List<String> solution = new ArrayList<>();
        Long part1Score = 0l;
        Long part2Score = 0l;

        Map<String, Integer> inputMap = new HashMap<>();
        inputData.forEach(entry -> {
            var split = entry.split(" ");
            inputMap.put(split[0], Integer.parseInt(split[1]));
        });

        // Part 1

        Map<Character, Integer> cardScoreMap = getCardScoreMap();

        Map<String, Integer> handScoreMap = getHandScoreMap(inputMap.keySet().stream().toList());

        Map<Integer, List<String>> groupedByHandScore = getCategorisedData(handScoreMap);

        List<Integer> scores = solveTheProblem(groupedByHandScore, cardScoreMap, inputMap);

        part1Score = (long) scores.stream().reduce(0, Integer::sum);

        // Part 2

        Map<String, Integer> handScoreWithJokerMap = getHandScoreWithJokerMap(inputMap.keySet().stream().toList());
        Map<Integer, List<String>> groupedByHandJokerScore = getCategorisedData(handScoreWithJokerMap);

        List<Integer> scoresWithJoker = solveTheProblem(groupedByHandJokerScore, cardScoreMap, inputMap);

        part2Score = (long) scoresWithJoker.stream().reduce(0, Integer::sum);

        solution.add(part1Score.toString());
        solution.add(part2Score.toString());
        return solution;
    }

    private Map<Character, Integer> getCardScoreMap() {
        Map<Character, Integer> scoreMap = new HashMap<>();
        for (int i = 2; i < 10; i++) {
            scoreMap.put((char) (i + '0'), i);
        }
        scoreMap.put('T', 10);
        scoreMap.put('J', 11);
        scoreMap.put('Q', 12);
        scoreMap.put('K', 13);
        scoreMap.put('A', 14);

        return scoreMap;
    }

    private Map<String, Integer> getHandScoreMap(List<String> hands) {
        Map<String, Integer> handScoreMap = new HashMap<>();

        for (String hand : hands) {
            Map<Character, Integer> cardCount = new HashMap<>();
            for (Character ch : hand.toCharArray()) {
                if (!cardCount.containsKey(ch)) {
                    cardCount.put(ch, 1);
                } else {
                    cardCount.put(ch, cardCount.get(ch) + 1);
                }
            }

            int cardNumberMax = cardCount.values().stream().max(Integer::compare).get();
            switch (cardCount.keySet().size()) {
                case 5:
                    handScoreMap.put(hand, 1);
                    break;
                case 4:
                    handScoreMap.put(hand, 2);
                    break;
                case 3:
                    if (cardNumberMax == 2)
                        handScoreMap.put(hand, 3);
                    else
                        handScoreMap.put(hand, 4);
                    break;
                case 2:
                    if (cardNumberMax == 3)
                        handScoreMap.put(hand, 5);
                    else
                        handScoreMap.put(hand, 6);
                    break;
                case 1:
                    handScoreMap.put(hand, 7);
                    break;
            }
        }

        return handScoreMap;
    }

    private Map<String, Integer> getHandScoreWithJokerMap(List<String> hands) {
        Map<String, Integer> handScoreMap = new HashMap<>();

        for (String hand : hands) {
            Map<Character, Integer> cardCount = new HashMap<>();
            for (Character ch : hand.toCharArray()) {
                if (!cardCount.containsKey(ch)) {
                    cardCount.put(ch, 1);
                } else {
                    cardCount.put(ch, cardCount.get(ch) + 1);
                }
            }

            int cardNumberMax = 0;

            if (cardCount.containsKey('J')) {
                int jokerCount = cardCount.get('J');
                if (jokerCount != 5) {
                    Map<Character, Integer> handCard = new HashMap<>();
                    cardCount.keySet().stream().filter(ch -> !ch.equals('J'))
                            .forEach(ch -> handCard.put(ch, cardCount.get(ch)));
                    cardNumberMax = jokerCount + handCard.values().stream().max(Integer::compareTo).get();
                } else {
                    cardNumberMax = jokerCount;
                }
            } else {
                cardNumberMax = cardCount.values().stream().max(Integer::compare).get();
            }

            switch (cardNumberMax) {
                case 5:
                    handScoreMap.put(hand, 7);
                    break;
                case 4:
                    handScoreMap.put(hand, 6);
                    break;
                case 3:
                    if (cardCount.keySet().size() == 2)
                        handScoreMap.put(hand, 5);
                    else
                        handScoreMap.put(hand, 4);
                    break;
                case 2:
                    if (cardCount.keySet().size() == 5)
                        handScoreMap.put(hand, 2);
                    else
                        handScoreMap.put(hand, 3);
                    break;
                case 1:
                    handScoreMap.put(hand, 1);
                    break;
            }
        }

        return handScoreMap;
    }

    private Map<Integer, List<String>> getCategorisedData(Map<String, Integer> handScoreMap) {

        Map<Integer, List<String>> groupedByHandScore = new HashMap<>();

        for (String hand : handScoreMap.keySet()) {
            Integer score = handScoreMap.get(hand);
            if (!groupedByHandScore.containsKey(score)) {
                List<String> valueList = new ArrayList<>();
                valueList.add(hand);
                groupedByHandScore.put(score, valueList);
            } else {
                List<String> valueList = groupedByHandScore.get(score);
                valueList.add(hand);
                groupedByHandScore.put(score, valueList);
            }
        }
        return groupedByHandScore;
    }

    private List<Integer> solveTheProblem(Map<Integer, List<String>> groupedByHandScore, Map<Character, Integer> cardScoreMap, Map<String, Integer> inputMap) {
        List<String> sortedList = new ArrayList<>();
        for (Integer key : groupedByHandScore.keySet()) {
            List<String> valueSet = groupedByHandScore.get(key);
            List<String> sortedValueList = valueSet.stream().sorted(new Comparator<String>() {
                @Override
                public int compare(String o1, String o2) {
                    if (o1.equals(o2)) {
                        return 0;
                    } else {
                        for (int i = 0; i < o1.length(); i++) {
                            int comparedValue = cardScoreMap.get(o1.charAt(i)).compareTo(cardScoreMap.get(o2.charAt(i)));
                            if (comparedValue == 0)
                                continue;
                            else
                                return comparedValue;
                        }
                        return 0;
                    }
                }
            }).toList();

            sortedList.addAll(sortedValueList);
        }

        return sortedList.stream()
                .map(element -> (sortedList.indexOf(element) + 1) * inputMap.get(element))
                .toList();
    }
}
