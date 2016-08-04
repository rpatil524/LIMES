package org.aksw.limes.core.measures.measure.topology;

import org.aksw.limes.core.measures.measure.Measure;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * @author Kevin Dreßler
 * @since 1.0
 */
public class WithinMeasureTest {

    @Test
    public void testGetSimilarity() throws Exception {
        Measure measure = new WithinMeasure();
        assertEquals(1.0d, measure.getSimilarity("POLYGON ((-8 -8, 2 8, 8 8, 8 2, -8 -8))", "POLYGON ((-10 -10, 0 10, 10 10, 10 0, -10 -10))"), 0d);
        assertEquals(0.0d, measure.getSimilarity("POLYGON ((-10 -10, 0 10, 10 10, 10 0, -10 -10))","POLYGON ((-20 -20, -10 0, 0 0, 0 -10, -20 -20))"), 0d);
    }
}