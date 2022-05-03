package com.example.prisonerofpower;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;

import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;

import android.widget.TextView;

public class Profile extends Activity implements View.OnClickListener{
    public static boolean LOGGED = false;
    EditText etName;
    EditText etPass;
    TextView etError;
    static DBHelper dbHelper;
    Button btnAdd,btnLog,btnDelete;
    public static SQLiteDatabase database;
    public static ContentValues contentValues;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_profile);
        btnAdd=(Button) findViewById(R.id.button5) ;
        btnAdd.setOnClickListener(this);
        btnLog=(Button) findViewById(R.id.button4) ;
        btnLog.setOnClickListener(this);

        //btnRead=(Button) findViewById(R.id.button6) ;
        //btnRead.setOnClickListener(this);

        //btnDelete=(Button) findViewById(R.id.button3) ;
        //btnDelete.setOnClickListener(this);

        etName =(EditText) findViewById(R.id.editTextTextPersonName2);
        etPass=(EditText) findViewById(R.id.editTextTextPassword3);
        etError= (TextView) findViewById(R.id.textView4);
        dbHelper=new DBHelper(this);
    }
    @Override
    public void onClick(View v)
    {
        String name =etName.getText().toString();
        String pass =etPass.getText().toString();
        database = dbHelper.getWritableDatabase();
         contentValues = new ContentValues();
        switch (v.getId())
        {
            case R.id.button5:
                contentValues.put(DBHelper.KEY_NAME,name);
                contentValues.put(DBHelper.KEY_PASSWORD,pass);

                database.insert(DBHelper.TABLE_CONTACTS,null,contentValues);
                LOGGED=true;
                Log.d("mLog","aa");
                //finish();
                break;
            case R.id.button4:
                boolean proof =false;
                Log.d("mLog","tt");
                Cursor cursor = database.query(DBHelper.TABLE_CONTACTS,null,null,null,null,null,null);
                if(cursor.moveToFirst())
                {

                    int nameIndex = cursor.getColumnIndex((DBHelper.KEY_NAME));
                    int passIndex = cursor.getColumnIndex((DBHelper.KEY_PASSWORD));

                    do{

                        if(name.equals(cursor.getString(nameIndex)))
                        { Log.d("mLog","YESss ");
                            if(pass.equals(cursor.getString(passIndex)))
                            {
                                LOGGED=true;
                                ProfileInfo.Name=name;
                                ProfileInfo.Pass=pass;
                                proof=true;
                                Log.d("mLog","YES ");
                                Intent intent = new Intent(this, MainActivity.class);
                                startActivity(intent);
                            }
                        }
                    } while(cursor.moveToNext());

                }
                else
                    Log.d("mLog","0 rows");


                cursor.close();
                if(!proof) etError.setText("Wrong name or password");
                break;
            /*case R.id.button6:
                Cursor cursor = database.query(DBHelper.TABLE_CONTACTS,null,null,null,null,null,null);
                if(cursor.moveToFirst())
                {
                    int idIndex = cursor.getColumnIndex((DBHelper.KEY_ID));
                    int nameIndex = cursor.getColumnIndex((DBHelper.KEY_NAME));
                    int passIndex = cursor.getColumnIndex((DBHelper.KEY_PASSWORD));
                    do{
                        Log.d("mLog","ID = "+ cursor.getInt(idIndex)+
                                ", name = " + cursor.getString(nameIndex)+
                                ", password = " + cursor.getString(passIndex));
                        addRow(cursor.getString(nameIndex),cursor.getString(passIndex));
                    } while(cursor.moveToNext());

                }
                else
                    Log.d("mLog","0 rows");

                cursor.close();
                break;
            case R.id.button3:
                database.delete(DBHelper.TABLE_CONTACTS,null,null);
                break;*/
        }
        dbHelper.close();
    }
    /*public void addRow(String c0, String  c1) {
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
        tv.setText(c1);

        tableLayout.addView(tr); //добавляем созданную строку в таблицу
    }*/


}