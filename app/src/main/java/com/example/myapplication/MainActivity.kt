package com.example.myapplication

import android.os.Bundle
import android.os.Environment
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.myapplication.ui.theme.MyApplicationTheme
import java.io.File
import java.io.FileOutputStream
import java.io.OutputStreamWriter
import java.time.LocalDateTime

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyApplicationTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    SaveTextToFile("ejemplo.txt")
                }
            }
        }
    }

    @Composable
    fun SaveTextToFile(nombreArchivo: String) {
        val datetime = LocalDateTime.now().toString()

        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Button(
                onClick = { guardarTextoEnArchivo(datetime, nombreArchivo) }
            ) {
                Text("Guardar archivo")
            }
        }
    }

    private fun guardarTextoEnArchivo(texto: String, nombreArchivo: String) {
        val estadoAlmacenamiento = Environment.getExternalStorageState()

        if (estadoAlmacenamiento == Environment.MEDIA_MOUNTED) {
            val directorio = getFilesDir()
            val archivo = File(directorio, nombreArchivo)

            try {
                val flujoSalida = FileOutputStream(archivo, true)
                val writer = OutputStreamWriter(flujoSalida)
                writer.append(texto)
                writer.close()

                Toast.makeText(this, "Texto añadido en $directorio $nombreArchivo", Toast.LENGTH_SHORT).show()
            } catch (e: Exception) {
                e.printStackTrace()
                Toast.makeText(this, "Error al guardar el archivo", Toast.LENGTH_SHORT).show()
            }
        } else {
            Toast.makeText(this, "No se pudo acceder al almacenamiento externo", Toast.LENGTH_SHORT).show()
        }
    }
}


