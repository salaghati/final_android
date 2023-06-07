package com.example.WeatherApp;    // Khai báo package

import org.json.JSONException;    // Nhập lớp JSONException để xử lý các ngoại lệ liên quan đến JSON
import org.json.JSONObject;    // Nhập lớp JSONObject để xử lý đối tượng JSON

public class weatherData {    // Khai báo class weatherData

    // Khai báo các thuộc tính của lớp
    private String mTemperature,micon,mcity,mWeatherType;
    private int mCondition;
    private double mWind;

    // Phương thức static từ JSON cho phép ta chuyển đổi JSONObject thành một đối tượng weatherData
    public static weatherData fromJson(JSONObject jsonObject)
    {
        try
        {
            weatherData weatherD=new weatherData();    // Tạo một đối tượng weatherData mới
            weatherD.mcity=jsonObject.getString("name");    // Lấy tên thành phố từ JSONObject
            weatherD.mCondition=jsonObject.getJSONArray("weather").getJSONObject(0).getInt("id");    // Lấy ID điều kiện thời tiết từ JSONObject
            weatherD.mWeatherType=jsonObject.getJSONArray("weather").getJSONObject(0).getString("main");    // Lấy loại thời tiết từ JSONObject
            weatherD.mWind=jsonObject.getJSONObject("wind").getDouble("speed"); // Lấy tốc độ gió từ JSONObject
            weatherD.micon=updateWeatherIcon(weatherD.mCondition);    // Cập nhật icon thời tiết dựa trên ID điều kiện thời tiết
            double tempResult=jsonObject.getJSONObject("main").getDouble("temp")-273.15;    // Lấy nhiệt độ từ JSONObject và chuyển đổi từ Kelvin sang Celsius
            int roundedValue=(int)Math.rint(tempResult);    // Làm tròn nhiệt độ
            weatherD.mTemperature=Integer.toString(roundedValue);    // Chuyển nhiệt độ thành chuỗi
            return weatherD;    // Trả về đối tượng weatherData
        }

        // Xử lý ngoại lệ JSONException
        catch (JSONException e) {
            e.printStackTrace();
            return null;
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