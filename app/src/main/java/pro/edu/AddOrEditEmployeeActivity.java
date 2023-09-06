package pro.edu;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import pro.edu.model.Employee;
import pro.edu.sqlite.EmployeeDAO;

public class AddOrEditEmployeeActivity extends AppCompatActivity  implements View.OnClickListener{
    private EditText etId, etName, etSalary;
    private Button btnAddOrUpdate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_or_edit_employee);

        this.getMethod();
        this.readEmployee();
    }

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.btnSave) {
            EmployeeDAO dao = new EmployeeDAO(this);
            Employee employee = new Employee(
                    etId.getText().toString(),
                    etName.getText().toString(),
                    Float.parseFloat(etSalary.getText().toString())
            );
            if (btnAddOrUpdate.getText().equals("Update")) {
                dao.update(employee);
                Toast.makeText(this, "Update employee successfully!", Toast.LENGTH_SHORT).show();
            } else {
                dao.insert(employee);
                Toast.makeText(this, "Create new employee successfully!", Toast.LENGTH_SHORT).show();
            }

        } else if (view.getId() == R.id.btnListEmployee) {
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
        }
    }

    private void readEmployee() {
        Intent intent = getIntent();
        Bundle bundle = intent.getBundleExtra("data");
        if (bundle == null) {
            btnAddOrUpdate.setText("Save");
            return;
        }

        String employeeId = bundle.getString("id");
        System.out.println("bugHere:: " + employeeId);
        EmployeeDAO dao = new EmployeeDAO(this);
        Employee employee = dao.getById(employeeId);

        etId.setText(employee.getId());
        etName.setText(employee.getName());
        etSalary.setText("" + employee.getSalary());
    }

    private void getMethod() {
        etId = (EditText) findViewById(R.id.etId);
        etName = (EditText) findViewById(R.id.etName);
        etSalary = (EditText) findViewById(R.id.etSalary);

        btnAddOrUpdate = findViewById(R.id.btnSave);
        btnAddOrUpdate.setOnClickListener(this);
        btnAddOrUpdate.setText("Update");

        findViewById(R.id.btnListEmployee).setOnClickListener(this);
    }
}