package strategy.concreteStrategy;

import strategy.ProblemSolvingStrategy;

import java.util.ArrayList;
import java.util.List;

public class Day8Strategy implements ProblemSolvingStrategy {
    @Override
    public List<String> solveProblem(List<String> inputData) {
        List<String> solution = new ArrayList<>();

        Integer part1Score = 0;
        Long part2Score = 0l;

        List<Node> nodeList = new ArrayList<>();

        char[] moveList = inputData.get(0).toCharArray();

        for (int i = 2; i < inputData.size(); i++) {
            String nodeInput = inputData.get(i);
            String[] splitData = nodeInput.split(" = ");
            String[] leftRightNodes = splitData[1].split(", ");
            String left = leftRightNodes[0].replace("(", "");
            String right = leftRightNodes[1].replace(")", "");
            nodeList.add(new Node(splitData[0], left, right));
        }

        Node startNode = nodeList.stream().filter(node -> node.current.equals("AAA")).findFirst().orElse(new Node("", "", ""));
        int moveIndex = 0;
        int moveNo = 0;

        while (!startNode.current.equals("ZZZ")) {
            if (moveIndex == moveList.length) {
                moveIndex = 0;
            }
            char ch = moveList[moveIndex];
            String nextNode;
            switch (ch) {
                case 'L':
                    nextNode = startNode.left;
                    break;
                case 'R':
                    nextNode = startNode.right;
                    break;
                default:
                    nextNode = "invalid";
                    break;
            }
            if (!nextNode.equals("invalid")) {
                startNode = nodeList.stream().filter(node -> node.current.equals(nextNode)).findFirst().orElse(new Node("", "", ""));
            }
            moveNo++;
            moveIndex++;
        }

        part1Score = moveNo;

        // Part 2

        List<Node> startNodes = new ArrayList<>();
        startNodes = nodeList.stream().filter(node -> node.current.charAt(2) == 'A').toList();

        List<Long> noMoves = new ArrayList<>();
        for (Node start : startNodes) {
            long movesNo = 0l;
            moveIndex = 0;
            while ((start.current.charAt(2) != 'Z')) {

                if (moveIndex == moveList.length) {
                    moveIndex = 0;
                }

                char ch = moveList[moveIndex];
                String nextNode;
                switch (ch) {
                    case 'L':
                        nextNode = start.left;
                        break;
                    case 'R':
                        nextNode = start.right;
                        break;
                    default:
                        nextNode = "invalid";
                        break;
                }
                if (!nextNode.equals("invalid")) {
                    start = nodeList.stream().filter(node -> node.current.equals(nextNode)).findFirst().orElse(new Node("", "", ""));
                }
                movesNo++;
                moveIndex++;
            }
            noMoves.add(movesNo);
        }

        part2Score = noMoves.stream().reduce(1l, (a, b) -> lcm(a, b));

        solution.add(part1Score.toString());
        solution.add(part2Score.toString());
        return solution;
    }

    private Long lcm(Long a, Long b) {
        if (a == 0 || b == 0) {
            return 0l;
        }
        long absNumber1 = Math.abs(a);
        long absNumber2 = Math.abs(b);
        long absHigherNumber = Math.max(absNumber1, absNumber2);
        long absLowerNumber = Math.min(absNumber1, absNumber2);
        long lcm = absHigherNumber;
        while (lcm % absLowerNumber != 0) {
            lcm += absHigherNumber;
        }
        return lcm;
    }

    private class Node {
        String current;
        String left;
        String right;

        Node(String current, String left, String right) {
            this.current = current;
            this.left = left;
            this.right = right;
        }
    }
}
