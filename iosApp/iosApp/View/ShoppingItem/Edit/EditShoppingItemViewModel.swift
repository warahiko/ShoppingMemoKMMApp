//
//  EditShoppingItemViewModel.swift
//  iosApp
//
//  Created by 柴田 剛 on 2021/11/14.
//  Copyright © 2021 orgName. All rights reserved.
//

import shared
import Combine

extension EditShoppingItemScreen {
    class ViewModel: ObservableObject {
        let tagRepository = InjectorIos().tagRepository
        let tagIosRepository = InjectorIos().tagIosRepository
        let editShoppingItemUseCase = InjectorIos().editShoppingItemUseCase
        
        @Published var uiModel: UiModel = .EMPTY
        @Published var showProgress: Bool = false
        
        init() {
            tagIosRepository.tags.collect { tags in
                self.uiModel = .from(tags: tags as? [Tag])
            }
        }
        
        func editShoppingItem(_ shoppingItem: ShoppingItem, onComplete: @escaping () -> Void) {
            showProgress = true
            editShoppingItemUseCase.invoke(newShoppingItem: shoppingItem) { _, error in
                if error == nil {
                    onComplete()
                }
                self.showProgress = false
            }
        }
    }
    
    struct UiModel {
        let tagGroupedByType: Dictionary<String, [Tag]>

        static let EMPTY: UiModel = .init(tagGroupedByType: [:])

        static func from(tags: [Tag]?) -> UiModel {
            let tagGroupedByType = tags.orEmpty()
                .groupBy { tag in tag.type }

            return UiModel(tagGroupedByType: tagGroupedByType)
        }
    }
}
