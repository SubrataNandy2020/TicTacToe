package help.snandy.tictactoe;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;


import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
   private boolean player1=true;
   private int set;
   private int p1=0,p2=0;
    private Button [][] buttons=new Button[3][3];
    private Button reset;
    private TextView p11,p22,s;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        p11=findViewById(R.id.p11);
        p22=findViewById(R.id.p22);

        for (int i=0;i<3;i++){
            for(int j=0;j<3;j++){
                String bId="bu_"+i+j;
                int setId=getResources().getIdentifier(bId,"id",getPackageName());
                buttons[i][j]=findViewById(setId);
                buttons[i][j].setOnClickListener(this);
            }
        }
        reset=findViewById(R.id.RESET);
        reset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
             fullreset();
            }
        });
    }

    @Override
    public void onClick(View v) {
        if(!((Button)v).getText().toString().equals("")){
            return;
        }
        if(player1){
            ((Button)v).setText("X");
            //((Button)v).setTextColor("#0328F7");
        }
        else {
            ((Button)v).setText("O");
        }
        set++;
        checking();

    }
    private void checking(){
        if(checkWin()){
            if(player1){
                p1wins();
            }
            else {
                p2wins();
            }
        }

        else if(set==9){
            draw();
        }
        else {
            player1=!player1;
        }
    }
    private boolean checkWin(){
        String check[][]=new String[3][3];
        for(int i=0;i<3;i++){
            for(int j=0;j<3;j++){
                check[i][j]=buttons[i][j].getText().toString();
            }
        }
        for(int i=0;i<3;i++){
            if(check[i][0].equals(check[i][1]) && check[i][1].equals(check[i][2]) && (!(check[i][0].equals("")))){
                return true;
            }
        }
        for(int j=0;j<3;j++){
            if(check[0][j].equals(check[1][j]) && check[1][j].equals(check[2][j]) && (!(check[0][j].equals("")))){
                return true;
            }
        }
        if(check[0][2].equals(check[1][1]) && check[1][1].equals(check[2][0]) && (!(check[0][2].equals("")))){
            return true;
        }
        if(check[0][0].equals(check[1][1]) && check[1][1].equals(check[2][2]) && (!(check[0][0].equals("")))){
            return true;
        }
        return false;
    }
    private void p1wins(){
        Toast.makeText(getApplicationContext(),"HURRAY!!!!!!!!PLAYER 1 WINS",Toast.LENGTH_LONG).show();
        p1++;
        setPoints();
        reset();
    }
    private void p2wins(){
        Toast.makeText(getApplicationContext(),"HURRAY!!!!!!!!PLAYER 2 WINS",Toast.LENGTH_LONG).show();
        p2++;
        setPoints();
        reset();
    }
    private void draw(){
        Toast.makeText(getApplicationContext(),"!!!!!!!!!!!!!!!DRAW!!!!!!!!!!",Toast.LENGTH_LONG).show();
        reset();
    }

    private void setPoints(){
        p11.setText("PLAYER 1:"+p1);
        p22.setText("PLAYER 2:"+p2);
    }
    private void reset(){
        for(int i=0;i<3;i++){
            for(int j=0;j<3;j++){
                buttons[i][j].setText("");
            }
        }
        set=0;
        player1=true;
    }
    private void fullreset(){
        Toast.makeText(getApplicationContext(),"RESET DONE",Toast.LENGTH_SHORT).show();
        p1=0;
        p2=0;
        setPoints();
        reset();
    }

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putBoolean("Player",player1);
        outState.putInt("P1",p1);
        outState.putInt("P2",p2);
        outState.putInt("Set",set);
    }

    @Override
    protected void onRestoreInstanceState(@NonNull Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        player1=savedInstanceState.getBoolean("Player");
        p1=savedInstanceState.getInt("P1");
        p2=savedInstanceState.getInt("P2");
        set=savedInstanceState.getInt("Set");
    }
}
