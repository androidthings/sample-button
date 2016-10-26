package com.google.samples.button;

import android.app.Activity;
import android.hardware.userdriver.InputDriver;
import android.hardware.userdriver.UserDriverManager;
import android.os.Bundle;
import android.hardware.pio.Gpio;
import android.hardware.pio.PeripheralManagerService;
import android.system.ErrnoException;
import android.util.Log;
import android.view.KeyEvent;

import com.google.brillo.driver.button.Button;

/**
 * Example of using Button driver for toggling a LED.
 *
 * This activity will flip the state of the LED GPIO pin whenever the button state changes.
 * You need to connect an LED and a push button switch to pins specified in {@link BoardDefaults}.
 */
public class ButtonActivity extends Activity {
    private static final String TAG = "ButtonActivity";

    // Linux input code for Escape
    private static final int KEY_CODE = 1;

    private Gpio mLedGpio;
    private Button mButton;
    private InputDriver mButtonDriver;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.i(TAG, "Starting ButtonActivity");

        PeripheralManagerService pioService = new PeripheralManagerService();
        try {
            Log.i(TAG, "Configuring GPIO pins");
            mLedGpio = pioService.openGpio(BoardDefaults.getGPIOForLED());
            mLedGpio.setDirection(Gpio.DIRECTION_OUT_INITIALLY_LOW);

            // Initialize GPIO for a button connected to Vcc through a pull-up
            mButton = new Button(BoardDefaults.getGPIOForButton(),
                    Button.LogicState.PRESSED_WHEN_LOW);

            Log.i(TAG, "Registering framework driver");
            mButtonDriver = mButton.createInputDriver(KEY_CODE);
            UserDriverManager.getManager().registerInputDriver(mButtonDriver);
        } catch (ErrnoException e) {
            Log.e(TAG, "Error configuring GPIO pins", e);
        }
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_ESCAPE) {
            // Turn on the LED
            setLedValue(true);
            return true;
        }

        return super.onKeyDown(keyCode, event);
    }

    @Override
    public boolean onKeyUp(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_ESCAPE) {
            // Turn off the LED
            setLedValue(false);
            return true;
        }

        return super.onKeyUp(keyCode, event);
    }

    /**
     * Update the value of the LED output.
     */
    private void setLedValue(boolean value) {
        try {
            mLedGpio.setValue(value);
        } catch (ErrnoException e) {
            Log.e(TAG, "Error updating GPIO value", e);
        }
    }

    @Override
    protected void onDestroy(){
        super.onDestroy();

        if (mButton != null) {
            UserDriverManager.getManager().unregisterInputDriver(mButtonDriver);
            mButton.close();
            mButton = null;
        }

        if (mLedGpio != null) {
            mLedGpio.close();
            mLedGpio = null;
        }
    }
}
