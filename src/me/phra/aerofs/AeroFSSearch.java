/**
 * AeroFS Challenge - Kenny Gao
 */

package me.phra.aerofs;

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

    public static SearchResult search(int[] items, int n_items, boolean ascending, int key, SearchType type) {
        return null;
    }

    /**
     * @param args unused
     */
    public static void main(String[] args) {
        System.out.println();
    }

}
