package edu.mills.mlm;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.support.test.InstrumentationRegistry;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class DatabaseHelperTest {
    private static final String EMAIL1 = "susan@mills.edu";
    private static final String NAME1 = "Susan Mills";
    private static final Context context = InstrumentationRegistry.getTargetContext();
    private DatabaseHelper helper;
    private SQLiteDatabase db;

    @Before
    public void setUp() {
        helper = new DatabaseHelper(context);
        db = helper.getWritableDatabase();
        assertTrue(true);
    }

    @After
    public void tearDown() {
        helper.deleteAllSubscribers(db);
        db.close();
    }

    @Test
    public void testAddingSubscriber() {
        assertEquals(0, helper.getNumSubscribers(db));
        assertFalse(
                EMAIL1 + " should not be subscribed in new database.",
                helper.isSubscribed(db, EMAIL1));
        helper.addSubscriber(db, EMAIL1, NAME1);
        assertTrue(
                EMAIL1 + " should be subscribed after adding to database.",
                helper.isSubscribed(db, EMAIL1));
        assertEquals(1, helper.getNumSubscribers(db));
    }

    @Test
    public void testDeletingSubscribers() {
        helper.addSubscriber(db, EMAIL1, NAME1);
        helper.deleteAllSubscribers(db);
        assertFalse(
                EMAIL1 + " should not be subscribed after deleting all subscribers.",
                helper.isSubscribed(db, EMAIL1));
        assertEquals(0, helper.getNumSubscribers(db));
    }
}