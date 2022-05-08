package com.example.db1demo;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.text.ParseException;

public class MainActivity extends AppCompatActivity {
    EditText name, contact, vid, vdesc, vyear, vtype, vcategory, veh_type, veh_category, veh_startdate, veh_id, cut_name, customerid_5, customer_name5,
            veh_endDate, amountdue_customername, amountdue_rdata, amountdue_vehicleID, vin_5, vehicledesc_5;
    Button viewCustomer, addnewCustomer, viewVehicle, addnewcehicle, viewsearchVehicle, btnBookVehicle, btnfetchpayment,
            btnpayment, btn_rentalDetailsView, btnVew5 ,btnView_5;
    DBHelper DB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        name = findViewById(R.id.name);
        contact = findViewById(R.id.contact);
        vid = findViewById(R.id.vid);
        vdesc = findViewById(R.id.vdesc);
        vyear = findViewById(R.id.vyear);
        vtype = findViewById(R.id.vtype);
        vcategory = findViewById(R.id.vcategory);
        viewCustomer = findViewById(R.id.btnViewCustomer);
        viewVehicle = findViewById(R.id.btnViewVehicle);
        addnewcehicle = findViewById(R.id.btnAddNewVehicle);

        veh_type = findViewById(R.id.veh_type);
        veh_category = findViewById(R.id.veh_category);
        veh_startdate = findViewById(R.id.veh_startdate);
        veh_id = findViewById(R.id.veh_id);
        cut_name = findViewById(R.id.veh_cust_name);
        veh_endDate = findViewById(R.id.veh_enddate);

        vin_5 = findViewById(R.id.vin5);
        vehicledesc_5 = findViewById(R.id.vehc_desc);

        customerid_5 = findViewById(R.id.customerid5);
        customer_name5 = findViewById(R.id.customername5);


        amountdue_customername = findViewById(R.id.cust_name_4);
        amountdue_vehicleID = findViewById(R.id.veh_id_4);
        amountdue_rdata = findViewById(R.id.returnDate_4);

        //Adding a new customer Requirement 1
        addnewCustomer = findViewById(R.id.btnAddCustomer);

        viewsearchVehicle = findViewById(R.id.btnSearchVehicle);
        btn_rentalDetailsView = findViewById(R.id.btn_rentalDetails);
        btnVew5 = findViewById(R.id.btn_showresult);

        btnView_5 = findViewById(R.id.btn_showvech);

        btnBookVehicle = findViewById(R.id.btnBookVehicle);

        btnfetchpayment = findViewById(R.id.btn_amountdue);
        btnpayment = findViewById(R.id.btn_payment4);

        DB = new DBHelper(this);

        addnewCustomer.setOnClickListener(
                view -> {
                    String nameTXT = name.getText().toString();
                    String contactTXT = contact.getText().toString();
                    Boolean checkAddedcust = DB.addNewCusotmer(nameTXT, contactTXT);
                    if (checkAddedcust) {
                        Toast.makeText(MainActivity.this, "New Customer Added", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(MainActivity.this, "Not Able To Add New Customer", Toast.LENGTH_SHORT).show();
                    }
                }
        );

        addnewcehicle.setOnClickListener(
                view -> {
                    String idtxt = vid.getText().toString();
                    String desctxt = vdesc.getText().toString();
                    String yeartxt = vyear.getText().toString();
                    String typetxt = vtype.getText().toString();
                    String cattxt = vcategory.getText().toString();
                    Boolean checkAddedVehcile = DB.addNewVehicle(idtxt, desctxt, yeartxt, typetxt, cattxt);
                    if (checkAddedVehcile) {
                        Toast.makeText(MainActivity.this, "New Vehicle Added", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(MainActivity.this, "Not Able To Add New Vehicle", Toast.LENGTH_SHORT).show();
                    }
                }
        );


//
//        insert.setOnClickListener(
//                new View.OnClickListener() {
//                    @Override
//                    public void onClick(View view) {
//                        String nameTXT = name.getText().toString();
//                        String contactTXT = contact.getText().toString();
//                        String dobTXT = dob.getText().toString();
//
//                        Boolean checkinsertdata = DB.insertuserdata(nameTXT, contactTXT, dobTXT);
//                        if(checkinsertdata == true){
//                            Toast.makeText(MainActivity.this, "New Entry Inserted", Toast.LENGTH_SHORT).show();
//                        }
//                        else{
//                            Toast.makeText(MainActivity.this, "New Entry Not Inserted", Toast.LENGTH_SHORT).show();
//                        }
//                    }
//                }
//        );
//
//        update.setOnClickListener(
//                new View.OnClickListener() {
//                    @Override
//                    public void onClick(View view) {
//                        String nameTXT = name.getText().toString();
//                        String contactTXT = contact.getText().toString();
//                        String dobTXT = dob.getText().toString();
//                        Boolean checkupdatedata = DB.updateuserdata(nameTXT, contactTXT, dobTXT);
//                        if(checkupdatedata == true){
//                            Toast.makeText(MainActivity.this, "Updated Data.", Toast.LENGTH_SHORT).show();
//                        }
//                        else{
//                            Toast.makeText(MainActivity.this, "Not Updated Data.", Toast.LENGTH_SHORT).show();
//                        }
//                    }
//                }
//        );
//
//        delete.setOnClickListener(
//                new View.OnClickListener() {
//                    @Override
//                    public void onClick(View view) {
//                        String nameTXT = name.getText().toString();
//                        Boolean checkdeletedata = DB.deletedata(nameTXT);
//                        if(checkdeletedata == true){
//                            Toast.makeText(MainActivity.this, "Entry Deleted", Toast.LENGTH_SHORT).show();
//                        }
//                        else{
//                            Toast.makeText(MainActivity.this, "Entry Not Deleted", Toast.LENGTH_SHORT).show();
//                        }
//                    }
//                }
//        );
//
//        view.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Cursor result = DB.getdata();
//                if(result.getCount() == 0){
//                    Toast.makeText(MainActivity.this, "No Entry exist", Toast.LENGTH_SHORT).show();
//                    return;
//                }
//                StringBuffer buffer = new StringBuffer();
//                while(result.moveToNext())
//                {
//                    buffer.append("Name :"+result.getString(0)+"\n");
//                    buffer.append("Contact :"+result.getString(1)+"\n");
//                    buffer.append("Date of Birth :"+result.getString(2)+"\n\n");
//                }
//
//                AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
//                builder.setCancelable(true);
//                builder.setTitle("User Entries:");
//                builder.setMessage(buffer.toString());
//                builder.show();
//            }
//        });

        viewCustomer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Cursor result = DB.getCustomerdata();
                if (result.getCount() == 0) {
                    Toast.makeText(MainActivity.this, "No Entry exist", Toast.LENGTH_SHORT).show();
                    return;
                }
                int recordCount = result.getCount();
                StringBuffer buffer = new StringBuffer();
                buffer.append("Total Number of Records: " + recordCount + "\n\n");
                while (result.moveToNext()) {
                    buffer.append("Customer ID :" + result.getString(0) + "\n");
                    buffer.append("Name :" + result.getString(1) + "\n");
                    buffer.append("Phone :" + result.getString(2) + "\n\n");
                }

                AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                builder.setCancelable(true);
                builder.setTitle("User Entries:");
//                builder.setMessage();
                builder.setMessage(buffer.toString());
                builder.show();
            }
        });

        viewVehicle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Cursor result = DB.getVehicledata();
                if (result.getCount() == 0) {
                    Toast.makeText(MainActivity.this, "No Entry exist", Toast.LENGTH_SHORT).show();
                    return;
                }
                int recordCount = result.getCount();
                StringBuffer buffer = new StringBuffer();
                buffer.append("Total Number of Records: " + recordCount + "\n\n");
                while (result.moveToNext()) {
                    buffer.append("Vehicle ID :" + result.getString(0) + "\n");
                    buffer.append("Description :" + result.getString(1) + "\n");
                    buffer.append("Year :" + result.getString(2) + "\n");
                    buffer.append("Type :" + result.getString(3) + "\n");
                    buffer.append("Category :" + result.getString(4) + "\n\n");
                }

                AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                builder.setCancelable(true);
                builder.setTitle("Vehicle Entries:");
//                builder.setMessage();
                builder.setMessage(buffer.toString());
                builder.show();
            }
        });

        //View searched vehicle
        viewsearchVehicle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String v_type = veh_type.getText().toString();
                String v_c = veh_category.getText().toString();
                String v_sdate = veh_startdate.getText().toString();
                Cursor result = DB.getVehicleRecords(v_type, v_c, v_sdate);
                if (result.getCount() == 0) {
                    Toast.makeText(MainActivity.this, "No Entry exist", Toast.LENGTH_SHORT).show();
                    return;
                }
                int recordCount = result.getCount();
                StringBuffer buffer = new StringBuffer();
                buffer.append("Total Number of Available Vehicles: " + recordCount + "\n\n");
                while (result.moveToNext()) {
                    buffer.append("Vehicle ID :" + result.getString(0) + "\n");
                    buffer.append("Description :" + result.getString(1) + "\n");
                }

                AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                builder.setCancelable(true);
                builder.setTitle("Vehicle Available:");
//                builder.setMessage();
                builder.setMessage(buffer.toString());
                builder.show();
            }
        });


        btnBookVehicle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String v_type = veh_type.getText().toString();
                String v_c = veh_category.getText().toString();
                String v_sdate = veh_startdate.getText().toString();
                String v_endDate = veh_endDate.getText().toString();
                String vehicleID = veh_id.getText().toString();
                String cutomer_name = cut_name.getText().toString();
                Boolean checkbooked = null;
                try {
                    checkbooked = DB.bookVehicle(cutomer_name, vehicleID, v_sdate, v_endDate, v_type, v_c);
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                System.out.println("Value of checkbooked" + checkbooked);
                StringBuffer buffer = new StringBuffer();
                AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                builder.setCancelable(true);
                builder.setMessage(buffer.toString());
                if (checkbooked == true) {
                    builder.setTitle("Booked!!!!");
                } else {
                    builder.setTitle("Not able to book.");
                }
                builder.show();
            }
        });

        //View payment
        btnfetchpayment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String cname = amountdue_customername.getText().toString();
                String v_id = amountdue_vehicleID.getText().toString();
                String v_rdate = amountdue_rdata.getText().toString();
                Cursor result = DB.paymentDue(cname, v_id, v_rdate);
                if (result.getCount() == 0) {
                    Toast.makeText(MainActivity.this, "Not Added.", Toast.LENGTH_SHORT).show();
                    return;
                }
                int recordCount = result.getCount();
                StringBuffer buffer = new StringBuffer();
                while (result.moveToNext()) {
                    buffer.append("Total Amount Calculated :" + result.getString(0) + "\n");
                }
                AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                builder.setCancelable(true);
                builder.setTitle("Payment Due:");
                builder.setMessage(buffer.toString());
                builder.show();
            }
        });

        //Do payment
        btnpayment.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.O)
            @Override
            public void onClick(View view) {
                String cname = amountdue_customername.getText().toString();
                String v_id = amountdue_vehicleID.getText().toString();
                String v_rdate = amountdue_rdata.getText().toString();
                Boolean payment_settle = DB.paymentSettle(cname, v_id, v_rdate);
                if (payment_settle) {
                    Toast.makeText(MainActivity.this, "Payment Sucessfull! Thankyou", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(MainActivity.this, "Not able to process the request.", Toast.LENGTH_SHORT).show();
                }
            }
        });


        btn_rentalDetailsView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Cursor result = DB.getRentalRecords();
                if (result.getCount() == 0) {
                    Toast.makeText(MainActivity.this, "No Data Exist.", Toast.LENGTH_SHORT).show();
                    return;
                }
                int recordCount = result.getCount();
                StringBuffer buffer = new StringBuffer();
                buffer.append("Total Number of Records: " + recordCount + "\n\n");
                while (result.moveToNext()) {
                    buffer.append("Vehicle ID :" + result.getString(0) + "\n");
                    buffer.append("Return Date :" + result.getString(1) + "\n");
                    buffer.append("TotalAmount :" + result.getString(2) +"$"+ "\n");
                    buffer.append("Payment Date :" + result.getString(3) + "\n");
                    buffer.append("Returned? 1:Yes, 0:No --->  " + result.getString(4) + "\n\n");
                }
                AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                builder.setCancelable(true);
                builder.setTitle("Rental Entries:");
//                builder.setMessage();
                builder.setMessage(buffer.toString());
                builder.show();
            }
        });


        btnVew5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String customerid = customerid_5.getText().toString();
                String customername = customer_name5.getText().toString();
                Cursor result = DB.getfilteredCustomer(customerid,customername);
                if (result.getCount() == 0) {
                    Toast.makeText(MainActivity.this, "No Data Exist.", Toast.LENGTH_SHORT).show();
                    return;
                }
                int recordCount = result.getCount();
                StringBuffer buffer = new StringBuffer();
                buffer.append("Total Number of Records: " + recordCount + "\n\n");
                while (result.moveToNext()) {
                    buffer.append("Customer ID :" + result.getString(0) + "\n");
                    buffer.append("Name :" + result.getString(1) + "\n");
                    if((result.getString(2) == null) || (result.getString(3).contains("1"))){
                        buffer.append("TotalAmount :" + "0$" + "\n\n");
                    }
                    else{
                        System.out.println("Value -->"+result.getString(3));
                        buffer.append("TotalAmount :" + result.getString(2)+"$" + "\n\n");
                    }
                }
                AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                builder.setCancelable(true);
                builder.setTitle("Remaining Balance Records:");
                builder.setMessage(buffer.toString());
                builder.show();
            }
        });


        btnView_5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String vin_number = vin_5.getText().toString();
                String veh_description = vehicledesc_5.getText().toString();
                Cursor result = DB.getfilteredVehcile(vin_number,veh_description);
                if (result.getCount() == 0) {
                    Toast.makeText(MainActivity.this, "No Data Exist.", Toast.LENGTH_SHORT).show();
                    return;
                }
                int recordCount = result.getCount();
                StringBuffer buffer = new StringBuffer();
                buffer.append("Total Number of Records: " + recordCount + "\n\n");
                while (result.moveToNext()) {
                    buffer.append("Vehicle VIN :" + result.getString(0) + "\n");
                    buffer.append("Description :" + result.getString(1) + "\n");
                    if(result.getString(2) == null){
                        buffer.append("Average Daily Price :" + "Non-Applicable" + "\n\n");
                    }
                    else{
                        buffer.append("Average Daily Price :" + result.getString(2)+"$" + "\n\n");
                    }
                }
                AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                builder.setCancelable(true);
                builder.setTitle("Daily Average Price Records:");
                builder.setMessage(buffer.toString());
                builder.show();
            }
        });

    }
}

