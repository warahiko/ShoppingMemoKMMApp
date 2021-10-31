import SwiftUI
import shared

struct ContentView: View {
	var body: some View {
        TabView {
            ShoppingItemListScreen(viewModel: .init())
                .tabItem {
                    Image(systemName: "cart.fill")
                    Text("買い物リスト")
                }
            
            TagListScreen(viewModel: .init())
                .tabItem {
                    Image(systemName: "tag.fill")
                    Text("タグ")
                }
        }
	}
}
