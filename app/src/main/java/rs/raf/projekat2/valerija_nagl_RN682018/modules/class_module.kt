package rs.raf.projekat2.valerija_nagl_RN682018.modules

import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import rs.raf.projekat2.valerija_nagl_RN682018.data.datasources.local.ClassDataBase
import rs.raf.projekat2.valerija_nagl_RN682018.data.datasources.remote.ClassService
import rs.raf.projekat2.valerija_nagl_RN682018.data.repositories.ClassRepository
import rs.raf.projekat2.valerija_nagl_RN682018.data.repositories.ClassRepositoryImpl
import rs.raf.projekat2.valerija_nagl_RN682018.presentation.viewmodel.MainViewModel


val classModule = module {

    viewModel { MainViewModel(classRepository = get()) }

    single<ClassRepository> { ClassRepositoryImpl(localDataSource = get(), remoteDataSource = get()) }

    single { get<ClassDataBase>().getClassDao() }

    single<ClassService> { create(retrofit = get()) }

}
