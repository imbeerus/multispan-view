# multispan-view
[![API](https://img.shields.io/badge/API-15%2B-orange.svg)](https://android-arsenal.com/api?level=15)
[![License](https://img.shields.io/badge/license-Apache%202-red.svg)](https://www.apache.org/licenses/LICENSE-2.0)
[![](https://img.shields.io/badge/docs-dokka-blue.svg?logo=kotlin)]()
![Android CI](https://github.com/lndmflngs/multispan-view/workflows/Android%20CI/badge.svg?branch=master)
[![](https://jitpack.io/v/lndmflngs/multispan-view.svg)](https://jitpack.io/#lndmflngs/multispan-view)

Replace your ConstraintLayout with TextView and power of Spans 💪

Allow to apply custom Spans and [change gravity of Drawable][2]

### Features
* Optimized way to use text styling and drawables with TextView
* RobotoSpanView with prepared Roboto font styles or FontSpanView if you need custom fonts 
* Change style of spans in your way (see [usage][7])
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
## Usage
The simplest way is to use `RobotoSpanView` or `FontSpanView` like a normal `TextView`

```xml
        <com.lockwood.multispan.roboto.RobotoTwoSpanView
            android:layout_width="match_parent"
            android:layout_height="wrap_content"
            app:firstRobotoFont="medium"
            app:firstText="Username"
            app:firstSeparator=":"
            app:orientation="vertical"
            app:secondRobotoFont="regular"
            app:secondText="Mr. Fluffypants" />
```
#### Styling
You can make your own spans like a [AlignTopSuperscriptSpan][12] and apply them in your [custom views][13]

#### Image loading
There are several libraries that follow best practices for loading images. I recommend [Glide][9], but you can use others: [Picasso][10], [Coil][11].

#### Data binding
If you use data binding you can use [this][8] adapter

For more information see `app` directory and documentation

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
[4]: https://github.com/lndmflngs/multispan-view/blob/161dc0db5c8c5a327d5ca9312e575c18a8385502/multispan-library/src/main/java/com/lockwood/multispan/MultiSpanView.kt#L97-L98
[5]: https://developer.android.com/reference/android/widget/TextView#attr_android:lineSpacingExtra
[6]: https://developer.android.com/reference/android/widget/TextView#attr_android:lineSpacingMultiplier
[7]: https://github.com/lndmflngs/multispan-view#usage
[8]: https://github.com/lndmflngs/multispan-view/blob/master/app/src/main/java/com/lockwood/multispandemo/BindingExample.kt
[9]: https://github.com/lndmflngs/compound-text-view/blob/master/app/src/main/java/com/lockwood/compoundemo/fragment/RecyclerFragment.kt#L102-L107
[10]: https://github.com/lndmflngs/compound-text-view/blob/master/app/src/main/java/com/lockwood/compoundemo/fragment/RecyclerFragment.kt#L110-L114
[11]: https://github.com/lndmflngs/compound-text-view/blob/master/app/src/main/java/com/lockwood/compoundemo/fragment/RecyclerFragment.kt#L117-L124
[12]: https://github.com/lndmflngs/multispan-view/blob/master/app/src/main/java/com/lockwood/multispandemo/span/AlignTopSuperscriptSpan.kt
[13]: https://github.com/lndmflngs/multispan-view/blob/master/app/src/main/java/com/lockwood/multispandemo/view/SuperscriptTextView.kt
