package com.samwrotethecode.practicemodules

import android.widget.Toast
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.PrimaryTabRow
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Tab
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import com.samwrotethecode.practicemodules.core.model.Person
import com.samwrotethecode.practicemodules.core.model.dummyPeople

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FavoritesScreen(modifier: Modifier = Modifier) {
    var selectedTabIndex by remember { mutableIntStateOf(0) }
    val tabs = listOf("Favorites", "Discover")

    // Dummy list of favorite people for demonstration
    val favoritePeople = remember {
        mutableStateListOf(
            dummyPeople[0],
            dummyPeople[2],
            dummyPeople[4]
        )
    }
    val context = LocalContext.current

    Scaffold(modifier = modifier) { innerPadding ->
        Column(
            Modifier
                .padding(innerPadding)
                .fillMaxSize()
        ) {
            PrimaryTabRow(selectedTabIndex = selectedTabIndex) {
                tabs.forEachIndexed { index, title ->
                    Tab(
                        selected = selectedTabIndex == index,
                        onClick = { selectedTabIndex = index },
                        text = { Text(title) }
                    )
                }
            }
            when (selectedTabIndex) {
                0 -> FavoritesList(people = favoritePeople, onLikeClicked = {
                    if (favoritePeople.contains(it)) {
                        favoritePeople.remove(it)
                        Toast.makeText(context, "${it.name} disliked!", Toast.LENGTH_SHORT).show()
                    } else {
                        favoritePeople.add(it)
                        Toast.makeText(context, "${it.name} liked!", Toast.LENGTH_SHORT).show()
                    }
                })

                1 -> DiscoverList(people = dummyPeople, favoritePeople = favoritePeople, onLikeClicked = {
                    if (favoritePeople.contains(it)) {
                        favoritePeople.remove(it)
                        Toast.makeText(context, "${it.name} disliked!", Toast.LENGTH_SHORT).show()
                    } else {
                        favoritePeople.add(it)
                        Toast.makeText(context, "${it.name} liked!", Toast.LENGTH_SHORT).show()
                    }
                })
            }
        }
    }
}

@Composable
fun FavoritesList(people: List<Person>, onLikeClicked: (Person) -> Unit) {
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 16.dp)
    ) {
        items(people) {
            PersonCard(person = it, isFavorite = true, onLikeClicked = { onLikeClicked(it) })
            Spacer(modifier = Modifier.height(8.dp))
        }
    }
}

@Composable
fun DiscoverList(people: List<Person>, favoritePeople: List<Person>, onLikeClicked: (Person) -> Unit) {
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 16.dp)
    ) {
        items(people) {
            PersonCard(person = it, isFavorite = favoritePeople.contains(it), onLikeClicked = { onLikeClicked(it) })
            Spacer(modifier = Modifier.height(8.dp))
        }
    }
}


@Composable
fun PersonCard(person: Person, isFavorite: Boolean, onLikeClicked: () -> Unit) {
    Card(
        modifier = Modifier.fillMaxWidth(),
    ) {
        Row(
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column(modifier = Modifier.weight(1f)) {
                Text(text = person.name, style = MaterialTheme.typography.titleMedium)
                Text(text = person.email, style = MaterialTheme.typography.bodySmall)
                Text(text = "Age: ${person.age}", style = MaterialTheme.typography.bodySmall)
                Text(text = person.address, style = MaterialTheme.typography.bodySmall)
            }
            IconButton(onClick = onLikeClicked) {
                Icon(
                    imageVector = if (isFavorite) Icons.Filled.Favorite else Icons.Filled.FavoriteBorder,
                    contentDescription = "Like",
                    tint = if (isFavorite) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.onSurfaceVariant
                )
            }
        }
    }
}
