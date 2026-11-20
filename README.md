# Naročilo pice

Šolska naloga pri predmetu **Razvoj mobilnih aplikacij**.
Android aplikacija v jeziku **Java**, ki združuje komponente iz vseh prejšnjih lekcij: gumbe s poslušalci, več aktivnosti, postavitve, spustni seznam, izbirna polja, `RecyclerView` z adapterjem, `Toast`, besedilne vire in obvestila.

## Opis

Uporabnik sestavi naročilo pice:

1. izbere **vrsto pice** (`Spinner`),
2. izbere **velikost** (`RadioButton`: Mala, Srednja, Velika),
3. izbere **dodatke** (`CheckBox`: Sir, Gobe, Olive),
4. s klikom na **Dodaj v košarico** doda pico na seznam (`RecyclerView`),
5. s klikom na **Pregled naročila** odpre drugo aktivnost z vsebino košarice,
6. s klikom na **Pošlji naročilo** prikaže obvestilo (`Notification`).

Ddodatno:
- klik na vrstico v košarici prikaže `Toast` z izbrano pico,
- dolg pritisk na vrstico pico odstrani iz košarice,
- če je košarica prazna, aplikacija pokaže opozorilo in ne odpre pregleda.

## Uporabljene komponente

| Lekcija | Uporaba v aplikaciji |
|---|---|
| Gumb in poslušalec | `setOnClickListener` pri gumbih **Dodaj v košarico**, **Pregled naročila**, **Pošlji naročilo** in **Nazaj** |
| Nov Activity | `MainActivity` → `OrderActivity` (`Intent`, `putStringArrayListExtra()`) |
| Layouts | `LinearLayout` z utežmi (glavni zaslon), `ConstraintLayout` (pregled naročila) |
| Spinner | izbira vrste pice, seznam v `strings.xml` (`string-array`) |
| CheckBox in RadioButton | dodatki in velikost pice |
| RecyclerView in Adapter | košarica (`PizzaAdapter`, `item_pizza.xml`) |
| Toast in Stringi | sporočila ob dodajanju, izbiri, odstranitvi; vsa besedila v `strings.xml` |
| Notifications | kanal obvestil, dovoljenje `POST_NOTIFICATIONS` (Android 13+), `NotificationCompat` |

## Zaslonske slike

**Glavni zaslon z izbiro in košarico**

![Glavni zaslon](glavni_zaslon.png)

**Pregled naročila**

![Pregled naročila](pregled_narocila.png)

**Obvestilo**

![Obvestilo](obvestilo.png)

## Struktura projekta

| Datoteka | Opis |
|---|---|
| `MainActivity.java` | izbira pice, dodajanje v košarico, odpiranje pregleda |
| `PizzaAdapter.java` | adapter za `RecyclerView` (klik in dolg pritisk) |
| `OrderActivity.java` | pregled naročila in obvestilo |
| `res/layout/activity_main.xml` | glavni zaslon |
| `res/layout/activity_order.xml` | zaslon za pregled naročila |
| `res/layout/item_pizza.xml` | vrstica v košarici |
| `res/values/strings.xml` | besedila in seznam vrst pice |
| `AndroidManifest.xml` | aktivnosti in dovoljenje za obvestila |

## Namestitev na telefon

Datoteka `app-debug.apk` je na voljo v razdelku **Releases** tega repozitorija.

1. Prenesi `app-debug.apk` na telefon.
2. Odpri datoteko in dovoli namestitev iz neznanih virov.
3. Tapni **Install** in nato **Open**.
4. Ob prvem pošiljanju naročila dovoli obvestila.

## Zagon iz izvorne kode

1. Kloniraj repozitorij: `git clone <URL-repozitorija>`
2. Odpri mapo v Android Studiu in počakaj na Gradle sinhronizacijo.
3. Izberi emulator ali telefon in klikni ▶ (**Run**).

## Avtor

Nejc Bajda – ŠC Nova Gorica, Elektrotehniška in računalniška šola
