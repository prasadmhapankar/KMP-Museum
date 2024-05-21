//
//  ListView.swift
//  iosApp
//
//  Created by Prasad Mhapankar on 20/05/2024.
//  Copyright © 2024 orgName. All rights reserved.
//
import Foundation
import SwiftUI
import Shared

@available(iOS 16.0, *)
struct ListView: View {
    
    @State private var viewModel : ListViewModel? = nil

    @State private var museumListState: MuseumListState?
    
    let columns = [
        GridItem(.adaptive(minimum: 120), alignment: .top)
    ]

    var body: some View {
        ZStack {
            if(museumListState != nil){
                NavigationStack {
                    ScrollView {
                        LazyVGrid(columns: columns, alignment: .leading, spacing: 20) {
                            if(museumListState?.list != nil){
                                ForEach(museumListState?.list ?? [MuseumObject](), id: \.self) { item in
                                    NavigationLink(destination: DetailView(objectId: item.objectID)) {
                                        ObjectFrame(obj: item, onClick: {})
                                    }
                                    .buttonStyle(PlainButtonStyle())
                                }
                            }
                        }
                        .padding(.horizontal)
                    }
                }
            } else {
                Text("No data available")
            }
        }.task {
            let viewModel = SharedModuleDependencies.shared.listViewModel
            await withTaskCancellationHandler(
                            operation: {
                                self.viewModel = viewModel
                                self.viewModel!.getMuseumData()
                            },
                            onCancel: {
                                viewModel.onCleared()
                                DispatchQueue.main.async {
                                    self.viewModel = nil
                                }
                            }
                        )
            
        }
        .task{
            if !viewModel.featureListState.isEmpty{
                
            }
            
            if(viewModel?.featureListState != nil){
                self.museumListState = viewModel?.featureListState.value
            }
        }
    }

}

struct ObjectFrame: View {
    let obj: MuseumObject
    let onClick: () -> Void

    var body: some View {
        VStack(alignment: .leading, spacing: 4) {
            GeometryReader { geometry in
                AsyncImage(url: URL(string: obj.primaryImageSmall)) { phase in
                    switch phase {
                    case .empty:
                        ProgressView()
                            .frame(width: geometry.size.width, height: geometry.size.width)
                    case .success(let image):
                        image
                            .resizable()
                            .scaledToFill()
                            .frame(width: geometry.size.width, height: geometry.size.width)
                            .clipped()
                            .aspectRatio(1, contentMode: .fill)
                    default:
                        EmptyView()
                            .frame(width: geometry.size.width, height: geometry.size.width)
                    }
                }
            }
            .aspectRatio(1, contentMode: .fit)

            Text(obj.title)
                .font(.headline)

            Text(obj.artistDisplayName)
                .font(.subheadline)

            Text(obj.objectDate)
                .font(.caption)
        }
    }
}
