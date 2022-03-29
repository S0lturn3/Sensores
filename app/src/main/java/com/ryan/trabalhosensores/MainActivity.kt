package com.ryan.trabalhosensores

import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.hardware.SensorManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import com.ryan.trabalhosensores.R


class MainActivity : AppCompatActivity(), SensorEventListener {

    private lateinit var sensorManager: SensorManager
    private var luminosity: Sensor? = null
    private var accelerometer: Sensor? = null
    private var proximity: Sensor? = null
    private var rotation: Sensor? = null
    private var magneticField: Sensor? = null

    private lateinit var proxText: TextView
    private lateinit var luminText: TextView
    private lateinit var accelText: TextView
    private  lateinit var rotatText: TextView
    private  lateinit var magnetText: TextView


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        proxText = findViewById(R.id.sensorDataProx)
        luminText = findViewById(R.id.sensorDataLum)
        accelText = findViewById(R.id.sensorDataAcc)
        rotatText = findViewById(R.id.sensorDataRot)
        magnetText = findViewById(R.id.sensorDataMag)

        setUpSensorStuff()
    }

    private fun setUpSensorStuff() {
        sensorManager = getSystemService(SENSOR_SERVICE) as SensorManager

        luminosity = sensorManager.getDefaultSensor(Sensor.TYPE_LIGHT)
        accelerometer = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER)
        proximity = sensorManager.getDefaultSensor(Sensor.TYPE_PROXIMITY)
        rotation = sensorManager.getDefaultSensor(Sensor.TYPE_GAME_ROTATION_VECTOR)
        magneticField = sensorManager.getDefaultSensor(Sensor.TYPE_MAGNETIC_FIELD)

    }

    override fun onSensorChanged(event: SensorEvent?)
    {
        if (event?.sensor?.type == Sensor.TYPE_LIGHT) {
            val light1 = event.values[0]
            proxText.text = "Luminosidade: ${light1}\n${brightness1(light1)}"
            luminText.text.toString()
        }

        if (event?.sensor?.type == Sensor.TYPE_ACCELEROMETER){
            val light2 = event.values[0]
            accelText.text = "Aceleração: ${light2}\n${brightness2(light2)} "
            accelText.text.toString()
        }

        if (event?.sensor?.type == Sensor.TYPE_PROXIMITY){
            val light2 = event.values[0]
            luminText.text = "Aproximação: ${light2}\n${brightness3(light2)} "
            luminText.text.toString()
        }

        if (event?.sensor?.type == Sensor.TYPE_GAME_ROTATION_VECTOR){
            val light2 = event.values[0]
            rotatText.text = "Rotação: ${light2}\n${brightness4(light2)} "
            rotatText.text.toString()
        }

        if (event?.sensor?.type == Sensor.TYPE_MAGNETIC_FIELD){
            val light2 = event.values[0]
            magnetText.text = "Bússola: ${light2}\n${brightness5(light2)} "
            magnetText.text.toString()
        }
    }

    private fun brightness1(brightness: Float): String {
        return when (brightness.toInt()) {
            0 -> "Sem luz"
            in 1..10 -> "Escuro"
            in 11..50 -> "Semi normal"
            in 51..5000 -> "Normal"
            in 5001..25000 -> "Claro"
            else -> "Muito claro"
        }
    }

    private  fun brightness2(brightness: Float):String{
        return  when (brightness.toInt()){
            0 -> "Parado"
            else -> "Em movimento"
        }
    }

    private  fun brightness3(brightness: Float):String{
        return when (brightness.toInt()){
            0 -> "Perto"
            in 0..10 -> "Longe"
            else -> "[?]"
        }
    }

    private  fun brightness4(brightness: Float):String{
        return when (brightness.toInt()){
            0-> "Parado"
            in 0..10-> "Girando"
            else -> "[?]"
        }
    }

    private  fun brightness5(brightness: Float):String{
        return when (brightness.toInt()){
            0 -> "Norte"
            in 1..89 -> "Nordeste"
            90 -> "Leste"
            in 91..179 -> "Sudeste"
            180 -> "Sul"
            in 181..269 -> "Sudoeste"
            270 -> "Oeste"
            in 271..359 -> "Noroeste"
            else -> "[?]"
        }
    }

    override fun onResume() {
        super.onResume()
        // Register a listener for the sensor.
        sensorManager.registerListener(this,proximity, SensorManager.SENSOR_DELAY_NORMAL)
        sensorManager.registerListener(this,luminosity, SensorManager.SENSOR_DELAY_NORMAL)
        sensorManager.registerListener(this,accelerometer, SensorManager.SENSOR_DELAY_NORMAL)
        sensorManager.registerListener(this,rotation, SensorManager.SENSOR_DELAY_NORMAL)
        sensorManager.registerListener(this,magneticField, SensorManager.SENSOR_DELAY_NORMAL)
    }

    override fun onPause() {
        super.onPause()
        sensorManager.unregisterListener(this)
    }


    override fun onAccuracyChanged(sensor: Sensor?, accuracy: Int) { return }
}
