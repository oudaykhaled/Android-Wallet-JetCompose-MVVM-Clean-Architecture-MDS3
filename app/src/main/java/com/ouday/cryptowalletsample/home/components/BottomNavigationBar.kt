import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Wallet
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.ouday.cryptowalletsample.R
import com.ouday.cryptowalletsample.ui.theme.*

@Composable
fun BottomNavigationBar(
    items: List<BottomNavItem>,
    onItemSelected: (BottomNavItem) -> Unit
) {
    Box(
        modifier = Modifier
            .padding(
            start = Space.spaceLarge,
            end = Space.spaceLarge,
            bottom = Space.spaceXLarge,
            top = Space.spaceSmall
        )
    ) {
        NavigationBar(
            modifier = Modifier
                .fillMaxWidth()
                .height(Space.space2XLarge)
                .background(
                    MaterialTheme.colorScheme.primary.copy(alpha = 0.2f),
                    RoundedCornerShape(MaterialCornerRadius.radiusMedium)
                ),
            containerColor = Color.Transparent
        ) {
            var selectedItem by remember { mutableStateOf<BottomNavItem>(BottomNavItem.Home) }

            items.forEachIndexed { index, item ->
                NavigationBarItem(
                    icon = {
                        Icon(
                            imageVector = item.icon,
                            contentDescription = stringResource(id = item.labelRes)
                        )
                    },

                    selected = selectedItem == item,
                    onClick = {
                        selectedItem = item
                        onItemSelected(item)
                    },
                    colors = NavigationBarItemDefaults.colors(
                        indicatorColor = MaterialTheme.colorScheme.primary,
                        unselectedIconColor = MaterialTheme.colorScheme.onSurface,
                        unselectedTextColor = MaterialTheme.colorScheme.onSurface,
                        selectedIconColor = MaterialTheme.colorScheme.onSecondaryContainer,
                        selectedTextColor = MaterialTheme.colorScheme.secondary,
                    ),
                    alwaysShowLabel = true
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewBottomNavigationBar() {
    // Sample items for the bottom navigation bar
    val items = listOf(
        BottomNavItem.Home,
        BottomNavItem.Search,
        BottomNavItem.Wallet,
        BottomNavItem.Profile
    )

    BottomNavigationBar(
        items = items
    )
    // This can be dynamic based on the current route in your actual app
    { /* Handle item selection */ }
}


sealed class BottomNavItem(val route: String, val icon: ImageVector, val labelRes: Int) {
    object Home : BottomNavItem("home", Icons.Filled.Home, R.string.nav_home)
    object Search : BottomNavItem("search", Icons.Filled.Search, R.string.nav_search)
    object Wallet : BottomNavItem("wallet", Icons.Filled.Wallet, R.string.nav_wallet)
    object Profile : BottomNavItem("profile", Icons.Filled.Person, R.string.nav_profile)
}