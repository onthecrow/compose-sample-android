# Jetpack Compose State Changing Sample

Пример Jetpack Compose (Swift UI должен работать подобным образом) приложения, иллюстрирующего то как Compose работает с постоянно и бытро изменяющимся состоянием UI
Основная логика находится в классе [MainViewModel](https://github.com/onthecrow/compose-sample-android/blob/main/app/src/main/java/com/onthecrow/compose_state_playground/MainViewModel.kt)

### Концепция приложения:
1) Приложение состоит из одного экрана
2) На экране находятся 24 текстовых элемента
3) Которые рендерят 1 пользовательский объект состояния (один объект MutableState с 12 полями типа Char) и 12 объектов состояния типа MutableState<Char>
4) Значение объектов MutableState меняется от 'a' до 'z' с задержкой между изменениями в 1 мс
5) Каждые несколько итераций происходит пауза в 1000 мс для того чтобы показать что состояние рендерится консистентно

[screen_recording.webm](https://github.com/user-attachments/assets/6954ae9d-3d07-4f26-835a-65723098e342)

# Как это работает

Под капотом Compose использует механизм неблокирующей синхронизации __MVCC__ (Multiversion concurrency control).
В основе механизма лежит система [снапшотов](https://blog.zachklipp.com/introduction-to-the-compose-snapshot-system/).

<img src="https://github.com/user-attachments/assets/48d99d5b-2804-47ee-86f9-e5ebab0f848a" width="350" align=center alt="Snapshot scheme">


* У приложения всегда есть Global Snapshot который представляет собой стейт приложения
* В момент когда мы изменяем значение объекта MutableState на одном из экранов - от главного снапшота создается снапшот с новым состоянием (см. схему)
* При этом в случае быстрых изменений __процесс оптимизируется__ и вместо создания нового снапшота на каждое изменение состояния изменяется уже созданный снапшот
* После изменения/изменений состояния, или перед рендером фрейма (16 - 8 мс, в зависимости от частоты обновления экрана) при непрерывных быстрых изменениях, снапшот с измененным значением коммитится в основной снапшот (по аналогии с транзакциями)
* В начале рендера (в Compose называется композицией) от главного снапшота создается иммутабельный снапшот, на основе которого будет происходить композиция
* Тем самым композиция/рендер происходит всегда на основе изолированного иммутабельного снапшота, при этом Global Snapshot не блокируется на время рендрера и, если за время рендера фрейма он изменится (в него будет закоммичен снапшот с изменениями), состояние не потеряется и будет отрендерено в следующем фрейме.


### Влияет ли скорость обновления состояния, и количество этих обновлений на плавность интерфейса?
Нет, если они происходят вне главного потока (как это и должно быть). Снапшоты с изменениями создаются от главного снапшота и коммитятся в него вне главного потока и никак его не блокируют.

### Происходит ли перерисовка интерфейса при каждом изменении состояния?
Нет, Compose получает уведомления от системы снапшотов при изменении состояния и __планирует__ перерисовку интерфейса, сама перерисовка произойдет в момент когда система запросит у приложения следующий фрейм.

### Как обеспечивается консистентность состояния?
Средствами языка, сам объект состояния UI (который находится в поле value обекта MutableState) должен быть иммутабельным, изменения поля valuе, если они происходят из разных потоков, должны быть синхронизированы. За остальное отвечает Snapshot system.

### Перерисовывается ли весь интерфес при рендере каждого фрейма?
Нет не происходит и нет не весь.
Compose не рекомпозирует (перерисовывает) интерфейс каждый фрейм, если состояние интерфейса не изменилось.
При этом если оно изменилось, Compose сравнивает старое состояние и новое, и перерисовывает только те элементы интерфейса, которые зависят от изменений.

# Полезные материалы для более подробного погружения в тему
* https://developer.android.com/develop/ui/compose/mental-model
* https://developer.android.com/develop/ui/compose/lifecycle
* https://blog.zachklipp.com/talk-opening-the-shutter-on-snapshots/
* https://www.youtube.com/watch?v=waJ_dklg6fU
* https://medium.com/@takahirom/inside-jetpack-compose-2e971675e55e
* https://www.youtube.com/watch?v=_hI2vwei9Rg
