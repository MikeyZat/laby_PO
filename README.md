## Zadania do wykonania

1. Wykorzystaj definicje klas z poprzedniego laboratorium.
2. Przyjrzyj się interfejsowi `IWorldMap`, który znajduje się w tym katalogu.
3. Zmodyfikuje klasę `Animal` z poprzedniego ćwiczenia:
   * zdefiniuj konstruktor `Animal(IWorldMap map)`; wykorzystaj argument `map` tak aby w metodzie `move` można było odwołać
     się do mapy i zweryfikować, czy zwierzę może przesunąć się na daną pozycję,
   * zdefiniuj konstruktor `Animal(IWorldMap map, Vector2d initialPosition)`, który dodatkowo określa początkowe położenie zwierzęcia na
     mapie,
   * dodaj metodę `Vector2d getPosition()`, która zwraca pozycję zwierzęcia,
   * zmodyfikuj metodę `toString` tak by zwracała jedynie schematyczną orientację zwierzęcia w postaci łańcucha
     składającego się z jednego znaku, Np. jeśli zwierzę ma orientację północną to metoda `toString()` powinna zwracać
     łańcuch "N" albo "^".
   * zmodyfikuj metodę `move`, tak by korzystała z wywołania `canMoveTo` interfejsu `IWorldMap`.
3. Zdefiniuj klasę `RectangularMap`, która:
   * definiuje prostokątną mapę,
   * implementuje interfejs `IWorldMap`
   * w konstruktorze akceptuje dwa parametry `width` oraz `height` wskazujące szerokość oraz wysokość mapy (zignoruj
     sytuację, w której użytkownik klasy poda niepoprawne wartości),
   * umożliwia poruszanie się w obrębie zdefiniowanego prostokąta (jak w laboratorium 3),
   * umożliwia występowanie więcej niż jednego zwierzęcia na mapie,
   * uniemożliwia występowanie więcej niż jednego zwierzęcia na tej samej pozycji,
   * posiada metodę `toString` rysującą aktualną konfigurację mapy (wykorzystaj klasę `MapVisualizer` która znajduje się
     w tym katalogu),
   * w metodzie `run` na przemian steruje ruchem wszystkich zwierząt. Przykładowo, jeśli użytkownik wprowadzi ciąg: `f
     b r l` a na mapie są dwa zwierzęcia, to pierwsze zwierzę otrzyma ruchy `f` i `r`, a drugie `b` i `l`. Ruchy obu
     zwierząt mają być wykonywane na przemian, tzn. po każdym ruchu pierwszego zwierzęcia następuje ruch drugiego
     zwierzęcia.
4. Wykonaj następujący kod w metodzie `main` klasy `World`:
```java
MoveDirection[] directions = new OptionsParser().parse(args);
IWorldMap map = new RectangularMap(10, 5);
map.place(new Animal(map));
map.place(new Animal(map,new Vector2d(3,4)));
map.run(directions);
```
Sprawdź czy zwierzęta poruszają się poprawnie dla ciągu: `f b r l f f r r f f f f f f f f`.

6. Dodaj testy integracyjne weryfikujące, że implementacja jest poprawna. Wykorzystaj dane z punktu 5 w celu
   ustalenia przebiegu testu.
7. (**Dla zaawansowanych**) Stwórz tekstowy widget biblioteki Swing, który będzie wyświetlał animację poruszających się
   zwierzaków.