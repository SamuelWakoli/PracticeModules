package com.samwrotethecode.practicemodules

import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
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
import androidx.compose.runtime.key
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.samwrotethecode.practicemodules.core.model.Person
import com.samwrotethecode.practicemodules.core.model.currentPerson
import com.samwrotethecode.practicemodules.core.model.dummyPeople

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FavoritesScreen(modifier: Modifier = Modifier) {
    var selectedTabIndex by remember { mutableIntStateOf(0) }
    val tabs = listOf("Favorites", "Discover")
    val context = LocalContext.current

    // This state will be used to trigger recomposition when the favorites list changes
    var favoritesVersion by remember { mutableIntStateOf(0) }

    // Function to call to update the version and trigger recomposition
    val refreshFavorites = { favoritesVersion++ }

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

            // By keying on favoritesVersion, we ensure these sections recompose when it changes
            key(favoritesVersion) {
                when (selectedTabIndex) {
                    0 -> FavoritesTabContent(
                        currentPerson = currentPerson,
                        onDislikeClicked = { personToDislike ->
                            currentPerson dislikes personToDislike
                            Toast.makeText(context, "${personToDislike.name} disliked!", Toast.LENGTH_SHORT).show()
                            refreshFavorites()
                        }
                    )
                    1 -> DiscoverTabContent(
                        allPeople = dummyPeople.filter { it.email != currentPerson.email }, // Exclude currentPerson
                        currentPerson = currentPerson,
                        onToggleFavorite = { personToToggle ->
                            if (currentPerson.favoritePersons.any { it.email == personToToggle.email }) {
                                currentPerson dislikes personToToggle
                                Toast.makeText(context, "${personToToggle.name} disliked!", Toast.LENGTH_SHORT).show()
                            } else {
                                currentPerson likes personToToggle
                                Toast.makeText(context, "${personToToggle.name} liked!", Toast.LENGTH_SHORT).show()
                            }
                            refreshFavorites()
                        }
                    )
                }
            }
        }
    }
}

@Composable
fun CurrentPersonDetailsCard(person: Person, modifier: Modifier = Modifier) {
    Card(modifier = modifier.fillMaxWidth().padding(horizontal = 16.dp, vertical = 8.dp)) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text("Current User", style = MaterialTheme.typography.headlineSmall)
            Spacer(Modifier.height(8.dp))
            Text(text = "Name: ${person.name}", style = MaterialTheme.typography.titleMedium)
            Text(text = "Email: ${person.email}", style = MaterialTheme.typography.bodyMedium)
            Text(text = "Age: ${person.age}", style = MaterialTheme.typography.bodyMedium)
            Text(text = "Address: ${person.address}", style = MaterialTheme.typography.bodyMedium)
        }
    }
}

@Composable
fun FavoritesTabContent(
    currentPerson: Person,
    onDislikeClicked: (Person) -> Unit,
    modifier: Modifier = Modifier,
) {
    Column(modifier = modifier.fillMaxSize()) {
        CurrentPersonDetailsCard(person = currentPerson)
        Text(
            text = "My Favorite People",
            style = MaterialTheme.typography.titleLarge,
            modifier = Modifier.padding(start = 16.dp, end = 16.dp, top = 16.dp, bottom = 12.dp) // Adjusted padding
        )
        if (currentPerson.favoritePersons.isEmpty()) {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(16.dp),
                contentAlignment = Alignment.Center
            ) {
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center
                ) {
                    Icon(
                        imageVector = Icons.Filled.FavoriteBorder,
                        contentDescription = "No favorites icon",
                        modifier = Modifier.size(64.dp),
                        tint = MaterialTheme.colorScheme.primary
                    )
                    Spacer(modifier = Modifier.height(16.dp))
                    Text(
                        text = "No favorites yet!",
                        style = MaterialTheme.typography.titleMedium,
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    Text(
                        text = "Discover people and tap the heart icon to add them to your favorites.",
                        style = MaterialTheme.typography.bodyMedium,
                        textAlign = TextAlign.Center,
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                }
            }
        } else {
            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(horizontal = 16.dp)
            ) {
                items(items = currentPerson.favoritePersons, key = { it.email }) { person ->
                    PersonCard(
                        person = person,
                        isFavorite = true, // It's always true in this list
                        onLikeClicked = { onDislikeClicked(person) } // Action here is to dislike
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                }
            }
        }
    }
}

@Composable
fun DiscoverTabContent(
    allPeople: List<Person>,
    currentPerson: Person,
    onToggleFavorite: (Person) -> Unit,
    modifier: Modifier = Modifier,
) {
    LazyColumn(
        modifier = modifier
            .fillMaxSize()
            .padding(horizontal = 16.dp, vertical = 8.dp)
    ) {
        items(items = allPeople,  key = { it.email }) { person ->
            val isCurrentlyFavorite = currentPerson.favoritePersons.any { it.email == person.email }
            PersonCard(
                person = person,
                isFavorite = isCurrentlyFavorite,
                onLikeClicked = { onToggleFavorite(person) }
            )
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
                    contentDescription = if (isFavorite) "Dislike ${person.name}" else "Like ${person.name}",
                    tint = if (isFavorite) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.onSurfaceVariant
                )
            }
        }
    }
}

