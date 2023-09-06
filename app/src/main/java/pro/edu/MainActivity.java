package pro.edu;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.List;

import pro.edu.adapter.EmployeeAdapter;
import pro.edu.model.Employee;
import pro.edu.sqlite.EmployeeDAO;

public class MainActivity extends AppCompatActivity  implements View.OnClickListener{
    private EmployeeAdapter employeeAdapter;
    private ListView lvEmployees;
    private String employeeId;
    private List<Employee> listEmployees;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        this.getMethod();
    }

    private void getMethod() {
        // Get references to edit, delete and insert buttons
        findViewById(R.id.btnEdit).setOnClickListener(this);
        findViewById(R.id.btnDelete).setOnClickListener(this);
        findViewById(R.id.btnInsert).setOnClickListener(this);

        // Get reference to ListView
        lvEmployees = (ListView) findViewById(R.id.lvEmployees);

        // Create and set adapter in one line
        // Get employees by creating DAO and calling getAll()
        // Pass context and employees to adapter constructor
        EmployeeDAO dao = new EmployeeDAO(this);
        listEmployees = dao.getAll();
        employeeAdapter = new EmployeeAdapter(this, listEmployees);
        lvEmployees.setAdapter(employeeAdapter);

        lvEmployees.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Employee employee = listEmployees.get(i);
                employeeId = employee.getId();
            }
        });
    }

    @Override
    public void onClick(View view) {
        Intent intent = new Intent(this, AddOrEditEmployeeActivity.class);
        if (view.getId() == R.id.btnEdit) {
            if (employeeId == null) {
                Toast.makeText(this, "Please select Item!", Toast.LENGTH_SHORT).show();
                return;
            }

            Bundle bundle = new Bundle();
            bundle.putString("id", employeeId);

            intent.putExtra("data", bundle);
            startActivity(intent);
        } else if (view.getId() == R.id.btnInsert) {
            startActivity(intent);
        } else if (view.getId() == R.id.btnDelete) {
            if (employeeId == null) {
                Toast.makeText(this, "Please select Item!", Toast.LENGTH_SHORT).show();
                return;
            }

            EmployeeDAO dao = new EmployeeDAO(this);
            dao.delete(employeeId);
            employeeId = null;
            onResume();
            Toast.makeText(this, "Delete employee successfully!", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    protected void onResume() {
        super.onResume();

        EmployeeDAO dao = new EmployeeDAO(this);
        List<Employee> updatedList = dao.getAll();
        listEmployees.clear();
        updatedList.forEach(employee -> listEmployees.add(employee));

        employeeAdapter.notifyDataSetChanged();
    }
}