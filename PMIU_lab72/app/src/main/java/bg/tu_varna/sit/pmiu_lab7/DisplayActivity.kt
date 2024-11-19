package bg.tu_varna.sit.pmiu_lab7

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Constraints
import androidx.compose.ui.unit.sp
import bg.tu_varna.sit.pmiu_lab7.data.Constants
import bg.tu_varna.sit.pmiu_lab7.data.Contact
import bg.tu_varna.sit.pmiu_lab7.ui.theme.PMIU_lab7Theme

class DisplayActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val contact = intent.getParcelableExtra(Constants.EXTRA_CONTACT, Contact::class.java)
        enableEdgeToEdge()
        setContent {
            PMIU_lab7Theme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    DisplayCompose(
                        name = contact!!.name,
                        email = contact.email,
                        phone = contact.phone,
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}

@Composable
fun DisplayCompose(name: String, email: String, phone: String, modifier: Modifier = Modifier) {
    Column(
        modifier = modifier.fillMaxSize().background(Color.LightGray),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    )  {
        CustomText("Hello $name!",
            Color.White,
            modifier = modifier)
        CustomText("your email $email",
            Color.Green,
            modifier = modifier)
        CustomText("your phone $phone",
            Color.Red,
            modifier = modifier)
    }
}

@Composable
fun CustomText(text: String, color: Color, modifier: Modifier = Modifier) {
    Text(
        text = text,
        color = color,
        fontSize = 50.sp,
        lineHeight = 60.sp,
        textAlign = TextAlign.Center,
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview3() {
    PMIU_lab7Theme {
        DisplayCompose("Android", "email@email.com", "0211680")
    }
}