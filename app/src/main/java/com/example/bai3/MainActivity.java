package com.example.bai3;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    DatabaseHandler db;
    ListView lv;
    ArrayAdapter<String> adapter;
    List<Contact> contacts;
    ArrayList<String> contactNames;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        lv = findViewById(R.id.lv_contacts);
        db = new DatabaseHandler(this);

        // Xóa dữ liệu cũ để tránh nhân đôi
        db.getWritableDatabase().execSQL("DELETE FROM contacts");

        // Thêm dữ liệu mẫu
        // Thêm dữ liệu mẫu
        db.addContact(new Contact("Ngô Thành Hữu", "1150070015"));
        db.addContact(new Contact("Srinivas", "1150070016"));
        db.addContact(new Contact("Tommy", "1150070017"));
        db.addContact(new Contact("Karthik", "1150070018"));
        db.addContact(new Contact("Lê Văn Minh", "0905123456"));
        db.addContact(new Contact("Trần Thị Thu Hà", "0912345678"));
        db.addContact(new Contact("Nguyễn Văn An", "0987654321"));
        db.addContact(new Contact("Đỗ Hoàng Long", "0977123123"));
        db.addContact(new Contact("Lý Hải Yến", "0933222111"));



        // Hiển thị dữ liệu ban đầu
        loadContacts();

        // Long click để xóa contact
        lv.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                Contact selectedContact = contacts.get(position);

                // Thông báo trước khi xóa
                Toast.makeText(MainActivity.this,
                        "Xóa: " + selectedContact.getName(),
                        Toast.LENGTH_SHORT).show();

                // Xóa contact trong DB
                db.deleteContact(selectedContact.getID());

                // Log ra Logcat
                Log.e("Delete", "Đã xóa contact: " + selectedContact);

                // Load lại danh sách để cập nhật giao diện
                loadContacts();

                return true; // bắt buộc return true để sự kiện long click được xử lý
            }
        });
    }

    private void loadContacts() {
        // Lấy toàn bộ contact từ DB
        contacts = db.getAllContacts();
        contactNames = new ArrayList<>();

        for (Contact c : contacts) {
            // In ra Logcat với toString()
            Log.e("Contact", c.toString());

            // Hiển thị ID - Name - Phone trong ListView
            contactNames.add("ID: " + c.getID()
                    + " - " + c.getName()
                    + " - " + c.getPhoneNumber());
        }

        // Đưa dữ liệu vào ListView
        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, contactNames);
        lv.setAdapter(adapter);
    }
}
