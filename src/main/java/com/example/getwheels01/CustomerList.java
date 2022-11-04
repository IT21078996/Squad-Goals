package com.example.getwheels01;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

public class CustomerList extends ArrayAdapter<Customer> {
    private Activity context;
    List<Customer> customers;

    public CustomerList(Activity context, List<Customer> customers) {
        super(context, R.layout.layout_user_list, customers);
        this.context = context;
        this.customers = customers;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = context.getLayoutInflater();
        View listViewItem = inflater.inflate(R.layout.layout_user_list, null, true);
        TextView textViewName = (TextView) listViewItem.findViewById
                (R.id.textViewName);
        TextView textviewecar = (TextView) listViewItem.findViewById
                (R.id.textviewemail);
        TextView textviewnumber = (TextView) listViewItem.findViewById
                (R.id.textviewnumber);
        Customer Customer = customers.get(position);
        textViewName.setText(Customer.getName());
        textviewecar.setText(Customer.getCar());
        textviewnumber.setText(Customer.getConNo());
        return listViewItem;
    }
}