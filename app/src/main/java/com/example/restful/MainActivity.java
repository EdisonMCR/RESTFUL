package com.example.restful;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import WebService.Asynchtask;
import WebService.WebService;

public class MainActivity extends AppCompatActivity implements Asynchtask {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void Mostrar (View view) {
        Map<String, String> datos = new HashMap<String, String>();
        WebService ws= new WebService(
                "https://jsonplaceholder.typicode.com/users",
                datos, MainActivity.this, MainActivity.this);
        ws.execute("GET");

    }

    public void btlimpiar (View view){


        TextView consulta = (TextView)findViewById(R.id.textView);
         consulta.setText("");

    }

    @Override
    public void processFinish(String result) throws JSONException {
        String lstEmail="";
        String lstNombre="";
        JSONArray listaJson = new JSONArray(result);

        for (int i=0; i<listaJson.length();i++)
        {
            JSONObject info=listaJson.getJSONObject(i);
            lstEmail=lstEmail+"\n"+info.getString("email").toString();
            lstNombre=lstNombre+"\n"+info.getString("name").toString();
        }

        TextView consulta = (TextView)findViewById(R.id.textView);
        consulta.setText("======>Nombres<======"+"\n"
        +lstNombre+"\n"+"\n"+"======>Correo<======"+"\n"+lstEmail);

    }
}