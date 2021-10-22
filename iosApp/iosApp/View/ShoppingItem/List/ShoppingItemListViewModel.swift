//
//  ShoppingItemListViewModel.swift
//  iosApp
//
//  Created by 柴田 剛 on 2021/10/21.
//  Copyright © 2021 orgName. All rights reserved.
//

import shared

extension ShoppingItemListScreen {
    class ViewModel: ObservableObject {
        let shoppingItemRepository = InjectorIos().shoppingItemRepository
        
        @Published var shoppingItems: [ShoppingItem] = []
        
        init() {
            fetchShoppingItems()
        }
        
        func fetchShoppingItems() {
            InjectorIos().shoppingItemRepository.fetchShoppingItems { shoppingItems, error in
                if let shoppingItems = shoppingItems {
                    self.shoppingItems = shoppingItems
                }
            }
        }
    }
}
