package com.maradroid.routeapp;


import android.os.AsyncTask;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by mara on 4/1/15.
 */
public class AsyncDownload extends AsyncTask<String, Void, Object[]> {

    private ArrayList<RouteObject> routeObjectArrayList;


    private SetAdapterCallback callback;

    public void setCallback(SetAdapterCallback callback) {
        this.callback = callback;
    }

    @Override
    protected Object[] doInBackground(String... params) {

        routeObjectArrayList = new ArrayList<RouteObject>();
        String id=null, duration=null, title=null, content=null, slika_id = null;
        //JSONObject comments;
        Object[] obj = new Object[1];



        String response = Http.getInstance().post(params[0]);

        JSONArray data = null;
        try {
            data = new JSONArray(response);
        } catch (JSONException e) {
            e.printStackTrace();
            Log.e("AsyncDownloader", "error getting json array");
         }


            for (int i = 0; i < data.length(); i++) {


                try {

                    id = data.getJSONObject(i).getString("id");
                    duration = data.getJSONObject(i).getJSONObject("meta").getString("duration");
                    title = data.getJSONObject(i).getJSONObject("translations").getJSONObject("2").getString("title");
                    content = data.getJSONObject(i).getJSONObject("translations").getJSONObject("2").getString("content");
                     slika_id = data.getJSONObject(i).getJSONObject("slike").getString("0");
                    ArrayList<String> koordinate = new ArrayList<String>();
                    for(int j = 0; j< 131; j++) {
                        koordinate.add(data.getJSONObject(i).getJSONObject("meta").getJSONArray("coordinates").getJSONObject(j).getString("lat"));
                        koordinate.add(data.getJSONObject(i).getJSONObject("meta").getJSONArray("coordinates").getJSONObject(j).getString("lng"));
                    }

                    routeObjectArrayList.add(new RouteObject(id,
                            duration,
                            title,
                            content,
                            slika_id, koordinate));




                } catch (JSONException e) {
                    e.printStackTrace();
                    Log.e("AsyncDownloader", "error making object");
                }



            }


        obj[0] = routeObjectArrayList;

        return obj;
    }

    @Override
    protected void onPostExecute(Object[] objects) {
        super.onPostExecute(objects);


                callback.onRequestCompleted(objects[0]);

    }
}
