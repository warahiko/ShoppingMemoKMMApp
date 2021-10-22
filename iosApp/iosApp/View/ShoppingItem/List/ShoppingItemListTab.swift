//
//  ShoppingItemListTab.swift
//  iosApp
//
//  Created by 柴田 剛 on 2021/10/21.
//  Copyright © 2021 orgName. All rights reserved.
//

import shared

// CaseIterable プロトコルに準拠させることで allCases プロパティが生える
enum ShoppingItemListTab: Int, CaseIterable, Identifiable {
    case main
    case archived
    case deleted
    
    var id: Int {
        return self.rawValue
    }
    
    var title: String {
        switch self {
        case .main:
            return "Main"
        case .archived:
            return "Archived"
        case .deleted:
            return "Deleted"
        }
    }
    
    var statusList: [Status] {
        switch self {
        case .main:
            return [Status.theNew, Status.done]
        case .archived:
            return [Status.archived]
        case .deleted:
            return [Status.deleted]
        }
    }
}
