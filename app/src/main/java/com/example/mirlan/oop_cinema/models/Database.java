package com.example.mirlan.oop_cinema.models;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.provider.BaseColumns;

import com.example.mirlan.oop_cinema.helpers.Movie;
import com.example.mirlan.oop_cinema.helpers.Reservation;
import com.example.mirlan.oop_cinema.helpers.Utils;

import java.util.ArrayList;

/**
 * Created by Mirlan on 26.11.2016.
 */
public class Database extends SQLiteOpenHelper {

    private static Database mInstance;

    private static final String DB_NAME = "data.db";
    private static final int DB_VERSION = 1;

    private static final String MOVIE_TABLE = "movieTable";
    private static final String NAME = "state";
    private static final String POSTER = "posterURL";
    private static final String GENRE = "genre";
    private static final String SLOGAN = "slogan";
    private static final String DESCRIPTION = "description";
    private static final String COST = "cost";
    private static final String movieTableCreationST = "create table " + MOVIE_TABLE +
            "(" + BaseColumns._ID + " integer primary key autoincrement," +
            NAME + " text," +
            POSTER + " text," +
            GENRE + " text," +
            SLOGAN + " text," +
            DESCRIPTION + " text," +
            COST + " integer);";

    private static final String USERS_TABLE = "usersTable";
    private static final String USERNAME = "username";
    private static final String LASTNAME = "last_name";
    private static final String PASSWORD = "password";
    private static final String MONEY = "money";
    private static final String usersTableCreationST = "create table " + USERS_TABLE +
            "(" + USERNAME + " text," +
            LASTNAME + " text," +
            PASSWORD + " text," +
            MONEY + " real);";

    private static final String tableForOneMovie = "create table %s(reserved integer);";

    private static final String ROWID = "RowIDOfMovie";
    private static final String SEATS_AMT = "amountOfSeats";
    private static final String SEATS_NUM = "numbersOfSeats";
    private static final String TIMESTAMP = "timestamp";
    private static final String tableForOneUser = "create table %s(" +
            BaseColumns._ID + " integer primary key autoincrement, " +
            ROWID + " integer, " +
            SEATS_AMT + " integer, " +
            SEATS_NUM + " text," +
            TIMESTAMP + " INTEGER);";

    private Database(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    public static Database getInstance(Context context) {
        if (mInstance == null) {
            mInstance = new Database(context.getApplicationContext());
        }
        return mInstance;
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(movieTableCreationST);
        sqLiteDatabase.execSQL(usersTableCreationST);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }


    /**
     * Looks for Distinct genres in DB
     *
     * @return ArrayList of genres
     */
    public ArrayList<String> getDistinctGenres() {
        ArrayList<String> column = new ArrayList<>();
        String statement = "select distinct " + GENRE + " from " + MOVIE_TABLE;
        Cursor cursor = getReadableDatabase().rawQuery(statement, null);
        while (cursor.moveToNext())
            column.add(cursor.getString(cursor.getColumnIndex(GENRE)));

        cursor.close();
        return column;
    }

    /**
     * Looks for movies in DB related to given genre
     *
     * @param genre type of genre
     * @return ArrayList Movie objects
     */
    public ArrayList<Movie> getMoviesByGenre(String genre) {
        ArrayList<Movie> names = new ArrayList<>();
        String statement = "select * from " + MOVIE_TABLE + " where " + GENRE + " = '" + genre + "'";
        Cursor cursor = getReadableDatabase().rawQuery(statement, null);
        while (cursor.moveToNext()) {
            names.add(new Movie(
                    cursor.getInt(cursor.getColumnIndex(BaseColumns._ID)),
                    cursor.getString(cursor.getColumnIndex(NAME)),
                    cursor.getString(cursor.getColumnIndex(POSTER)),
                    cursor.getString(cursor.getColumnIndex(GENRE)),
                    cursor.getString(cursor.getColumnIndex(SLOGAN)),
                    cursor.getString(cursor.getColumnIndex(DESCRIPTION)),
                    cursor.getInt(cursor.getColumnIndex(COST))));
        }
        cursor.close();
        return names;
    }

    /**
     * Looks for movie in DB with the given id
     *
     * @param id unique id of movie
     * @return Movie object if found, null otherwise
     */
    public Movie getMovieByID(int id) {
        String statement = "select * from " + MOVIE_TABLE + " where " + BaseColumns._ID + " = " + id + "";
        Cursor cursor = getReadableDatabase().rawQuery(statement, null);
        Movie movie = null;
        if (cursor.moveToFirst())
            movie = new Movie(
                    cursor.getInt(cursor.getColumnIndex(BaseColumns._ID)),
                    cursor.getString(cursor.getColumnIndex(NAME)),
                    cursor.getString(cursor.getColumnIndex(POSTER)),
                    cursor.getString(cursor.getColumnIndex(GENRE)),
                    cursor.getString(cursor.getColumnIndex(SLOGAN)),
                    cursor.getString(cursor.getColumnIndex(DESCRIPTION)),
                    cursor.getInt(cursor.getColumnIndex(COST)));
        cursor.close();
        return movie;
    }


    /**
     * Updates Money column in User table
     *
     * @param username user's name
     * @param amount   amount of money
     * @return true if update successful, false otherwise
     */
    public boolean updateUser(String username, double amount) {
        ContentValues cv = new ContentValues();
        cv.put(MONEY, ((int) amount));
        return (1 == getWritableDatabase().update(USERS_TABLE, cv, USERNAME + " = '" + username + "'", null));
    }

    /**
     * Looks for the User in Users Table
     *
     * @param username user's name
     * @param password real password, not SHA, will be converted to SHA and checked for matching inside the method
     * @return User object if found, null otherwise
     */
    public User getUser(String username, String password) {
        User user = null;
        if (!isTableEmpty(USERS_TABLE) && userExists(username)) {
            String statement = "select * from " + USERS_TABLE + " where " + USERNAME + " = '" + username + "'";
            Cursor cursor = getReadableDatabase().rawQuery(statement, null);
            if (cursor.moveToFirst()) {
                String hashNew = Utils.getSHA256Hash(password);
                String hashDB = cursor.getString(cursor.getColumnIndex(PASSWORD));
                if (hashDB.equals(hashNew)) {
                    double money = cursor.getDouble(cursor.getColumnIndex(MONEY));
                    user = User.getUser(username, money);
                }
            }
            cursor.close();
        }
        return user;
    }

    /**
     * Adds new user to Users table
     *
     * @param username user's name
     * @param password user's password
     * @param money    user's amount of money
     * @return true if user is added successfully and username is unique
     * (no users with given name), otherwise false
     */
    public boolean addToUsersTable(String username, String password, double money) {
//        if no user exists with the given username
        if (!userExists(username)) {
//            then create one and return true
            String statement = "INSERT into " + USERS_TABLE +
                    " (" + USERNAME + "," + PASSWORD + "," + MONEY + ") values ('" +
                    username + "','" + password + "','" + money + "')";
            SQLiteDatabase writableDB = getWritableDatabase();
            writableDB.execSQL(statement);
            writableDB.execSQL(String.format(tableForOneUser, username));
            return true;
        }
//        otherwise don't create and return false
        return false;
    }

    /**
     * Checks if User exist with given name in Users Table
     *
     * @param username user's name
     * @return True if found, false otherwise
     */
    private boolean userExists(String username) {
        boolean res = false;
        if (!isTableEmpty(USERS_TABLE)) {
            String statement = "select " + USERNAME + " from " + USERS_TABLE + " where " + USERNAME + " = '" + username + "'";
            Cursor cursor = getReadableDatabase().rawQuery(statement, null);
            if (cursor.getCount() > 0) {
                res = true;
            }
            cursor.close();
        }
        return res;
    }


    public ArrayList<Integer> getReservedSeats(String forMovie) {
        forMovie = Utils.format(forMovie);
        ArrayList<Integer> seats = new ArrayList<>();
        if (!isTableEmpty(forMovie)) {
            String statement = "SELECT * from " + forMovie;
            Cursor cursor = getReadableDatabase().rawQuery(statement, null);
            if (cursor.moveToFirst()) {
                for (int i = 0; i < cursor.getCount(); i++) {
                    seats.add(cursor.getInt(0));
                    cursor.moveToNext();
                }
            }
            cursor.close();
        }
        return seats;
    }

    public void updateSeatsOfMovie(String movieName, ArrayList<Integer> seats, boolean reserving) {
        movieName = Utils.format(movieName);
        SQLiteDatabase writableDB = getWritableDatabase();
        ContentValues cv = new ContentValues();
        if (reserving) {
            for (int i : seats) {
                cv.put("reserved", i);
                writableDB.insert(movieName, null, cv);
                cv.clear();
            }
        }
        writableDB.close();
    }

    public ArrayList<Reservation> getReservationHistory(String username) {
        ArrayList<Reservation> reservations = new ArrayList<>();
        String statement = "SELECT * from " + username;
        Cursor cursor = getReadableDatabase().rawQuery(statement, null);
        if (cursor.moveToFirst()) {
            for (int i = 0; i < cursor.getCount(); i++) {
                reservations.add(new Reservation(getMovieByID(cursor.getInt(cursor.getColumnIndex(ROWID))),
                        cursor.getInt(cursor.getColumnIndex(SEATS_AMT)),
                        cursor.getString(cursor.getColumnIndex(SEATS_NUM)),
                        cursor.getLong(cursor.getColumnIndex(TIMESTAMP))));
                cursor.moveToNext();
            }
        }
        cursor.close();
        return reservations;
    }

    public boolean saveUserReservation(String tableName, int rowId, int seatAmt, String seatNums, long timestamp) {
        ContentValues cv = new ContentValues();
        cv.put(ROWID, rowId);
        cv.put(SEATS_AMT, seatAmt);
        cv.put(SEATS_NUM, seatNums);
        cv.put(TIMESTAMP, timestamp);

        return (1 == getWritableDatabase().insert(tableName, null, cv));
    }


    /**
     * Saves Movies into Movie Table, called only once
     *
     * @param movies movie objects in Array List
     */
    public void saveInitialData(ArrayList<Movie> movies) {
        SQLiteDatabase writableDB = getWritableDatabase();
        Movie movie;
        ContentValues values = new ContentValues();
        for (int i = 0; i < movies.size(); i++) {
            movie = movies.get(i);
            values.put(NAME, movie.getName());
            values.put(POSTER, movie.getPoster());
            values.put(GENRE, movie.getGenre());
            values.put(SLOGAN, movie.getSlogan());
            values.put(DESCRIPTION, movie.getDescription());
            values.put(COST, movie.getCost());
            writableDB.insert(MOVIE_TABLE, null, values);

//            creating a table of seats for one movie named with it's name
            writableDB.execSQL(String.format(tableForOneMovie, Utils.format(movie.getName())));

            values.clear();
        }
        writableDB.close();
    }

    /**
     * Checks if table is empty
     *
     * @param tableName name of table
     */
    private boolean isTableEmpty(String tableName) {
        boolean res = false;
        Cursor cursor = getReadableDatabase().rawQuery("SELECT count(*) from " + tableName, null);
        if (cursor != null) {
            cursor.moveToFirst();                       // Always one row returned.
            if (cursor.getInt(0) == 0)                  // Zero count means empty table.
                res = true;
            cursor.close();
        }
        return res;
    }
}
