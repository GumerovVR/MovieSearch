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
Информация о реализации:

- Адаптивная установка [светлой/темной] темы приложения в зависимости от системной темы;
- Установка локализации приложения [Английский/Русский] в зависимости от языка системы;
- Хранение «Избранного» в БД [SQLite];
- Поддержка Альбомной ориентации. Корректное маштабирование интерфейса под любое разрешение экрана устройства;
- Отображение трейлеров к фильму. При нажатии на трейлер производится переход на YouTube[приложение/веб] для просмотра трейлера;
- Цвет таблички рейтинга фильма изменяется в зависимости от оценки.
В процессе разработки Придерживаясь Clean Architecture. Модель вветвления была выбрана: Gitflow.

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
## Ссылки:
### Jira: https://gumerov.atlassian.net/jira/software/projects/MOV/boards/1
### APK: https://drive.google.com/file/d/1ubxEfn7bmhNZ2chgMDLQofAnMCEnINi8/view?usp=sharing
### ТЗ: https://docs.google.com/document/d/1dluB7el9TTbXDsFLhXeA_pjbjiBC742Z/edit?usp=sharing&ouid=103592319134989979414&rtpof=true&sd=true
#
## Примечание
##### В Jira указаны задачи и фиксы багов, которые будут реализованы в будущем
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
