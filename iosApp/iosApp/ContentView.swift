import SwiftUI
import shared

struct ContentView: View {
	let greet = Greeting().greeting()
    let repository = InjectorIos().shoppingItemListRepository

	var body: some View {
        Text(greet + repository.fetchShoppingList().joined())
	}
}

struct ContentView_Previews: PreviewProvider {
	static var previews: some View {
		ContentView()
	}
}
