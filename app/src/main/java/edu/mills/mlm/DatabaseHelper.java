package edu.mills.mlm;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper extends SQLiteOpenHelper {
    private static final String DB_NAME = "mlm";
    private static final int DB_VERSION = 1;
    private static final String SUBSCRIBERS_TABLE = "subscribers";
    private static final String EMAIL_COL = "email";
    private static final String NAME_COL = "names";

    DatabaseHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        updateMyDatabase(db, 0, DB_VERSION);
    }

    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        updateMyDatabase(db, oldVersion, newVersion);
    }

    private void updateMyDatabase(SQLiteDatabase db, int oldVersion, int newVersion) {
        if (oldVersion < 1) {
            db.execSQL("CREATE TABLE " + SUBSCRIBERS_TABLE +
                    " (_id INTEGER PRIMARY KEY AUTOINCREMENT, "
                    + EMAIL_COL + " TEXT UNIQUE, "
                    + NAME_COL + " TEXT); ");
        }
    }

    void addSubscriber(SQLiteDatabase db, String email, String name) {
        ContentValues values = new ContentValues();
        values.put(EMAIL_COL, email);
        values.put(NAME_COL, name);
        db.insert(SUBSCRIBERS_TABLE, null, values);
    }

    boolean isSubscribed(SQLiteDatabase db, String email) {
        try (Cursor cursor = db.query(SUBSCRIBERS_TABLE,
                new String[]{}, EMAIL_COL + " = ?",
                new String[]{email}, null, null, null)) {
            return cursor.getCount() == 1;
        }
    }

    void deleteAllSubscribers(SQLiteDatabase db) {
        db.delete(SUBSCRIBERS_TABLE, null, null);
    }

}
