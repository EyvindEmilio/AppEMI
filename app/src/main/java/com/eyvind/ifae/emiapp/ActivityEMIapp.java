package com.eyvind.ifae.emiapp;

import android.app.Activity;
import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.activeandroid.ActiveAndroid;
import com.activeandroid.query.Select;
import com.eyvind.ifae.emiapp.DB.TABLES.test;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.InputStream;
import java.util.List;

public class ActivityEMIapp extends Activity {
    Button btnLogin;
    EditText inEmail;
    EditText inPassword;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_emiapp);
        btnLogin = (Button)findViewById(R.id.btnLogin);
        inEmail = (EditText)findViewById(R.id.inEmail);
        inPassword = (EditText)findViewById(R.id.inPassword);

        /*Intent n = new Intent(ActivityEMIapp.this,MainActivity.class);
        startActivity(n);*/
        ActiveAndroid.initialize(this);
/*
        test te = new test();
        te.category = "cat 1 ";
        te.name = "Eyvind";
        te.save();

        te = new test();
        te.category = "cat 2 ";
        te.name = "Emilio";
        te.save();

        te = new test();
        te.category = "cat 3 ";
        te.name = "Coaquira";
        te.save();

*/
        List<test> listas = new Select().from(test.class).where("name = ?","Eyvind").execute();

        for(int i = 0 ; i<listas.size();i++){
            System.out.println("---------------------------------------------");
            System.out.println("name: "+listas.get(i).name + "\t  Categoria: "+listas.get(i).category );
        }


        btnLogin.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                String email = inEmail.getText().toString();
                String password = inPassword.getText().toString();
                if(email.equals("") || password.equals("")){
                    Toast.makeText(getBaseContext(),"Ingrese correctamente los campos",Toast.LENGTH_LONG).show();
                    return;
                }
                postLogin login_task = new postLogin();
                login_task.execute(email,password);
            }
        });
    }

    public class postLogin extends AsyncTask<String, Void, String> {
        private final ProgressDialog dialog = new ProgressDialog(ActivityEMIapp.this);
        public InputStream is = null;
        @Override
        protected void onPreExecute() {
            dialog.setMessage("Ingresando..");
            dialog.show();
        }

        @Override
        protected String doInBackground(String... strings) {
            RestClient client = new RestClient(URLS.LOGIN);
            client.addParam("email", strings[0]);
            client.addParam("password", strings[1]);
            client.addHeader("content-type", "application/json");

            try {
                String response = client.executePost();
                return response;
            } catch (Exception e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(String result) {
            if (dialog != null && dialog.isShowing())
                dialog.dismiss();

            try {
                if (result != null) {
                    Toast.makeText(ActivityEMIapp.this, getMessage(result), Toast.LENGTH_LONG).show();
                } else {
                    Toast.makeText(ActivityEMIapp.this, "Revise su coneccion a internet", Toast.LENGTH_LONG).show();
                }
            } catch (JSONException e) {
                e.printStackTrace();
                Toast.makeText(ActivityEMIapp.this, "Error del servidor", Toast.LENGTH_LONG).show();
            }
            if (result != null) {
                System.out.println("result " + result);
                Log.i("RESULTADOS", result);
            } else {
                Toast.makeText(ActivityEMIapp.this,"Verifique su coneccion a internet",Toast.LENGTH_LONG).show();
            }
        }

        public String getMessage(String json) throws JSONException{
            JSONObject message = new JSONObject(json);
            return message.getString("Message");
        }

        public String getToken(String json) throws JSONException{
            JSONObject message = new JSONObject(json);
            return message.getString("Message");
        }
    }
}
