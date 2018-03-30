package io.github.abhimanbhau.userdb.ui;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import io.github.abhimanbhau.userdb.R;
import io.github.abhimanbhau.userdb.db.UserDataContract;
import io.github.abhimanbhau.userdb.db.UserDataDbHelper;

public class MainActivity extends AppCompatActivity {

    private Spinner spinner;
    private EditText txtName;
    private EditText txtEmail;
    private EditText txtID;

    private SQLiteDatabase sqLiteDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        spinner = findViewById(R.id.spinnerMovies);
        txtName = findViewById(R.id.txtName);
        txtEmail = findViewById(R.id.txtEmail);
        txtID = findViewById(R.id.txtID);

        Button btnAdd = findViewById(R.id.btnAdd);
        Button btnUpdate = findViewById(R.id.btnUpdate);
        Button btnDelete = findViewById(R.id.btnDelete);
        Button btnView = findViewById(R.id.btnView);

        initDb();

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!checkAndValidateData(true)) {
                    Toast.makeText(getApplicationContext(),
                            "Enter Data!", Toast.LENGTH_SHORT).show();
                    return;
                }
                AddToDb(txtName.getText().toString(),
                        txtEmail.getText().toString(),
                        spinner.getSelectedItem().toString());
                Toast.makeText(getApplicationContext(),
                        "Data Added!", Toast.LENGTH_SHORT).show();
            }
        });

        btnView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                builder.setTitle("Data")
                        .setMessage(getAllEntries());
                AlertDialog alert = builder.create();
                alert.show();
            }
        });

        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (txtID.getText().toString().length() == 0) {
                    Toast.makeText(getApplicationContext(),
                            "Enter ID!", Toast.LENGTH_SHORT).show();
                    return;
                }
                Toast.makeText(getApplicationContext(),
                        deleteEntry(Integer
                                .valueOf(txtID.getText().toString())) + " Rows deleted",
                        Toast.LENGTH_SHORT).show();
            }
        });

        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (txtID.getText().toString().length() == 0) {
                    Toast.makeText(getApplicationContext(),
                            "Enter ID", Toast.LENGTH_SHORT).show();
                    return;
                }
                Toast.makeText(getApplicationContext(),
                        updateData(txtName.getText().toString(),
                                txtEmail.getText().toString(),
                                spinner.getSelectedItem().toString(),
                                txtID.getText().toString()) + " Rows Updated!"
                        , Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void initDb() {
        UserDataDbHelper userDataDbHelper = new UserDataDbHelper(getApplicationContext());
        sqLiteDatabase = userDataDbHelper.getWritableDatabase();
    }

    private boolean checkAndValidateData(boolean addData) {
        if (addData) {
            if (txtName.getText().toString().length() == 0 ||
                    txtEmail.getText().toString().length() == 0)
                return false;
        } else {
            if (txtName.getText().toString().length() == 0 ||
                    txtEmail.getText().toString().length() == 0 ||
                    txtID.getText().toString().length() == 0)
                return false;
        }
        return true;
    }

    private void AddToDb(String name, String email, String show) {
        ContentValues values = new ContentValues();
        values.put(UserDataContract.UserEntry.COLUMN_NAME_NAME, name);
        values.put(UserDataContract.UserEntry.COLUMN_NAME_EMAIL, email);
        values.put(UserDataContract.UserEntry.COLUMN_NAME_FAVSHOWS, show);
        sqLiteDatabase.insert(UserDataContract.UserEntry.TABLE_NAME,
                null, values);
    }

    private String getAllEntries() {
        StringBuilder sb = new StringBuilder();
        Cursor cursor = sqLiteDatabase.rawQuery("select * from " +
                UserDataContract.UserEntry.TABLE_NAME, null);
        if (cursor.moveToFirst()) {
            while (!cursor.isAfterLast()) {
                sb.append("ID: ");
                String id =
                        cursor.getString(cursor.getColumnIndex(UserDataContract.UserEntry._ID));
                sb.append(id).append("\n");
                sb.append("NAME: ");
                String name =
                        cursor.getString(cursor
                                .getColumnIndex(UserDataContract.UserEntry.COLUMN_NAME_NAME));
                sb.append(name).append("\n");
                sb.append("EMAIL: ");
                String email =
                        cursor.getString(cursor
                                .getColumnIndex(UserDataContract.UserEntry.COLUMN_NAME_EMAIL));
                sb.append(email).append("\n");
                sb.append("FAV SHOW: ");
                String show =
                        cursor.getString(cursor
                                .getColumnIndex(UserDataContract.UserEntry.COLUMN_NAME_FAVSHOWS));
                sb.append(show).append("\n\n");
                cursor.moveToNext();
            }
        }
        cursor.close();
        return sb.toString();
    }

    private int deleteEntry(int id) {
        String selection = UserDataContract.UserEntry._ID + " = ?";
        String[] selectionArgs = {String.valueOf(id)};
        return sqLiteDatabase.delete(UserDataContract.UserEntry.TABLE_NAME,
                selection, selectionArgs);
    }

    private int updateData(String name, String email, String show, String id) {
        if (name.length() == 0 && email.length() == 0 && show.length() == 0) {
            Toast.makeText(getApplicationContext(),
                    "No data to update", Toast.LENGTH_SHORT).show();
            return 0;
        }


        ContentValues values = new ContentValues();
        if (name.length() != 0)
            values.put(UserDataContract.UserEntry.COLUMN_NAME_NAME, name);
        if (email.length() != 0)
            values.put(UserDataContract.UserEntry.COLUMN_NAME_EMAIL, email);
        if (show.length() != 0)
            values.put(UserDataContract.UserEntry.COLUMN_NAME_FAVSHOWS, show);
        String selection = UserDataContract.UserEntry._ID + " = ?";
        String[] selectionArgs = {id};

        return sqLiteDatabase.update(
                UserDataContract.UserEntry.TABLE_NAME,
                values,
                selection,
                selectionArgs);
    }
}
