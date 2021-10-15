import SwiftUI
import shared

struct ContentView: View {
	let greet = Greeting().greeting()
    let repository = InjectorIos().shoppingItemListRepository
    
    @State var text: String = ""

	var body: some View {
        Text(text)
            .onAppear {
                text = greet
                repository.fetchShoppingList { (shoppingItems: [ShoppingItem]?, error: Error?) in
                    if let unwraped = shoppingItems {
                        let itemDescriptions = unwraped.reduce("") { (left, right) -> String in
                            left + right.description()
                        }
                        text = itemDescriptions
                    }
                }
            }
	}
}

struct ContentView_Previews: PreviewProvider {
	static var previews: some View {
		ContentView()
	}
}
