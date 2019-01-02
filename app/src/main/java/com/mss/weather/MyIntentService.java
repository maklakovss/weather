package com.mss.weather;

import android.app.IntentService;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.ResultReceiver;

import java.util.ArrayList;


public class MyIntentService extends IntentService {

    public static final String ACTION_GET_CITIES = "com.mss.weather.action.GET_CITIES";
    public static final String CITY_NAME_TEMPLATE_KEY = "com.mss.weather.extra.CITY_NAME_TEMPLATE_KEY";
    public static final String RESULT_RECEIVER_KEY = "com.mss.weather.extra.RESULT_RECEIVER_KEY";
    public static final String RESULT_CITIES_NAME_LIST_KEY = "com.mss.weather.extra.RESULT_RECEIVER_KEY";
    public static final int RESULT_SUCCESS = 1;
    public static final int RESULT_ERROR = 0;

    public MyIntentService() {
        super("MyIntentService");
    }

    public static void startActionGetCityNames(Context context, String cityNameTemplate) {
        Intent intent = new Intent(context, MyIntentService.class);
        intent.setAction(ACTION_GET_CITIES);
        intent.putExtra(CITY_NAME_TEMPLATE_KEY, cityNameTemplate);
        context.startService(intent);
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        ResultReceiver resultReceiver = intent.getParcelableExtra(RESULT_RECEIVER_KEY);
        if (intent != null) {
            final String action = intent.getAction();
            if (ACTION_GET_CITIES.equals(action)) {
                final String cityNameTemplate = intent.getStringExtra(CITY_NAME_TEMPLATE_KEY);
                handleActionFoo(cityNameTemplate, resultReceiver);
            }
        }
    }

    private void handleActionFoo(String cityNameTemplate, final ResultReceiver resultReceiver) {
        AsyncTask<String, Void, ArrayList<String>> task = new AsyncTask<String, Void, ArrayList<String>>() {
            @Override
            protected ArrayList<String> doInBackground(String... strings) {
                //обращение на сервер за списком городов у которых название удовлетворяет шаблону
                ArrayList<String> listCities = new ArrayList<>();
                listCities.add("Москва");
                listCities.add("Санкт-Петербург");
                listCities.add("Тюмень");

                return listCities;
            }

            @Override
            protected void onPostExecute(ArrayList<String> listCities) {
                super.onPostExecute(listCities);
                Bundle bundle = new Bundle();
                bundle.putStringArrayList(RESULT_CITIES_NAME_LIST_KEY, listCities);
                resultReceiver.send(RESULT_SUCCESS, bundle);
            }
        };

    }

}

