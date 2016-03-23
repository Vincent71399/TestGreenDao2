package test.greendao.com.testgreendao2;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import java.util.Date;
import java.util.List;

import de.greenrobot.dao.query.QueryBuilder;
import test.greendao.com.testgreendao2.db.DaoMaster;
import test.greendao.com.testgreendao2.db.DaoSession;
import test.greendao.com.testgreendao2.db.Program;
import test.greendao.com.testgreendao2.db.ProgramDao;

public class MainActivity extends AppCompatActivity {

    private SQLiteDatabase db;
    private DaoMaster daoMaster;
    private DaoSession daoSession;
    private ProgramDao programDao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        DaoMaster.DevOpenHelper helper = new DaoMaster.DevOpenHelper(this, "test-db", null);
        db = helper.getWritableDatabase();
        daoMaster = new DaoMaster(db);
        daoSession = daoMaster.newSession();
        programDao = daoSession.getProgramDao();

        Program program = new Program(null, "Somename", new Date());
        programDao.insert(program);

        QueryBuilder queryBuilder = programDao.queryBuilder();
        List<Program> resultList = queryBuilder.list();
        Toast.makeText(this, "Size:" + resultList.size(), Toast.LENGTH_SHORT).show();


    }
}
