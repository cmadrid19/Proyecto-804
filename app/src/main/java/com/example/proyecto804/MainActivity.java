package com.example.proyecto804;

import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;


public class MainActivity extends AppCompatActivity
{
	private BaseDatos datos;
	private TextView tvNumCita;
	private EditText etFecha, etHora, etAsunto;
	private SQLiteDatabase db;

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		tvNumCita = findViewById(R.id.tvNumCita);
		etFecha = findViewById(R.id.etFecha);
		etHora = findViewById(R.id.etHora);
		etAsunto = findViewById(R.id.etAsunto);
	}

	public void onResume()
	{
		super.onResume();
		datos = new BaseDatos(this,"Datos",null,1);
		db = datos.getWritableDatabase();
	}
	public void onPause()
	{
		super.onPause();
		datos.close();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu)
	{
		super.onCreateOptionsMenu(menu);
		menu.add(Menu.NONE, 1, Menu.NONE, "Insertar Cita");
		menu.add(Menu.NONE, 2, Menu.NONE, "Borrar Cita");
		menu.add(Menu.NONE, 3, Menu.NONE, "Consultar Cita");
		menu.add(Menu.NONE, 4, Menu.NONE, "Modificar Cita");
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item)
	{
		switch (item.getItemId())
		{
			case 1:
				añadirCita();
				return true;
			case 2:
				borrarCita();
				return true;
			case 3:
				verCita();
				return true;
			case 4:
				modCita();
				return true;
			default:
				return super.onOptionsItemSelected(item);
		}
	}

	public void añadirCita()
	{
		if(comprobarDatos(true))
		{
			String fecha = etFecha.getText().toString();
			String hora = etHora.getText().toString();
			String asunto = etAsunto.getText().toString();
			String resultado = datos.addCita(this,fecha,hora,asunto);
			Toast.makeText(this, resultado, Toast.LENGTH_LONG).show();
			if(resultado == "Cita guardada")
				limpiarCampos();
		}
	}

	public void borrarCita()
	{
		if(comprobarDatos(false))
		{
			String fecha = etFecha.getText().toString();
			String hora = etHora.getText().toString();
			String resultado = datos.deleteCita(this,fecha,hora);
			Toast.makeText(this, resultado, Toast.LENGTH_LONG).show();
			if(resultado == "Cita borrada")
				limpiarCampos();
		}
	}

	public void verCita()
	{
		if(comprobarDatos(false))
		{
			String fecha = etFecha.getText().toString();
			String hora = etHora.getText().toString();
			Resultado resultado = datos.viewCita(this,fecha,hora);
			if(resultado.error.equals("")){
				tvNumCita.setText(String.valueOf(resultado.id));
				etAsunto.setText(resultado.asunto);
			}
			else{
				Toast.makeText(this, resultado.error, Toast.LENGTH_LONG).show();
			}
		}
	}

	public void modCita()
	{
		if(comprobarDatos(true))
		{
			String fecha = etFecha.getText().toString();
			String hora = etHora.getText().toString();
			String asunto = etAsunto.getText().toString();
			String resultado = datos.modCita(this,fecha,hora,asunto);
			Toast.makeText(this, resultado, Toast.LENGTH_LONG).show();
			if(resultado == "Cita modificada")
				limpiarCampos();
		}
	}

	public boolean comprobarDatos(boolean comprobarAsunto)
	{
		if(etFecha.getText().toString().isEmpty())
		{
			Toast.makeText(this, "La fecha es obligatoria", Toast.LENGTH_LONG).show();
			return false;
		}

		if(etHora.getText().toString().isEmpty())
		{
			Toast.makeText(this, "La hora es obligatoria", Toast.LENGTH_LONG).show();
			return false;
		}

		if (comprobarAsunto){
			if(etAsunto.getText().toString().isEmpty())
			{
				Toast.makeText(this, "El asunto es obligatorio", Toast.LENGTH_LONG).show();
				return false;
			}
		}
		return true;
	}

	public void limpiarCampos()
	{
		etAsunto.setText("");
		etFecha.setText("");
		etHora.setText("");
		tvNumCita.setText("NumCita");
	}
}
