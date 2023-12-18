# <h1 align="center"> Animated Like Button Library [![](https://jitpack.io/v/AppcentMobile/Animated-like-Button.svg)](https://jitpack.io/#AppcentMobile/Animated-like-Button) </h1>

<p align="center">Animated Like Button library is a versatile and user-friendly tool that empowers developers to create customizable buttons with animated interactions. Designed to enhance user engagement, this library allows you to effortlessly integrate animated like buttons into your projects, adding a touch of dynamism to user interactions. </p>

## <h1 align="center">🖼 Preview</h1>

<p align="center">
    <img src="https://github.com/betulnecanli/Animated-Like-Button/blob/master/preview/preview.gif?raw=true"/>
</p>

## <h1 align="center">🪧 About</h1>

- Customization: Tailor your like button to suit your application's aesthetic with the ability to choose from a variety of icons, background styles, and icon colors.
- Interactive Animations: Capture user attention and create a delightful user experience by incorporating smooth animations during button clicks and releases.
- Easy Integration: Seamlessly integrate the Animated Like Button into your projects with straightforward implementation, saving you time and effort.
- Versatility: Whether you're working on a social media platform, a blog, or any other application, this library adapts to various contexts, providing a polished and interactive element.

## <h1 align="center">📌 Usage</h1>

### Step 1. Add the JitPack repository to your build file

Add it in your root build.gradle at the end of repositories:

```gradle
dependencyResolutionManagement {
    repositories {
        ...
        maven { url 'https://jitpack.io' }
    }
}
```

### Step 2. Add the dependency

```gradle
dependencies {
  implementation 'com.github.AppcentMobile:Animated-like-Button:v1.0.0'
}
```

## <h1 align="center">📖 XML Properties</h1>

| Properties            | Description                                      |
|-----------------------|--------------------------------------------------|
| likeBackgroundColor   | Set the background color when clicked for liking |
| likeImageColor        | Set the icon color when clicked for liking.      |
| unlikeBackgroundColor | Set the background color when clicked to unlike  |
| unlikeImageColor      | Set the icon color when clicked to unlike        |
| iconDrawable          | set the drawable for the icon                    |
| iconWidth             | the width of the icon                            |
| iconHeight            | the height of the icon                           |

## <h1 align="center">📎 Example</h1>

```xml
<com.appcent.animatedbutton.LikeButton
    android:id="@+id/bttn_celebrate"
    android:layout_width="50dp"
    android:layout_height="50dp"
    android:layout_marginHorizontal="6dp"
    app:iconDrawable="@drawable/baseline_celebration_24"
    app:iconHeight="40dp"
    app:iconWidth="40dp"
    app:layout_constraintBottom_toBottomOf="parent"
    app:layout_constraintEnd_toEndOf="parent"
    app:layout_constraintStart_toStartOf="parent"
    app:layout_constraintTop_toTopOf="parent"
    app:likeImageColor="#FFD700"
    app:likeBackgroundColor="#001F3F" />
```

## License

This project is licensed under the Apache License, Version 2.0. See the [LICENSE](LICENSE) file for details.