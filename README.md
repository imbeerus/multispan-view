# multispan-view
[![API](https://img.shields.io/badge/API-15%2B-orange.svg)](https://android-arsenal.com/api?level=15)
[![License](https://img.shields.io/badge/license-Apache%202-red.svg)](https://www.apache.org/licenses/LICENSE-2.0)
[![](https://img.shields.io/badge/docs-dokka-blue.svg?logo=kotlin)]()
![Android CI](https://github.com/lndmflngs/multispan-view/workflows/Android%20CI/badge.svg?branch=master)
[![](https://jitpack.io/v/lndmflngs/multispan-view.svg)](https://jitpack.io/#lndmflngs/multispan-view)
[![](https://img.shields.io/badge/apk-demo-blueviolet?logo=android)][1]

Replace your ConstraintLayout with TextView and power of Spans 💪

Allow to apply custom Spans and [change gravity of Drawable][2]

### Features
* Optimized way to use text styling and drawables with TextView
* RobotoSpanView or if you need custom fonts FontSpanView
* Change style of spans in your way (by [Spannable][3] or [setSpanOnResult][4])
* Right-to-left Support

## Download
Download the [latest release][1] or grab via Gradle:

```
allprojects {
    repositories {
        ...
        maven { url 'https://jitpack.io' }
    }
}
```
```
dependencies {
        implementation 'com.github.lndmflngs:multispan-view:1.0.0'
}
```

## Issue Tracking
Found a bug? Have an idea for an improvement? Feel free to [add an issue](../../issues)

## License

```
Copyright (C) 2020 Ivan Zinovyev (https://github.com/lndmflngs)
Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at
http://www.apache.org/licenses/LICENSE-2.0
Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.
```
[1]: https://github.com/lndmflngs/multispan-view/releases/latest
[2]: https://github.com/lndmflngs/compound-text-view
[3]: https://github.com/lndmflngs/multispan-view/blob/master/multispan-library/src/main/java/com/lockwood/multispan/spannable/Spannable.kt
[4]: https://github.com/lndmflngs/multispan-view/blob/161dc0db5c8c5a327d5ca9312e575c18a8385502/multispan-library/src/main/java/com/lockwood/multispan/MultiSpanView.kt#L97-98
