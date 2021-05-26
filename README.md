# Button and LED

This Android Things sample demonstrates how to use a button input
UserDriver to listen to GPIO pin changes, generate and listen for key events
and change the state of an LED accordingly.

> **Note:** The Android Things Console will be turned down for non-commercial
> use on January 5, 2022. For more details, see the
> [FAQ page](https://developer.android.com/things/faq).

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

![Schematics for Raspberry Pi 3](rpi3_schematics.png)

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
[demo-gif]: demo1.gif
