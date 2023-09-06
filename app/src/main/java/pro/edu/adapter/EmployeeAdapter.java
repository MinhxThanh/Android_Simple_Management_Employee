package pro.edu.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

import pro.edu.R;
import pro.edu.model.Employee;

public class EmployeeAdapter extends BaseAdapter {
    private Context context;
    private List<Employee> employees;

    public EmployeeAdapter(Context context, List<Employee> employees) {
        this.context = context;
        this.employees = employees;
    }

    @Override
    public int getCount() {
        return employees.size();
    }

    @Override
    public Object getItem(int i) {
        return employees.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        if (view == null)
            view = LayoutInflater.from(context).inflate(R.layout.layout_list_view_employee_item, null);

        TextView tvName = (TextView) view.findViewById(R.id.tvName);
        TextView tvSalary = (TextView) view.findViewById(R.id.tvSalary);

        Employee employee = employees.get(i);

        tvName.setText(employee.getName());
        tvSalary.setText("$ " + employee.getSalary());
        return view;
    }
}
