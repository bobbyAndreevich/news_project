package com.example.news_project.DI.modules;


import com.example.news_project.data.filter.FilterRepositoryImpl;
import com.example.news_project.data.news.NewsRepositoryImpl;
import com.example.news_project.domain.use_cases.filter.AddFilterUseCase;
import com.example.news_project.domain.use_cases.filter.DeleteFilterUseCase;
import com.example.news_project.domain.use_cases.filter.GetFiltersUseCase;
import com.example.news_project.domain.use_cases.filter.UpdateFilterUseCase;
import com.example.news_project.domain.use_cases.news.GetNewsUseCase;

import dagger.Module;
import dagger.Provides;

@Module
public class UseCasesModule {

    @Provides
    AddFilterUseCase provideAddFilterUseCase(FilterRepositoryImpl filterRepository) {
        return new AddFilterUseCase(filterRepository);
    }

    @Provides
    UpdateFilterUseCase provideUpdateFilterUseCase(FilterRepositoryImpl filterRepository) {
        return new UpdateFilterUseCase(filterRepository);
    }

    @Provides
    DeleteFilterUseCase provideDeleteFilterUseCase(FilterRepositoryImpl filterRepository) {
        return new DeleteFilterUseCase(filterRepository);
    }

    @Provides
    GetFiltersUseCase provideGetFiltersUseCase(FilterRepositoryImpl filterRepository) {
        return new GetFiltersUseCase(filterRepository);
    }

    @Provides
    GetNewsUseCase provideGerNewsFilterUseCase(NewsRepositoryImpl newsRepository) {
        return new GetNewsUseCase(newsRepository);
    }

}
