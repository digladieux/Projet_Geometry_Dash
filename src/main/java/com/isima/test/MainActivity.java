package com.isima.test;

import android.app.Activity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.Window;
import android.view.WindowManager;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {


        /* Lance une methode de la super classe qui initialise l'activite */
        super.onCreate(savedInstanceState);
        /* Recupere la fenetre courant, et met la fenettre en pleine ecran */
        /* FULLSCREEN permet d'ecrire partout dans la fenetre, et d'enlever la barre d'outil */
        /*TODO : essayer avec true ou 1 ou autre en deuxieme argument */
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);

        /* Sur la fenetre courante, la caracteristique du titre n'apparait pas */
        this.requestWindowFeature(Window.FEATURE_NO_TITLE) ;

        /* info général sur l'écran : taille*/
        DisplayMetrics dm = new DisplayMetrics() ;

        /* Recupère la liste de toutes les fenetre puis Sur l'écran par default, on recuperer toutes les metrics (taille etc) */
        getWindowManager().getDefaultDisplay().getMetrics(dm);

        /* Affecte les coordoonée de l'écran a notre classe des var statique */
        Constants.SCREEN_WIDTH = dm.widthPixels ;
        Constants.SCREEN_HEIGHT = dm.heightPixels ;

      /*  TextView textView = (TextView) findViewById(R.id.all);
        Typeface typeface = Typeface.createFromAsset(getAssets(), "fonts/SHOWG.TTF");
        textView.setTypeface(typeface);*/
        /* On modifie l'affichage de l'ecran, pour mettre ce type de fenetre, qu'on definit dans GamePanel */
        setContentView(new GamePanel(this));

/*
        android:fontFamily="@fonts/showg"
        TextView textView = (TextView) findViewById(R.id.sample_text) ;
        Typeface typeface = Typeface.createFromAsset(getAssets(), "fonts/showg.ttf" );
        textView.setTypeface(typeface);
*/
    }
}








