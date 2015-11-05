package com.yaowen.sensorbigtest;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity implements SensorEventListener {
    private EditText etOrientation, etMagnetic, etTemerature, etLight, etPressure;
    private SensorManager sensorManager;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //UI控件
        etOrientation = (EditText) findViewById(R.id.etOrientation);
        etMagnetic = (EditText) findViewById(R.id.etMagnetic);
        etTemerature = (EditText) findViewById(R.id.etTemerature);
        etLight = (EditText) findViewById(R.id.etLight);
        etPressure = (EditText) findViewById(R.id.etPressure);
        // 获取真机的传感器管理服务
        // sensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        // 获取传感器模拟器的传感器管理服务
        sensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        Sensor sensor = sensorManager.getDefaultSensor(Sensor.TYPE_ALL);

    }

    @Override
    protected void onResume() {
        super.onResume();
        // 为系统的方向传感器注册监听器
        sensorManager.registerListener(this,
                sensorManager.getDefaultSensor(Sensor.TYPE_ORIENTATION),
                SensorManager.SENSOR_DELAY_GAME);
        // 为系统的磁场传感器注册监听器
        sensorManager.registerListener(this,
                sensorManager.getDefaultSensor(Sensor.TYPE_MAGNETIC_FIELD),
                SensorManager.SENSOR_DELAY_GAME);
        // 为系统的温度传感器注册监听器
        sensorManager.registerListener(this,
                sensorManager.getDefaultSensor(Sensor.TYPE_TEMPERATURE),
                //sensorManager.getDefaultSensor(Sensor.TYPE_AMBIENT_TEMPERATURE),
                SensorManager.SENSOR_DELAY_GAME);
        // 为系统的光传感器注册监听器
        sensorManager.registerListener(this,
                sensorManager.getDefaultSensor(Sensor.TYPE_LIGHT),
                SensorManager.SENSOR_DELAY_GAME);
        // 为系统的压力传感器注册监听器
        sensorManager.registerListener(this,
                sensorManager.getDefaultSensor(Sensor.TYPE_PRESSURE),
                SensorManager.SENSOR_DELAY_GAME);
    }

    @Override
    protected void onStop() {
        // 程序退出时取消注册传感器监听器
        sensorManager.unregisterListener(this);
        super.onStop();
    }

    @Override
    protected void onPause() {
        // 程序暂停时取消注册传感器监听器
        sensorManager.unregisterListener(this);
        super.onPause();
    }

    @Override
    protected void onDestroy() {
        // 程序销毁时取消注册传感器监听器
        sensorManager.unregisterListener(this);
        super.onDestroy();

    }


    @Override
    public void onSensorChanged(SensorEvent event) {
        float[] values = event.values;
        // // 真机上获取触发event的传感器类型
        // int sensorType = event.sensor.getType();
        // 模拟器上获取触发event的传感器类型
        Sensor sensorType = event.sensor;
        StringBuilder sb = null;
        sb = new StringBuilder();
        // 判断是哪个传感器发生改变
        if (event.sensor == sensorManager.getDefaultSensor(Sensor.TYPE_ORIENTATION)) {
            sb.append("绕Z轴转过的角度：");
            sb.append(values[0] + "\n");
            sb.append("绕X轴转过的角度：");
            sb.append(values[1] + "\n");
            sb.append(
                    "绕Y轴转过的角度：");
            sb.append(values[2]);
            etOrientation.setText(sb.toString());
        }
        if (event.sensor == sensorManager.getDefaultSensor(Sensor.TYPE_LIGHT)) {
            sb.append("当前光的强度为：");
            sb.append(values[0]);
            etLight.setText(sb.toString());
        }
        if (event.sensor == sensorManager.getDefaultSensor(Sensor.TYPE_MAGNETIC_FIELD)) {
            sb.append("X方向上的角度：");
            sb.append(values[0] + "\n");
            sb.append("Y方向上的角度：");
            sb.append(values[1] + "\n");
            sb.append("Z方向上的角度：");
            sb.append(values[2]);
            etMagnetic.setText(sb.toString());
        }
        if (event.sensor == sensorManager.getDefaultSensor(Sensor.TYPE_TEMPERATURE)) {
            sb.append("当前温度为：");
            sb.append(values[0]);
            etTemerature.setText(sb.toString());
        }
        if (event.sensor == sensorManager.getDefaultSensor(Sensor.TYPE_PRESSURE)) {
            sb.append("当前压力为：");
            sb.append(values[0]);
            etPressure.setText(sb.toString());
        }
        /*switch (sensorType) {
            // 方向传感器
            case Sensor.TYPE_ORIENTATION:
                sb = new StringBuilder();
                sb.append("绕Z轴转过的角度：");
                sb.append(values[0]);
                sb.append("绕X轴转过的角度：");
                sb.append(values[1]);
                sb.append(
                        "绕Y轴转过的角度：");
                sb.append(values[2]);
                etOrientation.setText(sb.toString());
                break;
            // 磁场传感器
            case Sensor.TYPE_MAGNETIC_FIELD:
                sb = new StringBuilder();
                sb.append("X方向上的角度：");
                sb.append(values[0]);
                sb.append("Y方向上的角度：");
                sb.append(values[1]);
                sb.append("Z方向上的角度：");
                sb.append(values[2]);
                etMagnetic.setText(sb.toString());
                break;
            // 温度传感器
            case Sensor.TYPE_TEMPERATURE:
                sb = new StringBuilder();
                sb.append("当前温度为：");
                sb.append(values[0]);
                etTemerature.setText(sb.toString());
                break;
            // 光传感器
            case Sensor.TYPE_LIGHT:
                sb = new StringBuilder();
                sb.append("当前光的强度为：");
                sb.append(values[0]);
                etLight.setText(sb.toString());
                break;
            // 压力传感器
            case Sensor.TYPE_PRESSURE:
                sb = new StringBuilder();
                sb.append("当前压力为：");
                sb.append(values[0]);
                etPressure.setText(sb.toString());
                break;
        }*/
    }


    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }
}
