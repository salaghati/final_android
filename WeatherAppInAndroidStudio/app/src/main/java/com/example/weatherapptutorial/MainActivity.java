/*
This code makes a weather app.
The code starts by importing the necessary packages.
The code then defines a main class.
The code then defines a few constants.
The code then defines a few variables.
The code then overrides the onCreate method.
The onCreate method sets the view and initializes the variables.
The onCreate method then sets an onClickListener to the mCityFinder variable.
The code then overrides the onResume method.
The onResume method checks if there is any city variable that was passed in the intent.
If there is, the getWeatherForNewCity method is called.
If there isn't, the getWeatherForCurrentLocation method is called.
The code then creates a getWeatherForNewCity method.
The getWeatherForNewCity method creates a RequestParams variable and sets the q parameter to the city variable.
The getWeatherForNewCity method then calls the letsdoSomeNetworking method.
The code then creates a getWeatherForCurrentLocation method.
The getWeatherForCurrentLocation method creates a LocationManager and LocationListener variable.
The getWeatherForCurrentLocation method then checks for permissions.
The getWeatherForCurrentLocation method then requests location updates.
The code then overrides the onRequestPermissionsResult method.
The onRequestPermissionsResult method grants permissions if the request code equals the REQUEST_CODE constant.
The code then creates a letsdoSomeNetworking method.
The letsdoSomeNetworking method creates an AsyncHttpClient and calls the get method.
The letsdoSomeNetworking method then shows a toast if the data has been received.
The letsdoSomeNetworking method then updates the UI.
The letsdoSomeNetworking method then shows a toast if the data could not be received.
The code then creates an updateUI method.
The updateUI method sets the text and image of the variables to match the current weather.
The code then overrides the onPause method.
The onPause method removes location updates.
*/
package com.example.weatherapptutorial;

import androidx.annotation.NonNull;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import org.json.JSONObject;

import cz.msebera.android.httpclient.Header;

public class MainActivity extends AppCompatActivity {


    final String APP_ID = "5f472d916ca61ef3232f9c18748b3303";
    final String WEATHER_URL = "https://api.openweathermap.org/data/2.5/weather";

    final long MIN_TIME = 5000;
    final float MIN_DISTANCE = 1000;
    final int REQUEST_CODE = 101;


    String Location_Provider = LocationManager.GPS_PROVIDER;

    TextView NameofCity, weatherState, Temperature, Wind,suggest;
    ImageView mweatherIcon;

    RelativeLayout mCityFinder;


    LocationManager mLocationManager;
    LocationListener mLocationListner;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        weatherState = findViewById(R.id.weatherCondition);
        Temperature = findViewById(R.id.temperature);
        mweatherIcon = findViewById(R.id.weatherIcon);
        mCityFinder = findViewById(R.id.cityFinder);
        NameofCity = findViewById(R.id.cityName);
        Wind = findViewById(R.id.wind_text_view);
        suggest=findViewById(R.id.go_out);



        mCityFinder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, cityFinder.class);
                startActivity(intent);
            }
        });

    }

 /*   @Override
   protected void onResume() {
       super.onResume();
       getWeatherForCurrentLocation();
    }*/

    @Override
    protected void onResume() {
        super.onResume();
        Intent mIntent = getIntent();
        String city = mIntent.getStringExtra("City");
        if(city != null) {
            getWeatherForNewCity(city);
        } else {
            if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED
                    || ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
                getWeatherForCurrentLocation();
            } else {
                ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, REQUEST_CODE);
            }
        }
    }



    private void getWeatherForNewCity(String city)
    {
        RequestParams params=new RequestParams();
        params.put("q",city);
        params.put("appid",APP_ID);
        letsdoSomeNetworking(params);

    }




    private void getWeatherForCurrentLocation() {

        getWeatherForNewCity("ho chi minh");
//        mLocationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
//        mLocationListner = new LocationListener() {
//            @Override
//            public void onLocationChanged(Location location) {
//                Log.d("MainActivity", "Location has changed.");
//
//                String Latitude = String.valueOf(location.getLatitude());
//                String Longitude = String.valueOf(location.getLongitude());
//
//                Log.d("MainActivity", "New Latitude: " + Latitude);
//                Log.d("MainActivity", "New Longitude: " + Longitude);
//
//                RequestParams params = new RequestParams();
//                params.put("lat" ,Latitude);
//                params.put("lon",Longitude);
//                params.put("appid",APP_ID);
//                letsdoSomeNetworking(params);
//            }
//
//
//            @Override
//            public void onStatusChanged(String provider, int status, Bundle extras) {
//
//            }
//
//            @Override
//            public void onProviderEnabled(String provider) {
//
//            }
//
//            @Override
//            public void onProviderDisabled(String provider) {
//                //not able to get location
//            }
//        };
//
//
//        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
//            // TODO: Consider calling
//            //    ActivityCompat#requestPermissions
//            // here to request the missing permissions, and then overriding
//            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
//            //                                          int[] grantResults)
//            // to handle the case where the user grants the permission. See the documentation
//            // for ActivityCompat#requestPermissions for more details.
//            ActivityCompat.requestPermissions(this,new String[]{Manifest.permission.ACCESS_FINE_LOCATION},REQUEST_CODE);
//            return;
//        }
//        mLocationManager.requestLocationUpdates(Location_Provider, MIN_TIME, MIN_DISTANCE, mLocationListner);

    }


    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        if(requestCode==REQUEST_CODE) {
            if(grantResults.length>0 && grantResults[0]==PackageManager.PERMISSION_GRANTED) {
                Toast.makeText(MainActivity.this,"Location permission granted",Toast.LENGTH_SHORT).show();
                getWeatherForCurrentLocation();
            } else {
                Toast.makeText(MainActivity.this,"Location permission denied",Toast.LENGTH_SHORT).show();
                // user denied the permission
            }
        }
    }




    private  void letsdoSomeNetworking(RequestParams params)
    {
        AsyncHttpClient client = new AsyncHttpClient();
        client.get(WEATHER_URL,params,new JsonHttpResponseHandler()
        {
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {

                Toast.makeText(MainActivity.this,"Understand! ",Toast.LENGTH_SHORT).show();

                weatherData weatherD=weatherData.fromJson(response);
                updateUI(weatherD);


                // super.onSuccess(statusCode, headers, response);
            }


            @Override
            public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONObject errorResponse) {
                //super.onFailure(statusCode, headers, throwable, errorResponse);
            }
        });



    }

    private  void updateUI(weatherData weather){

        Wind.setText("Wind speed: " +Double.toString(weather.getmWind())+" ms");
        Temperature.setText(weather.getmTemperature());
        NameofCity.setText(weather.getMcity());
        weatherState.setText(weather.getmWeatherType());
        int resourceID=getResources().getIdentifier(weather.getMicon(),"drawable",getPackageName());
        mweatherIcon.setImageResource(resourceID);
        int condition = weather.getmCondition();
        String text = "no suggestions";
        if(condition>=0 && condition<=300)    // Checking if condition is in range 0 to 300
        {
            text = "You shouldn't go out!! It is thunder outside bitch";    // return string value Shouldn't
        }
        else if(condition>=300 && condition<=500)    // Checking if condition is in range 300 to 500
        {
            text = "Go out but remember to bring a coat!!!!!";   // return string value Go Out
        }
        else if(condition>=500 && condition<=600)    // Checking if condition is in range 500 to 600
        {
            text = "You shouldn't go out!! It's raining outside ";   // return string value Shouldn't Go Out
        }
        else  if(condition>=600 && condition<=700)    // Checking if condition is in range 600 to 700
        {
            text = "You shouldn't go out!! It's snow outside ";   // return string value Shouldn't Go Out
        }
        else if(condition>=701 && condition<=771)    // Checking if condition is in range 701 to 771
        {
            text = "You should go out!!  ";   // return string value Should Go Out
        }

        else if(condition>=772 && condition<=800)    // Checking if condition is in range 772 to 800
        {
            text = "You should go out!!  ";   // return string value Should Go Out
        }
        else if(condition==800)    // Checking if condition is 800
        {
            text = "You should go out!!  ";   // return string value Should Go Out
        }
        else if(condition>=801 && condition<=804)    // Checking if condition is in range 801 to 804
        {
            text = "You should go out!!  ";   // return string value Should Go Out
        }
        else  if(condition>=900 && condition<=902)    // Checking if condition is in range 900 to 902
        {
            text = "You shouldn't go out!! It is thunder outside bitch";    // return string value Shouldn't
        }
        if(condition==903)    // Checking if condition is 903
        {
            text = "You shouldn't go out!! It's snow outside ";   // return string value Shouldn't Go Out
        }
        if(condition==904)    // Checking if condition is 904
        {
            text = "You should go out!! It's sunny ";   // return string value Should Go Out
        }
        if(condition>=905 && condition<=1000)    // Checking if condition is in range 905 to 1000
        {
            text = "You shouldn't go out!! It is thunder outside bitch";    // return string value Shouldn't
        }
        suggest.setText(text);


    }

    @Override
    protected void onPause() {
        super.onPause();
        if(mLocationManager!=null)
        {
            mLocationManager.removeUpdates(mLocationListner);
        }
    }
}