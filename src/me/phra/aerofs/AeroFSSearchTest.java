/**
 * AeroFS Challenge - Kenny Gao
 */

package me.phra.aerofs;

import org.junit.After;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import static me.phra.aerofs.AeroFSSearch.ResultType.*;
import static me.phra.aerofs.AeroFSSearch.SearchResult;
import static me.phra.aerofs.AeroFSSearch.SearchType.*;
import static me.phra.aerofs.AeroFSSearch.search;
import static org.junit.Assert.assertEquals;

/**
 * @author kennygao
 */
public class AeroFSSearchTest {
    int[] ascending_items;
    int[] descending_items;
    int n_items;

    @Before
    public void setUp() throws Exception {
        ascending_items = new int[]{0, 2, 4, 6, 8};
        descending_items = new int[]{8, 6, 4, 2, 0};
        n_items = 5;
    }

    @After
    public void tearDown() throws Exception {

    }

    @Ignore("might use this later")
    @Test
    public void testSearch() throws Exception {

    }

    @Test
    public void testSearchLessThan() throws Exception {
        SearchResult ascending_result = search(ascending_items, n_items, true, 0, LESS_THAN);
        assertEquals(ascending_result.type, NOT_FOUND);
        assertEquals(ascending_result.index, -1);

        SearchResult descending_result = search(descending_items, n_items, false, -1, LESS_THAN);
        assertEquals(descending_result.type, NOT_FOUND);
        assertEquals(descending_result.index, -1);
    }

    @Test
    public void testSearchLessThanEquals() throws Exception {
        SearchResult ascending_result = search(ascending_items, n_items, true, -1, LESS_THAN_EQUALS);
        assertEquals(ascending_result.type, NOT_FOUND);
        assertEquals(ascending_result.index, -1);

        SearchResult descending_result = search(descending_items, n_items, false, 4, LESS_THAN_EQUALS);
        assertEquals(descending_result.type, FOUND_EXACT);
        assertEquals(descending_result.index, 2);
    }

    @Test
    public void testSearchEquals() throws Exception {
        SearchResult ascending_result_1 = search(ascending_items, n_items, true, 0, EQUALS);
        assertEquals(ascending_result_1.type, FOUND_EXACT);
        assertEquals(ascending_result_1.index, 0);

        SearchResult ascending_result_2 = search(ascending_items, n_items, true, 1, EQUALS);
        assertEquals(ascending_result_2.type, NOT_FOUND);
        assertEquals(ascending_result_2.index, -1);

        SearchResult descending_result = search(descending_items, n_items, false, 8, EQUALS);
        assertEquals(descending_result.type, FOUND_EXACT);
        assertEquals(descending_result.index, 0);
    }

    @Test
    public void testSearchGreaterThanEquals() throws Exception {
        SearchResult ascending_result = search(ascending_items, n_items, true, 2, GREATER_THAN_EQUALS);
        assertEquals(ascending_result.type, FOUND_EXACT);
        assertEquals(ascending_result.index, 1);

        SearchResult descending_result_1 = search(descending_items, n_items, false, 5, GREATER_THAN_EQUALS);
        assertEquals(descending_result_1.type, FOUND_GREATER);
        assertEquals(descending_result_1.index, 1);

        SearchResult descending_result_2 = search(descending_items, n_items, false, 2, GREATER_THAN_EQUALS);
        assertEquals(descending_result_2.type, FOUND_EXACT);
        assertEquals(descending_result_2.index, 3);
    }

    @Test
    public void testSearchGreaterThan() throws Exception {
        SearchResult ascending_result = search(ascending_items, n_items, true, 2, GREATER_THAN);
        assertEquals(ascending_result.type, FOUND_GREATER);
        assertEquals(ascending_result.index, 2);

        SearchResult descending_result = search(descending_items, n_items, false, 9, GREATER_THAN);
        assertEquals(descending_result.type, NOT_FOUND);
        assertEquals(descending_result.index, -1);
    }
}
