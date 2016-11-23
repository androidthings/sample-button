package com.google.samples.button;

import android.os.Build;

@SuppressWarnings("WeakerAccess")
public class BoardDefaults {
    private static final String DEVICE_EDISON = "edison";
    private static final String DEVICE_RPI3 = "rpi3";
    private static final String DEVICE_NXP = "imx6ul";

    /**
     * Return the GPIO pin that the LED is connected on.
     * For example, on Intel Edison Arduino breakout, pin "IO13" is connected to an onboard LED
     * that turns on when the GPIO pin is HIGH, and off when low.
     */
    public static String getGPIOForLED() {
        // TODO: confirm preferred port for NXP
        switch (Build.DEVICE) {
            case DEVICE_EDISON:
                return "IO13";
            case DEVICE_RPI3:
                return "BCM6";
            case DEVICE_NXP:
                return "??";
            default:
                throw new IllegalStateException("Unknown Build.DEVICE " + Build.DEVICE);
        }
    }

    /**
     * Return the GPIO pin that the Button is connected on.
     */
    public static String getGPIOForButton() {
        // TODO: confirm preferred port for NXP
        switch (Build.DEVICE) {
            case DEVICE_EDISON:
                return "IO12";
            case DEVICE_RPI3:
                return "BCM21";
            case DEVICE_NXP:
                return "??";
            default:
                throw new IllegalStateException("Unknown Build.DEVICE " + Build.DEVICE);
        }
    }
}
