package com.example.weatherapptutorial;    // Package declaration

import org.json.JSONException;    // Import json exception
import org.json.JSONObject;    // Import json object

public class weatherData {    // Defining weatherData class

    private String mTemperature,micon,mcity,mWeatherType;    // Declaring string variables
    private int mCondition;    // Declaring integer variable

    private double mWind;    // Declaring double variable
    public static weatherData fromJson(JSONObject jsonObject)    // Declaring static method fromJson
    {

        try    // Try block
        {
            weatherData weatherD=new weatherData();    // Defining object of weatherData class
            weatherD.mcity=jsonObject.getString("name");    // Get String value of name from json object
            weatherD.mCondition=jsonObject.getJSONArray("weather").getJSONObject(0).getInt("id");    // Get integer value of id from json object
            weatherD.mWeatherType=jsonObject.getJSONArray("weather").getJSONObject(0).getString("main");    // Get String value of main from json object
            weatherD.mWind=jsonObject.getJSONObject("wind").getDouble("speed"); // Get String value of speed from json object
            weatherD.micon=updateWeatherIcon(weatherD.mCondition);    // Calling method updateWeatherIcon
            double tempResult=jsonObject.getJSONObject("main").getDouble("temp")-273.15;    // Get double value of temp from json object
            int roundedValue=(int)Math.rint(tempResult);    // Round off value of temp
            weatherD.mTemperature=Integer.toString(roundedValue);    // Convert integer variable to string
            return weatherD;    // return weatherD object
        }


        catch (JSONException e) {    // catch block
            e.printStackTrace();    // Print stack trace
            return null;    // return null
        }


    }


    private static String updateWeatherIcon(int condition)    // Declaring static method updateWeatherIcon
    {
        if(condition>=0 && condition<=300)    // Checking if condition is in range 0 to 300
        {
            return "thunderstrom1";    // return string value thunderstrom1
        }
        else if(condition>=300 && condition<=500)    // Checking if condition is in range 300 to 500
        {
            return "lightrain";    // return string value lightrain
        }
        else if(condition>=500 && condition<=600)    // Checking if condition is in range 500 to 600
        {
            return "shower";    // return string value shower
        }
        else  if(condition>=600 && condition<=700)    // Checking if condition is in range 600 to 700
        {
            return "snow2";    // return string value snow2
        }
        else if(condition>=701 && condition<=771)    // Checking if condition is in range 701 to 771
        {
            return "fog";    // return string value fog
        }

        else if(condition>=772 && condition<=800)    // Checking if condition is in range 772 to 800
        {
            return "overcast";    // return string value overcast
        }
        else if(condition==800)    // Checking if condition is 800
        {
            return "sunny";    // return string value sunny
        }
        else if(condition>=801 && condition<=804)    // Checking if condition is in range 801 to 804
        {
            return "cloudy";    // return string value cloudy
        }
        else  if(condition>=900 && condition<=902)    // Checking if condition is in range 900 to 902
        {
            return "thunderstrom1";    // return string value thunderstrom1
        }
        if(condition==903)    // Checking if condition is 903
        {
            return "snow1";    // return string value snow1
        }
        if(condition==904)    // Checking if condition is 904
        {
            return "sunny";    // return string value sunny
        }
        if(condition>=905 && condition<=1000)    // Checking if condition is in range 905 to 1000
        {
            return "thunderstrom2";    // return string value thunderstrom2
        }

        return "dunno";    // return string value dunno


    }

    public String getmTemperature() {    // Declaring getmTemperature method
        return mTemperature+"°C";    // Return string value of mTemperature
    }

    public String getMicon() {    // Declaring getMicon method
        return micon;    // Return string value of micon
    }

    public String getMcity() {    // Declaring getMcity method
        return mcity;    // Return string value of mcity
    }

    public String getmWeatherType() {    // Declaring getmWeatherType method
        return mWeatherType;    // Return string value of mWeatherType
    }

    public double getmWind() {
        return mWind;
    }

    public int getmCondition() {
        return mCondition;
    }
}
