package com.example.proyecto804;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.lang.reflect.Type;

public class BaseDatos extends SQLiteOpenHelper
{
	public BaseDatos(Context contexto, String nombre, SQLiteDatabase.CursorFactory factory, int version)
	{
		super(contexto,nombre,factory,version);
	}

	@Override
	public void onCreate(SQLiteDatabase db)
	{
		String crear = "CREATE TABLE Citas(_id integer primary key autoincrement,fecha text, hora text, asunto text)";
		db.execSQL(crear);
	}

	@Override
	public void onUpgrade(SQLiteDatabase db,int versionAnt,int versionNue){}

	private void cerrarBaseDatos(SQLiteDatabase db){
		db.close();
	}

	public String addCita(Context contexto, String fecha, String hora, String asunto)
	{
		String resultado="";
		// SELECT para comprobar si la cita existe
		// ...




		if(cursor.moveToFirst())
		{
			// La cita existe
			resultado = "Cita duplicada";
		}
		else
		{
			try
			{
				// Crear fichero de asunto
				//...

				// Insertar cita en BD
				//...
			}
			catch (Exception e)
			{
				resultado = e.getMessage();
			}
		}
		return resultado;
	}

	public String deleteCita(Context contexto, String fecha, String hora){
		String resultado="";
		// Inicializar parámetros
		//...

		// Borramos fichero ==> File.delete()
		if(/*fichero existía*/)
		{
			// Borramos BD
		}
		else
		{
			resultado =  "Error al borrar la cita";
		}

		return resultado;
	}

	public Resultado viewCita(Context contexto, String fecha, String hora){
		Resultado resultado = new Resultado();
		//Inicializar parámetros
		//...

		String consulta = "SELECT fecha, hora" +
				" FROM Citas " +
				"WHERE fecha LIKE \""+fecha+"\""+" AND " +
				"hora  LIKE \""+hora+"\"";

		SQLiteDatabase bd = this.getReadableDatabase();
		Cursor cursor = bd.rawQuery(consulta, null);

		// Consulta para obtener id de cita

		if(cursor.moveToFirst()){
			//añadimos id al resultado
			BufferedReader fichero = null;
			try {
				File dir = new File(bd.getPath());
				fichero = new BufferedReader(new InputStreamReader(openFileInput(dir)));


				//Leemos fichero y lo introducimos en resultado
				//...
				String res = "";
				if (cursor.moveToFirst()) {
					while (!cursor.isAfterLast()) {
						for (int i = 0; i < cursor.getColumnCount(); ++i) {
							int tipo = cursor.getType(i);

							res += cursor.getColumnName(i) + ": ";

							switch (tipo) {
								case Cursor.FIELD_TYPE_INTEGER:
									res += cursor.getInt(i) + " ; ";
									break;
								case Cursor.FIELD_TYPE_FLOAT:
									res += cursor.getFloat(i) + " ; ";
									break;
								case Cursor.FIELD_TYPE_STRING:
									res += cursor.getString(i) + " ; ";
									break;
							}
						}
						res += "\n";
						cursor.moveToNext();
					}
				}
			}
			catch (IOException e)
			{
				resultado.error = e.getMessage();
			}
		}
		else
		{
			resultado.error = "No existe una cita con esa fecha y hora";
		}

		return resultado;
	}

	public String modCita(Context contexto, String fecha, String hora, String asunto){

		String resultado = "";
		// SELECT para comprobar si la cita existe
		// ...

		if(cursor.moveToFirst())
		{
			//modificamos el fichero
			try
			{
				//...

				resultado = "Cita modificada";
			}
			catch (IOException e)
			{
				resultado = e.getMessage();
			}
		}
		else{
			// La cita no existe
			resultado = "No existe una cita con esa fecha y hora";
		}
		return resultado;
	}
}

class Resultado{
	public int id;
	public String asunto, error;

	Resultado(){
		id=-1;
		asunto="";
		error="";
	}
}