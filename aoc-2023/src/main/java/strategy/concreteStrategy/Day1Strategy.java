package strategy.concreteStrategy;

import strategy.ProblemSolvingStrategy;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Day1Strategy implements ProblemSolvingStrategy {

    private Map<String, Integer> inputMap = new HashMap<>();

    @Override
    public List<String> solveProblem(List<String> inputData) {
        loadStringMap();
        List<String> solution = new ArrayList<>();
        List<Integer> intValues = new ArrayList<>();
        inputData.forEach(str -> {
            char[] charSeq = str.toCharArray();
            List<Integer> digitChars = new ArrayList<>();
            for(char ch: charSeq) {
                if(Character.isDigit(ch)) {
                    digitChars.add(Character.getNumericValue(ch));
                }
            }

            intValues.add(getNumber(digitChars));
        });

        var part1 = intValues.stream().reduce(0, Integer::sum).toString();
        solution.add(part1);


        List<Integer> codeValues = new ArrayList<>();
        inputData.forEach(str ->{
            char[] charSeq = str.toCharArray();
            List<Integer> digitChars = new ArrayList<>();
            for(int i=0; i<charSeq.length; i++) {
                var ch = charSeq[i];
                if(Character.isDigit(ch)) {
                    digitChars.add(Character.getNumericValue(ch));
                } else {
                    var inputKeys = inputMap.keySet();
                    for(String s: inputKeys) {
                        if(ch == s.charAt(0) && (str.length() >= i + s.length())) {
                            String filteredString = str.substring(i, i + s.length());
                            if(inputKeys.contains(filteredString) && filteredString.equals(s)) {
                                digitChars.add(inputMap.get(filteredString));
                            }
                        }
                    }
                }
            }
            int extractedNumber = getNumber(digitChars);
            codeValues.add(extractedNumber);
        });

        var part2 = codeValues.stream().reduce(0, Integer::sum).toString();
        solution.add(part2);
        return solution;
    }

    private int getNumber(List<Integer> characters) {

        int len = characters.size();

        switch(len) {
            case 0 : return 0;
            case 1: return 11 * characters.get(0);
            default: return 10 * characters.get(0) + characters.get(len-1);
        }
    }

    private void loadStringMap() {
        inputMap.put("one",1);
        inputMap.put("two",2);
        inputMap.put("three",3);
        inputMap.put("four",4);
        inputMap.put("five",5);
        inputMap.put("six",6);
        inputMap.put("seven",7);
        inputMap.put("eight",8);
        inputMap.put("nine",9);
    }
}
