import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Wallet
import androidx.compose.material3.Icon
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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.ouday.cryptowalletsample.ui.theme.Colors

@Composable
fun BottomNavigationBar(
    items: List<BottomNavItem>,
    currentRoute: String,
    onItemSelected: (BottomNavItem) -> Unit
) {
    Box(modifier = Modifier.padding(start = 16.dp, end = 16.dp, bottom = 24.dp, top = 8.dp)) {
        NavigationBar(
            modifier = Modifier
                .fillMaxWidth()
                .height(50.dp)
                .background(Colors.surface, RoundedCornerShape(30.dp)),
            containerColor = Color.Transparent
        ) {
            var selectedItem by remember { mutableStateOf<BottomNavItem>(BottomNavItem.Home) }

            items.forEachIndexed { index, item ->
                NavigationBarItem(
                    icon = {
                        Icon(
                            imageVector = item.icon,
                            contentDescription = item.label
                        )
                    },

                    selected = selectedItem == item,
                    onClick = {
                        selectedItem = item
                        onItemSelected(item)
                    },
                    colors = NavigationBarItemDefaults.colors(
                        indicatorColor = Color(0xFFFF0000),
                        unselectedIconColor = Color(0xFF10720D),
                        unselectedTextColor = Color(0xFF1870DD),
                        selectedIconColor = Color(0xFF14DA7C),
                        selectedTextColor = Color(0xFFD6DD18),
                    ),
                    alwaysShowLabel = true
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun BottomNavigationBarPreview() {
    BottomNavigationBar(
        listOf(BottomNavItem.Home),
        "home",
        {}
    )
}

sealed class BottomNavItem(val route: String, val icon: ImageVector, val label: String) {
    object Home : BottomNavItem("home", Icons.Filled.Home, "Home")
    object Search : BottomNavItem("Search", Icons.Filled.Search, "Search")
    object Wallet : BottomNavItem("Wallet", Icons.Filled.Wallet, "Wallet")
    object Profile : BottomNavItem("Profile", Icons.Filled.Person, "Profile")
}
