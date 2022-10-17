package cl.aiep.android.DonbenitoysuPandilla.bd

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.content.ContentValues
import androidx.core.content.contentValuesOf
import cl.aiep.android.DonbenitoysuPandilla.Constantes.KEY_DESCRIPCION
import cl.aiep.android.DonbenitoysuPandilla.Constantes.KEY_ESPECIALIDAD
import cl.aiep.android.DonbenitoysuPandilla.Constantes.KEY_ID
import cl.aiep.android.DonbenitoysuPandilla.Constantes.KEY_LOCALIDAD
import cl.aiep.android.DonbenitoysuPandilla.Constantes.KEY_NOMBRE
import cl.aiep.android.DonbenitoysuPandilla.Constantes.TABLA_LUGARES
import cl.aiep.android.DonbenitoysuPandilla.Lugares.Lugares

class BaseDatos(
    context: Context,
    nombreBaseDatos: String,
    factory: SQLiteDatabase.CursorFactory?,
    version: Int
) : SQLiteOpenHelper(
    context, nombreBaseDatos, factory, version
) {
    override fun onCreate(db: SQLiteDatabase?) {
        val createTable =
            "CREATE TABLE $TABLA_LUGARES($KEY_ID INTERGER PRIMARY KEY AUTOINCREMENT,$KEY_NOMBRE TEXT, $KEY_LOCALIDAD TEXT, $KEY_ESPECIALIDAD TEXT, $KEY_DESCRIPCION TEXT)"
        db?.execSQL(createTable)
    }

    //Se va a crear esta funcion cuando actualice el numero de la version de la base de datos
    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
    }

    //Insertar La informacion
    fun agregarLugares(datoRecibido: Lugares): Long {
        //Declaracion de base de datos a modo de escritura
        val db = this.writableDatabase
        //declarion de contenedor donde se almacenaran los datos a guardar
        val contentValues = ContentValues()
        //al contenedor se le asigna el dato
        contentValues.put(KEY_NOMBRE, datoRecibido.nombre)
        contentValues.put(KEY_LOCALIDAD, datoRecibido.localidad)
        contentValues.put(KEY_DESCRIPCION, datoRecibido.descripcion)
        contentValues.put(KEY_ESPECIALIDAD, datoRecibido.especialidad)
        //insertar los datos
        val success = db.insert(TABLA_LUGARES,null, contentValues)
        db.close()
        return success
    }

    //Metodo para listar la informacion
    fun listarLugar(): ArrayList<Lugares>{
        //Lista vacia sin datos
        val listaData = ArrayList<Lugares>()
        //Declaracion de bd en modo lectura
        val db = this.readableDatabase
        //SQL a ejecutar
        var sqlQuery = "Select * from $TABLA_LUGARES"
        //declaracion de cursor para recorrer la data que trae la query
        var cursor = db.rawQuery(sqlQuery, null)
        while (cursor.moveToNext()){
            val lugar = Lugares(
                cursor.getInt(0).toString(),
                cursor.getString(1),
                cursor.getString(2),
                cursor.getString(3),
                cursor.getString(4),
            )
            listaData.add(lugar)
        }
        return listaData
    }

}

