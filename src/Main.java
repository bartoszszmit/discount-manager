import java.util.*;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) {
        List<Item> itemList = new ArrayList<>();
        //itemList.add(new Item(10,"Lodówka_HISENSE", 2899.0, true, false, true, null));
        itemList.add(new Item(15, "Euro_Lodówka_Whirlpool", 2599.0, false, false, true, 350.0));
        itemList.add(new Item(20, "Pralka_AMICA", 1399.0, true, true, false, 107.0));
        itemList.add(new Item(30, "Piekarnik_SHARP", 1599.0, true, false, true, null));
        itemList.add(new Item(35, "Euro_Mikrofalówka_Beko", 379.0, true, false, true, null));
        itemList.add(new Item(40, "Zmywarka_AMICA", 1699.0, true, true, false, null));
        itemList.add(new Item(50, "Płyta_SAMSUNG", 1399.0, true, true, true, 200.0));
//        itemList.add(new Item(60,"Ekspres_DELONGHI", 1599.0, false, true, false, null));
//        itemList.add(new Item(70,"Robot_LENOVO", 1699.0, true, false, true, null));
//        itemList.add(new Item(80,"Okap_AMICA", 1399.0, true, false, true, null));
//        itemList.add(new Item(70,"Robot_PHILIPS_Aqua", 1799.0, true, true, false, null));
        itemList.add(new Item(81, "Okap_BEKO", 499.0, true, false, true, null));
        itemList.add(new Item(90, "Zlewozmywak_Deante", 579.0, true, false, true, 140.0));

        List<Combination> allCombinedItems = getAllCombinations(new ArrayList<>(itemList));

        List<Combination> theBestCombinedItems = allCombinedItems.stream().sorted(Comparator.comparingDouble(Combination::getRabat).reversed()).limit(40).collect(Collectors.toList());
        theBestCombinedItems.forEach(System.out::println);
    }

    public static List<List<Integer>> generateNumberCombinations(int target) {
        List<List<Integer>> result = new ArrayList<>();
        List<Integer> currentCombination = new ArrayList<>();
        generateNumberCombinationsHelper(target, 1, currentCombination, result);
        return result;
    }

    private static void generateNumberCombinationsHelper(int target, int start, List<Integer> currentCombination, List<List<Integer>> result) {
        if (target == 0) {
            result.add(new ArrayList<>(currentCombination));
            return;
        }
        for (int i = start; i <= target; i++) {
            currentCombination.add(i);
            generateNumberCombinationsHelper(target - i, i, currentCombination, result);
            currentCombination.remove(currentCombination.size() - 1);
        }
    }

    public static void combinations(List<Object> values, List<Object> current, Set<Set<Object>> accumulator, int size, int pos) {
        if (current.size() == size) {
            Set<Object> toAdd = current.stream().collect(Collectors.toSet());
            if (accumulator.contains(toAdd)) {
                throw new RuntimeException("Duplicated value " + current);
            }
            accumulator.add(toAdd);
            return;
        }
        for (int i = pos; i <= values.size() - size + current.size(); i++) {
            current.add((Object) values.get(i));
            combinations(values, current, accumulator, size, i + 1);
            current.remove(current.size() - 1);
        }
    }

    public static List<Combination> getAllCombinations(List<Object> list) {
        var numberCombinations = generateNumberCombinations(list.size());
        List<Object> result = new ArrayList<>();
        for (List<Integer> pattern : numberCombinations) {
            System.out.println(pattern);
            Set<Object> wholeRow = generateCombinationsForPattern(list, pattern);
            result.add(wholeRow);
        }
        List<Combination> a = new ArrayList<>();
        result.forEach(wholeCombination -> {
            for (Object combinationSets : (HashSet) wholeCombination) {
                Set<CombinationSet> combSets = new HashSet<>();
                for (Object item : (HashSet) combinationSets) {
                    combSets.add(new CombinationSet((item instanceof Set) ? (HashSet) item : new HashSet<>(Collections.singletonList((Item) item))));
                }
                a.add(new Combination(combSets));
            }
        });
        return a;
    }

    private static Set<Object> generateCombinationsForPattern(List<Object> all, List<Integer> pattern) {
        Set<Object> row = new HashSet<>();
        recursivePatternAnalyzer(all, row, pattern, new HashSet<>(), new HashSet<>());
        return row;
    }

    private static void recursivePatternAnalyzer(List<Object> all, Set<Object> row, List<Integer> pattern, Set<Object> without, Set<Object> rest) {
        List<Integer> tmpPattern = new ArrayList<>(pattern);
        List<Object> tmpAll = new ArrayList<>(all);
        tmpAll.removeAll(without);

        if (!tmpPattern.isEmpty()) {
            Integer currentPattern = tmpPattern.get(0);
            tmpPattern.remove(currentPattern);

            Set<Set<Object>> result = new HashSet<>();
            combinations(tmpAll, new ArrayList<>(), result, currentPattern, 0);
            for (Set<Object> res : result) {
                Set<Object> newCombination = new HashSet<>(rest);
                if (res instanceof Set && res.size() == 1) {
                    for (Object o : res) {
                        newCombination.add(o);
                    }
                } else {
                    newCombination.add(res);
                }
                recursivePatternAnalyzer(tmpAll, row, tmpPattern, res, newCombination);
            }
        } else {
            HashSet<Object> r = new HashSet<>(rest);
            row.add(r);
            rest.clear();
        }
    }
}