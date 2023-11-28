package strategy;

import java.util.List;

public class DayProblem {
    private ProblemSolvingStrategy problemSolvingStrategy;

    public DayProblem(ProblemSolvingStrategy problemSolvingStrategy) {
        this.problemSolvingStrategy = problemSolvingStrategy;
    }

    public List<String> getSolution(List<String> inputData) {
        return problemSolvingStrategy.solveProblem(inputData);
    }
}
