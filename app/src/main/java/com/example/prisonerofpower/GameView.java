package com.example.prisonerofpower;


import android.app.Activity;
import android.content.Context;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Point;
import android.graphics.Rect;

import android.os.Build;
import android.os.CountDownTimer;
import android.os.Handler;
import android.util.Log;
import android.view.Display;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;


import androidx.annotation.RequiresApi;

import androidx.core.view.GestureDetectorCompat;


import java.util.Timer;
import java.util.TimerTask;


public class GameView extends View
    {
    public static int lengthLevel;
    private final GestureDetectorCompat gd;
    Handler handler;
    Runnable runnable;
    final int UPDATE_MILLIS=30; //частота обновления onDraw полагаю
    Bitmap background,background_2,race,race1_1;
    Bitmap[] player;
    Bitmap[] bot1,bot2;
    Bitmap prep;
    Display display;
    Point point;
    int dWight,dHeight;
    int playerFrame = 0; // Для смены кадра движения у игрока
    int botFrame = 0; // Для смены кадра движения у ботов


        float prepX;

    float playerX,bot1X,bot2X,raceX,race1X; //для позиций игрока и ботов по Х
    float playerY,bot1Y,bot2Y,raceY; // Можно избавиться     для позиций игрока и ботов по У
    float speed; //для изменения playerX



    int counterForStopGame = 0;//Счетчик для окончания игры,когда врезался впрепятствие     Это я так от проблемы со счетчиком ушел
    float stopping =-0.09f; //сила споротивления -- тянет игрока влево

    boolean firststep= true; // проверка на первый шаг игрока
    boolean secondstep=false;// проверка на второй шаг игрока

    float speedup; // то же что и speed но для оси У
    float gravity=3; // сопротивленеи для speedup
    boolean isgrounded=true; // проверка на то,приземлился ли
    boolean gameStopped=false; // остановлена ли игра
    Timer timer = new Timer(); //таймер


    String distance =""; // для вывода на экран float граниченного n знаками
    String finishin=""; // для вывода на экран float граниченного n знаками
    int maxChar=0; // для вывода на экран float граниченного n знаками
    int translating_finishing=1;// для перемещения по оси У растояния до финиша
    int a=1; // для перемещения по оси У растояния до финиша

    String startIn = ""; // для вывода на экран секунд до начала забега

    float meters; // растояние

    int i=0;

    float speedRace=0; //скорость движения бэкграунда влево
    boolean gameState=false;
        private boolean gameStateRun = false;
        private float bgX=0;
        private float bg1X;
        private boolean DoNotFall = true;
        public static int Score;

        public GameView(Context context) {
        super(context);

        new CountDownTimer(5000, 1000) {

            //Здесь обновляем текст счетчика обратного отсчета с каждой секундой
            public void onTick(long millisUntilFinished) {

                       startIn="" + millisUntilFinished / 1000;

            }

            public void onFinish() {

                startIn="";
                gameStateRun=true;
            }
        }.start();



        gd= new GestureDetectorCompat(context, new GestureListener());


        handler = new Handler();
        runnable = new Runnable() {
            @Override
            public void run() {
                invalidate();
            }
        };
        background= BitmapFactory.decodeResource(getResources(),R.drawable.stadium);

        background_2= BitmapFactory.decodeResource(getResources(),R.drawable.stadium);

        prep=BitmapFactory.decodeResource(getResources(),R.drawable.prep);

        race= BitmapFactory.decodeResource(getResources(),R.drawable.race_3);
        race1_1= BitmapFactory.decodeResource(getResources(),R.drawable.race_3);

            player = new Bitmap[10];
            bot1 = new Bitmap[10];
            bot2 = new Bitmap[10];

        Skins(Shop.skin);

        display = ((Activity)getContext()).getWindowManager().getDefaultDisplay();
        point = new Point();
        display.getSize(point);
        dHeight=point.y;
        dWight=point.x;


        playerX=dWight/4 -player[playerFrame].getWidth()/2;
        playerY=dHeight/1.5f -40 -player[playerFrame].getHeight()/2;

        bot1X=dWight/4 -player[playerFrame].getWidth()/2;
        bot1Y=dHeight/1.5f-80 -player[playerFrame].getHeight()/2;
        bot2X=dWight/4 -player[playerFrame].getWidth()/2;
        bot2Y=dHeight/1.5f +110 -player[playerFrame].getHeight()/2;
        raceX=0;
        raceY=dHeight - race.getWidth()/5 +120;
        race1X=race.getWidth();
        bg1X=background.getWidth();
        prepX=dWight;



        timer.schedule(new UpdateTimeTask(), 0, 10);

    }
    class UpdateTimeTask extends TimerTask {
            public void run() {

            meters+=(speedRace*0.0008);

            }
        }
    public void Skins(int change){
            if(change==1) {
                player[0]= BitmapFactory.decodeResource(getResources(),R.drawable.cop1);
                player[1]= BitmapFactory.decodeResource(getResources(),R.drawable.cop2);
                player[2]= BitmapFactory.decodeResource(getResources(),R.drawable.cop3);
                player[3]= BitmapFactory.decodeResource(getResources(),R.drawable.cop4);
                player[4]= BitmapFactory.decodeResource(getResources(),R.drawable.cop5);
                player[5]= BitmapFactory.decodeResource(getResources(),R.drawable.cop6);
                player[6]= BitmapFactory.decodeResource(getResources(),R.drawable.cop7);
                player[7]= BitmapFactory.decodeResource(getResources(),R.drawable.cop8);
                player[8]= BitmapFactory.decodeResource(getResources(),R.drawable.cop9);
                player[9]= BitmapFactory.decodeResource(getResources(),R.drawable.cop10);
            }
            else {


                player[0] = BitmapFactory.decodeResource(getResources(), R.drawable.run1);
                player[1] = BitmapFactory.decodeResource(getResources(), R.drawable.run2);
                player[2] = BitmapFactory.decodeResource(getResources(), R.drawable.run3);
                player[3] = BitmapFactory.decodeResource(getResources(), R.drawable.run4);
                player[4] = BitmapFactory.decodeResource(getResources(), R.drawable.run5);
                player[5] = BitmapFactory.decodeResource(getResources(), R.drawable.run6);
                player[6] = BitmapFactory.decodeResource(getResources(), R.drawable.run7);
                player[7] = BitmapFactory.decodeResource(getResources(), R.drawable.run8);
            }

        bot1[0]= BitmapFactory.decodeResource(getResources(),R.drawable.cop8);
        bot1[1]= BitmapFactory.decodeResource(getResources(),R.drawable.cop9);
        bot1[2]= BitmapFactory.decodeResource(getResources(),R.drawable.cop10);
        bot1[3]= BitmapFactory.decodeResource(getResources(),R.drawable.cop1);
        bot1[4]= BitmapFactory.decodeResource(getResources(),R.drawable.cop2);
        bot1[5]= BitmapFactory.decodeResource(getResources(),R.drawable.cop3);
        bot1[6]= BitmapFactory.decodeResource(getResources(),R.drawable.cop4);
        bot1[7]= BitmapFactory.decodeResource(getResources(),R.drawable.cop5);
        bot1[8]= BitmapFactory.decodeResource(getResources(),R.drawable.cop6);
        bot1[9]= BitmapFactory.decodeResource(getResources(),R.drawable.cop7);


        bot2[0]= BitmapFactory.decodeResource(getResources(),R.drawable.cop1);
        bot2[1]= BitmapFactory.decodeResource(getResources(),R.drawable.cop2);
        bot2[2]= BitmapFactory.decodeResource(getResources(),R.drawable.cop3);
        bot2[3]= BitmapFactory.decodeResource(getResources(),R.drawable.cop4);
        bot2[4]= BitmapFactory.decodeResource(getResources(),R.drawable.cop5);
        bot2[5]= BitmapFactory.decodeResource(getResources(),R.drawable.cop6);
        bot2[6]= BitmapFactory.decodeResource(getResources(),R.drawable.cop7);
        bot2[7]= BitmapFactory.decodeResource(getResources(),R.drawable.cop8);
        bot2[8]= BitmapFactory.decodeResource(getResources(),R.drawable.cop9);
        bot2[9]= BitmapFactory.decodeResource(getResources(),R.drawable.cop10);
    }

        @Override
        public boolean onTouchEvent(MotionEvent event) {

            return gd.onTouchEvent(event); }
        private final class GestureListener extends GestureDetector.SimpleOnGestureListener {

            private static final int SWIPE_THRESHOLD = 100;
            private static final int SWIPE_VELOCITY_THRESHOLD = 100;

            @Override
            public boolean onDown(MotionEvent e) {
    if(gameStateRun)
    {
        gameState=true;
    }
                return true;
            }

            @Override
            public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
                boolean result = false;
                try {
                    float diffY = e2.getY() - e1.getY();
                    float diffX = e2.getX() - e1.getX();
                    if (Math.abs(diffX) > Math.abs(diffY)) {
                        if (Math.abs(diffX) > SWIPE_THRESHOLD && Math.abs(velocityX) > SWIPE_VELOCITY_THRESHOLD) {
                            if (diffX > 0) {
                                onSwipeRight();
                            } else {
                                onSwipeLeft();
                            }
                            result = true;
                        }
                    }
                    else if (Math.abs(diffY) > SWIPE_THRESHOLD && Math.abs(velocityY) > SWIPE_VELOCITY_THRESHOLD) {
                        if (diffY > 0) {
                            onSwipeBottom();
                        } else {
                            onSwipeTop();
                        }
                        result = true;
                    }
                } catch (Exception exception) {
                    exception.printStackTrace();
                }
                return result;
            }
        }

        public void onSwipeRight() {
            if (gameState) {
                if (firststep) {
                    speed = 25;
                    firststep = false;
                    secondstep = true;
                }

                if (speedRace < 20) {
                    speed += 4;
                    speedRace += 0.2;
                }
                if (speedRace < 40 && speedRace >= 20) {
                    speed += 6;
                    stopping -= 0.003f;
                    speedRace += 0.4;

                }
                if (speedRace >= 40) {
                    speed += 9;
                    stopping -= 0.006f;
                    if (speedRace < 80) {
                        speedRace += 0.7f;
                    }
                }


            }
        }

        public void onSwipeLeft() {
        if(gameState){
            if(speedRace<20){
                speed-=2;
            }
            if(speedRace<40&&speedRace>=20){
                speed-=4;
            }
            if(speedRace>=40){
                speed-=6;
            }
        }}

        public void onSwipeTop() {
           if(isgrounded&&gameState){
            speedup+=30;
            isgrounded=false;
           }

        }

        public void onSwipeBottom() {
        }

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        Logic();
        LogicForObstacles();



        canvas.drawBitmap(background, bgX,0, null);
        canvas.drawBitmap(background_2, bg1X,0, null);
        canvas.drawBitmap(race, raceX,raceY, null);
        canvas.drawBitmap(race1_1, race1X, raceY, null);
        canvas.drawBitmap(player[playerFrame], playerX, playerY, null);
        canvas.drawBitmap(bot1[botFrame], bot1X, bot1Y, null);
        canvas.drawBitmap(bot2[botFrame], bot2X, bot2Y, null);
        canvas.drawBitmap(prep, prepX, dHeight/2 +180, null);

        Paint paint = new Paint();
        paint.setColor(Color.WHITE);
        paint.setTextSize(50);
        paint.setAntiAlias(true);

        Paint paint1 = new Paint();
        paint1.setColor(Color.WHITE);
        paint1.setTextSize(100);
        paint1.setAntiAlias(true);

        canvas.drawText("Speed:", 50, 200, paint);

        canvas.drawText(distance, 220, 200, paint);
        canvas.drawText("km/h", 320, 200, paint);
        canvas.drawText(startIn, dWight / 2, dHeight / 2, paint1);

        canvas.drawText(finishin, dWight-400, 200+translating_finishing, paint);
        canvas.drawPath(drawTriangle(),paint);





    }

        private void LogicForObstacles() {
            prepX-=speedRace;
            if(prepX<=playerX+prep.getWidth()/2 && prepX>=playerX-prep.getWidth()/2 && isgrounded) { DoNotFall=false; }
            System.out.println(playerX);
            System.out.println(prepX);
            if(prepX<=-3000){prepX=dWight+1000;}

        }

        private void stopGame() {
            ProfileInfo.ScoreInt+=meters;
            ProfileInfo.Score=String.valueOf(ProfileInfo.ScoreInt);
            Activity activity = (Activity) getContext();
            activity.finish();
        }

        private void Logic() {

        if(Condition(0) && DoNotFall) {

            if ((playerX < 900 && i==0)) {}
            else{
                if (secondstep) {
                    speed -= 24;
                    secondstep = false;
                    stopping -= 0.08;
                }
                i=1;
            WorkWithX();
            }

            if (gameState) {
                if(speedRace>48)
                {
                    bot1X-=0.3;
                    bot2X-=0.2;
                }
                else{
                    double direction1 = Math.random();
                    if(direction1>0.8)
                    {
                        bot1X+=1+direction1*10;
                    }
                    else{
                        bot1X-=(1+direction1*5);
                    }
                    double direction2 = Math.random();
                    if(direction2>0.8)
                    {
                        bot2X+=1+direction2*10;
                    }
                    else{
                        bot2X-=(1+direction2*5);
                    }
                }

                Jump();
                ChangeFrame();

                if (Condition(2)) {
                    playerX += speed;
                } else {

                    if (speed > 0) {
                        if (playerX >= dHeight * 2 ) {
                            playerX += 0;
                            speed = -2f;
                        } else {

                            playerX += speed;
                        }
                    } else {
                        playerX += speed;
                    }
                }
                speed += stopping;
                if (speed > speedRace) {
                    speedRace = speed;
                }

                if (stopping < -1.8) {
                    stopping *= 1.01;
                }

                WorkWithspeedRace();

            }
            distance=converter(distance,speedRace/4);

            finishin=converter(finishin,lengthLevel-meters);

            if(translating_finishing>-31&&translating_finishing<31)
            {
                if(translating_finishing==30 ||translating_finishing==-30){a*=-1;}
                translating_finishing+=a;
            }
            handler.postDelayed(runnable, UPDATE_MILLIS);
        }

        else stopGame();

    }

        private void ChangeFrame() {
            if(Shop.skin==1) {
                if (playerFrame < 9) {
                    playerFrame++;
                } else {
                    playerFrame = 0;
                }
            }
            else{
                if (playerFrame < 7) {
                    playerFrame++;
                } else {
                    playerFrame = 0;
                }}


            if (botFrame < 9) {
                botFrame++;
            } else {
                botFrame = 0;
            }
        }

        private void Jump() {
            playerY -= speedup;
            if (playerY < dHeight / 1.5f - 40 - player[playerFrame].getHeight() / 2) {
                speedup -= gravity;
            }
            if (playerY >= dHeight / 1.5f - 40 - player[playerFrame].getHeight() / 2 && !isgrounded) {
                speedup = 0;
                isgrounded = true;
            }

        }

        private void WorkWithX() {
            if (raceX > -3000) {
                raceX -= speedRace;
            } else {
                raceX = race1X + race.getWidth() - 150;
            }
            if (race1X > -3000) {
                race1X -= speedRace;
            } else {
                race1X = raceX + race1_1.getWidth() - 150;
            }
            if (bgX > -2570) {
                bgX -= speedRace*0.1;
            } else {
                bgX = bg1X + background_2.getWidth()-30;
            }
            if (bg1X > -2570) {
                bg1X -= speedRace*0.1;
            } else {
                bg1X = bgX + background.getWidth()-30;
            }
            if (playerX < -50) {
                gameState = false;
                gameStopped = true;
            }

        }

        private void WorkWithspeedRace() {
            if (speedRace < 20) {
                speedRace += 0.003;
            }
            if (speedRace < 40 && speedRace >= 20) {
                speedRace += 0.009;
            }
            if (speedRace >= 40 && speedRace < 80) {
                speedRace += 0.02;
            }
        }

        private Path drawTriangle() {
            Point a = new Point(dWight-150, 155+translating_finishing);
            Point b = new Point(dWight-150, 215+translating_finishing);
            Point c = new Point(dWight-100, 185+translating_finishing);

            Path path = new Path();
            path.setFillType(Path.FillType.EVEN_ODD);
            path.moveTo(b.x,b.y);
            path.lineTo(b.x, b.y);
            path.lineTo(c.x, c.y);
            path.lineTo(a.x, a.y);
            path.close();
            return path;


        }

        private String converter(String s,float f) {

            s=String.valueOf(f);
            maxChar=s.indexOf(".");
            s=s.substring(0, maxChar+2);
            return s;
        }
    private boolean Condition(int i) {
        switch (i)
        {
            case 0: { if(!gameStopped && lengthLevel>=meters) return true;
                        else return false;}
            case 1:{ if (playerX > 900 && i>0) return true;}
            case 2:{if(playerX < dHeight * 2) return true;}
            case 3:{}
            case 4:{}
            case 5:{}

        }
        return false;
    }

    }
