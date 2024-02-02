package com.alberto.contactosbasesdedatos

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast

class MainActivity : AppCompatActivity() {

    private lateinit var nameEditText: EditText
    private lateinit var emailEditText: EditText
    private lateinit var saveButton: Button
    private lateinit var consultaButton: Button
    private lateinit var consultaTextView: TextView
    private lateinit var db: Databasehandler

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        nameEditText = findViewById(R.id.nameEditText)
        emailEditText = findViewById(R.id.emailEditText)
        saveButton = findViewById(R.id.saveButton)
        consultaButton = findViewById(R.id.consultaButton)
        consultaTextView = findViewById(R.id.consultaTextView)

        db = Databasehandler(this)

        saveButton.setOnClickListener {
            val name = nameEditText.text.toString().trim()
            val email = emailEditText.text.toString().trim()

            if (name.isNotEmpty() && email.isNotEmpty()) {
                val id = db.addContact(name, email)
                if (id != -1L) {
                    //error al guardar en la base de datos
                    Toast.makeText(
                        applicationContext,
                        "Campos guardados",
                        Toast.LENGTH_SHORT
                    ).show()
                    nameEditText.text.clear()
                    emailEditText.text.clear()

                } else {
                    //toast para avisar que se ha guardado el registro en la base de datos
                }

            } else {
                Toast.makeText(
                    applicationContext,
                    "Te falta algún campo por rellenar",
                    Toast.LENGTH_SHORT
                ).show()
            }
        }
        consultaButton.setOnClickListener{

            val contactList = db.getAllContacts()
            for(contact in contactList){
                Log.d("Contacto ", "ID: ${contact.id}, Nombre: ${contact.name}, Email: ${contact.email}")
                consultaTextView.append("ID: ${contact.id} ,Nombre: ${contact.name}, Email: ${contact.email} \n ")
            }
        }
    }
}
