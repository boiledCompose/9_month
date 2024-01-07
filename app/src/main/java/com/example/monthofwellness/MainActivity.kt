package com.example.monthofwellness

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.spring
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.monthofwellness.model.Wellness
import com.example.monthofwellness.model.WellnessRepository.wellnessList
import com.example.monthofwellness.ui.theme.MonthOfWellnessTheme


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            MonthOfWellnessTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    WellnessApp()
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun WellnessApp(modifier: Modifier = Modifier) {
    Scaffold(
        topBar = {
            Text(
                text = stringResource(id = R.string.wellness_top_app_bar),
                style = MaterialTheme.typography.titleLarge,
                modifier = modifier.background(MaterialTheme.colorScheme.primary)
                    .fillMaxWidth()
                    .padding(vertical = 16.dp)
            )
        }
    ){
        WellnessCardList(modifier = modifier.padding(it))
    }
}

@Composable
fun WellnessCardList(modifier: Modifier = Modifier){
    LazyColumn(
        modifier = modifier
    ){
        items(wellnessList){ wellness->
            WellnessCard(
                wellness = wellness,
                modifier = Modifier,

            )
        }
    }
}

@Composable
fun WellnessCard(wellness: Wellness, modifier: Modifier = Modifier){
    var expanded by remember{ mutableStateOf(false) }

    Card(
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp),
        modifier = modifier.padding(horizontal = 16.dp, vertical = 8.dp)
    ){
        Column(
            modifier = Modifier.padding(8.dp).animateContentSize(
                animationSpec = spring(
                    dampingRatio = Spring.DampingRatioHighBouncy,
                    stiffness = Spring.StiffnessMedium
                )
            )
        ){
            Text(
                text = stringResource(id = R.string.wellness_day, wellness.index),
                style = MaterialTheme.typography.bodyLarge
            )

            Text(
                text = stringResource(id = wellness.titleStringRes),
                style = MaterialTheme.typography.bodyMedium
            )


            Image(
                painter = painterResource(id = wellness.imageRes),
                contentDescription = null,
                modifier = Modifier.padding(bottom = 8.dp).clickable {
                    expanded = !expanded
                }
            )



            if(expanded) {
                Text(
                    text = stringResource(id = wellness.bodyStringRes),
                    style = MaterialTheme.typography.bodySmall
                )
            }
        }
    }
}


@Preview(showBackground = true, showSystemUi = true)
@Composable
fun GreetingPreview() {
    MonthOfWellnessTheme {
        WellnessApp()
    }
}