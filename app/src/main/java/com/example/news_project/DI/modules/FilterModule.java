package com.example.news_project.DI.modules;


import com.example.news_project.data.NewsDatabase;
import com.example.news_project.data.filter.FilterRepositoryImpl;
import com.example.news_project.data.mapper.DataFilterToDomainMapper;
import com.example.news_project.data.mapper.DomainFilterToDataMapper;
import com.example.news_project.domain.IFilterRepository;
import com.example.news_project.domain.use_cases.filter.AddFilterUseCase;
import com.example.news_project.domain.use_cases.filter.DeleteFilterUseCase;
import com.example.news_project.domain.use_cases.filter.GetFiltersUseCase;
import com.example.news_project.domain.use_cases.filter.UpdateFilterUseCase;

import dagger.Binds;
import dagger.Module;
import dagger.Provides;
import dagger.Reusable;

@Module
public abstract class FilterModule {

//    @Provides
//    public GetFiltersUseCase provideGetFiltersUseCase(IFilterRepository repository) {
//        return new GetFiltersUseCase(repository);
//    }
//
//    @Provides
//    public UpdateFilterUseCase provideUpdateFilterUseCase(IFilterRepository repository) {
//        return new UpdateFilterUseCase(repository);
//    }
//
//    @Provides
//    public DeleteFilterUseCase provideDeleteFilterUseCase(IFilterRepository repository) {
//        return new DeleteFilterUseCase(repository);
//    }
//
//    @Provides
//    public AddFilterUseCase provideAddFilterUseCase(IFilterRepository repository) {
//        return new AddFilterUseCase(repository);
//    }

    @Binds
    @Reusable
    public abstract IFilterRepository provideFilterRepository(FilterRepositoryImpl impl);

}
