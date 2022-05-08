package com.example.db1demo;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Build;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.Locale;
import java.util.concurrent.TimeUnit;



public class DBHelper extends SQLiteOpenHelper {

    static final String DATABASE_NAME = "carRental.db";
    static final int DATABASE_VERSION = 1;
    public DBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase DB) {
        DB.execSQL("create Table Userdetails(name TEXT primary key, contact TEXT, dob TEXT)");

    }
    @Override
    public void onUpgrade(SQLiteDatabase DB, int i, int i1) {
//        DB.execSQL("drop Table if exists Userdetails");

    }
//
//    public Boolean insertuserdata(String name, String contact, String dob){
//        SQLiteDatabase DB = this.getWritableDatabase();
//        ContentValues contentValues = new ContentValues();
//        contentValues.put("name", name);
//        contentValues.put("contact", contact);
//        contentValues.put("dob", dob);
//        long result = DB.insert("Userdetails", null, contentValues);
//        if(result == -1){
//            return false;
//        }
//        else{
//            return true;
//        }
//
//    }

    //Add a new customer into Customer Table:
    public Boolean addNewCusotmer(String name, String phone){
        SQLiteDatabase DB = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("name", name);
        contentValues.put("phone", phone);
        long result = DB.insert("CUSTOMER", null, contentValues);
        if(result == -1){
            return false;
        }
        else{
            return true;
        }

    }

    //Add new Vehicle
    public Boolean addNewVehicle(String vid, String vdesc, String year, String type, String category){
        SQLiteDatabase DB = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("VehicleID", vid);
        contentValues.put("Description", vdesc);
        contentValues.put("Year", year);
        contentValues.put("Type", type);
        contentValues.put("Category", category);

        long result = DB.insert("vehicle", null, contentValues);
        if(result == -1){
            return false;
        }
        else{
            return true;
        }

    }

//
//    public Boolean updateuserdata(String name, String contact, String dob){
//        SQLiteDatabase DB = this.getWritableDatabase();
//        ContentValues contentValues = new ContentValues();
//        contentValues.put("name", name);
//        contentValues.put("contact", contact);
//        contentValues.put("dob", dob);
//
//        Cursor cursor = DB.rawQuery("Select * from Userdetails where name = ?", new String[]{name});
//        if(cursor.getCount() > 0) {
//            long result = DB.update("Userdetails", contentValues, "name=?", new String[]{name});
//            if (result == -1) {
//                return false;
//            } else {
//                return true;
//            }
//        }
//        else{
//            return false;
//        }
//
//    }
//
//    public Boolean deletedata(String name ){
//        SQLiteDatabase DB = this.getWritableDatabase();
//        Cursor cursor = DB.rawQuery("Select * from Userdetails where name = ?", new String[]{name});
//        if(cursor.getCount() > 0) {
//            long result = DB.delete("Userdetails", "name=?", new String[]{name});
//            if (result == -1) {
//                return false;
//            } else {
//                return true;
//            }
//        }
//        else{
//            return false;
//        }
//    }

//    public Cursor getdata(){
//        SQLiteDatabase DB = this.getWritableDatabase();
//        Cursor cursor = DB.rawQuery("Select * from Userdetails", null);
//        return cursor;
//    }

    public Cursor getCustomerdata(){
        SQLiteDatabase DB = this.getWritableDatabase();
        Cursor cursor = DB.rawQuery("Select * from CUSTOMER", null);
        return cursor;
    }
    public Cursor getVehicledata(){
        SQLiteDatabase DB = this.getWritableDatabase();
        Cursor cursor = DB.rawQuery("Select * from vehicle", null);
        return cursor;
    }
//        public Boolean updateuserdata(String name, String contact, String dob){
//        SQLiteDatabase DB = this.getWritableDatabase();
//        ContentValues contentValues = new ContentValues();
//        contentValues.put("name", name);
//        contentValues.put("contact", contact);
//        contentValues.put("dob", dob);
//
//        Cursor cursor = DB.rawQuery("Select * from Userdetails where name = ?", new String[]{name});
//        if(cursor.getCount() > 0) {
//            long result = DB.update("Userdetails", contentValues, "name=?", new String[]{name});
//            if (result == -1) {
//                return false;
//            } else {
//                return true;
//            }
//        }
//        else{
//            return false;
//        }
//
//    }
    //Requirement 3:
    public Cursor getVehicleRecords(String type, String category, String r_date){
        SQLiteDatabase DB = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("Type", type);
        contentValues.put("Category", category);
        contentValues.put("ReturnDate", r_date);
        System.out.println("Working till here");
        Cursor cursor = DB.rawQuery("select distinct v.vehicleid, v.Description " +
                "from vehicle as v left join Rental as r on v.vehicleid=r.vehicleid  " +
                "where v.Type=? and v.Category=? and r.ReturnDate <= ?;", new String[]{type,category,r_date});
        return cursor;
    }

    public Boolean bookVehicle(String customerName, String vehcileid, String s_date, String r_date, String type, String catgry) throws ParseException {
        SQLiteDatabase DB = this.getWritableDatabase();
        //get customer id from customer name
        Cursor cursor = DB.rawQuery("select CustID from customer where Name= ?", new String[]{customerName});
        if(cursor.getCount() == 0){
            return false;
        }
        String CustID = "";
        while(cursor.moveToNext())
        {
            CustID = cursor.getString(0);
        }
        System.out.println("Value of customer:"+CustID);
        //get rate to calculate total amount
        cursor = DB.rawQuery("select Daily from Rate where Type= ? and Category = ?", new String[]{type,catgry});
        String ratevalue = "";
        while(cursor.moveToNext())
        {
            ratevalue = cursor.getString(0);
        }
        System.out.println("Value of ratevalue:"+ratevalue);
        //get number of days:
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH);
        Date firstDate = sdf.parse(s_date);
        Date secondDate = sdf.parse(r_date);
        long diffInMillis = Math.abs(secondDate.getTime() - firstDate.getTime());
        long diff = TimeUnit.DAYS.convert(diffInMillis, TimeUnit.MILLISECONDS);
        String days = diff+"";
        System.out.println("Value of days:"+days);
        int Total = Integer.parseInt(ratevalue) * Integer.parseInt(days);
        System.out.println("Value of Total:"+Total);
        ContentValues contentValues = new ContentValues();
        contentValues.put("CustID", CustID);
        contentValues.put("VehicleID", vehcileid);
        contentValues.put("StartDate", s_date);
        contentValues.put("OrderDate", s_date);
        contentValues.put("RentalType", "1");
        contentValues.put("Qty", "1");
        contentValues.put("ReturnDate", r_date);
        contentValues.put("TotalAmount", Total);
        contentValues.put("PaymentDate", "NULL");
        contentValues.put("Returned", "0");
        long result = DB.insert("RENTAL", null, contentValues);
        if(result == -1){
            return false;
        }
        else{
            return true;
        }
    }

    //Payment Due: Requirement 4
    public Cursor paymentDue(String customerName, String vehcileid, String r_date) {
        SQLiteDatabase DB = this.getWritableDatabase();
        //get customer id from customer name
        Cursor cursor = DB.rawQuery("select CustID from customer where Name= ?", new String[]{customerName});
        if(cursor.getCount() == 0){
            return cursor;
        }
        String CustID = "";
        while(cursor.moveToNext())
        {
            CustID = cursor.getString(0);
        }
        System.out.println("Value of customer:"+CustID);

        //get rate to calculate total amount
        cursor = DB.rawQuery("select distinct TotalAmount from RENTAL where CustID=? and VehicleID=? and ReturnDate=?", new String[]{CustID,vehcileid,r_date});
        return cursor;
    }

    //Payment Due: Requirement 4
    @RequiresApi(api = Build.VERSION_CODES.O)
    public Boolean paymentSettle(String customerName, String vehcileid, String r_date) {
        SQLiteDatabase DB = this.getWritableDatabase();
        //get customer id from customer name
        Cursor cursor = DB.rawQuery("select CustID from customer where Name= ?", new String[]{customerName});
        if(cursor.getCount() == 0){
            return false;
        }
        String CustID = "";
        while(cursor.moveToNext())
        {
            CustID = cursor.getString(0);
        }
        System.out.println("Value of customer:"+CustID);
        //Get current date
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDateTime now = LocalDateTime.now();
        String c_date="";
        c_date = dtf.format(now);
        System.out.println(dtf.format(now));

        ContentValues contentValues = new ContentValues();
        contentValues.put("PaymentDate", c_date);
        contentValues.put("Returned", "1");

        cursor = DB.rawQuery("select distinct TotalAmount from RENTAL where CustID=? and VehicleID=? and ReturnDate=?", new String[]{CustID,vehcileid,r_date});

        if(cursor.getCount() > 0) {
            long result = DB.update("RENTAL", contentValues, "CustID=? and VehicleID=? and ReturnDate=?", new String[]{CustID,vehcileid,r_date});
            if (result == -1) {
                return false;
            } else {
                return true;
            }
        }
        else{
            return false;
        }
    }

    public Cursor getRentalRecords(){
        SQLiteDatabase DB = this.getWritableDatabase();
        Cursor cursor = DB.rawQuery("select VehicleID,ReturnDate,TotalAmount,PaymentDate,Returned from Rental",null);
        return cursor;
    }

    public Cursor getfilteredCustomer(String customerid,String name){
        SQLiteDatabase DB = this.getWritableDatabase();
        System.out.println("Value of name and customerid= "+name+" and "+customerid);
        customerid = customerid+"%";
        name = "%"+name+"%";
        String query = "select distinct c.CustID, c.name , r.TotalAmount from customer c left join rental r on c.CustID = r.CustID " +
                "where c.CustID LIKE '"+customerid+"' and c.name LIKE '"+name+"';";
        System.out.println(query);
        Cursor cursor = DB.rawQuery("select distinct c.CustID, c.name , r.TotalAmount, r.Returned from customer c left join rental r on c.CustID = r.CustID " +
                "where c.CustID LIKE ? and c.name LIKE ?;", new String[]{customerid,name});
        return cursor;
    }

    public Cursor getfilteredVehcile(String vin,String desc){
        SQLiteDatabase DB = this.getWritableDatabase();
        vin = vin+"%";
        desc = "%"+desc+"%";
        Cursor cursor = DB.rawQuery("select v.VehicleID, v.Description, avg(r.TotalAmount) from vehicle as v left join rental as r on v.VehicleID=r.VehicleID " +
                "where v.vehicleID LIKE ? and v.description LIKE ? group by v.vehicleID;", new String[]{vin,desc});
        return cursor;
    }


}
