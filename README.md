# Операционные системы. Практика 1

## Выполнил студент группы БББО-05-20 Романько Максим

### Краткое задание
>Разработать программу для взаимодействия с файлами в различных форматах с возможностью их модификации, создания или удаления. Разработанный код должен иметь возможность его повторного использования и модификации.

**Классы и их предназначение:**
- **Main** - основный класс программы для выполнения программы
- **Task** - абстрактный класс для наследования. Содержит методы и переменные, которые повторно используются в других классах программы
  - **Task1** - класс, реализующий работу с дисками, вывода информации о них
  - **Task2** - класс, реализующий работу с файлами, ввода/вывода информации из них
  - **Task3** - класс, реализующий работу с файлами в формате _JSON_, ввода/вывода информации из них
  - **Task4** - класс, реализующий работу с файлами в формате _XML_, ввода/вывода информации из них
  - **Task5** - класс, реализующий работу с архивами, создания/добавления/извлечения файлов
- **Person** - вспомогательный класс, представлющий собой информацию об абстрактном человеке, содержит поля для хранения его имени, фамилии и возраста.
