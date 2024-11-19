package bg.tu_varna.sit.pmiu_lab7

import android.content.Intent
import android.os.Bundle
import android.util.Patterns
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import bg.tu_varna.sit.pmiu_lab7.data.Constants
import bg.tu_varna.sit.pmiu_lab7.data.Contact
import bg.tu_varna.sit.pmiu_lab7.ui.theme.PMIU_lab7Theme
import java.lang.Error

class InsertActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val name = intent.getStringExtra(Constants.EXTRA_NAME)
        enableEdgeToEdge()
        setContent {
            PMIU_lab7Theme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    InsertCompose(
                        name = name!!,
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}

@Composable
fun InsertCompose(name: String, modifier: Modifier = Modifier) {
    val context = LocalContext.current
    val intent = Intent(context, DisplayActivity::class.java)
    var email by remember { mutableStateOf("") }
    var phone by remember { mutableStateOf("") }
    var isValidEmail by remember { mutableStateOf(true) }
    var isValidPhone by remember { mutableStateOf(true) }
    Column(
        modifier = modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Hello $name!",
            modifier = modifier
        )
        if (!(isValidEmail && isValidPhone)) {
            Text(
                text = stringResource(R.string.error_message),
                modifier = modifier
            )
        }
//        TextField(
//            modifier = modifier,
//            value = email,
//            onValueChange = { email = it },
//            placeholder = {
//                Text(stringResource(R.string.enter_email))
//            },
//            keyboardOptions = KeyboardOptions(
//                keyboardType = KeyboardType.Email
//            )
//        )
//        TextField(
//            modifier = modifier,
//            value = phone,
//            onValueChange = { phone = it },
//            placeholder = {
//                Text(stringResource(R.string.enter_phone))
//            },
//            keyboardOptions = KeyboardOptions(
//                keyboardType = KeyboardType.Phone
//            )
//        )
        CustomTextField(email, stringResource(R.string.enter_email), KeyboardType.Email, !isValidEmail, modifier) {
            email = it
        }
        CustomTextField(phone, stringResource(R.string.enter_phone), KeyboardType.Phone, !isValidEmail, modifier) {
            phone = it
        }
        Button(
            modifier = modifier.fillMaxWidth(),
            onClick = {
                isValidEmail = Patterns.EMAIL_ADDRESS.matcher(email).matches()
                isValidPhone = Patterns.PHONE.matcher(phone).matches()
                if (isValidEmail && isValidPhone) {
                    intent.putExtra(Constants.EXTRA_CONTACT, Contact(name, email, phone))
                    context.startActivity(intent)
                } else {
                    email = ""; phone = ""
                }
            }) {
            Text(stringResource(R.string.next))
        }
    }
}

@Composable
fun CustomTextField(value: String,
                    placeholder: String,
                    keyboardType: KeyboardType,
                    isError: Boolean,
                    modifier: Modifier = Modifier,
                    onValueChange: (String) -> Unit) {
    TextField(
        modifier = modifier,
        value = value,
        onValueChange = onValueChange,
        placeholder = {
            Text(placeholder)
        },
        isError = isError,
        keyboardOptions = KeyboardOptions(
            keyboardType = keyboardType
        )
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview2() {
    PMIU_lab7Theme {
        InsertCompose("Android")
    }
}