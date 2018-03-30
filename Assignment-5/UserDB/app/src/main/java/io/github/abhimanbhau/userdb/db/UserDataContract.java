package io.github.abhimanbhau.userdb.db;

import android.provider.BaseColumns;


public class UserDataContract {

    private UserDataContract() {
    }

    public static class UserEntry implements BaseColumns {
        public static final String TABLE_NAME = "entry";
        public static final String COLUMN_NAME_NAME = "name";
        public static final String COLUMN_NAME_EMAIL = "email";
        public static final String COLUMN_NAME_FAVSHOWS = "show";


        public static final String SQL_CREATE_ENTRIES =
                "CREATE TABLE " + UserEntry.TABLE_NAME + " (" +
                        UserEntry._ID + " INTEGER PRIMARY KEY," +
                        UserEntry.COLUMN_NAME_NAME + " TEXT," +
                        UserEntry.COLUMN_NAME_EMAIL + " TEXT," +
                        UserEntry.COLUMN_NAME_FAVSHOWS + " TEXT)";

        public static final String SQL_DELETE_ENTRIES =
                "DROP TABLE IF EXISTS " + UserEntry.TABLE_NAME;
    }
}
