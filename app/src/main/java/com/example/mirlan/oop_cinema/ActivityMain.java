package com.example.mirlan.oop_cinema;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import java.util.ArrayList;

public class ActivityMain extends AppCompatActivity {

    private static int REQCODE = 101;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setSupportActionBar((Toolbar) findViewById(R.id.toolbar));

        AppPreferences preferences = new AppPreferences(getApplicationContext());
//        check if app has been launched before
        if (preferences.isRunningFirstTime()) {
            Log.e("OOPAPP", "----> running for the first time <----");
            Database.getInstance(this).saveInitialData(getArray());
//            save in preferences that app has already run
            preferences.savePref(AppPreferences.FIRSTTIME, false);
//            saveToFirebase(getArray());
        }

        if (savedInstanceState == null)
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.mainContainer, new FragmentMain())
                    .commit();
    }
        @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == REQCODE && resultCode == RESULT_OK) {
            invalidateOptionsMenu();
        }
        super.onActivityResult(requestCode, resultCode, data);
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        if (User.getUser() instanceof UserAnonymous) {
            menu.getItem(0).setVisible(false);
            menu.getItem(0).setEnabled(false);
            menu.getItem(1).setTitle(R.string.sign_in);
        }
         else {
            menu.getItem(0).setVisible(true);
            menu.getItem(0).setEnabled(true);
            menu.getItem(1).setTitle(R.string.sign_out);
        }
        return super.onCreateOptionsMenu(menu);
    }
     @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.signIn) {
            if (User.getUser() instanceof UserRegistered) {
                User.signOut();
                invalidateOptionsMenu();
            }
               else
                startActivityForResult(new Intent(this, ActivitySignIn.class), REQCODE);
            return true;
        }
        else if (item.getItemId() == R.id.profile) {
            startActivity(new Intent(this, ActivityUserProfile.class));
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
    /*public void saveToFirebase(ArrayList<Movie> movies) {
        DatabaseReference reference = FirebaseDatabase.getInstance().getReference();
        for (int i = 0; i < movies.size(); i++) {
            reference.child("movies").push().setValue(movies.get(i));
        }
    }*/
     private ArrayList<Movie> getArray() {
        ArrayList<Movie> movies = new ArrayList<>();
        movies.add(new Movie("Плохой Санта 2",
                "https://www.kinopoisk.ru/images/film_big/577229.jpg",
                "Комедии",
                "Еще более дикий. Еще более пьяный. Еще более плохой",
                "Продолжение новогодних приключений проходимца Вилли, который в канун Рождества, как всегда, переодевается в Санта-Клауса. Ограбить очередной супермаркет в этом наряде не представляет никаких проблем. Но в Рождество случаются всякие чудеса. Каким оно будет для Вилли на этот раз?",
                190));
        movies.add(new Movie("Миссия: Неадекватна",
                "https://www.kinopoisk.ru/images/film_big/895894.jpg",
                "Комедии",
                "One man''s wildly true quest to capture Osama Bin Laden",
                "Абсолютно безумная и уморительная история обычного американского гражданина, который по поручению Всевышнего решает в одиночку поймать Усаму бен Ладена. Когда миру нужен герой, Гэри спешит на помощь!",
                150));
         movies.add(new Movie("Ёлки 5",
                "https://www.kinopoisk.ru/images/film_big/893500.jpg",
                "Комедии",
                "Те самые ёлки!",
                "В этот новый год любимые герои «Ёлок» всё также делают глупости и надеются на чудо. Боре нужно как-то восстановить семейное счастье и ради этого он готов украсть у лучшего друга Жени пингвина. Лыжник и Сноубордист, так и не повзрослевшие, устраивают сумасшедшую погоню за ёлкой. Баба Маня осваивает интернет в надежде найти старую любовь. Профессор из Екатеринбурга остепенился, но теперь сам сходит с ума от ревности. На далеком Севере инженер по технике безопасности должен рискнуть и, наконец, признаться в любви. Ну а пингвину просто очень нужно сесть на яйцо…",
                160));
        movies.add(new Movie("Почему он?",
                "https://www.kinopoisk.ru/images/film_big/930000.jpg",
                "Комедии",
                "Of all the guys his daughter could have chosen...",
                "Глава семейства вступает в «противостояние» с молодым и богатым парнем своей дочери.",
                170));
        movies.add(new Movie("Союзники",
                "https://www.kinopoisk.ru/images/film_big/915140.jpg",
                "Боевики",
                "Nothing is as it seems",
                "1942 год. Во время опасной миссии в Северной Африке разведчик Макс встречает участницу сопротивления Марианну. Их чувствам суждено подвергнуться тысяче испытаний, главным из которых станет испытание правдой…",
                180));
        movies.add(new Movie("Кредо убийцы",
                "https://www.kinopoisk.ru/images/film_big/690615.jpg",
                "Боевики",
                "Welcome to the Spanish Inquisition",
                "Благодаря революционной технологии, которая высвобождает генетическую память, Каллум Линч переживает приключения своего предка Агилара в Испании XV столетия. Заполучив невероятные знания и способности, Каллум сразится с рыцарями ордена тамплиеров в наше время.",
                190));
        movies.add(new Movie("Изгой-один: Звёздные войны. Истории",
                "https://st.kp.yandex.net/images/film_iphone/iphone360_840152.jpg",
                "Боевики",
                "Мятеж с надеждой в сердце",
                "Фильм рассказывает о событиях, которые предшествовали четвёртому эпизоду «Звёздных Войн». Узнав о том, что Империя построила самую совершенную боевую станцию, способную уничтожать целые планеты, Альянс Повстанцев собирает особый отряд под руководством бывшей преступницы Джин Эрсо, целью которого является чертежи совершенного оружия Империи.\n" +
                        "\n" +
                        "Несмотря на то, что миссия кажется самоубийственной, отряд готов рискнуть всем ради победы. Однако расстановка сил резко меняется, когда Император посылает за ними своего самого страшного и могущественного приспешника — Дарта Вейдера, чьё присутствие гарантирует как минимум одно — отряду повстанцев не суждено вернуться со своей миссии живыми.",
                200));