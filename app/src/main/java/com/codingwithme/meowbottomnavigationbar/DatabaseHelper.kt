import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class DatabaseHelper(context: Context) :
    SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {

    companion object {
        private const val DATABASE_VERSION = 1
        private const val DATABASE_NAME = "DrugInteractionChecker.db"
    }

    override fun onCreate(db: SQLiteDatabase?) {
        // Create tables here




        db?.execSQL("CREATE TABLE IF NOT EXISTS Interaction (" +
                "data_id INTEGER PRIMARY KEY, " +
                "name_drug_1 TEXT NOT NULL, " +
                "name_drug_2 TEXT NOT NULL, " +
                "interaction_type TEXT NOT NULL)")


        db?.execSQL("CREATE TABLE IF NOT EXISTS InteractionType (" +
                "id_drug_type INTEGER PRIMARY KEY, " +
                "drug_type_index INTEGER NOT NULL, " +
                "interaction_type TEXT NOT NULL, " +
                "severity TEXT NOT NULL, " +
                "description TEXT NOT NULL," +
                "ext_description TEXT NOT NULL)")
        db?.execSQL("create table DDI_types(orderID INTEGER PRIMARY KEY, merged_ddi_type_index INTEGER, origin_ddis_type TEXT, severity TEXT, description TEXT )")

    }


    fun gettext(){
        val db = this.writableDatabase
        val cursor = db.rawQuery("select * from DDI_types", null)
//        return cursor
    }


    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        db?.execSQL("drop table if exists DDI_types")
    }
}
