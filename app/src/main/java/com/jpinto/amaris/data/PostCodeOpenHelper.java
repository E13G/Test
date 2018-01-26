package com.jpinto.amaris.data;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/*
 * Created by JPinto on 1/25/2018.
 */

public class PostCodeOpenHelper extends SQLiteOpenHelper {

    private static final String TAG = PostCodeOpenHelper.class.getSimpleName();

    private static final int DATABASE_VERSION = 3;
    private static final String POSTCODE_LIST_TABLE = "postcode_entries";
    private static final String DATABASE_NAME = "postcodelist";

    public static final String KEY_ID = "_id";
    public static final String KEY_CODE = "postcode";

    private static final String[] COLUMNS = { KEY_ID, KEY_CODE };

    private static final String POSTCODE_TABLE_CREATE =
            "CREATE TABLE " + POSTCODE_LIST_TABLE + " (" + KEY_ID + " INTEGER PRIMARY KEY, " +
                    KEY_CODE + " TEXT );";

    private SQLiteDatabase writableDB;
    private SQLiteDatabase readableDB;

    public PostCodeOpenHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(POSTCODE_TABLE_CREATE);
        fillDatabaseWithData(db);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        db.execSQL("DROP TABLE IF EXISTS " + POSTCODE_LIST_TABLE);
        onCreate(db);
    }

    public PostCode query(int position){

        String query = "SELECT * FROM " + POSTCODE_LIST_TABLE +
                " ORDER BY " + KEY_CODE + " ASC " +
                "LIMIT " + position + ",1";

        Cursor cursor = null;

        PostCode entry = new PostCode();

        try{
            if (readableDB == null) {
                readableDB = getReadableDatabase();
            }
            cursor = readableDB.rawQuery(query, null);
            cursor.moveToFirst();

            entry.setId(cursor.getInt(cursor.getColumnIndex(KEY_ID)));
            entry.setPostCode(cursor.getString(cursor.getColumnIndex(KEY_CODE)));

        }catch (Exception e){
            Log.d(TAG, "QUERY EXCEPTION! " + e);
        }finally {
            cursor.close();

        }
        return entry;
    }

    public Cursor search(String searchString) {
        String[] columns = new String[]{KEY_CODE};
        String where =  KEY_CODE + " LIKE ?";
        searchString = "%" + searchString + "%";
        String[] whereArgs = new String[]{searchString};

        Cursor cursor = null;
        try {
            if (readableDB == null) {
                readableDB = getReadableDatabase();
            }
            cursor = readableDB.query(POSTCODE_LIST_TABLE, columns, where, whereArgs, null, null, null);
        } catch (Exception e) {
            Log.d(TAG, "SEARCH EXCEPTION! " + e); // Just log the exception
        }
        return cursor;
    }

    public long count() {
        if (readableDB == null) {readableDB = getReadableDatabase();}
        return DatabaseUtils.queryNumEntries(readableDB, POSTCODE_LIST_TABLE);
    }

    private void fillDatabaseWithData(SQLiteDatabase db) {

        String[] words = {"3750-011, AGADÃO",
                "3750-031, AGUADA DE BAIXO",
                "3750-317, ÁGUEDA",
                "3750-712, RECARDÃES",
                "3750-853, BORRALHA",
                "3750-427, FERMENTELOS",
                "3750-552, LAMAS DO VOUGA",
                "3750-381, CASTANHEIRA DO VOUGA",
                "3850-125, ALBERGARIA-A-VELHA",
                "3850-458, ANGEJA",
                "3850-794, SÃO JOÃO DE LOURE",

                "3850-827, VALMAIOR",
                "3780-624, AGUIM",
                "3780-182, SÃO LOURENÇO DO BAIRRO",
                "3780-476, ANADIA",
                "4540-661, URRÔ",
                "4540-753, CHAVE",
                "4540-125, AROUCA",
                "3810-245, AVEIRO",
                "3810-762, NOSSA SENHORA DE FÁTIMA",
                "4905-099, FRAGOSO",

                "4905-069, DURRÃES",
                "7040-011, ARRAIOLOS",
                "7040-211, IGREJINHA",
                "7040-513, SÃO PEDRO GAFANHOEIRA",
                "7100-255, ÉVORA MONTE",
                "7100-654, SÃO LOURENÇO DE MAMPORCÃO",
                "7005-123, AZARUJA",
                "7005-839, ÉVORA",
                "7005-247, ÉVORA",
                "7000-222, N SENHORA DE GUADALUPE",

                "7000-202, S SEBASTIÃO DA GIESTEIRA",
                "7050-600, SÃO CRISTÓVÃO",
                "7050-662, SILVEIRAS",
                "7490-204, MORA",
                "7240-220, MOURÃO",
                "7220-024, ALQUEVA",
                "7220-230, MONTE DO TRIGO",
                "7220-439, PORTEL",
                "7220-528, SÃO BARTOLOMEU DO OUTEIRO",
                "7200-083, CORVAL",

                "7080-024,VENDAS NOVAS",
                "7090-072,ALCÁÇOVAS",
                "7090-246,VIANA DO ALENTEJO",
                "7160-363,PARDAIS",
                "7160-363,PARDAIS",
                "8200-379,ALBUFEIRA",
                "8970-071,ALCOUTIM",
                "8970-104,GIÕES",
                "8970-274,MARTIM LONGO",
                "8670-090,ALJEZUR",

                "8005-456,FARO",
                "8400-419,LAGOA",
                "8100-267,LOULÉ",
                "8400-419,LAGOA",
                "8125-157,QUARTEIRA",
                "8550-238,MONCHIQUE",
                "2690-299,SANTA IRIA DE AZÓIA",
                "2745-232,QUELUZ",
                "2720-087,AMADORA",
                "4640-173,BAIÃO",
        };

        ContentValues values = new ContentValues();

        for (String word : words) {
            values.put(KEY_CODE, word);
            db.insert(POSTCODE_LIST_TABLE, null, values);
        }
    }

}
