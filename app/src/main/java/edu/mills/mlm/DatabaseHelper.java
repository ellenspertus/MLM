package edu.mills.mlm;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

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
/*
    boolean isSubscribed(SQLiteDatabase db, String email) {
        Cursor cursor = db.query(SUBSCRIBERS_TABLE,
                new String[]{}, // columns to retrieve
                EMAIL_COL + " = ?",  // selection
                new String[]{email},  // selection args
                null,  // group by
                null,   // having
                null); // order by
        boolean result = cursor.getColumnCount() == 1;
        cursor.close();
        return result;
    }
    */

    boolean isSubscribed(SQLiteDatabase db, String email) {
        try (Cursor cursor = db.query(SUBSCRIBERS_TABLE,
                new String[]{}, EMAIL_COL + " = ?",
                new String[]{email}, null, null, null)) {
            return cursor.getColumnCount() == 1;
        }
    }

    void deleteAllSubscribers(SQLiteDatabase db) {
        db.delete(SUBSCRIBERS_TABLE, null, null);
    }

    String getFileContents(String inputFile) throws IOException {
        isSubscribed(null, null);
        StringBuilder sb = new StringBuilder();
        try (Scanner scanner = new Scanner(new File(inputFile))) {
            while (scanner.hasNext()) {
                sb.append(scanner.nextLine());
            }
            return sb.toString();
        }
    }
/*
    String getFileContents(String inputFile) throws IOException {
        StringBuffer sb = new StringBuffer();
        Scanner scanner = new Scanner(new File(inputFile));
        while (scanner.hasNext()) {
            sb.append(scanner.nextLine());
        }
        scanner.close();
        return sb.toString();
    }
    */

    /*
    void printFile(String inputFile) throws IOException {
        try (Scanner scanner = new Scanner(new File(inputFile))) {
            while (scanner.hasNext()) {
                System.out.println(scanner.nextLine());
            }
        }
    }
    */

    void copyFile(String inputFile, String outputFile) throws IOException {
        try (Scanner scanner = new Scanner(new File(inputFile));
             PrintWriter writer = new PrintWriter(new File(outputFile))) {
            while (scanner.hasNext()) {
                writer.print(scanner.nextLine());
            }
        }
    }

}
