package com.example.supermercado;

import java.text.DecimalFormat;

import android.os.Bundle;
import android.app.Activity;
import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.view.Menu;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity {
	
	EditText e1, e2, e3, e4, e5;
	TextView t1;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		e1 = (EditText)findViewById(R.id.editText1);
		e2 = (EditText)findViewById(R.id.editText2);
		e3 = (EditText)findViewById(R.id.editText3);
		e4 = (EditText)findViewById(R.id.editText4);
		e5 = (EditText)findViewById(R.id.editText5);
		
		t1 = (TextView)findViewById(R.id.textView13);
		
	}

	
  public void limpiar(View v)
  {
	  e1.setText("");
	  e2.setText("");
	  e3.setText("");
	  e4.setText("");
	  e5.setText("");
	  
	  t1.setText("$0.00");
	  
  }
  
  public void alta(View v)
  {
	  Administrador a = new Administrador(this,"comercio",null,1);
	  SQLiteDatabase db = a.getWritableDatabase();
	  
	  String codi = e1.getText().toString();
	  String nombre = e2.getText().toString();
	  String rubro = e3.getText().toString();
	  String precio = e4.getText().toString();
	  
	  if(codi.equals("")||nombre.equals("")||rubro.equals("")||precio.equals(""))
	  {
		  Toast t = Toast.makeText(this, "Falta ingresar algun dato",Toast.LENGTH_LONG);
		  t.show();
	  }
	  else
	  {
	  ContentValues registro  = new ContentValues();
	  
	  registro.put("codigo", codi);
	  registro.put("nombre", nombre);
	  registro.put("rubro", rubro);
	  registro.put("precio", precio);
	  
	  db.insert("articulos", null, registro);
	  
	  db.close();
	  
	  Toast t = Toast.makeText(this, "El articulo " + nombre + "\nSe grabó correctamente", Toast.LENGTH_LONG);
	  t.show();
	   
	  limpiar(v);
	  }
  }
  
  public void consulta(View v)
  {
	  Administrador a = new Administrador(this,"comercio",null,1);
	  SQLiteDatabase db = a.getWritableDatabase();  
  
      String cx = e1.getText().toString();
      
      String sql = "Select nombre, rubro, precio from articulos where codigo='"+cx+"'";
	  
      Cursor fila = db.rawQuery(sql, null);
      
	  if(fila.moveToFirst())
	  {
		  e2.setText(fila.getString(0));
		  e3.setText(fila.getString(1));
		  e4.setText(fila.getString(2));
	  }
	  else
	  {
		  Toast t = Toast.makeText(this, "El articulo con codigo: " + cx + "\nNo se encontró registrado", Toast.LENGTH_LONG);
		  t.show(); 
	  }
	  
	  db.close(); 
  }  

  
  public void importe(View v) 
  {
	 
	  String precio = e4.getText().toString();
	  String cantidad = e5.getText().toString();
	  
	  if(precio.equals("")||cantidad.equals(""))
	  {
		  Toast t = Toast.makeText(this, "Falta ingresar algun dato",Toast.LENGTH_LONG);
		  t.show();
	  }
	  else
	  {
		  
		  float p = Float.parseFloat(precio);
		  int c = Integer.parseInt(cantidad);
		  
		  float imp = p * c;

		  DecimalFormat f = new DecimalFormat("0.00");
		  
		  t1.setText("$ " + f.format(imp));
		  
		  
	  }
	  
  }
  
  public void baja(View v)
  {
	  Administrador a = new Administrador(this,"comercio",null,1);
	  SQLiteDatabase db = a.getWritableDatabase();  
  
      String cx = e1.getText().toString();    
       
      int codx = Integer.parseInt(cx);       
      
      int cantidad = db.delete("articulos", "codigo='" + codx + "'", null);
      
      if (cantidad > 0)
      {
    	  Toast t = Toast.makeText(this, "El articulo para el codigo: " + cx + "\nFue eliminado correctamente", Toast.LENGTH_LONG);
    	  t.show();
      }
      else
      {
    	  Toast t = Toast.makeText(this, "El articulo para el codigo: " + cx + "\nNo se encontro en la base de Datos", Toast.LENGTH_LONG);
    	  t.show();
      }  	  
      
      limpiar(v);
      
      db.close();
      
  }
	
  public void modificacion(View v)
  {
	  Administrador a = new Administrador(this,"comercio",null,1);
	  SQLiteDatabase db = a.getWritableDatabase();  
  
      // Datos nuevos
	  
	  String cx = e1.getText().toString();    
      String nx = e2.getText().toString(); 
      String rx = e3.getText().toString(); 
      String px = e4.getText().toString(); 
      
      ContentValues registro = new ContentValues();
      registro.put("codigo", cx);
      registro.put("nombre", nx);
      registro.put("rubro", rx);
      registro.put("precio", px);
      
      int cantidad = db.update("articulos", registro, "codigo='" + cx + "'", null);
      
      if (cantidad > 0)
      {
    	  Toast t = Toast.makeText(this, "El articulo para el codigo: " + cx + "\nFue Actualizado correctamente", Toast.LENGTH_LONG);
    	  t.show();
      }
      else
      {
    	  Toast t = Toast.makeText(this, "El articulo para el codigo: " + cx + "\nNo se encontro en la base de Datos", Toast.LENGTH_LONG);
    	  t.show();
      }  	  
      
      limpiar(v);     
      
      
      
      db.close();    
  }
  
  
  
}
