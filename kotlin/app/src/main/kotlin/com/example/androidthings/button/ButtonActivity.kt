/*
 * Copyright 2016, The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.example.androidthings.button

import android.app.Activity
import android.os.Bundle
import android.util.Log
import android.view.KeyEvent

import com.google.android.things.contrib.driver.button.Button
import com.google.android.things.contrib.driver.button.ButtonInputDriver
import com.google.android.things.pio.Gpio
import com.google.android.things.pio.PeripheralManager

/**
 * Example of using Button driver for toggling a LED.
 *
 * This activity initialize an InputDriver to emit key events when the button GPIO pin state change
 * and flip the state of the LED GPIO pin.
 *
 * You need to connect an LED and a push button switch to pins specified in [BoardDefaults]
 * according to the schematic provided in the sample README.
 */

class ButtonActivity : Activity() {

    private lateinit var ledGpio: Gpio
    private lateinit var buttonInputDriver: ButtonInputDriver

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.i(TAG, "Starting ButtonActivity")

        val pioService = PeripheralManager.getInstance()

        Log.i(TAG, "Configuring GPIO pins")
        ledGpio = pioService.openGpio(BoardDefaults.gpioForLED)
        ledGpio.setDirection(Gpio.DIRECTION_OUT_INITIALLY_LOW)

        Log.i(TAG, "Registering button driver")
        // Initialize and register the InputDriver that will emit SPACE key events
        // on GPIO state changes.
        buttonInputDriver = ButtonInputDriver(
                BoardDefaults.gpioForButton,
                Button.LogicState.PRESSED_WHEN_LOW,
                KeyEvent.KEYCODE_SPACE)
        buttonInputDriver.register()
    }

    override fun onKeyDown(keyCode: Int, event: KeyEvent): Boolean {
        if (keyCode == KeyEvent.KEYCODE_SPACE) {
            // Turn on the LED
            setLedValue(true)
            return true
        }

        return super.onKeyDown(keyCode, event)
    }

    override fun onKeyUp(keyCode: Int, event: KeyEvent): Boolean {
        if (keyCode == KeyEvent.KEYCODE_SPACE) {
            // Turn off the LED
            setLedValue(false)
            return true
        }
        return super.onKeyUp(keyCode, event)
    }

    /**
     * Update the value of the LED output.
     */
    private fun setLedValue(value: Boolean) {
        Log.d(TAG, "Setting LED value to $value")
        ledGpio.value = value
    }

    override fun onStop() {
        buttonInputDriver.unregister()
        buttonInputDriver.close()

        ledGpio.close()

        super.onStop()
    }

    companion object {
        private val TAG = ButtonActivity::class.java.simpleName
    }
}
