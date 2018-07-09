# Button and LED (Java)

This Android Things sample demonstrates how to use a button input
UserDriver to listen to GPIO pin changes, generate and listen for key events
and change the state of an LED accordingly. Follow the [Peripheral I/O Codelab](https://codelabs.developers.google.com/codelabs/androidthings-peripherals) for step-by-step instructions on how to build a similar sample.

## Screenshots

![Button sample demo][demo-gif]

[(Watch the demo on YouTube)][demo-yt]

## Pre-requisites

- Android Things compatible board
- Android Studio 2.2+
- [Rainbow HAT for Android Things](https://shop.pimoroni.com/products/rainbow-hat-for-android-things) or the following individual components:
    - 1 LED
    - 1 push button
    - 2 resistors
    - jumper wires
    - 1 breadboard

## Schematics

If you have the Raspberry Pi [Rainbow HAT for Android Things](https://shop.pimoroni.com/products/rainbow-hat-for-android-things), just plug it onto your Raspberry Pi 3.

![Schematics for Raspberry Pi 3][schematics-png]

## Run on Android Things Starter Kit

If you have an Android Things Starter Kit, you can easily run this sample on your i.MX7D development board from the [Android Things Toolkit](https://play.google.com/store/apps/details?id=com.google.android.things.companion&hl=en) app.

To run the sample on your i.MX7D development board:
  1. Set up your device using Toolkit
  2. Navigate to the Apps tab
  3. Select Run next to the Button sample.
  4. Press the "A" button on your Rainbow HAT to light up the LED above the button.

![Running Button Sample on Toolkit][toolkit-jpg]

## Build and install

On Android Studio, click on the "Run" button.

If you prefer to run on the command line, type

```bash
./gradlew installDebug
adb shell am start com.example.androidthings.button/.ButtonActivity
```

If you have everything set up correctly, the LED will light up when you press
the button and light off when you release it.

## Enable auto-launch behavior

This sample app is currently configured to launch only when deployed from your
development machine. To enable the main activity to launch automatically on boot,
add the following `intent-filter` to the app's manifest file:

```xml
<activity ...>

    <intent-filter>
        <action android:name="android.intent.action.MAIN"/>
        <category android:name="android.intent.category.HOME"/>
        <category android:name="android.intent.category.DEFAULT"/>
    </intent-filter>

</activity>
```

## License

Copyright 2016 The Android Open Source Project, Inc.

Licensed to the Apache Software Foundation (ASF) under one or more contributor
license agreements.  See the NOTICE file distributed with this work for
additional information regarding copyright ownership.  The ASF licenses this
file to you under the Apache License, Version 2.0 (the "License"); you may not
use this file except in compliance with the License.  You may obtain a copy of
the License at

  http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.  See the
License for the specific language governing permissions and limitations under
the License.

[demo-yt]: https://www.youtube.com/watch?v=hKmPZryY_Qc&index=3&list=PLWz5rJ2EKKc-GjpNkFe9q3DhE2voJscDT
[demo-gif]: ../demo1.gif
[schematics-png]: ../rpi3_schematics.png
[toolkit-jpg]: ../toolkit_buttonsample.jpg
