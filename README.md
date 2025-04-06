# Sequenia
<a name="up"></a>

---

### ✨ Тестовое задание для компании [**Sequenia**](https://sequenia.com/).

Проект написан на языке программирования [**Kotlin**](https://kotlinlang.org/) и основан по архитектуре **MVVM (Model-View-ViewModel)** и с одной активностью.

Android приложение для отображения списка фильмов и просмотра информации о них.
За основу взята информация с сайта [КиноПоиск](https://www.kinopoisk.ru).

---

## 🚀 Стек используемых технологий

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
  Набор библиотек и инструментов для обработки различных вариантов использования навигации и упрощаения навигации между фрагментами, включая анимации и передачу данных.

### Модуль хранения состояния - `ViewModel`

- **[Lifecycle ViewModel](https://developer.android.com/topic/libraries/architecture/viewmodel)**  
  Компонент архитектуры `Android` для хранения и управления состоянием и `UI`-данными с учетом жизненного цикла.

### Сетевые запросы и серилизация данных

- **[Retrofit 2](https://github.com/square/retrofit)**  
  Библиотека для работы с `HTTP`-запросами с помощью типобезопасного `HTTP`-клиента. Преобразует `REST API` в интерфейсы `Kotlin`.

- **[Kotlin Serialization](https://github.com/square/retrofit/tree/trunk/retrofit-converters/kotlinx-serialization)**  
  Официальная библиотека `Kotlin` для сериализации/десериализации `JSON` и других форматов. Использует `kotlin.serialization` для сериализации.

- **[OkHttp](https://github.com/square/okhttp)**  
  Мощный `HTTP`-клиент с поддержкой `HTTP/2`, кэшированием и перехватчиками.

- **[OkHttp Logging Interceptor](https://github.com/square/okhttp/tree/master/okhttp-logging-interceptor)**  
  Перехватчик для логирования сетевых запросов и ответов в `Logcat`.

### Обработка `URI` изображений

- **[Glide](https://github.com/bumptech/glide)**  
  Быстрая и эффективная платформа для управления мультимедиа и загрузки изображений с открытым исходным кодом для `Android`.

### Внедрение зависимостей DI

- **[Koin](https://github.com/InsertKoinIO/koin)**  
  Прагматичный фреймворк для внедрения зависимостей (**Dependency Injection**, `DI`) для `Kotlin`.

---

> [!WARNING]
> ### **🔧 Компиляция проекта**
>
> Для того, чтобы собрать проект, необходимо создать **`secrets.properties`** в корне проекта:
>
>```properties
>    URL_SERVER_FILM="URL подключаемого сервера с фильмами" String
>```

---

#### [README](README.md) [ВВЕРХ](#up)
