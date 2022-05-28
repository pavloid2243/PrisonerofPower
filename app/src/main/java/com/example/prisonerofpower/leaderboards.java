package com.example.prisonerofpower;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

public class leaderboards extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_leaderboards);
        addRows();

    }

    private void addRows() {
        long i = DatabaseUtils.queryNumEntries(Profile.database, DBHelper.TABLE_CONTACTS);

        Cursor cursor = Profile.database.query(DBHelper.TABLE_CONTACTS,null,null,null,null,null,"score");
        if(cursor.moveToFirst())
        {

            int nameIndex = cursor.getColumnIndex((DBHelper.KEY_NAME));
            int passIndex = cursor.getColumnIndex((DBHelper.KEY_PASSWORD));
            int scoreIndex = cursor.getColumnIndex((DBHelper.KEY_SCORE));
            int levelsIndex = cursor.getColumnIndex((DBHelper.KEY_LEVELS));

            do{
                addRow(cursor.getString(nameIndex),cursor.getString(passIndex),cursor.getString(scoreIndex),cursor.getString(levelsIndex));


            } while(cursor.moveToNext());

        }
        else
            Log.d("mLog","0 rows");


        cursor.close();

    }

    public void back(View view)
    {
        Intent intent = new Intent(this,MainActivity.class);
        startActivity(intent);
    }
    public static void addRowStage(String c0, String  c1,String  c2,String  c3)
    {
        new leaderboards().addRow(c0,c1,c2,c3);
    }
    public void addRow(String c0, String  c1,String  c2,String  c3) {
        //Сначала найдем в разметке активити саму таблицу по идентификатору
        TableLayout tableLayout = (TableLayout) findViewById(R.id.table);
        //Создаём экземпляр инфлейтера, который понадобится для создания строки таблицы из шаблона. В качестве контекста у нас используется сама активити
        LayoutInflater inflater = LayoutInflater.from(this);
        //Создаем строку таблицы, используя шаблон из файла /res/layout/table_row.xml
        TableRow tr = (TableRow) inflater.inflate(R.layout.table_row, null);
        //Находим ячейку для номера дня по идентификатору
        TextView tv = (TextView) tr.findViewById(R.id.col1);
        //Обязательно приводим число к строке, иначе оно будет воспринято как идентификатор ресурса
        tv.setText(c0+ "   ");
        //Ищем следующую ячейку и устанавливаем её значение
        tv = (TextView) tr.findViewById(R.id.col2);
        tv.setText(c1+ "   ");
        tv = (TextView) tr.findViewById(R.id.col3);
        tv.setText(c2+ "   ");
        tv = (TextView) tr.findViewById(R.id.col4);
        tv.setText(c3);

        tableLayout.addView(tr); //добавляем созданную строку в таблицу
    }
}