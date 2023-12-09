//Funciona el push
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class dbContext extends SQLiteOpenHelper{
    private static final String DB_NAME = "proyecto2progra";
    private static final int DB_VERSION = 1;
    private static final String TABLE_Usuario = "usuario";
    private static final String ID_COL_Usu = "id";
    private static final String NAME_COL_Usu = "nombre";
    private static final String Correo_COL_Usu = "correo";
    private static final String Password_COL_Usu = "password";
    private static final String TABLE_Productos = "producto";
    private static final String NAME_COL_Prod = "nombre";
    private static final String Cantidad_COL_Prod = "cantidad";
    private static final String Precio_COL_Prod = "precio";
    private static final String Descrip_COL_Prod = "descripcion";
    private static final String Imagen_COL_Prod = "imagen";

    public dbContext(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        String query = "CREATE TABLE " + TABLE_Usuario + " ("
                + ID_COL + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + NAME_COL + " TEXT NOT NULL,"
                + Correo_COL_Usu + " TEXT NOT NULL,"
                + Password_COL_Usu + " TEXT NOT NULL)";
        db.execSQL(query);
        String query = "CREATE TABLE " + TABLE_Productos + " ("
                + ID_COL + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + NAME_COL_Prod + " TEXT NOT NULL,"
                + Cantidad_COL_Prod + " INT NOT NULL,"
                + Precio_COL_Prod + " INT NOT NULL,"
                + Descrip_COL_Prod + " TEXT NOT NULL,"
                + Imagen_COL_Prod + " TEXT NOT NULL)";
        db.execSQL(query);
    }


    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_Usuario);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_Productos);
        onCreate(db);
    }

}
