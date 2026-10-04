package dario.cortez.pokedex

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.Guideline
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        val guidelineBottom = findViewById<Guideline>(R.id.guidelineBottom)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            // Sin padding inferior para que la tarjeta blanca llegue al borde de la pantalla
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, 0)
            guidelineBottom.setGuidelineEnd(systemBars.bottom)
            insets
        }
    }
}