package edu.sjsu.android.temperatureconverter ;

public class ConverterUtil
{
    public static float convertFahrenheitToCelsius(float fahrenheit) {return ((fahrenheit - 32) * 5 / 9) ;} // from F to C
    public static float convertCelsiusToFahrenheit(float celsius) {return ((celsius * 9) / 5) + 32 ;}   // from C to F
}
