package com.example.chandsachin.connect3;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    //0-nr 1-qw
    int activeplayer = 0;
    boolean gameisactive = true;
    int[] gamestate = {2, 2, 2, 2, 2, 2, 2, 2, 2};
    int [][] winnningpositions = {{0,1,2},{3,4,5},{6,7,8} , {0,3,6} , {1,4,7} , {2,5,8} , {0,4,8} , {2,4,6}};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void dropin(View view) {
        ImageView counter = (ImageView) view;
        counter.getTag().toString();

        int tappedcounter = Integer.parseInt(counter.getTag().toString());

        if (gamestate[tappedcounter] == 2 && gameisactive) {

            gamestate[tappedcounter] = activeplayer;
            counter.setTranslationY(-1000f);

            if (activeplayer == 0) {
                counter.setImageResource(R.drawable.bl);
                activeplayer = 1;
            } else {
                counter.setImageResource(R.drawable.red);
                activeplayer = 0;
            }
            counter.animate().translationYBy(1000f).rotation(360).setDuration(200);

            for (int []winnigpos :winnningpositions){

                if(gamestate[winnigpos[0]] == gamestate[winnigpos[1]] &&
                        gamestate[winnigpos[1]] == gamestate[winnigpos[2]] &&
                        gamestate[winnigpos[0]]!=2){

                 //   System.out.println(gamestate[winnigpos[0]]);

                    gameisactive=false;
                    String winn="Player 2";

                    if(gamestate[winnigpos[0]] == 0){
                        winn="Player 1";
                    }

                    TextView winnermsg=(TextView)findViewById(R.id.textView);
                    winnermsg.setText(winn +"  wins");
                    LinearLayout ly=(LinearLayout)findViewById(R.id.playagainll);
                    ly.setVisibility(View.VISIBLE);
                }

                else{
                    boolean gameisover= true;

                    for(int counterstate : gamestate){
                        if(counterstate== 2){  gameisover=false;                    }
                    }

                    if( gameisover){
                        TextView winnermsg=(TextView)findViewById(R.id.textView);
                        winnermsg.setText("It's a draw");
                        LinearLayout ly=(LinearLayout)findViewById(R.id.playagainll);
                        ly.setVisibility(View.VISIBLE);
                    }
                }
            }
        }
    }

    public void playagain(View view){
        gameisactive =true;
        LinearLayout ly=(LinearLayout)findViewById(R.id.playagainll);
        ly.setVisibility(view.INVISIBLE);
        activeplayer =0;
        //gamestate ={2,2,2,2,2,2,2,2,2};
        for(int i=0;i<gamestate.length;i++){
            gamestate[i]=2;
        }
        GridLayout gl=(GridLayout)findViewById(R.id.gridlayout);
        for(int i=0;i<gl.getChildCount();i++){
            ((ImageView)gl.getChildAt(i)).setImageResource(0);
        }
    }
}