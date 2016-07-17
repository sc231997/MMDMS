package ml.alohomora.mmdms;

import android.app.Dialog;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.ShareCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    SQLiteDatabase sqLiteDatabase;
    Cursor cursor;
    EditText editText;
    ImageButton imageButton;
    String searchType = "Generic";
    ListView listView;
    ArrayList<String> name,contact,lastVisit;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button button = (Button)findViewById(R.id.buttonLaunchTabbed);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this,PrimaryTabbedActivity.class);
                startActivity(intent);
                finish();
            }
        });

        initialise();
        editText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if(charSequence.length() > 2)
                    search(charSequence, "Generic");
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    void initialise()
    {
        sqLiteDatabase = SQLiteDatabase.openDatabase("PatInfo",null,MODE_PRIVATE);
        sqLiteDatabase.execSQL("CREATE TABLE IF NOT EXISTS PatInfo (pid INT,eid INT, name VARCHAR(100),contactNumber VARCHAR(100),age INT,gender VARCHAR(10),bloodGroup VARCHAR(5),"+"" +
                "glucoseLevel FLOAT,respiratoryProblem VARCHAR(2), cardiacProblem VARCHAR(2),bmi FLOAT,weight FLOAT,height FLOAT,haemoglobin FLOAT,wbc LONG,balance FLOAT, amount FLOAT)");
        listView = (ListView)findViewById(R.id.listViewPrimary);
        editText = (EditText) findViewById(R.id.editTextSearch);
        imageButton = (ImageButton)findViewById(R.id.imageButtonSortedSearch);
        imageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showDialogAndGetSort();
            }
        });
    }
    void search(CharSequence querySeq,String searchType)
    {
        String queryString = querySeq.toString();
        if(sqLiteDatabase == null)
        {
            Toast.makeText(MainActivity.this,"Sorry some problem with the database,please contact tech support",Toast.LENGTH_LONG).show();
        }
        else
        {
            switch (searchType)
            {
                case "Generic":
                    cursor = sqLiteDatabase.rawQuery("SELECT * from PatInfo WHERE name LIKE '%" + queryString + "%' OR " +
                            "contactNumber LIKE '%" + queryString + "%';",null);
                    break;
                case "BMI":
                    cursor = sqLiteDatabase.rawQuery("SELECT * from PatInfo ORDER BY bmi",null);
                    break;
                case "GlucoseLevel":
                    cursor = sqLiteDatabase.rawQuery("SELECT * from PatInfo ORDER BY glucoseLevel",null);
                    break;
                case "Haemoglobin":
                    cursor = sqLiteDatabase.rawQuery("SELECT * from PatInfo ORDER BY haemoglobin",null);
                    break;
                case "Height":
                    cursor = sqLiteDatabase.rawQuery("SELECT * from PatInfo ORDER BY height",null);
                    break;
                case "WBC":
                    cursor = sqLiteDatabase.rawQuery("SELECT * from PatInfo ORDER BY wbc",null);
                    break;
                case "Weight":
                    cursor = sqLiteDatabase.rawQuery("SELECT * from PatInfo ORDER BY weight",null);
                    break;
                case "Age":
                    cursor = sqLiteDatabase.rawQuery("SELECT * from PatInfo ORDER BY age",null);
                    break;

            }
            cursor.moveToFirst();
            while (cursor.isAfterLast() == false)
            {
                name.add(cursor.getString(2));
                contact.add(cursor.getString(3));

            }
            CustomListAdapter customListAdapter = new CustomListAdapter(MainActivity.this,name,lastVisit,contact);
            listView.setAdapter(customListAdapter);

        }
    }
    void showDialogAndGetSort()
    {
        final Dialog dialog = new Dialog(MainActivity.this);
        dialog.setContentView(R.layout.dialog_sort_select);
        dialog.show();
        RadioGroup radioGroup = (RadioGroup) dialog.findViewById(R.id.radioGroupSort);
        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                switch (radioGroup.getCheckedRadioButtonId())
                {
                    case R.id.radioButtonSortName:
                        searchType = "Generic";
                        break;
                    case R.id.radioButtonSortBMI:
                        searchType = "BMI";
                        break;
                    case R.id.radioButtonSortGlucoseLevel:
                        searchType = "GlucoseLevel";
                        break;
                    case R.id.radioButtonSortHaemoglobin:
                        searchType = "Haemoglobin";
                        break;
                    case R.id.radioButtonSortHeight:
                        searchType = "Height";
                        break;
                    case R.id.radioButtonSortWBC:
                        searchType = "WBC";
                        break;
                    case R.id.radioButtonSortWeight:
                        searchType = "Weight";
                        break;
                    case R.id.radioSortButtonAge:
                        searchType = "Age";
                }
            }
        });
        findViewById(R.id.buttonSortOk).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });
    }
}
