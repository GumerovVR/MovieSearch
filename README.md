# MovieSearch
## О проекте
Локализация: Русский, Английский

API: https://api.themoviedb.org

Приложение предназначено для поиска и просмотра информации о фильмах. А также составление списка «Избранное».
#
Базовая функциональность:

- Просмотр подборок фильмов: самые популярные, рейтинговые, кассовые;
- Поиск фильмов;
- Просмотр детальной информации о произведении: год выпуска, рейтинг, название и описание, трейлеры к фильму;
- Добавление в список избранного с возможностью просмотра без сетевого подключения;
#
Дополнительная функциональность:

- Адаптивная установка [светлой/темной] темы приложения в зависимости от системной темы;
- Установка локализации приложения [Английский/Русский] в зависимости от языка системы;
- Хранение «Избранного» в БД [SQLite];
- Поддержка Альбомной ориентации. Корректное маштабирование интерфейса под любое разрешение экрана устройства;
- Отображение трейлеров к фильму. При нажатии на трейлер производится переход на YouTube[приложение/браузер] для просмотра трейлера;
- Цвет таблички рейтинга фильма изменяется в зависимости от оценки.
#
Информация о реализации:

- Управление в проекте производилось в Jira[[Скриншот](https://user-images.githubusercontent.com/75484199/141645470-cd438d80-cc70-4d6d-8ba1-e5b6dd8c5c9c.jpg)];
- В Git модель ветвления была выбрана: Gitflow.
#
## Стек технологий
-	Kotlin
-	MVVM
-	Coroutine 
-	Retrofit
-	GSON
-	Picasso
-	Room
-	Hilt
-	Jetpack Navigation
-	Jetpack Paging 3
#
## Ссылки
### [Демо видео функционала приложения](https://www.youtube.com/watch?v=3p4drjZkk2w)
### [APK](https://drive.google.com/file/d/1I1__jRlLMNj__Rhip9ocvJGvgOqJ0bhf/view?usp=sharing)
### [Jira](https://user-images.githubusercontent.com/75484199/141645470-cd438d80-cc70-4d6d-8ba1-e5b6dd8c5c9c.jpg)
#
## Примечание
- Версия проекта не окончательная. Реализован только базовый функционал. В Jira указаны задачи и фиксы багов, которые будут реализованы в будущем;
- Для запуска проекта, необходимо в файле `common/Const` присвоить `API_KEY` [свой ключ](https://api.themoviedb.org).
#

## Скриншоты
### [Смартфон/Портретная ориентация/Темная тема/Русский язык]
<img src="https://user-images.githubusercontent.com/75484199/141421551-32524d74-b2a9-4fb3-ae05-90787b211ea1.jpg" width="300">   <img src="https://user-images.githubusercontent.com/75484199/141421716-0c882e51-445a-4781-87fc-98dd4bcde575.jpg" width="300"> 

<img src="https://user-images.githubusercontent.com/75484199/141421767-5920c965-8173-4a78-9fdf-eef2d06fcd18.jpg" width="300">   <img src="https://user-images.githubusercontent.com/75484199/141421791-707580f8-6c78-4c7c-99d4-5af5647cc5d8.jpg" width="300">
#
#
### [Планшет/Альбомная ориентация/Светлая тема/Английский язык]
![Screenshot_1636695785](https://user-images.githubusercontent.com/75484199/141421829-a466954d-ba16-4221-81c9-dceff5fd661a.png)
![Screenshot_1636695805](https://user-images.githubusercontent.com/75484199/141421845-cb788f55-c179-4554-a766-5679c16defff.png)
