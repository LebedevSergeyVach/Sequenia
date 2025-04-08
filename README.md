# Sequenia
<a name="up"></a>

---

### ✨ Тестовое задание для компании [**Sequenia**](https://sequenia.com/).

Проект написан на языке программирования [**Kotlin**](https://kotlinlang.org/) и основан по архитектуре **MVVM (Model-View-ViewModel)** и с одной активностью.

Android приложение для отображения списка фильмов и просмотра информации о них.
За основу взята информация с сайта [КиноПоиск](https://www.kinopoisk.ru).

---

### Скачать [APK-файл](https://github.com/LebedevSergeyVach/Sequenia/releases/tag/v1.0.0) release версии приложения.
### Посмотреть изображения мобильного приложения [Films](documentation/README.md).

[![Version](https://img.shields.io/badge/Version-1.0.0-green.svg)](https://github.com/LebedevSergeyVach/Sequenia/releases/tag/v1.0.0)

---

## 🚀 Стек используемых технологий

| [<img src="https://cdn.jsdelivr.net/gh/devicons/devicon/icons/kotlin/kotlin-original.svg" width="45" height="45" alt="Kotlin" />](https://kotlinlang.org/) | [<img src="https://uploads-ssl.webflow.com/60996f3af06ca2ff488a7bfb/60a269bf446a85794a4d3b6b_Retrofit.jpg" width="45" height="45" alt="Retrofit" />](https://github.com/square/retrofit) | [<img src="https://avatars.githubusercontent.com/u/82592?s=48&v=4" width="45" height="45" alt="OkHttp" />](https://github.com/square/okhttp) | [<img src="https://s3.amazonaws.com/playstore/images/60bb08c2fc6d0bddb91e0e3553dcdb48" width="45" height="45" alt="Glide" />](https://github.com/bumptech/glide) | [<img src="https://camo.githubusercontent.com/7d103fddb0f73e6440cfca7e630cef73a2d0bcaf479303a29aeda4be2cdc1bdd/68747470733a2f2f696e736572742d6b6f696e2e696f2f696d672f6b6f696e5f6e65775f6c6f676f2e706e67" width="45" height="45" alt="Koin" />](https://github.com/InsertKoinIO/koin) | [<img src="https://services.google.com/fh/files/emails/android_dev_newsletter_feb_image3.png" width="45" height="45" alt="AndroidX Jetpack" />](https://developer.android.com/jetpack) | [<img src="https://cdn.jsdelivr.net/gh/devicons/devicon/icons/gradle/gradle-original.svg" width="45" height="45" alt="Gradle" />](https://gradle.org/) | [<img src="https://raw.githubusercontent.com/Faltenreich/SkeletonLayout/refs/heads/develop/images/android.png" width="45" height="45" alt="SkeletonLayout" />](https://github.com/Faltenreich/SkeletonLayout) |
|:----------------------------------------------------------------------------------------------------------------------------------------------------------:|:----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------:|:--------------------------------------------------------------------------------------------------------------------------------------------:|:----------------------------------------------------------------------------------------------------------------------------------------------------------------:|:------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------:|:--------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------:|:------------------------------------------------------------------------------------------------------------------------------------------------------:|:-------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------:|
|                                                                           Kotlin                                                                           |                                                                                         Retrofit                                                                                         |                                                                    OkHttp                                                                    |                                                                              Glide                                                                               |                                                                                                                                         Koin                                                                                                                                         |                                                                                    AndroidX Jetpack                                                                                    |                                                                         Gradle                                                                         |                                                                                                Skeleton Layout                                                                                                |                                                                                                                                                                                               |

### Основные компоненты Android

- **[AndroidX Core KTX](https://developer.android.com/kotlin/ktx)**  
  Расширения `Kotlin` для упрощения работы с `Android API`.

- **[AndroidX AppCompat](https://developer.android.com/jetpack/androidx/releases/appcompat)**  
  Поддержка обратной совместимости для новых функций `Android`.

- **[AndroidX Activity](https://developer.android.com/jetpack/androidx/releases/activity)**  
  Современный `API` для работы с `Activity`, включая поддержку `Kotlin` корутин.

### UI

- **[ConstraintLayout](https://developer.android.com/training/constraint-layout)**  
  `ConstraintLayout` позволяет создавать большие и сложные макеты с плоской иерархией представлений — без вложенных групп представлений. Альтернатива традиционным `LinearLayout` и `RelativeLayout`.

- **[Material Components](https://material.io/develop/android)**  
  Реализация **Material Design** от **Google**. Включает готовые `UI`-компоненты.

- **[SkeletonLayout](https://github.com/Faltenreich/SkeletonLayout)**  
  Библиотека для отображения скелетонов (заглушек) во время загрузки данных.

- **[Android SplashScreen API](https://developer.android.com/guide/topics/ui/splash-screen)**  
  Официальное `API` для создания экрана-заставки в стиле **Material Design**.

### Навигация фрагментов - Android Jetpack's Navigation component

- **[Navigation Component](https://developer.android.com/guide/navigation)**  
  Набор библиотек и инструментов для обработки различных вариантов использования навигации и упрощения навигации между фрагментами, включая анимации и передачу данных.

### Модуль хранения состояния - `ViewModel`

- **[Lifecycle ViewModel](https://developer.android.com/topic/libraries/architecture/viewmodel)**  
  Компонент архитектуры `Android` для хранения и управления состоянием и `UI`-данными с учетом жизненного цикла.

### Сетевые запросы и сериализации данных

- **[Retrofit 2](https://github.com/square/retrofit)**  
  Библиотека для работы с `HTTP`-запросами с помощью типобезопасного `HTTP`-клиента. Преобразует `REST API` в интерфейсы `Kotlin`.

- **[Kotlin Serialization](https://github.com/Kotlin/kotlinx.serialization)**  
  Официальная библиотека `Kotlin` для сериализации/десериализации `JSON` и других форматов. Использует `kotlin.serialization` для сериализации.

- **[OkHttp](https://github.com/square/okhttp)**  
  Мощный `HTTP`-клиент с поддержкой `HTTP/2`, кэшированием и перехватчиками.

- **[OkHttp Logging Interceptor](https://github.com/square/okhttp/tree/master/okhttp-logging-interceptor)**  
  Перехватчик для логирования сетевых запросов и ответов в `Logcat`.

### Обработка `URI` изображений

- **[Glide](https://github.com/bumptech/glide)**  
  Быстрая и эффективная платформа для управления мультимедиа и загрузки изображений с открытым исходным кодом для `Android`.

### Внедрение зависимостей `DI`

- **[Koin](https://github.com/InsertKoinIO/koin)**  
  Прагматичный фреймворк для внедрения зависимостей (**Dependency Injection**) для `Kotlin`.

---

> [!WARNING]
> ### **🔧 Компиляция проекта**
>
> Для того, чтобы собрать проект, необходимо создать **`secrets.properties`** в корне проекта:
>
>```properties
>    URL_SERVER_FILM="URL подключаемого сервера с фильмами"
>```

---

#### [README](README.md) [ВВЕРХ](#up)
