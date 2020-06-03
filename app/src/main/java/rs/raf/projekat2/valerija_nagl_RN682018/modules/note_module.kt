package rs.raf.projekat2.valerija_nagl_RN682018.modules

import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import rs.raf.projekat2.valerija_nagl_RN682018.data.datasources.local.NoteDataBase
import rs.raf.projekat2.valerija_nagl_RN682018.data.repositories.NoteRepository
import rs.raf.projekat2.valerija_nagl_RN682018.data.repositories.NoteRepositoryImpl
import rs.raf.projekat2.valerija_nagl_RN682018.presentation.viewmodel.NoteViewModel

val noteModule = module {

    viewModel { NoteViewModel(get()) }

    single<NoteRepository> { NoteRepositoryImpl(get()) }

    single { get<NoteDataBase>().getNoteDao() }

}