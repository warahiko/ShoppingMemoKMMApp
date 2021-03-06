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
        let shoppingItemIosRepository = InjectorIos().shoppingItemIosRepository

        @Published var uiModel: UiModel = .EMPTY

        init() {
            fetchShoppingItems()
            shoppingItemIosRepository.shoppingItems.collect { shoppingItems in
                self.uiModel = UiModel.from(shoppingItems: shoppingItems as? [ShoppingItem])
            }
        }

        func fetchShoppingItems() {
            shoppingItemRepository.fetchShoppingItems { _, _ in }
        }
    }

    struct UiModel {
        let mainShoppingItems: Dictionary<String, [ShoppingItem]>
        let archivedShoppingItems: Dictionary<String, [ShoppingItem]>
        let deletedShoppingItems: [ShoppingItem]

        static let EMPTY: UiModel = .init(
            mainShoppingItems: [:],
            archivedShoppingItems: [:],
            deletedShoppingItems: []
        )

        static func from(shoppingItems: [ShoppingItem]?) -> UiModel {
            let mainShoppingItems = shoppingItems.orEmpty()
                .filter({ shoppingItem in
                    ShoppingItemListTab.main.statusList.contains { status in
                        status == shoppingItem.status
                    }
                })
                .groupBy { shoppingItem in
                    // かっこがあるとなぜかString? でなくString 扱いになる
                    (shoppingItem.tag?.type).orEmpty()
                }
            let archivedShoppingItems = shoppingItems.orEmpty()
                .filter({ shoppingItem in
                    ShoppingItemListTab.archived.statusList.contains { status in
                        status == shoppingItem.status
                    }
                })
                .groupBy { shoppingItem in
                    (shoppingItem.doneDate?.description()).orEmpty()
                }
            let deletedShoppingItems = shoppingItems.orEmpty()
                .filter({ shoppingItem in
                    ShoppingItemListTab.deleted.statusList.contains { status in
                        status == shoppingItem.status
                    }
                })

            return UiModel.init(
                mainShoppingItems: mainShoppingItems,
                archivedShoppingItems: archivedShoppingItems,
                deletedShoppingItems: deletedShoppingItems
            )
        }
    }
}
