package com.mobile.soundtest;

import android.annotation.SuppressLint;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class sound_sqlite extends AppCompatActivity {

    myDBHelper myDBHelper;
    SQLiteDatabase sqlDB;
    EditText et_saveName;
    Button btn_save;
    Button btn_sound1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sound_sqlite);
        setTitle("SQLite Check");

        myDBHelper = new myDBHelper(this);
        btn_save.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("WrongConstant")
            @Override
            public void onClick(View v) {
                sqlDB = myDBHelper.getWritableDatabase();
                sqlDB.execSQL("INSERT INTO DBSoundCheck VALUES ( '" + et_saveName.getText().toString() + "' , ");
                sqlDB.close();
                Toast.makeText(getApplicationContext(),"입력됨",0).show();
            }
        });

        btn_sound1 = (Button) findViewById(R.id.btn_sound1);
        MediaPlayer mediaPlayer = MediaPlayer.create(this, R.raw.sound1);
        btn_sound1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mediaPlayer.start();
            }
        });

    }

    public class myDBHelper extends SQLiteOpenHelper {

        public myDBHelper(Context context) {
            super(context, "DBSoundCheck", null, 1);
        }

        @Override
        public void onCreate(SQLiteDatabase db) {
            db.execSQL("CREATE TABLE DBSoundCheck ( sNumber INTEGER PRIMARY KEY);");
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            db.execSQL("DROP TABLE IF EXISTS DBSoundCheck");
            onCreate(db);
        }
    }

}