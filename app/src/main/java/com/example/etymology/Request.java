package com.example.etymology;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.constraint.ConstraintLayout;
import android.widget.EditText;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;

import javax.net.ssl.HttpsURLConnection;

public class Request extends AsyncTask<String,Integer,String>
{
    final String app_id = "dcbe5d78";
    final String app_key = "81b2c239ccd988a9be74b86f8a8291b0";
   Context context;
   TextView wordOrigin;
  public Request(Context context,TextView wordOrigin)
    { this.context=context;
        this.wordOrigin=wordOrigin;

    }



    @Override
    protected String doInBackground(String...params){


        try {
            URL url = new URL(params[0]);
            HttpsURLConnection urlConnection = (HttpsURLConnection) url.openConnection();
            urlConnection.setRequestProperty("Accept","application/json");
            urlConnection.setRequestProperty("app_id",app_id);
            urlConnection.setRequestProperty("app_key",app_key);

            // read the output from the server
            BufferedReader reader = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));
            StringBuilder stringBuilder = new StringBuilder();

            String line = null;
            while ((line = reader.readLine()) != null) {
                stringBuilder.append(line + "\n");
            }

            return stringBuilder.toString();

        }
        catch (Exception e) {
            e.printStackTrace();
            return e.toString();
        }



    }
    @Override
    protected void onPostExecute(String task)
    {
        super.onPostExecute(task);
        try
        {//results
            JSONObject object=new JSONObject(task);
            JSONArray results=object.getJSONArray("results");

            //lexicalEntries
             JSONObject lEntries=results.getJSONObject(0);
             JSONArray array=lEntries.getJSONArray("lexicalEntries");

             //entries
            JSONObject entries =array.getJSONObject(0);
        JSONArray e=entries.getJSONArray("entries");

        //etymologies
        JSONObject jsonObject=e.getJSONObject(0);
        JSONArray etymologyArray=jsonObject.getJSONArray("etymologies");

        String etymologies=etymologyArray.getString(0);
         wordOrigin.setText(etymologies);


        }
        catch(JSONException e)
        {
            e.printStackTrace();
        }

    }

}
