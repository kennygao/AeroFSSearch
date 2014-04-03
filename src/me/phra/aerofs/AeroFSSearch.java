/**
 * AeroFS Challenge - Kenny Gao
 */

package me.phra.aerofs;

import java.util.OptionalInt;
import java.util.function.IntBinaryOperator;
import java.util.function.IntPredicate;
import java.util.stream.IntStream;

/**
 * @author kennygao
 */
public class AeroFSSearch {

    public static enum SearchType {
        LESS_THAN, LESS_THAN_EQUALS, EQUALS, GREATER_THAN_EQUALS, GREATER_THAN
    }

    public static enum ResultType {
        NOT_FOUND, FOUND_LESS, FOUND_EXACT, FOUND_GREATER
    }

    public static class SearchResult {
        ResultType type;
        int index;

        public SearchResult(ResultType type, int index) {
            this.type = type;
            this.index = index;
        }
    }

    public static SearchResult search(int[] items, int n_items, boolean ascending, int key, SearchType searchType) {
        IntPredicate predicate = null;
        IntBinaryOperator operator = null;

        final IntBinaryOperator maxOperator = (a, b) -> items[a] > items[b] ? a : b;
        final IntBinaryOperator nullOperator = (a, b) -> -1; // will never be used
        final IntBinaryOperator minOperator = (a, b) -> items[a] < items[b] ? a : b;

        switch (searchType) {
            case LESS_THAN:
                predicate = i -> items[i] < key;
                operator = maxOperator;
                break;
            case LESS_THAN_EQUALS:
                predicate = i -> items[i] <= key;
                operator = maxOperator;
                break;
            case EQUALS:
                predicate = i -> items[i] == key;
                operator = nullOperator;
                break;
            case GREATER_THAN_EQUALS:
                predicate = i -> items[i] >= key;
                operator = minOperator;
                break;
            case GREATER_THAN:
                predicate = i -> items[i] > key;
                operator = minOperator;
                break;
        }

        OptionalInt candidate = IntStream.range(0, n_items).filter(predicate).reduce(operator);

        if (!candidate.isPresent()) {
            return new SearchResult(ResultType.NOT_FOUND, -1);
        }

        int index = candidate.getAsInt();

        switch (Integer.signum(Integer.compare(items[index], key))) {
            case -1:
                return new SearchResult(ResultType.FOUND_LESS, index);
            case 0:
                return new SearchResult(ResultType.FOUND_EXACT, index);
            case 1:
                return new SearchResult(ResultType.FOUND_GREATER, index);
        }

        return null;
    }

}
