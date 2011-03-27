package org.jfrog.build.api;

import com.google.common.collect.Maps;
import com.google.common.collect.Sets;
import org.jfrog.build.api.release.Promotion;
import org.testng.annotations.Test;

import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.Date;
import java.util.Map;
import java.util.Set;

import static org.testng.Assert.*;

/**
 * @author Noam Y. Tenne
 */
@Test
public class PromotionTest {

    public void testDefaultValues() {
        Promotion promotion = new Promotion();
        assertNull(promotion.getStatus(), "Unexpected default status.");
        assertNull(promotion.getComment(), "Unexpected default comment.");
        assertNull(promotion.getCiUser(), "Unexpected default ci user.");
        assertNull(promotion.getTimestamp(), "Unexpected default timestamp.");
        assertFalse(promotion.isDryRun(), "Unexpected default dry run state.");
        assertNull(promotion.getTargetRepo(), "Unexpected default target repo.");
        assertFalse(promotion.isCopy(), "Unexpected default copy state.");
        assertTrue(promotion.isArtifacts(), "Unexpected default artifacts state.");
        assertFalse(promotion.isDependencies(), "Unexpected default dependencies state.");
        assertNull(promotion.getScopes(), "Unexpected default scopes.");
        assertNull(promotion.getProperties(), "Unexpected default properties.");
    }

    public void testConstructor() {
        Set<String> scopes = Sets.newHashSet();
        Map<String, Collection<String>> properties = Maps.newHashMap();

        Promotion promotion = new Promotion(Promotion.ROLLED_BACK, "comment", "ciUser", "timestamp",
                true, "targetRepo", false, true, false, scopes, properties);

        assertEquals(promotion.getStatus(), Promotion.ROLLED_BACK, "Unexpected status.");
        assertEquals(promotion.getComment(), "comment", "Unexpected comment.");
        assertEquals(promotion.getCiUser(), "ciUser", "Unexpected ci user.");
        assertEquals(promotion.getTimestamp(), "timestamp", "Unexpected timestamp.");
        assertTrue(promotion.isDryRun(), "Unexpected dry run state.");
        assertEquals(promotion.getTargetRepo(), "targetRepo", "Unexpected target repo.");
        assertFalse(promotion.isCopy(), "Unexpected copy state.");
        assertTrue(promotion.isArtifacts(), "Unexpected artifacts state.");
        assertFalse(promotion.isDependencies(), "Unexpected dependencies state.");
        assertEquals(promotion.getScopes(), scopes, "Unexpected scopes.");
        assertEquals(promotion.getProperties(), properties, "Unexpected properties.");
    }

    public void testSetters() {
        Set<String> scopes = Sets.newHashSet();
        Map<String, Collection<String>> properties = Maps.newHashMap();

        Promotion promotion = new Promotion();
        promotion.setStatus(Promotion.ROLLED_BACK);
        promotion.setComment("comment");
        promotion.setCiUser("ciUser");
        promotion.setTimestamp("timestamp");
        promotion.setDryRun(true);
        promotion.setTargetRepo("targetRepo");
        promotion.setCopy(false);
        promotion.setArtifacts(true);
        promotion.setDependencies(false);
        promotion.setScopes(scopes);
        promotion.setProperties(properties);

        assertEquals(promotion.getStatus(), Promotion.ROLLED_BACK, "Unexpected status.");
        assertEquals(promotion.getComment(), "comment", "Unexpected comment.");
        assertEquals(promotion.getCiUser(), "ciUser", "Unexpected ci user.");
        assertEquals(promotion.getTimestamp(), "timestamp", "Unexpected timestamp.");
        assertTrue(promotion.isDryRun(), "Unexpected dry run state.");
        assertEquals(promotion.getTargetRepo(), "targetRepo", "Unexpected target repo.");
        assertFalse(promotion.isCopy(), "Unexpected copy state.");
        assertTrue(promotion.isArtifacts(), "Unexpected artifacts state.");
        assertFalse(promotion.isDependencies(), "Unexpected dependencies state.");
        assertEquals(promotion.getScopes(), scopes, "Unexpected scopes.");
        assertEquals(promotion.getProperties(), properties, "Unexpected properties.");
    }

    public void testNullTimestampDateGetter() {
        Promotion promotion = new Promotion(null, null, null, null, true, null, true, true, true, null, null);
        assertNull(promotion.getTimestampDate(), "No timestamp was set. Should have received null");
    }

    public void testTimestampDateGetters() {
        SimpleDateFormat format = new SimpleDateFormat(Build.STARTED_FORMAT);

        Date timestampDate = new Date();

        Promotion promotion = new Promotion(null, null, null, format.format(timestampDate), true, null, true, true,
                true, null, null);
        assertEquals(promotion.getTimestampDate(), timestampDate, "Unexpected timestamp date.");
    }
}