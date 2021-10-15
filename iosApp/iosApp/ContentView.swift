import SwiftUI
import shared

struct ContentView: View {
	let greet = Greeting().greeting()
    let repository = InjectorIos().shoppingItemListRepository
    let tagRepository = InjectorIos().tagListRepository
    
    @State var text: String = ""

	var body: some View {
        Text(text)
            .onAppear {
                text = greet
                repository.fetchShoppingList { (shoppingItems: [ShoppingItem]?, error: Error?) in
                    if let unwraped = shoppingItems {
                        let itemDescriptions = unwraped.prefix(2).reduce("") { (left, right) -> String in
                            left + right.description()
                        }
                        text = text + itemDescriptions
                    }
                }
                tagRepository.fetchTagList { (tags: [Tag]?, error: Error?) in
                    if let unwraped = tags {
                        let tagDescriptions = unwraped.prefix(2).reduce("") { (left, right) -> String in
                            left + right.description()
                        }
                        text = text + tagDescriptions
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
