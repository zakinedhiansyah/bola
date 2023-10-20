package utdi.zaki.nedhiansyah.bola

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import utdi.zaki.nedhiansyah.bola.data.DataSource
import utdi.zaki.nedhiansyah.bola.model.Topic
import utdi.zakinedhiansyah.bola.R
import utdi.zakinedhiansyah.bola.ui.theme.BolaTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            BolaTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    TopicGrid(
                        modifier = Modifier.padding(dimensionResource(R.dimen.padding_small))
                    )
                }
            }
        }
    }
}

@Composable
fun TopicGrid(modifier: Modifier = Modifier) {
    LazyVerticalGrid(
        columns = GridCells.Fixed(1), // count untuk menentukan mau berapa banyak gambar ditampilkan
        verticalArrangement = Arrangement.spacedBy(dimensionResource(R.dimen.padding_small)),// untuk menentukan jarak antar gambar
        horizontalArrangement = Arrangement.spacedBy(dimensionResource(R.dimen.padding_small)), // untuk menentukan jarak antar gambar
        modifier = modifier
    ) {
        items(DataSource.topics) { topic ->
            TopicCard(topic) // menampilkan beberapa objek ke topicCard
        }
    }
}

@Composable
fun TopicCard(topic: Topic, modifier: Modifier = Modifier) {
    Card {
        Row {
            Box {
                Image(
                    painter = painterResource(id = topic.imageRes),
                    contentDescription = null,
                    modifier = modifier
                        .size(width = 100.dp, height = 100.dp) //untuk menentukan ukuran pada gambar
                        .aspectRatio(1f) // untuk menentukan ukuran rasio pada gambar
                )
            }

            Column {
                Text(
                    text = stringResource(id = topic.name), //mengambil teks dari @StringRes val name pada class topic
                    style = MaterialTheme.typography.bodyLarge, //style untuk text
                    modifier = Modifier.padding(
                        start = dimensionResource(R.dimen.padding_small),
                        top = dimensionResource(R.dimen.padding_medium)
                    )
                )
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Text(
                        text = topic.availableBola.toString(),
                        style = MaterialTheme.typography.labelLarge,
                        modifier = Modifier.padding(
                            start = dimensionResource(R.dimen.padding_small),
                            top = dimensionResource(R.dimen.padding_small))
                    )
                }
            }
        }
    }
}
@Preview(showBackground = true)
@Composable
fun AllTopicsPreview() {
    BolaTheme {
        LazyVerticalGrid(
            columns = GridCells.Fixed(1), // Sesuaikan dengan jumlah kolom yang diinginkan
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.spacedBy(dimensionResource(R.dimen.padding_small)),
            horizontalArrangement = Arrangement.spacedBy(dimensionResource(R.dimen.padding_small)),
        ) {
            items(DataSource.topics) { topic ->
                TopicCard(topic = topic)
            }
        }
    }
}
