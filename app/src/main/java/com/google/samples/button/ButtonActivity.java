package com.google.samples.button;

import android.app.Activity;
import android.os.Bundle;
import android.hardware.pio.Gpio;
import android.hardware.pio.PeripheralManagerService;
import android.system.ErrnoException;
import android.util.Log;

import com.google.brillo.driver.button.Button;
import com.google.samples.simplepio.BoardDefaults;

/**
 * Example of using Button driver for toggling a LED.
 *
 * This activity will flip the state of the LED GPIO pin whenever the button is pressed.
 * You need to connect an LED and a push button switch to pins specified in {@link BoardDefaults}.
 */
public class ButtonActivity extends Activity {
    private static final String TAG = "ButtonActivity";

    private Gpio mLedGpio;
    private Button mButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.i(TAG, "Starting ButtonActivity");

        PeripheralManagerService pioService = new PeripheralManagerService();
        try {
            Log.i(TAG, "Configuring GPIO");
            mLedGpio = pioService.openGpio(BoardDefaults.getGPIOForLED());
            mLedGpio.setDirection(Gpio.DIRECTION_OUT_INITIALLY_LOW);
            mButton = new Button(BoardDefaults.getGPIOForButton(),
                    Button.LogicState.PRESSED_WHEN_HIGH);
            mButton.setOnButtonEventListener(pressed -> {
                Log.d(TAG, "onButtonEvent:" + pressed);
                try {
                    mLedGpio.setValue(pressed);
                } catch (ErrnoException e) {
                    Log.e(TAG, "Error toggling GPIO pins", e);
                }
                return true;
            });
        } catch (ErrnoException e) {
            Log.e(TAG, "Error configuring GPIO pins", e);
        }
    }

    @Override
    protected void onDestroy(){
        super.onDestroy();
        if (mButton != null) {
            mButton.close();
            mButton = null;
        }
        if (mLedGpio != null) {
            mLedGpio.close();
            mLedGpio = null;
        }
    }
}
