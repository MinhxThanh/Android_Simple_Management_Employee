package pro.edu.sqlite;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

import pro.edu.model.Employee;

public class EmployeeDAO {
   private SQLiteDatabase db;

    public EmployeeDAO(Context context) {
        DBHelper helper = new DBHelper(context);
        db = helper.getWritableDatabase();
    }

    @SuppressLint("Range")
    public List<Employee> get(String sql, String... selectionArgs) {
        List<Employee> employees = new ArrayList<>();
        Cursor cursor = db.rawQuery(sql, selectionArgs);
        while (cursor.moveToNext()) {
            Employee employee = new Employee(
                    cursor.getString(cursor.getColumnIndex("id")),
                    cursor.getString(cursor.getColumnIndex("name")),
                    cursor.getFloat(cursor.getColumnIndex("salary"))
            );
            employees.add(employee);
        }
        return employees;
    }

    public List<Employee> getAll() {
        String sqlQuery = "select * from employee";
        return get(sqlQuery);
    }

    public Employee getById(String id) {
        String sqlQuery = "select * from employee where id = ?";
        List<Employee> employees = get(sqlQuery, id);
        return employees.get(0);
    }

    public long insert(Employee employee) {
        ContentValues values = new ContentValues();
        values.put("id", employee.getId());
        values.put("name", employee.getName());
        values.put("salary", employee.getSalary());
        return db.insert("employee", null, values);
    }

    public int update(Employee employee) {
        ContentValues values = new ContentValues();
        values.put("name", employee.getName());
        values.put("salary", employee.getSalary());
        return db.update("employee", values, "id = ?", new String[]{employee.getId()});
    }

    public int delete(String id) {
        return db.delete("employee", "id = ?", new String[]{id});
    }

}
